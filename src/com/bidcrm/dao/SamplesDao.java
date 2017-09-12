
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

	public class SamplesDao extends SamplesImpl {
		Map<String, Cookie> cookies; 
		Map<String,String> userdata;
		protected String []deletetabs={"samples,sampleorderitem,reordersamples"};
		protected String []childtabs={"samples,sampleorderitem,reordersamples"};
		protected String []childtabnames={"Samples Facts,SampleOrderItem,ReorderSamples"};
		
		protected String [] maintype={"table"};
		protected String [] maincol={"objid","samples2lead","samples2samplereserve","samples2parts","samples2campaign","name","ispublic","qntrequest","qntunused","status"};
		protected String [] maincolcaption={"Id","Lead","SampleReserve","Parts","Campaign","Item","Is Available To Public","Quantity Requested","Quantity UnUsed","Status"};
		protected String [] mainsqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.INTEGER,DataType.INTEGER,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.INTEGER,DataType.INTEGER,DataType.VARCHAR};
		protected String [] maindatadomain={"Raw_t","Raw_t","Raw_t","Raw_t","Raw_t","String200_t","Boolean_t","Int_t","Int_t","Status_t"};
		protected String [] maincolsearch={"#text_filter,#select_filter,#select_filter,#select_filter,#select_filter,#text_filter,#text_filter,#text_filter,#text_filter,#select_filter"};
		
		protected String [] maincoldisable={"yes,no,no,no,no,no,no,no,yes,yes"};
		private String [] mainformfields={"input","input","input","input","input","input","input","input","input","combo"};
		
		protected String [] summarycol={"name","description","price","qntordered","qntonhand"};
		protected String [] summarycolcaption={"Name","Description","Price","Quantity Ordered","Quantity On Hand"};
		protected String [] summarysqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.INTEGER,DataType.INTEGER};
		protected String [] summarydatadomain={"Name_t","String400_t","String100_t","Int_t","Int_t"};
		
		protected String [] reportcol={"objid","ispublic","qntrequest","qntunused","description","status","price","qntordered","qntonhand","name"};
		protected String [] reportcolcaption={"Id","Is Available To Public","Quantity Requested","Quantity UnUsed","Description","Status","Price","Quantity Ordered","Quantity On Hand","Item"};
		protected String [] reportsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.INTEGER,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.INTEGER,DataType.INTEGER,DataType.VARCHAR};
		protected String [] reportdatadomain={"Id_t","Boolean_t","Int_t","Int_t","String400_t","Status_t","String100_t","Int_t","Int_t","String200_t"};
		
		protected String [] searchcol={"objid","ispublic","qntrequest","qntunused","description","status","price","qntordered","qntonhand","name"};
		protected String [] searchcolcaption={"Id","Is Available To Public","Quantity Requested","Quantity UnUsed","Description","Status","Price","Quantity Ordered","Quantity On Hand","Item"};
		protected String [] searchcoltype={"integer","text","integer","integer","text","select","text","integer","integer","text"};
		protected String [] searchdatadomain={"Id_t","Boolean_t","Int_t","Int_t","String400_t","Status_t","String100_t","Int_t","Int_t","String200_t"};

	protected String [] propSampleslist={"status"};
		protected String [] codeSampleslist={};
		protected String [] relationSampleslist={"lead:samples2lead:list:","samplereserve:samples2samplereserve:list:","parts:samples2parts:list:","campaign:samples2campaign:list:"};
		
		protected String [] sampleorderitemtype={"table"};
		protected String [] sampleorderitemcol={"objid","name","description","price","qntordered","qntdispatched","itemamount","taxamount","totalamount"};
		protected String [] sampleorderitemcolcaption={"Id","Item","Description","Price","Quantity Ordered","Quantity Dispatched","Item Amount","Tax Amount","ItemAmount"};
		protected String [] sampleorderitemsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.NUMBER,DataType.INTEGER,DataType.INTEGER,DataType.NUMBER,DataType.NUMBER,DataType.NUMBER};
		protected String [] sampleorderitemdatadomain={"Raw_t","String200_t","String400_t","Money_t","Int_t","Int_t","Money_t","Money_t","Money_t"};
		protected String [] sampleorderitemdisable={"yes,no,yes,no,no,no,no,no,no"};
		protected String [] sampleorderitemcolsearch={"#text_filter,#text_filter,#text_filter,#numeric_filter,#text_filter,#text_filter,#numeric_filter,#numeric_filter,#numeric_filter"};
		
		protected String [] reordersamplestype={"table"};
		protected String [] reordersamplescol={"objid","reordersamples2samplereserve","reason","qntrequest","status","approvernote"};
		protected String [] reordersamplescolcaption={"Id","SampleReserve","Reorder Reason","Quantity Requested","Status","Approver Note"};
		protected String [] reordersamplessqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.VARCHAR,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] reordersamplesdatadomain={"Raw_t","Raw_t","String500_t","Int_t","Status_t","String500_t"};
		protected String [] reordersamplesdisable={"yes,no,no,no,yes,no"};
		protected String [] reordersamplescolsearch={"#text_filter,#select_filter,#text_filter,#text_filter,#select_filter,#text_filter"};

		public SamplesDao(UriInfo uriInfo, HttpHeaders header) throws AuthenticationException{
			this.setObject("Samples");
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
				if(entity.toLowerCase().contains("<samples>")){
					tmp=entity.replace("<?", "");
					this.setMainxml("<?xml"+tmp);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Main XML="+this.getMainxml());
					}
				}else if(entity.toLowerCase().contains("<sampleorderitem>")){
					this.setSampleorderitemxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getSampleorderitemxml());
					}
				}else if(entity.toLowerCase().contains("<reordersamples>")){
					this.setReordersamplesxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getReordersamplesxml());
					}
				}
			}
		}

		public Rows getSampleorderitemRows(){
			TemplateTable tab=this.doSelectChild("sampleorderitem", "sampleorderitem2samples",this.getParentobjid(),sampleorderitemcol,sampleorderitemsqldatatype,this.SampleorderitemFilter,sampleorderitemAccessFilter);
			String [] propSampleorderitemlist={};
			String [] codeSampleorderitemlist={};
			String [] relationSampleorderitemlist={};
			Rows rows=tu.getXMLRows(tab, "sampleorderitem",codeSampleorderitemlist,propSampleorderitemlist,relationSampleorderitemlist,sampleorderitemcolcaption,sampleorderitemdatadomain,(QueryImpl)this);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(sampleorderitemcolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(sampleorderitemdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(sampleorderitemtype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getReordersamplesRows(){
			TemplateTable tab=this.doSelectChild("reordersamples", "reordersamples2samples",this.getParentobjid(),reordersamplescol,reordersamplessqldatatype,this.ReordersamplesFilter,reordersamplesAccessFilter);
			String [] propReordersampleslist={"status"};
			String [] codeReordersampleslist={};
			String [] relationReordersampleslist={"samplereserve:reordersamples2samplereserve:list:"};
			Rows rows=tu.getXMLRows(tab, "reordersamples",codeReordersampleslist,propReordersampleslist,relationReordersampleslist,reordersamplescolcaption,reordersamplesdatadomain,(QueryImpl)this);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(reordersamplescolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(reordersamplesdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(reordersamplestype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getSamplesSummaryRows(){
			TemplateTable tab=this.doSelect(summarycol,summarysqldatatype,this.SamplesFilter,false,samplesAccessFilter);
			ArrayList<String> chartcols=tu.getChartSelectColumns("Samples");
			ArrayList<String> moneycols=tu.getSummaryMoneyColsIndex(summarydatadomain);
			Rows rows=tu.getXMLSummaryRows(tab,summarycolcaption);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("charts",chartcols);
			userdata.add(data1);
			for(String chartcol:chartcols){
				ArrayList<String> datas= tu.getChartPropertyJSON("Samples", tab, chartcol);
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

		public Items getSamplesForm(){
			TemplateTable tab=this.doSelect(maincol,mainsqldatatype,this.SamplesFilter,true,samplesAccessFilter);
			Items items=tu.getXMLForm(tab, "Samples",codeSampleslist,propSampleslist,relationSampleslist,maincolcaption,maindatadomain,mainformfields,(QueryImpl)this);
			return items;
		}

		public Rows getSamplesRows(){
			TemplateTable tab=this.doSelect(maincol,mainsqldatatype,null,false,samplesAccessFilter);
			Rows rows=tu.getXMLRows(tab, "Samples",codeSampleslist,propSampleslist,relationSampleslist,maincolcaption,maindatadomain,(QueryImpl)this);
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

		public Rows getSamplesRowModified(){
			Rows rows=tu.getXMLRows(maindata, "Samples",codeSampleslist,propSampleslist,relationSampleslist,maincolcaption,maindatadomain,(QueryImpl)this);
			return rows;
		}

		public Rows getSamplesRowDeleted(){
			Rows rows;
			if(this.doDelete(childtabs)){
				rows=tu.getDeletedRows(this.getParentobjid());
			}else{
				rows=tu.getDeletedRows("-1");
			}
			return rows;
		}

		public boolean postSamplesContainer() throws DaoException{
			if(!tu.isEmptyValue(this.getMainxml())){
				return(this.doInsert());
			}else{
				throw new DaoException("ERROR: Post unsuccessful! Probably your XML is missing parent entity or having error!", this.getClass().getName());
			}
		}

		public Rows getSamplesByFilter(){
			String newfilter=" groupuser='"+this.getGroupuser()+"'";
			if(!tu.isEmptyValue(this.getFilters()) &&this.getFilters().toLowerCase().indexOf("samples2")<0){
				newfilter+=" and samples2"+this.getFilters();
				this.setParentobjid(this.getFilters().split("=")[1].replaceAll("'",""));
			}else{
				newfilter+=" and "+this.getFilters();
			}
			String sql= "select * from table_Samples where "+ newfilter;
			TemplateTable tab=tu.getResultSet(sql);
			Rows rows=tu.getXMLFilterRows(tab, "Samples",codeSampleslist,propSampleslist,relationSampleslist,maincol,maincolcaption,maindatadomain,(QueryImpl)this);
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

		 public JSONObject getSamplesRecordCount() throws JSONException{
			String newfilter=" groupuser='"+this.getGroupuser()+"'";
			if(!tu.isEmptyValue(this.getFilters()) &&this.getFilters().toLowerCase().indexOf("samples2")<0){
				newfilter+=" and samples2"+this.getFilters();
			}else{
				newfilter+=" and "+this.getFilters();
			}
			String sql= "select * from table_Samples where "+ newfilter;
			TemplateTable tab=tu.getResultSet(sql);
			JSONObject data=new JSONObject();
			data.put("table",this.getObject());
			data.put("rowcount",tab.getRowCount());
			return data;
			}
	}
