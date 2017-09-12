
 	 package com.bidcrm.bean; 

 	 import org.apache.commons.logging.Log; 
 	 import org.apache.commons.logging.LogFactory; 
 	 import cms.service.event.EventListener;
	 import cms.service.event.QueryEvent;
	 import cms.service.event.QueryImpl;
	 import cms.service.template.*; 
	 /** A simple bean that has a single String property 
	 *  called message. 
 	 *  
	 * @author S.K Jana Version 1.0 
 	 * @Copyright : This code belongs to BidERP.com. All right reserved! 
 	 * @since 2005-2017 
 	 */ 

	 public class EmailresponseImpl extends QueryImpl implements QueryEvent {
		 protected static Log logger = LogFactory.getLog(EmailresponseImpl.class); 
 
		 protected String automationlogxml,automationlogdeleteid;
		 protected String actionnotexml,actionnotedeleteid;

		 protected TemplateTable automationlogdata=new TemplateTable();

		 protected TemplateTable actionnotedata=new TemplateTable();

		 public String EmailresponseFilter="";
		 public String AutomationlogFilter="to_char(AutomationLog.gendate# 'mm/dd/yyyy hh:mi') EntryDate@AutomationLog@AutomationLog.objid=AutomationLog.objid";
		 public String ActionnoteFilter="to_char(ActionNote.gendate # 'mm/dd/yyyy hh:mi') EntryDate,ActionNote.genuser ActionBy@ActionNote@ActionNote.objid=ActionNote.objid";

		 public String emailresponseAccessFilter="";

		 public String automationlogAccessFilter="";

		 public String actionnoteAccessFilter="";
		 public String getAutomationlogxml() {
			 return automationlogxml;
		 }
		 public void setAutomationlogxml(String automationlogxml) {
			 this.automationlogxml=automationlogxml;
		 }
		 public String getAutomationlogdeleteid() {
			 return automationlogdeleteid;
		 }
		 public void setAutomationlogdeleteid(String automationlogdeleteid) {
			 this.automationlogdeleteid=automationlogdeleteid;
		 }
		 public String getActionnotexml() {
			 return actionnotexml;
		 }
		 public void setActionnotexml(String actionnotexml) {
			 this.actionnotexml=actionnotexml;
		 }
		 public String getActionnotedeleteid() {
			 return actionnotedeleteid;
		 }
		 public void setActionnotedeleteid(String actionnotedeleteid) {
			 this.actionnotedeleteid=actionnotedeleteid;
		 }


		  public  TemplateTable  doSelect(String[] column,String[] datatype,String parentfilter,boolean isform,String accessfilter){
			 String sql="";
			 TemplateQuery query =new TemplateQuery();
			//do some custom pre query operation if any 
			 EventListener.registerPreQueryParent("Emailresponse",column,datatype);
			 query.setUserName(this.getUsername());
			 if(isform){
				 query.setIsForm(isform);
			 }
			 if (tu.isEmptyValue(parentfilter)){
				 if(!tu.isEmptyValue(this.getParentobjid()))
					query.makeTableSelect(this.getObject(),"ObjId","=",this.getParentobjid(),column,datatype,accessfilter,(QueryImpl)this);
				 else
					query.makeSQL(this.getObject(),query.getArrayData(this.getSearchdata()),column,datatype,accessfilter,(QueryImpl)this);
			 }else{
				 if(!tu.isEmptyValue(this.getParentobjid()))
					 query.makeTableSelectObjectFilter(this.getObject(),"ObjId","=",this.getParentobjid(),column,datatype,parentfilter,accessfilter,(QueryImpl)this);
				 else
					 query.makeObjectFilterSQL(this.getObject(),query.getArrayData(this.getSearchdata()),column,datatype,parentfilter,accessfilter,(QueryImpl)this);
			 }
			 if(ACONST.GENERATE_LOG)
				 logger.info(query.getQuery());
			 if(this.getPage()>0){
				 int startrow=(this.getPage()-1)*getPagesize();
				 query.setStartRow(startrow);
				 query.setNumRows(getPagesize());
			}
			 maindata=query.getTableResultset();
					// do any post query operation for custom implementation
					EventListener.registerPostQueryParent("Emailresponse",column,datatype);
			 return(maindata);
		 }


		 public  TemplateTable  doSelectChild(String childname,String relfield,String pid,String[]column,String[]datatype,String childfilter,String accessfilter){
			String sql=""; 
			TemplateTable data =new TemplateTable();
			TemplateQuery query =new TemplateQuery();
			// Do some pre query child operation for custom implementation
			EventListener.registerPreQueryChild("Emailresponse",childname,pid,relfield,column,datatype);
			if (tu.isEmptyValue(childfilter)){
				sql=query.makeChildSql(this.getObject(),childname,relfield,pid,column,datatype,accessfilter,(QueryImpl)this);
			}else{
					sql=query.makeChildObjectFilterSql(this.getObject(),childname,relfield,pid,column,datatype,childfilter,accessfilter,(QueryImpl)this);
			}
			query.setQuery(sql);
			data=query.getTableResultset();
			if(ACONST.GENERATE_LOG)
				logger.info(query.getQuery());
			if (data.getRowCount()>0){
			//Do some post query operation for child
					EventListener.registerPostQueryChild("Emailresponse",childname,pid,relfield,column,datatype);
			}
			return(data);
		}
		public  boolean   doDelete(String[] childtabs){
			TemplateQuery query =new TemplateQuery();
			// Do some pre delete operation 
			String sql=query.removeSql("Emailresponse",this.getParentobjid(),childtabs,groupuser);
			if(!tu.isEmptyValue(sql)){
				tu.applyObjectRuleForDelete("Emailresponse",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_BEFORE,this.getParentobjid());
				EventListener.registerPreDeleteEvent("Emailresponse",this.getParentobjid());
				query.setQuery(sql);
				if(ACONST.GENERATE_LOG)
					logger.info(query.getQuery());
				if (query.getTableResultset().getRowCount()>0){
					// Do some post delete operation
					tu.applyObjectRuleForDelete("Emailresponse",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_AFTER,this.getParentobjid());
					EventListener.registerPostDeleteEvent("Emailresponse",this.getParentobjid());
					return(true);
				}
			}
			return(false);
		}

		public  boolean  doInsert(){
			String sql=null;
			String usql=""; 
			TemplateQuery query =new TemplateQuery();
			if(!tu.isEmptyValue(this.getMainxml())){
					sql=query.makeBulkSQL(true,this.getMainxml(),"EmailResponse2Campaign",(QueryImpl)this);
					maindata=query.getTableData();
					tu.applyObjectRule("Emailresponse",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,maindata);
					EventListener.registerPreInsertEvent("Emailresponse",maindata);
			}
			if(sql==null){
					if(ACONST.GENERATE_LOG)
						logger.info(" WARNING: Parent record exists! Duplicate record");
				return(false);
			}else{
				 if(ACONST.GENERATE_LOG)
				 logger.info("parent ObjId="+query.getObjId());
				setParentobjid(query.getObjId()); 
				 if(!tu.isEmptyValue(this.getAutomationlogxml()) ){
					 sql+=query.makeBulkSQL(false,getAutomationlogxml(),"AutomationLog2EmailResponse",(QueryImpl)this);
					 automationlogdata=query.getTableData();
					 tu.applyObjectRule("Automationlog",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,automationlogdata);
					 EventListener.registerPreInsertEvent("AutomationLog",automationlogdata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"AutomationLog","AutomationLog2EmailResponse",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getActionnotexml()) ){
					 sql+=query.makeBulkSQL(false,getActionnotexml(),"ActionNote2EmailResponse",(QueryImpl)this);
					 actionnotedata=query.getTableData();
					 tu.applyObjectRule("Actionnote",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,actionnotedata);
					 EventListener.registerPreInsertEvent("ActionNote",actionnotedata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"ActionNote","ActionNote2EmailResponse",getParentobjid());
				}
			 }
			 sql+="\t\t end;";
			 query.setQuery(sql);
			if(ACONST.GENERATE_LOG)
			 logger.info(query.getQuery());
			 if (query.getTableResultset().getRowCount()>0){
				
				 usql=(usql.equals("")?"":"\n\t begin"+usql +"\n\t end;");
				 if(!usql.equals(""))
					 tu.executeQuery(usql);
				if(ACONST.GENERATE_LOG)
					logger.info(usql);
				 tu.applyObjectRule("Emailresponse",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,maindata);
				 tu.applyMTMRelation("Emailresponse","Emailresponse",getParentobjid());
				 EventListener.registerPostInsertEvent("Emailresponse",maindata);
				 tu.applyObjectRule("Automationlog",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,automationlogdata);
				 tu.applyMTMRelation("automationlog","Emailresponse",getParentobjid());
				 EventListener.registerPostInsertEvent("AutomationLog",automationlogdata);
				 tu.applyObjectRule("Actionnote",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,actionnotedata);
				 tu.applyMTMRelation("actionnote","Emailresponse",getParentobjid());
				 EventListener.registerPostInsertEvent("ActionNote",actionnotedata);
				 return(true);
			}
			 return(false);
		}
	}
