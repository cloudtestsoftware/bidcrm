set heading off;
set linesize 500;
spool /home/srimanta/erp/biderp/WebContent/data/bean.sh;
select 'java -classpath $JAVA_CLASSPATH cms.service.gen.beangen -bean ' ||objectname|| ' -beanpath $JSP_HOME/src  -app cms  -inifile $DATA_PATH/context.xml' from sml_dbobject order by objectname;                                            
spool off;