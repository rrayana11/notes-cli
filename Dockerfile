FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY src/ ./src/
RUN javac src/com/example/*.java
VOLUME /app/data
ENTRYPOINT ["java", "-cp", "src", "com.example.App"]
