
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

	public class EmployeeDao extends EmployeeImpl {
		Map<String, Cookie> cookies; 
		Map<String,String> userdata;
		protected String []deletetabs={"employee,reviewer"};
		protected String []childtabs={"reviewer"};
		protected String []childtabnames={"Reviewer"};
		
		protected String [] maintype={"table"};
		protected String [] maincol={"objid","employee2reportto","employee2user","employee2site","name","lastname","street","city","state","zipcode","countrycode","deptcode","jobcode","empcode","evalcode","phone","phone2","ssn","email","birthdate","joindate","lastdate","status"};
		protected String [] maincolcaption={"Id","ReportTo","User","Site","First Name","Last Name","Street","City","State","Zip Code","Country","Department","Role","Employee Type","Evaluation Type","Main Phone","Other Phone","SSN/ID","Email","Birth Date","Joining Date","Last Date","Status"};
		protected String [] mainsqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.INTEGER,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.DATE,DataType.DATE,DataType.VARCHAR};
		protected String [] maindatadomain={"Raw_t","Raw_t","Raw_t","Raw_t","Name_t","Name_t","String200_t","String100_t","Name_t","ZipCode_t","Code_t","Code_t","Code_t","Code_t","Code_t","Phone_t","Phone_t","String10_t","Email_t","Date_t","Date_t","Date_t","Status_t"};
		protected String [] maincolsearch={"#text_filter,#select_filter,#select_filter,#select_filter,#text_filter,#text_filter,,,,,#text_filter,#select_filter,#select_filter,#select_filter,#select_filter,,,,,#text_filter,#text_filter,#text_filter,#text_filter"};
		
		protected String [] maincoldisable={"yes,no,no,no,no,no,no,no,no,no,no,no,no,no,no,no,no,no,no,no,no,no,no"};
		private String [] mainformfields={"input","input","input","input","input","input","input","input","input","input","input","combo","combo","combo","combo","input","input","input","input","calendar","calendar","calendar","input"};
		
		protected String [] summarycol={"name"};
		protected String [] summarycolcaption={"Name"};
		protected String [] summarysqldatatype={DataType.VARCHAR};
		protected String [] summarydatadomain={"Name_t"};
		
		protected String [] reportcol={"objid","lastname","street","city","state","zipcode","countrycode","deptcode","jobcode","empcode","evalcode","phone","birthdate","joindate","name","lastdate","status"};
		protected String [] reportcolcaption={"Id","Last Name","Street","City","State","Zip Code","Country","Department","Role","Employee Type","Evaluation Type","Main Phone","Birth Date","Joining Date","First Name","Last Date","Status"};
		protected String [] reportsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.DATE,DataType.VARCHAR,DataType.DATE,DataType.VARCHAR};
		protected String [] reportdatadomain={"Id_t","Name_t","String200_t","String100_t","Name_t","ZipCode_t","Code_t","Code_t","Code_t","Code_t","Code_t","Phone_t","Date_t","Date_t","Name_t","Date_t","Status_t"};
		
		protected String [] searchcol={"objid","lastname","countrycode","deptcode","jobcode","empcode","evalcode","birthdate","joindate","name","lastdate","status"};
		protected String [] searchcolcaption={"Id","Last Name","Country","Department","Role","Employee Type","Evaluation Type","Birth Date","Joining Date","First Name","Last Date","Status"};
		protected String [] searchcoltype={"integer","text","text","select","select","select","select","date","date","text","date","text"};
		protected String [] searchdatadomain={"Id_t","Name_t","Code_t","Code_t","Code_t","Code_t","Code_t","Date_t","Date_t","Name_t","Date_t","Status_t"};

	protected String [] propEmployeelist={"status"};
		protected String [] codeEmployeelist={"countrycode","deptcode","jobcode","empcode","evalcode"};
		protected String [] relationEmployeelist={"reportto:employee2reportto:list:","user:employee2user:list:","site:employee2site:list:"};
		
		protected String [] reviewertype={"table"};
		protected String [] reviewercol={"objid","asigneddate","isapproved","email"};
		protected String [] reviewercolcaption={"Id","Asigned Date","Is Approved","Approver Email"};
		protected String [] reviewersqldatatype={DataType.VARCHAR,DataType.DATE,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] reviewerdatadomain={"Raw_t","Date_t","Boolean_t","Email_t"};
		protected String [] reviewerdisable={"yes,no,yes,yes"};
		protected String [] reviewercolsearch={"#text_filter,#text_filter,#text_filter,#text_filter"};

		public EmployeeDao(UriInfo uriInfo, HttpHeaders header) throws AuthenticationException{
			this.setObject("Employee");
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
				if(entity.toLowerCase().contains("<employee>")){
					tmp=entity.replace("<?", "");
					this.setMainxml("<?xml"+tmp);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Main XML="+this.getMainxml());
					}
				}else if(entity.toLowerCase().contains("<reviewer>")){
					this.setReviewerxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getReviewerxml());
					}
				}
			}
		}

		public Rows getReviewerRows(){
			TemplateTable tab=this.doSelectChild("reviewer", "reviewer2employee",this.getParentobjid(),reviewercol,reviewersqldatatype,this.ReviewerFilter,reviewerAccessFilter);
			String [] propReviewerlist={};
			String [] codeReviewerlist={};
			String [] relationReviewerlist={};
			Rows rows=tu.getXMLRows(tab, "reviewer",codeReviewerlist,propReviewerlist,relationReviewerlist,reviewercolcaption,reviewerdatadomain,(QueryImpl)this);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(reviewercolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(reviewerdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(reviewertype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getEmployeeSummaryRows(){
			TemplateTable tab=this.doSelect(summarycol,summarysqldatatype,this.EmployeeFilter,false,employeeAccessFilter);
			ArrayList<String> chartcols=tu.getChartSelectColumns("Employee");
			ArrayList<String> moneycols=tu.getSummaryMoneyColsIndex(summarydatadomain);
			Rows rows=tu.getXMLSummaryRows(tab,summarycolcaption);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("charts",chartcols);
			userdata.add(data1);
			for(String chartcol:chartcols){
				ArrayList<String> datas= tu.getChartPropertyJSON("Employee", tab, chartcol);
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

		public Items getEmployeeForm(){
			TemplateTable tab=this.doSelect(maincol,mainsqldatatype,this.EmployeeFilter,true,employeeAccessFilter);
			Items items=tu.getXMLForm(tab, "Employee",codeEmployeelist,propEmployeelist,relationEmployeelist,maincolcaption,maindatadomain,mainformfields,(QueryImpl)this);
			return items;
		}

		public Rows getEmployeeRows(){
			TemplateTable tab=this.doSelect(maincol,mainsqldatatype,null,false,employeeAccessFilter);
			Rows rows=tu.getXMLRows(tab, "Employee",codeEmployeelist,propEmployeelist,relationEmployeelist,maincolcaption,maindatadomain,(QueryImpl)this);
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

		public Rows getEmployeeRowModified(){
			Rows rows=tu.getXMLRows(maindata, "Employee",codeEmployeelist,propEmployeelist,relationEmployeelist,maincolcaption,maindatadomain,(QueryImpl)this);
			return rows;
		}

		public Rows getEmployeeRowDeleted(){
			Rows rows;
			if(this.doDelete(childtabs)){
				rows=tu.getDeletedRows(this.getParentobjid());
			}else{
				rows=tu.getDeletedRows("-1");
			}
			return rows;
		}

		public boolean postEmployeeContainer() throws DaoException{
			if(!tu.isEmptyValue(this.getMainxml())){
				return(this.doInsert());
			}else{
				throw new DaoException("ERROR: Post unsuccessful! Probably your XML is missing parent entity or having error!", this.getClass().getName());
			}
		}

		public Rows getEmployeeByFilter(){
			String newfilter=" groupuser='"+this.getGroupuser()+"'";
			if(!tu.isEmptyValue(this.getFilters()) &&this.getFilters().toLowerCase().indexOf("employee2")<0){
				newfilter+=" and employee2"+this.getFilters();
				this.setParentobjid(this.getFilters().split("=")[1].replaceAll("'",""));
			}else{
				newfilter+=" and "+this.getFilters();
			}
			String sql= "select * from table_Employee where "+ newfilter;
			TemplateTable tab=tu.getResultSet(sql);
			Rows rows=tu.getXMLFilterRows(tab, "Employee",codeEmployeelist,propEmployeelist,relationEmployeelist,maincol,maincolcaption,maindatadomain,(QueryImpl)this);
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

		 public JSONObject getEmployeeRecordCount() throws JSONException{
			String newfilter=" groupuser='"+this.getGroupuser()+"'";
			if(!tu.isEmptyValue(this.getFilters()) &&this.getFilters().toLowerCase().indexOf("employee2")<0){
				newfilter+=" and employee2"+this.getFilters();
			}else{
				newfilter+=" and "+this.getFilters();
			}
			String sql= "select * from table_Employee where "+ newfilter;
			TemplateTable tab=tu.getResultSet(sql);
			JSONObject data=new JSONObject();
			data.put("table",this.getObject());
			data.put("rowcount",tab.getRowCount());
			return data;
			}
	}
