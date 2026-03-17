FROM eclipse-temurin:21-jre

WORKDIR /app

COPY poetry-stream.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java","-XX:MaxRAMPercentage=75","-jar","/app/app.jar","--spring.profiles.active=prod"]