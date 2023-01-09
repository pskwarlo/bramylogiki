FROM openjdk:15-jdk
COPY BramyLogiki.jar /tmp
COPY javafx-sdk-19 /tmp
COPY myconfigfile.txt /tmp
ENV CONFIG_FILE /tmp/myconfigfile.txt

ENTRYPOINT java -jar /tmp/BramyLogiki.jar
