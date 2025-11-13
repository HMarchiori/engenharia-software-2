FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 8120
ENTRYPOINT ["java","-jar","/app/app.jar"]