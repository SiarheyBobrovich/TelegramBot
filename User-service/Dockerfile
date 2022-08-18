FROM openjdk:11-jdk-slim
MAINTAINER Bobrovich.by

COPY target/user-service-1.0.0-SNAPSHOT.jar user-service-1.0.0-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod", "/user-service-1.0.0-SNAPSHOT.jar"]