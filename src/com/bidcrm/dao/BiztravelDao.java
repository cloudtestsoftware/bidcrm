
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

	public class BiztravelDao extends BiztravelImpl {
		Map<String, Cookie> cookies; 
		Map<String,String> userdata;
		protected String []deletetabs={"biztravel,travelbooking,travelexpense"};
		protected String []childtabs={"biztravel,travelbooking,travelexpense"};
		protected String []childtabnames={"Biztravel Facts,TravelBooking,TravelExpense"};
		
		protected String [] maintype={"table"};
		protected String [] maincol={"objid","biztravel2approver","biztravel2campaign","name","purpose","firstname","lastname","location","needbooking","isapproved","traveldate","starttimecode","returndate","travelleremail","travellermobile","approvernote","approvedby","approveddate"};
		protected String [] maincolcaption={"Id","Approver","Campaign","Trip Name","Purpose","First Name","Last Name","Location","Need Booking","Is Approved","Travel Date","Start Time","Return Date","Traveller Email","Traveller Mobile","Approver Note","Approved By","Approved Date"};
		protected String [] mainsqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.VARCHAR,DataType.DATE,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE};
		protected String [] maindatadomain={"Raw_t","Raw_t","Raw_t","Name_t","String500_t","Name_t","Name_t","String200_t","Boolean_t","Boolean_t","Date_t","Code_t","Date_t","Email_t","Phone_t","String300_t","Name_t","Date_t"};
		protected String [] maincolsearch={"#text_filter,#select_filter,#select_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter"};
		
		protected String [] maincoldisable={"yes,no,no,no,no,no,no,no,no,no,no,no,no,no,no,no,yes,yes"};
		private String [] mainformfields={"input","input","input","input","input","input","input","input","input","input","calendar","input","calendar","input","input","input","input","calendar"};
		
		protected String [] summarycol={"name","approveremail","approvedby","approveddate"};
		protected String [] summarycolcaption={"Name","Approver Email","Approved By","Approved Date"};
		protected String [] summarysqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE};
		protected String [] summarydatadomain={"Name_t","Email_t","Name_t","Date_t"};
		
		protected String [] reportcol={"objid","purpose","firstname","lastname","location","needbooking","isapproved","traveldate","starttimecode","returndate","travelleremail","travellermobile","approvernote","name","approvedby","approveddate","approveremail","approvedby","approveddate"};
		protected String [] reportcolcaption={"Id","Purpose","First Name","Last Name","Location","Need Booking","Is Approved","Travel Date","Start Time","Return Date","Traveller Email","Traveller Mobile","Approver Note","Trip Name","Approved By","Approved Date","Approver Email","Approved By","Approved Date"};
		protected String [] reportsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.VARCHAR,DataType.DATE,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE};
		protected String [] reportdatadomain={"Id_t","String500_t","Name_t","Name_t","String200_t","Boolean_t","Boolean_t","Date_t","Code_t","Date_t","Email_t","Phone_t","String300_t","Name_t","Name_t","Date_t","Email_t","Name_t","Date_t"};
		
		protected String [] searchcol={"objid","purpose","firstname","lastname","location","needbooking","isapproved","traveldate","starttimecode","returndate","travelleremail","travellermobile","approvernote","name","approvedby","approveddate","approveremail","approvedby","approveddate"};
		protected String [] searchcolcaption={"Id","Purpose","First Name","Last Name","Location","Need Booking","Is Approved","Travel Date","Start Time","Return Date","Traveller Email","Traveller Mobile","Approver Note","Trip Name","Approved By","Approved Date","Approver Email","Approved By","Approved Date"};
		protected String [] searchcoltype={"integer","text","text","text","text","text","text","date","text","date","text","text","text","text","text","date","text","text","date"};
		protected String [] searchdatadomain={"Id_t","String500_t","Name_t","Name_t","String200_t","Boolean_t","Boolean_t","Date_t","Code_t","Date_t","Email_t","Phone_t","String300_t","Name_t","Name_t","Date_t","Email_t","Name_t","Date_t"};

	protected String [] propBiztravellist={"isapproved"};
		protected String [] codeBiztravellist={"starttimecode"};
		protected String [] relationBiztravellist={"approver:biztravel2approver:list:","campaign:biztravel2campaign:list:"};
		
		protected String [] travelbookingtype={"table"};
		protected String [] travelbookingcol={"objid","name","tolocation","bookingcode","startdate","enddate","reservationno","isconfirmed","note"};
		protected String [] travelbookingcolcaption={"Id","From Location","To Location","Booking Type","StartDate","End Date","Reservation No","Is Confirmed","Note"};
		protected String [] travelbookingsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.DATE,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] travelbookingdatadomain={"Raw_t","Name_t","Name_t","Code_t","Date_t","Date_t","String200_t","Boolean_t","String500_t"};
		protected String [] travelbookingdisable={"yes,no,no,no,no,no,no,no,no"};
		protected String [] travelbookingcolsearch={"#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter"};
		
		protected String [] travelexpensetype={"table"};
		protected String [] travelexpensecol={"objid","name","description","bookingcode","espensedate","amount","hasreceipt","isapproved","note","url"};
		protected String [] travelexpensecolcaption={"Id","Title","Description","Receipt Type","Expense Date","Amount","Has Receipt","Is Approved","Note","Attach Receipt"};
		protected String [] travelexpensesqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.NUMBER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] travelexpensedatadomain={"Raw_t","Name_t","String500_t","Code_t","Date_t","Money_t","Boolean_t","Boolean_t","String500_t","String200_t"};
		protected String [] travelexpensedisable={"yes,no,no,no,no,no,no,yes,no,no"};
		protected String [] travelexpensecolsearch={"#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#numeric_filter,#text_filter,#text_filter,#text_filter,#text_filter"};

		public BiztravelDao(UriInfo uriInfo, HttpHeaders header) throws AuthenticationException{
			this.setObject("Biztravel");
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
				if(entity.toLowerCase().contains("<biztravel>")){
					tmp=entity.replace("<?", "");
					this.setMainxml("<?xml"+tmp);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Main XML="+this.getMainxml());
					}
				}else if(entity.toLowerCase().contains("<travelbooking>")){
					this.setTravelbookingxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getTravelbookingxml());
					}
				}else if(entity.toLowerCase().contains("<travelexpense>")){
					this.setTravelexpensexml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getTravelexpensexml());
					}
				}
			}
		}

		public Rows getTravelbookingRows(){
			TemplateTable tab=this.doSelectChild("travelbooking", "travelbooking2biztravel",this.getParentobjid(),travelbookingcol,travelbookingsqldatatype,this.TravelbookingFilter,travelbookingAccessFilter);
			String [] propTravelbookinglist={"note"};
			String [] codeTravelbookinglist={"bookingcode"};
			String [] relationTravelbookinglist={};
			Rows rows=tu.getXMLRows(tab, "travelbooking",codeTravelbookinglist,propTravelbookinglist,relationTravelbookinglist,travelbookingcolcaption,travelbookingdatadomain,(QueryImpl)this);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(travelbookingcolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(travelbookingdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(travelbookingtype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getTravelexpenseRows(){
			TemplateTable tab=this.doSelectChild("travelexpense", "travelexpense2biztravel",this.getParentobjid(),travelexpensecol,travelexpensesqldatatype,this.TravelexpenseFilter,travelexpenseAccessFilter);
			String [] propTravelexpenselist={};
			String [] codeTravelexpenselist={"bookingcode"};
			String [] relationTravelexpenselist={};
			Rows rows=tu.getXMLRows(tab, "travelexpense",codeTravelexpenselist,propTravelexpenselist,relationTravelexpenselist,travelexpensecolcaption,travelexpensedatadomain,(QueryImpl)this);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(travelexpensecolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(travelexpensedisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(travelexpensetype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getBiztravelSummaryRows(){
			TemplateTable tab=this.doSelect(summarycol,summarysqldatatype,this.BiztravelFilter,false,biztravelAccessFilter);
			ArrayList<String> chartcols=tu.getChartSelectColumns("Biztravel");
			ArrayList<String> moneycols=tu.getSummaryMoneyColsIndex(summarydatadomain);
			Rows rows=tu.getXMLSummaryRows(tab,summarycolcaption);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("charts",chartcols);
			userdata.add(data1);
			for(String chartcol:chartcols){
				ArrayList<String> datas= tu.getChartPropertyJSON("Biztravel", tab, chartcol);
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

		public Items getBiztravelForm(){
			TemplateTable tab=this.doSelect(maincol,mainsqldatatype,this.BiztravelFilter,true,biztravelAccessFilter);
			Items items=tu.getXMLForm(tab, "Biztravel",codeBiztravellist,propBiztravellist,relationBiztravellist,maincolcaption,maindatadomain,mainformfields,(QueryImpl)this);
			return items;
		}

		public Rows getBiztravelRows(){
			TemplateTable tab=this.doSelect(maincol,mainsqldatatype,null,false,biztravelAccessFilter);
			Rows rows=tu.getXMLRows(tab, "Biztravel",codeBiztravellist,propBiztravellist,relationBiztravellist,maincolcaption,maindatadomain,(QueryImpl)this);
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

		public Rows getBiztravelRowModified(){
			Rows rows=tu.getXMLRows(maindata, "Biztravel",codeBiztravellist,propBiztravellist,relationBiztravellist,maincolcaption,maindatadomain,(QueryImpl)this);
			return rows;
		}

		public Rows getBiztravelRowDeleted(){
			Rows rows;
			if(this.doDelete(childtabs)){
				rows=tu.getDeletedRows(this.getParentobjid());
			}else{
				rows=tu.getDeletedRows("-1");
			}
			return rows;
		}

		public boolean postBiztravelContainer() throws DaoException{
			if(!tu.isEmptyValue(this.getMainxml())){
				return(this.doInsert());
			}else{
				throw new DaoException("ERROR: Post unsuccessful! Probably your XML is missing parent entity or having error!", this.getClass().getName());
			}
		}

		public Rows getBiztravelByFilter(){
			String newfilter=" groupuser='"+this.getGroupuser()+"'";
			if(!tu.isEmptyValue(this.getFilters()) &&this.getFilters().toLowerCase().indexOf("biztravel2")<0){
				newfilter+=" and biztravel2"+this.getFilters();
				this.setParentobjid(this.getFilters().split("=")[1].replaceAll("'",""));
			}else{
				newfilter+=" and "+this.getFilters();
			}
			String sql= "select * from table_Biztravel where "+ newfilter;
			TemplateTable tab=tu.getResultSet(sql);
			Rows rows=tu.getXMLFilterRows(tab, "Biztravel",codeBiztravellist,propBiztravellist,relationBiztravellist,maincol,maincolcaption,maindatadomain,(QueryImpl)this);
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

		 public JSONObject getBiztravelRecordCount() throws JSONException{
			String newfilter=" groupuser='"+this.getGroupuser()+"'";
			if(!tu.isEmptyValue(this.getFilters()) &&this.getFilters().toLowerCase().indexOf("biztravel2")<0){
				newfilter+=" and biztravel2"+this.getFilters();
			}else{
				newfilter+=" and "+this.getFilters();
			}
			String sql= "select * from table_Biztravel where "+ newfilter;
			TemplateTable tab=tu.getResultSet(sql);
			JSONObject data=new JSONObject();
			data.put("table",this.getObject());
			data.put("rowcount",tab.getRowCount());
			return data;
			}
	}
