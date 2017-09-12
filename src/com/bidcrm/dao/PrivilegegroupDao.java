
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

	public class PrivilegegroupDao extends PrivilegegroupImpl {
		Map<String, Cookie> cookies; 
		Map<String,String> userdata;
		protected String []deletetabs={"privilegegroup,user,module,objectprivilege"};
		protected String []childtabs={"user,module,objectprivilege"};
		protected String []childtabnames={"User,Module,ObjectPrivilege"};
		
		protected String [] maintype={"table"};
		protected String [] maincol={"objid","name","scope","status"};
		protected String [] maincolcaption={"Id","Group Name","Scope","Status"};
		protected String [] mainsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] maindatadomain={"Raw_t","String50_t","String100_t","Status_t"};
		protected String [] maincolsearch={"#text_filter,#text_filter,#text_filter,#text_filter"};
		
		protected String [] maincoldisable={"yes,no,no,no"};
		private String [] mainformfields={"input","input","input","input"};
		
		protected String [] summarycol={"name"};
		protected String [] summarycolcaption={"Name"};
		protected String [] summarysqldatatype={DataType.VARCHAR};
		protected String [] summarydatadomain={"Name_t"};
		
		protected String [] reportcol={"objid","name","scope","status"};
		protected String [] reportcolcaption={"Id","Group Name","Scope","Status"};
		protected String [] reportsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] reportdatadomain={"Id_t","String50_t","String100_t","Status_t"};
		
		protected String [] searchcol={"objid","name","scope","status"};
		protected String [] searchcolcaption={"Id","Group Name","Scope","Status"};
		protected String [] searchcoltype={"integer","text","text","text"};
		protected String [] searchdatadomain={"Id_t","String50_t","String100_t","Status_t"};

	protected String [] propPrivilegegrouplist={"status"};
		protected String [] codePrivilegegrouplist={};
		protected String [] relationPrivilegegrouplist={};
		
		protected String [] usertype={"table"};
		protected String [] usercol={"objid","user2company","name","lastname","loginname","status","usertype","email"};
		protected String [] usercolcaption={"Id","Company","Name","Last Name","Login Name","Status","User Type","Email"};
		protected String [] usersqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] userdatadomain={"Raw_t","Raw_t","Name_t","Name_t","Name_t","Status_t","Type_t","Email_t"};
		protected String [] userdisable={"yes,no,no,no,no,no,no,no"};
		protected String [] usercolsearch={"#text_filter,#select_filter,#text_filter,,,,,"};
		
		protected String [] moduletype={"table"};
		protected String [] modulecol={"objid","name","moduleindex"};
		protected String [] modulecolcaption={"Id","Module Name","Index"};
		protected String [] modulesqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.INTEGER};
		protected String [] moduledatadomain={"Raw_t","Name_t","Int_t"};
		protected String [] moduledisable={"yes,no,no"};
		protected String [] modulecolsearch={"#text_filter,#select_filter,"};
		
		protected String [] objectprivilegetype={"table"};
		protected String [] objectprivilegecol={"objid","objectprivilege2moduleobject","name","title","value","type","isrecursive"};
		protected String [] objectprivilegecolcaption={"Id","ModuleObject","Table Name","Title","Privelege Value","Privelege Type","Is Recursive"};
		protected String [] objectprivilegesqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] objectprivilegedatadomain={"Raw_t","Raw_t","Name_t","Name_t","String20_t","String20_t","Boolean_t"};
		protected String [] objectprivilegedisable={"yes,no,no,no,no,no,no"};
		protected String [] objectprivilegecolsearch={"#text_filter,#select_filter,#text_filter,#text_filter,#select_filter,#select_filter,#select_filter"};

		public PrivilegegroupDao(UriInfo uriInfo, HttpHeaders header) throws AuthenticationException{
			this.setObject("Privilegegroup");
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
				if(entity.toLowerCase().contains("<privilegegroup>")){
					tmp=entity.replace("<?", "");
					this.setMainxml("<?xml"+tmp);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Main XML="+this.getMainxml());
					}
				}else if(entity.toLowerCase().contains("<user>")){
					this.setUserxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getUserxml());
					}
				}else if(entity.toLowerCase().contains("<module>")){
					this.setModulexml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getModulexml());
					}
				}else if(entity.toLowerCase().contains("<objectprivilege>")){
					this.setObjectprivilegexml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getObjectprivilegexml());
					}
				}
			}
		}

		public Rows getUserRows(){
			TemplateTable tab=this.doSelectChild("user", "user2privilegegroup",this.getParentobjid(),usercol,usersqldatatype,this.UserFilter,userAccessFilter);
			String [] propUserlist={"status","usertype"};
			String [] codeUserlist={};
			String [] relationUserlist={"company:user2company:list:"};
			Rows rows=tu.getXMLRows(tab, "user",codeUserlist,propUserlist,relationUserlist,usercolcaption,userdatadomain,(QueryImpl)this);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(usercolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(userdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(usertype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getModuleRows(){
			TemplateTable tab=this.doSelectChild("module", "module2privilegegroup",this.getParentobjid(),modulecol,modulesqldatatype,this.ModuleFilter,moduleAccessFilter);
			String [] propModulelist={"name"};
			String [] codeModulelist={};
			String [] relationModulelist={};
			Rows rows=tu.getXMLRows(tab, "module",codeModulelist,propModulelist,relationModulelist,modulecolcaption,moduledatadomain,(QueryImpl)this);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(modulecolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(moduledisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(moduletype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getObjectprivilegeRows(){
			TemplateTable tab=this.doSelectChild("objectprivilege", "objectprivilege2privilegegroup",this.getParentobjid(),objectprivilegecol,objectprivilegesqldatatype,this.ObjectprivilegeFilter,objectprivilegeAccessFilter);
			String [] propObjectprivilegelist={"value","type","isrecursive"};
			String [] codeObjectprivilegelist={};
			String [] relationObjectprivilegelist={"moduleobject:objectprivilege2moduleobject:list:"};
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



		public Rows getPrivilegegroupSummaryRows(){
			TemplateTable tab=this.doSelect(summarycol,summarysqldatatype,this.PrivilegegroupFilter,false,privilegegroupAccessFilter);
			ArrayList<String> chartcols=tu.getChartSelectColumns("Privilegegroup");
			ArrayList<String> moneycols=tu.getSummaryMoneyColsIndex(summarydatadomain);
			Rows rows=tu.getXMLSummaryRows(tab,summarycolcaption);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("charts",chartcols);
			userdata.add(data1);
			for(String chartcol:chartcols){
				ArrayList<String> datas= tu.getChartPropertyJSON("Privilegegroup", tab, chartcol);
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

		public Items getPrivilegegroupForm(){
			TemplateTable tab=this.doSelect(maincol,mainsqldatatype,this.PrivilegegroupFilter,true,privilegegroupAccessFilter);
			Items items=tu.getXMLForm(tab, "Privilegegroup",codePrivilegegrouplist,propPrivilegegrouplist,relationPrivilegegrouplist,maincolcaption,maindatadomain,mainformfields,(QueryImpl)this);
			return items;
		}

		public Rows getPrivilegegroupRows(){
			TemplateTable tab=this.doSelect(maincol,mainsqldatatype,null,false,privilegegroupAccessFilter);
			Rows rows=tu.getXMLRows(tab, "Privilegegroup",codePrivilegegrouplist,propPrivilegegrouplist,relationPrivilegegrouplist,maincolcaption,maindatadomain,(QueryImpl)this);
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

		public Rows getPrivilegegroupRowModified(){
			Rows rows=tu.getXMLRows(maindata, "Privilegegroup",codePrivilegegrouplist,propPrivilegegrouplist,relationPrivilegegrouplist,maincolcaption,maindatadomain,(QueryImpl)this);
			return rows;
		}

		public Rows getPrivilegegroupRowDeleted(){
			Rows rows;
			if(this.doDelete(childtabs)){
				rows=tu.getDeletedRows(this.getParentobjid());
			}else{
				rows=tu.getDeletedRows("-1");
			}
			return rows;
		}

		public boolean postPrivilegegroupContainer() throws DaoException{
			if(!tu.isEmptyValue(this.getMainxml())){
				return(this.doInsert());
			}else{
				throw new DaoException("ERROR: Post unsuccessful! Probably your XML is missing parent entity or having error!", this.getClass().getName());
			}
		}

		public Rows getPrivilegegroupByFilter(){
			String newfilter=" groupuser='"+this.getGroupuser()+"'";
			if(!tu.isEmptyValue(this.getFilters()) &&this.getFilters().toLowerCase().indexOf("privilegegroup2")<0){
				newfilter+=" and privilegegroup2"+this.getFilters();
				this.setParentobjid(this.getFilters().split("=")[1].replaceAll("'",""));
			}else{
				newfilter+=" and "+this.getFilters();
			}
			String sql= "select * from table_Privilegegroup where "+ newfilter;
			TemplateTable tab=tu.getResultSet(sql);
			Rows rows=tu.getXMLFilterRows(tab, "Privilegegroup",codePrivilegegrouplist,propPrivilegegrouplist,relationPrivilegegrouplist,maincol,maincolcaption,maindatadomain,(QueryImpl)this);
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

		 public JSONObject getPrivilegegroupRecordCount() throws JSONException{
			String newfilter=" groupuser='"+this.getGroupuser()+"'";
			if(!tu.isEmptyValue(this.getFilters()) &&this.getFilters().toLowerCase().indexOf("privilegegroup2")<0){
				newfilter+=" and privilegegroup2"+this.getFilters();
			}else{
				newfilter+=" and "+this.getFilters();
			}
			String sql= "select * from table_Privilegegroup where "+ newfilter;
			TemplateTable tab=tu.getResultSet(sql);
			JSONObject data=new JSONObject();
			data.put("table",this.getObject());
			data.put("rowcount",tab.getRowCount());
			return data;
			}
	}
