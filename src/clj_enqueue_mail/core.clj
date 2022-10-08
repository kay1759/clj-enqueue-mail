(ns clj-enqueue-mail.core
  (:require
   [langohr.basic   :as lb]
   [langohr.channel :as lch]
   [langohr.core    :as rmq]
   [protobuf.core :as protobuf])
  (:import
   (clj_enqueue_mail.email Example$Email)))

(def connection (atom #{}))

(defn open
  ([host port username password vhost exchange queue queue_error]
   (let [conn
         (rmq/connect
          {:host host
           :port port
           :username username
           :password password
           :vhost vhost})
         channel (lch/open conn)]
     (reset! connection
             {:conn conn
              :channel channel
              :exchange exchange
              :queue queue
              :queue_error queue_error})))

  ([host username password vhost exchange queue queue_error]
   (open host 5672 username password vhost exchange queue queue_error))

  ([host username password exchange queue queue_error]
   (open host username password "/" exchange queue queue_error)))

(defn publish
  [payload]
  (let [{:keys [channel exchange queue]} @connection]
    (if (and (not= channel "") (not= exchange "") (not= queue ""))
      (lb/publish channel
                  exchange
                  queue
                  payload))))

(defn close
  []
  (let [{:keys [conn channel]} @connection]
    (rmq/close channel)
    (rmq/close conn))
  (reset! connection #{}))

(defn ->protobuf
  [sender receiver subject content]
  (protobuf/create Example$Email
                   {:from sender,
                    :to receiver,
                    :subject subject,
                    :text_body content}))

(defn ->bytes
  [buf]
  (protobuf/->bytes buf))

(defn send_mail
  [sender receiver subject content]
  (->
   (->protobuf sender receiver subject content)
   (->bytes)
   (publish)))
