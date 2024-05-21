FROM bellsoft/liberica-runtime-container:jre-17-stream-musl
COPY target/theatre-service-0.0.1-SNAPSHOT.jar theatre-service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/theatre-service.jar"]