
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

	public class RfqDao extends RfqImpl {
		Map<String, Cookie> cookies; 
		Map<String,String> userdata;
		protected String []deletetabs={"rfq,rfqparts,rfqemail,trtasks,qrtasks,bidtasks,potasks,emaildocs"};
		protected String []childtabs={"rfq,rfqparts,rfqemail,trtasks,qrtasks,bidtasks,potasks,emaildocs"};
		protected String []childtabnames={"Rfq Facts,RfqParts,RFQEmail,TRTasks,QRTasks,BidTasks,POTasks,Attachment"};
		
		protected String [] maintype={"table"};
		protected String [] maincol={"objid","rfq2partlist","rfq2messagequeue","name","customername","note","estbudget","duedate","custaddress","contactname","contactphone","phone2","email","othercontact","preparedby","leadsourcecode","pctmargin","status","rfqno","validdate"};
		protected String [] maincolcaption={"Id","PartList","MessageQueue","RFQ Name","Customer Name","Note","Estimated Budget","Due Date","Customer Address","Contact Name","Contact Phone","Mobile/Other No","Contact Email","Additional Contact","Prepared By","Lead Source","Margin(%)","Status","RFQ No","Valid Till"};
		protected String [] mainsqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.NUMBER,DataType.DATE,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.NUMBER,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE};
		protected String [] maindatadomain={"Raw_t","Raw_t","Raw_t","Name_t","Name_t","String4000_t","Money_t","Date_t","String400_t","Name_t","Phone_t","Phone_t","Email_t","String300_t","String100_t","Code_t","Float_t","Status_t","String20_t","Date_t"};
		protected String [] maincolsearch={"#text_filter,#select_filter,#select_filter,#text_filter,#text_filter,,,#text_filter,,#text_filter,,,,,,,,#select_filter,#text_filter,#text_filter"};
		
		protected String [] maincoldisable={"yes,no,no,no,no,no,no,no,no,no,no,no,no,no,no,no,no,no,yes,no"};
		private String [] mainformfields={"input","input","input","input","input","input","input","calendar","input","input","input","input","input","input","input","combo","input","combo","input","calendar"};
		
		protected String [] summarycol={"name","total"};
		protected String [] summarycolcaption={"Name","Total"};
		protected String [] summarysqldatatype={DataType.VARCHAR,DataType.NUMBER};
		protected String [] summarydatadomain={"Name_t","Money_t"};
		
		protected String [] reportcol={"objid","name","customername","estbudget","duedate","custaddress","contactphone","phone2","pctmargin","status","rfqno","validdate","total"};
		protected String [] reportcolcaption={"Id","RFQ Name","Customer Name","Estimated Budget","Due Date","Customer Address","Contact Phone","Mobile/Other No","Margin(%)","Status","RFQ No","Valid Till","Total"};
		protected String [] reportsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.NUMBER,DataType.DATE,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.NUMBER,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.NUMBER};
		protected String [] reportdatadomain={"Id_t","Name_t","Name_t","Money_t","Date_t","String400_t","Phone_t","Phone_t","Float_t","Status_t","String20_t","Date_t","Money_t"};
		
		protected String [] searchcol={"objid","name","customername","duedate","contactname","status","rfqno","validdate"};
		protected String [] searchcolcaption={"Id","RFQ Name","Customer Name","Due Date","Contact Name","Status","RFQ No","Valid Till"};
		protected String [] searchcoltype={"integer","text","text","date","text","select","text","date"};
		protected String [] searchdatadomain={"Id_t","Name_t","Name_t","Date_t","Name_t","Status_t","String20_t","Date_t"};

	protected String [] propRfqlist={"status"};
		protected String [] codeRfqlist={"leadsourcecode"};
		protected String [] relationRfqlist={"partlist:rfq2partlist:list:","messagequeue:rfq2messagequeue:list:"};
		
		protected String [] rfqpartstype={"table"};
		protected String [] rfqpartscol={"objid","rfqparts2parts","name","partcode","partno","description","approvedprice","unitprice","unitcount","umcode","note","validdate"};
		protected String [] rfqpartscolcaption={"Id","Parts","Name","Part Code","Part No","Part Spec","Approved Price","Part Price","Quantity","Um Code","Customer Need","Price Valid Till"};
		protected String [] rfqpartssqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.NUMBER,DataType.NUMBER,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE};
		protected String [] rfqpartsdatadomain={"Raw_t","Raw_t","Name_t","String30_t","String100_t","String1000_t","Money_t","Money_t","Int_t","Code_t","String4000_t","Date_t"};
		protected String [] rfqpartsdisable={"yes,no,no,yes,yes,yes,no,yes,no,yes,no,no"};
		protected String [] rfqpartscolsearch={"#text_filter,#select_filter,#text_filter,#text_filter,,,,,,#select_filter,,#text_filter"};
		
		protected String [] rfqemailtype={"table"};
		protected String [] rfqemailcol={"objid","name","sendto","isgroup","sentby","stage","emailtype","partno","message","footer","url"};
		protected String [] rfqemailcolcaption={"Id","Subject","SendTo","Is Group Email","Sent By","Stage","Email Type","Part No","Message","Footer","Attachment"};
		protected String [] rfqemailsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] rfqemaildatadomain={"Raw_t","Name_t","String400_t","Boolean_t","String200_t","Type_t","Type_t","String100_t","String4000_t","String300_t","String4000_t"};
		protected String [] rfqemaildisable={"yes,no,no,no,no,no,no,no,no,no,no"};
		protected String [] rfqemailcolsearch={"#text_filter,#text_filter,,,,#select_filter,#select_filter,#text_filter,,,"};
		
		protected String [] trtaskstype={"table"};
		protected String [] trtaskscol={"objid","name","stage","partno","note","url"};
		protected String [] trtaskscolcaption={"Id","Step Name","Stage","Part No","Note","Document"};
		protected String [] trtaskssqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] trtasksdatadomain={"Raw_t","Name_t","Type_t","String100_t","String4000_t","String300_t"};
		protected String [] trtasksdisable={"yes,no,no,no,no,no"};
		protected String [] trtaskscolsearch={"#text_filter,#text_filter,#select_filter,#text_filter,,#text_filter"};
		
		protected String [] qrtaskstype={"table"};
		protected String [] qrtaskscol={"objid","name","stage","partno","note","url"};
		protected String [] qrtaskscolcaption={"Id","Step Name","Stage","Part No","Note","Document"};
		protected String [] qrtaskssqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] qrtasksdatadomain={"Raw_t","Name_t","Type_t","String100_t","String4000_t","String300_t"};
		protected String [] qrtasksdisable={"yes,no,no,no,no,no"};
		protected String [] qrtaskscolsearch={"#text_filter,#text_filter,#select_filter,#text_filter,,#text_filter"};
		
		protected String [] bidtaskstype={"table"};
		protected String [] bidtaskscol={"objid","name","stage","partno","note","url"};
		protected String [] bidtaskscolcaption={"Id","Step Name","Stage","Part No","Note","Document"};
		protected String [] bidtaskssqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] bidtasksdatadomain={"Raw_t","Name_t","Type_t","String100_t","String4000_t","String300_t"};
		protected String [] bidtasksdisable={"yes,no,no,no,no,no"};
		protected String [] bidtaskscolsearch={"#text_filter,#text_filter,#select_filter,#text_filter,,#text_filter"};
		
		protected String [] potaskstype={"table"};
		protected String [] potaskscol={"objid","name","stage","partno","note","url"};
		protected String [] potaskscolcaption={"Id","Step Name","Stage","Part No","Note","Document"};
		protected String [] potaskssqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] potasksdatadomain={"Raw_t","Name_t","Type_t","String100_t","String4000_t","String300_t"};
		protected String [] potasksdisable={"yes,no,no,no,no,no"};
		protected String [] potaskscolsearch={"#text_filter,#text_filter,#select_filter,#text_filter,,#text_filter"};
		
		protected String [] emaildocstype={"table"};
		protected String [] emaildocscol={"objid","name","description","url"};
		protected String [] emaildocscolcaption={"Id","Name","Description","Document"};
		protected String [] emaildocssqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] emaildocsdatadomain={"Raw_t","String200_t","String300_t","String500_t"};
		protected String [] emaildocsdisable={"yes,no,no,yes"};
		protected String [] emaildocscolsearch={"#text_filter,#text_filter,,#text_filter"};

		public RfqDao(UriInfo uriInfo, HttpHeaders header) throws AuthenticationException{
			this.setObject("Rfq");
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
				if(entity.toLowerCase().contains("<rfq>")){
					tmp=entity.replace("<?", "");
					this.setMainxml("<?xml"+tmp);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Main XML="+this.getMainxml());
					}
				}else if(entity.toLowerCase().contains("<rfqparts>")){
					this.setRfqpartsxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getRfqpartsxml());
					}
				}else if(entity.toLowerCase().contains("<rfqemail>")){
					this.setRfqemailxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getRfqemailxml());
					}
				}else if(entity.toLowerCase().contains("<trtasks>")){
					this.setTrtasksxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getTrtasksxml());
					}
				}else if(entity.toLowerCase().contains("<qrtasks>")){
					this.setQrtasksxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getQrtasksxml());
					}
				}else if(entity.toLowerCase().contains("<bidtasks>")){
					this.setBidtasksxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getBidtasksxml());
					}
				}else if(entity.toLowerCase().contains("<potasks>")){
					this.setPotasksxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getPotasksxml());
					}
				}else if(entity.toLowerCase().contains("<emaildocs>")){
					this.setEmaildocsxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getEmaildocsxml());
					}
				}
			}
		}

		public Rows getRfqpartsRows(){
			TemplateTable tab=this.doSelectChild("rfqparts", "rfqparts2rfq",this.getParentobjid(),rfqpartscol,rfqpartssqldatatype,this.RfqpartsFilter,rfqpartsAccessFilter);
			String [] propRfqpartslist={};
			String [] codeRfqpartslist={};
			String [] relationRfqpartslist={"parts:rfqparts2parts:list:select parts.* from table_parts parts, table_rfq rfq where rfq.rfq2partlist=parts.parts2partlist and rfq.groupuser=@groupuser"};
			Rows rows=tu.getXMLRows(tab, "rfqparts",codeRfqpartslist,propRfqpartslist,relationRfqpartslist,rfqpartscolcaption,rfqpartsdatadomain,(QueryImpl)this);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(rfqpartscolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(rfqpartsdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(rfqpartstype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getRfqemailRows(){
			TemplateTable tab=this.doSelectChild("rfqemail", "rfqemail2rfq",this.getParentobjid(),rfqemailcol,rfqemailsqldatatype,this.RfqemailFilter,rfqemailAccessFilter);
			String [] propRfqemaillist={"stage","emailtype"};
			String [] codeRfqemaillist={};
			String [] relationRfqemaillist={};
			Rows rows=tu.getXMLRows(tab, "rfqemail",codeRfqemaillist,propRfqemaillist,relationRfqemaillist,rfqemailcolcaption,rfqemaildatadomain,(QueryImpl)this);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(rfqemailcolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(rfqemaildisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(rfqemailtype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getTrtasksRows(){
			TemplateTable tab=this.doSelectChild("trtasks", "trtasks2rfq",this.getParentobjid(),trtaskscol,trtaskssqldatatype,this.TrtasksFilter,trtasksAccessFilter);
			String [] propTrtaskslist={"stage"};
			String [] codeTrtaskslist={};
			String [] relationTrtaskslist={};
			Rows rows=tu.getXMLRows(tab, "trtasks",codeTrtaskslist,propTrtaskslist,relationTrtaskslist,trtaskscolcaption,trtasksdatadomain,(QueryImpl)this);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(trtaskscolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(trtasksdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(trtaskstype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getQrtasksRows(){
			TemplateTable tab=this.doSelectChild("qrtasks", "qrtasks2rfq",this.getParentobjid(),qrtaskscol,qrtaskssqldatatype,this.QrtasksFilter,qrtasksAccessFilter);
			String [] propQrtaskslist={"stage"};
			String [] codeQrtaskslist={};
			String [] relationQrtaskslist={};
			Rows rows=tu.getXMLRows(tab, "qrtasks",codeQrtaskslist,propQrtaskslist,relationQrtaskslist,qrtaskscolcaption,qrtasksdatadomain,(QueryImpl)this);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(qrtaskscolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(qrtasksdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(qrtaskstype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getBidtasksRows(){
			TemplateTable tab=this.doSelectChild("bidtasks", "bidtasks2rfq",this.getParentobjid(),bidtaskscol,bidtaskssqldatatype,this.BidtasksFilter,bidtasksAccessFilter);
			String [] propBidtaskslist={"stage"};
			String [] codeBidtaskslist={};
			String [] relationBidtaskslist={};
			Rows rows=tu.getXMLRows(tab, "bidtasks",codeBidtaskslist,propBidtaskslist,relationBidtaskslist,bidtaskscolcaption,bidtasksdatadomain,(QueryImpl)this);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(bidtaskscolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(bidtasksdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(bidtaskstype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getPotasksRows(){
			TemplateTable tab=this.doSelectChild("potasks", "potasks2rfq",this.getParentobjid(),potaskscol,potaskssqldatatype,this.PotasksFilter,potasksAccessFilter);
			String [] propPotaskslist={"stage"};
			String [] codePotaskslist={};
			String [] relationPotaskslist={};
			Rows rows=tu.getXMLRows(tab, "potasks",codePotaskslist,propPotaskslist,relationPotaskslist,potaskscolcaption,potasksdatadomain,(QueryImpl)this);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(potaskscolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(potasksdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(potaskstype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getEmaildocsRows(){
			TemplateTable tab=this.doSelectChild("emaildocs", "emaildocs2rfq",this.getParentobjid(),emaildocscol,emaildocssqldatatype,this.EmaildocsFilter,emaildocsAccessFilter);
			String [] propEmaildocslist={};
			String [] codeEmaildocslist={};
			String [] relationEmaildocslist={};
			Rows rows=tu.getXMLRows(tab, "emaildocs",codeEmaildocslist,propEmaildocslist,relationEmaildocslist,emaildocscolcaption,emaildocsdatadomain,(QueryImpl)this);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(emaildocscolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(emaildocsdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(emaildocstype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getRfqSummaryRows(){
			TemplateTable tab=this.doSelect(summarycol,summarysqldatatype,this.RfqFilter,false,rfqAccessFilter);
			ArrayList<String> chartcols=tu.getChartSelectColumns("Rfq");
			ArrayList<String> moneycols=tu.getSummaryMoneyColsIndex(summarydatadomain);
			Rows rows=tu.getXMLSummaryRows(tab,summarycolcaption);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("charts",chartcols);
			userdata.add(data1);
			for(String chartcol:chartcols){
				ArrayList<String> datas= tu.getChartPropertyJSON("Rfq", tab, chartcol);
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

		public Items getRfqForm(){
			TemplateTable tab=this.doSelect(maincol,mainsqldatatype,this.RfqFilter,true,rfqAccessFilter);
			Items items=tu.getXMLForm(tab, "Rfq",codeRfqlist,propRfqlist,relationRfqlist,maincolcaption,maindatadomain,mainformfields,(QueryImpl)this);
			return items;
		}

		public Rows getRfqRows(){
			TemplateTable tab=this.doSelect(maincol,mainsqldatatype,null,false,rfqAccessFilter);
			Rows rows=tu.getXMLRows(tab, "Rfq",codeRfqlist,propRfqlist,relationRfqlist,maincolcaption,maindatadomain,(QueryImpl)this);
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

		public Rows getRfqRowModified(){
			Rows rows=tu.getXMLRows(maindata, "Rfq",codeRfqlist,propRfqlist,relationRfqlist,maincolcaption,maindatadomain,(QueryImpl)this);
			return rows;
		}

		public Rows getRfqRowDeleted(){
			Rows rows;
			if(this.doDelete(childtabs)){
				rows=tu.getDeletedRows(this.getParentobjid());
			}else{
				rows=tu.getDeletedRows("-1");
			}
			return rows;
		}

		public boolean postRfqContainer() throws DaoException{
			if(!tu.isEmptyValue(this.getMainxml())){
				return(this.doInsert());
			}else{
				throw new DaoException("ERROR: Post unsuccessful! Probably your XML is missing parent entity or having error!", this.getClass().getName());
			}
		}

		public Rows getRfqByFilter(){
			String newfilter=" groupuser='"+this.getGroupuser()+"'";
			if(!tu.isEmptyValue(this.getFilters()) &&this.getFilters().toLowerCase().indexOf("rfq2")<0){
				newfilter+=" and rfq2"+this.getFilters();
				this.setParentobjid(this.getFilters().split("=")[1].replaceAll("'",""));
			}else{
				newfilter+=" and "+this.getFilters();
			}
			String sql= "select * from table_Rfq where "+ newfilter;
			TemplateTable tab=tu.getResultSet(sql);
			Rows rows=tu.getXMLFilterRows(tab, "Rfq",codeRfqlist,propRfqlist,relationRfqlist,maincol,maincolcaption,maindatadomain,(QueryImpl)this);
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

		 public JSONObject getRfqRecordCount() throws JSONException{
			String newfilter=" groupuser='"+this.getGroupuser()+"'";
			if(!tu.isEmptyValue(this.getFilters()) &&this.getFilters().toLowerCase().indexOf("rfq2")<0){
				newfilter+=" and rfq2"+this.getFilters();
			}else{
				newfilter+=" and "+this.getFilters();
			}
			String sql= "select * from table_Rfq where "+ newfilter;
			TemplateTable tab=tu.getResultSet(sql);
			JSONObject data=new JSONObject();
			data.put("table",this.getObject());
			data.put("rowcount",tab.getRowCount());
			return data;
			}
	}
