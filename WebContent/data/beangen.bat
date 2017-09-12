#To change into unix format open the file in vi
# Then type :set ff=unix and save the file


set JAVA_HOME="C:\Program Files\Java\jdk1.7.0_51"
set DATA_PATH=C:\Users\sjana\MyApps\SoftleanService\data
set JSP_HOME==C:\Users\sjana\MyApps\test
set JAR_PATH=C:\Users\sjana\MyApps\3rdparty\lib
set PATH=%JAVA_HOME%\bin;%PATH%

set SPRING_JARS=%JAR_PATH%\org.springframework.beans-3.0.5.RELEASE.jar;%JAR_PATH%\org.springframework.core-3.0.5.RELEASE.jar;%JAR_PATH%\org.springframework.jdbc-3.0.5.RELEASE.jar;%JAR_PATH%\org.springframework.transaction-3.0.5.RELEASE.jar

set JAVA_CLASSPATH=.%JAVA_CLASSPATH%;%JAR_PATH%\serviceinfra.jar;%SPRING_JARS%;%JAR_PATH%\ojdbc6.jar;%JAR_PATH%\dom4j-1.6.1.jar;%JAVA_HOME%\lib;%JAVA_HOME%\lib\tools.jar;%JAR_PATH%\xerces.jar;%JAR_PATH%\tomcat-dbcp.jar;%JAR_PATH%\commons-logging-1.1.jar;



java -classpath %JAVA_CLASSPATH% cms.service.gen.beangen -bean Project -beanpath %JSP_HOME%\src  -app cms -inifile=%DATA_PATH%\context.xml
##java -classpath %JAVA_CLASSPATH% cms.service.gen.beangen -bean ProjectPlan -beanpath %JSP_HOME%\src  -app cms -inifile=%DATA_PATH%\context.xml
