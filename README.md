# Microservices Spring

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

### Deploy StatefulSet

```$bash
kubectl apply -f k8s/minikube/bootstrap/postgres
kubectl apply -f k8s/minikube/bootstrap/rabbitmq
kubectl apply -f k8s/minikube/bootstrap/zipkin
```

### Delete Resources

```$bash
kubectl delete -f k8s/minikube/bootstrap/postgres
kubectl delete -f k8s/minikube/bootstrap/rabbitmq
kubectl delete -f k8s/minikube/bootstrap/zipkin
```

### Create dbs

```$bash
kubectl exec -it postgres-0 -- psql -U pblgllgs

create database customer;
create database fraud;
create database notification;
```
