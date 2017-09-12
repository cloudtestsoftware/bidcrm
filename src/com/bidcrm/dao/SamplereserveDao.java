
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

	public class SamplereserveDao extends SamplereserveImpl {
		Map<String, Cookie> cookies; 
		Map<String,String> userdata;
		protected String []deletetabs={"samplereserve,samples,reordersamples"};
		protected String []childtabs={"samples,reordersamples"};
		protected String []childtabnames={"Samples,ReorderSamples"};
		
		protected String [] maintype={"table"};
		protected String [] maincol={"objid","samplereserve2store","samplereserve2messagequeue","samplereserve2campaign","name","requestedby","requesteremail","requesterphone","requestdate","requireddate","approveddate","approvedby","storemanager","agentemail","agentphone","requitionid","status"};
		protected String [] maincolcaption={"Id","Store","MessageQueue","Campaign","Title","Requested By","RequesterEmail","Requester Phone","Request Date","Required Date","Approved Date","Approved By","Store Manager","Store Agent Email","Store Agent Phone","Requition Id","Status"};
		protected String [] mainsqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.INTEGER,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.DATE,DataType.DATE,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] maindatadomain={"Raw_t","Raw_t","Raw_t","Raw_t","Name_t","Name_t","Email_t","Phone_t","Date_t","Date_t","Date_t","Name_t","Name_t","Email_t","Phone_t","String50_t","Type_t"};
		protected String [] maincolsearch={"#text_filter,#select_filter,#select_filter,#select_filter,#text_filter,#text_filter,#text_filter,#text_filter,,,,#text_filter,#text_filter,#text_filter,#text_filter,,#select_filter"};
		
		protected String [] maincoldisable={"yes,no,no,no,no,no,no,no,no,no,no,no,no,no,no,no,no"};
		private String [] mainformfields={"input","input","input","input","input","input","input","input","calendar","calendar","calendar","input","input","input","input","input","combo"};
		
		protected String [] summarycol={"name"};
		protected String [] summarycolcaption={"Name"};
		protected String [] summarysqldatatype={DataType.VARCHAR};
		protected String [] summarydatadomain={"Name_t"};
		
		protected String [] reportcol={"objid","name","requestedby","requesteremail","requesterphone","requestdate","requireddate","approveddate","agentemail","agentphone","approvedby","storemanager","status"};
		protected String [] reportcolcaption={"Id","Title","Requested By","RequesterEmail","Requester Phone","Request Date","Required Date","Approved Date","Store Agent Email","Store Agent Phone","Approved By","Store Manager","Status"};
		protected String [] reportsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.DATE,DataType.DATE,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] reportdatadomain={"Id_t","Name_t","Name_t","Email_t","Phone_t","Date_t","Date_t","Date_t","Email_t","Phone_t","Name_t","Name_t","Type_t"};
		
		protected String [] searchcol={"objid","name","requestedby","requesteremail","requesterphone","agentemail","agentphone","approvedby","storemanager","status"};
		protected String [] searchcolcaption={"Id","Title","Requested By","RequesterEmail","Requester Phone","Store Agent Email","Store Agent Phone","Approved By","Store Manager","Status"};
		protected String [] searchcoltype={"integer","text","text","text","text","text","text","text","text","select"};
		protected String [] searchdatadomain={"Id_t","Name_t","Name_t","Email_t","Phone_t","Email_t","Phone_t","Name_t","Name_t","Type_t"};

	protected String [] propSamplereservelist={"status"};
		protected String [] codeSamplereservelist={};
		protected String [] relationSamplereservelist={"store:samplereserve2store:list:","messagequeue:samplereserve2messagequeue:list:","campaign:samplereserve2campaign:list:"};
		
		protected String [] samplestype={"table"};
		protected String [] samplescol={"objid","samples2parts","name","ispublic","qntrequest","qntunused","description","status","price","qntordered","qntonhand"};
		protected String [] samplescolcaption={"Id","Parts","Item","Is Available To Public","Quantity Requested","Quantity UnUsed","Description","Status","Price","Quantity Ordered","Quantity On Hand"};
		protected String [] samplessqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.INTEGER,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.INTEGER,DataType.INTEGER};
		protected String [] samplesdatadomain={"Raw_t","Raw_t","String200_t","Boolean_t","Int_t","Int_t","String400_t","Status_t","String100_t","Int_t","Int_t"};
		protected String [] samplesdisable={"yes,no,no,no,no,yes,yes,yes,no,yes,yes"};
		protected String [] samplescolsearch={"#text_filter,#select_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#select_filter,#text_filter,#text_filter,#text_filter"};
		
		protected String [] reordersamplestype={"table"};
		protected String [] reordersamplescol={"objid","reordersamples2samples","reason","qntrequest","status","approvernote"};
		protected String [] reordersamplescolcaption={"Id","Samples","Reorder Reason","Quantity Requested","Status","Approver Note"};
		protected String [] reordersamplessqldatatype={DataType.VARCHAR,DataType.INTEGER,DataType.VARCHAR,DataType.INTEGER,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] reordersamplesdatadomain={"Raw_t","Raw_t","String500_t","Int_t","Status_t","String500_t"};
		protected String [] reordersamplesdisable={"yes,no,no,no,yes,no"};
		protected String [] reordersamplescolsearch={"#text_filter,#select_filter,#text_filter,#text_filter,#select_filter,#text_filter"};

		public SamplereserveDao(UriInfo uriInfo, HttpHeaders header) throws AuthenticationException{
			this.setObject("Samplereserve");
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
				if(entity.toLowerCase().contains("<samplereserve>")){
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
				}else if(entity.toLowerCase().contains("<reordersamples>")){
					this.setReordersamplesxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getReordersamplesxml());
					}
				}
			}
		}

		public Rows getSamplesRows(){
			TemplateTable tab=this.doSelectChild("samples", "samples2samplereserve",this.getParentobjid(),samplescol,samplessqldatatype,this.SamplesFilter,samplesAccessFilter);
			String [] propSampleslist={"status"};
			String [] codeSampleslist={};
			String [] relationSampleslist={"parts:samples2parts:list:"};
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



		public Rows getReordersamplesRows(){
			TemplateTable tab=this.doSelectChild("reordersamples", "reordersamples2samplereserve",this.getParentobjid(),reordersamplescol,reordersamplessqldatatype,this.ReordersamplesFilter,reordersamplesAccessFilter);
			String [] propReordersampleslist={"status"};
			String [] codeReordersampleslist={};
			String [] relationReordersampleslist={"samples:reordersamples2samples:list:"};
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



		public Rows getSamplereserveSummaryRows(){
			TemplateTable tab=this.doSelect(summarycol,summarysqldatatype,this.SamplereserveFilter,false,samplereserveAccessFilter);
			ArrayList<String> chartcols=tu.getChartSelectColumns("Samplereserve");
			ArrayList<String> moneycols=tu.getSummaryMoneyColsIndex(summarydatadomain);
			Rows rows=tu.getXMLSummaryRows(tab,summarycolcaption);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("charts",chartcols);
			userdata.add(data1);
			for(String chartcol:chartcols){
				ArrayList<String> datas= tu.getChartPropertyJSON("Samplereserve", tab, chartcol);
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

		public Items getSamplereserveForm(){
			TemplateTable tab=this.doSelect(maincol,mainsqldatatype,this.SamplereserveFilter,true,samplereserveAccessFilter);
			Items items=tu.getXMLForm(tab, "Samplereserve",codeSamplereservelist,propSamplereservelist,relationSamplereservelist,maincolcaption,maindatadomain,mainformfields,(QueryImpl)this);
			return items;
		}

		public Rows getSamplereserveRows(){
			TemplateTable tab=this.doSelect(maincol,mainsqldatatype,null,false,samplereserveAccessFilter);
			Rows rows=tu.getXMLRows(tab, "Samplereserve",codeSamplereservelist,propSamplereservelist,relationSamplereservelist,maincolcaption,maindatadomain,(QueryImpl)this);
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

		public Rows getSamplereserveRowModified(){
			Rows rows=tu.getXMLRows(maindata, "Samplereserve",codeSamplereservelist,propSamplereservelist,relationSamplereservelist,maincolcaption,maindatadomain,(QueryImpl)this);
			return rows;
		}

		public Rows getSamplereserveRowDeleted(){
			Rows rows;
			if(this.doDelete(childtabs)){
				rows=tu.getDeletedRows(this.getParentobjid());
			}else{
				rows=tu.getDeletedRows("-1");
			}
			return rows;
		}

		public boolean postSamplereserveContainer() throws DaoException{
			if(!tu.isEmptyValue(this.getMainxml())){
				return(this.doInsert());
			}else{
				throw new DaoException("ERROR: Post unsuccessful! Probably your XML is missing parent entity or having error!", this.getClass().getName());
			}
		}

		public Rows getSamplereserveByFilter(){
			String newfilter=" groupuser='"+this.getGroupuser()+"'";
			if(!tu.isEmptyValue(this.getFilters()) &&this.getFilters().toLowerCase().indexOf("samplereserve2")<0){
				newfilter+=" and samplereserve2"+this.getFilters();
				this.setParentobjid(this.getFilters().split("=")[1].replaceAll("'",""));
			}else{
				newfilter+=" and "+this.getFilters();
			}
			String sql= "select * from table_Samplereserve where "+ newfilter;
			TemplateTable tab=tu.getResultSet(sql);
			Rows rows=tu.getXMLFilterRows(tab, "Samplereserve",codeSamplereservelist,propSamplereservelist,relationSamplereservelist,maincol,maincolcaption,maindatadomain,(QueryImpl)this);
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

		 public JSONObject getSamplereserveRecordCount() throws JSONException{
			String newfilter=" groupuser='"+this.getGroupuser()+"'";
			if(!tu.isEmptyValue(this.getFilters()) &&this.getFilters().toLowerCase().indexOf("samplereserve2")<0){
				newfilter+=" and samplereserve2"+this.getFilters();
			}else{
				newfilter+=" and "+this.getFilters();
			}
			String sql= "select * from table_Samplereserve where "+ newfilter;
			TemplateTable tab=tu.getResultSet(sql);
			JSONObject data=new JSONObject();
			data.put("table",this.getObject());
			data.put("rowcount",tab.getRowCount());
			return data;
			}
	}
