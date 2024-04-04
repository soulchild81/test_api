FROM openjdk:17
WORKDIR /app
ARG JAR_FILE=/build/libs/lezhin_api-0.0.1.jar
ADD ${JAR_FILE} lezhin_api.jar

EXPOSE 9201
ENTRYPOINT ["java","-jar","lezhin_api.jar"]
