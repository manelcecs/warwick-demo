##Build stage
FROM maven:3.6.1-jdk-11-slim as build

RUN mkdir -p /docker
WORKDIR /docker
COPY pom.xml /docker
COPY src /docker/src
RUN mvn -f pom.xml clean package

##Run Stage
FROM openjdk:8-alpine
COPY --from=build /docker/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]