FROM openjdk:16-jdk-alpine
ADD target/portfolio-service-0.0.1-SNAPSHOT.jar cryptoboard-portfolio-service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "cryptoboard-portfolio-service.jar"]