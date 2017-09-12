
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

	public class ProfileDao extends ProfileImpl {
		Map<String, Cookie> cookies; 
		Map<String,String> userdata;
		protected String []deletetabs={"profile,profilesetting,subscription"};
		protected String []childtabs={"profilesetting,subscription"};
		protected String []childtabnames={"Setting,Subscription"};
		
		protected String [] maintype={"table"};
		protected String [] maincol={"objid","name","password","verifypassword","firstname","lastname","company","url","street","city","state","zipcode","countrycode","phone","phone2","fax","reasoncode","mediacode"};
		protected String [] maincolcaption={"Id","Email As Login Name","Password","Verify Password","First Name","Other/Last Name","Company/Owner Name","Document","Street","City","State(Like CA,..)","Zip Code","Country","Phone(xxx-xxx-xxxx)","Cell Phone","Fax","Type","How Do You Know Us"};
		protected String [] mainsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] maindatadomain={"Raw_t","Email_t","String20_t","String20_t","Name_t","Name_t","String100_t","String200_t","String200_t","String100_t","StateCode_t","ZipCode_t","Code_t","Phone_t","Phone_t","Phone_t","Code_t","Code_t"};
		protected String [] maincolsearch={"#text_filter,#text_filter,,,#text_filter,#text_filter,#text_filter,#text_filter,,,,,#text_filter,,,,,"};
		
		protected String [] maincoldisable={"yes,no,no,no,no,no,no,no,no,no,no,no,no,no,no,no,no,no"};
		private String [] mainformfields={"input","input","password","input","input","input","input","input","input","input","input","input","input","input","input","input","combo","combo"};
		
		protected String [] summarycol={"name"};
		protected String [] summarycolcaption={"Name"};
		protected String [] summarysqldatatype={DataType.VARCHAR};
		protected String [] summarydatadomain={"Name_t"};
		
		protected String [] reportcol={"objid","name","firstname","lastname","zipcode","countrycode","phone"};
		protected String [] reportcolcaption={"Id","Email As Login Name","First Name","Other/Last Name","Zip Code","Country","Phone(xxx-xxx-xxxx)"};
		protected String [] reportsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] reportdatadomain={"Id_t","Email_t","Name_t","Name_t","ZipCode_t","Code_t","Phone_t"};
		
		protected String [] searchcol={"objid","name","firstname","lastname","company","url","countrycode"};
		protected String [] searchcolcaption={"Id","Email As Login Name","First Name","Other/Last Name","Company/Owner Name","Document","Country"};
		protected String [] searchcoltype={"integer","email","text","text","text","text","text"};
		protected String [] searchdatadomain={"Id_t","Email_t","Name_t","Name_t","String100_t","String200_t","Code_t"};

	protected String [] propProfilelist={};
		protected String [] codeProfilelist={"countrycode","reasoncode","mediacode"};
		protected String [] relationProfilelist={};
		
		protected String [] profilesettingtype={"table"};
		protected String [] profilesettingcol={"objid","name","zipcode","distance","bizpolicy"};
		protected String [] profilesettingcolcaption={"Id","Login Name","Zip Code","Distance Within(Miles)","Bisuness Policy"};
		protected String [] profilesettingsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.INTEGER,DataType.VARCHAR};
		protected String [] profilesettingdatadomain={"Raw_t","Email_t","ZipCode_t","Int_t","String4000_t"};
		protected String [] profilesettingdisable={"yes,no,yes,no,no"};
		protected String [] profilesettingcolsearch={"#text_filter,#text_filter,#text_filter,#text_filter,#text_filter"};
		
		protected String [] subscriptiontype={"table"};
		protected String [] subscriptioncol={"objid","name","subscribecode","startdate","enddate","fullname","cardno","monthcode","yearcode","cardid","cardtypecode","status"};
		protected String [] subscriptioncolcaption={"Id","Login Name","Subscription Type","Start Date","End Date","Full Name(As in Credit Card)","Credit Card Number","Month of Expiry","Year of Expiry","3 Digit Verification No","Card Type","Status"};
		protected String [] subscriptionsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.DATE,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] subscriptiondatadomain={"Raw_t","Email_t","Code_t","Date_t","Date_t","String100_t","String16_t","Code_t","Code_t","String10_t","Code_t","Status_t"};
		protected String [] subscriptiondisable={"yes,no,no,yes,yes,no,no,no,no,no,no,yes"};
		protected String [] subscriptioncolsearch={"#text_filter,#text_filter,#select_filter,#text_filter,#text_filter,#text_filter,#text_filter,#select_filter,#select_filter,#text_filter,#select_filter,#select_filter"};

		public ProfileDao(UriInfo uriInfo, HttpHeaders header) throws AuthenticationException{
			this.setObject("Profile");
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
				if(entity.toLowerCase().contains("<profile>")){
					tmp=entity.replace("<?", "");
					this.setMainxml("<?xml"+tmp);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Main XML="+this.getMainxml());
					}
				}else if(entity.toLowerCase().contains("<profilesetting>")){
					this.setProfilesettingxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getProfilesettingxml());
					}
				}else if(entity.toLowerCase().contains("<subscription>")){
					this.setSubscriptionxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getSubscriptionxml());
					}
				}
			}
		}

		public Rows getProfilesettingRows(){
			TemplateTable tab=this.doSelectChild("profilesetting", "profilesetting2profile",this.getParentobjid(),profilesettingcol,profilesettingsqldatatype,this.ProfilesettingFilter,profilesettingAccessFilter);
			String [] propProfilesettinglist={};
			String [] codeProfilesettinglist={};
			String [] relationProfilesettinglist={};
			Rows rows=tu.getXMLRows(tab, "profilesetting",codeProfilesettinglist,propProfilesettinglist,relationProfilesettinglist,profilesettingcolcaption,profilesettingdatadomain,(QueryImpl)this);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(profilesettingcolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(profilesettingdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(profilesettingtype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getSubscriptionRows(){
			TemplateTable tab=this.doSelectChild("subscription", "subscription2profile",this.getParentobjid(),subscriptioncol,subscriptionsqldatatype,this.SubscriptionFilter,subscriptionAccessFilter);
			String [] propSubscriptionlist={"status"};
			String [] codeSubscriptionlist={"subscribecode","monthcode","yearcode","cardtypecode"};
			String [] relationSubscriptionlist={};
			Rows rows=tu.getXMLRows(tab, "subscription",codeSubscriptionlist,propSubscriptionlist,relationSubscriptionlist,subscriptioncolcaption,subscriptiondatadomain,(QueryImpl)this);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(subscriptioncolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(subscriptiondisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(subscriptiontype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getProfileSummaryRows(){
			TemplateTable tab=this.doSelect(summarycol,summarysqldatatype,this.ProfileFilter,false,profileAccessFilter);
			ArrayList<String> chartcols=tu.getChartSelectColumns("Profile");
			ArrayList<String> moneycols=tu.getSummaryMoneyColsIndex(summarydatadomain);
			Rows rows=tu.getXMLSummaryRows(tab,summarycolcaption);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("charts",chartcols);
			userdata.add(data1);
			for(String chartcol:chartcols){
				ArrayList<String> datas= tu.getChartPropertyJSON("Profile", tab, chartcol);
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

		public Items getProfileForm(){
			TemplateTable tab=this.doSelect(maincol,mainsqldatatype,this.ProfileFilter,true,profileAccessFilter);
			Items items=tu.getXMLForm(tab, "Profile",codeProfilelist,propProfilelist,relationProfilelist,maincolcaption,maindatadomain,mainformfields,(QueryImpl)this);
			return items;
		}

		public Rows getProfileRows(){
			TemplateTable tab=this.doSelect(maincol,mainsqldatatype,null,false,profileAccessFilter);
			Rows rows=tu.getXMLRows(tab, "Profile",codeProfilelist,propProfilelist,relationProfilelist,maincolcaption,maindatadomain,(QueryImpl)this);
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

		public Rows getProfileRowModified(){
			Rows rows=tu.getXMLRows(maindata, "Profile",codeProfilelist,propProfilelist,relationProfilelist,maincolcaption,maindatadomain,(QueryImpl)this);
			return rows;
		}

		public Rows getProfileRowDeleted(){
			Rows rows;
			if(this.doDelete(childtabs)){
				rows=tu.getDeletedRows(this.getParentobjid());
			}else{
				rows=tu.getDeletedRows("-1");
			}
			return rows;
		}

		public boolean postProfileContainer() throws DaoException{
			if(!tu.isEmptyValue(this.getMainxml())){
				return(this.doInsert());
			}else{
				throw new DaoException("ERROR: Post unsuccessful! Probably your XML is missing parent entity or having error!", this.getClass().getName());
			}
		}

		public Rows getProfileByFilter(){
			String newfilter=" groupuser='"+this.getGroupuser()+"'";
			if(!tu.isEmptyValue(this.getFilters()) &&this.getFilters().toLowerCase().indexOf("profile2")<0){
				newfilter+=" and profile2"+this.getFilters();
				this.setParentobjid(this.getFilters().split("=")[1].replaceAll("'",""));
			}else{
				newfilter+=" and "+this.getFilters();
			}
			String sql= "select * from table_Profile where "+ newfilter;
			TemplateTable tab=tu.getResultSet(sql);
			Rows rows=tu.getXMLFilterRows(tab, "Profile",codeProfilelist,propProfilelist,relationProfilelist,maincol,maincolcaption,maindatadomain,(QueryImpl)this);
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

		 public JSONObject getProfileRecordCount() throws JSONException{
			String newfilter=" groupuser='"+this.getGroupuser()+"'";
			if(!tu.isEmptyValue(this.getFilters()) &&this.getFilters().toLowerCase().indexOf("profile2")<0){
				newfilter+=" and profile2"+this.getFilters();
			}else{
				newfilter+=" and "+this.getFilters();
			}
			String sql= "select * from table_Profile where "+ newfilter;
			TemplateTable tab=tu.getResultSet(sql);
			JSONObject data=new JSONObject();
			data.put("table",this.getObject());
			data.put("rowcount",tab.getRowCount());
			return data;
			}
	}
