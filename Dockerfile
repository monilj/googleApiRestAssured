FROM openjdk:8-jre-alpine
EXPOSE 8080
WORKDIR /
COPY ./target/ContractTest-1.0-SNAPSHOT.jar /ContractTest-1.0-SNAPSHOT.jar
ENTRYPOINT [ "sh", "-c", "java -jar /ContractTest-1.0-SNAPSHOT.jar“ ]
