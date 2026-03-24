#acr镜像
FROM crpi-2ofa7xlrovlzmrxk.cn-hangzhou.personal.cr.aliyuncs.com/sweet-mini/soft:21.0.10-cds

# 工作目录
WORKDIR /app

# 复制jar包
COPY ./target/sweet-admin.jar /

# 暴露端口
EXPOSE 8082

# 时区配置
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# 启动命令
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar admin.jar --spring.profiles.active=dev"]