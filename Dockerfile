# Use an official OpenJDK runtime as a parent image
FROM eclipse-temurin:17

# Copy the jar file from your local machine to the container
COPY target/user-management-0.0.1-SNAPSHOT.jar app.jar

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]