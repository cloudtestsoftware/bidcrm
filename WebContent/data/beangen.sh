#!/bin/sh

#To change into unix format open the file in vi
# Then type :set ff=unix and save the file


export JAVA_HOME=/home/srimanta/Documents/jdk1.8.0_121
export DATA_PATH=/home/srimanta/erp/bidcrm/WebContent/data
export JSP_HOME=/home/srimanta/erp/bidcrm
export JAR_PATH=/home/srimanta/erp/bidcrm/WebContent/WEB-INF/lib

export PATH=$JAVA_HOME/bin:$PATH

export SPRING_JARS=$JAR_PATH/org.springframework.beans-3.0.5.RELEASE.jar:$JAR_PATH/org.springframework.core-3.0.5.RELEASE.jar:$JAR_PATH/org.springframework.jdbc-3.0.5.RELEASE.jar:$JAR_PATH/org.springframework.transaction-3.0.5.RELEASE.jar

export JAVA_CLASSPATH=.$JAVA_CLASSPATH:$JAR_PATH/servlet.jar:$JAR_PATH/bidcrminfra.jar:$SPRING_JARS:$JAR_PATH/ojdbc6.jar:$JAR_PATH/dom4j-1.6.1.jar:$JAVA_HOME/bin:$JAVA_HOME/lib:$JAVA_HOME/lib/tools.jar:$JAR_PATH/xerces.jar:$JAR_PATH/tomcat-dbcp.jar:$JAR_PATH/commons-logging-1.1.jar:

#generate all objects
#java -classpath $JAVA_CLASSPATH cms.service.gen.beangen  -beanpath $JSP_HOME/src  -app bidcrm -inifile $DATA_PATH/context.xml

java -classpath $JAVA_CLASSPATH cms.service.gen.beangen -bean FinancialYear -beanpath $JSP_HOME/src  -app bidcrm -inifile $DATA_PATH/context.xml

java -classpath $JAVA_CLASSPATH cms.service.gen.beangen -bean CampaignCalendar -beanpath $JSP_HOME/src  -app bidcrm -inifile $DATA_PATH/context.xml
java -classpath $JAVA_CLASSPATH cms.service.gen.beangen -bean TravelCalendar -beanpath $JSP_HOME/src  -app bidcrm -inifile $DATA_PATH/context.xml
java -classpath $JAVA_CLASSPATH cms.service.gen.beangen -bean EventCalendar -beanpath $JSP_HOME/src  -app bidcrm -inifile $DATA_PATH/context.xml
#java -classpath $JAVA_CLASSPATH cms.service.gen.beangen -bean EventPortal -beanpath $JSP_HOME/src  -app bidcrm -inifile $DATA_PATH/context.xml
#java -classpath $JAVA_CLASSPATH cms.service.gen.beangen -bean VedioPortal -beanpath $JSP_HOME/src  -app bidcrm -inifile $DATA_PATH/context.xml
#java -classpath $JAVA_CLASSPATH cms.service.gen.beangen -bean SurveyPortal -beanpath $JSP_HOME/src  -app bidcrm -inifile $DATA_PATH/context.xml
#java -classpath $JAVA_CLASSPATH cms.service.gen.beangen -bean CustomPortal -beanpath $JSP_HOME/src  -app bidcrm -inifile $DATA_PATH/context.xml
#java -classpath $JAVA_CLASSPATH cms.service.gen.beangen -bean CustomLinks -beanpath $JSP_HOME/src  -app bidcrm -inifile $DATA_PATH/context.xml
#java -classpath $JAVA_CLASSPATH cms.service.gen.beangen -bean EmailAttribute -beanpath $JSP_HOME/src  -app bidcrm -inifile $DATA_PATH/context.xml

#java -classpath $JAVA_CLASSPATH cms.service.gen.beangen -bean EmailDocs -beanpath $JSP_HOME/src  -app bidcrm -inifile $DATA_PATH/context.xml
