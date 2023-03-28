# springboot-rabitmq
RabbitMQ Implementation with Spring Boot + Java 17
endpoint URL : localhost:8080/rabbitmq/sender
Method : POST
Sample Rest JSON Body message.
{
    "orderIdentifier":"menue order 1",
    "orderId":1,
    "orderList":[
        "Order One",
        "Order Two",
        "Order Three",
        "Order Four",
        "Order Five"
    ],
    "customerName" :"Riyaz Ahmed"
}
