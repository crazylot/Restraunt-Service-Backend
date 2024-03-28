# Use a base image with the desired version of Java
FROM openjdk:17-jdk-slim

# Set the working directory within the container
WORKDIR /app

# Copy the Spring Boot application JAR to the container
COPY target/*.jar /app/app.jar

# Expose the port your application is listening on (if applicable)
EXPOSE 8761

# Run the Spring Boot application when the container starts
CMD ["java", "-jar", "app.jar"]