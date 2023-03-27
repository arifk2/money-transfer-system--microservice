# Use a lightweight base image
FROM openjdk:8

# Expose the port
EXPOSE 9191

# add jar to docker image
ADD target/cloud-config-server.jar cloud-config-server.jar


# Configure the entry point
ENTRYPOINT ["java", "-jar", "/cloud-config-server.jar"]