
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

	 public class SamplereserveImpl extends QueryImpl implements QueryEvent {
		 protected static Log logger = LogFactory.getLog(SamplereserveImpl.class); 
 
		 protected String samplesxml,samplesdeleteid;
		 protected String reordersamplesxml,reordersamplesdeleteid;

		 protected TemplateTable samplesdata=new TemplateTable();

		 protected TemplateTable reordersamplesdata=new TemplateTable();

		 public String SamplereserveFilter="";
		 public String SamplesFilter="Parts.partspec Description,Parts.unitprice Price,storepartcount.ordered QntOrdered,storepartcount.available QntOnHand@samples,Parts,storeparts,storepartcount@samples.samples2Parts=Parts.objid and parts.objid=storeparts2parts and storeparts.objid=storepartcount.objid(+)";
		 public String ReordersamplesFilter="";

		 public String samplereserveAccessFilter="";

		 public String samplesAccessFilter="";

		 public String reordersamplesAccessFilter="";
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
			 EventListener.registerPreQueryParent("Samplereserve",column,datatype);
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
					EventListener.registerPostQueryParent("Samplereserve",column,datatype);
			 return(maindata);
		 }


		 public  TemplateTable  doSelectChild(String childname,String relfield,String pid,String[]column,String[]datatype,String childfilter,String accessfilter){
			String sql=""; 
			TemplateTable data =new TemplateTable();
			TemplateQuery query =new TemplateQuery();
			// Do some pre query child operation for custom implementation
			EventListener.registerPreQueryChild("Samplereserve",childname,pid,relfield,column,datatype);
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
					EventListener.registerPostQueryChild("Samplereserve",childname,pid,relfield,column,datatype);
			}
			return(data);
		}
		public  boolean   doDelete(String[] childtabs){
			TemplateQuery query =new TemplateQuery();
			// Do some pre delete operation 
			String sql=query.removeSql("Samplereserve",this.getParentobjid(),childtabs,groupuser);
			if(!tu.isEmptyValue(sql)){
				tu.applyObjectRuleForDelete("Samplereserve",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_BEFORE,this.getParentobjid());
				EventListener.registerPreDeleteEvent("Samplereserve",this.getParentobjid());
				query.setQuery(sql);
				if(ACONST.GENERATE_LOG)
					logger.info(query.getQuery());
				if (query.getTableResultset().getRowCount()>0){
					// Do some post delete operation
					tu.applyObjectRuleForDelete("Samplereserve",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_AFTER,this.getParentobjid());
					EventListener.registerPostDeleteEvent("Samplereserve",this.getParentobjid());
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
					tu.applyObjectRule("Samplereserve",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,maindata);
					EventListener.registerPreInsertEvent("Samplereserve",maindata);
			}
			if(sql==null){
					if(ACONST.GENERATE_LOG)
						logger.info(" WARNING: Parent record exists! Duplicate record");
				return(false);
			}else{
				 if(ACONST.GENERATE_LOG)
				 logger.info("parent ObjId="+query.getObjId());
				setParentobjid(query.getObjId()); 
				 if(!tu.isEmptyValue(this.getSamplesxml()) ){
					 sql+=query.makeBulkSQL(false,getSamplesxml(),"Samples2SampleReserve",(QueryImpl)this);
					 samplesdata=query.getTableData();
					 tu.applyObjectRule("Samples",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,samplesdata);
					 EventListener.registerPreInsertEvent("Samples",samplesdata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"Samples","Samples2SampleReserve",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getReordersamplesxml()) ){
					 sql+=query.makeBulkSQL(false,getReordersamplesxml(),"ReorderSamples2SampleReserve",(QueryImpl)this);
					 reordersamplesdata=query.getTableData();
					 tu.applyObjectRule("Reordersamples",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,reordersamplesdata);
					 EventListener.registerPreInsertEvent("ReorderSamples",reordersamplesdata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"ReorderSamples","ReorderSamples2SampleReserve",getParentobjid());
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
				 tu.applyConsoleObject("Samplereserve",maindata,this.getUsername(),groupuser,true);
				 tu.applyObjectRule("Samplereserve",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,maindata);
				 tu.applyMTMRelation("Samplereserve","Samplereserve",getParentobjid());
				 EventListener.registerPostInsertEvent("Samplereserve",maindata);
				 tu.applyObjectRule("Samples",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,samplesdata);
				 tu.applyMTMRelation("samples","Samplereserve",getParentobjid());
				 EventListener.registerPostInsertEvent("Samples",samplesdata);
				 tu.applyObjectRule("Reordersamples",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,reordersamplesdata);
				 tu.applyMTMRelation("reordersamples","Samplereserve",getParentobjid());
				 EventListener.registerPostInsertEvent("ReorderSamples",reordersamplesdata);
				 return(true);
			}
			 return(false);
		}
	}
