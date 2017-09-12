
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

	 public class CompanyImpl extends QueryImpl implements QueryEvent {
		 protected static Log logger = LogFactory.getLog(CompanyImpl.class); 
 
		 protected String currencyxml,currencydeleteid;
		 protected String territoryxml,territorydeleteid;
		 protected String userxml,userdeleteid;

		 protected TemplateTable currencydata=new TemplateTable();

		 protected TemplateTable territorydata=new TemplateTable();

		 protected TemplateTable userdata=new TemplateTable();

		 public String CompanyFilter="";
		 public String CurrencyFilter="";
		 public String TerritoryFilter="";
		 public String UserFilter="";

		 public String companyAccessFilter="";

		 public String currencyAccessFilter="";

		 public String territoryAccessFilter="";

		 public String userAccessFilter="";
		 public String getCurrencyxml() {
			 return currencyxml;
		 }
		 public void setCurrencyxml(String currencyxml) {
			 this.currencyxml=currencyxml;
		 }
		 public String getCurrencydeleteid() {
			 return currencydeleteid;
		 }
		 public void setCurrencydeleteid(String currencydeleteid) {
			 this.currencydeleteid=currencydeleteid;
		 }
		 public String getTerritoryxml() {
			 return territoryxml;
		 }
		 public void setTerritoryxml(String territoryxml) {
			 this.territoryxml=territoryxml;
		 }
		 public String getTerritorydeleteid() {
			 return territorydeleteid;
		 }
		 public void setTerritorydeleteid(String territorydeleteid) {
			 this.territorydeleteid=territorydeleteid;
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
			 EventListener.registerPreQueryParent("Company",column,datatype);
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
					EventListener.registerPostQueryParent("Company",column,datatype);
			 return(maindata);
		 }


		 public  TemplateTable  doSelectChild(String childname,String relfield,String pid,String[]column,String[]datatype,String childfilter,String accessfilter){
			String sql=""; 
			TemplateTable data =new TemplateTable();
			TemplateQuery query =new TemplateQuery();
			// Do some pre query child operation for custom implementation
			EventListener.registerPreQueryChild("Company",childname,pid,relfield,column,datatype);
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
					EventListener.registerPostQueryChild("Company",childname,pid,relfield,column,datatype);
			}
			return(data);
		}
		public  boolean   doDelete(String[] childtabs){
			TemplateQuery query =new TemplateQuery();
			// Do some pre delete operation 
			String sql=query.removeSql("Company",this.getParentobjid(),childtabs,groupuser);
			if(!tu.isEmptyValue(sql)){
				tu.applyObjectRuleForDelete("Company",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_BEFORE,this.getParentobjid());
				EventListener.registerPreDeleteEvent("Company",this.getParentobjid());
				query.setQuery(sql);
				if(ACONST.GENERATE_LOG)
					logger.info(query.getQuery());
				if (query.getTableResultset().getRowCount()>0){
					// Do some post delete operation
					tu.applyObjectRuleForDelete("Company",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_AFTER,this.getParentobjid());
					EventListener.registerPostDeleteEvent("Company",this.getParentobjid());
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
					tu.applyObjectRule("Company",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,maindata);
					EventListener.registerPreInsertEvent("Company",maindata);
			}
			if(sql==null){
					if(ACONST.GENERATE_LOG)
						logger.info(" WARNING: Parent record exists! Duplicate record");
				return(false);
			}else{
				 if(ACONST.GENERATE_LOG)
				 logger.info("parent ObjId="+query.getObjId());
				setParentobjid(query.getObjId()); 
				 if(!tu.isEmptyValue(this.getCurrencyxml()) ){
					 sql+=query.makeBulkSQL(false,getCurrencyxml(),"Currency2Company",(QueryImpl)this);
					 currencydata=query.getTableData();
					 tu.applyObjectRule("Currency",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,currencydata);
					 EventListener.registerPreInsertEvent("Currency",currencydata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"Currency","Currency2Company",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getTerritoryxml()) ){
					 sql+=query.makeBulkSQL(false,getTerritoryxml(),"Territory2Company",(QueryImpl)this);
					 territorydata=query.getTableData();
					 tu.applyObjectRule("Territory",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,territorydata);
					 EventListener.registerPreInsertEvent("Territory",territorydata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"Territory","Territory2Company",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getUserxml()) ){
					 sql+=query.makeBulkSQL(false,getUserxml(),"User2Company",(QueryImpl)this);
					 userdata=query.getTableData();
					 tu.applyObjectRule("User",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,userdata);
					 EventListener.registerPreInsertEvent("User",userdata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"User","User2Company",getParentobjid());
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
				 tu.applyObjectRule("Company",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,maindata);
				 tu.applyMTMRelation("Company","Company",getParentobjid());
				 EventListener.registerPostInsertEvent("Company",maindata);
				 tu.applyObjectRule("Currency",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,currencydata);
				 tu.applyMTMRelation("currency","Company",getParentobjid());
				 EventListener.registerPostInsertEvent("Currency",currencydata);
				 tu.applyObjectRule("Territory",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,territorydata);
				 tu.applyMTMRelation("territory","Company",getParentobjid());
				 EventListener.registerPostInsertEvent("Territory",territorydata);
				 tu.applyObjectRule("User",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,userdata);
				 tu.applyMTMRelation("user","Company",getParentobjid());
				 EventListener.registerPostInsertEvent("User",userdata);
				 return(true);
			}
			 return(false);
		}
	}
