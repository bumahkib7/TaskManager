
# Pull the openjdk-17:1.14 image from the Red Hat registry
FROM registry.access.redhat.com/ubi8/openjdk-17:1.14 as build

# Set the language environment to US English
ENV LANGUAGE='en_US:en'

# Copy the library files from the target/quarkus-app/lib/ directory to the deployments/lib/ directory, setting the owner to 185
COPY --chown=185 target/quarkus-app/lib/ /deployments/lib/

# Copy the jar files from the target/quarkus-app/ directory to the deployments/ directory, setting the owner to 185
COPY --chown=185 target/quarkus-app/*.jar /deployments/

# Copy the application files from the target/quarkus-app/app/ directory to the deployments/app/ directory, setting the owner to 185
COPY --chown=185 target/quarkus-app/app/ /deployments/app/

# Copy the quarkus files from the target/quarkus-app/quarkus/ directory to the deployments/quarkus/ directory, setting the owner to 185
COPY --chown=185 target/quarkus-app/quarkus/ /deployments/quarkus/

# Expose port 8080
EXPOSE 8080

# Set the user to 185
USER 185

# Set the Java options to allow the application to be hosted on any IP address and use the JBoss log manager
ENV JAVA_OPTS="-Dquarkus.http.host=0.0.0.0 -Djava.util.logging.manager=org.jboss.logmanager.LogManager"

# Set the environment variable for the application jar
ENV JAVA_APP_JAR="/deployments/quarkus-run.jar"

