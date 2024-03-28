FROM openjdk:17-slim

EXPOSE 8080

ADD target/Inditex-0.0.1-SNAPSHOT.jar inditex-application.jar

ENTRYPOINT ["java", "-jar", "/inditex-application.jar"]
