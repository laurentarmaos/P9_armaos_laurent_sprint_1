FROM openjdk:11-jre

ARG JAR_FILE=target/Mediscreen-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} patient.jar

EXPOSE 8081

CMD ["java", "-jar", "/patient.jar"]