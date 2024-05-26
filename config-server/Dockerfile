FROM openjdk:17
ARG JAR_FILE=target/config-server-0.0.1.jar
COPY ${JAR_FILE} app-config-server.jar
ENTRYPOINT ["java", "-jar", "app-config-server.jar"]