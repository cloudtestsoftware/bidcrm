
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

	public class StorepartsDao extends StorepartsImpl {
		Map<String, Cookie> cookies; 
		Map<String,String> userdata;
		protected String []deletetabs={"storeparts,addinventory"};
		protected String []childtabs={"storeparts,addinventory"};
		protected String []childtabnames={"Storeparts Facts,AddInventory"};
		
		protected String [] maintype={"table"};
		protected String [] maincol={"objid","storeparts2parts","storeparts2storepartlist","name","domaincode","servicelife","storepartno","storeprice","note"};
		protected String [] maincolcaption={"Id","Parts","StorePartList","Name","Part Group","Service Life (Yrs)","Store Part No","Store Price","Note"};
		protected String [] mainsqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.NUMBER,DataType.VARCHAR,DataType.NUMBER,DataType.VARCHAR};
		protected String [] maindatadomain={"Raw_t","Raw_t","Raw_t","Name_t","Code_t","Float_t","String100_t","Money_t","String500_t"};
		protected String [] maincolsearch={"#text_filter,#select_filter,#select_filter,#text_filter,#select_filter,,#text_filter,,"};
		
		protected String [] maincoldisable={"yes,no,no,no,no,no,no,no,no"};
		private String [] mainformfields={"input","input","input","input","combo","input","input","input","input"};
		
		protected String [] summarycol={"name","partlistno","umcode","unitprice","pcttax","partspec","totalinventory","assigned","available","partno"};
		protected String [] summarycolcaption={"Name","Part Code","UM Code","List Price","(%) Tax","Details","Store Inventory","Assigned","Available","PartList Part No"};
		protected String [] summarysqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.NUMBER,DataType.NUMBER,DataType.VARCHAR,DataType.INTEGER,DataType.INTEGER,DataType.INTEGER,DataType.VARCHAR};
		protected String [] summarydatadomain={"Name_t","String30_t","Code_t","Money_t","Percent_t","String500_t","Int_t","Int_t","Int_t","String100_t"};
		
		protected String [] reportcol={"objid","domaincode","storepartno","storeprice","partlistno","umcode","unitprice","totalinventory","assigned","available","name","partno"};
		protected String [] reportcolcaption={"Id","Part Group","Store Part No","Store Price","Part Code","UM Code","List Price","Store Inventory","Assigned","Available","Name","PartList Part No"};
		protected String [] reportsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.NUMBER,DataType.VARCHAR,DataType.VARCHAR,DataType.NUMBER,DataType.INTEGER,DataType.INTEGER,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] reportdatadomain={"Id_t","Code_t","String100_t","Money_t","String30_t","Code_t","Money_t","Int_t","Int_t","Int_t","Name_t","String100_t"};
		
		protected String [] searchcol={"objid","domaincode","storepartno","partlistno","umcode","name","partno"};
		protected String [] searchcolcaption={"Id","Part Group","Store Part No","Part Code","UM Code","Name","PartList Part No"};
		protected String [] searchcoltype={"integer","select","text","text","select","text","text"};
		protected String [] searchdatadomain={"Id_t","Code_t","String100_t","String30_t","Code_t","Name_t","String100_t"};

	protected String [] propStorepartslist={};
		protected String [] codeStorepartslist={"domaincode"};
		protected String [] relationStorepartslist={"parts:storeparts2parts:list:","storepartlist:storeparts2storepartlist:list:"};
		
		protected String [] addinventorytype={"table"};
		protected String [] addinventorycol={"objid","name","quantity","stockdate"};
		protected String [] addinventorycolcaption={"Id","Receipt NO","Quantity","Stock Date"};
		protected String [] addinventorysqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.INTEGER,DataType.DATE};
		protected String [] addinventorydatadomain={"Raw_t","Name_t","Int_t","Date_t"};
		protected String [] addinventorydisable={"yes,no,no,no"};
		protected String [] addinventorycolsearch={"#text_filter,#text_filter,#text_filter,"};

		public StorepartsDao(UriInfo uriInfo, HttpHeaders header) throws AuthenticationException{
			this.setObject("Storeparts");
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
				if(entity.toLowerCase().contains("<storeparts>")){
					tmp=entity.replace("<?", "");
					this.setMainxml("<?xml"+tmp);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Main XML="+this.getMainxml());
					}
				}else if(entity.toLowerCase().contains("<addinventory>")){
					this.setAddinventoryxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getAddinventoryxml());
					}
				}
			}
		}

		public Rows getAddinventoryRows(){
			TemplateTable tab=this.doSelectChild("addinventory", "addinventory2storeparts",this.getParentobjid(),addinventorycol,addinventorysqldatatype,this.AddinventoryFilter,addinventoryAccessFilter);
			String [] propAddinventorylist={};
			String [] codeAddinventorylist={};
			String [] relationAddinventorylist={};
			Rows rows=tu.getXMLRows(tab, "addinventory",codeAddinventorylist,propAddinventorylist,relationAddinventorylist,addinventorycolcaption,addinventorydatadomain,(QueryImpl)this);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(addinventorycolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(addinventorydisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(addinventorytype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getStorepartsSummaryRows(){
			TemplateTable tab=this.doSelect(summarycol,summarysqldatatype,this.StorepartsFilter,false,storepartsAccessFilter);
			ArrayList<String> chartcols=tu.getChartSelectColumns("Storeparts");
			ArrayList<String> moneycols=tu.getSummaryMoneyColsIndex(summarydatadomain);
			Rows rows=tu.getXMLSummaryRows(tab,summarycolcaption);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("charts",chartcols);
			userdata.add(data1);
			for(String chartcol:chartcols){
				ArrayList<String> datas= tu.getChartPropertyJSON("Storeparts", tab, chartcol);
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

		public Items getStorepartsForm(){
			TemplateTable tab=this.doSelect(maincol,mainsqldatatype,this.StorepartsFilter,true,storepartsAccessFilter);
			Items items=tu.getXMLForm(tab, "Storeparts",codeStorepartslist,propStorepartslist,relationStorepartslist,maincolcaption,maindatadomain,mainformfields,(QueryImpl)this);
			return items;
		}

		public Rows getStorepartsRows(){
			TemplateTable tab=this.doSelect(maincol,mainsqldatatype,null,false,storepartsAccessFilter);
			Rows rows=tu.getXMLRows(tab, "Storeparts",codeStorepartslist,propStorepartslist,relationStorepartslist,maincolcaption,maindatadomain,(QueryImpl)this);
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

		public Rows getStorepartsRowModified(){
			Rows rows=tu.getXMLRows(maindata, "Storeparts",codeStorepartslist,propStorepartslist,relationStorepartslist,maincolcaption,maindatadomain,(QueryImpl)this);
			return rows;
		}

		public Rows getStorepartsRowDeleted(){
			Rows rows;
			if(this.doDelete(childtabs)){
				rows=tu.getDeletedRows(this.getParentobjid());
			}else{
				rows=tu.getDeletedRows("-1");
			}
			return rows;
		}

		public boolean postStorepartsContainer() throws DaoException{
			if(!tu.isEmptyValue(this.getMainxml())){
				return(this.doInsert());
			}else{
				throw new DaoException("ERROR: Post unsuccessful! Probably your XML is missing parent entity or having error!", this.getClass().getName());
			}
		}

		public Rows getStorepartsByFilter(){
			String newfilter=" groupuser='"+this.getGroupuser()+"'";
			if(!tu.isEmptyValue(this.getFilters()) &&this.getFilters().toLowerCase().indexOf("storeparts2")<0){
				newfilter+=" and storeparts2"+this.getFilters();
				this.setParentobjid(this.getFilters().split("=")[1].replaceAll("'",""));
			}else{
				newfilter+=" and "+this.getFilters();
			}
			String sql= "select * from table_Storeparts where "+ newfilter;
			TemplateTable tab=tu.getResultSet(sql);
			Rows rows=tu.getXMLFilterRows(tab, "Storeparts",codeStorepartslist,propStorepartslist,relationStorepartslist,maincol,maincolcaption,maindatadomain,(QueryImpl)this);
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

		 public JSONObject getStorepartsRecordCount() throws JSONException{
			String newfilter=" groupuser='"+this.getGroupuser()+"'";
			if(!tu.isEmptyValue(this.getFilters()) &&this.getFilters().toLowerCase().indexOf("storeparts2")<0){
				newfilter+=" and storeparts2"+this.getFilters();
			}else{
				newfilter+=" and "+this.getFilters();
			}
			String sql= "select * from table_Storeparts where "+ newfilter;
			TemplateTable tab=tu.getResultSet(sql);
			JSONObject data=new JSONObject();
			data.put("table",this.getObject());
			data.put("rowcount",tab.getRowCount());
			return data;
			}
	}
