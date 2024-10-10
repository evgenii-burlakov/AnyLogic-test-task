FROM maven:3.8.1-openjdk-17-slim AS build

COPY pom.xml /srv/${SERVICE_DIR}/
COPY src /srv/${SERVICE_DIR}/src

WORKDIR /srv/${SERVICE_DIR}

RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim

COPY --from=build /srv/${SERVICE_DIR}/target/*.jar app.jar
COPY --from=build /srv/${SERVICE_DIR}/src/main/resources/config /config

CMD ["java", "-jar", "app.jar"]