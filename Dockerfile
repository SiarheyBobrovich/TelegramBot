FROM openjdk:11-jdk-slim
MAINTAINER Bobrovich.by

COPY target/TelegramBot-1.0.0-SNAPSHOT.jar telegramBot-1.0.0-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod", "/telegramBot-1.0.0-SNAPSHOT.jar"]