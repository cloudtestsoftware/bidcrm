
 	 package com.bidcrm.dao; 

 	 import java.util.Map; 
 	 import java.util.ArrayList; 
	 import java.util.Arrays; 
	 import javax.ws.rs.core.Cookie;
 	 import javax.ws.rs.core.HttpHeaders; 
	 import javax.ws.rs.core.UriInfo; 
	 import cms.service.app.ServiceManager;
	 import cms.service.dhtmlx.*;
	 import cms.service.dhtmlx.forms.Items;
	 import cms.service.exceptions.DaoException; 
	 import cms.service.exceptions.AuthenticationException;
	 import org.json.JSONException;
	 import org.json.JSONObject;
	 import cms.service.jdbc.DataType; 
	 import cms.service.event.QueryImpl;
	 import cms.service.template.TemplateTable; 
	 import com.bidcrm.bean.*; 
 
 	 /** A simple bean that has a single String property 
	 *  called message. 
 	 *  
	 * @author S.K Jana Version 1.0 
 	 * @Copyright : This code belongs to BidERP.com. All right reserved! 
 	 * @since 2005-2017 
 	 */ 

	public class AttributeDao extends AttributeImpl {
		Map<String, Cookie> cookies; 
		Map<String,String> userdata;
		protected String []deletetabs={"attribute,attrprivilege,customlist,listproperty"};
		protected String []childtabs={"attrprivilege,customlist,listproperty"};
		protected String []childtabnames={"AttrPrivilege,CustomList,ListProperty"};
		
		protected String [] maintype={"table"};
		protected String [] maincol={"objid","attribute2object","name","tablename","hasproperty","hascodeobject"};
		protected String [] maincolcaption={"Id","Object","Attribute Name","Table Name","Has Property","Has Code"};
		protected String [] mainsqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] maindatadomain={"Raw_t","Raw_t","Name_t","Name_t","Type_t","Type_t"};
		protected String [] maincolsearch={"#text_filter,#select_filter,#text_filter,#text_filter,#text_filter,#text_filter"};
		
		protected String [] maincoldisable={"yes,no,no,no,no,no"};
		private String [] mainformfields={"input","input","input","input","input","input"};
		
		protected String [] summarycol={"name"};
		protected String [] summarycolcaption={"Name"};
		protected String [] summarysqldatatype={DataType.VARCHAR};
		protected String [] summarydatadomain={"Name_t"};
		
		protected String [] reportcol={"objid","name","tablename"};
		protected String [] reportcolcaption={"Id","Attribute Name","Table Name"};
		protected String [] reportsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] reportdatadomain={"Id_t","Name_t","Name_t"};
		
		protected String [] searchcol={"objid","name","tablename","hasproperty","hascodeobject"};
		protected String [] searchcolcaption={"Id","Attribute Name","Table Name","Has Property","Has Code"};
		protected String [] searchcoltype={"integer","text","text","text","text"};
		protected String [] searchdatadomain={"Id_t","Name_t","Name_t","Type_t","Type_t"};

	protected String [] propAttributelist={"hasproperty","hascodeobject"};
		protected String [] codeAttributelist={};
		protected String [] relationAttributelist={"object:attribute2object:list:"};
		
		protected String [] attrprivilegetype={"table"};
		protected String [] attrprivilegecol={"objid","attrprivilege2objectprivilege","name","value"};
		protected String [] attrprivilegecolcaption={"Id","ObjectPrivilege","Attribute Name","Privelege Value"};
		protected String [] attrprivilegesqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] attrprivilegedatadomain={"Raw_t","Raw_t","Name_t","String20_t"};
		protected String [] attrprivilegedisable={"yes,no,no,no"};
		protected String [] attrprivilegecolsearch={"#text_filter,#select_filter,#text_filter,#select_filter"};
		
		protected String [] customlisttype={"table"};
		protected String [] customlistcol={"objid","customlist2object","propertystring","propertyvalue","propindex"};
		protected String [] customlistcolcaption={"Id","Object","Property String","Property Value","Index"};
		protected String [] customlistsqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.INTEGER};
		protected String [] customlistdatadomain={"Raw_t","Raw_t","String50_t","String50_t","Int_t"};
		protected String [] customlistdisable={"yes,no,no,no,no"};
		protected String [] customlistcolsearch={"#text_filter,#select_filter,#text_filter,,"};
		
		protected String [] listpropertytype={"table"};
		protected String [] listpropertycol={"objid","listproperty2object","name","tablename","propertystring","propertyvalue","propindex"};
		protected String [] listpropertycolcaption={"Id","Object","Rule Name","Table Name","Property String","Property Value","Index"};
		protected String [] listpropertysqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.INTEGER};
		protected String [] listpropertydatadomain={"Raw_t","Raw_t","Name_t","Name_t","String50_t","String50_t","Int_t"};
		protected String [] listpropertydisable={"yes,no,no,no,no,no,no"};
		protected String [] listpropertycolsearch={"#text_filter,#select_filter,#text_filter,#text_filter,#text_filter,,"};

		public AttributeDao(UriInfo uriInfo, HttpHeaders header) throws AuthenticationException{
			this.setObject("Attribute");
			if(!tu.isEmptyValue(uriInfo.getQueryParameters().getFirst("generate_log"))){
					ACONST.GENERATE_LOG=true;
			}
			if(!tu.isEmptyValue(uriInfo.getPathParameters().getFirst("id"))){
				this.setParentobjid(uriInfo.getPathParameters().getFirst("id").replace("id-", ""));
			}else if(!tu.isEmptyValue(uriInfo.getQueryParameters().getFirst("searchfilter"))){
				this.setSearchdata(uriInfo.getQueryParameters().getFirst("searchfilter")+""+(char)2);
			}else{
				this.setSearchdata("ObjId"+(char)1+"All"+(char)1+""+(char)2);
			}
			if(!tu.isEmptyValue(uriInfo.getQueryParameters().getFirst("token"))){
				this.setToken(uriInfo.getQueryParameters().getFirst("token"));
				this.userdata=ServiceManager.verifyUserToken(this.getToken());
			}
			if(this.userdata!=null &&!this.userdata.isEmpty()){
				this.groupuser=userdata.get("groupuser");
				this.username=userdata.get("username");
				this.setSearchdata(this.getSearchdata()+"groupuser"+(char)1+"="+(char)1+getGroupuser());
			}else{
				throw new AuthenticationException("Authentication Failed for user="+username+" Token ="+ this.getToken());
			}
			if(!tu.isEmptyValue(uriInfo.getQueryParameters().getFirst("pagesize"))){
				this.setPagesize(Integer.parseInt(uriInfo.getQueryParameters().getFirst("pagesize")));
			}
			if(!tu.isEmptyValue(uriInfo.getQueryParameters().getFirst("bqn"))){
				this.setBqn(uriInfo.getQueryParameters().getFirst("bqn"));
			}
			if(!tu.isEmptyValue(uriInfo.getQueryParameters().getFirst("page"))){
				this.setPage(Integer.parseInt(uriInfo.getQueryParameters().getFirst("page")));
			}
			if(!tu.isEmptyValue(uriInfo.getQueryParameters().getFirst("X-Forwarded-For"))){
				this.setClientip(uriInfo.getQueryParameters().getFirst("X-Forwarded-For"));
			}
			if(!tu.isEmptyValue(uriInfo.getQueryParameters().getFirst("relationfilter"))){
				this.setRelationFilters(uriInfo.getQueryParameters().getFirst("relationfilter"));
			}
			if(!tu.isEmptyValue(uriInfo.getQueryParameters().getFirst("filters"))){
				this.setFilters(uriInfo.getQueryParameters().getFirst("filters"));
			}
			if(ACONST.GENERATE_LOG){
				logger.info("getPathParameters="+uriInfo.getPathParameters().values());
				logger.info("getQueryParameters="+uriInfo.getQueryParameters().values());
				logger.info("User Data="+this.userdata.toString());
			}
			this.cookies=header.getCookies();
		}

		public void setPostXml(String xml) throws DaoException{
			if(tu.isEmptyValue(xml)) throw new DaoException("ERROR: Post XML Is null or empty");
			if(!xml.contains("<?xml")) throw new DaoException("ERROR: Please provide xml document header at the begining of each entity in the POST XML body.");
			String [] entitys=xml.split("<?xml");
			for(String entity:entitys){
				String tmp="";
				if(entity.toLowerCase().contains("<attribute>")){
					tmp=entity.replace("<?", "");
					this.setMainxml("<?xml"+tmp);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Main XML="+this.getMainxml());
					}
				}else if(entity.toLowerCase().contains("<attrprivilege>")){
					this.setAttrprivilegexml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getAttrprivilegexml());
					}
				}else if(entity.toLowerCase().contains("<customlist>")){
					this.setCustomlistxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getCustomlistxml());
					}
				}else if(entity.toLowerCase().contains("<listproperty>")){
					this.setListpropertyxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getListpropertyxml());
					}
				}
			}
		}

		public Rows getAttrprivilegeRows(){
			TemplateTable tab=this.doSelectChild("attrprivilege", "attrprivilege2attribute",this.getParentobjid(),attrprivilegecol,attrprivilegesqldatatype,this.AttrprivilegeFilter,attrprivilegeAccessFilter);
			String [] propAttrprivilegelist={"value"};
			String [] codeAttrprivilegelist={};
			String [] relationAttrprivilegelist={"objectprivilege:attrprivilege2objectprivilege:list:"};
			Rows rows=tu.getXMLRows(tab, "attrprivilege",codeAttrprivilegelist,propAttrprivilegelist,relationAttrprivilegelist,attrprivilegecolcaption,attrprivilegedatadomain,(QueryImpl)this);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(attrprivilegecolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(attrprivilegedisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(attrprivilegetype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getCustomlistRows(){
			TemplateTable tab=this.doSelectChild("customlist", "customlist2attribute",this.getParentobjid(),customlistcol,customlistsqldatatype,this.CustomlistFilter,customlistAccessFilter);
			String [] propCustomlistlist={};
			String [] codeCustomlistlist={};
			String [] relationCustomlistlist={"object:customlist2object:list:"};
			Rows rows=tu.getXMLRows(tab, "customlist",codeCustomlistlist,propCustomlistlist,relationCustomlistlist,customlistcolcaption,customlistdatadomain,(QueryImpl)this);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(customlistcolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(customlistdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(customlisttype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getListpropertyRows(){
			TemplateTable tab=this.doSelectChild("listproperty", "listproperty2attribute",this.getParentobjid(),listpropertycol,listpropertysqldatatype,this.ListpropertyFilter,listpropertyAccessFilter);
			String [] propListpropertylist={};
			String [] codeListpropertylist={};
			String [] relationListpropertylist={"object:listproperty2object:list:"};
			Rows rows=tu.getXMLRows(tab, "listproperty",codeListpropertylist,propListpropertylist,relationListpropertylist,listpropertycolcaption,listpropertydatadomain,(QueryImpl)this);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(listpropertycolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(listpropertydisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(listpropertytype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getAttributeSummaryRows(){
			TemplateTable tab=this.doSelect(summarycol,summarysqldatatype,this.AttributeFilter,false,attributeAccessFilter);
			ArrayList<String> chartcols=tu.getChartSelectColumns("Attribute");
			ArrayList<String> moneycols=tu.getSummaryMoneyColsIndex(summarydatadomain);
			Rows rows=tu.getXMLSummaryRows(tab,summarycolcaption);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("charts",chartcols);
			userdata.add(data1);
			for(String chartcol:chartcols){
				ArrayList<String> datas= tu.getChartPropertyJSON("Attribute", tab, chartcol);
				ArrayList<String> data2= new ArrayList<String>();
				data2.add(datas.get(0));
				Userdata chart= new Userdata(chartcol+".chart",data2);
				userdata.add(chart);
				ArrayList<String> data3= new ArrayList<String>();
				data3.add(datas.get(1));
				Userdata griddata= new Userdata(chartcol+".data",data3);
				userdata.add(griddata);
			}
			Userdata data4= new Userdata("grid.moneycols",moneycols);
			userdata.add(data4);
			rows.setUserdata(userdata);
			return rows;
		}

		public Items getAttributeForm(){
			TemplateTable tab=this.doSelect(maincol,mainsqldatatype,this.AttributeFilter,true,attributeAccessFilter);
			Items items=tu.getXMLForm(tab, "Attribute",codeAttributelist,propAttributelist,relationAttributelist,maincolcaption,maindatadomain,mainformfields,(QueryImpl)this);
			return items;
		}

		public Rows getAttributeRows(){
			TemplateTable tab=this.doSelect(maincol,mainsqldatatype,null,false,attributeAccessFilter);
			Rows rows=tu.getXMLRows(tab, "Attribute",codeAttributelist,propAttributelist,relationAttributelist,maincolcaption,maindatadomain,(QueryImpl)this);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("tabs",Arrays.asList(childtabs));
			Userdata data2= new Userdata("tabnames",Arrays.asList(childtabnames));
			Userdata data3= new Userdata("filters",Arrays.asList(maincolsearch));
			Userdata data4= new Userdata("deletetabs",Arrays.asList(deletetabs));
			Userdata data5= new Userdata("disablecols",Arrays.asList(maincoldisable));
			Userdata data6= new Userdata("tabletype",Arrays.asList(maintype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			userdata.add(data4);
			userdata.add(data5);
			userdata.add(data6);
			rows.setUserdata(userdata);
			return rows;
		}

		public Rows getAttributeRowModified(){
			Rows rows=tu.getXMLRows(maindata, "Attribute",codeAttributelist,propAttributelist,relationAttributelist,maincolcaption,maindatadomain,(QueryImpl)this);
			return rows;
		}

		public Rows getAttributeRowDeleted(){
			Rows rows;
			if(this.doDelete(childtabs)){
				rows=tu.getDeletedRows(this.getParentobjid());
			}else{
				rows=tu.getDeletedRows("-1");
			}
			return rows;
		}

		public boolean postAttributeContainer() throws DaoException{
			if(!tu.isEmptyValue(this.getMainxml())){
				return(this.doInsert());
			}else{
				throw new DaoException("ERROR: Post unsuccessful! Probably your XML is missing parent entity or having error!", this.getClass().getName());
			}
		}

		public Rows getAttributeByFilter(){
			String newfilter=" groupuser='"+this.getGroupuser()+"'";
			if(!tu.isEmptyValue(this.getFilters()) &&this.getFilters().toLowerCase().indexOf("attribute2")<0){
				newfilter+=" and attribute2"+this.getFilters();
				this.setParentobjid(this.getFilters().split("=")[1].replaceAll("'",""));
			}else{
				newfilter+=" and "+this.getFilters();
			}
			String sql= "select * from table_Attribute where "+ newfilter;
			TemplateTable tab=tu.getResultSet(sql);
			Rows rows=tu.getXMLFilterRows(tab, "Attribute",codeAttributelist,propAttributelist,relationAttributelist,maincol,maincolcaption,maindatadomain,(QueryImpl)this);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("tabs",Arrays.asList(childtabs));
			Userdata data2= new Userdata("tabnames",Arrays.asList(childtabnames));
			Userdata data3= new Userdata("filters",Arrays.asList(maincolsearch));
			Userdata data4= new Userdata("deletetabs",Arrays.asList(deletetabs));
			Userdata data5= new Userdata("disablecols",Arrays.asList(maincoldisable));
			Userdata data6= new Userdata("tabletype",Arrays.asList(maintype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			userdata.add(data4);
			userdata.add(data5);
			userdata.add(data6);
			rows.setUserdata(userdata);
			return rows;
		}

		 public JSONObject getAttributeRecordCount() throws JSONException{
			String newfilter=" groupuser='"+this.getGroupuser()+"'";
			if(!tu.isEmptyValue(this.getFilters()) &&this.getFilters().toLowerCase().indexOf("attribute2")<0){
				newfilter+=" and attribute2"+this.getFilters();
			}else{
				newfilter+=" and "+this.getFilters();
			}
			String sql= "select * from table_Attribute where "+ newfilter;
			TemplateTable tab=tu.getResultSet(sql);
			JSONObject data=new JSONObject();
			data.put("table",this.getObject());
			data.put("rowcount",tab.getRowCount());
			return data;
			}
	}
