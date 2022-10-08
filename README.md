# clj-enqueue-mail

This library provides enqueueing mail to [MailQueue](https://github.com/kay1759/mail_queue) in Clojure.

## Installation

[![Clojars Project](https://img.shields.io/clojars/v/org.clojars.kay1759/clj-enqueue-mail.svg)](https://clojars.org/org.clojars.kay1759/clj-enqueue-mail)

### development

A file 'src/java/clj_enqueue_mail/email/Example.java' are created from 'resources/proto/clj_enqueue_mail/email.proto' by the command below:
```
protoc -I=/usr/include -I=/usr/local/include -I=resources/proto --java_out=src/java resources/proto/clj_enqueue_mail/*.proto
```

## Usage
### Usage:
```
(require '[clj-enqueue-mail.core :as cem])

(cem/open <amqp server> <amqp server user> <amqp server password> <exchange> <queue> <queue error>)

(cem/send_mail
  <sender address>
  <receiver address>
  <title>
  <content (plain text)>)
  
(cem/close)
```

### Example:

```
(cem/open "localhost" "app" "app"
    "gen_server_mail_exchange" "gen_server_mail_queue" "gen_server_mail_queue_error")

(cem/send_mail
  "sender@example.com"
  "receiver@example.com"
  "Greeting"
  "Long time no see.\nHow are you ?")
  
(cem/close)
```

### Data Format:
    message Email {
      required string from = 1;
      required string to = 2; 
      required string subject = 3;
      optional string text_body = 4; 
      optional string html_body = 5;
    }

## Tests ##
```
lein test
```

## References:
- [MailQueue](https://github.com/kay1759/mail_queue)
- [Protocol Buffers Official](https://developers.google.com/protocol-buffers/)
- [clojusc/protobuf](https://github.com/clojusc/protobuf)
- [Langohr, a feature-rich Clojure RabbitMQ client](https://github.com/michaelklishin/langohr)

## Licence:

[MIT]

## Author

[Katsuyoshi Yabe](https://github.com/kay1759)
