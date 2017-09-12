
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

	public class CompanyDao extends CompanyImpl {
		Map<String, Cookie> cookies; 
		Map<String,String> userdata;
		protected String []deletetabs={"company,currency,territory,user"};
		protected String []childtabs={"currency,territory,user"};
		protected String []childtabnames={"Currency,Territory,User"};
		
		protected String [] maintype={"table"};
		protected String [] maincol={"objid","name","description","adminemail","adminname","phone"};
		protected String [] maincolcaption={"Id","Company Name","Description","Admin Email","Admin User Name","Phone No"};
		protected String [] mainsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] maindatadomain={"Raw_t","Name_t","Description_t","Email_t","Name_t","Phone_t"};
		protected String [] maincolsearch={"#text_filter,#text_filter,,,,"};
		
		protected String [] maincoldisable={"yes,no,no,no,no,no"};
		private String [] mainformfields={"input","input","input","input","input","input"};
		
		protected String [] summarycol={"name"};
		protected String [] summarycolcaption={"Name"};
		protected String [] summarysqldatatype={DataType.VARCHAR};
		protected String [] summarydatadomain={"Name_t"};
		
		protected String [] reportcol={"objid","name"};
		protected String [] reportcolcaption={"Id","Company Name"};
		protected String [] reportsqldatatype={DataType.VARCHAR,DataType.VARCHAR};
		protected String [] reportdatadomain={"Id_t","Name_t"};
		
		protected String [] searchcol={"objid","name"};
		protected String [] searchcolcaption={"Id","Company Name"};
		protected String [] searchcoltype={"integer","text"};
		protected String [] searchdatadomain={"Id_t","Name_t"};

	protected String [] propCompanylist={};
		protected String [] codeCompanylist={};
		protected String [] relationCompanylist={};
		
		protected String [] currencytype={"table"};
		protected String [] currencycol={"objid","name","country","symbol","conversion","type"};
		protected String [] currencycolcaption={"Id","Title","Country","Symbol","Conversion Rate","Type"};
		protected String [] currencysqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.NUMBER,DataType.VARCHAR};
		protected String [] currencydatadomain={"Raw_t","Name_t","String100_t","String10_t","Float_t","Type_t"};
		protected String [] currencydisable={"yes,no,no,no,no,no"};
		protected String [] currencycolsearch={"#text_filter,#text_filter,#text_filter,,,#text_filter"};
		
		protected String [] territorytype={"table"};
		protected String [] territorycol={"objid","name","country","location"};
		protected String [] territorycolcaption={"Id","Name","Country","Location"};
		protected String [] territorysqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] territorydatadomain={"Raw_t","Name_t","Name_t","String300_t"};
		protected String [] territorydisable={"yes,no,no,no"};
		protected String [] territorycolsearch={"#text_filter,#text_filter,#text_filter,#text_filter"};
		
		protected String [] usertype={"table"};
		protected String [] usercol={"objid","user2privilegegroup","name","lastname","loginname","status","usertype","email"};
		protected String [] usercolcaption={"Id","PrivilegeGroup","Name","Last Name","Login Name","Status","User Type","Email"};
		protected String [] usersqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] userdatadomain={"Raw_t","Raw_t","Name_t","Name_t","Name_t","Status_t","Type_t","Email_t"};
		protected String [] userdisable={"yes,no,no,no,no,no,no,no"};
		protected String [] usercolsearch={"#text_filter,#select_filter,#text_filter,,,,,"};

		public CompanyDao(UriInfo uriInfo, HttpHeaders header) throws AuthenticationException{
			this.setObject("Company");
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
				if(entity.toLowerCase().contains("<company>")){
					tmp=entity.replace("<?", "");
					this.setMainxml("<?xml"+tmp);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Main XML="+this.getMainxml());
					}
				}else if(entity.toLowerCase().contains("<currency>")){
					this.setCurrencyxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getCurrencyxml());
					}
				}else if(entity.toLowerCase().contains("<territory>")){
					this.setTerritoryxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getTerritoryxml());
					}
				}else if(entity.toLowerCase().contains("<user>")){
					this.setUserxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getUserxml());
					}
				}
			}
		}

		public Rows getCurrencyRows(){
			TemplateTable tab=this.doSelectChild("currency", "currency2company",this.getParentobjid(),currencycol,currencysqldatatype,this.CurrencyFilter,currencyAccessFilter);
			String [] propCurrencylist={"type"};
			String [] codeCurrencylist={};
			String [] relationCurrencylist={};
			Rows rows=tu.getXMLRows(tab, "currency",codeCurrencylist,propCurrencylist,relationCurrencylist,currencycolcaption,currencydatadomain,(QueryImpl)this);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(currencycolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(currencydisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(currencytype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getTerritoryRows(){
			TemplateTable tab=this.doSelectChild("territory", "territory2company",this.getParentobjid(),territorycol,territorysqldatatype,this.TerritoryFilter,territoryAccessFilter);
			String [] propTerritorylist={};
			String [] codeTerritorylist={};
			String [] relationTerritorylist={};
			Rows rows=tu.getXMLRows(tab, "territory",codeTerritorylist,propTerritorylist,relationTerritorylist,territorycolcaption,territorydatadomain,(QueryImpl)this);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(territorycolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(territorydisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(territorytype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getUserRows(){
			TemplateTable tab=this.doSelectChild("user", "user2company",this.getParentobjid(),usercol,usersqldatatype,this.UserFilter,userAccessFilter);
			String [] propUserlist={"status","usertype"};
			String [] codeUserlist={};
			String [] relationUserlist={"privilegegroup:user2privilegegroup:list:"};
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



		public Rows getCompanySummaryRows(){
			TemplateTable tab=this.doSelect(summarycol,summarysqldatatype,this.CompanyFilter,false,companyAccessFilter);
			ArrayList<String> chartcols=tu.getChartSelectColumns("Company");
			ArrayList<String> moneycols=tu.getSummaryMoneyColsIndex(summarydatadomain);
			Rows rows=tu.getXMLSummaryRows(tab,summarycolcaption);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("charts",chartcols);
			userdata.add(data1);
			for(String chartcol:chartcols){
				ArrayList<String> datas= tu.getChartPropertyJSON("Company", tab, chartcol);
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

		public Items getCompanyForm(){
			TemplateTable tab=this.doSelect(maincol,mainsqldatatype,this.CompanyFilter,true,companyAccessFilter);
			Items items=tu.getXMLForm(tab, "Company",codeCompanylist,propCompanylist,relationCompanylist,maincolcaption,maindatadomain,mainformfields,(QueryImpl)this);
			return items;
		}

		public Rows getCompanyRows(){
			TemplateTable tab=this.doSelect(maincol,mainsqldatatype,null,false,companyAccessFilter);
			Rows rows=tu.getXMLRows(tab, "Company",codeCompanylist,propCompanylist,relationCompanylist,maincolcaption,maindatadomain,(QueryImpl)this);
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

		public Rows getCompanyRowModified(){
			Rows rows=tu.getXMLRows(maindata, "Company",codeCompanylist,propCompanylist,relationCompanylist,maincolcaption,maindatadomain,(QueryImpl)this);
			return rows;
		}

		public Rows getCompanyRowDeleted(){
			Rows rows;
			if(this.doDelete(childtabs)){
				rows=tu.getDeletedRows(this.getParentobjid());
			}else{
				rows=tu.getDeletedRows("-1");
			}
			return rows;
		}

		public boolean postCompanyContainer() throws DaoException{
			if(!tu.isEmptyValue(this.getMainxml())){
				return(this.doInsert());
			}else{
				throw new DaoException("ERROR: Post unsuccessful! Probably your XML is missing parent entity or having error!", this.getClass().getName());
			}
		}

		public Rows getCompanyByFilter(){
			String newfilter=" groupuser='"+this.getGroupuser()+"'";
			if(!tu.isEmptyValue(this.getFilters()) &&this.getFilters().toLowerCase().indexOf("company2")<0){
				newfilter+=" and company2"+this.getFilters();
				this.setParentobjid(this.getFilters().split("=")[1].replaceAll("'",""));
			}else{
				newfilter+=" and "+this.getFilters();
			}
			String sql= "select * from table_Company where "+ newfilter;
			TemplateTable tab=tu.getResultSet(sql);
			Rows rows=tu.getXMLFilterRows(tab, "Company",codeCompanylist,propCompanylist,relationCompanylist,maincol,maincolcaption,maindatadomain,(QueryImpl)this);
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

		 public JSONObject getCompanyRecordCount() throws JSONException{
			String newfilter=" groupuser='"+this.getGroupuser()+"'";
			if(!tu.isEmptyValue(this.getFilters()) &&this.getFilters().toLowerCase().indexOf("company2")<0){
				newfilter+=" and company2"+this.getFilters();
			}else{
				newfilter+=" and "+this.getFilters();
			}
			String sql= "select * from table_Company where "+ newfilter;
			TemplateTable tab=tu.getResultSet(sql);
			JSONObject data=new JSONObject();
			data.put("table",this.getObject());
			data.put("rowcount",tab.getRowCount());
			return data;
			}
	}
