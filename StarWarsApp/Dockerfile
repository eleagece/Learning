FROM openjdk:11-jdk
EXPOSE 6969
COPY target/starwars-1.0.0.jar starwars-1.0.0-docker.jar
ENTRYPOINT ["java","-jar","/starwars-1.0.0-docker.jar"]