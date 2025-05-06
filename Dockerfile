FROM gradle:8.13-jdk17 AS build

WORKDIR /app/disabled-escort-server

COPY gradle gradle
COPY gradlew .
COPY build.gradle .
COPY settings.gradle .

RUN ./gradlew dependencies --no-daemon

COPY src src

RUN ./gradlew bootJar --no-daemon

FROM eclipse-temurin:17-jre AS runtime

WORKDIR /app/disabled-escort-server

COPY --from=build /app/disabled-escort-server/build/libs/*.jar disabled-escort-server.jar

EXPOSE $ESCORT_SERVER_PORT

ENTRYPOINT ["java", "-jar", "disabled-escort-server.jar"]