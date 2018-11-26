FROM registry-vpc.cn-beijing.aliyuncs.com/rsq-public/tomcat:8.0.50-jre8-memcached-v2

LABEL name="rishiqing-back-end-admin" \
       description="backend admin for rishiqing server V2" \
       maintainer="rishiqing group" \
       org="rishiqing"

# set default time zone to +0800 (Shanghai)
ENV TIME_ZONE=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TIME_ZONE /etc/localtime && echo $TIME_ZONE > /etc/timezone

# path of config file
#ENV RISHIQING_BACK_END_CONFIG_PATH=/etc/rishiqing/rishiqing-v2-main/conf

ENV CATALINA_HOME=/usr/local/tomcat
WORKDIR $CATALINA_HOME

ADD admin.war webapps/admin1.war

#ARG APP_NAME
#ARG TIER_NAME
#ARG LICENSE_KEY

# RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone
#RUN cd $CATALINA_HOME \
#&& wget https://rsqsystem.oss-cn-beijing.aliyuncs.com/serverFile/oneapm/javaagent-linux-x64-lastversion.zip \
#&& unzip javaagent-linux-x64-lastversion.zip  \
#&& cd OneAPM/ \
#&& sed -i "s|app_name = .*$|app_name = ${APP_NAME}|" oneapm.properties \
#&& sed -i "s|tier_name = .*$|tier_name = ${TIER_NAME}|" oneapm.properties \
#&& sed -i "s|license_key = .*$|license_key = ${LICENSE_KEY}|" oneapm.properties \
#&& java -jar oneapm.jar install

#WORKDIR /app

#CMD ["./tomcat/bin/startup.sh"]
