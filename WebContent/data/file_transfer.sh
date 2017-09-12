#To fix erratic mouse movement execute below command
sudo killall syndaemon
sudo syndaemon -d
sudo syndaemon -i 4 -d


zip -r WebContent.zip WebContent

scp   -i /Users/srimanta.jana/paycheck/paydb softlean.jar  root@107.21.103.155:~

scp   -i "/home/srimanta/Documents/Projects/testrepo/data/pem/CloudTestAutomation.pem" WebContent.zip  root@107.21.103.155:/u02/app/apache/tomcat6erp/webapps

scp -i "/home/srimanta/Documents/Projects/testrepo/data/pem/CloudTestAutomation.pem" root@107.21.103.155:/u02/app/apache/tomcat6a/webapps/payroll.zip /home/srimanta/payroll/

scp   -i "/home/srimanta/Documents/Projects/testrepo/data/pem/CloudTestAutomation.pem" WebContent.zip  root@107.21.103.155:/u02/app/apache/tomcat9erp/webapps


scp   -i "/home/srimanta/Documents/Projects/testrepo/data/pem/CloudTestAutomation.pem" sslforfree_all.zip root@107.21.103.155:/etc/nginx/ssl


scp   -i "/home/srimanta/Documents/Projects/testrepo/data/pem/CloudTestAutomation.pem" ZsLiZRwZnrpAg1gFAplTUhENb0xKui4QO39_yqaoDd8 root@107.21.103.155:/u02/app/apache/tomcat6a/webapps/.well-known/acme-challenge



