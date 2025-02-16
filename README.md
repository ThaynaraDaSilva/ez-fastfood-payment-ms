# ez-fastfood-payment-ms
ez-fastfood-payment-ms


https://www.javatodev.com/how-to-use-amazon-sqs-with-spring-boot/

 aws --endpoint-url=http://localhost:4566 sqs send-message --queue-url http://localhost:4566/000000000000/order-payment-queue --message-body '{\"orderId\":1,\"userId\":1,\"amount\":20.5}'
 
 ### Delete process
 aws --endpoint-url=http://localhost:4566 sqs receive-message --queue-url http://localhost:4566/000000000000/order-payment-queue
 
### USEFULL COMMANDS
```
//CREATE QUEUE
awslocal --endpoint-url=https://localhost.localstack.cloud:4566 sqs create-queue --queue-name order-payment-queue --region us-east-1 --attributes file://C:\THAYNARA_DEV\workspaces\ez-fastfood-order-ms\src\main\resources\attributes.json

//CREATE MESSAGE
awslocal sqs send-message --queue-url https://sqs.us-east-1.amazonaws.com/637423288778/ez-fastfood-order-payment-queue-dev --message-body "{\"orderId\":1,\"amount\":50.0}" --region us-east-1 

// LIST QUEUES
aws sqs list-queues --endpoint-url=http://localhost:4566 --region us-east-1

aws sqs send-message --queue-url "https://sqs.us-east-1.amazonaws.com/637423288778/ez-fastfood-order-payment-queue-dev" --message-body '{\"orderId\":1,\"amount\":50.0}' --region us-east-1



```