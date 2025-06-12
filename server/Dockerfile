# Use official OpenJDK 17 image
FROM eclipse-temurin:17-jdk

# Set working directory
WORKDIR /app

# Copy everything
COPY . .

# Build the application using Maven wrapper
RUN chmod +x mvnw && ./mvnw clean install

# Expose the port your app runs on (change if needed)
EXPOSE 8080

# Run the jar file
CMD ["java", "-jar", "target/AgentManagementBE-0.0.1-SNAPSHOT.jar"]
