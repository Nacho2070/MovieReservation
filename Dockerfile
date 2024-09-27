FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/MovieReservation-0.0.1.jar
COPY ${JAR_FILE} movieReservation
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app_movie_reservation"]