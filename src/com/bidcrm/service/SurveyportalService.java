
		package com.bidcrm.service;

		import javax.ws.rs.GET;
		import javax.ws.rs.Consumes;
		import javax.ws.rs.FormParam;
		import com.sun.jersey.multipart.FormDataParam;
		import javax.ws.rs.POST;
		import javax.ws.rs.Path;
		import javax.ws.rs.Produces;
		import javax.ws.rs.core.Context;
		import javax.ws.rs.core.HttpHeaders;
		import javax.ws.rs.core.MediaType;
		import javax.ws.rs.core.UriInfo;
		import org.json.JSONException;
		import org.json.JSONObject;
		import javax.ws.rs.core.Response;
		import org.apache.commons.logging.Log;
		import org.apache.commons.logging.LogFactory;
		import cms.service.dhtmlx.Rows;
		import cms.service.dhtmlx.forms.Items;
		import cms.service.template.TemplateUtility;
		import cms.service.exceptions.DaoException;
		import cms.service.exceptions.AuthenticationException;
		import com.bidcrm.dao.SurveyportalDao;
		/*
		*  URL Parameters:
		*  
		*  Mandatory : loginname, groupuser, token i.e  {Base URL}/project/{id}/estimation?loginname=abc@example.com&groupuser=cdf@eaxmple.com&token=2343434334444
		*  
		*  Optional : id= parent objid for any child url i.e {Base URL}/project/{id}/estimation?loginname=abc@example.com&groupuser=cdf@eaxmple.com&token=2343434334444
		*  
		*  Optional: page, pagesize for search i.e {Base URL}/project/{id}/estimation?loginname=abc@example.com&groupuser=cdf@eaxmple.com&token=2343434334444&page=1&pagesize=50
		*  
		*  Optional: name for filter i.e {Base URL}/project/{id}/estimation?loginname=abc@example.com&groupuser=cdf@eaxmple.com&token=2343434334444&page=1&pagesize=50&name=Alex
		*  
		*  Optional: fields=column1,column2,...  i.e {Base URL}/project/{id}/estimation?loginname=abc@example.com&groupuser=cdf@eaxmple.com&
		*  				token=2343434334444&page=1&pagesize=50&name=Alex&fields=name,title,projectcode...
		*  
		*/

		//Use this URI resource with Base URL to access Surveyportal
		@Path("/surveyportal")
		public class SurveyportalService {
			static Log logger = LogFactory.getLog(SurveyportalService.class);

			// Get all contextual objects for this class
			@Context UriInfo uriInfo;
			@Context  HttpHeaders header;
			 
			// Get all rows for Surveyportal
			@GET
			@Path("/rows")
			@Produces({"application/xml"})
			public Rows getSurveyportalRows() {
				Rows rows = null;
				try {
					rows=new SurveyportalDao(uriInfo,header).getSurveyportalRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					 logger.info( "Error calling getSurveyportalRows()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			// Get Surveyportal record by id
			@GET
			@Path("/{id}/record")
			@Produces({"application/xml"})
			public Rows getSurveyportalRecord() {
				Rows rows = null;
				try {
					rows=new SurveyportalDao(uriInfo,header).getSurveyportalRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					 logger.info( "Error calling getSurveyportalRecord()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			// Get Surveyportal form
			@GET
			@Path("/form")
			@Produces({"application/xml"})
			public Items getSurveyportalForm() {
				Items items = null;
				try {
					items=new SurveyportalDao(uriInfo,header).getSurveyportalForm();
				} catch (AuthenticationException e) {
					 items=new TemplateUtility().getFailedItemMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					 logger.info( "Error calling getSurveyportalRecord()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return items;
			}
			 
			// Get all rows with filter for Surveyportal
			@GET
			@Path("/filter")
			@Produces({"application/xml"})
			public Rows getSurveyportalRowsByFilter() {
				Rows rows = null;
				try {
					rows=new SurveyportalDao(uriInfo,header).getSurveyportalByFilter();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					 logger.info( "Error calling getSurveyportalRowsByFilter()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			// Get record count with filter for Surveyportal
			@GET
			@Path("/recordcount")
			@Produces({"application/json"})
			public Response getSurveyportalRecordCount() throws JSONException {
				JSONObject rows = new JSONObject();
				try {
					rows=new SurveyportalDao(uriInfo,header).getSurveyportalRecordCount();
				} catch (AuthenticationException e) {
					 rows.put("error",new TemplateUtility().getFailedMessage(e.getMessage()));
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getSurveyportalRows()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return Response.status(200).entity(rows.toString()).build();
			}
			 
			// Get summary row against object ID for Surveyportal
			@GET
			@Path("/{id}/summary")
			@Produces({"application/xml"})
			public Rows getSurveyportalSummaryRows() {
				Rows rows = null;
				try {
					rows=new SurveyportalDao(uriInfo,header).getSurveyportalSummaryRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getSurveyportalRows()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			// Get Surveyportal record deleted using id
			@GET
			@Path("/{id}/delete")
			@Produces({"application/xml"})
			public Rows getSurveyportalRowDeleted() {
				Rows rows = null;
				try {
					rows=new SurveyportalDao(uriInfo,header).getSurveyportalRowDeleted();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getSurveyportalRowDeleted()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			 
			// Post all data changes in your grid for parent and child together
			@POST
			@Path("/post")
			@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
			@Produces({MediaType.APPLICATION_XML})
			public Rows postSurveyportal(@Context UriInfo uriInfo,@Context  HttpHeaders header,@FormParam("body") String xml) {
				Rows rows = null;
				SurveyportalDao post;
				try {
					post=new SurveyportalDao(uriInfo,header);
					post.setPostXml(xml.trim());
					post.postSurveyportalContainer();
					rows=post.getSurveyportalRowModified();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (DaoException d) {
					 d.printStackTrace();
				}
				return rows;
			}

			// Post all data changes in using form
			@POST
			@Path("/formdata")
			@Consumes(MediaType.MULTIPART_FORM_DATA)
			@Produces({MediaType.APPLICATION_XML})
			public Rows postFormDataSurveyportal(@Context UriInfo uriInfo,@Context  HttpHeaders header,@FormDataParam("body") String xml) {
				Rows rows = null;
				SurveyportalDao post;
				try {
					post=new SurveyportalDao(uriInfo,header);
					post.setPostXml(xml.trim());
					if(post.postSurveyportalContainer()){
						rows=post.getSurveyportalRowModified();
					}else{
					 rows=new TemplateUtility().getFailedMessage("{\"rowcount\":0,\"errormsg\":\"Record could not be saved!\"}");
					}
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (DaoException d) {
					 d.printStackTrace();
				}
				return rows;
			}
		}
