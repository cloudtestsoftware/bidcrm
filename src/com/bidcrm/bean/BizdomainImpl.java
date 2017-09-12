
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

	 public class BizdomainImpl extends QueryImpl implements QueryEvent {
		 protected static Log logger = LogFactory.getLog(BizdomainImpl.class); 
 
		 protected String bizprofilexml,bizprofiledeleteid;
		 protected String partlistxml,partlistdeleteid;

		 protected TemplateTable bizprofiledata=new TemplateTable();

		 protected TemplateTable partlistdata=new TemplateTable();

		 public String BizdomainFilter="";
		 public String BizprofileFilter="";
		 public String PartlistFilter="";

		 public String bizdomainAccessFilter="";

		 public String bizprofileAccessFilter="";

		 public String partlistAccessFilter="";
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
		 public String getPartlistxml() {
			 return partlistxml;
		 }
		 public void setPartlistxml(String partlistxml) {
			 this.partlistxml=partlistxml;
		 }
		 public String getPartlistdeleteid() {
			 return partlistdeleteid;
		 }
		 public void setPartlistdeleteid(String partlistdeleteid) {
			 this.partlistdeleteid=partlistdeleteid;
		 }


		  public  TemplateTable  doSelect(String[] column,String[] datatype,String parentfilter,boolean isform,String accessfilter){
			 String sql="";
			 TemplateQuery query =new TemplateQuery();
			//do some custom pre query operation if any 
			 EventListener.registerPreQueryParent("Bizdomain",column,datatype);
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
					EventListener.registerPostQueryParent("Bizdomain",column,datatype);
			 return(maindata);
		 }


		 public  TemplateTable  doSelectChild(String childname,String relfield,String pid,String[]column,String[]datatype,String childfilter,String accessfilter){
			String sql=""; 
			TemplateTable data =new TemplateTable();
			TemplateQuery query =new TemplateQuery();
			// Do some pre query child operation for custom implementation
			EventListener.registerPreQueryChild("Bizdomain",childname,pid,relfield,column,datatype);
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
					EventListener.registerPostQueryChild("Bizdomain",childname,pid,relfield,column,datatype);
			}
			return(data);
		}
		public  boolean   doDelete(String[] childtabs){
			TemplateQuery query =new TemplateQuery();
			// Do some pre delete operation 
			String sql=query.removeSql("Bizdomain",this.getParentobjid(),childtabs,groupuser);
			if(!tu.isEmptyValue(sql)){
				tu.applyObjectRuleForDelete("Bizdomain",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_BEFORE,this.getParentobjid());
				EventListener.registerPreDeleteEvent("Bizdomain",this.getParentobjid());
				query.setQuery(sql);
				if(ACONST.GENERATE_LOG)
					logger.info(query.getQuery());
				if (query.getTableResultset().getRowCount()>0){
					// Do some post delete operation
					tu.applyObjectRuleForDelete("Bizdomain",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_AFTER,this.getParentobjid());
					EventListener.registerPostDeleteEvent("Bizdomain",this.getParentobjid());
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
					tu.applyObjectRule("Bizdomain",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,maindata);
					EventListener.registerPreInsertEvent("Bizdomain",maindata);
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
					 sql+=query.makeBulkSQL(false,getBizprofilexml(),"BizProfile2BizDomain",(QueryImpl)this);
					 bizprofiledata=query.getTableData();
					 tu.applyObjectRule("Bizprofile",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,bizprofiledata);
					 EventListener.registerPreInsertEvent("BizProfile",bizprofiledata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"BizProfile","BizProfile2BizDomain",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getPartlistxml()) ){
					 sql+=query.makeBulkSQL(false,getPartlistxml(),"PartList2BizDomain",(QueryImpl)this);
					 partlistdata=query.getTableData();
					 tu.applyObjectRule("Partlist",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,partlistdata);
					 EventListener.registerPreInsertEvent("PartList",partlistdata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"PartList","PartList2BizDomain",getParentobjid());
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
				 tu.applyObjectRule("Bizdomain",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,maindata);
				 tu.applyMTMRelation("Bizdomain","Bizdomain",getParentobjid());
				 EventListener.registerPostInsertEvent("Bizdomain",maindata);
				 tu.applyObjectRule("Bizprofile",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,bizprofiledata);
				 tu.applyMTMRelation("bizprofile","Bizdomain",getParentobjid());
				 EventListener.registerPostInsertEvent("BizProfile",bizprofiledata);
				 tu.applyObjectRule("Partlist",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,partlistdata);
				 tu.applyMTMRelation("partlist","Bizdomain",getParentobjid());
				 EventListener.registerPostInsertEvent("PartList",partlistdata);
				 return(true);
			}
			 return(false);
		}
	}
