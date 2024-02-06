FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY /target/WorkEstimateApi-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 80
CMD ["java", "-jar", "app.jar"]
