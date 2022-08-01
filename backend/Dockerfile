FROM openjdk:11-jdk
COPY target/spring-boot-docker-*.jar app.jar
CMD java -jar application.jar
WORKDIR /root
COPY target/demo*.jar /root/application.jar