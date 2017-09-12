 #!/bin/sh
 
 cd /home/srimanta/erp
 
 mv bidcrm.zip bidcrm.zip.priv
 
 zip -r bidcrm.zip bidcrm/
 scp   -i "/home/srimanta/erp/3rdparty/pem/CloudTestAutomation.pem" bidcrm.zip  root@107.21.103.155:/u02/app/backup/bidcrm
 

 zip -r bidcrmcore.zip bidcrmcore/
 scp   -i "/home/srimanta/erp/3rdparty/pem/CloudTestAutomation.pem" bidcrmcore.zip  root@107.21.103.155:/u02/app/backup/bidcrm
 
 
 zip -r authprovider.zip authprovider/
 scp   -i "/home/srimanta/erp/3rdparty/pem/CloudTestAutomation.pem" authprovider.zip  root@107.21.103.155:/u02/app/backup/bidcrm
 
 
 zip -r bidcrmtask.zip bidcrmtask/
 scp   -i "/home/srimanta/erp/3rdparty/pem/CloudTestAutomation.pem" bidcrmtask.zip  root@107.21.103.155:/u02/app/backup/bidcrm
 