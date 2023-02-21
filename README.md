# Microservices Spring

- Spring boot 2.5.6
- Spring Data JPA
- Spring Cloud OpenFeign
- Spring Cloud Gateway
- Spring Cloud Sleuth
- Zipkin
- Netflix Eureka Server
- Rabbitmq
- Postgres
- Jib maven plugin
- Docker compose
- k8s

## Run microservices

```$bash
java -jar .\eureka-server\target\eureka-server-0.0.1-SNAPSHOT.jar

java -jar .\notification\target\notification-0.0.1-SNAPSHOT.jar

java -jar .\customer\target\customer-0.0.1-SNAPSHOT.jar

java -jar .\fraud\target\fraud-0.0.1-SNAPSHOT.jar

java -jar .\apigw\target\apigw-0.0.1-SNAPSHOT.jar

java -jar .\apigw\target\apigw-0.0.1-SNAPSHOT.jar
```

## Run jib plugin and generate docker images

```$bash
mvn clean package -P build-docker-image
```

## Deploy postgres database and create dbs

### Deploy Resources

```$bash
kubectl apply -f k8s/minikube/bootstrap/postgres
kubectl apply -f k8s/minikube/bootstrap/rabbitmq
kubectl apply -f k8s/minikube/bootstrap/zipkin

kubectl apply -f k8s/minikube/services/customer
kubectl apply -f k8s/minikube/services/notification
kubectl apply -f k8s/minikube/services/fraud
```

### Delete Resources

```$bash
kubectl delete -f k8s/minikube/bootstrap/postgres
kubectl delete -f k8s/minikube/bootstrap/rabbitmq
kubectl delete -f k8s/minikube/bootstrap/zipkin

kubectl delete -f k8s/minikube/services/customer
kubectl delete -f k8s/minikube/services/notification
kubectl delete -f k8s/minikube/services/fraud
```

### Create dbs

```$bash
kubectl exec -it postgres-0 -- psql -U pblgllgs

create database customer;
create database fraud;
create database notification;
```
