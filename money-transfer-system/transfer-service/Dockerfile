# Use a lightweight base image
FROM openjdk:8

# Expose the port
EXPOSE 8080

# add jar to docker image
ADD target/transfer-service.jar transfer-service.jar


# Configure the entry point
ENTRYPOINT ["java", "-jar", "/transfer-service.jar"]