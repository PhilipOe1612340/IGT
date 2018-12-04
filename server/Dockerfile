FROM maven:3.5.2-jdk-8-alpine

ENV PORT=6001

COPY pom.xml /tmp/
COPY src /tmp/src/
WORKDIR /tmp/
EXPOSE ${PORT}
LABEL db=${DB}
RUN mvn package
CMD java -jar /tmp/target/IGT.jar $PORT
