
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

	 public class SupplierImpl extends QueryImpl implements QueryEvent {
		 protected static Log logger = LogFactory.getLog(SupplierImpl.class); 
 
		 protected String bizprofilexml,bizprofiledeleteid;
		 protected String jobhistoryxml,jobhistorydeleteid;
		 protected String userxml,userdeleteid;

		 protected TemplateTable bizprofiledata=new TemplateTable();

		 protected TemplateTable jobhistorydata=new TemplateTable();

		 protected TemplateTable userdata=new TemplateTable();

		 public String SupplierFilter="";
		 public String BizprofileFilter="";
		 public String JobhistoryFilter="";
		 public String UserFilter="";

		 public String supplierAccessFilter="";

		 public String bizprofileAccessFilter="";

		 public String jobhistoryAccessFilter="";

		 public String userAccessFilter="";
		 public String getBizprofilexml() {
			 return bizprofilexml;
		 }
		 public void setBizprofilexml(String bizprofilexml) {
			 this.bizprofilexml=bizprofilexml;
		 }
		 public String getBizprofiledeleteid() {
			 return bizprofiledeleteid;
		 }
		 public void setBizprofiledeleteid(String bizprofiledeleteid) {
			 this.bizprofiledeleteid=bizprofiledeleteid;
		 }
		 public String getJobhistoryxml() {
			 return jobhistoryxml;
		 }
		 public void setJobhistoryxml(String jobhistoryxml) {
			 this.jobhistoryxml=jobhistoryxml;
		 }
		 public String getJobhistorydeleteid() {
			 return jobhistorydeleteid;
		 }
		 public void setJobhistorydeleteid(String jobhistorydeleteid) {
			 this.jobhistorydeleteid=jobhistorydeleteid;
		 }
		 public String getUserxml() {
			 return userxml;
		 }
		 public void setUserxml(String userxml) {
			 this.userxml=userxml;
		 }
		 public String getUserdeleteid() {
			 return userdeleteid;
		 }
		 public void setUserdeleteid(String userdeleteid) {
			 this.userdeleteid=userdeleteid;
		 }


		  public  TemplateTable  doSelect(String[] column,String[] datatype,String parentfilter,boolean isform,String accessfilter){
			 String sql="";
			 TemplateQuery query =new TemplateQuery();
			//do some custom pre query operation if any 
			 EventListener.registerPreQueryParent("Supplier",column,datatype);
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
					EventListener.registerPostQueryParent("Supplier",column,datatype);
			 return(maindata);
		 }


		 public  TemplateTable  doSelectChild(String childname,String relfield,String pid,String[]column,String[]datatype,String childfilter,String accessfilter){
			String sql=""; 
			TemplateTable data =new TemplateTable();
			TemplateQuery query =new TemplateQuery();
			// Do some pre query child operation for custom implementation
			EventListener.registerPreQueryChild("Supplier",childname,pid,relfield,column,datatype);
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
					EventListener.registerPostQueryChild("Supplier",childname,pid,relfield,column,datatype);
			}
			return(data);
		}
		public  boolean   doDelete(String[] childtabs){
			TemplateQuery query =new TemplateQuery();
			// Do some pre delete operation 
			String sql=query.removeSql("Supplier",this.getParentobjid(),childtabs,groupuser);
			if(!tu.isEmptyValue(sql)){
				tu.applyObjectRuleForDelete("Supplier",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_BEFORE,this.getParentobjid());
				EventListener.registerPreDeleteEvent("Supplier",this.getParentobjid());
				query.setQuery(sql);
				if(ACONST.GENERATE_LOG)
					logger.info(query.getQuery());
				if (query.getTableResultset().getRowCount()>0){
					// Do some post delete operation
					tu.applyObjectRuleForDelete("Supplier",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_AFTER,this.getParentobjid());
					EventListener.registerPostDeleteEvent("Supplier",this.getParentobjid());
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
					tu.applyObjectRule("Supplier",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,maindata);
					EventListener.registerPreInsertEvent("Supplier",maindata);
			}
			if(sql==null){
					if(ACONST.GENERATE_LOG)
						logger.info(" WARNING: Parent record exists! Duplicate record");
				return(false);
			}else{
				 if(ACONST.GENERATE_LOG)
				 logger.info("parent ObjId="+query.getObjId());
				setParentobjid(query.getObjId()); 
				 if(!tu.isEmptyValue(this.getBizprofilexml()) ){
					 sql+=query.makeBulkSQL(false,getBizprofilexml(),"BizProfile2Supplier",(QueryImpl)this);
					 bizprofiledata=query.getTableData();
					 tu.applyObjectRule("Bizprofile",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,bizprofiledata);
					 EventListener.registerPreInsertEvent("BizProfile",bizprofiledata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"BizProfile","BizProfile2Supplier",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getJobhistoryxml()) ){
					 sql+=query.makeBulkSQL(false,getJobhistoryxml(),"JobHistory2Supplier",(QueryImpl)this);
					 jobhistorydata=query.getTableData();
					 tu.applyObjectRule("Jobhistory",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,jobhistorydata);
					 EventListener.registerPreInsertEvent("JobHistory",jobhistorydata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"JobHistory","JobHistory2Supplier",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getUserxml()) ){
					 sql+=query.makeBulkSQL(false,getUserxml(),"User2Supplier",(QueryImpl)this);
					 userdata=query.getTableData();
					 tu.applyObjectRule("User",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,userdata);
					 EventListener.registerPreInsertEvent("User",userdata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"User","User2Supplier",getParentobjid());
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
				 tu.applyObjectRule("Supplier",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,maindata);
				 tu.applyMTMRelation("Supplier","Supplier",getParentobjid());
				 EventListener.registerPostInsertEvent("Supplier",maindata);
				 tu.applyObjectRule("Bizprofile",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,bizprofiledata);
				 tu.applyMTMRelation("bizprofile","Supplier",getParentobjid());
				 EventListener.registerPostInsertEvent("BizProfile",bizprofiledata);
				 tu.applyObjectRule("Jobhistory",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,jobhistorydata);
				 tu.applyMTMRelation("jobhistory","Supplier",getParentobjid());
				 EventListener.registerPostInsertEvent("JobHistory",jobhistorydata);
				 tu.applyObjectRule("User",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,userdata);
				 tu.applyMTMRelation("user","Supplier",getParentobjid());
				 EventListener.registerPostInsertEvent("User",userdata);
				 return(true);
			}
			 return(false);
		}
	}
