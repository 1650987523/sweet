# 基础镜像：OpenJDK 21
FROM bellsoft/liberica-openjdk-rocky:21.0.8-cds

# 设置工作目录
WORKDIR /app

# 复制 JAR 包
COPY target/sweet-admin.jar admin.jar

# 暴露端口
EXPOSE 8082

# 设置时区
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# 启动命令
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar admin.jar --spring.profiles.active=dev"]