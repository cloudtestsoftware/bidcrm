
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

	 public class SampleorderImpl extends QueryImpl implements QueryEvent {
		 protected static Log logger = LogFactory.getLog(SampleorderImpl.class); 
 
		 protected String sampleorderitemxml,sampleorderitemdeleteid;

		 protected TemplateTable sampleorderitemdata=new TemplateTable();

		 public String SampleorderFilter="sampleordertotal.ordertotal OrderTotal@sampleorder,sampleordertotal@sampleorder.objid=sampleordertotal.objid(+)";
		 public String SampleorderitemFilter="samples.name Name,orderitemtotal.partspec Description,orderitemtotal.unitprice Price,orderitemtotal.itemamount ItemAmount,orderitemtotal.taxamount TaxAmount,orderitemtotal.totalamount TotalAmount@SampleOrderItem,samples,orderitemtotal@SampleOrderItem2samples=samples.objid and SampleOrderItem.objid=orderitemtotal.orderitemtotal2sampleorderitem(+)";

		 public String sampleorderAccessFilter="";

		 public String sampleorderitemAccessFilter="";
		 public String getSampleorderitemxml() {
			 return sampleorderitemxml;
		 }
		 public void setSampleorderitemxml(String sampleorderitemxml) {
			 this.sampleorderitemxml=sampleorderitemxml;
		 }
		 public String getSampleorderitemdeleteid() {
			 return sampleorderitemdeleteid;
		 }
		 public void setSampleorderitemdeleteid(String sampleorderitemdeleteid) {
			 this.sampleorderitemdeleteid=sampleorderitemdeleteid;
		 }


		  public  TemplateTable  doSelect(String[] column,String[] datatype,String parentfilter,boolean isform,String accessfilter){
			 String sql="";
			 TemplateQuery query =new TemplateQuery();
			//do some custom pre query operation if any 
			 EventListener.registerPreQueryParent("Sampleorder",column,datatype);
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
					EventListener.registerPostQueryParent("Sampleorder",column,datatype);
			 return(maindata);
		 }


		 public  TemplateTable  doSelectChild(String childname,String relfield,String pid,String[]column,String[]datatype,String childfilter,String accessfilter){
			String sql=""; 
			TemplateTable data =new TemplateTable();
			TemplateQuery query =new TemplateQuery();
			// Do some pre query child operation for custom implementation
			EventListener.registerPreQueryChild("Sampleorder",childname,pid,relfield,column,datatype);
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
					EventListener.registerPostQueryChild("Sampleorder",childname,pid,relfield,column,datatype);
			}
			return(data);
		}
		public  boolean   doDelete(String[] childtabs){
			TemplateQuery query =new TemplateQuery();
			// Do some pre delete operation 
			String sql=query.removeSql("Sampleorder",this.getParentobjid(),childtabs,groupuser);
			if(!tu.isEmptyValue(sql)){
				tu.applyObjectRuleForDelete("Sampleorder",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_BEFORE,this.getParentobjid());
				EventListener.registerPreDeleteEvent("Sampleorder",this.getParentobjid());
				query.setQuery(sql);
				if(ACONST.GENERATE_LOG)
					logger.info(query.getQuery());
				if (query.getTableResultset().getRowCount()>0){
					// Do some post delete operation
					tu.applyObjectRuleForDelete("Sampleorder",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_AFTER,this.getParentobjid());
					EventListener.registerPostDeleteEvent("Sampleorder",this.getParentobjid());
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
					tu.applyObjectRule("Sampleorder",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,maindata);
					EventListener.registerPreInsertEvent("Sampleorder",maindata);
			}
			if(sql==null){
					if(ACONST.GENERATE_LOG)
						logger.info(" WARNING: Parent record exists! Duplicate record");
				return(false);
			}else{
				 if(ACONST.GENERATE_LOG)
				 logger.info("parent ObjId="+query.getObjId());
				setParentobjid(query.getObjId()); 
				 if(!tu.isEmptyValue(this.getSampleorderitemxml()) ){
					 sql+=query.makeBulkSQL(false,getSampleorderitemxml(),"SampleOrderItem2SampleOrder",(QueryImpl)this);
					 sampleorderitemdata=query.getTableData();
					 tu.applyObjectRule("Sampleorderitem",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,sampleorderitemdata);
					 EventListener.registerPreInsertEvent("SampleOrderItem",sampleorderitemdata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"SampleOrderItem","SampleOrderItem2SampleOrder",getParentobjid());
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
				 tu.applyObjectRule("Sampleorder",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,maindata);
				 tu.applyMTMRelation("Sampleorder","Sampleorder",getParentobjid());
				 EventListener.registerPostInsertEvent("Sampleorder",maindata);
				 tu.applyObjectRule("Sampleorderitem",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,sampleorderitemdata);
				 tu.applyMTMRelation("sampleorderitem","Sampleorder",getParentobjid());
				 EventListener.registerPostInsertEvent("SampleOrderItem",sampleorderitemdata);
				 return(true);
			}
			 return(false);
		}
	}
