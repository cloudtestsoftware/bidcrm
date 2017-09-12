
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
		import com.bidcrm.dao.SalesrepDao;
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

		//Use this URI resource with Base URL to access Salesrep
		@Path("/salesrep")
		public class SalesrepService {
			static Log logger = LogFactory.getLog(SalesrepService.class);

			// Get all contextual objects for this class
			@Context UriInfo uriInfo;
			@Context  HttpHeaders header;
			 
			// Get all rows for Salesrep
			@GET
			@Path("/rows")
			@Produces({"application/xml"})
			public Rows getSalesrepRows() {
				Rows rows = null;
				try {
					rows=new SalesrepDao(uriInfo,header).getSalesrepRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					 logger.info( "Error calling getSalesrepRows()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			// Get Salesrep record by id
			@GET
			@Path("/{id}/record")
			@Produces({"application/xml"})
			public Rows getSalesrepRecord() {
				Rows rows = null;
				try {
					rows=new SalesrepDao(uriInfo,header).getSalesrepRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					 logger.info( "Error calling getSalesrepRecord()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			// Get Salesrep form
			@GET
			@Path("/form")
			@Produces({"application/xml"})
			public Items getSalesrepForm() {
				Items items = null;
				try {
					items=new SalesrepDao(uriInfo,header).getSalesrepForm();
				} catch (AuthenticationException e) {
					 items=new TemplateUtility().getFailedItemMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					 logger.info( "Error calling getSalesrepRecord()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return items;
			}
			 
			// Get all rows with filter for Salesrep
			@GET
			@Path("/filter")
			@Produces({"application/xml"})
			public Rows getSalesrepRowsByFilter() {
				Rows rows = null;
				try {
					rows=new SalesrepDao(uriInfo,header).getSalesrepByFilter();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					 logger.info( "Error calling getSalesrepRowsByFilter()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			// Get record count with filter for Salesrep
			@GET
			@Path("/recordcount")
			@Produces({"application/json"})
			public Response getSalesrepRecordCount() throws JSONException {
				JSONObject rows = new JSONObject();
				try {
					rows=new SalesrepDao(uriInfo,header).getSalesrepRecordCount();
				} catch (AuthenticationException e) {
					 rows.put("error",new TemplateUtility().getFailedMessage(e.getMessage()));
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getSalesrepRows()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return Response.status(200).entity(rows.toString()).build();
			}
			 
			// Get summary row against object ID for Salesrep
			@GET
			@Path("/{id}/summary")
			@Produces({"application/xml"})
			public Rows getSalesrepSummaryRows() {
				Rows rows = null;
				try {
					rows=new SalesrepDao(uriInfo,header).getSalesrepSummaryRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getSalesrepRows()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			// Post all data changes in your grid for parent and child together
			@POST
			@Path("/post")
			@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
			@Produces({MediaType.APPLICATION_XML})
			public Rows postSalesrep(@Context UriInfo uriInfo,@Context  HttpHeaders header,@FormParam("body") String xml) {
				Rows rows = null;
				SalesrepDao post;
				try {
					post=new SalesrepDao(uriInfo,header);
					post.setPostXml(xml.trim());
					post.postSalesrepContainer();
					rows=post.getSalesrepRowModified();
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
			public Rows postFormDataSalesrep(@Context UriInfo uriInfo,@Context  HttpHeaders header,@FormDataParam("body") String xml) {
				Rows rows = null;
				SalesrepDao post;
				try {
					post=new SalesrepDao(uriInfo,header);
					post.setPostXml(xml.trim());
					if(post.postSalesrepContainer()){
						rows=post.getSalesrepRowModified();
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
