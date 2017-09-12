
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

	 public class ObjectruleImpl extends QueryImpl implements QueryEvent {
		 protected static Log logger = LogFactory.getLog(ObjectruleImpl.class); 
 
		 protected String actionqueryxml,actionquerydeleteid;

		 protected TemplateTable actionquerydata=new TemplateTable();

		 public String ObjectruleFilter="";
		 public String ActionqueryFilter="";

		 public String objectruleAccessFilter="";

		 public String actionqueryAccessFilter="";
		 public String getActionqueryxml() {
			 return actionqueryxml;
		 }
		 public void setActionqueryxml(String actionqueryxml) {
			 this.actionqueryxml=actionqueryxml;
		 }
		 public String getActionquerydeleteid() {
			 return actionquerydeleteid;
		 }
		 public void setActionquerydeleteid(String actionquerydeleteid) {
			 this.actionquerydeleteid=actionquerydeleteid;
		 }


		  public  TemplateTable  doSelect(String[] column,String[] datatype,String parentfilter,boolean isform,String accessfilter){
			 String sql="";
			 TemplateQuery query =new TemplateQuery();
			//do some custom pre query operation if any 
			 EventListener.registerPreQueryParent("Objectrule",column,datatype);
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
					EventListener.registerPostQueryParent("Objectrule",column,datatype);
			 return(maindata);
		 }


		 public  TemplateTable  doSelectChild(String childname,String relfield,String pid,String[]column,String[]datatype,String childfilter,String accessfilter){
			String sql=""; 
			TemplateTable data =new TemplateTable();
			TemplateQuery query =new TemplateQuery();
			// Do some pre query child operation for custom implementation
			EventListener.registerPreQueryChild("Objectrule",childname,pid,relfield,column,datatype);
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
					EventListener.registerPostQueryChild("Objectrule",childname,pid,relfield,column,datatype);
			}
			return(data);
		}
		public  boolean   doDelete(String[] childtabs){
			TemplateQuery query =new TemplateQuery();
			// Do some pre delete operation 
			String sql=query.removeSql("Objectrule",this.getParentobjid(),childtabs,groupuser);
			if(!tu.isEmptyValue(sql)){
				tu.applyObjectRuleForDelete("Objectrule",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_BEFORE,this.getParentobjid());
				EventListener.registerPreDeleteEvent("Objectrule",this.getParentobjid());
				query.setQuery(sql);
				if(ACONST.GENERATE_LOG)
					logger.info(query.getQuery());
				if (query.getTableResultset().getRowCount()>0){
					// Do some post delete operation
					tu.applyObjectRuleForDelete("Objectrule",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_AFTER,this.getParentobjid());
					EventListener.registerPostDeleteEvent("Objectrule",this.getParentobjid());
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
					sql=query.makeBulkSQL(true,this.getMainxml(),"ObjectRule2Object",(QueryImpl)this);
					maindata=query.getTableData();
					tu.applyObjectRule("Objectrule",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,maindata);
					EventListener.registerPreInsertEvent("Objectrule",maindata);
			}
			if(sql==null){
					if(ACONST.GENERATE_LOG)
						logger.info(" WARNING: Parent record exists! Duplicate record");
				return(false);
			}else{
				 if(ACONST.GENERATE_LOG)
				 logger.info("parent ObjId="+query.getObjId());
				setParentobjid(query.getObjId()); 
				 if(!tu.isEmptyValue(this.getActionqueryxml()) ){
					 sql+=query.makeBulkSQL(false,getActionqueryxml(),"ActionQuery2ObjectRule",(QueryImpl)this);
					 actionquerydata=query.getTableData();
					 tu.applyObjectRule("Actionquery",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,actionquerydata);
					 EventListener.registerPreInsertEvent("ActionQuery",actionquerydata);
				
				 String [] autofieldActionQuerylist={"tablename"};
				 usql+="\n\t\t"+tu.copyParent2Child(maindata,"ActionQuery",autofieldActionQuerylist,"ActionQuery2ObjectRule",getParentobjid());
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"ActionQuery","ActionQuery2ObjectRule",getParentobjid());
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
				 tu.applyObjectRule("Objectrule",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,maindata);
				 tu.applyMTMRelation("Objectrule","Objectrule",getParentobjid());
				 EventListener.registerPostInsertEvent("Objectrule",maindata);
				 tu.applyObjectRule("Actionquery",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,actionquerydata);
				 tu.applyMTMRelation("actionquery","Objectrule",getParentobjid());
				 EventListener.registerPostInsertEvent("ActionQuery",actionquerydata);
				 return(true);
			}
			 return(false);
		}
	}