FROM openjdk:8
ADD /ServiceYota/testService-1.0-SNAPSHOT.jar backend.jar
ENTRYPOINT ["java", "-jar", "backend.jar"]