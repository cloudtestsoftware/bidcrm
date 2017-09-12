
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

	 public class FinancialyearImpl extends QueryImpl implements QueryEvent {
		 protected static Log logger = LogFactory.getLog(FinancialyearImpl.class); 
 
		 protected String campaigncalendarxml,campaigncalendardeleteid;
		 protected String eventcalendarxml,eventcalendardeleteid;
		 protected String travelcalendarxml,travelcalendardeleteid;

		 protected TemplateTable campaigncalendardata=new TemplateTable();

		 protected TemplateTable eventcalendardata=new TemplateTable();

		 protected TemplateTable travelcalendardata=new TemplateTable();

		 public String FinancialyearFilter="";
		 public String CampaigncalendarFilter="";
		 public String EventcalendarFilter="";
		 public String TravelcalendarFilter="";

		 public String financialyearAccessFilter="";

		 public String campaigncalendarAccessFilter="";

		 public String eventcalendarAccessFilter="";

		 public String travelcalendarAccessFilter="";
		 public String getCampaigncalendarxml() {
			 return campaigncalendarxml;
		 }
		 public void setCampaigncalendarxml(String campaigncalendarxml) {
			 this.campaigncalendarxml=campaigncalendarxml;
		 }
		 public String getCampaigncalendardeleteid() {
			 return campaigncalendardeleteid;
		 }
		 public void setCampaigncalendardeleteid(String campaigncalendardeleteid) {
			 this.campaigncalendardeleteid=campaigncalendardeleteid;
		 }
		 public String getEventcalendarxml() {
			 return eventcalendarxml;
		 }
		 public void setEventcalendarxml(String eventcalendarxml) {
			 this.eventcalendarxml=eventcalendarxml;
		 }
		 public String getEventcalendardeleteid() {
			 return eventcalendardeleteid;
		 }
		 public void setEventcalendardeleteid(String eventcalendardeleteid) {
			 this.eventcalendardeleteid=eventcalendardeleteid;
		 }
		 public String getTravelcalendarxml() {
			 return travelcalendarxml;
		 }
		 public void setTravelcalendarxml(String travelcalendarxml) {
			 this.travelcalendarxml=travelcalendarxml;
		 }
		 public String getTravelcalendardeleteid() {
			 return travelcalendardeleteid;
		 }
		 public void setTravelcalendardeleteid(String travelcalendardeleteid) {
			 this.travelcalendardeleteid=travelcalendardeleteid;
		 }


		  public  TemplateTable  doSelect(String[] column,String[] datatype,String parentfilter,boolean isform,String accessfilter){
			 String sql="";
			 TemplateQuery query =new TemplateQuery();
			//do some custom pre query operation if any 
			 EventListener.registerPreQueryParent("Financialyear",column,datatype);
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
					EventListener.registerPostQueryParent("Financialyear",column,datatype);
			 return(maindata);
		 }


		 public  TemplateTable  doSelectChild(String childname,String relfield,String pid,String[]column,String[]datatype,String childfilter,String accessfilter){
			String sql=""; 
			TemplateTable data =new TemplateTable();
			TemplateQuery query =new TemplateQuery();
			// Do some pre query child operation for custom implementation
			EventListener.registerPreQueryChild("Financialyear",childname,pid,relfield,column,datatype);
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
					EventListener.registerPostQueryChild("Financialyear",childname,pid,relfield,column,datatype);
			}
			return(data);
		}
		public  boolean   doDelete(String[] childtabs){
			TemplateQuery query =new TemplateQuery();
			// Do some pre delete operation 
			String sql=query.removeSql("Financialyear",this.getParentobjid(),childtabs,groupuser);
			if(!tu.isEmptyValue(sql)){
				tu.applyObjectRuleForDelete("Financialyear",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_BEFORE,this.getParentobjid());
				EventListener.registerPreDeleteEvent("Financialyear",this.getParentobjid());
				query.setQuery(sql);
				if(ACONST.GENERATE_LOG)
					logger.info(query.getQuery());
				if (query.getTableResultset().getRowCount()>0){
					// Do some post delete operation
					tu.applyObjectRuleForDelete("Financialyear",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_AFTER,this.getParentobjid());
					EventListener.registerPostDeleteEvent("Financialyear",this.getParentobjid());
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
					tu.applyObjectRule("Financialyear",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,maindata);
					EventListener.registerPreInsertEvent("Financialyear",maindata);
			}
			if(sql==null){
					if(ACONST.GENERATE_LOG)
						logger.info(" WARNING: Parent record exists! Duplicate record");
				return(false);
			}else{
				 if(ACONST.GENERATE_LOG)
				 logger.info("parent ObjId="+query.getObjId());
				setParentobjid(query.getObjId()); 
				 if(!tu.isEmptyValue(this.getCampaigncalendarxml()) ){
					 sql+=query.makeBulkSQL(false,getCampaigncalendarxml(),"CampaignCalendar2FinancialYear",(QueryImpl)this);
					 campaigncalendardata=query.getTableData();
					 tu.applyObjectRule("Campaigncalendar",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,campaigncalendardata);
					 EventListener.registerPreInsertEvent("CampaignCalendar",campaigncalendardata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"CampaignCalendar","CampaignCalendar2FinancialYear",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getEventcalendarxml()) ){
					 sql+=query.makeBulkSQL(false,getEventcalendarxml(),"EventCalendar2FinancialYear",(QueryImpl)this);
					 eventcalendardata=query.getTableData();
					 tu.applyObjectRule("Eventcalendar",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,eventcalendardata);
					 EventListener.registerPreInsertEvent("EventCalendar",eventcalendardata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"EventCalendar","EventCalendar2FinancialYear",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getTravelcalendarxml()) ){
					 sql+=query.makeBulkSQL(false,getTravelcalendarxml(),"TravelCalendar2FinancialYear",(QueryImpl)this);
					 travelcalendardata=query.getTableData();
					 tu.applyObjectRule("Travelcalendar",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,travelcalendardata);
					 EventListener.registerPreInsertEvent("TravelCalendar",travelcalendardata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"TravelCalendar","TravelCalendar2FinancialYear",getParentobjid());
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
				 tu.applyObjectRule("Financialyear",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,maindata);
				 tu.applyMTMRelation("Financialyear","Financialyear",getParentobjid());
				 EventListener.registerPostInsertEvent("Financialyear",maindata);
				 tu.applyObjectRule("Campaigncalendar",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,campaigncalendardata);
				 tu.applyMTMRelation("campaigncalendar","Financialyear",getParentobjid());
				 EventListener.registerPostInsertEvent("CampaignCalendar",campaigncalendardata);
				 tu.applyObjectRule("Eventcalendar",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,eventcalendardata);
				 tu.applyMTMRelation("eventcalendar","Financialyear",getParentobjid());
				 EventListener.registerPostInsertEvent("EventCalendar",eventcalendardata);
				 tu.applyObjectRule("Travelcalendar",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,travelcalendardata);
				 tu.applyMTMRelation("travelcalendar","Financialyear",getParentobjid());
				 EventListener.registerPostInsertEvent("TravelCalendar",travelcalendardata);
				 return(true);
			}
			 return(false);
		}
	}
