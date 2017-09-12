
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

	public class StorepartlistDao extends StorepartlistImpl {
		Map<String, Cookie> cookies; 
		Map<String,String> userdata;
		protected String []deletetabs={"storepartlist,storeparts"};
		protected String []childtabs={"storepartlist,storeparts"};
		protected String []childtabnames={"Storepartlist Facts,Store Parts"};
		
		protected String [] maintype={"table"};
		protected String [] maincol={"objid","storepartlist2store","storepartlist2partlist","name","storelistno"};
		protected String [] maincolcaption={"Id","Store","PartList","Name","Store List No"};
		protected String [] mainsqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] maindatadomain={"Raw_t","Raw_t","Raw_t","Name_t","String30_t"};
		protected String [] maincolsearch={"#text_filter,#select_filter,#select_filter,#text_filter,#text_filter"};
		
		protected String [] maincoldisable={"yes,no,no,no,no"};
		private String [] mainformfields={"input","input","input","input","input"};
		
		protected String [] summarycol={"name","partlistno","description"};
		protected String [] summarycolcaption={"Name","Part List No","Description"};
		protected String [] summarysqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] summarydatadomain={"Name_t","String30_t","String1000_t"};
		
		protected String [] reportcol={"objid","name","partlistno","description","storelistno"};
		protected String [] reportcolcaption={"Id","Name","Part List No","Description","Store List No"};
		protected String [] reportsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] reportdatadomain={"Id_t","Name_t","String30_t","String1000_t","String30_t"};
		
		protected String [] searchcol={"objid","name","partlistno","storelistno"};
		protected String [] searchcolcaption={"Id","Name","Part List No","Store List No"};
		protected String [] searchcoltype={"integer","text","text","text"};
		protected String [] searchdatadomain={"Id_t","Name_t","String30_t","String30_t"};

	protected String [] propStorepartlistlist={};
		protected String [] codeStorepartlistlist={};
		protected String [] relationStorepartlistlist={"store:storepartlist2store:list:","partlist:storepartlist2partlist:list:"};
		
		protected String [] storepartstype={"table"};
		protected String [] storepartscol={"objid","storeparts2parts","name","domaincode","storepartno","storeprice","note","partlistno","umcode","unitprice","pcttax","partspec","totalinventory","assigned","available","partno"};
		protected String [] storepartscolcaption={"Id","Parts","Name","Part Group","Store Part No","Store Price","Note","Part Code","UM Code","List Price","(%) Tax","Details","Store Inventory","Assigned","Available","PartList Part No"};
		protected String [] storepartssqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.NUMBER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.NUMBER,DataType.NUMBER,DataType.VARCHAR,DataType.INTEGER,DataType.INTEGER,DataType.INTEGER,DataType.VARCHAR};
		protected String [] storepartsdatadomain={"Raw_t","Raw_t","Name_t","Code_t","String100_t","Money_t","String500_t","String30_t","Code_t","Money_t","Percent_t","String500_t","Int_t","Int_t","Int_t","String100_t"};
		protected String [] storepartsdisable={"yes,no,no,no,no,no,no,yes,yes,yes,yes,yes,yes,yes,yes,yes"};
		protected String [] storepartscolsearch={"#text_filter,#select_filter,#text_filter,#select_filter,#text_filter,,,#text_filter,#select_filter,,,,,,,#text_filter"};

		public StorepartlistDao(UriInfo uriInfo, HttpHeaders header) throws AuthenticationException{
			this.setObject("Storepartlist");
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
				if(entity.toLowerCase().contains("<storepartlist>")){
					tmp=entity.replace("<?", "");
					this.setMainxml("<?xml"+tmp);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Main XML="+this.getMainxml());
					}
				}else if(entity.toLowerCase().contains("<storeparts>")){
					this.setStorepartsxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getStorepartsxml());
					}
				}
			}
		}

		public Rows getStorepartsRows(){
			TemplateTable tab=this.doSelectChild("storeparts", "storeparts2storepartlist",this.getParentobjid(),storepartscol,storepartssqldatatype,this.StorepartsFilter,storepartsAccessFilter);
			String [] propStorepartslist={};
			String [] codeStorepartslist={"domaincode","umcode"};
			String [] relationStorepartslist={"parts:storeparts2parts:list:"};
			Rows rows=tu.getXMLRows(tab, "storeparts",codeStorepartslist,propStorepartslist,relationStorepartslist,storepartscolcaption,storepartsdatadomain,(QueryImpl)this);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(storepartscolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(storepartsdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(storepartstype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getStorepartlistSummaryRows(){
			TemplateTable tab=this.doSelect(summarycol,summarysqldatatype,this.StorepartlistFilter,false,storepartlistAccessFilter);
			ArrayList<String> chartcols=tu.getChartSelectColumns("Storepartlist");
			ArrayList<String> moneycols=tu.getSummaryMoneyColsIndex(summarydatadomain);
			Rows rows=tu.getXMLSummaryRows(tab,summarycolcaption);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("charts",chartcols);
			userdata.add(data1);
			for(String chartcol:chartcols){
				ArrayList<String> datas= tu.getChartPropertyJSON("Storepartlist", tab, chartcol);
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

		public Items getStorepartlistForm(){
			TemplateTable tab=this.doSelect(maincol,mainsqldatatype,this.StorepartlistFilter,true,storepartlistAccessFilter);
			Items items=tu.getXMLForm(tab, "Storepartlist",codeStorepartlistlist,propStorepartlistlist,relationStorepartlistlist,maincolcaption,maindatadomain,mainformfields,(QueryImpl)this);
			return items;
		}

		public Rows getStorepartlistRows(){
			TemplateTable tab=this.doSelect(maincol,mainsqldatatype,null,false,storepartlistAccessFilter);
			Rows rows=tu.getXMLRows(tab, "Storepartlist",codeStorepartlistlist,propStorepartlistlist,relationStorepartlistlist,maincolcaption,maindatadomain,(QueryImpl)this);
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

		public Rows getStorepartlistRowModified(){
			Rows rows=tu.getXMLRows(maindata, "Storepartlist",codeStorepartlistlist,propStorepartlistlist,relationStorepartlistlist,maincolcaption,maindatadomain,(QueryImpl)this);
			return rows;
		}

		public Rows getStorepartlistRowDeleted(){
			Rows rows;
			if(this.doDelete(childtabs)){
				rows=tu.getDeletedRows(this.getParentobjid());
			}else{
				rows=tu.getDeletedRows("-1");
			}
			return rows;
		}

		public boolean postStorepartlistContainer() throws DaoException{
			if(!tu.isEmptyValue(this.getMainxml())){
				return(this.doInsert());
			}else{
				throw new DaoException("ERROR: Post unsuccessful! Probably your XML is missing parent entity or having error!", this.getClass().getName());
			}
		}

		public Rows getStorepartlistByFilter(){
			String newfilter=" groupuser='"+this.getGroupuser()+"'";
			if(!tu.isEmptyValue(this.getFilters()) &&this.getFilters().toLowerCase().indexOf("storepartlist2")<0){
				newfilter+=" and storepartlist2"+this.getFilters();
				this.setParentobjid(this.getFilters().split("=")[1].replaceAll("'",""));
			}else{
				newfilter+=" and "+this.getFilters();
			}
			String sql= "select * from table_Storepartlist where "+ newfilter;
			TemplateTable tab=tu.getResultSet(sql);
			Rows rows=tu.getXMLFilterRows(tab, "Storepartlist",codeStorepartlistlist,propStorepartlistlist,relationStorepartlistlist,maincol,maincolcaption,maindatadomain,(QueryImpl)this);
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

		 public JSONObject getStorepartlistRecordCount() throws JSONException{
			String newfilter=" groupuser='"+this.getGroupuser()+"'";
			if(!tu.isEmptyValue(this.getFilters()) &&this.getFilters().toLowerCase().indexOf("storepartlist2")<0){
				newfilter+=" and storepartlist2"+this.getFilters();
			}else{
				newfilter+=" and "+this.getFilters();
			}
			String sql= "select * from table_Storepartlist where "+ newfilter;
			TemplateTable tab=tu.getResultSet(sql);
			JSONObject data=new JSONObject();
			data.put("table",this.getObject());
			data.put("rowcount",tab.getRowCount());
			return data;
			}
	}
