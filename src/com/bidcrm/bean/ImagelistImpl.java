
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

	 public class ImagelistImpl extends QueryImpl implements QueryEvent {
		 protected static Log logger = LogFactory.getLog(ImagelistImpl.class); 
 
		 protected String imagesxml,imagesdeleteid;
		 protected String mastertemplatexml,mastertemplatedeleteid;

		 protected TemplateTable imagesdata=new TemplateTable();

		 protected TemplateTable mastertemplatedata=new TemplateTable();

		 public String ImagelistFilter="";
		 public String ImagesFilter="";
		 public String MastertemplateFilter="";

		 public String imagelistAccessFilter="";

		 public String imagesAccessFilter="";

		 public String mastertemplateAccessFilter="select m.objid from table_employee e,table_mastertemplate m,table_reviewer r where e.objid=r.reviewer2employee and r.reviewer2mastertemplate=m.objid and upper(m.groupuser)=upper(@groupuser)";
		 public String getImagesxml() {
			 return imagesxml;
		 }
		 public void setImagesxml(String imagesxml) {
			 this.imagesxml=imagesxml;
		 }
		 public String getImagesdeleteid() {
			 return imagesdeleteid;
		 }
		 public void setImagesdeleteid(String imagesdeleteid) {
			 this.imagesdeleteid=imagesdeleteid;
		 }
		 public String getMastertemplatexml() {
			 return mastertemplatexml;
		 }
		 public void setMastertemplatexml(String mastertemplatexml) {
			 this.mastertemplatexml=mastertemplatexml;
		 }
		 public String getMastertemplatedeleteid() {
			 return mastertemplatedeleteid;
		 }
		 public void setMastertemplatedeleteid(String mastertemplatedeleteid) {
			 this.mastertemplatedeleteid=mastertemplatedeleteid;
		 }


		  public  TemplateTable  doSelect(String[] column,String[] datatype,String parentfilter,boolean isform,String accessfilter){
			 String sql="";
			 TemplateQuery query =new TemplateQuery();
			//do some custom pre query operation if any 
			 EventListener.registerPreQueryParent("Imagelist",column,datatype);
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
					EventListener.registerPostQueryParent("Imagelist",column,datatype);
			 return(maindata);
		 }


		 public  TemplateTable  doSelectChild(String childname,String relfield,String pid,String[]column,String[]datatype,String childfilter,String accessfilter){
			String sql=""; 
			TemplateTable data =new TemplateTable();
			TemplateQuery query =new TemplateQuery();
			// Do some pre query child operation for custom implementation
			EventListener.registerPreQueryChild("Imagelist",childname,pid,relfield,column,datatype);
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
					EventListener.registerPostQueryChild("Imagelist",childname,pid,relfield,column,datatype);
			}
			return(data);
		}
		public  boolean   doDelete(String[] childtabs){
			TemplateQuery query =new TemplateQuery();
			// Do some pre delete operation 
			String sql=query.removeSql("Imagelist",this.getParentobjid(),childtabs,groupuser);
			if(!tu.isEmptyValue(sql)){
				tu.applyObjectRuleForDelete("Imagelist",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_BEFORE,this.getParentobjid());
				EventListener.registerPreDeleteEvent("Imagelist",this.getParentobjid());
				query.setQuery(sql);
				if(ACONST.GENERATE_LOG)
					logger.info(query.getQuery());
				if (query.getTableResultset().getRowCount()>0){
					// Do some post delete operation
					tu.applyObjectRuleForDelete("Imagelist",ACONST.EVENT_REASON_DELETE, ACONST.EVENT_STATE_AFTER,this.getParentobjid());
					EventListener.registerPostDeleteEvent("Imagelist",this.getParentobjid());
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
					tu.applyObjectRule("Imagelist",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,maindata);
					EventListener.registerPreInsertEvent("Imagelist",maindata);
			}
			if(sql==null){
					if(ACONST.GENERATE_LOG)
						logger.info(" WARNING: Parent record exists! Duplicate record");
				return(false);
			}else{
				 if(ACONST.GENERATE_LOG)
				 logger.info("parent ObjId="+query.getObjId());
				setParentobjid(query.getObjId()); 
				 if(!tu.isEmptyValue(this.getImagesxml()) ){
					 sql+=query.makeBulkSQL(false,getImagesxml(),"Images2ImageList",(QueryImpl)this);
					 imagesdata=query.getTableData();
					 tu.applyObjectRule("Images",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,imagesdata);
					 EventListener.registerPreInsertEvent("Images",imagesdata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"Images","Images2ImageList",getParentobjid());
				}
				 if(!tu.isEmptyValue(this.getMastertemplatexml()) ){
					 sql+=query.makeBulkSQL(false,getMastertemplatexml(),"MasterTemplate2ImageList",(QueryImpl)this);
					 mastertemplatedata=query.getTableData();
					 tu.applyObjectRule("Mastertemplate",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_BEFORE,mastertemplatedata);
					 EventListener.registerPreInsertEvent("MasterTemplate",mastertemplatedata);
				 usql+="\n\t\t"+tu.copyParentBQN2Child(this.getObject(),"MasterTemplate","MasterTemplate2ImageList",getParentobjid());
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
				 tu.applyObjectRule("Imagelist",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,maindata);
				 tu.applyMTMRelation("Imagelist","Imagelist",getParentobjid());
				 EventListener.registerPostInsertEvent("Imagelist",maindata);
				 tu.applyObjectRule("Images",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,imagesdata);
				 tu.applyMTMRelation("images","Imagelist",getParentobjid());
				 EventListener.registerPostInsertEvent("Images",imagesdata);
				 tu.applyObjectRule("Mastertemplate",ACONST.EVENT_REASON_INSERT, ACONST.EVENT_STATE_AFTER,mastertemplatedata);
				 tu.applyMTMRelation("mastertemplate","Imagelist",getParentobjid());
				 EventListener.registerPostInsertEvent("MasterTemplate",mastertemplatedata);
				 return(true);
			}
			 return(false);
		}
	}
