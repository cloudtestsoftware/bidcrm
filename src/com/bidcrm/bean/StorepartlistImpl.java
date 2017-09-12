
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

	 public class StorepartlistImpl extends QueryImpl implements QueryEvent {
		 protected static Log logger = LogFactory.getLog(StorepartlistImpl.class); 
 
		 protected String storepartsxml,storepartsdeleteid;

		 protected TemplateTable storepartsdata=new TemplateTable();

		 public String StorepartlistFilter="partlist.partlistno PartListNo,partlist.description Description@partlist,storepartlist@storepartlist2partlist=partlist.objid";
		 public String StorepartsFilter="partlist.partlistno PartListNo,parts.umcode UmCode,parts.unitprice UnitPrice,parts.pcttax PctTax,parts.partSpec PartSpec,storepartcount.totalinventory TotalInventory,storepartcount.assigned Assigned,storepartcount.available Available,parts.partno PartNo@partlist,parts,storepartlist,StoreParts,storepartcount@partlist.objid=storepartlist.storepartList2partlist  and storepartlist.objid=storeparts.storeparts2storepartlist and partlist.objid=parts.parts2partlist and parts.objid=storeparts2parts and storeparts.objid=storepartcount.objid(+)";

		 public String storepartlistAccessFilter="";

		 public String storepartsAccessFilter="";
		 public String getStorepartsxml() {
			 return storepartsxml;
		 }
		 public void setStorepartsxml(String storepartsxml) {
			 this.storepartsxml=storepartsxml;
		 }
		 public String getStorepartsdeleteid() {
			 return storepartsdeleteid;
		 }
		 public void setStorepartsdeleteid(String storepartsdeleteid) {
			 this.storepartsdeleteid=storepartsdeleteid;
		 }


		  public  TemplateTable  doSelect(String[] column,String[] datatype,String parentfilter,boolean isform,String accessfilter){
			 String sql="";
			 TemplateQuery query =new TemplateQuery();
			//do some custom pre query operation if any 
			 EventListener.registerPreQueryParent("Storepartlist",column,datatype);
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
					EventListener.registerPostQueryParent("Storepartlist",column,datatype);
			 return(maindata);
		 }


		 public  TemplateTable  doSelectChild(String childname,String relfield,String pid,String[]column,String[]datatype,String childfilter,String accessfilter){
			String sql=""; 
			TemplateTable data =new TemplateTable();
			TemplateQuery query =new TemplateQuery();
			// Do some pre query child operation for custom implementation
			EventListener.registerPreQueryChild("Storepartlist",childname,pid,relfield,column,datatype);
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
					EventListener.registerPostQueryChild("Storepartlist",childname,pid,relfield,column,datatype);
			}
			return(data);
		}
		public  boolean   doDelete(String[] childtabs){
			TemplateQuery query =new TemplateQuery();
			// Do some pre delete operation 
			String sql=query.removeSql("Storepartlist",this.getParentobjid(),childtabs,groupuser);
			if(!tu.isEmptyValue(sql)){
				tu.applyObjectRuleForDelete("Storepartlist",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_BEFORE,this.getParentobjid());
				EventListener.registerPreDeleteEvent("Storepartlist",this.getParentobjid());
				query.setQuery(sql);
				if(ACONST.GENERATE_LOG)
					logger.info(query.getQuery());
				if (query.getTableResultset().getRowCount()>0){
					// Do some post delete operation
					tu.applyObjectRuleForDelete("Storepartlist",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_AFTER,this.getParentobjid());
					EventListener.registerPostDeleteEvent("Storepartlist",this.getParentobjid());
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
					sql=query.makeBulkSQL(true,this.getMainxml(),"StorePartList2PartList",(QueryImpl)this);
					maindata=query.getTableData();
					tu.applyObjectRule("Storepartlist",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,maindata);
					EventListener.registerPreInsertEvent("Storepartlist",maindata);
			}
			if(sql==null){
					if(ACONST.GENERATE_LOG)
						logger.info(" WARNING: Parent record exists! Duplicate record");
				return(false);
			}else{
				 if(ACONST.GENERATE_LOG)
				 logger.info("parent ObjId="+query.getObjId());
				setParentobjid(query.getObjId()); 
				 if(!tu.isEmptyValue(this.getStorepartsxml()) ){
					 sql+=query.makeBulkSQL(false,getStorepartsxml(),"StoreParts2StorePartList",(QueryImpl)this);
					 storepartsdata=query.getTableData();
					 tu.applyObjectRule("Storeparts",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,storepartsdata);
					 EventListener.registerPreInsertEvent("StoreParts",storepartsdata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"StoreParts","StoreParts2StorePartList",getParentobjid());
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
				 tu.applyObjectRule("Storepartlist",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,maindata);
				 tu.applyMTMRelation("Storepartlist","Storepartlist",getParentobjid());
				 EventListener.registerPostInsertEvent("Storepartlist",maindata);
				 tu.applyObjectRule("Storeparts",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,storepartsdata);
				 tu.applyMTMRelation("storeparts","Storepartlist",getParentobjid());
				 EventListener.registerPostInsertEvent("StoreParts",storepartsdata);
				 return(true);
			}
			 return(false);
		}
	}
