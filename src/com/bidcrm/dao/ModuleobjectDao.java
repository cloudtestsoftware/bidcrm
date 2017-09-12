
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

	public class ModuleobjectDao extends ModuleobjectImpl {
		Map<String, Cookie> cookies; 
		Map<String,String> userdata;
		protected String []deletetabs={"moduleobject,objectprivilege"};
		protected String []childtabs={"objectprivilege"};
		protected String []childtabnames={"ObjectPrivilege"};
		
		protected String [] maintype={"table"};
		protected String [] maincol={"objid","moduleobject2object","moduleobject2module","moduleobject2submodule","name","title","objectrole","designuse"};
		protected String [] maincolcaption={"Id","Object","Module","SubModule","Table Name","Module Name","Object Role","Design Use"};
		protected String [] mainsqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.INTEGER,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] maindatadomain={"Raw_t","Raw_t","Raw_t","Raw_t","Name_t","Name_t","Type_t","Type_t"};
		protected String [] maincolsearch={"#text_filter,#select_filter,#select_filter,#select_filter,,#text_filter,,"};
		
		protected String [] maincoldisable={"yes,no,no,no,no,no,no,no"};
		private String [] mainformfields={"input","input","input","input","input","input","combo","combo"};
		
		protected String [] summarycol={"name"};
		protected String [] summarycolcaption={"Name"};
		protected String [] summarysqldatatype={DataType.VARCHAR};
		protected String [] summarydatadomain={"Name_t"};
		
		protected String [] reportcol={"objid","title"};
		protected String [] reportcolcaption={"Id","Module Name"};
		protected String [] reportsqldatatype={DataType.VARCHAR,DataType.VARCHAR};
		protected String [] reportdatadomain={"Id_t","Name_t"};
		
		protected String [] searchcol={"objid","title"};
		protected String [] searchcolcaption={"Id","Module Name"};
		protected String [] searchcoltype={"integer","text"};
		protected String [] searchdatadomain={"Id_t","Name_t"};

	protected String [] propModuleobjectlist={"objectrole","designuse"};
		protected String [] codeModuleobjectlist={};
		protected String [] relationModuleobjectlist={"object:moduleobject2object:list:","module:moduleobject2module:list:","submodule:moduleobject2submodule:list:"};
		
		protected String [] objectprivilegetype={"table"};
		protected String [] objectprivilegecol={"objid","objectprivilege2privilegegroup","name","title","value","type","isrecursive"};
		protected String [] objectprivilegecolcaption={"Id","PrivilegeGroup","Table Name","Title","Privelege Value","Privelege Type","Is Recursive"};
		protected String [] objectprivilegesqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] objectprivilegedatadomain={"Raw_t","Raw_t","Name_t","Name_t","String20_t","String20_t","Boolean_t"};
		protected String [] objectprivilegedisable={"yes,no,no,no,no,no,no"};
		protected String [] objectprivilegecolsearch={"#text_filter,#select_filter,#text_filter,#text_filter,#select_filter,#select_filter,#select_filter"};

		public ModuleobjectDao(UriInfo uriInfo, HttpHeaders header) throws AuthenticationException{
			this.setObject("Moduleobject");
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
				if(entity.toLowerCase().contains("<moduleobject>")){
					tmp=entity.replace("<?", "");
					this.setMainxml("<?xml"+tmp);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Main XML="+this.getMainxml());
					}
				}else if(entity.toLowerCase().contains("<objectprivilege>")){
					this.setObjectprivilegexml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getObjectprivilegexml());
					}
				}
			}
		}

		public Rows getObjectprivilegeRows(){
			TemplateTable tab=this.doSelectChild("objectprivilege", "objectprivilege2moduleobject",this.getParentobjid(),objectprivilegecol,objectprivilegesqldatatype,this.ObjectprivilegeFilter,objectprivilegeAccessFilter);
			String [] propObjectprivilegelist={"value","type","isrecursive"};
			String [] codeObjectprivilegelist={};
			String [] relationObjectprivilegelist={"privilegegroup:objectprivilege2privilegegroup:list:"};
			Rows rows=tu.getXMLRows(tab, "objectprivilege",codeObjectprivilegelist,propObjectprivilegelist,relationObjectprivilegelist,objectprivilegecolcaption,objectprivilegedatadomain,(QueryImpl)this);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(objectprivilegecolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(objectprivilegedisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(objectprivilegetype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getModuleobjectSummaryRows(){
			TemplateTable tab=this.doSelect(summarycol,summarysqldatatype,this.ModuleobjectFilter,false,moduleobjectAccessFilter);
			ArrayList<String> chartcols=tu.getChartSelectColumns("Moduleobject");
			ArrayList<String> moneycols=tu.getSummaryMoneyColsIndex(summarydatadomain);
			Rows rows=tu.getXMLSummaryRows(tab,summarycolcaption);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("charts",chartcols);
			userdata.add(data1);
			for(String chartcol:chartcols){
				ArrayList<String> datas= tu.getChartPropertyJSON("Moduleobject", tab, chartcol);
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

		public Items getModuleobjectForm(){
			TemplateTable tab=this.doSelect(maincol,mainsqldatatype,this.ModuleobjectFilter,true,moduleobjectAccessFilter);
			Items items=tu.getXMLForm(tab, "Moduleobject",codeModuleobjectlist,propModuleobjectlist,relationModuleobjectlist,maincolcaption,maindatadomain,mainformfields,(QueryImpl)this);
			return items;
		}

		public Rows getModuleobjectRows(){
			TemplateTable tab=this.doSelect(maincol,mainsqldatatype,null,false,moduleobjectAccessFilter);
			Rows rows=tu.getXMLRows(tab, "Moduleobject",codeModuleobjectlist,propModuleobjectlist,relationModuleobjectlist,maincolcaption,maindatadomain,(QueryImpl)this);
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

		public Rows getModuleobjectRowModified(){
			Rows rows=tu.getXMLRows(maindata, "Moduleobject",codeModuleobjectlist,propModuleobjectlist,relationModuleobjectlist,maincolcaption,maindatadomain,(QueryImpl)this);
			return rows;
		}

		public Rows getModuleobjectRowDeleted(){
			Rows rows;
			if(this.doDelete(childtabs)){
				rows=tu.getDeletedRows(this.getParentobjid());
			}else{
				rows=tu.getDeletedRows("-1");
			}
			return rows;
		}

		public boolean postModuleobjectContainer() throws DaoException{
			if(!tu.isEmptyValue(this.getMainxml())){
				return(this.doInsert());
			}else{
				throw new DaoException("ERROR: Post unsuccessful! Probably your XML is missing parent entity or having error!", this.getClass().getName());
			}
		}

		public Rows getModuleobjectByFilter(){
			String newfilter=" groupuser='"+this.getGroupuser()+"'";
			if(!tu.isEmptyValue(this.getFilters()) &&this.getFilters().toLowerCase().indexOf("moduleobject2")<0){
				newfilter+=" and moduleobject2"+this.getFilters();
				this.setParentobjid(this.getFilters().split("=")[1].replaceAll("'",""));
			}else{
				newfilter+=" and "+this.getFilters();
			}
			String sql= "select * from table_Moduleobject where "+ newfilter;
			TemplateTable tab=tu.getResultSet(sql);
			Rows rows=tu.getXMLFilterRows(tab, "Moduleobject",codeModuleobjectlist,propModuleobjectlist,relationModuleobjectlist,maincol,maincolcaption,maindatadomain,(QueryImpl)this);
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

		 public JSONObject getModuleobjectRecordCount() throws JSONException{
			String newfilter=" groupuser='"+this.getGroupuser()+"'";
			if(!tu.isEmptyValue(this.getFilters()) &&this.getFilters().toLowerCase().indexOf("moduleobject2")<0){
				newfilter+=" and moduleobject2"+this.getFilters();
			}else{
				newfilter+=" and "+this.getFilters();
			}
			String sql= "select * from table_Moduleobject where "+ newfilter;
			TemplateTable tab=tu.getResultSet(sql);
			JSONObject data=new JSONObject();
			data.put("table",this.getObject());
			data.put("rowcount",tab.getRowCount());
			return data;
			}
	}
