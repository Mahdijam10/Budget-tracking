FROM eclipse-temurin:17-jre-alpine
VOLUME /tmp
COPY target/Budget_Tracking-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]


EXPOSE 8089
