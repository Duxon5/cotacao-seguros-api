FROM openjdk:17.0.1-jdk-slim
ARG JAR_FILE=target/projeto-cotacao-seguros-api.jar
COPY ./target/projeto-cotacao-seguros-api.jar app.jar
EXPOSE 4321 4322 9090 3100
ENTRYPOINT ["java", "-jar", "/app.jar"]