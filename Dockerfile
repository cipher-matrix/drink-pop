FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/biri-marung-0.0.1-SNAPSHOT.jar biri-marung.jar
ENTRYPOINT ["java","-jar","/biri-marung.jar"]