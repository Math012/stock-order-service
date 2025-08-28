FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY build/libs/order-service-0.0.1-SNAPSHOT.jar /app/order-service.jar

EXPOSE 8082

CMD ["java", "-jar", "/app/order-service.jar"]