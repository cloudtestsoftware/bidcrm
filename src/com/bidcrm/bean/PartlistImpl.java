
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

	 public class PartlistImpl extends QueryImpl implements QueryEvent {
		 protected static Log logger = LogFactory.getLog(PartlistImpl.class); 
 
		 protected String partsxml,partsdeleteid;
		 protected String storepartlistxml,storepartlistdeleteid;

		 protected TemplateTable partsdata=new TemplateTable();

		 protected TemplateTable storepartlistdata=new TemplateTable();

		 public String PartlistFilter="";
		 public String PartsFilter="partlist.partlistno PartListNo@partlist,Parts@partlist.objid=Parts.Parts2partlist order by Parts.name";
		 public String StorepartlistFilter="partlist.partlistno PartListNo,partlist.description Description@partlist,storepartlist@storepartlist2partlist=partlist.objid";

		 public String partlistAccessFilter="";

		 public String partsAccessFilter="";

		 public String storepartlistAccessFilter="";
		 public String getPartsxml() {
			 return partsxml;
		 }
		 public void setPartsxml(String partsxml) {
			 this.partsxml=partsxml;
		 }
		 public String getPartsdeleteid() {
			 return partsdeleteid;
		 }
		 public void setPartsdeleteid(String partsdeleteid) {
			 this.partsdeleteid=partsdeleteid;
		 }
		 public String getStorepartlistxml() {
			 return storepartlistxml;
		 }
		 public void setStorepartlistxml(String storepartlistxml) {
			 this.storepartlistxml=storepartlistxml;
		 }
		 public String getStorepartlistdeleteid() {
			 return storepartlistdeleteid;
		 }
		 public void setStorepartlistdeleteid(String storepartlistdeleteid) {
			 this.storepartlistdeleteid=storepartlistdeleteid;
		 }


		  public  TemplateTable  doSelect(String[] column,String[] datatype,String parentfilter,boolean isform,String accessfilter){
			 String sql="";
			 TemplateQuery query =new TemplateQuery();
			//do some custom pre query operation if any 
			 EventListener.registerPreQueryParent("Partlist",column,datatype);
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
					EventListener.registerPostQueryParent("Partlist",column,datatype);
			 return(maindata);
		 }


		 public  TemplateTable  doSelectChild(String childname,String relfield,String pid,String[]column,String[]datatype,String childfilter,String accessfilter){
			String sql=""; 
			TemplateTable data =new TemplateTable();
			TemplateQuery query =new TemplateQuery();
			// Do some pre query child operation for custom implementation
			EventListener.registerPreQueryChild("Partlist",childname,pid,relfield,column,datatype);
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
					EventListener.registerPostQueryChild("Partlist",childname,pid,relfield,column,datatype);
			}
			return(data);
		}
		public  boolean   doDelete(String[] childtabs){
			TemplateQuery query =new TemplateQuery();
			// Do some pre delete operation 
			String sql=query.removeSql("Partlist",this.getParentobjid(),childtabs,groupuser);
			if(!tu.isEmptyValue(sql)){
				tu.applyObjectRuleForDelete("Partlist",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_BEFORE,this.getParentobjid());
				EventListener.registerPreDeleteEvent("Partlist",this.getParentobjid());
				query.setQuery(sql);
				if(ACONST.GENERATE_LOG)
					logger.info(query.getQuery());
				if (query.getTableResultset().getRowCount()>0){
					// Do some post delete operation
					tu.applyObjectRuleForDelete("Partlist",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_AFTER,this.getParentobjid());
					EventListener.registerPostDeleteEvent("Partlist",this.getParentobjid());
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
					sql=query.makeBulkSQL(true,this.getMainxml(),"PartList2BizDomain",(QueryImpl)this);
					maindata=query.getTableData();
					tu.applyObjectRule("Partlist",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,maindata);
					EventListener.registerPreInsertEvent("Partlist",maindata);
			}
			if(sql==null){
					if(ACONST.GENERATE_LOG)
						logger.info(" WARNING: Parent record exists! Duplicate record");
				return(false);
			}else{
				 if(ACONST.GENERATE_LOG)
				 logger.info("parent ObjId="+query.getObjId());
				setParentobjid(query.getObjId()); 
				 if(!tu.isEmptyValue(this.getPartsxml()) ){
					 sql+=query.makeBulkSQL(false,getPartsxml(),"Parts2PartList",(QueryImpl)this);
					 partsdata=query.getTableData();
					 tu.applyObjectRule("Parts",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,partsdata);
					 EventListener.registerPreInsertEvent("Parts",partsdata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"Parts","Parts2PartList",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getStorepartlistxml()) ){
					 sql+=query.makeBulkSQL(false,getStorepartlistxml(),"StorePartList2PartList",(QueryImpl)this);
					 storepartlistdata=query.getTableData();
					 tu.applyObjectRule("Storepartlist",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,storepartlistdata);
					 EventListener.registerPreInsertEvent("StorePartList",storepartlistdata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"StorePartList","StorePartList2PartList",getParentobjid());
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
				 tu.applyObjectRule("Partlist",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,maindata);
				 tu.applyMTMRelation("Partlist","Partlist",getParentobjid());
				 EventListener.registerPostInsertEvent("Partlist",maindata);
				 tu.applyObjectRule("Parts",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,partsdata);
				 tu.applyMTMRelation("parts","Partlist",getParentobjid());
				 EventListener.registerPostInsertEvent("Parts",partsdata);
				 tu.applyObjectRule("Storepartlist",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,storepartlistdata);
				 tu.applyMTMRelation("storepartlist","Partlist",getParentobjid());
				 EventListener.registerPostInsertEvent("StorePartList",storepartlistdata);
				 return(true);
			}
			 return(false);
		}
	}
