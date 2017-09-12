
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

	public class CampaignDao extends CampaignImpl {
		Map<String, Cookie> cookies; 
		Map<String,String> userdata;
		protected String []deletetabs={"campaign,cmpmember,contactlist,emailsetting,automation,samplereserve,emailresponse,sampleorder,samples,campaignnote,surveyquestion"};
		protected String []childtabs={"campaign,cmpmember,contactlist,emailsetting,automation,samplereserve,emailresponse,sampleorder,samples,campaignnote,surveyquestion"};
		protected String []childtabnames={"Campaign Facts,Member,ContactList,EmailSetting,Automation,SampleReserve,EmailResponse,SampleOrder,Samples,Note,Survey Question"};
		
		protected String [] maintype={"table"};
		protected String [] maincol={"objid","campaign2partlist","name","title","campaignid","campaigntype","owner","email","startdate","enddate","amountbudgeted","amountspent","bannermsg","offer","otheroffer","finalmessage","videourl","campaignurl","surveyurl","zone","allowpublicemail","agentid","reportinterval","lastreported"};
		protected String [] maincolcaption={"Id","PartList","Name","Banner Title","Campaign Id","Campaign Type","Campaign Owner","Owner Email","Start Date","End Date","Budget","Amount Spent","Banner","Main Offer","Alternate Offer","Final Message","Video URL","Campaign URL","Survey URL","Campaign Zone","Allow Public Email","Agent Id","Report Interval In Days","Last Reported"};
		protected String [] mainsqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.DATE,DataType.NUMBER,DataType.NUMBER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.INTEGER,DataType.DATE};
		protected String [] maindatadomain={"Raw_t","Raw_t","String100_t","String200_t","String50_t","Type_t","Name_t","Email_t","Date_t","Date_t","Money_t","Money_t","String500_t","String1000_t","String1000_t","String1000_t","String200_t","String200_t","String200_t","String100_t","Boolean_t","String20_t","Int_t","Date_t"};
		protected String [] maincolsearch={"#text_filter,#select_filter,#text_filter,#text_filter,#text_filter,,,,,,#numeric_filter,#numeric_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,,,,,"};
		
		protected String [] maincoldisable={"yes,no,no,no,no,no,no,no,no,no,no,no,no,no,no,no,no,no,no,no,no,no,no,yes"};
		private String [] mainformfields={"input","input","input","input","input","input","input","input","calendar","calendar","input","input","input","input","input","input","input","input","input","input","input","input","input","calendar"};
		
		protected String [] summarycol={"name","pctresponse","totalemail","totalresponse","totalopened","totalclicked","totalordered"};
		protected String [] summarycolcaption={"Name","Response (%)","Total Email","Total Response","Total Opened","Total Clicked","Total Ordered"};
		protected String [] summarysqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.INTEGER,DataType.INTEGER,DataType.INTEGER,DataType.INTEGER,DataType.INTEGER};
		protected String [] summarydatadomain={"Name_t","Int_t","Int_t","Int_t","Int_t","Int_t","Int_t"};
		
		protected String [] reportcol={"objid","campaignid","campaigntype","amountbudgeted","amountspent","name","title","zone","allowpublicemail"};
		protected String [] reportcolcaption={"Id","Campaign Id","Campaign Type","Budget","Amount Spent","Name","Banner Title","Campaign Zone","Allow Public Email"};
		protected String [] reportsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.NUMBER,DataType.NUMBER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] reportdatadomain={"Id_t","String50_t","Type_t","Money_t","Money_t","String100_t","String200_t","String100_t","Boolean_t"};
		
		protected String [] searchcol={"objid","campaignid","amountbudgeted","amountspent","bannermsg","name","title","offer","otheroffer","finalmessage","videourl","campaignurl","surveyurl"};
		protected String [] searchcolcaption={"Id","Campaign Id","Budget","Amount Spent","Banner","Name","Banner Title","Main Offer","Alternate Offer","Final Message","Video URL","Campaign URL","Survey URL"};
		protected String [] searchcoltype={"integer","text","currency","currency","text","text","text","text","text","text","text","text","text"};
		protected String [] searchdatadomain={"Id_t","String50_t","Money_t","Money_t","String500_t","String100_t","String200_t","String1000_t","String1000_t","String1000_t","String200_t","String200_t","String200_t"};

	protected String [] propCampaignlist={"campaigntype"};
		protected String [] codeCampaignlist={};
		protected String [] relationCampaignlist={"partlist:campaign2partlist:list:"};
		
		protected String [] cmpmembertype={"table"};
		protected String [] cmpmembercol={"objid","cmpmember2salesrep","cmpmember2territory","name","isapprover","isapproved","assignifopened","assignifclicked","assignifordered","notifyifopened","notifyifclicked","notifyifordered","greaterthan","lessthan","note","email","phone","phone2","aprovedby","aproveddate"};
		protected String [] cmpmembercolcaption={"Id","SalesRep","Territory","Role","Is Approver","Is Approved","Assign If Opened","Assign If Clicked","Assign If Ordered","Notify If Opened","Notify If Clicked","Notify If Ordered","Opened/Clicked >=","Opened/Clicked <=","Note","Email","Mobile","Office No","Approved By","Approved Date"};
		protected String [] cmpmembersqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.INTEGER,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE};
		protected String [] cmpmemberdatadomain={"Raw_t","Raw_t","Raw_t","Type_t","Boolean_t","Boolean_t","Boolean_t","Boolean_t","Boolean_t","Boolean_t","Boolean_t","Boolean_t","Int_t","Int_t","String300_t","Name_t","Phone_t","Phone_t","Name_t","Date_t"};
		protected String [] cmpmemberdisable={"yes,no,no,no,no,yes,no,no,no,no,no,no,no,no,no,no,no,no,yes,yes"};
		protected String [] cmpmembercolsearch={"#text_filter,#select_filter,#select_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter"};
		
		protected String [] contactlisttype={"table"};
		protected String [] contactlistcol={"objid","contactlist2salesrep","contactlist2emaillist","name","stagecode","firstround","secondround","thirdround","fourthround","fifthround","sixthround","seventhround","eighthround","ninthround","tenthround","reminder1","reminder2","reminder3"};
		protected String [] contactlistcolcaption={"Id","SalesRep","EmailList","Name","Last Stage","First Round","Second Round","Third Round","Fourth Round","Fifth Round","Sixth Round","Seventh Round","Eighth Round","Ninth Round","Tenth Round","Reminder 1","Reminder 2","Reminder 3"};
		protected String [] contactlistsqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] contactlistdatadomain={"Raw_t","Raw_t","Raw_t","Name_t","Code_t","Boolean_t","Boolean_t","Boolean_t","Boolean_t","Boolean_t","Boolean_t","Boolean_t","Boolean_t","Boolean_t","Boolean_t","Boolean_t","Boolean_t","Boolean_t"};
		protected String [] contactlistdisable={"yes,no,no,no,yes,yes,yes,yes,yes,yes,yes,yes,yes,yes,yes,yes,yes,yes"};
		protected String [] contactlistcolsearch={"#text_filter,#select_filter,#select_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter"};
		
		protected String [] emailsettingtype={"table"};
		protected String [] emailsettingcol={"objid","emailsetting2mastertemplate","emailsetting2contactlist","name","emailsubject","stagecode","contenttype","channelscode","startdate","starttimecode","enddate","usemaster","masterurl","lockedby","lockeddate","uploadurl"};
		protected String [] emailsettingcolcaption={"Id","MasterTemplate","ContactList","Short Name","Email Subject","Email Stage","Content Type","Channels","Start Date","Start Time","End Date","Use Master","Master Template","Locked By","Locked Date","Uploaded File"};
		protected String [] emailsettingsqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.VARCHAR,DataType.DATE,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.VARCHAR};
		protected String [] emailsettingdatadomain={"Raw_t","Raw_t","Raw_t","Name_t","String200_t","Code_t","Code_t","Code_t","Date_t","Code_t","Date_t","Boolean_t","String500_t","Name_t","Date_t","String500_t"};
		protected String [] emailsettingdisable={"yes,no,no,no,no,no,no,no,no,no,no,no,yes,yes,yes,yes"};
		protected String [] emailsettingcolsearch={"#text_filter,#select_filter,#select_filter,#text_filter,#text_filter,#text_filter,#text_filter,,,,,,#text_filter,#text_filter,#text_filter,#text_filter"};
		
		protected String [] automationtype={"table"};
		protected String [] automationcol={"objid","automation2emailsetting","automation2nextchannel","name","actafterwaiting","actifopened","actifclicked","actifsubscribed","actiffilledup","actifdownloaded","actifordered","greaterthan","lessthan","sourcestagecode","sourcechannelscode","targetstagecode","targetchannelscode"};
		protected String [] automationcolcaption={"Id","EmailSetting","NextChannel","Name","Waiting Time In Hr","Act If Opened","Act If Clicked","Act If Subscribed","Act If Filled Up Form","Act If Downloaded","Act If Sample Ordered","AND Opened / Clicked Count >=","OR Opened / Clicked Count <=","Source Email Stage","Source Channels","Target Email Stage","Target Channels"};
		protected String [] automationsqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.INTEGER,DataType.VARCHAR,DataType.NUMBER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.INTEGER,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] automationdatadomain={"Raw_t","Raw_t","Raw_t","Name_t","Float_t","Boolean_t","Boolean_t","Boolean_t","Boolean_t","Boolean_t","Boolean_t","Int_t","Int_t","Code_t","Code_t","Code_t","Code_t"};
		protected String [] automationdisable={"yes,no,no,no,no,no,no,no,no,no,no,no,no,no,no,no,no"};
		protected String [] automationcolsearch={"#text_filter,#select_filter,#select_filter,#text_filter,#numeric_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,,#text_filter,"};
		
		protected String [] samplereservetype={"table"};
		protected String [] samplereservecol={"objid","samplereserve2store","name","requestedby","requesteremail","requesterphone","requestdate","requireddate","approveddate","approvedby","storemanager","agentemail","agentphone","status"};
		protected String [] samplereservecolcaption={"Id","Store","Title","Requested By","RequesterEmail","Requester Phone","Request Date","Required Date","Approved Date","Approved By","Store Manager","Store Agent Email","Store Agent Phone","Status"};
		protected String [] samplereservesqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.DATE,DataType.DATE,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] samplereservedatadomain={"Raw_t","Raw_t","Name_t","Name_t","Email_t","Phone_t","Date_t","Date_t","Date_t","Name_t","Name_t","Email_t","Phone_t","Type_t"};
		protected String [] samplereservedisable={"yes,no,no,no,no,no,no,no,no,no,no,no,no,no"};
		protected String [] samplereservecolsearch={"#text_filter,#select_filter,#text_filter,#text_filter,#text_filter,#text_filter,,,,#text_filter,#text_filter,#text_filter,#text_filter,#select_filter"};
		
		protected String [] emailresponsetype={"table"};
		protected String [] emailresponsecol={"objid","name","lastname","email","phone","phone2","status","opened","clicked","issubscribed","isfilledup","isdownloaded","isordered","stagecode","responsedate","isassigned"};
		protected String [] emailresponsecolcaption={"Id","First Name","Last Name","Email","Mobile","Office No","Status","Opened","Clicked","Is Subscribed","Is Filled Up Form","Is Download","Is Sample Ordered","Email Stage","Response Date","Is Assigned"};
		protected String [] emailresponsesqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.INTEGER,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.VARCHAR};
		protected String [] emailresponsedatadomain={"Raw_t","Name_t","Name_t","Name_t","Phone_t","Phone_t","Status_t","Int_t","Int_t","Boolean_t","Boolean_t","Boolean_t","Boolean_t","Code_t","Date_t","Boolean_t"};
		protected String [] emailresponsedisable={"yes,no,no,no,no,no,no,no,no,yes,yes,yes,yes,no,no,yes"};
		protected String [] emailresponsecolsearch={"#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter"};
		
		protected String [] sampleordertype={"table"};
		protected String [] sampleordercol={"objid","name","lastname","billingaddress","billingcountry","billingstate","billingcity","billingzipcode","officephone","mobile","email","shippingaddress","shippingcountry","shippingstate","shippingcity","shippingzipcode","shippingfedex","ordertotal","quicknote","status"};
		protected String [] sampleordercolcaption={"Id","Name","Last Name","Billing Address","Billing Country","Billing State","Billing City","Billing Zip","Office Number","Mobile No","Email","Shipping Address","Shipping Country","Shipping State","Shipping City","Shipping Zip","Fedex Account(optional)","Order Total","Quick Note","Status"};
		protected String [] sampleordersqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.NUMBER,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] sampleorderdatadomain={"Raw_t","Name_t","Name_t","String300_t","String30_t","String30_t","String50_t","String10_t","Phone_t","Phone_t","Email_t","String300_t","String40_t","String30_t","String50_t","String10_t","String100_t","Money_t","String4000_t","Type_t"};
		protected String [] sampleorderdisable={"yes,no,no,no,no,no,no,no,no,no,no,no,no,no,no,no,no,yes,no,no"};
		protected String [] sampleordercolsearch={"#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,,,#select_filter"};
		
		protected String [] samplestype={"table"};
		protected String [] samplescol={"objid","samples2samplereserve","samples2parts","name","ispublic","qntrequest","qntunused","description","status","price","qntordered","qntonhand"};
		protected String [] samplescolcaption={"Id","SampleReserve","Parts","Item","Is Available To Public","Quantity Requested","Quantity UnUsed","Description","Status","Price","Quantity Ordered","Quantity On Hand"};
		protected String [] samplessqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.INTEGER,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.INTEGER,DataType.INTEGER};
		protected String [] samplesdatadomain={"Raw_t","Raw_t","Raw_t","String200_t","Boolean_t","Int_t","Int_t","String400_t","Status_t","String100_t","Int_t","Int_t"};
		protected String [] samplesdisable={"yes,no,no,no,no,no,yes,yes,yes,no,yes,yes"};
		protected String [] samplescolsearch={"#text_filter,#select_filter,#select_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#select_filter,#text_filter,#text_filter,#text_filter"};
		
		protected String [] campaignnotetype={"table"};
		protected String [] campaignnotecol={"objid","name","note"};
		protected String [] campaignnotecolcaption={"Id","Entered By","Note"};
		protected String [] campaignnotesqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] campaignnotedatadomain={"Raw_t","String100_t","String4000_t"};
		protected String [] campaignnotedisable={"yes,no,no"};
		protected String [] campaignnotecolsearch={"#text_filter,#text_filter,#text_filter"};
		
		protected String [] surveyquestiontype={"table"};
		protected String [] surveyquestioncol={"objid","name","question","majorityanswer","minorityanswer","rareanswer","solution"};
		protected String [] surveyquestioncolcaption={"Id","Title","Question","Mjority Answer","Minority Answer","Rare Answer","Solution"};
		protected String [] surveyquestionsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] surveyquestiondatadomain={"Raw_t","String100_t","String4000_t","String4000_t","String4000_t","String4000_t","String4000_t"};
		protected String [] surveyquestiondisable={"yes,no,no,no,no,no,no"};
		protected String [] surveyquestioncolsearch={"#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter"};

		public CampaignDao(UriInfo uriInfo, HttpHeaders header) throws AuthenticationException{
			this.setObject("Campaign");
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
				if(entity.toLowerCase().contains("<campaign>")){
					tmp=entity.replace("<?", "");
					this.setMainxml("<?xml"+tmp);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Main XML="+this.getMainxml());
					}
				}else if(entity.toLowerCase().contains("<cmpmember>")){
					this.setCmpmemberxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getCmpmemberxml());
					}
				}else if(entity.toLowerCase().contains("<contactlist>")){
					this.setContactlistxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getContactlistxml());
					}
				}else if(entity.toLowerCase().contains("<emailsetting>")){
					this.setEmailsettingxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getEmailsettingxml());
					}
				}else if(entity.toLowerCase().contains("<automation>")){
					this.setAutomationxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getAutomationxml());
					}
				}else if(entity.toLowerCase().contains("<samplereserve>")){
					this.setSamplereservexml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getSamplereservexml());
					}
				}else if(entity.toLowerCase().contains("<emailresponse>")){
					this.setEmailresponsexml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getEmailresponsexml());
					}
				}else if(entity.toLowerCase().contains("<sampleorder>")){
					this.setSampleorderxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getSampleorderxml());
					}
				}else if(entity.toLowerCase().contains("<samples>")){
					this.setSamplesxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getSamplesxml());
					}
				}else if(entity.toLowerCase().contains("<campaignnote>")){
					this.setCampaignnotexml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getCampaignnotexml());
					}
				}else if(entity.toLowerCase().contains("<surveyquestion>")){
					this.setSurveyquestionxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getSurveyquestionxml());
					}
				}
			}
		}

		public Rows getCmpmemberRows(){
			TemplateTable tab=this.doSelectChild("cmpmember", "cmpmember2campaign",this.getParentobjid(),cmpmembercol,cmpmembersqldatatype,this.CmpmemberFilter,cmpmemberAccessFilter);
			String [] propCmpmemberlist={"name"};
			String [] codeCmpmemberlist={};
			String [] relationCmpmemberlist={"salesrep:cmpmember2salesrep:list:","territory:cmpmember2territory:list:"};
			Rows rows=tu.getXMLRows(tab, "cmpmember",codeCmpmemberlist,propCmpmemberlist,relationCmpmemberlist,cmpmembercolcaption,cmpmemberdatadomain,(QueryImpl)this);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(cmpmembercolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(cmpmemberdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(cmpmembertype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getContactlistRows(){
			TemplateTable tab=this.doSelectChild("contactlist", "contactlist2campaign",this.getParentobjid(),contactlistcol,contactlistsqldatatype,this.ContactlistFilter,contactlistAccessFilter);
			String [] propContactlistlist={};
			String [] codeContactlistlist={"stagecode"};
			String [] relationContactlistlist={"salesrep:contactlist2salesrep:list:","emaillist:contactlist2emaillist:list:"};
			Rows rows=tu.getXMLRows(tab, "contactlist",codeContactlistlist,propContactlistlist,relationContactlistlist,contactlistcolcaption,contactlistdatadomain,(QueryImpl)this);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(contactlistcolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(contactlistdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(contactlisttype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getEmailsettingRows(){
			TemplateTable tab=this.doSelectChild("emailsetting", "emailsetting2campaign",this.getParentobjid(),emailsettingcol,emailsettingsqldatatype,this.EmailsettingFilter,emailsettingAccessFilter);
			String [] propEmailsettinglist={};
			String [] codeEmailsettinglist={"stagecode","contenttype","channelscode","starttimecode"};
			String [] relationEmailsettinglist={"mastertemplate:emailsetting2mastertemplate:list:select m.channelscode||^ - ^||m.name as name, m.objid from table_mastertemplate m where m.groupuser=@groupuser and m.genuser=@genuser","contactlist:emailsetting2contactlist:list:"};
			Rows rows=tu.getXMLRows(tab, "emailsetting",codeEmailsettinglist,propEmailsettinglist,relationEmailsettinglist,emailsettingcolcaption,emailsettingdatadomain,(QueryImpl)this);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(emailsettingcolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(emailsettingdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(emailsettingtype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getAutomationRows(){
			TemplateTable tab=this.doSelectChild("automation", "automation2campaign",this.getParentobjid(),automationcol,automationsqldatatype,this.AutomationFilter,automationAccessFilter);
			String [] propAutomationlist={};
			String [] codeAutomationlist={"sourcestagecode","sourcechannelscode","targetstagecode","targetchannelscode"};
			String [] relationAutomationlist={"emailsetting:automation2emailsetting:list:select e.channelscode||^ - ^||e.name  as name, e.objid from table_emailsetting e where e.emailsetting2campaign=@parentobjid or e.emailsetting2campaign= (select  e1.emailsetting2campaign from table_emailsetting e1 where e1.objid=@parentobjid)","nextchannel:automation2nextchannel:list:select e.name, e.objid from table_nextchannel e where e.nextchannel2campaign=@parentobjid or e.nextchannel2campaign= (select  e1.emailsetting2campaign from table_emailsetting e1 where e1.objid=@parentobjid)"};
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



		public Rows getSamplereserveRows(){
			TemplateTable tab=this.doSelectChild("samplereserve", "samplereserve2campaign",this.getParentobjid(),samplereservecol,samplereservesqldatatype,this.SamplereserveFilter,samplereserveAccessFilter);
			String [] propSamplereservelist={"status"};
			String [] codeSamplereservelist={};
			String [] relationSamplereservelist={"store:samplereserve2store:list:"};
			Rows rows=tu.getXMLRows(tab, "samplereserve",codeSamplereservelist,propSamplereservelist,relationSamplereservelist,samplereservecolcaption,samplereservedatadomain,(QueryImpl)this);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(samplereservecolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(samplereservedisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(samplereservetype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getEmailresponseRows(){
			TemplateTable tab=this.doSelectChild("emailresponse", "emailresponse2campaign",this.getParentobjid(),emailresponsecol,emailresponsesqldatatype,this.EmailresponseFilter,emailresponseAccessFilter);
			String [] propEmailresponselist={"status"};
			String [] codeEmailresponselist={"stagecode"};
			String [] relationEmailresponselist={};
			Rows rows=tu.getXMLRows(tab, "emailresponse",codeEmailresponselist,propEmailresponselist,relationEmailresponselist,emailresponsecolcaption,emailresponsedatadomain,(QueryImpl)this);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(emailresponsecolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(emailresponsedisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(emailresponsetype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getSampleorderRows(){
			TemplateTable tab=this.doSelectChild("sampleorder", "sampleorder2campaign",this.getParentobjid(),sampleordercol,sampleordersqldatatype,this.SampleorderFilter,sampleorderAccessFilter);
			String [] propSampleorderlist={"status"};
			String [] codeSampleorderlist={};
			String [] relationSampleorderlist={};
			Rows rows=tu.getXMLRows(tab, "sampleorder",codeSampleorderlist,propSampleorderlist,relationSampleorderlist,sampleordercolcaption,sampleorderdatadomain,(QueryImpl)this);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(sampleordercolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(sampleorderdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(sampleordertype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getSamplesRows(){
			TemplateTable tab=this.doSelectChild("samples", "samples2campaign",this.getParentobjid(),samplescol,samplessqldatatype,this.SamplesFilter,samplesAccessFilter);
			String [] propSampleslist={"status"};
			String [] codeSampleslist={};
			String [] relationSampleslist={"samplereserve:samples2samplereserve:list:","parts:samples2parts:list:"};
			Rows rows=tu.getXMLRows(tab, "samples",codeSampleslist,propSampleslist,relationSampleslist,samplescolcaption,samplesdatadomain,(QueryImpl)this);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(samplescolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(samplesdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(samplestype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getCampaignnoteRows(){
			TemplateTable tab=this.doSelectChild("campaignnote", "campaignnote2campaign",this.getParentobjid(),campaignnotecol,campaignnotesqldatatype,this.CampaignnoteFilter,campaignnoteAccessFilter);
			String [] propCampaignnotelist={};
			String [] codeCampaignnotelist={};
			String [] relationCampaignnotelist={};
			Rows rows=tu.getXMLRows(tab, "campaignnote",codeCampaignnotelist,propCampaignnotelist,relationCampaignnotelist,campaignnotecolcaption,campaignnotedatadomain,(QueryImpl)this);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(campaignnotecolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(campaignnotedisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(campaignnotetype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getSurveyquestionRows(){
			TemplateTable tab=this.doSelectChild("surveyquestion", "surveyquestion2campaign",this.getParentobjid(),surveyquestioncol,surveyquestionsqldatatype,this.SurveyquestionFilter,surveyquestionAccessFilter);
			String [] propSurveyquestionlist={};
			String [] codeSurveyquestionlist={};
			String [] relationSurveyquestionlist={};
			Rows rows=tu.getXMLRows(tab, "surveyquestion",codeSurveyquestionlist,propSurveyquestionlist,relationSurveyquestionlist,surveyquestioncolcaption,surveyquestiondatadomain,(QueryImpl)this);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(surveyquestioncolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(surveyquestiondisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(surveyquestiontype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getCampaignSummaryRows(){
			TemplateTable tab=this.doSelect(summarycol,summarysqldatatype,this.CampaignFilter,false,campaignAccessFilter);
			ArrayList<String> chartcols=tu.getChartSelectColumns("Campaign");
			ArrayList<String> moneycols=tu.getSummaryMoneyColsIndex(summarydatadomain);
			Rows rows=tu.getXMLSummaryRows(tab,summarycolcaption);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("charts",chartcols);
			userdata.add(data1);
			for(String chartcol:chartcols){
				ArrayList<String> datas= tu.getChartPropertyJSON("Campaign", tab, chartcol);
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

		public Items getCampaignForm(){
			TemplateTable tab=this.doSelect(maincol,mainsqldatatype,this.CampaignFilter,true,campaignAccessFilter);
			Items items=tu.getXMLForm(tab, "Campaign",codeCampaignlist,propCampaignlist,relationCampaignlist,maincolcaption,maindatadomain,mainformfields,(QueryImpl)this);
			return items;
		}

		public Rows getCampaignRows(){
			TemplateTable tab=this.doSelect(maincol,mainsqldatatype,null,false,campaignAccessFilter);
			Rows rows=tu.getXMLRows(tab, "Campaign",codeCampaignlist,propCampaignlist,relationCampaignlist,maincolcaption,maindatadomain,(QueryImpl)this);
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

		public Rows getCampaignRowModified(){
			Rows rows=tu.getXMLRows(maindata, "Campaign",codeCampaignlist,propCampaignlist,relationCampaignlist,maincolcaption,maindatadomain,(QueryImpl)this);
			return rows;
		}

		public Rows getCampaignRowDeleted(){
			Rows rows;
			if(this.doDelete(childtabs)){
				rows=tu.getDeletedRows(this.getParentobjid());
			}else{
				rows=tu.getDeletedRows("-1");
			}
			return rows;
		}

		public boolean postCampaignContainer() throws DaoException{
			if(!tu.isEmptyValue(this.getMainxml())){
				return(this.doInsert());
			}else{
				throw new DaoException("ERROR: Post unsuccessful! Probably your XML is missing parent entity or having error!", this.getClass().getName());
			}
		}

		public Rows getCampaignByFilter(){
			String newfilter=" groupuser='"+this.getGroupuser()+"'";
			if(!tu.isEmptyValue(this.getFilters()) &&this.getFilters().toLowerCase().indexOf("campaign2")<0){
				newfilter+=" and campaign2"+this.getFilters();
				this.setParentobjid(this.getFilters().split("=")[1].replaceAll("'",""));
			}else{
				newfilter+=" and "+this.getFilters();
			}
			String sql= "select * from table_Campaign where "+ newfilter;
			TemplateTable tab=tu.getResultSet(sql);
			Rows rows=tu.getXMLFilterRows(tab, "Campaign",codeCampaignlist,propCampaignlist,relationCampaignlist,maincol,maincolcaption,maindatadomain,(QueryImpl)this);
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

		 public JSONObject getCampaignRecordCount() throws JSONException{
			String newfilter=" groupuser='"+this.getGroupuser()+"'";
			if(!tu.isEmptyValue(this.getFilters()) &&this.getFilters().toLowerCase().indexOf("campaign2")<0){
				newfilter+=" and campaign2"+this.getFilters();
			}else{
				newfilter+=" and "+this.getFilters();
			}
			String sql= "select * from table_Campaign where "+ newfilter;
			TemplateTable tab=tu.getResultSet(sql);
			JSONObject data=new JSONObject();
			data.put("table",this.getObject());
			data.put("rowcount",tab.getRowCount());
			return data;
			}
	}
