# 直接用阿里云自带的 Alibaba Linux 3 作为基础镜像（100% 国内，不用拉 Docker Hub）
FROM alinux3

# 切换阿里云 YUM 源（系统包都从国内下）
RUN sed -e 's|^mirrorlist=|#mirrorlist=|g' \
        -e 's|^#baseurl=http://mirrors.aliyun.com|baseurl=http://mirrors.aliyun.com|g' \
        -i /etc/yum.repos.d/*.repo

# 安装 curl
RUN yum install -y curl && yum clean all

# 从清华下载 JDK21（国内地址，你在浏览器也能打开）
RUN curl -fL -o /tmp/jdk.tar.gz https://mirrors.tuna.tsinghua.edu.cn/Adoptium/temurin21-binaries/releases/download/jdk-21.0.2+13/OpenJDK21U-jdk_x64_linux_hotspot_21.0.2_13.tar.gz \
    && mkdir -p /opt/jdk \
    && tar -zxvf /tmp/jdk.tar.gz -C /opt/jdk --strip-components=1 \
    && rm -rf /tmp/jdk.tar.gz

# JDK环境变量
ENV JAVA_HOME=/opt/jdk
ENV PATH=$JAVA_HOME/bin:$PATH
WORKDIR /app
COPY target/sweet-app.jar app.jar
EXPOSE 8081

ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar --spring.profiles.active=dev"]