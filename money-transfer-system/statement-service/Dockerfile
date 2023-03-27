# Use a lightweight base image
FROM openjdk:8

# Expose the port
EXPOSE 8081

# add jar to docker image
ADD target/statement-service.jar statement-service.jar


# Configure the entry point
ENTRYPOINT ["java", "-jar", "/statement-service.jar"]