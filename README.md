# ez-fastfood-payment-ms
ez-fastfood-payment-ms


https://www.javatodev.com/how-to-use-amazon-sqs-with-spring-boot/

 aws --endpoint-url=http://localhost:4566 sqs send-message --queue-url http://localhost:4566/000000000000/order-payment-queue --message-body '{\"orderId\":1,\"userId\":1,\"amount\":20.5}'
 
 ### Delete process
 aws --endpoint-url=http://localhost:4566 sqs receive-message --queue-url http://localhost:4566/000000000000/order-payment-queue