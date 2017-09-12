
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

	public class TravelbookingDao extends TravelbookingImpl {
		Map<String, Cookie> cookies; 
		Map<String,String> userdata;
		protected String []deletetabs={"travelbooking,"};
		protected String []childtabs={""};
		protected String []childtabnames={""};
		
		protected String [] maintype={"table"};
		protected String [] maincol={"objid","travelbooking2biztravel","name","tolocation","bookingcode","startdate","enddate","reservationno","isconfirmed","note"};
		protected String [] maincolcaption={"Id","BizTravel","From Location","To Location","Booking Type","StartDate","End Date","Reservation No","Is Confirmed","Note"};
		protected String [] mainsqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.DATE,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] maindatadomain={"Raw_t","Raw_t","Name_t","Name_t","Code_t","Date_t","Date_t","String200_t","Boolean_t","String500_t"};
		protected String [] maincolsearch={"#text_filter,#select_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter"};
		
		protected String [] maincoldisable={"yes,no,no,no,no,no,no,no,no,no"};
		private String [] mainformfields={"input","input","input","input","input","calendar","calendar","input","input","input"};
		
		protected String [] summarycol={"name"};
		protected String [] summarycolcaption={"Name"};
		protected String [] summarysqldatatype={DataType.VARCHAR};
		protected String [] summarydatadomain={"Name_t"};
		
		protected String [] reportcol={"objid","bookingcode","startdate","tolocation","enddate","reservationno","isconfirmed","note","name"};
		protected String [] reportcolcaption={"Id","Booking Type","StartDate","To Location","End Date","Reservation No","Is Confirmed","Note","From Location"};
		protected String [] reportsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.VARCHAR,DataType.DATE,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] reportdatadomain={"Id_t","Code_t","Date_t","Name_t","Date_t","String200_t","Boolean_t","String500_t","Name_t"};
		
		protected String [] searchcol={"objid","bookingcode","startdate","tolocation","enddate","reservationno","isconfirmed","note","name"};
		protected String [] searchcolcaption={"Id","Booking Type","StartDate","To Location","End Date","Reservation No","Is Confirmed","Note","From Location"};
		protected String [] searchcoltype={"integer","text","date","text","date","text","text","text","text"};
		protected String [] searchdatadomain={"Id_t","Code_t","Date_t","Name_t","Date_t","String200_t","Boolean_t","String500_t","Name_t"};

	protected String [] propTravelbookinglist={"note"};
		protected String [] codeTravelbookinglist={"bookingcode"};
		protected String [] relationTravelbookinglist={"biztravel:travelbooking2biztravel:list:"};

		public TravelbookingDao(UriInfo uriInfo, HttpHeaders header) throws AuthenticationException{
			this.setObject("Travelbooking");
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
				if(entity.toLowerCase().contains("<travelbooking>")){
					tmp=entity.replace("<?", "");
					this.setMainxml("<?xml"+tmp);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Main XML="+this.getMainxml());
					}
				}
			}
		}

		public Rows getTravelbookingSummaryRows(){
			TemplateTable tab=this.doSelect(summarycol,summarysqldatatype,this.TravelbookingFilter,false,travelbookingAccessFilter);
			ArrayList<String> chartcols=tu.getChartSelectColumns("Travelbooking");
			ArrayList<String> moneycols=tu.getSummaryMoneyColsIndex(summarydatadomain);
			Rows rows=tu.getXMLSummaryRows(tab,summarycolcaption);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("charts",chartcols);
			userdata.add(data1);
			for(String chartcol:chartcols){
				ArrayList<String> datas= tu.getChartPropertyJSON("Travelbooking", tab, chartcol);
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

		public Items getTravelbookingForm(){
			TemplateTable tab=this.doSelect(maincol,mainsqldatatype,this.TravelbookingFilter,true,travelbookingAccessFilter);
			Items items=tu.getXMLForm(tab, "Travelbooking",codeTravelbookinglist,propTravelbookinglist,relationTravelbookinglist,maincolcaption,maindatadomain,mainformfields,(QueryImpl)this);
			return items;
		}

		public Rows getTravelbookingRows(){
			TemplateTable tab=this.doSelect(maincol,mainsqldatatype,null,false,travelbookingAccessFilter);
			Rows rows=tu.getXMLRows(tab, "Travelbooking",codeTravelbookinglist,propTravelbookinglist,relationTravelbookinglist,maincolcaption,maindatadomain,(QueryImpl)this);
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

		public Rows getTravelbookingRowModified(){
			Rows rows=tu.getXMLRows(maindata, "Travelbooking",codeTravelbookinglist,propTravelbookinglist,relationTravelbookinglist,maincolcaption,maindatadomain,(QueryImpl)this);
			return rows;
		}

		public Rows getTravelbookingRowDeleted(){
			Rows rows;
			if(this.doDelete(childtabs)){
				rows=tu.getDeletedRows(this.getParentobjid());
			}else{
				rows=tu.getDeletedRows("-1");
			}
			return rows;
		}

		public boolean postTravelbookingContainer() throws DaoException{
			if(!tu.isEmptyValue(this.getMainxml())){
				return(this.doInsert());
			}else{
				throw new DaoException("ERROR: Post unsuccessful! Probably your XML is missing parent entity or having error!", this.getClass().getName());
			}
		}

		public Rows getTravelbookingByFilter(){
			String newfilter=" groupuser='"+this.getGroupuser()+"'";
			if(!tu.isEmptyValue(this.getFilters()) &&this.getFilters().toLowerCase().indexOf("travelbooking2")<0){
				newfilter+=" and travelbooking2"+this.getFilters();
				this.setParentobjid(this.getFilters().split("=")[1].replaceAll("'",""));
			}else{
				newfilter+=" and "+this.getFilters();
			}
			String sql= "select * from table_Travelbooking where "+ newfilter;
			TemplateTable tab=tu.getResultSet(sql);
			Rows rows=tu.getXMLFilterRows(tab, "Travelbooking",codeTravelbookinglist,propTravelbookinglist,relationTravelbookinglist,maincol,maincolcaption,maindatadomain,(QueryImpl)this);
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

		 public JSONObject getTravelbookingRecordCount() throws JSONException{
			String newfilter=" groupuser='"+this.getGroupuser()+"'";
			if(!tu.isEmptyValue(this.getFilters()) &&this.getFilters().toLowerCase().indexOf("travelbooking2")<0){
				newfilter+=" and travelbooking2"+this.getFilters();
			}else{
				newfilter+=" and "+this.getFilters();
			}
			String sql= "select * from table_Travelbooking where "+ newfilter;
			TemplateTable tab=tu.getResultSet(sql);
			JSONObject data=new JSONObject();
			data.put("table",this.getObject());
			data.put("rowcount",tab.getRowCount());
			return data;
			}
	}
