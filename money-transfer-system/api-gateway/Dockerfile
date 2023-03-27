# Use a lightweight base image
FROM openjdk:8

# Expose the port
EXPOSE 8989

# add jar to docker image
ADD target/api-gateway.jar api-gateway.jar


# Configure the entry point
ENTRYPOINT ["java", "-jar", "/api-gateway.jar"]