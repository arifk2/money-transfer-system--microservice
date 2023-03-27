# Use a lightweight base image
FROM openjdk:8

# Expose the port
EXPOSE 8761

# add jar to docker image
ADD target/service-registry.jar service-registry.jar


# Configure the entry point
ENTRYPOINT ["java", "-jar", "/service-registry.jar"]