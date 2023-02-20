FROM openjdk:11-jre

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} Mediscreen-0.0.1-SNAPSHOT.jar

EXPOSE 8081

CMD ["java", "-jar", "/Mediscreen-0.0.1-SNAPSHOT.jar"]