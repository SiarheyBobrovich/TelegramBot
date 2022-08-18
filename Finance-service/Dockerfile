FROM openjdk:11-jdk-slim
MAINTAINER Bobrovich.by

COPY target/finance-service-1.0.0-SNAPSHOT.jar finance-service-1.0.0-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod", "/finance-service-1.0.0-SNAPSHOT.jar"]