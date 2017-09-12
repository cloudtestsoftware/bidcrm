
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

	 public class ModuleImpl extends QueryImpl implements QueryEvent {
		 protected static Log logger = LogFactory.getLog(ModuleImpl.class); 
 
		 protected String moduleobjectxml,moduleobjectdeleteid;
		 protected String submodulexml,submoduledeleteid;

		 protected TemplateTable moduleobjectdata=new TemplateTable();

		 protected TemplateTable submoduledata=new TemplateTable();

		 public String ModuleFilter="";
		 public String ModuleobjectFilter="";
		 public String SubmoduleFilter="";

		 public String moduleAccessFilter="";

		 public String moduleobjectAccessFilter="";

		 public String submoduleAccessFilter="";
		 public String getModuleobjectxml() {
			 return moduleobjectxml;
		 }
		 public void setModuleobjectxml(String moduleobjectxml) {
			 this.moduleobjectxml=moduleobjectxml;
		 }
		 public String getModuleobjectdeleteid() {
			 return moduleobjectdeleteid;
		 }
		 public void setModuleobjectdeleteid(String moduleobjectdeleteid) {
			 this.moduleobjectdeleteid=moduleobjectdeleteid;
		 }
		 public String getSubmodulexml() {
			 return submodulexml;
		 }
		 public void setSubmodulexml(String submodulexml) {
			 this.submodulexml=submodulexml;
		 }
		 public String getSubmoduledeleteid() {
			 return submoduledeleteid;
		 }
		 public void setSubmoduledeleteid(String submoduledeleteid) {
			 this.submoduledeleteid=submoduledeleteid;
		 }


		  public  TemplateTable  doSelect(String[] column,String[] datatype,String parentfilter,boolean isform,String accessfilter){
			 String sql="";
			 TemplateQuery query =new TemplateQuery();
			//do some custom pre query operation if any 
			 EventListener.registerPreQueryParent("Module",column,datatype);
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
					EventListener.registerPostQueryParent("Module",column,datatype);
			 return(maindata);
		 }


		 public  TemplateTable  doSelectChild(String childname,String relfield,String pid,String[]column,String[]datatype,String childfilter,String accessfilter){
			String sql=""; 
			TemplateTable data =new TemplateTable();
			TemplateQuery query =new TemplateQuery();
			// Do some pre query child operation for custom implementation
			EventListener.registerPreQueryChild("Module",childname,pid,relfield,column,datatype);
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
					EventListener.registerPostQueryChild("Module",childname,pid,relfield,column,datatype);
			}
			return(data);
		}
		public  boolean   doDelete(String[] childtabs){
			TemplateQuery query =new TemplateQuery();
			// Do some pre delete operation 
			String sql=query.removeSql("Module",this.getParentobjid(),childtabs,groupuser);
			if(!tu.isEmptyValue(sql)){
				tu.applyObjectRuleForDelete("Module",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_BEFORE,this.getParentobjid());
				EventListener.registerPreDeleteEvent("Module",this.getParentobjid());
				query.setQuery(sql);
				if(ACONST.GENERATE_LOG)
					logger.info(query.getQuery());
				if (query.getTableResultset().getRowCount()>0){
					// Do some post delete operation
					tu.applyObjectRuleForDelete("Module",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_AFTER,this.getParentobjid());
					EventListener.registerPostDeleteEvent("Module",this.getParentobjid());
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
					sql=query.makeBulkSQL(true,this.getMainxml(),"Module2PrivilegeGroup",(QueryImpl)this);
					maindata=query.getTableData();
					tu.applyObjectRule("Module",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,maindata);
					EventListener.registerPreInsertEvent("Module",maindata);
			}
			if(sql==null){
					if(ACONST.GENERATE_LOG)
						logger.info(" WARNING: Parent record exists! Duplicate record");
				return(false);
			}else{
				 if(ACONST.GENERATE_LOG)
				 logger.info("parent ObjId="+query.getObjId());
				setParentobjid(query.getObjId()); 
				 if(!tu.isEmptyValue(this.getModuleobjectxml()) ){
					 sql+=query.makeBulkSQL(false,getModuleobjectxml(),"ModuleObject2Module",(QueryImpl)this);
					 moduleobjectdata=query.getTableData();
					 tu.applyObjectRule("Moduleobject",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,moduleobjectdata);
					 EventListener.registerPreInsertEvent("ModuleObject",moduleobjectdata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"ModuleObject","ModuleObject2Module",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getSubmodulexml()) ){
					 sql+=query.makeBulkSQL(false,getSubmodulexml(),"SubModule2Module",(QueryImpl)this);
					 submoduledata=query.getTableData();
					 tu.applyObjectRule("Submodule",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,submoduledata);
					 EventListener.registerPreInsertEvent("SubModule",submoduledata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"SubModule","SubModule2Module",getParentobjid());
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
				 tu.applyObjectRule("Module",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,maindata);
				 tu.applyMTMRelation("Module","Module",getParentobjid());
				 EventListener.registerPostInsertEvent("Module",maindata);
				 tu.applyObjectRule("Moduleobject",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,moduleobjectdata);
				 tu.applyMTMRelation("moduleobject","Module",getParentobjid());
				 EventListener.registerPostInsertEvent("ModuleObject",moduleobjectdata);
				 tu.applyObjectRule("Submodule",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,submoduledata);
				 tu.applyMTMRelation("submodule","Module",getParentobjid());
				 EventListener.registerPostInsertEvent("SubModule",submoduledata);
				 return(true);
			}
			 return(false);
		}
	}
