
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

	 public class SamplesImpl extends QueryImpl implements QueryEvent {
		 protected static Log logger = LogFactory.getLog(SamplesImpl.class); 
 
		 protected String sampleorderitemxml,sampleorderitemdeleteid;
		 protected String reordersamplesxml,reordersamplesdeleteid;

		 protected TemplateTable sampleorderitemdata=new TemplateTable();

		 protected TemplateTable reordersamplesdata=new TemplateTable();

		 public String SamplesFilter="Parts.partspec Description,Parts.unitprice Price,storepartcount.ordered QntOrdered,storepartcount.available QntOnHand@samples,Parts,storeparts,storepartcount@samples.samples2Parts=Parts.objid and parts.objid=storeparts2parts and storeparts.objid=storepartcount.objid(+)";
		 public String SampleorderitemFilter="samples.name Name,orderitemtotal.partspec Description,orderitemtotal.unitprice Price,orderitemtotal.itemamount ItemAmount,orderitemtotal.taxamount TaxAmount,orderitemtotal.totalamount TotalAmount@SampleOrderItem,samples,orderitemtotal@SampleOrderItem2samples=samples.objid and SampleOrderItem.objid=orderitemtotal.orderitemtotal2sampleorderitem(+)";
		 public String ReordersamplesFilter="";

		 public String samplesAccessFilter="";

		 public String sampleorderitemAccessFilter="";

		 public String reordersamplesAccessFilter="";
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
		 public String getReordersamplesxml() {
			 return reordersamplesxml;
		 }
		 public void setReordersamplesxml(String reordersamplesxml) {
			 this.reordersamplesxml=reordersamplesxml;
		 }
		 public String getReordersamplesdeleteid() {
			 return reordersamplesdeleteid;
		 }
		 public void setReordersamplesdeleteid(String reordersamplesdeleteid) {
			 this.reordersamplesdeleteid=reordersamplesdeleteid;
		 }


		  public  TemplateTable  doSelect(String[] column,String[] datatype,String parentfilter,boolean isform,String accessfilter){
			 String sql="";
			 TemplateQuery query =new TemplateQuery();
			//do some custom pre query operation if any 
			 EventListener.registerPreQueryParent("Samples",column,datatype);
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
					EventListener.registerPostQueryParent("Samples",column,datatype);
			 return(maindata);
		 }


		 public  TemplateTable  doSelectChild(String childname,String relfield,String pid,String[]column,String[]datatype,String childfilter,String accessfilter){
			String sql=""; 
			TemplateTable data =new TemplateTable();
			TemplateQuery query =new TemplateQuery();
			// Do some pre query child operation for custom implementation
			EventListener.registerPreQueryChild("Samples",childname,pid,relfield,column,datatype);
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
					EventListener.registerPostQueryChild("Samples",childname,pid,relfield,column,datatype);
			}
			return(data);
		}
		public  boolean   doDelete(String[] childtabs){
			TemplateQuery query =new TemplateQuery();
			// Do some pre delete operation 
			String sql=query.removeSql("Samples",this.getParentobjid(),childtabs,groupuser);
			if(!tu.isEmptyValue(sql)){
				tu.applyObjectRuleForDelete("Samples",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_BEFORE,this.getParentobjid());
				EventListener.registerPreDeleteEvent("Samples",this.getParentobjid());
				query.setQuery(sql);
				if(ACONST.GENERATE_LOG)
					logger.info(query.getQuery());
				if (query.getTableResultset().getRowCount()>0){
					// Do some post delete operation
					tu.applyObjectRuleForDelete("Samples",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_AFTER,this.getParentobjid());
					EventListener.registerPostDeleteEvent("Samples",this.getParentobjid());
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
					sql=query.makeBulkSQL(true,this.getMainxml(),"Samples2Parts",(QueryImpl)this);
					maindata=query.getTableData();
					tu.applyObjectRule("Samples",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,maindata);
					EventListener.registerPreInsertEvent("Samples",maindata);
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
					 sql+=query.makeBulkSQL(false,getSampleorderitemxml(),"SampleOrderItem2Samples",(QueryImpl)this);
					 sampleorderitemdata=query.getTableData();
					 tu.applyObjectRule("Sampleorderitem",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,sampleorderitemdata);
					 EventListener.registerPreInsertEvent("SampleOrderItem",sampleorderitemdata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"SampleOrderItem","SampleOrderItem2Samples",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getReordersamplesxml()) ){
					 sql+=query.makeBulkSQL(false,getReordersamplesxml(),"ReorderSamples2Samples",(QueryImpl)this);
					 reordersamplesdata=query.getTableData();
					 tu.applyObjectRule("Reordersamples",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,reordersamplesdata);
					 EventListener.registerPreInsertEvent("ReorderSamples",reordersamplesdata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"ReorderSamples","ReorderSamples2Samples",getParentobjid());
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
				 tu.applyObjectRule("Samples",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,maindata);
				 tu.applyMTMRelation("Samples","Samples",getParentobjid());
				 EventListener.registerPostInsertEvent("Samples",maindata);
				 tu.applyObjectRule("Sampleorderitem",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,sampleorderitemdata);
				 tu.applyMTMRelation("sampleorderitem","Samples",getParentobjid());
				 EventListener.registerPostInsertEvent("SampleOrderItem",sampleorderitemdata);
				 tu.applyObjectRule("Reordersamples",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,reordersamplesdata);
				 tu.applyMTMRelation("reordersamples","Samples",getParentobjid());
				 EventListener.registerPostInsertEvent("ReorderSamples",reordersamplesdata);
				 return(true);
			}
			 return(false);
		}
	}
