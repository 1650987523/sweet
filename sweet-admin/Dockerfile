# 清华官方最小 JDK21 镜像（linux/amd64）
FROM docker.mirrors.tuna.tsinghua.edu.cn/library/eclipse-temurin:21-jdk-alpine

# 工作目录
WORKDIR /app

# 复制jar包
COPY target/sweet-admin.jar admin.jar

# 暴露端口
EXPOSE 8082

# 时区配置
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# 启动命令
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar admin.jar --spring.profiles.active=dev"]