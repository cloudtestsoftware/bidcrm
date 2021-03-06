
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

	 public class MastertemplateImpl extends QueryImpl implements QueryEvent {
		 protected static Log logger = LogFactory.getLog(MastertemplateImpl.class); 
 
		 protected String reviewnotexml,reviewnotedeleteid;
		 protected String reviewerxml,reviewerdeleteid;
		 protected String emailsettingxml,emailsettingdeleteid;

		 protected TemplateTable reviewnotedata=new TemplateTable();

		 protected TemplateTable reviewerdata=new TemplateTable();

		 protected TemplateTable emailsettingdata=new TemplateTable();

		 public String MastertemplateFilter="";
		 public String ReviewnoteFilter="";
		 public String ReviewerFilter="employee.email Email@Reviewer,Employee@Reviewer.Reviewer2Employee=Employee.objid";
		 public String EmailsettingFilter="MasterTemplate.url MasterUrl,MasterTemplate.LockedBy LockedBy,MasterTemplate.LockedDate LockedDate@EmailSetting,MasterTemplate@EmailSetting.EmailSetting2MasterTemplate=MasterTemplate.objid";

		 public String mastertemplateAccessFilter="select m.objid from table_employee e,table_mastertemplate m,table_reviewer r where e.objid=r.reviewer2employee and r.reviewer2mastertemplate=m.objid and upper(m.groupuser)=upper(@groupuser)";

		 public String reviewnoteAccessFilter="";

		 public String reviewerAccessFilter="";

		 public String emailsettingAccessFilter="";
		 public String getReviewnotexml() {
			 return reviewnotexml;
		 }
		 public void setReviewnotexml(String reviewnotexml) {
			 this.reviewnotexml=reviewnotexml;
		 }
		 public String getReviewnotedeleteid() {
			 return reviewnotedeleteid;
		 }
		 public void setReviewnotedeleteid(String reviewnotedeleteid) {
			 this.reviewnotedeleteid=reviewnotedeleteid;
		 }
		 public String getReviewerxml() {
			 return reviewerxml;
		 }
		 public void setReviewerxml(String reviewerxml) {
			 this.reviewerxml=reviewerxml;
		 }
		 public String getReviewerdeleteid() {
			 return reviewerdeleteid;
		 }
		 public void setReviewerdeleteid(String reviewerdeleteid) {
			 this.reviewerdeleteid=reviewerdeleteid;
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
			 EventListener.registerPreQueryParent("Mastertemplate",column,datatype);
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
					EventListener.registerPostQueryParent("Mastertemplate",column,datatype);
			 return(maindata);
		 }


		 public  TemplateTable  doSelectChild(String childname,String relfield,String pid,String[]column,String[]datatype,String childfilter,String accessfilter){
			String sql=""; 
			TemplateTable data =new TemplateTable();
			TemplateQuery query =new TemplateQuery();
			// Do some pre query child operation for custom implementation
			EventListener.registerPreQueryChild("Mastertemplate",childname,pid,relfield,column,datatype);
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
					EventListener.registerPostQueryChild("Mastertemplate",childname,pid,relfield,column,datatype);
			}
			return(data);
		}
		public  boolean   doDelete(String[] childtabs){
			TemplateQuery query =new TemplateQuery();
			// Do some pre delete operation 
			String sql=query.removeSql("Mastertemplate",this.getParentobjid(),childtabs,groupuser);
			if(!tu.isEmptyValue(sql)){
				tu.applyObjectRuleForDelete("Mastertemplate",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_BEFORE,this.getParentobjid());
				EventListener.registerPreDeleteEvent("Mastertemplate",this.getParentobjid());
				query.setQuery(sql);
				if(ACONST.GENERATE_LOG)
					logger.info(query.getQuery());
				if (query.getTableResultset().getRowCount()>0){
					// Do some post delete operation
					tu.applyObjectRuleForDelete("Mastertemplate",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_AFTER,this.getParentobjid());
					EventListener.registerPostDeleteEvent("Mastertemplate",this.getParentobjid());
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
					sql=query.makeBulkSQL(true,this.getMainxml(),"",(QueryImpl)this);
					maindata=query.getTableData();
					tu.applyObjectRule("Mastertemplate",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,maindata);
					EventListener.registerPreInsertEvent("Mastertemplate",maindata);
			}
			if(sql==null){
					if(ACONST.GENERATE_LOG)
						logger.info(" WARNING: Parent record exists! Duplicate record");
				return(false);
			}else{
				 if(ACONST.GENERATE_LOG)
				 logger.info("parent ObjId="+query.getObjId());
				setParentobjid(query.getObjId()); 
				 if(!tu.isEmptyValue(this.getReviewnotexml()) ){
					 sql+=query.makeBulkSQL(false,getReviewnotexml(),"ReviewNote2MasterTemplate",(QueryImpl)this);
					 reviewnotedata=query.getTableData();
					 tu.applyObjectRule("Reviewnote",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,reviewnotedata);
					 EventListener.registerPreInsertEvent("ReviewNote",reviewnotedata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"ReviewNote","ReviewNote2MasterTemplate",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getReviewerxml()) ){
					 sql+=query.makeBulkSQL(false,getReviewerxml(),"Reviewer2MasterTemplate",(QueryImpl)this);
					 reviewerdata=query.getTableData();
					 tu.applyObjectRule("Reviewer",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,reviewerdata);
					 EventListener.registerPreInsertEvent("Reviewer",reviewerdata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"Reviewer","Reviewer2MasterTemplate",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getEmailsettingxml()) ){
					 sql+=query.makeBulkSQL(false,getEmailsettingxml(),"EmailSetting2MasterTemplate",(QueryImpl)this);
					 emailsettingdata=query.getTableData();
					 tu.applyObjectRule("Emailsetting",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,emailsettingdata);
					 EventListener.registerPreInsertEvent("EmailSetting",emailsettingdata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"EmailSetting","EmailSetting2MasterTemplate",getParentobjid());
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
				 tu.applyObjectRule("Mastertemplate",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,maindata);
				 tu.applyMTMRelation("Mastertemplate","Mastertemplate",getParentobjid());
				 EventListener.registerPostInsertEvent("Mastertemplate",maindata);
				 tu.applyObjectRule("Reviewnote",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,reviewnotedata);
				 tu.applyMTMRelation("reviewnote","Mastertemplate",getParentobjid());
				 EventListener.registerPostInsertEvent("ReviewNote",reviewnotedata);
				 tu.applyObjectRule("Reviewer",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,reviewerdata);
				 tu.applyMTMRelation("reviewer","Mastertemplate",getParentobjid());
				 EventListener.registerPostInsertEvent("Reviewer",reviewerdata);
				 tu.applyObjectRule("Emailsetting",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,emailsettingdata);
				 tu.applyMTMRelation("emailsetting","Mastertemplate",getParentobjid());
				 EventListener.registerPostInsertEvent("EmailSetting",emailsettingdata);
				 return(true);
			}
			 return(false);
		}
	}
