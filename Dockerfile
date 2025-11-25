# Use Java 17
FROM eclipse-temurin:17-jdk-alpine


# Set working directory
WORKDIR /app

# Copy Maven wrapper files
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Download dependencies (cached layer)
RUN ./mvnw dependency:go-offline -B

# Copy source code
COPY src ./src

# Build the application
RUN ./mvnw clean package -DskipTests

# Copy the JAR file
RUN cp target/*.jar app.jar

# Expose port 8089 (your custom port)
EXPOSE 8089

# Set environment variable for Docker profile
ENV SPRING_PROFILES_ACTIVE=docker

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]