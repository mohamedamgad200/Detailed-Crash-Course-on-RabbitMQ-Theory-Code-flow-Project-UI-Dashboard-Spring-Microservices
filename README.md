# RabbitMQ Practice

My personal practice repo for learning **RabbitMQ** with **Spring Boot microservices**.
It follows a crash course covering theory, code flow, a small project, and the management UI dashboard.

> This is a learning project, not production code.

## What I'm Practicing

- Message broker basics and why microservices use async messaging
- Producer, Exchange, Queue, Binding, Routing Key, Consumer
- Exchange types: Direct, Fanout, Topic, Headers
- Sending and receiving messages with Spring AMQP
- Monitoring queues and exchanges from the RabbitMQ dashboard

## Tech Stack

- Java
- Spring Boot (Spring AMQP)
- RabbitMQ
- Maven
- Docker

## Run RabbitMQ

```bash
docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management
```

Dashboard: http://localhost:15672 (username `guest`, password `guest`)

## Run the Services

Start the consumer first, then the producer:

```bash
cd consumer-service
mvn spring-boot:run
```

```bash
cd producer-service
mvn spring-boot:run
```

> Replace the folder names above with the ones in this repo.

## Message Flow

```
Producer --> Exchange --> Queue --> Consumer
```

## Notes

- Add what you learned, problems you hit, and how you fixed them here.

## Author

Mohamed Amgad - [@mohamedamgad200](https://github.com/mohamedamgad200)
