 #!/bin/sh
 
 cd /home/srimanta/erp/bidcrm
 
rm bidcrm.priv.zip
 
mv bidcrm.zip bidcrm.priv.zip
 
 zip -r bidcrm.zip WebContent/
 
 scp   -i "/home/srimanta/erp/3rdparty/pem/CloudTestAutomation.pem" bidcrm.zip  root@107.21.103.155:/u02/app/apache/tomcat9crm/webapps
 
 ssh -i "/home/srimanta/erp/3rdparty/pem/CloudTestAutomation.pem" root@ec2-107-21-103-155.compute-1.amazonaws.com
 

 