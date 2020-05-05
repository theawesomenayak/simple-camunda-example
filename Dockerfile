FROM amazoncorretto:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} simple-camunda-example.jar
ENTRYPOINT ["java","-jar","/simple-camunda-example.jar"]