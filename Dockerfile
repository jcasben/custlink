FROM openjdk:17

WORKDIR /app

CMD ["./mvnw", "clean", "package"]
COPY ./target/custlink-0.0.1-SNAPSHOT.jar /app
EXPOSE 8888
CMD ["java", "-jar", "custlink-0.0.1-SNAPSHOT.jar"]
