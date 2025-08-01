FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

COPY . /app

RUN ./mvnw clean install -DskipTests

EXPOSE 8080

CMD ["./mvnw", "spring-boot:run"]