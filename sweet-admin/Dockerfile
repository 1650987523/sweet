# ---------------- 阿里云官方公开镜像 · 不用登录 · 直接拉 ----------------
FROM registry.aliyuncs.com/acs/debian:bookworm-slim

# 切换阿里云 apt 源（超快）
RUN sed -i 's/deb.debian.org/mirrors.aliyun.com/g' /etc/apt/sources.list \
 && sed -i 's/security.debian.org/mirrors.aliyun.com/g' /etc/apt/sources.list

# 从清华下载 JDK21（国内！你看得见的地址！）
RUN apt-get update && apt-get install -y curl --no-install-recommends \
 && curl -fL -o /tmp/jdk.tar.gz https://mirrors.tuna.tsinghua.edu.cn/Adoptium/temurin21-binaries/releases/download/jdk-21.0.2+13/OpenJDK21U-jdk_x64_linux_hotspot_21.0.2_13.tar.gz \
 && mkdir -p /opt/jdk \
 && tar -zxvf /tmp/jdk.tar.gz -C /opt/jdk --strip-components=1 \
 && rm -rf /tmp/jdk.tar.gz /var/lib/apt/lists/*

# JDK环境变量
ENV JAVA_HOME=/opt/jdk
ENV PATH=$JAVA_HOME/bin:$PATH
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