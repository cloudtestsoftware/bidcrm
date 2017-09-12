
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

	public class LeadDao extends LeadImpl {
		Map<String, Cookie> cookies; 
		Map<String,String> userdata;
		protected String []deletetabs={"lead,samples"};
		protected String []childtabs={"samples"};
		protected String []childtabnames={"Samples"};
		
		protected String [] maintype={"table"};
		protected String [] maincol={"objid","lead2campaign","lead2assignedto","name","middlename","lastname","jobtitle","company","address","country","state","city","zipcode","officephone","mobile","fax","email","url","othercontact","quicknote","leadstage","leadstatus","leadsource","referrer","agentid","leadid"};
		protected String [] maincolcaption={"Id","Campaign","AssignedTo","Name","Middle Name","Last Name","Job Title","Company","Address","Country","State","City","Zip","Office Number","Mobile No","Fax","Email","URL","Other Contact","Quick Note","Stage","Followup Status","Lead Source","Referrer","Agent Id","Lead Id"};
		protected String [] mainsqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] maindatadomain={"Raw_t","Raw_t","Raw_t","Name_t","Name_t","Name_t","String50_t","Name_t","String300_t","String30_t","String30_t","String50_t","String10_t","Phone_t","Phone_t","Phone_t","Email_t","String100_t","String500_t","String4000_t","Type_t","Type_t","Type_t","Name_t","String20_t","String20_t"};
		protected String [] maincolsearch={"#text_filter,#select_filter,#select_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,,#text_filter,#text_filter,#text_filter,#text_filter,,,,,,,,#select_filter,#select_filter,#select_filter,,,"};
		
		protected String [] maincoldisable={"yes,no,no,no,no,no,no,no,no,no,no,no,no,no,no,no,no,no,no,no,no,no,no,no,no,no"};
		private String [] mainformfields={"input","input","input","input","input","input","input","input","input","input","input","input","input","input","input","input","input","input","input","input","combo","combo","combo","input","input","input"};
		
		protected String [] summarycol={"name"};
		protected String [] summarycolcaption={"Name"};
		protected String [] summarysqldatatype={DataType.VARCHAR};
		protected String [] summarydatadomain={"Name_t"};
		
		protected String [] reportcol={"objid","name","middlename","lastname","jobtitle","country","state","city","zipcode","officephone","othercontact","quicknote","leadstage","leadstatus","leadsource","address"};
		protected String [] reportcolcaption={"Id","Name","Middle Name","Last Name","Job Title","Country","State","City","Zip","Office Number","Other Contact","Quick Note","Stage","Followup Status","Lead Source","Address"};
		protected String [] reportsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] reportdatadomain={"Id_t","Name_t","Name_t","Name_t","String50_t","String30_t","String30_t","String50_t","String10_t","Phone_t","String500_t","String4000_t","Type_t","Type_t","Type_t","String300_t"};
		
		protected String [] searchcol={"objid","name","middlename","lastname","jobtitle","company","country","state","city","zipcode","leadstage","leadstatus","leadsource"};
		protected String [] searchcolcaption={"Id","Name","Middle Name","Last Name","Job Title","Company","Country","State","City","Zip","Stage","Followup Status","Lead Source"};
		protected String [] searchcoltype={"integer","text","text","text","text","text","text","text","text","text","select","select","select"};
		protected String [] searchdatadomain={"Id_t","Name_t","Name_t","Name_t","String50_t","Name_t","String30_t","String30_t","String50_t","String10_t","Type_t","Type_t","Type_t"};

	protected String [] propLeadlist={"leadstage","leadstatus","leadsource"};
		protected String [] codeLeadlist={};
		protected String [] relationLeadlist={"campaign:lead2campaign:list:","assignedto:lead2assignedto:list:"};
		
		protected String [] samplestype={"table"};
		protected String [] samplescol={"objid","samples2samplereserve","samples2parts","name","ispublic","qntrequest","qntunused","description","status","price","qntordered","qntonhand"};
		protected String [] samplescolcaption={"Id","SampleReserve","Parts","Item","Is Available To Public","Quantity Requested","Quantity UnUsed","Description","Status","Price","Quantity Ordered","Quantity On Hand"};
		protected String [] samplessqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.INTEGER,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.INTEGER,DataType.INTEGER};
		protected String [] samplesdatadomain={"Raw_t","Raw_t","Raw_t","String200_t","Boolean_t","Int_t","Int_t","String400_t","Status_t","String100_t","Int_t","Int_t"};
		protected String [] samplesdisable={"yes,no,no,no,no,no,yes,yes,yes,no,yes,yes"};
		protected String [] samplescolsearch={"#text_filter,#select_filter,#select_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#select_filter,#text_filter,#text_filter,#text_filter"};

		public LeadDao(UriInfo uriInfo, HttpHeaders header) throws AuthenticationException{
			this.setObject("Lead");
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
				if(entity.toLowerCase().contains("<lead>")){
					tmp=entity.replace("<?", "");
					this.setMainxml("<?xml"+tmp);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Main XML="+this.getMainxml());
					}
				}else if(entity.toLowerCase().contains("<samples>")){
					this.setSamplesxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getSamplesxml());
					}
				}
			}
		}

		public Rows getSamplesRows(){
			TemplateTable tab=this.doSelectChild("samples", "samples2lead",this.getParentobjid(),samplescol,samplessqldatatype,this.SamplesFilter,samplesAccessFilter);
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



		public Rows getLeadSummaryRows(){
			TemplateTable tab=this.doSelect(summarycol,summarysqldatatype,this.LeadFilter,false,leadAccessFilter);
			ArrayList<String> chartcols=tu.getChartSelectColumns("Lead");
			ArrayList<String> moneycols=tu.getSummaryMoneyColsIndex(summarydatadomain);
			Rows rows=tu.getXMLSummaryRows(tab,summarycolcaption);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("charts",chartcols);
			userdata.add(data1);
			for(String chartcol:chartcols){
				ArrayList<String> datas= tu.getChartPropertyJSON("Lead", tab, chartcol);
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

		public Items getLeadForm(){
			TemplateTable tab=this.doSelect(maincol,mainsqldatatype,this.LeadFilter,true,leadAccessFilter);
			Items items=tu.getXMLForm(tab, "Lead",codeLeadlist,propLeadlist,relationLeadlist,maincolcaption,maindatadomain,mainformfields,(QueryImpl)this);
			return items;
		}

		public Rows getLeadRows(){
			TemplateTable tab=this.doSelect(maincol,mainsqldatatype,null,false,leadAccessFilter);
			Rows rows=tu.getXMLRows(tab, "Lead",codeLeadlist,propLeadlist,relationLeadlist,maincolcaption,maindatadomain,(QueryImpl)this);
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

		public Rows getLeadRowModified(){
			Rows rows=tu.getXMLRows(maindata, "Lead",codeLeadlist,propLeadlist,relationLeadlist,maincolcaption,maindatadomain,(QueryImpl)this);
			return rows;
		}

		public Rows getLeadRowDeleted(){
			Rows rows;
			if(this.doDelete(childtabs)){
				rows=tu.getDeletedRows(this.getParentobjid());
			}else{
				rows=tu.getDeletedRows("-1");
			}
			return rows;
		}

		public boolean postLeadContainer() throws DaoException{
			if(!tu.isEmptyValue(this.getMainxml())){
				return(this.doInsert());
			}else{
				throw new DaoException("ERROR: Post unsuccessful! Probably your XML is missing parent entity or having error!", this.getClass().getName());
			}
		}

		public Rows getLeadByFilter(){
			String newfilter=" groupuser='"+this.getGroupuser()+"'";
			if(!tu.isEmptyValue(this.getFilters()) &&this.getFilters().toLowerCase().indexOf("lead2")<0){
				newfilter+=" and lead2"+this.getFilters();
				this.setParentobjid(this.getFilters().split("=")[1].replaceAll("'",""));
			}else{
				newfilter+=" and "+this.getFilters();
			}
			String sql= "select * from table_Lead where "+ newfilter;
			TemplateTable tab=tu.getResultSet(sql);
			Rows rows=tu.getXMLFilterRows(tab, "Lead",codeLeadlist,propLeadlist,relationLeadlist,maincol,maincolcaption,maindatadomain,(QueryImpl)this);
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

		 public JSONObject getLeadRecordCount() throws JSONException{
			String newfilter=" groupuser='"+this.getGroupuser()+"'";
			if(!tu.isEmptyValue(this.getFilters()) &&this.getFilters().toLowerCase().indexOf("lead2")<0){
				newfilter+=" and lead2"+this.getFilters();
			}else{
				newfilter+=" and "+this.getFilters();
			}
			String sql= "select * from table_Lead where "+ newfilter;
			TemplateTable tab=tu.getResultSet(sql);
			JSONObject data=new JSONObject();
			data.put("table",this.getObject());
			data.put("rowcount",tab.getRowCount());
			return data;
			}
	}
