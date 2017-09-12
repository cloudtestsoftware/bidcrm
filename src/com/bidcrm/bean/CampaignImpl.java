
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

	 public class CampaignImpl extends QueryImpl implements QueryEvent {
		 protected static Log logger = LogFactory.getLog(CampaignImpl.class); 
 
		 protected String cmpmemberxml,cmpmemberdeleteid;
		 protected String contactlistxml,contactlistdeleteid;
		 protected String emailsettingxml,emailsettingdeleteid;
		 protected String automationxml,automationdeleteid;
		 protected String samplereservexml,samplereservedeleteid;
		 protected String emailresponsexml,emailresponsedeleteid;
		 protected String sampleorderxml,sampleorderdeleteid;
		 protected String samplesxml,samplesdeleteid;
		 protected String campaignnotexml,campaignnotedeleteid;
		 protected String surveyquestionxml,surveyquestiondeleteid;

		 protected TemplateTable cmpmemberdata=new TemplateTable();

		 protected TemplateTable contactlistdata=new TemplateTable();

		 protected TemplateTable emailsettingdata=new TemplateTable();

		 protected TemplateTable automationdata=new TemplateTable();

		 protected TemplateTable samplereservedata=new TemplateTable();

		 protected TemplateTable emailresponsedata=new TemplateTable();

		 protected TemplateTable sampleorderdata=new TemplateTable();

		 protected TemplateTable samplesdata=new TemplateTable();

		 protected TemplateTable campaignnotedata=new TemplateTable();

		 protected TemplateTable surveyquestiondata=new TemplateTable();

		 public String CampaignFilter="cmpemailcount.pctresponse PctResponse,cmpemailcount.totalemail TotalEmail,cmpemailcount.totalresponse TotalResponse,cmpemailcount.totalopened TotalOpened,cmpemailcount.totalclicked TotalClicked,cmpemailcount.totalordered TotalOrdered@campaign,cmpemailcount@campaign.objid=cmpemailcount.cmpemailcount2campaign(+)";
		 public String CmpmemberFilter="employee.email Email,employee.phone Phone,employee.phone2 Phone2@CmpMember,Employee,SalesRep@CmpMember.CmpMember2SalesRep=SalesRep.objid and SalesRep.objid=employee.objid";
		 public String ContactlistFilter="";
		 public String EmailsettingFilter="MasterTemplate.url MasterUrl,MasterTemplate.LockedBy LockedBy,MasterTemplate.LockedDate LockedDate@EmailSetting,MasterTemplate@EmailSetting.EmailSetting2MasterTemplate=MasterTemplate.objid";
		 public String AutomationFilter="EmailSetting.StageCode SourceStageCode,EmailSetting.ChannelsCode SourceChannelsCode,NextChannel.StageCode TargetStageCode,NextChannel.ChannelsCode TargetChannelsCode@Automation, EmailSetting, NextChannel@EmailSetting.objid=Automation.Automation2EmailSetting and NextChannel.objid=Automation.Automation2NextChannel";
		 public String SamplereserveFilter="";
		 public String EmailresponseFilter="";
		 public String SampleorderFilter="sampleordertotal.ordertotal OrderTotal@sampleorder,sampleordertotal@sampleorder.objid=sampleordertotal.objid(+)";
		 public String SamplesFilter="Parts.partspec Description,Parts.unitprice Price,storepartcount.ordered QntOrdered,storepartcount.available QntOnHand@samples,Parts,storeparts,storepartcount@samples.samples2Parts=Parts.objid and parts.objid=storeparts2parts and storeparts.objid=storepartcount.objid(+)";
		 public String CampaignnoteFilter="";
		 public String SurveyquestionFilter="";

		 public String campaignAccessFilter="";

		 public String cmpmemberAccessFilter="";

		 public String contactlistAccessFilter="";

		 public String emailsettingAccessFilter="";

		 public String automationAccessFilter="";

		 public String samplereserveAccessFilter="";

		 public String emailresponseAccessFilter="";

		 public String sampleorderAccessFilter="";

		 public String samplesAccessFilter="";

		 public String campaignnoteAccessFilter="";

		 public String surveyquestionAccessFilter="";
		 public String getCmpmemberxml() {
			 return cmpmemberxml;
		 }
		 public void setCmpmemberxml(String cmpmemberxml) {
			 this.cmpmemberxml=cmpmemberxml;
		 }
		 public String getCmpmemberdeleteid() {
			 return cmpmemberdeleteid;
		 }
		 public void setCmpmemberdeleteid(String cmpmemberdeleteid) {
			 this.cmpmemberdeleteid=cmpmemberdeleteid;
		 }
		 public String getContactlistxml() {
			 return contactlistxml;
		 }
		 public void setContactlistxml(String contactlistxml) {
			 this.contactlistxml=contactlistxml;
		 }
		 public String getContactlistdeleteid() {
			 return contactlistdeleteid;
		 }
		 public void setContactlistdeleteid(String contactlistdeleteid) {
			 this.contactlistdeleteid=contactlistdeleteid;
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
		 public String getAutomationxml() {
			 return automationxml;
		 }
		 public void setAutomationxml(String automationxml) {
			 this.automationxml=automationxml;
		 }
		 public String getAutomationdeleteid() {
			 return automationdeleteid;
		 }
		 public void setAutomationdeleteid(String automationdeleteid) {
			 this.automationdeleteid=automationdeleteid;
		 }
		 public String getSamplereservexml() {
			 return samplereservexml;
		 }
		 public void setSamplereservexml(String samplereservexml) {
			 this.samplereservexml=samplereservexml;
		 }
		 public String getSamplereservedeleteid() {
			 return samplereservedeleteid;
		 }
		 public void setSamplereservedeleteid(String samplereservedeleteid) {
			 this.samplereservedeleteid=samplereservedeleteid;
		 }
		 public String getEmailresponsexml() {
			 return emailresponsexml;
		 }
		 public void setEmailresponsexml(String emailresponsexml) {
			 this.emailresponsexml=emailresponsexml;
		 }
		 public String getEmailresponsedeleteid() {
			 return emailresponsedeleteid;
		 }
		 public void setEmailresponsedeleteid(String emailresponsedeleteid) {
			 this.emailresponsedeleteid=emailresponsedeleteid;
		 }
		 public String getSampleorderxml() {
			 return sampleorderxml;
		 }
		 public void setSampleorderxml(String sampleorderxml) {
			 this.sampleorderxml=sampleorderxml;
		 }
		 public String getSampleorderdeleteid() {
			 return sampleorderdeleteid;
		 }
		 public void setSampleorderdeleteid(String sampleorderdeleteid) {
			 this.sampleorderdeleteid=sampleorderdeleteid;
		 }
		 public String getSamplesxml() {
			 return samplesxml;
		 }
		 public void setSamplesxml(String samplesxml) {
			 this.samplesxml=samplesxml;
		 }
		 public String getSamplesdeleteid() {
			 return samplesdeleteid;
		 }
		 public void setSamplesdeleteid(String samplesdeleteid) {
			 this.samplesdeleteid=samplesdeleteid;
		 }
		 public String getCampaignnotexml() {
			 return campaignnotexml;
		 }
		 public void setCampaignnotexml(String campaignnotexml) {
			 this.campaignnotexml=campaignnotexml;
		 }
		 public String getCampaignnotedeleteid() {
			 return campaignnotedeleteid;
		 }
		 public void setCampaignnotedeleteid(String campaignnotedeleteid) {
			 this.campaignnotedeleteid=campaignnotedeleteid;
		 }
		 public String getSurveyquestionxml() {
			 return surveyquestionxml;
		 }
		 public void setSurveyquestionxml(String surveyquestionxml) {
			 this.surveyquestionxml=surveyquestionxml;
		 }
		 public String getSurveyquestiondeleteid() {
			 return surveyquestiondeleteid;
		 }
		 public void setSurveyquestiondeleteid(String surveyquestiondeleteid) {
			 this.surveyquestiondeleteid=surveyquestiondeleteid;
		 }


		  public  TemplateTable  doSelect(String[] column,String[] datatype,String parentfilter,boolean isform,String accessfilter){
			 String sql="";
			 TemplateQuery query =new TemplateQuery();
			//do some custom pre query operation if any 
			 EventListener.registerPreQueryParent("Campaign",column,datatype);
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
					EventListener.registerPostQueryParent("Campaign",column,datatype);
			 return(maindata);
		 }


		 public  TemplateTable  doSelectChild(String childname,String relfield,String pid,String[]column,String[]datatype,String childfilter,String accessfilter){
			String sql=""; 
			TemplateTable data =new TemplateTable();
			TemplateQuery query =new TemplateQuery();
			// Do some pre query child operation for custom implementation
			EventListener.registerPreQueryChild("Campaign",childname,pid,relfield,column,datatype);
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
					EventListener.registerPostQueryChild("Campaign",childname,pid,relfield,column,datatype);
			}
			return(data);
		}
		public  boolean   doDelete(String[] childtabs){
			TemplateQuery query =new TemplateQuery();
			// Do some pre delete operation 
			String sql=query.removeSql("Campaign",this.getParentobjid(),childtabs,groupuser);
			if(!tu.isEmptyValue(sql)){
				tu.applyObjectRuleForDelete("Campaign",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_BEFORE,this.getParentobjid());
				EventListener.registerPreDeleteEvent("Campaign",this.getParentobjid());
				query.setQuery(sql);
				if(ACONST.GENERATE_LOG)
					logger.info(query.getQuery());
				if (query.getTableResultset().getRowCount()>0){
					// Do some post delete operation
					tu.applyObjectRuleForDelete("Campaign",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_AFTER,this.getParentobjid());
					EventListener.registerPostDeleteEvent("Campaign",this.getParentobjid());
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
					tu.applyObjectRule("Campaign",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,maindata);
					EventListener.registerPreInsertEvent("Campaign",maindata);
			}
			if(sql==null){
					if(ACONST.GENERATE_LOG)
						logger.info(" WARNING: Parent record exists! Duplicate record");
				return(false);
			}else{
				 if(ACONST.GENERATE_LOG)
				 logger.info("parent ObjId="+query.getObjId());
				setParentobjid(query.getObjId()); 
				 if(!tu.isEmptyValue(this.getCmpmemberxml()) ){
					 sql+=query.makeBulkSQL(false,getCmpmemberxml(),"CmpMember2Campaign",(QueryImpl)this);
					 cmpmemberdata=query.getTableData();
					 tu.applyObjectRule("Cmpmember",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,cmpmemberdata);
					 EventListener.registerPreInsertEvent("CmpMember",cmpmemberdata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"CmpMember","CmpMember2Campaign",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getContactlistxml()) ){
					 sql+=query.makeBulkSQL(false,getContactlistxml(),"ContactList2Campaign",(QueryImpl)this);
					 contactlistdata=query.getTableData();
					 tu.applyObjectRule("Contactlist",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,contactlistdata);
					 EventListener.registerPreInsertEvent("ContactList",contactlistdata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"ContactList","ContactList2Campaign",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getEmailsettingxml()) ){
					 sql+=query.makeBulkSQL(false,getEmailsettingxml(),"EmailSetting2Campaign",(QueryImpl)this);
					 emailsettingdata=query.getTableData();
					 tu.applyObjectRule("Emailsetting",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,emailsettingdata);
					 EventListener.registerPreInsertEvent("EmailSetting",emailsettingdata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"EmailSetting","EmailSetting2Campaign",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getAutomationxml()) ){
					 sql+=query.makeBulkSQL(false,getAutomationxml(),"Automation2Campaign",(QueryImpl)this);
					 automationdata=query.getTableData();
					 tu.applyObjectRule("Automation",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,automationdata);
					 EventListener.registerPreInsertEvent("Automation",automationdata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"Automation","Automation2Campaign",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getSamplereservexml()) ){
					 sql+=query.makeBulkSQL(false,getSamplereservexml(),"SampleReserve2Campaign",(QueryImpl)this);
					 samplereservedata=query.getTableData();
					 tu.applyObjectRule("Samplereserve",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,samplereservedata);
					 EventListener.registerPreInsertEvent("SampleReserve",samplereservedata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"SampleReserve","SampleReserve2Campaign",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getEmailresponsexml()) ){
					 sql+=query.makeBulkSQL(false,getEmailresponsexml(),"EmailResponse2Campaign",(QueryImpl)this);
					 emailresponsedata=query.getTableData();
					 tu.applyObjectRule("Emailresponse",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,emailresponsedata);
					 EventListener.registerPreInsertEvent("EmailResponse",emailresponsedata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"EmailResponse","EmailResponse2Campaign",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getSampleorderxml()) ){
					 sql+=query.makeBulkSQL(false,getSampleorderxml(),"SampleOrder2Campaign",(QueryImpl)this);
					 sampleorderdata=query.getTableData();
					 tu.applyObjectRule("Sampleorder",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,sampleorderdata);
					 EventListener.registerPreInsertEvent("SampleOrder",sampleorderdata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"SampleOrder","SampleOrder2Campaign",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getSamplesxml()) ){
					 sql+=query.makeBulkSQL(false,getSamplesxml(),"Samples2Campaign",(QueryImpl)this);
					 samplesdata=query.getTableData();
					 tu.applyObjectRule("Samples",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,samplesdata);
					 EventListener.registerPreInsertEvent("Samples",samplesdata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"Samples","Samples2Campaign",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getCampaignnotexml()) ){
					 sql+=query.makeBulkSQL(false,getCampaignnotexml(),"CampaignNote2Campaign",(QueryImpl)this);
					 campaignnotedata=query.getTableData();
					 tu.applyObjectRule("Campaignnote",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,campaignnotedata);
					 EventListener.registerPreInsertEvent("CampaignNote",campaignnotedata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"CampaignNote","CampaignNote2Campaign",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getSurveyquestionxml()) ){
					 sql+=query.makeBulkSQL(false,getSurveyquestionxml(),"SurveyQuestion2Campaign",(QueryImpl)this);
					 surveyquestiondata=query.getTableData();
					 tu.applyObjectRule("Surveyquestion",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,surveyquestiondata);
					 EventListener.registerPreInsertEvent("SurveyQuestion",surveyquestiondata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"SurveyQuestion","SurveyQuestion2Campaign",getParentobjid());
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
				 tu.applyObjectRule("Campaign",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,maindata);
				 tu.applyMTMRelation("Campaign","Campaign",getParentobjid());
				 EventListener.registerPostInsertEvent("Campaign",maindata);
				 tu.applyObjectRule("Cmpmember",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,cmpmemberdata);
				 tu.applyMTMRelation("cmpmember","Campaign",getParentobjid());
				 EventListener.registerPostInsertEvent("CmpMember",cmpmemberdata);
				 tu.applyObjectRule("Contactlist",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,contactlistdata);
				 tu.applyMTMRelation("contactlist","Campaign",getParentobjid());
				 EventListener.registerPostInsertEvent("ContactList",contactlistdata);
				 tu.applyObjectRule("Emailsetting",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,emailsettingdata);
				 tu.applyMTMRelation("emailsetting","Campaign",getParentobjid());
				 EventListener.registerPostInsertEvent("EmailSetting",emailsettingdata);
				 tu.applyObjectRule("Automation",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,automationdata);
				 tu.applyMTMRelation("automation","Campaign",getParentobjid());
				 EventListener.registerPostInsertEvent("Automation",automationdata);
				 tu.applyObjectRule("Samplereserve",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,samplereservedata);
				 tu.applyMTMRelation("samplereserve","Campaign",getParentobjid());
				 EventListener.registerPostInsertEvent("SampleReserve",samplereservedata);
				 tu.applyConsoleObject("samplereserve",samplereservedata,this.getUsername(),groupuser,false);
				 tu.applyObjectRule("Emailresponse",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,emailresponsedata);
				 tu.applyMTMRelation("emailresponse","Campaign",getParentobjid());
				 EventListener.registerPostInsertEvent("EmailResponse",emailresponsedata);
				 tu.applyObjectRule("Sampleorder",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,sampleorderdata);
				 tu.applyMTMRelation("sampleorder","Campaign",getParentobjid());
				 EventListener.registerPostInsertEvent("SampleOrder",sampleorderdata);
				 tu.applyObjectRule("Samples",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,samplesdata);
				 tu.applyMTMRelation("samples","Campaign",getParentobjid());
				 EventListener.registerPostInsertEvent("Samples",samplesdata);
				 tu.applyObjectRule("Campaignnote",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,campaignnotedata);
				 tu.applyMTMRelation("campaignnote","Campaign",getParentobjid());
				 EventListener.registerPostInsertEvent("CampaignNote",campaignnotedata);
				 tu.applyObjectRule("Surveyquestion",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,surveyquestiondata);
				 tu.applyMTMRelation("surveyquestion","Campaign",getParentobjid());
				 EventListener.registerPostInsertEvent("SurveyQuestion",surveyquestiondata);
				 return(true);
			}
			 return(false);
		}
	}
