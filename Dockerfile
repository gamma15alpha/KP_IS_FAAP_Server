FROM ubuntu:latest

FROM amazoncorretto:21

WORKDIR /app

COPY build/libs/myapp-0.0.1.jar /app/myapp-0.0.1.jar

LABEL authors="Denis"

ENTRYPOINT ["java", "-jar", "/app/myapp-0.0.1.jar"]