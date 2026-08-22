FROM eclipse-temurin:25-jdk AS build
WORKDIR /app

COPY . .
RUN ./gradlew backend:bootJar

FROM eclipse-temurin:25-jre
WORKDIR /app
COPY --from=build /app/backend/build/libs/app.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
