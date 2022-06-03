# define base docker image
FROM openjdk:18
LABEL maintainer="TheScalables"
ADD target/board-1.0-SNAPSHOT.jar demo7.jar
ENTRYPOINT ["java", "-jar", "demo7.jar"]
