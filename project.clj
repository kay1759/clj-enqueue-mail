(defproject org.clojars.kay1759/clj-enqueue-mail "0.1.0"
  :description "enqueueing mail to RabbitMQ MailQueue"
  :url "https://github.com/kay1759/clj-enqueue-mail"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [com.novemberain/langohr "5.4.0"]
                 [clojusc/protobuf "3.5.1-v1.1"]
                 [com.google.protobuf/protobuf-java "3.8.0"]]
  :java-source-paths ["src/java"]
  :repl-options {:init-ns clj-enqueue-mail.core})
