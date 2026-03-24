FROM docker.mirrors.tuna.tsinghua.edu.cn/library/eclipse-temurin:21-jdk-alpine

WORKDIR /app
COPY target/sweet-app.jar app.jar
EXPOSE 8081

ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar --spring.profiles.active=dev"]