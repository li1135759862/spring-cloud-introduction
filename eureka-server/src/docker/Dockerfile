FROM openjdk:8-jdk-alpine
MAINTAINER lxl
ENV TZ=Asia/Shanghai
ENV JAVA_OPTS=""

RUN echo $TZ > /etc/timezone &&\
    mkdir -p /eureka-server/log && \
    mkdir -p /eureka-server/config && \
    mkdir -p /eureka-server/upload/temp
VOLUME /eureka-server/log
WORKDIR /eureka-server
COPY eureka-server-1.0.0-SNAPSHOT.jar /eureka-server/eureka-server.jar
EXPOSE 8899
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /eureka-server/eureka-server.jar -Dfile.encoding=utf-8"]
