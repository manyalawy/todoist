# define base docker image
FROM openjdk:18
LABEL maintainer="TheScalables"
ADD target/demo-0.0.1-SNAPSHOT.jar demo7.jar
ENTRYPOINT ["java", "-jar", "demo7.jar"]