FROM openjdk:11.0.11-jre-slim
COPY target/my-anime-service.war my-anime-service.war
ENTRYPOINT ["java","-jar","/my-anime-service.war"]