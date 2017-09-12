
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

	 public class TerritoryImpl extends QueryImpl implements QueryEvent {
		 protected static Log logger = LogFactory.getLog(TerritoryImpl.class); 
 
		 protected String accountxml,accountdeleteid;
		 protected String sitexml,sitedeleteid;

		 protected TemplateTable accountdata=new TemplateTable();

		 protected TemplateTable sitedata=new TemplateTable();

		 public String TerritoryFilter="";
		 public String AccountFilter="";
		 public String SiteFilter="";

		 public String territoryAccessFilter="";

		 public String accountAccessFilter="";

		 public String siteAccessFilter="";
		 public String getAccountxml() {
			 return accountxml;
		 }
		 public void setAccountxml(String accountxml) {
			 this.accountxml=accountxml;
		 }
		 public String getAccountdeleteid() {
			 return accountdeleteid;
		 }
		 public void setAccountdeleteid(String accountdeleteid) {
			 this.accountdeleteid=accountdeleteid;
		 }
		 public String getSitexml() {
			 return sitexml;
		 }
		 public void setSitexml(String sitexml) {
			 this.sitexml=sitexml;
		 }
		 public String getSitedeleteid() {
			 return sitedeleteid;
		 }
		 public void setSitedeleteid(String sitedeleteid) {
			 this.sitedeleteid=sitedeleteid;
		 }


		  public  TemplateTable  doSelect(String[] column,String[] datatype,String parentfilter,boolean isform,String accessfilter){
			 String sql="";
			 TemplateQuery query =new TemplateQuery();
			//do some custom pre query operation if any 
			 EventListener.registerPreQueryParent("Territory",column,datatype);
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
					EventListener.registerPostQueryParent("Territory",column,datatype);
			 return(maindata);
		 }


		 public  TemplateTable  doSelectChild(String childname,String relfield,String pid,String[]column,String[]datatype,String childfilter,String accessfilter){
			String sql=""; 
			TemplateTable data =new TemplateTable();
			TemplateQuery query =new TemplateQuery();
			// Do some pre query child operation for custom implementation
			EventListener.registerPreQueryChild("Territory",childname,pid,relfield,column,datatype);
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
					EventListener.registerPostQueryChild("Territory",childname,pid,relfield,column,datatype);
			}
			return(data);
		}
		public  boolean   doDelete(String[] childtabs){
			TemplateQuery query =new TemplateQuery();
			// Do some pre delete operation 
			String sql=query.removeSql("Territory",this.getParentobjid(),childtabs,groupuser);
			if(!tu.isEmptyValue(sql)){
				tu.applyObjectRuleForDelete("Territory",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_BEFORE,this.getParentobjid());
				EventListener.registerPreDeleteEvent("Territory",this.getParentobjid());
				query.setQuery(sql);
				if(ACONST.GENERATE_LOG)
					logger.info(query.getQuery());
				if (query.getTableResultset().getRowCount()>0){
					// Do some post delete operation
					tu.applyObjectRuleForDelete("Territory",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_AFTER,this.getParentobjid());
					EventListener.registerPostDeleteEvent("Territory",this.getParentobjid());
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
					sql=query.makeBulkSQL(true,this.getMainxml(),"Territory2Company",(QueryImpl)this);
					maindata=query.getTableData();
					tu.applyObjectRule("Territory",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,maindata);
					EventListener.registerPreInsertEvent("Territory",maindata);
			}
			if(sql==null){
					if(ACONST.GENERATE_LOG)
						logger.info(" WARNING: Parent record exists! Duplicate record");
				return(false);
			}else{
				 if(ACONST.GENERATE_LOG)
				 logger.info("parent ObjId="+query.getObjId());
				setParentobjid(query.getObjId()); 
				 if(!tu.isEmptyValue(this.getAccountxml()) ){
					 sql+=query.makeBulkSQL(false,getAccountxml(),"Account2Territory",(QueryImpl)this);
					 accountdata=query.getTableData();
					 tu.applyObjectRule("Account",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,accountdata);
					 EventListener.registerPreInsertEvent("Account",accountdata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"Account","Account2Territory",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getSitexml()) ){
					 sql+=query.makeBulkSQL(false,getSitexml(),"Site2Territory",(QueryImpl)this);
					 sitedata=query.getTableData();
					 tu.applyObjectRule("Site",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,sitedata);
					 EventListener.registerPreInsertEvent("Site",sitedata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"Site","Site2Territory",getParentobjid());
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
				 tu.applyObjectRule("Territory",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,maindata);
				 tu.applyMTMRelation("Territory","Territory",getParentobjid());
				 EventListener.registerPostInsertEvent("Territory",maindata);
				 tu.applyObjectRule("Account",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,accountdata);
				 tu.applyMTMRelation("account","Territory",getParentobjid());
				 EventListener.registerPostInsertEvent("Account",accountdata);
				 tu.applyObjectRule("Site",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,sitedata);
				 tu.applyMTMRelation("site","Territory",getParentobjid());
				 EventListener.registerPostInsertEvent("Site",sitedata);
				 return(true);
			}
			 return(false);
		}
	}
