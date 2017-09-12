
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

	public class CmpmemberDao extends CmpmemberImpl {
		Map<String, Cookie> cookies; 
		Map<String,String> userdata;
		protected String []deletetabs={"cmpmember,"};
		protected String []childtabs={"cmpmember,"};
		protected String []childtabnames={"Cmpmember Facts,"};
		
		protected String [] maintype={"table"};
		protected String [] maincol={"objid","cmpmember2campaign","cmpmember2salesrep","cmpmember2territory","name","isapprover","isapproved","assignifopened","assignifclicked","assignifordered","notifyifopened","notifyifclicked","notifyifordered","greaterthan","lessthan","note","aprovedby","aproveddate"};
		protected String [] maincolcaption={"Id","Campaign","SalesRep","Territory","Role","Is Approver","Is Approved","Assign If Opened","Assign If Clicked","Assign If Ordered","Notify If Opened","Notify If Clicked","Notify If Ordered","Opened/Clicked >=","Opened/Clicked <=","Note","Approved By","Approved Date"};
		protected String [] mainsqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.INTEGER,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.INTEGER,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE};
		protected String [] maindatadomain={"Raw_t","Raw_t","Raw_t","Raw_t","Type_t","Boolean_t","Boolean_t","Boolean_t","Boolean_t","Boolean_t","Boolean_t","Boolean_t","Boolean_t","Int_t","Int_t","String300_t","Name_t","Date_t"};
		protected String [] maincolsearch={"#text_filter,#select_filter,#select_filter,#select_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter"};
		
		protected String [] maincoldisable={"yes,no,no,no,no,no,yes,no,no,no,no,no,no,no,no,no,yes,yes"};
		private String [] mainformfields={"input","input","input","input","input","input","input","input","input","input","input","input","input","input","input","input","input","calendar"};
		
		protected String [] summarycol={"name","email","phone","phone2"};
		protected String [] summarycolcaption={"Name","Email","Mobile","Office No"};
		protected String [] summarysqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] summarydatadomain={"Name_t","Name_t","Phone_t","Phone_t"};
		
		protected String [] reportcol={"objid","name","isapprover","isapproved","assignifopened","assignifclicked","assignifordered","notifyifopened","notifyifclicked","notifyifordered","greaterthan","lessthan","note","email","phone","phone2","aprovedby","aproveddate"};
		protected String [] reportcolcaption={"Id","Role","Is Approver","Is Approved","Assign If Opened","Assign If Clicked","Assign If Ordered","Notify If Opened","Notify If Clicked","Notify If Ordered","Opened/Clicked >=","Opened/Clicked <=","Note","Email","Mobile","Office No","Approved By","Approved Date"};
		protected String [] reportsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.INTEGER,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE};
		protected String [] reportdatadomain={"Id_t","Type_t","Boolean_t","Boolean_t","Boolean_t","Boolean_t","Boolean_t","Boolean_t","Boolean_t","Boolean_t","Int_t","Int_t","String300_t","Name_t","Phone_t","Phone_t","Name_t","Date_t"};
		
		protected String [] searchcol={"objid","name","isapprover","isapproved","assignifopened","assignifclicked","assignifordered","notifyifopened","notifyifclicked","notifyifordered","greaterthan","lessthan","note","email","phone","phone2","aprovedby","aproveddate"};
		protected String [] searchcolcaption={"Id","Role","Is Approver","Is Approved","Assign If Opened","Assign If Clicked","Assign If Ordered","Notify If Opened","Notify If Clicked","Notify If Ordered","Opened/Clicked >=","Opened/Clicked <=","Note","Email","Mobile","Office No","Approved By","Approved Date"};
		protected String [] searchcoltype={"integer","text","text","text","text","text","text","text","text","text","integer","integer","text","text","text","text","text","date"};
		protected String [] searchdatadomain={"Id_t","Type_t","Boolean_t","Boolean_t","Boolean_t","Boolean_t","Boolean_t","Boolean_t","Boolean_t","Boolean_t","Int_t","Int_t","String300_t","Name_t","Phone_t","Phone_t","Name_t","Date_t"};

	protected String [] propCmpmemberlist={"name"};
		protected String [] codeCmpmemberlist={};
		protected String [] relationCmpmemberlist={"campaign:cmpmember2campaign:list:","salesrep:cmpmember2salesrep:list:","territory:cmpmember2territory:list:"};

		public CmpmemberDao(UriInfo uriInfo, HttpHeaders header) throws AuthenticationException{
			this.setObject("Cmpmember");
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
				if(entity.toLowerCase().contains("<cmpmember>")){
					tmp=entity.replace("<?", "");
					this.setMainxml("<?xml"+tmp);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Main XML="+this.getMainxml());
					}
				}
			}
		}

		public Rows getCmpmemberSummaryRows(){
			TemplateTable tab=this.doSelect(summarycol,summarysqldatatype,this.CmpmemberFilter,false,cmpmemberAccessFilter);
			ArrayList<String> chartcols=tu.getChartSelectColumns("Cmpmember");
			ArrayList<String> moneycols=tu.getSummaryMoneyColsIndex(summarydatadomain);
			Rows rows=tu.getXMLSummaryRows(tab,summarycolcaption);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("charts",chartcols);
			userdata.add(data1);
			for(String chartcol:chartcols){
				ArrayList<String> datas= tu.getChartPropertyJSON("Cmpmember", tab, chartcol);
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

		public Items getCmpmemberForm(){
			TemplateTable tab=this.doSelect(maincol,mainsqldatatype,this.CmpmemberFilter,true,cmpmemberAccessFilter);
			Items items=tu.getXMLForm(tab, "Cmpmember",codeCmpmemberlist,propCmpmemberlist,relationCmpmemberlist,maincolcaption,maindatadomain,mainformfields,(QueryImpl)this);
			return items;
		}

		public Rows getCmpmemberRows(){
			TemplateTable tab=this.doSelect(maincol,mainsqldatatype,null,false,cmpmemberAccessFilter);
			Rows rows=tu.getXMLRows(tab, "Cmpmember",codeCmpmemberlist,propCmpmemberlist,relationCmpmemberlist,maincolcaption,maindatadomain,(QueryImpl)this);
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

		public Rows getCmpmemberRowModified(){
			Rows rows=tu.getXMLRows(maindata, "Cmpmember",codeCmpmemberlist,propCmpmemberlist,relationCmpmemberlist,maincolcaption,maindatadomain,(QueryImpl)this);
			return rows;
		}

		public Rows getCmpmemberRowDeleted(){
			Rows rows;
			if(this.doDelete(childtabs)){
				rows=tu.getDeletedRows(this.getParentobjid());
			}else{
				rows=tu.getDeletedRows("-1");
			}
			return rows;
		}

		public boolean postCmpmemberContainer() throws DaoException{
			if(!tu.isEmptyValue(this.getMainxml())){
				return(this.doInsert());
			}else{
				throw new DaoException("ERROR: Post unsuccessful! Probably your XML is missing parent entity or having error!", this.getClass().getName());
			}
		}

		public Rows getCmpmemberByFilter(){
			String newfilter=" groupuser='"+this.getGroupuser()+"'";
			if(!tu.isEmptyValue(this.getFilters()) &&this.getFilters().toLowerCase().indexOf("cmpmember2")<0){
				newfilter+=" and cmpmember2"+this.getFilters();
				this.setParentobjid(this.getFilters().split("=")[1].replaceAll("'",""));
			}else{
				newfilter+=" and "+this.getFilters();
			}
			String sql= "select * from table_Cmpmember where "+ newfilter;
			TemplateTable tab=tu.getResultSet(sql);
			Rows rows=tu.getXMLFilterRows(tab, "Cmpmember",codeCmpmemberlist,propCmpmemberlist,relationCmpmemberlist,maincol,maincolcaption,maindatadomain,(QueryImpl)this);
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

		 public JSONObject getCmpmemberRecordCount() throws JSONException{
			String newfilter=" groupuser='"+this.getGroupuser()+"'";
			if(!tu.isEmptyValue(this.getFilters()) &&this.getFilters().toLowerCase().indexOf("cmpmember2")<0){
				newfilter+=" and cmpmember2"+this.getFilters();
			}else{
				newfilter+=" and "+this.getFilters();
			}
			String sql= "select * from table_Cmpmember where "+ newfilter;
			TemplateTable tab=tu.getResultSet(sql);
			JSONObject data=new JSONObject();
			data.put("table",this.getObject());
			data.put("rowcount",tab.getRowCount());
			return data;
			}
	}
