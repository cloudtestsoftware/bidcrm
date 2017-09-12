
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

	public class EmailsettingDao extends EmailsettingImpl {
		Map<String, Cookie> cookies; 
		Map<String,String> userdata;
		protected String []deletetabs={"emailsetting,emailattribute,reviewnote,reviewer,automation"};
		protected String []childtabs={"emailsetting,emailattribute,reviewnote,reviewer,automation"};
		protected String []childtabnames={"Emailsetting Facts,Email Attributes,ReviewNote,Reviewer,Automation"};
		
		protected String [] maintype={"table"};
		protected String [] maincol={"objid","emailsetting2contactlist","emailsetting2mastertemplate","emailsetting2campaign","name","emailsubject","stagecode","contenttype","channelscode","startdate","starttimecode","enddate","usemaster","uploadurl"};
		protected String [] maincolcaption={"Id","ContactList","MasterTemplate","Campaign","Short Name","Email Subject","Email Stage","Content Type","Channels","Start Date","Start Time","End Date","Use Master","Uploaded File"};
		protected String [] mainsqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.INTEGER,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.VARCHAR,DataType.DATE,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] maindatadomain={"Raw_t","Raw_t","Raw_t","Raw_t","Name_t","String200_t","Code_t","Code_t","Code_t","Date_t","Code_t","Date_t","Boolean_t","String500_t"};
		protected String [] maincolsearch={"#text_filter,#select_filter,#select_filter,#select_filter,#text_filter,#text_filter,#text_filter,#text_filter,,,,,,#text_filter"};
		
		protected String [] maincoldisable={"yes,no,no,no,no,no,no,no,no,no,no,no,no,yes"};
		private String [] mainformfields={"input","input","input","input","input","input","input","input","input","calendar","input","calendar","input","input"};
		
		protected String [] summarycol={"name","masterurl","lockedby","lockeddate"};
		protected String [] summarycolcaption={"Name","Master Template","Locked By","Locked Date"};
		protected String [] summarysqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE};
		protected String [] summarydatadomain={"Name_t","String500_t","Name_t","Date_t"};
		
		protected String [] reportcol={"objid","name","emailsubject","stagecode","contenttype","starttimecode","enddate","usemaster","masterurl","channelscode","lockedby","startdate","lockeddate","uploadurl"};
		protected String [] reportcolcaption={"Id","Short Name","Email Subject","Email Stage","Content Type","Start Time","End Date","Use Master","Master Template","Channels","Locked By","Start Date","Locked Date","Uploaded File"};
		protected String [] reportsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.DATE,DataType.VARCHAR};
		protected String [] reportdatadomain={"Id_t","Name_t","String200_t","Code_t","Code_t","Code_t","Date_t","Boolean_t","String500_t","Code_t","Name_t","Date_t","Date_t","String500_t"};
		
		protected String [] searchcol={"objid","name","emailsubject","stagecode","contenttype","masterurl","lockedby","lockeddate","uploadurl"};
		protected String [] searchcolcaption={"Id","Short Name","Email Subject","Email Stage","Content Type","Master Template","Locked By","Locked Date","Uploaded File"};
		protected String [] searchcoltype={"integer","text","text","integer","text","text","text","date","text"};
		protected String [] searchdatadomain={"Id_t","Name_t","String200_t","Code_t","Code_t","String500_t","Name_t","Date_t","String500_t"};

	protected String [] propEmailsettinglist={};
		protected String [] codeEmailsettinglist={"stagecode","contenttype","channelscode","starttimecode"};
		protected String [] relationEmailsettinglist={"contactlist:emailsetting2contactlist:list:","mastertemplate:emailsetting2mastertemplate:list:select m.channelscode||^ - ^||m.name as name, m.objid from table_mastertemplate m where m.groupuser=@groupuser and m.genuser=@genuser","campaign:emailsetting2campaign:list:"};
		
		protected String [] emailattributetype={"table"};
		protected String [] emailattributecol={"objid","name","value","attributetype","linktext"};
		protected String [] emailattributecolcaption={"Id","Attribute Name","Attribute Value","Attribute Type","Hover/Link Text"};
		protected String [] emailattributesqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] emailattributedatadomain={"Raw_t","Name_t","String200_t","Type_t","String200_t"};
		protected String [] emailattributedisable={"yes,no,no,no,no"};
		protected String [] emailattributecolsearch={"#text_filter,#text_filter,#text_filter,#select_filter,#text_filter"};
		
		protected String [] reviewnotetype={"table"};
		protected String [] reviewnotecol={"objid","name","note","createdate"};
		protected String [] reviewnotecolcaption={"Id","Reviewer Name","Note","Creation Date"};
		protected String [] reviewnotesqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE};
		protected String [] reviewnotedatadomain={"Raw_t","Name_t","String4000_t","Date_t"};
		protected String [] reviewnotedisable={"yes,no,no,no"};
		protected String [] reviewnotecolsearch={"#text_filter,#text_filter,#text_filter,#text_filter"};
		
		protected String [] reviewertype={"table"};
		protected String [] reviewercol={"objid","reviewer2employee","asigneddate","isapproved","email"};
		protected String [] reviewercolcaption={"Id","Employee","Asigned Date","Is Approved","Approver Email"};
		protected String [] reviewersqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.DATE,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] reviewerdatadomain={"Raw_t","Raw_t","Date_t","Boolean_t","Email_t"};
		protected String [] reviewerdisable={"yes,no,no,yes,yes"};
		protected String [] reviewercolsearch={"#text_filter,#select_filter,#text_filter,#text_filter,#text_filter"};
		
		protected String [] automationtype={"table"};
		protected String [] automationcol={"objid","automation2campaign","automation2nextchannel","name","actafterwaiting","actifopened","actifclicked","actifsubscribed","actiffilledup","actifdownloaded","actifordered","greaterthan","lessthan","sourcestagecode","sourcechannelscode","targetstagecode","targetchannelscode"};
		protected String [] automationcolcaption={"Id","Campaign","NextChannel","Name","Waiting Time In Hr","Act If Opened","Act If Clicked","Act If Subscribed","Act If Filled Up Form","Act If Downloaded","Act If Sample Ordered","AND Opened / Clicked Count >=","OR Opened / Clicked Count <=","Source Email Stage","Source Channels","Target Email Stage","Target Channels"};
		protected String [] automationsqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.INTEGER,DataType.VARCHAR,DataType.NUMBER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.INTEGER,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] automationdatadomain={"Raw_t","Raw_t","Raw_t","Name_t","Float_t","Boolean_t","Boolean_t","Boolean_t","Boolean_t","Boolean_t","Boolean_t","Int_t","Int_t","Code_t","Code_t","Code_t","Code_t"};
		protected String [] automationdisable={"yes,no,no,no,no,no,no,no,no,no,no,no,no,no,no,no,no"};
		protected String [] automationcolsearch={"#text_filter,#select_filter,#select_filter,#text_filter,#numeric_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,,#text_filter,"};

		public EmailsettingDao(UriInfo uriInfo, HttpHeaders header) throws AuthenticationException{
			this.setObject("Emailsetting");
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
				if(entity.toLowerCase().contains("<emailsetting>")){
					tmp=entity.replace("<?", "");
					this.setMainxml("<?xml"+tmp);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Main XML="+this.getMainxml());
					}
				}else if(entity.toLowerCase().contains("<emailattribute>")){
					this.setEmailattributexml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getEmailattributexml());
					}
				}else if(entity.toLowerCase().contains("<reviewnote>")){
					this.setReviewnotexml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getReviewnotexml());
					}
				}else if(entity.toLowerCase().contains("<reviewer>")){
					this.setReviewerxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getReviewerxml());
					}
				}else if(entity.toLowerCase().contains("<automation>")){
					this.setAutomationxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getAutomationxml());
					}
				}
			}
		}

		public Rows getEmailattributeRows(){
			TemplateTable tab=this.doSelectChild("emailattribute", "emailattribute2emailsetting",this.getParentobjid(),emailattributecol,emailattributesqldatatype,this.EmailattributeFilter,emailattributeAccessFilter);
			String [] propEmailattributelist={"attributetype"};
			String [] codeEmailattributelist={};
			String [] relationEmailattributelist={};
			Rows rows=tu.getXMLRows(tab, "emailattribute",codeEmailattributelist,propEmailattributelist,relationEmailattributelist,emailattributecolcaption,emailattributedatadomain,(QueryImpl)this);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(emailattributecolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(emailattributedisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(emailattributetype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getReviewnoteRows(){
			TemplateTable tab=this.doSelectChild("reviewnote", "reviewnote2emailsetting",this.getParentobjid(),reviewnotecol,reviewnotesqldatatype,this.ReviewnoteFilter,reviewnoteAccessFilter);
			String [] propReviewnotelist={};
			String [] codeReviewnotelist={};
			String [] relationReviewnotelist={};
			Rows rows=tu.getXMLRows(tab, "reviewnote",codeReviewnotelist,propReviewnotelist,relationReviewnotelist,reviewnotecolcaption,reviewnotedatadomain,(QueryImpl)this);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(reviewnotecolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(reviewnotedisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(reviewnotetype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getReviewerRows(){
			TemplateTable tab=this.doSelectChild("reviewer", "reviewer2emailsetting",this.getParentobjid(),reviewercol,reviewersqldatatype,this.ReviewerFilter,reviewerAccessFilter);
			String [] propReviewerlist={};
			String [] codeReviewerlist={};
			String [] relationReviewerlist={"employee:reviewer2employee:list:"};
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



		public Rows getAutomationRows(){
			TemplateTable tab=this.doSelectChild("automation", "automation2emailsetting",this.getParentobjid(),automationcol,automationsqldatatype,this.AutomationFilter,automationAccessFilter);
			String [] propAutomationlist={};
			String [] codeAutomationlist={"sourcestagecode","sourcechannelscode","targetstagecode","targetchannelscode"};
			String [] relationAutomationlist={"campaign:automation2campaign:list:","nextchannel:automation2nextchannel:list:select e.name, e.objid from table_nextchannel e where e.nextchannel2campaign=@parentobjid or e.nextchannel2campaign= (select  e1.emailsetting2campaign from table_emailsetting e1 where e1.objid=@parentobjid)"};
			Rows rows=tu.getXMLRows(tab, "automation",codeAutomationlist,propAutomationlist,relationAutomationlist,automationcolcaption,automationdatadomain,(QueryImpl)this);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(automationcolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(automationdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(automationtype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getEmailsettingSummaryRows(){
			TemplateTable tab=this.doSelect(summarycol,summarysqldatatype,this.EmailsettingFilter,false,emailsettingAccessFilter);
			ArrayList<String> chartcols=tu.getChartSelectColumns("Emailsetting");
			ArrayList<String> moneycols=tu.getSummaryMoneyColsIndex(summarydatadomain);
			Rows rows=tu.getXMLSummaryRows(tab,summarycolcaption);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("charts",chartcols);
			userdata.add(data1);
			for(String chartcol:chartcols){
				ArrayList<String> datas= tu.getChartPropertyJSON("Emailsetting", tab, chartcol);
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

		public Items getEmailsettingForm(){
			TemplateTable tab=this.doSelect(maincol,mainsqldatatype,this.EmailsettingFilter,true,emailsettingAccessFilter);
			Items items=tu.getXMLForm(tab, "Emailsetting",codeEmailsettinglist,propEmailsettinglist,relationEmailsettinglist,maincolcaption,maindatadomain,mainformfields,(QueryImpl)this);
			return items;
		}

		public Rows getEmailsettingRows(){
			TemplateTable tab=this.doSelect(maincol,mainsqldatatype,null,false,emailsettingAccessFilter);
			Rows rows=tu.getXMLRows(tab, "Emailsetting",codeEmailsettinglist,propEmailsettinglist,relationEmailsettinglist,maincolcaption,maindatadomain,(QueryImpl)this);
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

		public Rows getEmailsettingRowModified(){
			Rows rows=tu.getXMLRows(maindata, "Emailsetting",codeEmailsettinglist,propEmailsettinglist,relationEmailsettinglist,maincolcaption,maindatadomain,(QueryImpl)this);
			return rows;
		}

		public Rows getEmailsettingRowDeleted(){
			Rows rows;
			if(this.doDelete(childtabs)){
				rows=tu.getDeletedRows(this.getParentobjid());
			}else{
				rows=tu.getDeletedRows("-1");
			}
			return rows;
		}

		public boolean postEmailsettingContainer() throws DaoException{
			if(!tu.isEmptyValue(this.getMainxml())){
				return(this.doInsert());
			}else{
				throw new DaoException("ERROR: Post unsuccessful! Probably your XML is missing parent entity or having error!", this.getClass().getName());
			}
		}

		public Rows getEmailsettingByFilter(){
			String newfilter=" groupuser='"+this.getGroupuser()+"'";
			if(!tu.isEmptyValue(this.getFilters()) &&this.getFilters().toLowerCase().indexOf("emailsetting2")<0){
				newfilter+=" and emailsetting2"+this.getFilters();
				this.setParentobjid(this.getFilters().split("=")[1].replaceAll("'",""));
			}else{
				newfilter+=" and "+this.getFilters();
			}
			String sql= "select * from table_Emailsetting where "+ newfilter;
			TemplateTable tab=tu.getResultSet(sql);
			Rows rows=tu.getXMLFilterRows(tab, "Emailsetting",codeEmailsettinglist,propEmailsettinglist,relationEmailsettinglist,maincol,maincolcaption,maindatadomain,(QueryImpl)this);
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

		 public JSONObject getEmailsettingRecordCount() throws JSONException{
			String newfilter=" groupuser='"+this.getGroupuser()+"'";
			if(!tu.isEmptyValue(this.getFilters()) &&this.getFilters().toLowerCase().indexOf("emailsetting2")<0){
				newfilter+=" and emailsetting2"+this.getFilters();
			}else{
				newfilter+=" and "+this.getFilters();
			}
			String sql= "select * from table_Emailsetting where "+ newfilter;
			TemplateTable tab=tu.getResultSet(sql);
			JSONObject data=new JSONObject();
			data.put("table",this.getObject());
			data.put("rowcount",tab.getRowCount());
			return data;
			}
	}
