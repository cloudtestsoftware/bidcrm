#!/bin/sh

#To change into unix format open the file in vi
# Then type :set ff=unix and save the file

export JAVA_HOME=/home/srimanta/Documents/jdk1.8.0_121
export DATA_PATH=/home/srimanta/erp/bidcrm/WebContent/data
export JAR_PATH=/home/srimanta/erp/bidcrm/WebContent/WEB-INF/lib
export PATH=$JAVA_HOME/bin:$PATH

export SPRING_JARS=$JAR_PATH/org.springframework.beans-3.0.5.RELEASE.jar:$JAR_PATH/org.springframework.core-3.0.5.RELEASE.jar:$JAR_PATH/org.springframework.jdbc-3.0.5.RELEASE.jar:$JAR_PATH/org.springframework.transaction-3.0.5.RELEASE.jar

export JAVA_CLASSPATH=.$JAVA_CLASSPATH:$JAR_PATH/servlet.jar:$JAR_PATH/bidcrminfra.jar:$SPRING_JARS:$JAR_PATH/ojdbc6.jar:$JAR_PATH/dom4j-1.6.1.jar:$JAVA_HOME/bin:$JAVA_HOME/lib:$JAVA_HOME/lib/tools.jar:$JAR_PATH/xerces.jar:$JAR_PATH/tomcat-dbcp.jar:$JAR_PATH/commons-logging-1.1.jar:

### Import Full schema and compile full
#java -classpath $JAVA_CLASSPATH cms.service.gen.datagen -import schema -file $DATA_PATH/crm_service.dat  -compile -log cms.log -inifile $DATA_PATH/context.xml

### Import Single table and No compile
#java -classpath $JAVA_CLASSPATH cms.service.gen.datagen -import schema -file $DATA_PATH/crm_service.dat -table CampaignCalendar -compile  -log cms.log -inifile $DATA_PATH/context.xml
java -classpath $JAVA_CLASSPATH cms.service.gen.datagen -import schema -file $DATA_PATH/crm_service.dat -table TravelCalendar -compile  -log cms.log -inifile $DATA_PATH/context.xml
#java -classpath $JAVA_CLASSPATH cms.service.gen.datagen -import schema -file $DATA_PATH/crm_service.dat -table SamplesPortal -compile  -log cms.log -inifile $DATA_PATH/context.xml
#java -classpath $JAVA_CLASSPATH cms.service.gen.datagen -import schema -file $DATA_PATH/crm_service.dat -table EventPortal -compile  -log cms.log -inifile $DATA_PATH/context.xml
#java -classpath $JAVA_CLASSPATH cms.service.gen.datagen -import schema -file $DATA_PATH/crm_service.dat -table VedioPortal -compile   -log cms.log -inifile $DATA_PATH/context.xml
#java -classpath $JAVA_CLASSPATH cms.service.gen.datagen -import schema -file $DATA_PATH/crm_service.dat -table SurveyPortal -compile   -log cms.log -inifile $DATA_PATH/context.xml

#java -classpath $JAVA_CLASSPATH cms.service.gen.datagen -import schema -file $DATA_PATH/crm_service.dat -table CustomPortal -compile  -log cms.log -inifile $DATA_PATH/context.xml
#java -classpath $JAVA_CLASSPATH cms.service.gen.datagen -import schema -file $DATA_PATH/crm_service.dat -table CustomLinks -compile  -log cms.log -inifile $DATA_PATH/context.xml
#java -classpath $JAVA_CLASSPATH cms.service.gen.datagen -import schema -file $DATA_PATH/crm_service.dat -table OrderItemCount -compile  -log cms.log -inifile $DATA_PATH/context.xml
#java -classpath $JAVA_CLASSPATH cms.service.gen.datagen -import schema -file $DATA_PATH/crm_service.dat -table StorePartCount -compile  -log cms.log -inifile $DATA_PATH/context.xml
#java -classpath $JAVA_CLASSPATH cms.service.gen.datagen -import schema -file $DATA_PATH/crm_service.dat -table SampleOrderItem -compile  -log cms.log -inifile $DATA_PATH/context.xml
#java -classpath $JAVA_CLASSPATH cms.service.gen.datagen -import schema -file $DATA_PATH/crm_service.dat -table SmapleOrderItem -compile  -log cms.log -inifile $DATA_PATH/context.xml

