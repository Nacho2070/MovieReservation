FROM azul/zulu-openjdk:17-latest
ARG JAR_FILE=./target/MovieReservation-0.0.1.jar
COPY ${JAR_FILE} movieReservation.app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "movieReservation"]