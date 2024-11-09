# Patterns of Distributted Systems

## Summary

The main idea of ​​this lab is to develop a basic robust distributed system that demonstrates the key principles of scalability, fault tolerance, and dynamic service discovery. A replicated key-value store capable of handling node failures and dynamically accommodating new nodes is implemented, ensuring state consistency across the system. It also includes the creation of a simple web application that allows clients to register their names, with data stored in the replicated structure along with timestamps.

The architecture incorporates a load balancer, implemented in Spring, that uses a round-robin strategy to evenly distribute requests across the backend services responsible for data storage. Additionally, a service discovery mechanism will allow backend services to register themselves, allowing the load balancer to dynamically track available instances. Basically, it demonstrates in a practical way the creation of a resilient, real-time system with distributed data handling, load balancing, and service discovery.

## Features

#### Java
```
Java version: 17
```
### Run Instructions

1. Start Eureka Server: (***port*** 8325) [serviceDiscovery](https://github.com/nduran06/AYGO-Lab2-Distributed-patterns/tree/main/serviceDiscovery "serviceDiscovery")
```
mvn spring-boot:run
```
2.  Start Load Balancer: (***port*** 8764) [loadBalancer](https://github.com/nduran06/AYGO-Lab2-Distributed-patterns/tree/main/loadBalancer "loadBalancer")
```
mvn spring-boot:run
```
3.  Start Storage Node(s): (***port*** *random* You need to assign it: 8081, 8082, ...) [nodeStorageApp](https://github.com/nduran06/AYGO-Lab2-Distributed-patterns/tree/main/nodeStorageApp "nodeStorageApp")
```
mvn spring-boot:run -Drun.arguments="--server.port=<port>"
```
4.  Start Web Client: (***port*** 3000) [webClient](https://github.com/nduran06/AYGO-Lab2-Distributed-patterns/tree/main/webClient "webClient")
```
mvn spring-boot:run
```
