
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

	 public class ProfileImpl extends QueryImpl implements QueryEvent {
		 protected static Log logger = LogFactory.getLog(ProfileImpl.class); 
 
		 protected String profilesettingxml,profilesettingdeleteid;
		 protected String subscriptionxml,subscriptiondeleteid;

		 protected TemplateTable profilesettingdata=new TemplateTable();

		 protected TemplateTable subscriptiondata=new TemplateTable();

		 public String ProfileFilter="";
		 public String ProfilesettingFilter="";
		 public String SubscriptionFilter="";

		 public String profileAccessFilter="";

		 public String profilesettingAccessFilter="";

		 public String subscriptionAccessFilter="";
		 public String getProfilesettingxml() {
			 return profilesettingxml;
		 }
		 public void setProfilesettingxml(String profilesettingxml) {
			 this.profilesettingxml=profilesettingxml;
		 }
		 public String getProfilesettingdeleteid() {
			 return profilesettingdeleteid;
		 }
		 public void setProfilesettingdeleteid(String profilesettingdeleteid) {
			 this.profilesettingdeleteid=profilesettingdeleteid;
		 }
		 public String getSubscriptionxml() {
			 return subscriptionxml;
		 }
		 public void setSubscriptionxml(String subscriptionxml) {
			 this.subscriptionxml=subscriptionxml;
		 }
		 public String getSubscriptiondeleteid() {
			 return subscriptiondeleteid;
		 }
		 public void setSubscriptiondeleteid(String subscriptiondeleteid) {
			 this.subscriptiondeleteid=subscriptiondeleteid;
		 }


		  public  TemplateTable  doSelect(String[] column,String[] datatype,String parentfilter,boolean isform,String accessfilter){
			 String sql="";
			 TemplateQuery query =new TemplateQuery();
			//do some custom pre query operation if any 
			 EventListener.registerPreQueryParent("Profile",column,datatype);
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
					EventListener.registerPostQueryParent("Profile",column,datatype);
			 return(maindata);
		 }


		 public  TemplateTable  doSelectChild(String childname,String relfield,String pid,String[]column,String[]datatype,String childfilter,String accessfilter){
			String sql=""; 
			TemplateTable data =new TemplateTable();
			TemplateQuery query =new TemplateQuery();
			// Do some pre query child operation for custom implementation
			EventListener.registerPreQueryChild("Profile",childname,pid,relfield,column,datatype);
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
					EventListener.registerPostQueryChild("Profile",childname,pid,relfield,column,datatype);
			}
			return(data);
		}
		public  boolean   doDelete(String[] childtabs){
			TemplateQuery query =new TemplateQuery();
			// Do some pre delete operation 
			String sql=query.removeSql("Profile",this.getParentobjid(),childtabs,groupuser);
			if(!tu.isEmptyValue(sql)){
				tu.applyObjectRuleForDelete("Profile",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_BEFORE,this.getParentobjid());
				EventListener.registerPreDeleteEvent("Profile",this.getParentobjid());
				query.setQuery(sql);
				if(ACONST.GENERATE_LOG)
					logger.info(query.getQuery());
				if (query.getTableResultset().getRowCount()>0){
					// Do some post delete operation
					tu.applyObjectRuleForDelete("Profile",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_AFTER,this.getParentobjid());
					EventListener.registerPostDeleteEvent("Profile",this.getParentobjid());
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
					tu.applyObjectRule("Profile",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,maindata);
					EventListener.registerPreInsertEvent("Profile",maindata);
			}
			if(sql==null){
					if(ACONST.GENERATE_LOG)
						logger.info(" WARNING: Parent record exists! Duplicate record");
				return(false);
			}else{
				 if(ACONST.GENERATE_LOG)
				 logger.info("parent ObjId="+query.getObjId());
				setParentobjid(query.getObjId()); 
				 if(!tu.isEmptyValue(this.getProfilesettingxml()) ){
					 sql+=query.makeBulkSQL(false,getProfilesettingxml(),"ProfileSetting2Profile",(QueryImpl)this);
					 profilesettingdata=query.getTableData();
					 tu.applyObjectRule("Profilesetting",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,profilesettingdata);
					 EventListener.registerPreInsertEvent("ProfileSetting",profilesettingdata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"ProfileSetting","ProfileSetting2Profile",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getSubscriptionxml()) ){
					 sql+=query.makeBulkSQL(false,getSubscriptionxml(),"Subscription2Profile",(QueryImpl)this);
					 subscriptiondata=query.getTableData();
					 tu.applyObjectRule("Subscription",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,subscriptiondata);
					 EventListener.registerPreInsertEvent("Subscription",subscriptiondata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"Subscription","Subscription2Profile",getParentobjid());
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
				 tu.applyObjectRule("Profile",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,maindata);
				 tu.applyMTMRelation("Profile","Profile",getParentobjid());
				 EventListener.registerPostInsertEvent("Profile",maindata);
				 tu.applyObjectRule("Profilesetting",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,profilesettingdata);
				 tu.applyMTMRelation("profilesetting","Profile",getParentobjid());
				 EventListener.registerPostInsertEvent("ProfileSetting",profilesettingdata);
				 tu.applyObjectRule("Subscription",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,subscriptiondata);
				 tu.applyMTMRelation("subscription","Profile",getParentobjid());
				 EventListener.registerPostInsertEvent("Subscription",subscriptiondata);
				 return(true);
			}
			 return(false);
		}
	}
