FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} BranchDelegation.jar
ENTRYPOINT ["java","-jar","/BranchDelegation.jar"]