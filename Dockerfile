FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline -B
COPY src ./src
RUN ./mvnw clean package -DskipTests
EXPOSE 8282
CMD ["java", "-jar", "target/smart-interview-pre-platform-0.0.1-SNAPSHOT.jar"]