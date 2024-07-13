FROM amazoncorretto:17-alpine-jdk

WORKDIR /app
COPY ./build/libs/community-0.0.1-SNAPSHOT.jar /app/uh-this.jar

ENV TZ=Asia/Seoul

CMD ["java", "-jar", "uh-this.jar"]
