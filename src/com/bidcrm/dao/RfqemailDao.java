
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

	public class RfqemailDao extends RfqemailImpl {
		Map<String, Cookie> cookies; 
		Map<String,String> userdata;
		protected String []deletetabs={"rfqemail,emaildocs"};
		protected String []childtabs={"rfqemail,emaildocs"};
		protected String []childtabnames={"Rfqemail Facts,Attachment"};
		
		protected String [] maintype={"table"};
		protected String [] maincol={"objid","rfqemail2rfq","name","sendto","isgroup","sentby","stage","emailtype","partno","message","footer"};
		protected String [] maincolcaption={"Id","RFQ","Subject","SendTo","Is Group Email","Sent By","Stage","Email Type","Part No","Message","Footer"};
		protected String [] mainsqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] maindatadomain={"Raw_t","Raw_t","Name_t","String400_t","Boolean_t","String200_t","Type_t","Type_t","String100_t","String4000_t","String300_t"};
		protected String [] maincolsearch={"#text_filter,#select_filter,#text_filter,,,,#select_filter,#select_filter,#text_filter,,"};
		
		protected String [] maincoldisable={"yes,no,no,no,no,no,no,no,no,no,no"};
		private String [] mainformfields={"input","input","input","input","input","input","combo","combo","input","input","input"};
		
		protected String [] summarycol={"name","url"};
		protected String [] summarycolcaption={"Name","Attachment"};
		protected String [] summarysqldatatype={DataType.VARCHAR,DataType.VARCHAR};
		protected String [] summarydatadomain={"Name_t","String4000_t"};
		
		protected String [] reportcol={"objid","name","sendto","isgroup","sentby","stage","emailtype","partno","message","footer","url"};
		protected String [] reportcolcaption={"Id","Subject","SendTo","Is Group Email","Sent By","Stage","Email Type","Part No","Message","Footer","Attachment"};
		protected String [] reportsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] reportdatadomain={"Id_t","Name_t","String400_t","Boolean_t","String200_t","Type_t","Type_t","String100_t","String4000_t","String300_t","String4000_t"};
		
		protected String [] searchcol={"objid","name","stage","emailtype","partno"};
		protected String [] searchcolcaption={"Id","Subject","Stage","Email Type","Part No"};
		protected String [] searchcoltype={"integer","text","select","select","text"};
		protected String [] searchdatadomain={"Id_t","Name_t","Type_t","Type_t","String100_t"};

	protected String [] propRfqemaillist={"stage","emailtype"};
		protected String [] codeRfqemaillist={};
		protected String [] relationRfqemaillist={"rfq:rfqemail2rfq:list:"};
		
		protected String [] emaildocstype={"table"};
		protected String [] emaildocscol={"objid","name","description","url"};
		protected String [] emaildocscolcaption={"Id","Name","Description","Document"};
		protected String [] emaildocssqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] emaildocsdatadomain={"Raw_t","String200_t","String300_t","String500_t"};
		protected String [] emaildocsdisable={"yes,no,no,yes"};
		protected String [] emaildocscolsearch={"#text_filter,#text_filter,,#text_filter"};

		public RfqemailDao(UriInfo uriInfo, HttpHeaders header) throws AuthenticationException{
			this.setObject("Rfqemail");
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
				if(entity.toLowerCase().contains("<rfqemail>")){
					tmp=entity.replace("<?", "");
					this.setMainxml("<?xml"+tmp);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Main XML="+this.getMainxml());
					}
				}else if(entity.toLowerCase().contains("<emaildocs>")){
					this.setEmaildocsxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getEmaildocsxml());
					}
				}
			}
		}

		public Rows getEmaildocsRows(){
			TemplateTable tab=this.doSelectChild("emaildocs", "emaildocs2rfqemail",this.getParentobjid(),emaildocscol,emaildocssqldatatype,this.EmaildocsFilter,emaildocsAccessFilter);
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



		public Rows getRfqemailSummaryRows(){
			TemplateTable tab=this.doSelect(summarycol,summarysqldatatype,this.RfqemailFilter,false,rfqemailAccessFilter);
			ArrayList<String> chartcols=tu.getChartSelectColumns("Rfqemail");
			ArrayList<String> moneycols=tu.getSummaryMoneyColsIndex(summarydatadomain);
			Rows rows=tu.getXMLSummaryRows(tab,summarycolcaption);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("charts",chartcols);
			userdata.add(data1);
			for(String chartcol:chartcols){
				ArrayList<String> datas= tu.getChartPropertyJSON("Rfqemail", tab, chartcol);
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

		public Items getRfqemailForm(){
			TemplateTable tab=this.doSelect(maincol,mainsqldatatype,this.RfqemailFilter,true,rfqemailAccessFilter);
			Items items=tu.getXMLForm(tab, "Rfqemail",codeRfqemaillist,propRfqemaillist,relationRfqemaillist,maincolcaption,maindatadomain,mainformfields,(QueryImpl)this);
			return items;
		}

		public Rows getRfqemailRows(){
			TemplateTable tab=this.doSelect(maincol,mainsqldatatype,null,false,rfqemailAccessFilter);
			Rows rows=tu.getXMLRows(tab, "Rfqemail",codeRfqemaillist,propRfqemaillist,relationRfqemaillist,maincolcaption,maindatadomain,(QueryImpl)this);
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

		public Rows getRfqemailRowModified(){
			Rows rows=tu.getXMLRows(maindata, "Rfqemail",codeRfqemaillist,propRfqemaillist,relationRfqemaillist,maincolcaption,maindatadomain,(QueryImpl)this);
			return rows;
		}

		public Rows getRfqemailRowDeleted(){
			Rows rows;
			if(this.doDelete(childtabs)){
				rows=tu.getDeletedRows(this.getParentobjid());
			}else{
				rows=tu.getDeletedRows("-1");
			}
			return rows;
		}

		public boolean postRfqemailContainer() throws DaoException{
			if(!tu.isEmptyValue(this.getMainxml())){
				return(this.doInsert());
			}else{
				throw new DaoException("ERROR: Post unsuccessful! Probably your XML is missing parent entity or having error!", this.getClass().getName());
			}
		}

		public Rows getRfqemailByFilter(){
			String newfilter=" groupuser='"+this.getGroupuser()+"'";
			if(!tu.isEmptyValue(this.getFilters()) &&this.getFilters().toLowerCase().indexOf("rfqemail2")<0){
				newfilter+=" and rfqemail2"+this.getFilters();
				this.setParentobjid(this.getFilters().split("=")[1].replaceAll("'",""));
			}else{
				newfilter+=" and "+this.getFilters();
			}
			String sql= "select * from table_Rfqemail where "+ newfilter;
			TemplateTable tab=tu.getResultSet(sql);
			Rows rows=tu.getXMLFilterRows(tab, "Rfqemail",codeRfqemaillist,propRfqemaillist,relationRfqemaillist,maincol,maincolcaption,maindatadomain,(QueryImpl)this);
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

		 public JSONObject getRfqemailRecordCount() throws JSONException{
			String newfilter=" groupuser='"+this.getGroupuser()+"'";
			if(!tu.isEmptyValue(this.getFilters()) &&this.getFilters().toLowerCase().indexOf("rfqemail2")<0){
				newfilter+=" and rfqemail2"+this.getFilters();
			}else{
				newfilter+=" and "+this.getFilters();
			}
			String sql= "select * from table_Rfqemail where "+ newfilter;
			TemplateTable tab=tu.getResultSet(sql);
			JSONObject data=new JSONObject();
			data.put("table",this.getObject());
			data.put("rowcount",tab.getRowCount());
			return data;
			}
	}
