FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} statistic-service.jar
ENTRYPOINT ["java","-jar","/statistic-service.jar"]