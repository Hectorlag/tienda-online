FROM openjdk:17
ARG JAR_FILE=target/compras-0.0.1.jar
COPY ${JAR_FILE} app_compras.jar
ENTRYPOINT ["java", "-jar", "app_compras.jar"]