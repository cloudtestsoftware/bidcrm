
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

	public class ConsoleDao extends ConsoleImpl {
		Map<String, Cookie> cookies; 
		Map<String,String> userdata;
		protected String []deletetabs={"console,consolelog"};
		protected String []childtabs={"console,consolelog"};
		protected String []childtabnames={"Console Facts,ConsoleLog"};
		
		protected String [] maintype={"table"};
		protected String [] maincol={"objid","console2assignto","name","title","description","note","status","keyobjid","mqid","isread","isarchived","entrydate","assignedby"};
		protected String [] maincolcaption={"Id","AssignTo","Object","Record Title","Description","Add Note","Status","Key Objid","MessageQueue","Is Read","Is Archived","Entry Date","Assigned By"};
		protected String [] mainsqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.VARCHAR};
		protected String [] maindatadomain={"Raw_t","Raw_t","Name_t","Name_t","String200_t","String200_t","Status_t","Id_t","Id_t","Boolean_t","Boolean_t","Date_t","Email_t"};
		protected String [] maincolsearch={"#text_filter,#select_filter,#text_filter,#text_filter,#text_filter,#text_filter,#select_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter"};
		
		protected String [] maincoldisable={"yes,no,yes,yes,yes,yes,yes,yes,yes,yes,yes,yes,yes"};
		private String [] mainformfields={"input","input","input","input","input","input","combo","input","input","input","input","calendar","input"};
		
		protected String [] summarycol={"name","elapseday"};
		protected String [] summarycolcaption={"Name","Elapse Days"};
		protected String [] summarysqldatatype={DataType.VARCHAR,DataType.NUMBER};
		protected String [] summarydatadomain={"Name_t","Float_t"};
		
		protected String [] reportcol={"objid","description","note","status","elapseday","entrydate","assignedby","name","title"};
		protected String [] reportcolcaption={"Id","Description","Add Note","Status","Elapse Days","Entry Date","Assigned By","Object","Record Title"};
		protected String [] reportsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.NUMBER,DataType.DATE,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] reportdatadomain={"Id_t","String200_t","String200_t","Status_t","Float_t","Date_t","Email_t","Name_t","Name_t"};
		
		protected String [] searchcol={"objid","description","note","status","keyobjid","mqid","isread","isarchived","elapseday","entrydate","assignedby","name","title"};
		protected String [] searchcolcaption={"Id","Description","Add Note","Status","Key Objid","MessageQueue","Is Read","Is Archived","Elapse Days","Entry Date","Assigned By","Object","Record Title"};
		protected String [] searchcoltype={"integer","text","text","select","text","text","integer","integer","float","date","text","text","text"};
		protected String [] searchdatadomain={"Id_t","String200_t","String200_t","Status_t","Id_t","Id_t","Boolean_t","Boolean_t","Float_t","Date_t","Email_t","Name_t","Name_t"};

	protected String [] propConsolelist={"status"};
		protected String [] codeConsolelist={};
		protected String [] relationConsolelist={"assignto:console2assignto:list:"};
		
		protected String [] consolelogtype={"table"};
		protected String [] consolelogcol={"objid","name","logdate"};
		protected String [] consolelogcolcaption={"Id","Note","Log Date"};
		protected String [] consolelogsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.DATE};
		protected String [] consolelogdatadomain={"Raw_t","String500_t","Date_t"};
		protected String [] consolelogdisable={"yes,no,no"};
		protected String [] consolelogcolsearch={"#text_filter,#text_filter,#text_filter"};

		public ConsoleDao(UriInfo uriInfo, HttpHeaders header) throws AuthenticationException{
			this.setObject("Console");
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
				if(entity.toLowerCase().contains("<console>")){
					tmp=entity.replace("<?", "");
					this.setMainxml("<?xml"+tmp);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Main XML="+this.getMainxml());
					}
				}else if(entity.toLowerCase().contains("<consolelog>")){
					this.setConsolelogxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getConsolelogxml());
					}
				}
			}
		}

		public Rows getConsolelogRows(){
			TemplateTable tab=this.doSelectChild("consolelog", "consolelog2console",this.getParentobjid(),consolelogcol,consolelogsqldatatype,this.ConsolelogFilter,consolelogAccessFilter);
			String [] propConsoleloglist={};
			String [] codeConsoleloglist={};
			String [] relationConsoleloglist={};
			Rows rows=tu.getXMLRows(tab, "consolelog",codeConsoleloglist,propConsoleloglist,relationConsoleloglist,consolelogcolcaption,consolelogdatadomain,(QueryImpl)this);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(consolelogcolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(consolelogdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(consolelogtype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getConsoleSummaryRows(){
			TemplateTable tab=this.doSelect(summarycol,summarysqldatatype,this.ConsoleFilter,false,consoleAccessFilter);
			ArrayList<String> chartcols=tu.getChartSelectColumns("Console");
			ArrayList<String> moneycols=tu.getSummaryMoneyColsIndex(summarydatadomain);
			Rows rows=tu.getXMLSummaryRows(tab,summarycolcaption);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("charts",chartcols);
			userdata.add(data1);
			for(String chartcol:chartcols){
				ArrayList<String> datas= tu.getChartPropertyJSON("Console", tab, chartcol);
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

		public Items getConsoleForm(){
			TemplateTable tab=this.doSelect(maincol,mainsqldatatype,this.ConsoleFilter,true,consoleAccessFilter);
			Items items=tu.getXMLForm(tab, "Console",codeConsolelist,propConsolelist,relationConsolelist,maincolcaption,maindatadomain,mainformfields,(QueryImpl)this);
			return items;
		}

		public Rows getConsoleRows(){
			TemplateTable tab=this.doSelect(maincol,mainsqldatatype,null,false,consoleAccessFilter);
			Rows rows=tu.getXMLRows(tab, "Console",codeConsolelist,propConsolelist,relationConsolelist,maincolcaption,maindatadomain,(QueryImpl)this);
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

		public Rows getConsoleRowModified(){
			Rows rows=tu.getXMLRows(maindata, "Console",codeConsolelist,propConsolelist,relationConsolelist,maincolcaption,maindatadomain,(QueryImpl)this);
			return rows;
		}

		public Rows getConsoleRowDeleted(){
			Rows rows;
			if(this.doDelete(childtabs)){
				rows=tu.getDeletedRows(this.getParentobjid());
			}else{
				rows=tu.getDeletedRows("-1");
			}
			return rows;
		}

		public boolean postConsoleContainer() throws DaoException{
			if(!tu.isEmptyValue(this.getMainxml())){
				return(this.doInsert());
			}else{
				throw new DaoException("ERROR: Post unsuccessful! Probably your XML is missing parent entity or having error!", this.getClass().getName());
			}
		}

		public Rows getConsoleByFilter(){
			String newfilter=" groupuser='"+this.getGroupuser()+"'";
			if(!tu.isEmptyValue(this.getFilters()) &&this.getFilters().toLowerCase().indexOf("console2")<0){
				newfilter+=" and console2"+this.getFilters();
				this.setParentobjid(this.getFilters().split("=")[1].replaceAll("'",""));
			}else{
				newfilter+=" and "+this.getFilters();
			}
			String sql= "select * from table_Console where "+ newfilter;
			TemplateTable tab=tu.getResultSet(sql);
			Rows rows=tu.getXMLFilterRows(tab, "Console",codeConsolelist,propConsolelist,relationConsolelist,maincol,maincolcaption,maindatadomain,(QueryImpl)this);
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

		 public JSONObject getConsoleRecordCount() throws JSONException{
			String newfilter=" groupuser='"+this.getGroupuser()+"'";
			if(!tu.isEmptyValue(this.getFilters()) &&this.getFilters().toLowerCase().indexOf("console2")<0){
				newfilter+=" and console2"+this.getFilters();
			}else{
				newfilter+=" and "+this.getFilters();
			}
			String sql= "select * from table_Console where "+ newfilter;
			TemplateTable tab=tu.getResultSet(sql);
			JSONObject data=new JSONObject();
			data.put("table",this.getObject());
			data.put("rowcount",tab.getRowCount());
			return data;
			}
	}
