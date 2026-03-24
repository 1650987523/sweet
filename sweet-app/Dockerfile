# 基础镜像：OpenJDK 21（阿里云镜像源）
FROM registry.cn-hangzhou.aliyuncs.com/acs/openjdk:21-jdk-slim

# 设置工作目录
WORKDIR /app

# 复制 JAR 包（从父目录的 target）
COPY target/sweet-app.jar app.jar

# 暴露端口（根据你的 application.yml 配置）
EXPOSE 8081

# 设置时区
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# 启动命令
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar --spring.profiles.active=dev"]