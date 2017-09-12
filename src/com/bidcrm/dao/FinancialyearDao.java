
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

	public class FinancialyearDao extends FinancialyearImpl {
		Map<String, Cookie> cookies; 
		Map<String,String> userdata;
		protected String []deletetabs={"financialyear,"};
		protected String []childtabs={"campaigncalendar,eventcalendar,travelcalendar"};
		protected String []childtabnames={"CampaignCalendar,EventCalendar,TravelCalendar"};
		
		protected String [] maintype={"table"};
		protected String [] maincol={"objid","name","startdate","enddate"};
		protected String [] maincolcaption={"Id","Name","Start Date","End Date"};
		protected String [] mainsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.DATE};
		protected String [] maindatadomain={"Raw_t","Name_t","Date_t","Date_t"};
		protected String [] maincolsearch={"#text_filter,#text_filter,#text_filter,#text_filter"};
		
		protected String [] maincoldisable={"yes,no,no,no"};
		private String [] mainformfields={"input","input","calendar","calendar"};
		
		protected String [] summarycol={"name"};
		protected String [] summarycolcaption={"Name"};
		protected String [] summarysqldatatype={DataType.VARCHAR};
		protected String [] summarydatadomain={"Name_t"};
		
		protected String [] reportcol={"objid","name","startdate","enddate"};
		protected String [] reportcolcaption={"Id","Name","Start Date","End Date"};
		protected String [] reportsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.DATE};
		protected String [] reportdatadomain={"Id_t","Name_t","Date_t","Date_t"};
		
		protected String [] searchcol={"objid","name","startdate","enddate"};
		protected String [] searchcolcaption={"Id","Name","Start Date","End Date"};
		protected String [] searchcoltype={"integer","text","date","date"};
		protected String [] searchdatadomain={"Id_t","Name_t","Date_t","Date_t"};

	protected String [] propFinancialyearlist={};
		protected String [] codeFinancialyearlist={};
		protected String [] relationFinancialyearlist={};
		
		protected String [] campaigncalendartype={"view"};
		protected String [] campaigncalendarcol={"objid","name","year","startdate"};
		protected String [] campaigncalendarcolcaption={"Id","Campaign Name","Year","Start Date"};
		protected String [] campaigncalendarsqldatatype={DataType.VARCHAR,DataType.NUMBER,DataType.INTEGER,DataType.INTEGER};
		protected String [] campaigncalendardatadomain={"Raw_t","Double_t","Int_t","Int_t"};
		protected String [] campaigncalendardisable={"yes,no,no,no"};
		protected String [] campaigncalendarcolsearch={"#text_filter,,,"};
		
		protected String [] eventcalendartype={"view"};
		protected String [] eventcalendarcol={"objid","name","year","startdate","count"};
		protected String [] eventcalendarcolcaption={"Id","Campaign Name","Year","Campaign Start Date","Event Count"};
		protected String [] eventcalendarsqldatatype={DataType.VARCHAR,DataType.NUMBER,DataType.INTEGER,DataType.INTEGER,DataType.INTEGER};
		protected String [] eventcalendardatadomain={"Raw_t","Double_t","Int_t","Int_t","Int_t"};
		protected String [] eventcalendardisable={"yes,no,no,no,no"};
		protected String [] eventcalendarcolsearch={"#text_filter,,,,"};
		
		protected String [] travelcalendartype={"view"};
		protected String [] travelcalendarcol={"objid","name","year","startdate","count","amount"};
		protected String [] travelcalendarcolcaption={"Id","Campaign Name","Year","Campaign Start Date","Travel Count","Amount Spent"};
		protected String [] travelcalendarsqldatatype={DataType.VARCHAR,DataType.NUMBER,DataType.INTEGER,DataType.INTEGER,DataType.INTEGER,DataType.INTEGER};
		protected String [] travelcalendardatadomain={"Raw_t","Double_t","Int_t","Int_t","Int_t","Int_t"};
		protected String [] travelcalendardisable={"yes,no,no,no,no,no"};
		protected String [] travelcalendarcolsearch={"#text_filter,,,,,"};

		public FinancialyearDao(UriInfo uriInfo, HttpHeaders header) throws AuthenticationException{
			this.setObject("Financialyear");
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
				if(entity.toLowerCase().contains("<financialyear>")){
					tmp=entity.replace("<?", "");
					this.setMainxml("<?xml"+tmp);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Main XML="+this.getMainxml());
					}
				}else if(entity.toLowerCase().contains("<campaigncalendar>")){
					this.setCampaigncalendarxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getCampaigncalendarxml());
					}
				}else if(entity.toLowerCase().contains("<eventcalendar>")){
					this.setEventcalendarxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getEventcalendarxml());
					}
				}else if(entity.toLowerCase().contains("<travelcalendar>")){
					this.setTravelcalendarxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getTravelcalendarxml());
					}
				}
			}
		}

		public Rows getCampaigncalendarRows(){
			TemplateTable tab=this.doSelectChild("campaigncalendar", "campaigncalendar2financialyear",this.getParentobjid(),campaigncalendarcol,campaigncalendarsqldatatype,this.CampaigncalendarFilter,campaigncalendarAccessFilter);
			String [] propCampaigncalendarlist={};
			String [] codeCampaigncalendarlist={};
			String [] relationCampaigncalendarlist={};
			Rows rows=tu.getXMLRows(tab, "campaigncalendar",codeCampaigncalendarlist,propCampaigncalendarlist,relationCampaigncalendarlist,campaigncalendarcolcaption,campaigncalendardatadomain,(QueryImpl)this);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(campaigncalendarcolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(campaigncalendardisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(campaigncalendartype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getEventcalendarRows(){
			TemplateTable tab=this.doSelectChild("eventcalendar", "eventcalendar2financialyear",this.getParentobjid(),eventcalendarcol,eventcalendarsqldatatype,this.EventcalendarFilter,eventcalendarAccessFilter);
			String [] propEventcalendarlist={};
			String [] codeEventcalendarlist={};
			String [] relationEventcalendarlist={};
			Rows rows=tu.getXMLRows(tab, "eventcalendar",codeEventcalendarlist,propEventcalendarlist,relationEventcalendarlist,eventcalendarcolcaption,eventcalendardatadomain,(QueryImpl)this);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(eventcalendarcolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(eventcalendardisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(eventcalendartype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getTravelcalendarRows(){
			TemplateTable tab=this.doSelectChild("travelcalendar", "travelcalendar2financialyear",this.getParentobjid(),travelcalendarcol,travelcalendarsqldatatype,this.TravelcalendarFilter,travelcalendarAccessFilter);
			String [] propTravelcalendarlist={};
			String [] codeTravelcalendarlist={};
			String [] relationTravelcalendarlist={};
			Rows rows=tu.getXMLRows(tab, "travelcalendar",codeTravelcalendarlist,propTravelcalendarlist,relationTravelcalendarlist,travelcalendarcolcaption,travelcalendardatadomain,(QueryImpl)this);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(travelcalendarcolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(travelcalendardisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(travelcalendartype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getFinancialyearSummaryRows(){
			TemplateTable tab=this.doSelect(summarycol,summarysqldatatype,this.FinancialyearFilter,false,financialyearAccessFilter);
			ArrayList<String> chartcols=tu.getChartSelectColumns("Financialyear");
			ArrayList<String> moneycols=tu.getSummaryMoneyColsIndex(summarydatadomain);
			Rows rows=tu.getXMLSummaryRows(tab,summarycolcaption);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("charts",chartcols);
			userdata.add(data1);
			for(String chartcol:chartcols){
				ArrayList<String> datas= tu.getChartPropertyJSON("Financialyear", tab, chartcol);
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

		public Items getFinancialyearForm(){
			TemplateTable tab=this.doSelect(maincol,mainsqldatatype,this.FinancialyearFilter,true,financialyearAccessFilter);
			Items items=tu.getXMLForm(tab, "Financialyear",codeFinancialyearlist,propFinancialyearlist,relationFinancialyearlist,maincolcaption,maindatadomain,mainformfields,(QueryImpl)this);
			return items;
		}

		public Rows getFinancialyearRows(){
			TemplateTable tab=this.doSelect(maincol,mainsqldatatype,null,false,financialyearAccessFilter);
			Rows rows=tu.getXMLRows(tab, "Financialyear",codeFinancialyearlist,propFinancialyearlist,relationFinancialyearlist,maincolcaption,maindatadomain,(QueryImpl)this);
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

		public Rows getFinancialyearRowModified(){
			Rows rows=tu.getXMLRows(maindata, "Financialyear",codeFinancialyearlist,propFinancialyearlist,relationFinancialyearlist,maincolcaption,maindatadomain,(QueryImpl)this);
			return rows;
		}

		public Rows getFinancialyearRowDeleted(){
			Rows rows;
			if(this.doDelete(childtabs)){
				rows=tu.getDeletedRows(this.getParentobjid());
			}else{
				rows=tu.getDeletedRows("-1");
			}
			return rows;
		}

		public boolean postFinancialyearContainer() throws DaoException{
			if(!tu.isEmptyValue(this.getMainxml())){
				return(this.doInsert());
			}else{
				throw new DaoException("ERROR: Post unsuccessful! Probably your XML is missing parent entity or having error!", this.getClass().getName());
			}
		}

		public Rows getFinancialyearByFilter(){
			String newfilter=" groupuser='"+this.getGroupuser()+"'";
			if(!tu.isEmptyValue(this.getFilters()) &&this.getFilters().toLowerCase().indexOf("financialyear2")<0){
				newfilter+=" and financialyear2"+this.getFilters();
				this.setParentobjid(this.getFilters().split("=")[1].replaceAll("'",""));
			}else{
				newfilter+=" and "+this.getFilters();
			}
			String sql= "select * from table_Financialyear where "+ newfilter;
			TemplateTable tab=tu.getResultSet(sql);
			Rows rows=tu.getXMLFilterRows(tab, "Financialyear",codeFinancialyearlist,propFinancialyearlist,relationFinancialyearlist,maincol,maincolcaption,maindatadomain,(QueryImpl)this);
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

		 public JSONObject getFinancialyearRecordCount() throws JSONException{
			String newfilter=" groupuser='"+this.getGroupuser()+"'";
			if(!tu.isEmptyValue(this.getFilters()) &&this.getFilters().toLowerCase().indexOf("financialyear2")<0){
				newfilter+=" and financialyear2"+this.getFilters();
			}else{
				newfilter+=" and "+this.getFilters();
			}
			String sql= "select * from table_Financialyear where "+ newfilter;
			TemplateTable tab=tu.getResultSet(sql);
			JSONObject data=new JSONObject();
			data.put("table",this.getObject());
			data.put("rowcount",tab.getRowCount());
			return data;
			}
	}
