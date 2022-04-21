#FROM openjdk:8-jdk-alpine
FROM openjdk:8
MAINTAINER sumanasahabits
VOLUME /tmp
ARG JAR_FILE=target/prodmicroservice-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} prodmicroservice-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/prodmicroservice-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080
