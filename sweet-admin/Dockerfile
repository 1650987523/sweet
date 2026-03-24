FROM debian:bookworm-slim

RUN apt-get update && apt-get install -y curl --no-install-recommends \
    && curl -fL -o /tmp/jdk.tar.gz https://mirrors.tuna.tsinghua.edu.cn/Adoptium/21/jdk/x64/linux/OpenJDK21U-jdk_x64_linux_hotspot_21.0.10_7.tar.gz \
    && mkdir -p /opt/jdk \
    && tar -zxvf /tmp/jdk.tar.gz --strip-components=1 -C /opt/jdk \
    && rm -rf /tmp/jdk.tar.gz /var/lib/apt/lists/*

ENV JAVA_HOME=/opt/jdk
ENV PATH=$JAVA_HOME/bin:$PATH

WORKDIR /app
COPY target/sweet-app.jar app.jar
EXPOSE 8081

ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar --spring.profiles.active=dev"]