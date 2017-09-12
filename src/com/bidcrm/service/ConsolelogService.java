
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
		import com.bidcrm.dao.ConsolelogDao;
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

		//Use this URI resource with Base URL to access Consolelog
		@Path("/consolelog")
		public class ConsolelogService {
			static Log logger = LogFactory.getLog(ConsolelogService.class);

			// Get all contextual objects for this class
			@Context UriInfo uriInfo;
			@Context  HttpHeaders header;
			 
			// Get all rows for Consolelog
			@GET
			@Path("/rows")
			@Produces({"application/xml"})
			public Rows getConsolelogRows() {
				Rows rows = null;
				try {
					rows=new ConsolelogDao(uriInfo,header).getConsolelogRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					 logger.info( "Error calling getConsolelogRows()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			// Get Consolelog record by id
			@GET
			@Path("/{id}/record")
			@Produces({"application/xml"})
			public Rows getConsolelogRecord() {
				Rows rows = null;
				try {
					rows=new ConsolelogDao(uriInfo,header).getConsolelogRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					 logger.info( "Error calling getConsolelogRecord()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			// Get Consolelog form
			@GET
			@Path("/form")
			@Produces({"application/xml"})
			public Items getConsolelogForm() {
				Items items = null;
				try {
					items=new ConsolelogDao(uriInfo,header).getConsolelogForm();
				} catch (AuthenticationException e) {
					 items=new TemplateUtility().getFailedItemMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					 logger.info( "Error calling getConsolelogRecord()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return items;
			}
			 
			// Get all rows with filter for Consolelog
			@GET
			@Path("/filter")
			@Produces({"application/xml"})
			public Rows getConsolelogRowsByFilter() {
				Rows rows = null;
				try {
					rows=new ConsolelogDao(uriInfo,header).getConsolelogByFilter();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					 logger.info( "Error calling getConsolelogRowsByFilter()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			// Get record count with filter for Consolelog
			@GET
			@Path("/recordcount")
			@Produces({"application/json"})
			public Response getConsolelogRecordCount() throws JSONException {
				JSONObject rows = new JSONObject();
				try {
					rows=new ConsolelogDao(uriInfo,header).getConsolelogRecordCount();
				} catch (AuthenticationException e) {
					 rows.put("error",new TemplateUtility().getFailedMessage(e.getMessage()));
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getConsolelogRows()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return Response.status(200).entity(rows.toString()).build();
			}
			 
			// Get summary row against object ID for Consolelog
			@GET
			@Path("/{id}/summary")
			@Produces({"application/xml"})
			public Rows getConsolelogSummaryRows() {
				Rows rows = null;
				try {
					rows=new ConsolelogDao(uriInfo,header).getConsolelogSummaryRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getConsolelogRows()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			// Get Consolelog record deleted using id
			@GET
			@Path("/{id}/delete")
			@Produces({"application/xml"})
			public Rows getConsolelogRowDeleted() {
				Rows rows = null;
				try {
					rows=new ConsolelogDao(uriInfo,header).getConsolelogRowDeleted();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getConsolelogRowDeleted()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			 
			// Post all data changes in your grid for parent and child together
			@POST
			@Path("/post")
			@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
			@Produces({MediaType.APPLICATION_XML})
			public Rows postConsolelog(@Context UriInfo uriInfo,@Context  HttpHeaders header,@FormParam("body") String xml) {
				Rows rows = null;
				ConsolelogDao post;
				try {
					post=new ConsolelogDao(uriInfo,header);
					post.setPostXml(xml.trim());
					post.postConsolelogContainer();
					rows=post.getConsolelogRowModified();
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
			public Rows postFormDataConsolelog(@Context UriInfo uriInfo,@Context  HttpHeaders header,@FormDataParam("body") String xml) {
				Rows rows = null;
				ConsolelogDao post;
				try {
					post=new ConsolelogDao(uriInfo,header);
					post.setPostXml(xml.trim());
					if(post.postConsolelogContainer()){
						rows=post.getConsolelogRowModified();
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
