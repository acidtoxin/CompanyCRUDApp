FROM openjdk:21-jdk
VOLUME /tmp
COPY target/company-0.0.1-SNAPSHOT.jar company-service.jar
ENTRYPOINT ["java", "-jar", "/company-service.jar"]