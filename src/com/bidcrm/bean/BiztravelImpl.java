
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

	 public class BiztravelImpl extends QueryImpl implements QueryEvent {
		 protected static Log logger = LogFactory.getLog(BiztravelImpl.class); 
 
		 protected String travelbookingxml,travelbookingdeleteid;
		 protected String travelexpensexml,travelexpensedeleteid;

		 protected TemplateTable travelbookingdata=new TemplateTable();

		 protected TemplateTable travelexpensedata=new TemplateTable();

		 public String BiztravelFilter="approver.email ApproverEmail,BizTravel.ApprovedBy ApprovedBy,BizTravel.ApprovedDate ApprovedDate@BizTravel,Approver@BizTravel.BizTravel2Approver=Approver.objid(+)";
		 public String TravelbookingFilter="";
		 public String TravelexpenseFilter="";

		 public String biztravelAccessFilter="";

		 public String travelbookingAccessFilter="";

		 public String travelexpenseAccessFilter="";
		 public String getTravelbookingxml() {
			 return travelbookingxml;
		 }
		 public void setTravelbookingxml(String travelbookingxml) {
			 this.travelbookingxml=travelbookingxml;
		 }
		 public String getTravelbookingdeleteid() {
			 return travelbookingdeleteid;
		 }
		 public void setTravelbookingdeleteid(String travelbookingdeleteid) {
			 this.travelbookingdeleteid=travelbookingdeleteid;
		 }
		 public String getTravelexpensexml() {
			 return travelexpensexml;
		 }
		 public void setTravelexpensexml(String travelexpensexml) {
			 this.travelexpensexml=travelexpensexml;
		 }
		 public String getTravelexpensedeleteid() {
			 return travelexpensedeleteid;
		 }
		 public void setTravelexpensedeleteid(String travelexpensedeleteid) {
			 this.travelexpensedeleteid=travelexpensedeleteid;
		 }


		  public  TemplateTable  doSelect(String[] column,String[] datatype,String parentfilter,boolean isform,String accessfilter){
			 String sql="";
			 TemplateQuery query =new TemplateQuery();
			//do some custom pre query operation if any 
			 EventListener.registerPreQueryParent("Biztravel",column,datatype);
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
					EventListener.registerPostQueryParent("Biztravel",column,datatype);
			 return(maindata);
		 }


		 public  TemplateTable  doSelectChild(String childname,String relfield,String pid,String[]column,String[]datatype,String childfilter,String accessfilter){
			String sql=""; 
			TemplateTable data =new TemplateTable();
			TemplateQuery query =new TemplateQuery();
			// Do some pre query child operation for custom implementation
			EventListener.registerPreQueryChild("Biztravel",childname,pid,relfield,column,datatype);
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
					EventListener.registerPostQueryChild("Biztravel",childname,pid,relfield,column,datatype);
			}
			return(data);
		}
		public  boolean   doDelete(String[] childtabs){
			TemplateQuery query =new TemplateQuery();
			// Do some pre delete operation 
			String sql=query.removeSql("Biztravel",this.getParentobjid(),childtabs,groupuser);
			if(!tu.isEmptyValue(sql)){
				tu.applyObjectRuleForDelete("Biztravel",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_BEFORE,this.getParentobjid());
				EventListener.registerPreDeleteEvent("Biztravel",this.getParentobjid());
				query.setQuery(sql);
				if(ACONST.GENERATE_LOG)
					logger.info(query.getQuery());
				if (query.getTableResultset().getRowCount()>0){
					// Do some post delete operation
					tu.applyObjectRuleForDelete("Biztravel",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_AFTER,this.getParentobjid());
					EventListener.registerPostDeleteEvent("Biztravel",this.getParentobjid());
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
					tu.applyObjectRule("Biztravel",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,maindata);
					EventListener.registerPreInsertEvent("Biztravel",maindata);
			}
			if(sql==null){
					if(ACONST.GENERATE_LOG)
						logger.info(" WARNING: Parent record exists! Duplicate record");
				return(false);
			}else{
				 if(ACONST.GENERATE_LOG)
				 logger.info("parent ObjId="+query.getObjId());
				setParentobjid(query.getObjId()); 
				 if(!tu.isEmptyValue(this.getTravelbookingxml()) ){
					 sql+=query.makeBulkSQL(false,getTravelbookingxml(),"TravelBooking2BizTravel",(QueryImpl)this);
					 travelbookingdata=query.getTableData();
					 tu.applyObjectRule("Travelbooking",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,travelbookingdata);
					 EventListener.registerPreInsertEvent("TravelBooking",travelbookingdata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"TravelBooking","TravelBooking2BizTravel",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getTravelexpensexml()) ){
					 sql+=query.makeBulkSQL(false,getTravelexpensexml(),"TravelExpense2BizTravel",(QueryImpl)this);
					 travelexpensedata=query.getTableData();
					 tu.applyObjectRule("Travelexpense",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,travelexpensedata);
					 EventListener.registerPreInsertEvent("TravelExpense",travelexpensedata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"TravelExpense","TravelExpense2BizTravel",getParentobjid());
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
				 tu.applyObjectRule("Biztravel",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,maindata);
				 tu.applyMTMRelation("Biztravel","Biztravel",getParentobjid());
				 EventListener.registerPostInsertEvent("Biztravel",maindata);
				 tu.applyObjectRule("Travelbooking",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,travelbookingdata);
				 tu.applyMTMRelation("travelbooking","Biztravel",getParentobjid());
				 EventListener.registerPostInsertEvent("TravelBooking",travelbookingdata);
				 tu.applyObjectRule("Travelexpense",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,travelexpensedata);
				 tu.applyMTMRelation("travelexpense","Biztravel",getParentobjid());
				 EventListener.registerPostInsertEvent("TravelExpense",travelexpensedata);
				 return(true);
			}
			 return(false);
		}
	}
