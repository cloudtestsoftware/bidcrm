
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

	 public class ContactlistImpl extends QueryImpl implements QueryEvent {
		 protected static Log logger = LogFactory.getLog(ContactlistImpl.class); 
 
		 protected String contactlistlogxml,contactlistlogdeleteid;
		 protected String emailsettingxml,emailsettingdeleteid;

		 protected TemplateTable contactlistlogdata=new TemplateTable();

		 protected TemplateTable emailsettingdata=new TemplateTable();

		 public String ContactlistFilter="";
		 public String ContactlistlogFilter="";
		 public String EmailsettingFilter="MasterTemplate.url MasterUrl,MasterTemplate.LockedBy LockedBy,MasterTemplate.LockedDate LockedDate@EmailSetting,MasterTemplate@EmailSetting.EmailSetting2MasterTemplate=MasterTemplate.objid";

		 public String contactlistAccessFilter="";

		 public String contactlistlogAccessFilter="";

		 public String emailsettingAccessFilter="";
		 public String getContactlistlogxml() {
			 return contactlistlogxml;
		 }
		 public void setContactlistlogxml(String contactlistlogxml) {
			 this.contactlistlogxml=contactlistlogxml;
		 }
		 public String getContactlistlogdeleteid() {
			 return contactlistlogdeleteid;
		 }
		 public void setContactlistlogdeleteid(String contactlistlogdeleteid) {
			 this.contactlistlogdeleteid=contactlistlogdeleteid;
		 }
		 public String getEmailsettingxml() {
			 return emailsettingxml;
		 }
		 public void setEmailsettingxml(String emailsettingxml) {
			 this.emailsettingxml=emailsettingxml;
		 }
		 public String getEmailsettingdeleteid() {
			 return emailsettingdeleteid;
		 }
		 public void setEmailsettingdeleteid(String emailsettingdeleteid) {
			 this.emailsettingdeleteid=emailsettingdeleteid;
		 }


		  public  TemplateTable  doSelect(String[] column,String[] datatype,String parentfilter,boolean isform,String accessfilter){
			 String sql="";
			 TemplateQuery query =new TemplateQuery();
			//do some custom pre query operation if any 
			 EventListener.registerPreQueryParent("Contactlist",column,datatype);
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
					EventListener.registerPostQueryParent("Contactlist",column,datatype);
			 return(maindata);
		 }


		 public  TemplateTable  doSelectChild(String childname,String relfield,String pid,String[]column,String[]datatype,String childfilter,String accessfilter){
			String sql=""; 
			TemplateTable data =new TemplateTable();
			TemplateQuery query =new TemplateQuery();
			// Do some pre query child operation for custom implementation
			EventListener.registerPreQueryChild("Contactlist",childname,pid,relfield,column,datatype);
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
					EventListener.registerPostQueryChild("Contactlist",childname,pid,relfield,column,datatype);
			}
			return(data);
		}
		public  boolean   doDelete(String[] childtabs){
			TemplateQuery query =new TemplateQuery();
			// Do some pre delete operation 
			String sql=query.removeSql("Contactlist",this.getParentobjid(),childtabs,groupuser);
			if(!tu.isEmptyValue(sql)){
				tu.applyObjectRuleForDelete("Contactlist",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_BEFORE,this.getParentobjid());
				EventListener.registerPreDeleteEvent("Contactlist",this.getParentobjid());
				query.setQuery(sql);
				if(ACONST.GENERATE_LOG)
					logger.info(query.getQuery());
				if (query.getTableResultset().getRowCount()>0){
					// Do some post delete operation
					tu.applyObjectRuleForDelete("Contactlist",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_AFTER,this.getParentobjid());
					EventListener.registerPostDeleteEvent("Contactlist",this.getParentobjid());
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
					sql=query.makeBulkSQL(true,this.getMainxml(),"ContactList2Campaign",(QueryImpl)this);
					maindata=query.getTableData();
					tu.applyObjectRule("Contactlist",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,maindata);
					EventListener.registerPreInsertEvent("Contactlist",maindata);
			}
			if(sql==null){
					if(ACONST.GENERATE_LOG)
						logger.info(" WARNING: Parent record exists! Duplicate record");
				return(false);
			}else{
				 if(ACONST.GENERATE_LOG)
				 logger.info("parent ObjId="+query.getObjId());
				setParentobjid(query.getObjId()); 
				 if(!tu.isEmptyValue(this.getContactlistlogxml()) ){
					 sql+=query.makeBulkSQL(false,getContactlistlogxml(),"ContactListLog2ContactList",(QueryImpl)this);
					 contactlistlogdata=query.getTableData();
					 tu.applyObjectRule("Contactlistlog",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,contactlistlogdata);
					 EventListener.registerPreInsertEvent("ContactListLog",contactlistlogdata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"ContactListLog","ContactListLog2ContactList",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getEmailsettingxml()) ){
					 sql+=query.makeBulkSQL(false,getEmailsettingxml(),"EmailSetting2ContactList",(QueryImpl)this);
					 emailsettingdata=query.getTableData();
					 tu.applyObjectRule("Emailsetting",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,emailsettingdata);
					 EventListener.registerPreInsertEvent("EmailSetting",emailsettingdata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"EmailSetting","EmailSetting2ContactList",getParentobjid());
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
				 tu.applyObjectRule("Contactlist",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,maindata);
				 tu.applyMTMRelation("Contactlist","Contactlist",getParentobjid());
				 EventListener.registerPostInsertEvent("Contactlist",maindata);
				 tu.applyObjectRule("Contactlistlog",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,contactlistlogdata);
				 tu.applyMTMRelation("contactlistlog","Contactlist",getParentobjid());
				 EventListener.registerPostInsertEvent("ContactListLog",contactlistlogdata);
				 tu.applyObjectRule("Emailsetting",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,emailsettingdata);
				 tu.applyMTMRelation("emailsetting","Contactlist",getParentobjid());
				 EventListener.registerPostInsertEvent("EmailSetting",emailsettingdata);
				 return(true);
			}
			 return(false);
		}
	}
