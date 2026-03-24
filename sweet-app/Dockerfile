# 基础镜像：OpenJDK 21
FROM openjdk:21-jdk-slim

# 设置工作目录
WORKDIR /app

# 复制 JAR 包（从父目录的 target）
COPY target/sweet-app.jar app.jar

# 暴露端口（根据你的 application.yml 配置）
EXPOSE 8081

# 设置时区
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# JVM 参数
ENV JAVA_OPTS="-Xms512m -Xmx1024m -Djava.security.egd=file:/dev/./urandom"

# 健康检查
HEALTHCHECK --interval=30s --timeout=3s --start-period=60s --retries=3 \
  CMD curl -f http://localhost:8081/actuator/health || exit 1

# 启动命令
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar --spring.profiles.active=prod"]