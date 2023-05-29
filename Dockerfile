FROM openjdk:11.0.11-jre-slim
COPY target/my-anime-service.war home/my-anime-service.war
COPY target/classes/anime.db home/anime.db
ENTRYPOINT ["java","-jar","/home/my-anime-service.war"]