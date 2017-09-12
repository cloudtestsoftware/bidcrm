
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

	public class ImagelistDao extends ImagelistImpl {
		Map<String, Cookie> cookies; 
		Map<String,String> userdata;
		protected String []deletetabs={"imagelist,images,mastertemplate"};
		protected String []childtabs={"images,mastertemplate"};
		protected String []childtabnames={"Images,MasterTemplate"};
		
		protected String [] maintype={"table"};
		protected String [] maincol={"objid","name","ownername"};
		protected String [] maincolcaption={"Id","List Name","Owner Name"};
		protected String [] mainsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] maindatadomain={"Raw_t","Name_t","Name_t"};
		protected String [] maincolsearch={"#text_filter,#text_filter,#text_filter"};
		
		protected String [] maincoldisable={"yes,no,no"};
		private String [] mainformfields={"input","input","input"};
		
		protected String [] summarycol={"name"};
		protected String [] summarycolcaption={"Name"};
		protected String [] summarysqldatatype={DataType.VARCHAR};
		protected String [] summarydatadomain={"Name_t"};
		
		protected String [] reportcol={"objid","name","ownername"};
		protected String [] reportcolcaption={"Id","List Name","Owner Name"};
		protected String [] reportsqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] reportdatadomain={"Id_t","Name_t","Name_t"};
		
		protected String [] searchcol={"objid","name","ownername"};
		protected String [] searchcolcaption={"Id","List Name","Owner Name"};
		protected String [] searchcoltype={"integer","text","text"};
		protected String [] searchdatadomain={"Id_t","Name_t","Name_t"};

	protected String [] propImagelistlist={};
		protected String [] codeImagelistlist={};
		protected String [] relationImagelistlist={};
		
		protected String [] imagestype={"table"};
		protected String [] imagescol={"objid","name","attributename","ispublic","url"};
		protected String [] imagescolcaption={"Id","Image Name","Attribute Name","Is For World","URI Path"};
		protected String [] imagessqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR};
		protected String [] imagesdatadomain={"Raw_t","Name_t","Name_t","Boolean_t","String200_t"};
		protected String [] imagesdisable={"yes,no,no,no,yes"};
		protected String [] imagescolsearch={"#text_filter,#text_filter,#text_filter,#text_filter,#text_filter"};
		
		protected String [] mastertemplatetype={"table"};
		protected String [] mastertemplatecol={"objid","name","owner","owneremail","createdate","channelscode","isactive","isreminder","isforworld","islocked","lockedby","lockeddate","unlockedby","unlockeddate","url"};
		protected String [] mastertemplatecolcaption={"Id","Template Name","Owner Name","Owner Email","Creation Date","Channels","Is Active","Is For Reminder","Is For World","Is Locked","Locked By","Locked Date","Un Locked By","Un Locked Date","Template Path"};
		protected String [] mastertemplatesqldatatype={DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.VARCHAR,DataType.DATE,DataType.VARCHAR,DataType.DATE,DataType.VARCHAR};
		protected String [] mastertemplatedatadomain={"Raw_t","Name_t","Name_t","Email_t","Date_t","Code_t","Boolean_t","Boolean_t","Boolean_t","Boolean_t","Name_t","Date_t","Name_t","Date_t","String200_t"};
		protected String [] mastertemplatedisable={"yes,no,no,no,no,no,no,no,yes,yes,yes,yes,yes,yes,no"};
		protected String [] mastertemplatecolsearch={"#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter,#text_filter"};

		public ImagelistDao(UriInfo uriInfo, HttpHeaders header) throws AuthenticationException{
			this.setObject("Imagelist");
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
				if(entity.toLowerCase().contains("<imagelist>")){
					tmp=entity.replace("<?", "");
					this.setMainxml("<?xml"+tmp);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Main XML="+this.getMainxml());
					}
				}else if(entity.toLowerCase().contains("<images>")){
					this.setImagesxml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getImagesxml());
					}
				}else if(entity.toLowerCase().contains("<mastertemplate>")){
					this.setMastertemplatexml("<?xml"+entity);
					if(ACONST.GENERATE_LOG){
						logger.info("Setting Child XML="+this.getMastertemplatexml());
					}
				}
			}
		}

		public Rows getImagesRows(){
			TemplateTable tab=this.doSelectChild("images", "images2imagelist",this.getParentobjid(),imagescol,imagessqldatatype,this.ImagesFilter,imagesAccessFilter);
			String [] propImageslist={};
			String [] codeImageslist={};
			String [] relationImageslist={};
			Rows rows=tu.getXMLRows(tab, "images",codeImageslist,propImageslist,relationImageslist,imagescolcaption,imagesdatadomain,(QueryImpl)this);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(imagescolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(imagesdisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(imagestype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getMastertemplateRows(){
			TemplateTable tab=this.doSelectChild("mastertemplate", "mastertemplate2imagelist",this.getParentobjid(),mastertemplatecol,mastertemplatesqldatatype,this.MastertemplateFilter,mastertemplateAccessFilter);
			String [] propMastertemplatelist={};
			String [] codeMastertemplatelist={"channelscode"};
			String [] relationMastertemplatelist={};
			Rows rows=tu.getXMLRows(tab, "mastertemplate",codeMastertemplatelist,propMastertemplatelist,relationMastertemplatelist,mastertemplatecolcaption,mastertemplatedatadomain,(QueryImpl)this);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("filters",Arrays.asList(mastertemplatecolsearch));
			Userdata data2= new Userdata("disablecols",Arrays.asList(mastertemplatedisable));
			Userdata data3= new Userdata("tabletype",Arrays.asList(mastertemplatetype));
			userdata.add(data1);
			userdata.add(data2);
			userdata.add(data3);
			rows.setUserdata(userdata);
			return rows;
		}



		public Rows getImagelistSummaryRows(){
			TemplateTable tab=this.doSelect(summarycol,summarysqldatatype,this.ImagelistFilter,false,imagelistAccessFilter);
			ArrayList<String> chartcols=tu.getChartSelectColumns("Imagelist");
			ArrayList<String> moneycols=tu.getSummaryMoneyColsIndex(summarydatadomain);
			Rows rows=tu.getXMLSummaryRows(tab,summarycolcaption);
			ArrayList<Userdata> userdata=rows.getUserdata();
			Userdata data1= new Userdata("charts",chartcols);
			userdata.add(data1);
			for(String chartcol:chartcols){
				ArrayList<String> datas= tu.getChartPropertyJSON("Imagelist", tab, chartcol);
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

		public Items getImagelistForm(){
			TemplateTable tab=this.doSelect(maincol,mainsqldatatype,this.ImagelistFilter,true,imagelistAccessFilter);
			Items items=tu.getXMLForm(tab, "Imagelist",codeImagelistlist,propImagelistlist,relationImagelistlist,maincolcaption,maindatadomain,mainformfields,(QueryImpl)this);
			return items;
		}

		public Rows getImagelistRows(){
			TemplateTable tab=this.doSelect(maincol,mainsqldatatype,null,false,imagelistAccessFilter);
			Rows rows=tu.getXMLRows(tab, "Imagelist",codeImagelistlist,propImagelistlist,relationImagelistlist,maincolcaption,maindatadomain,(QueryImpl)this);
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

		public Rows getImagelistRowModified(){
			Rows rows=tu.getXMLRows(maindata, "Imagelist",codeImagelistlist,propImagelistlist,relationImagelistlist,maincolcaption,maindatadomain,(QueryImpl)this);
			return rows;
		}

		public Rows getImagelistRowDeleted(){
			Rows rows;
			if(this.doDelete(childtabs)){
				rows=tu.getDeletedRows(this.getParentobjid());
			}else{
				rows=tu.getDeletedRows("-1");
			}
			return rows;
		}

		public boolean postImagelistContainer() throws DaoException{
			if(!tu.isEmptyValue(this.getMainxml())){
				return(this.doInsert());
			}else{
				throw new DaoException("ERROR: Post unsuccessful! Probably your XML is missing parent entity or having error!", this.getClass().getName());
			}
		}

		public Rows getImagelistByFilter(){
			String newfilter=" groupuser='"+this.getGroupuser()+"'";
			if(!tu.isEmptyValue(this.getFilters()) &&this.getFilters().toLowerCase().indexOf("imagelist2")<0){
				newfilter+=" and imagelist2"+this.getFilters();
				this.setParentobjid(this.getFilters().split("=")[1].replaceAll("'",""));
			}else{
				newfilter+=" and "+this.getFilters();
			}
			String sql= "select * from table_Imagelist where "+ newfilter;
			TemplateTable tab=tu.getResultSet(sql);
			Rows rows=tu.getXMLFilterRows(tab, "Imagelist",codeImagelistlist,propImagelistlist,relationImagelistlist,maincol,maincolcaption,maindatadomain,(QueryImpl)this);
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

		 public JSONObject getImagelistRecordCount() throws JSONException{
			String newfilter=" groupuser='"+this.getGroupuser()+"'";
			if(!tu.isEmptyValue(this.getFilters()) &&this.getFilters().toLowerCase().indexOf("imagelist2")<0){
				newfilter+=" and imagelist2"+this.getFilters();
			}else{
				newfilter+=" and "+this.getFilters();
			}
			String sql= "select * from table_Imagelist where "+ newfilter;
			TemplateTable tab=tu.getResultSet(sql);
			JSONObject data=new JSONObject();
			data.put("table",this.getObject());
			data.put("rowcount",tab.getRowCount());
			return data;
			}
	}
