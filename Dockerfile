FROM openjdk:11
COPY  build/libs/spring-cognito-sample-0.0.1-SNAPSHOT.jar /var/myapp/spring-cognito-sample.jar
WORKDIR /var/myapp
EXPOSE 8080
ENTRYPOINT ["java","-jar","spring-sample.jar"]