#java -classpath $JAVA_CLASSPATH cms.service.gen.datagen -import schema -file $DATA_PATH/crm_service.dat -table Samples   -log cms.log -inifile $DATA_PATH/context.xml
#java -classpath $JAVA_CLASSPATH cms.service.gen.datagen -import schema -file $DATA_PATH/crm_service.dat -table EmailTemplate   -log cms.log -inifile $DATA_PATH/context.xml
#java -classpath $JAVA_CLASSPATH cms.service.gen.datagen -import schema -file $DATA_PATH/crm_service.dat -table SampleReserve   -log cms.log -inifile $DATA_PATH/context.xml
#java -classpath $JAVA_CLASSPATH cms.service.gen.datagen -import schema -file $DATA_PATH/crm_service.dat -table CmpEmailList   -log cms.log -inifile $DATA_PATH/context.xml
#java -classpath $JAVA_CLASSPATH cms.service.gen.datagen -import schema -file $DATA_PATH/crm_service.dat -table Samples   -log cms.log -inifile $DATA_PATH/context.xml
#java -classpath $JAVA_CLASSPATH cms.service.gen.datagen -import schema -file $DATA_PATH/crm_service.dat -table EmailContact -compile  -log cms.log -inifile $DATA_PATH/context.xml
#java -classpath $JAVA_CLASSPATH cms.service.gen.datagen -import schema -file $DATA_PATH/crm_service.dat -table SampleOrderItem -compile  -log cms.log -inifile $DATA_PATH/context.xml
#java -classpath $JAVA_CLASSPATH cms.service.gen.datagen -import schema -file $DATA_PATH/crm_service.dat -table SmapleOrderItem -compile  -log cms.log -inifile $DATA_PATH/context.xml



#java -classpath $JAVA_CLASSPATH cms.service.gen.datagen -import schema -file $DATA_PATH/crm_service.dat -table Bids -compile -log cms.log -inifile $DATA_PATH/context.xml
#java -classpath $JAVA_CLASSPATH cms.service.gen.datagen -import schema -file $DATA_PATH/crm_service.dat -table QuoteResource -compile -log cms.log -inifile $DATA_PATH/context.xml
#java -classpath $JAVA_CLASSPATH cms.service.gen.datagen -import schema -file $DATA_PATH/crm_service.dat -table BidRequest -compile -log cms.log -inifile $DATA_PATH/context.xml


#java -classpath $JAVA_CLASSPATH% cms.service.gen.datagen -import jobcode  -file $DATA_PATH/cmsjobcodenew.dat -dbtype oracle  -log cms.log -inifile $DATA_PATH/context.xml

#java -classpath $JAVA_CLASSPATH% cms.service.gen.datagen -import rule -file $DATA_PATH/crmruleraw.dat  -log rule.log -inifile $DATA_PATH/context.xml


#java -classpath $JAVA_CLASSPATH% cms.service.gen.datagen -export rule -file $DATA_PATH/crmruleraw.dat  -log rule.log -inifile $DATA_PATH/context.xml

#### import full schema without compile
#java -classpath $JAVA_CLASSPATH cms.service.gen.datagen -import schema -file $DATA_PATH/crm_service.dat  -log cms.log -inifile $DATA_PATH/context.xml

#### import single table without compile
#java -classpath $JAVA_CLASSPATH cms.service.gen.datagen -import schema -file $DATA_PATH/crm_service.dat -table QuizReply  -log cms.log -inifile $DATA_PATH/context.xml

#### compile all views
#java -classpath $JAVA_CLASSPATH cms.service.gen.datagen  -compileview -log cms.log -inifile $DATA_PATH/context.xml
