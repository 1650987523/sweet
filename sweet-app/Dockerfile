#acr镜像
FROM crpi-2ofa7xlrovlzmrxk.cn-hangzhou.personal.cr.aliyuncs.com/sweet-mini/soft:jdk21.0.10

WORKDIR /app
COPY  target/sweet-app.jar /
EXPOSE 8081

ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar sweet-app.jar --spring.profiles.active=dev"]