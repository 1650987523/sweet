# 基础镜像（Linux amd64 官方标准系统）
FROM debian:bookworm-slim

# 下载并安装清华源 OpenJDK 21（x64/linux）
RUN apt-get update && apt-get install -y curl --no-install-recommends \
    && curl -fL -o /tmp/jdk.tar.gz https://mirrors.tuna.tsinghua.edu.cn/Adoptium/21/jdk/x64/linux/OpenJDK21U-jdk_x64_linux_hotspot_21.0.10_7.tar.gz \
    && mkdir -p /opt/jdk && tar -zxvf /tmp/jdk.tar.gz --strip-components=1 -C /opt/jdk \
    && rm -rf /tmp/jdk.tar.gz /var/lib/apt/lists/*

# JDK 环境变量
ENV JAVA_HOME=/opt/jdk
ENV PATH=$JAVA_HOME/bin:$PATH

# 设置工作目录
WORKDIR /app

# 复制 JAR 包
COPY target/sweet-app.jar app.jar

# 暴露端口
EXPOSE 8081

# 设置时区
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# 启动命令
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar --spring.profiles.active=dev"]