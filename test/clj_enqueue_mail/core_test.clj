(ns clj-enqueue-mail.core-test
  (:require
   [clj-enqueue-mail.core :refer :all]
   [clojure.test :refer :all]
   [protobuf.core :as protobuf])
  (:import
   (clj_enqueue_mail.email Example$Email)))

(def email-schema (protobuf/create Example$Email
                                   {:from "",
                                    :to "",
                                    :subject "",
                                    :text_body ""}))

(deftest a-test
  (testing "encode and decode protocol buffer 'Email'"
    (let [test-mail (->protobuf
                     "sender@example.com"
                     "receiver@example.com"
                     "Greeting"
                     "Long time no see.\nHow are you ?")
          test-bytes (->bytes test-mail)]
      (is (= test-mail (protobuf/bytes-> email-schema test-bytes))))))
