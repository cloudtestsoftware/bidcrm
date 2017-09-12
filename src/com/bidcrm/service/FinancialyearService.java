
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
		import com.bidcrm.dao.FinancialyearDao;
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

		//Use this URI resource with Base URL to access Financialyear
		@Path("/financialyear")
		public class FinancialyearService {
			static Log logger = LogFactory.getLog(FinancialyearService.class);

			// Get all contextual objects for this class
			@Context UriInfo uriInfo;
			@Context  HttpHeaders header;
			 
			// Get all rows for Financialyear
			@GET
			@Path("/rows")
			@Produces({"application/xml"})
			public Rows getFinancialyearRows() {
				Rows rows = null;
				try {
					rows=new FinancialyearDao(uriInfo,header).getFinancialyearRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					 logger.info( "Error calling getFinancialyearRows()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			// Get Financialyear record by id
			@GET
			@Path("/{id}/record")
			@Produces({"application/xml"})
			public Rows getFinancialyearRecord() {
				Rows rows = null;
				try {
					rows=new FinancialyearDao(uriInfo,header).getFinancialyearRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					 logger.info( "Error calling getFinancialyearRecord()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			// Get Financialyear form
			@GET
			@Path("/form")
			@Produces({"application/xml"})
			public Items getFinancialyearForm() {
				Items items = null;
				try {
					items=new FinancialyearDao(uriInfo,header).getFinancialyearForm();
				} catch (AuthenticationException e) {
					 items=new TemplateUtility().getFailedItemMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					 logger.info( "Error calling getFinancialyearRecord()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return items;
			}
			 
			// Get all rows with filter for Financialyear
			@GET
			@Path("/filter")
			@Produces({"application/xml"})
			public Rows getFinancialyearRowsByFilter() {
				Rows rows = null;
				try {
					rows=new FinancialyearDao(uriInfo,header).getFinancialyearByFilter();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					 logger.info( "Error calling getFinancialyearRowsByFilter()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			// Get record count with filter for Financialyear
			@GET
			@Path("/recordcount")
			@Produces({"application/json"})
			public Response getFinancialyearRecordCount() throws JSONException {
				JSONObject rows = new JSONObject();
				try {
					rows=new FinancialyearDao(uriInfo,header).getFinancialyearRecordCount();
				} catch (AuthenticationException e) {
					 rows.put("error",new TemplateUtility().getFailedMessage(e.getMessage()));
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getFinancialyearRows()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return Response.status(200).entity(rows.toString()).build();
			}
			 
			// Get summary row against object ID for Financialyear
			@GET
			@Path("/{id}/summary")
			@Produces({"application/xml"})
			public Rows getFinancialyearSummaryRows() {
				Rows rows = null;
				try {
					rows=new FinancialyearDao(uriInfo,header).getFinancialyearSummaryRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getFinancialyearRows()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			// Get Financialyear record deleted using id
			@GET
			@Path("/{id}/delete")
			@Produces({"application/xml"})
			public Rows getFinancialyearRowDeleted() {
				Rows rows = null;
				try {
					rows=new FinancialyearDao(uriInfo,header).getFinancialyearRowDeleted();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getFinancialyearRowDeleted()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			 
			// Get all Campaigncalendar rows against object ID for Financialyear
			@GET
			@Path("/{id}/campaigncalendar")
			@Produces({"application/xml"})
			public Rows getCampaigncalendarRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new FinancialyearDao(uriInfo,header).getCampaigncalendarRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getCampaigncalendarRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Get all Eventcalendar rows against object ID for Financialyear
			@GET
			@Path("/{id}/eventcalendar")
			@Produces({"application/xml"})
			public Rows getEventcalendarRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new FinancialyearDao(uriInfo,header).getEventcalendarRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getEventcalendarRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Get all Travelcalendar rows against object ID for Financialyear
			@GET
			@Path("/{id}/travelcalendar")
			@Produces({"application/xml"})
			public Rows getTravelcalendarRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new FinancialyearDao(uriInfo,header).getTravelcalendarRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getTravelcalendarRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Post all data changes in your grid for parent and child together
			@POST
			@Path("/post")
			@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
			@Produces({MediaType.APPLICATION_XML})
			public Rows postFinancialyear(@Context UriInfo uriInfo,@Context  HttpHeaders header,@FormParam("body") String xml) {
				Rows rows = null;
				FinancialyearDao post;
				try {
					post=new FinancialyearDao(uriInfo,header);
					post.setPostXml(xml.trim());
					post.postFinancialyearContainer();
					rows=post.getFinancialyearRowModified();
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
			public Rows postFormDataFinancialyear(@Context UriInfo uriInfo,@Context  HttpHeaders header,@FormDataParam("body") String xml) {
				Rows rows = null;
				FinancialyearDao post;
				try {
					post=new FinancialyearDao(uriInfo,header);
					post.setPostXml(xml.trim());
					if(post.postFinancialyearContainer()){
						rows=post.getFinancialyearRowModified();
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
