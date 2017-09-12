
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

	 public class CodeattributeImpl extends QueryImpl implements QueryEvent {
		 protected static Log logger = LogFactory.getLog(CodeattributeImpl.class); 
 
		 protected String customcodexml,customcodedeleteid;
		 protected String genericcodexml,genericcodedeleteid;

		 protected TemplateTable customcodedata=new TemplateTable();

		 protected TemplateTable genericcodedata=new TemplateTable();

		 public String CodeattributeFilter="";
		 public String CustomcodeFilter="";
		 public String GenericcodeFilter="";

		 public String codeattributeAccessFilter="";

		 public String customcodeAccessFilter="";

		 public String genericcodeAccessFilter="";
		 public String getCustomcodexml() {
			 return customcodexml;
		 }
		 public void setCustomcodexml(String customcodexml) {
			 this.customcodexml=customcodexml;
		 }
		 public String getCustomcodedeleteid() {
			 return customcodedeleteid;
		 }
		 public void setCustomcodedeleteid(String customcodedeleteid) {
			 this.customcodedeleteid=customcodedeleteid;
		 }
		 public String getGenericcodexml() {
			 return genericcodexml;
		 }
		 public void setGenericcodexml(String genericcodexml) {
			 this.genericcodexml=genericcodexml;
		 }
		 public String getGenericcodedeleteid() {
			 return genericcodedeleteid;
		 }
		 public void setGenericcodedeleteid(String genericcodedeleteid) {
			 this.genericcodedeleteid=genericcodedeleteid;
		 }


		  public  TemplateTable  doSelect(String[] column,String[] datatype,String parentfilter,boolean isform,String accessfilter){
			 String sql="";
			 TemplateQuery query =new TemplateQuery();
			//do some custom pre query operation if any 
			 EventListener.registerPreQueryParent("Codeattribute",column,datatype);
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
					EventListener.registerPostQueryParent("Codeattribute",column,datatype);
			 return(maindata);
		 }


		 public  TemplateTable  doSelectChild(String childname,String relfield,String pid,String[]column,String[]datatype,String childfilter,String accessfilter){
			String sql=""; 
			TemplateTable data =new TemplateTable();
			TemplateQuery query =new TemplateQuery();
			// Do some pre query child operation for custom implementation
			EventListener.registerPreQueryChild("Codeattribute",childname,pid,relfield,column,datatype);
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
					EventListener.registerPostQueryChild("Codeattribute",childname,pid,relfield,column,datatype);
			}
			return(data);
		}
		public  boolean   doDelete(String[] childtabs){
			TemplateQuery query =new TemplateQuery();
			// Do some pre delete operation 
			String sql=query.removeSql("Codeattribute",this.getParentobjid(),childtabs,groupuser);
			if(!tu.isEmptyValue(sql)){
				tu.applyObjectRuleForDelete("Codeattribute",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_BEFORE,this.getParentobjid());
				EventListener.registerPreDeleteEvent("Codeattribute",this.getParentobjid());
				query.setQuery(sql);
				if(ACONST.GENERATE_LOG)
					logger.info(query.getQuery());
				if (query.getTableResultset().getRowCount()>0){
					// Do some post delete operation
					tu.applyObjectRuleForDelete("Codeattribute",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_AFTER,this.getParentobjid());
					EventListener.registerPostDeleteEvent("Codeattribute",this.getParentobjid());
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
					tu.applyObjectRule("Codeattribute",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,maindata);
					EventListener.registerPreInsertEvent("Codeattribute",maindata);
			}
			if(sql==null){
					if(ACONST.GENERATE_LOG)
						logger.info(" WARNING: Parent record exists! Duplicate record");
				return(false);
			}else{
				 if(ACONST.GENERATE_LOG)
				 logger.info("parent ObjId="+query.getObjId());
				setParentobjid(query.getObjId()); 
				 if(!tu.isEmptyValue(this.getCustomcodexml()) ){
					 sql+=query.makeBulkSQL(false,getCustomcodexml(),"CustomCode2CodeAttribute",(QueryImpl)this);
					 customcodedata=query.getTableData();
					 tu.applyObjectRule("Customcode",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,customcodedata);
					 EventListener.registerPreInsertEvent("CustomCode",customcodedata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"CustomCode","CustomCode2CodeAttribute",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getGenericcodexml()) ){
					 sql+=query.makeBulkSQL(false,getGenericcodexml(),"GenericCode2CodeAttribute",(QueryImpl)this);
					 genericcodedata=query.getTableData();
					 tu.applyObjectRule("Genericcode",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,genericcodedata);
					 EventListener.registerPreInsertEvent("GenericCode",genericcodedata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"GenericCode","GenericCode2CodeAttribute",getParentobjid());
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
				 tu.applyObjectRule("Codeattribute",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,maindata);
				 tu.applyMTMRelation("Codeattribute","Codeattribute",getParentobjid());
				 EventListener.registerPostInsertEvent("Codeattribute",maindata);
				 tu.applyObjectRule("Customcode",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,customcodedata);
				 tu.applyMTMRelation("customcode","Codeattribute",getParentobjid());
				 EventListener.registerPostInsertEvent("CustomCode",customcodedata);
				 tu.applyObjectRule("Genericcode",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,genericcodedata);
				 tu.applyMTMRelation("genericcode","Codeattribute",getParentobjid());
				 EventListener.registerPostInsertEvent("GenericCode",genericcodedata);
				 return(true);
			}
			 return(false);
		}
	}
