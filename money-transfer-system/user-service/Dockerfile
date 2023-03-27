# Use a lightweight base image
FROM openjdk:8

# Expose the port
EXPOSE 8082

# add jar to docker image
ADD target/user-service.jar user-service.jar


# Configure the entry point
ENTRYPOINT ["java", "-jar", "/user-service.jar"]