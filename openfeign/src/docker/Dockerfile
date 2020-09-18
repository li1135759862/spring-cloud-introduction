FROM openjdk:8-jdk-alpine
MAINTAINER lxl
ENV TZ=Asia/Shanghai
ENV JAVA_OPTS=""

RUN echo $TZ > /etc/timezone &&\
    mkdir -p /openfeign/log && \
    mkdir -p /openfeign/config && \
    mkdir -p /openfeign/upload/temp
VOLUME /openfeign/log
WORKDIR /openfeign
COPY openfeign-1.0.0-SNAPSHOT.jar /openfeign/openfeign.jar
EXPOSE 8887
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /openfeign/openfeign.jar -Dfile.encoding=utf-8"]
