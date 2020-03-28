FROM tomcat:8.5.34-jre8-alpine
ARG WAR_FILE
ARG ELASTIC_APM_AGEN_VERSION=0.7.0
COPY ${WAR_FILE} /usr/local/tomcat/app.war
RUN cd /usr/local/tomcat && \
    rm -rf webapps/* && \
    mkdir -p webapps/ROOT && \
    unzip app.war -d webapps/ROOT > /dev/null && \
    rm /usr/local/tomcat/app.war
RUN wget https://search.maven.org/remotecontent?filepath=co/elastic/apm/elastic-apm-agent/${ELASTIC_APM_AGEN_VERSION}/elastic-apm-agent-${ELASTIC_APM_AGEN_VERSION}.jar \
    -O ${HOME}/elastic-apm-agent.jar
CMD ["catalina.sh", "run"]