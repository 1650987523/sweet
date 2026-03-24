# 基础镜像：OpenJDK 21
FROM openjdk:21-jdk-slim

# 设置工作目录
WORKDIR /app

# 复制 JAR 包
COPY target/sweet-admin.jar app.jar

# 暴露端口
EXPOSE 8082

# 设置时区
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# 健康检查
HEALTHCHECK --interval=30s --timeout=3s --start-period=60s --retries=3 \
  CMD curl -f http://localhost:8082/actuator/health || exit 1

# 启动命令
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar --spring.profiles.active=prod"]