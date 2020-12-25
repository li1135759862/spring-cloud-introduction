FROM openjdk:8-jdk-alpine
MAINTAINER lxl
ENV TZ=Asia/Shanghai
ENV JAVA_OPTS=""

RUN echo $TZ > /etc/timezone &&\
    mkdir -p /server/log && \
    mkdir -p /server/config && \
    mkdir -p /server/upload/temp
VOLUME /server/log
WORKDIR /server
COPY server-1.0.0-SNAPSHOT.jar /server/server.jar
EXPOSE 8886
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /server/server.jar -Dfile.encoding=utf-8"]
