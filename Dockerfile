FROM hub.c.163.com/library/tomcat

MAINTAINER liqitian

ENV WORK_PATH /usr/local/tomcat/conf

ENV SERVER_CONF_FILE_NAME server.xml

ADD family-school.war /usr/local/tomcat/webapps/family-school.war

RUN rm $WORK_PATH/$SERVER_CONF_FILE_NAME

COPY  ./$SERVER_CONF_FILE_NAME $WORK_PATH/
EXPOSE 8080
RUN cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
&& echo 'Asia/Shanghai' >/etc/timezone