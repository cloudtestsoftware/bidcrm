
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
		import com.bidcrm.dao.BillingDao;
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

		//Use this URI resource with Base URL to access Billing
		@Path("/billing")
		public class BillingService {
			static Log logger = LogFactory.getLog(BillingService.class);

			// Get all contextual objects for this class
			@Context UriInfo uriInfo;
			@Context  HttpHeaders header;
			 
			// Get all rows for Billing
			@GET
			@Path("/rows")
			@Produces({"application/xml"})
			public Rows getBillingRows() {
				Rows rows = null;
				try {
					rows=new BillingDao(uriInfo,header).getBillingRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					 logger.info( "Error calling getBillingRows()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			// Get Billing record by id
			@GET
			@Path("/{id}/record")
			@Produces({"application/xml"})
			public Rows getBillingRecord() {
				Rows rows = null;
				try {
					rows=new BillingDao(uriInfo,header).getBillingRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					 logger.info( "Error calling getBillingRecord()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			// Get Billing form
			@GET
			@Path("/form")
			@Produces({"application/xml"})
			public Items getBillingForm() {
				Items items = null;
				try {
					items=new BillingDao(uriInfo,header).getBillingForm();
				} catch (AuthenticationException e) {
					 items=new TemplateUtility().getFailedItemMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					 logger.info( "Error calling getBillingRecord()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return items;
			}
			 
			// Get all rows with filter for Billing
			@GET
			@Path("/filter")
			@Produces({"application/xml"})
			public Rows getBillingRowsByFilter() {
				Rows rows = null;
				try {
					rows=new BillingDao(uriInfo,header).getBillingByFilter();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					 logger.info( "Error calling getBillingRowsByFilter()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			// Get record count with filter for Billing
			@GET
			@Path("/recordcount")
			@Produces({"application/json"})
			public Response getBillingRecordCount() throws JSONException {
				JSONObject rows = new JSONObject();
				try {
					rows=new BillingDao(uriInfo,header).getBillingRecordCount();
				} catch (AuthenticationException e) {
					 rows.put("error",new TemplateUtility().getFailedMessage(e.getMessage()));
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getBillingRows()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return Response.status(200).entity(rows.toString()).build();
			}
			 
			// Get summary row against object ID for Billing
			@GET
			@Path("/{id}/summary")
			@Produces({"application/xml"})
			public Rows getBillingSummaryRows() {
				Rows rows = null;
				try {
					rows=new BillingDao(uriInfo,header).getBillingSummaryRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getBillingRows()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			// Get Billing record deleted using id
			@GET
			@Path("/{id}/delete")
			@Produces({"application/xml"})
			public Rows getBillingRowDeleted() {
				Rows rows = null;
				try {
					rows=new BillingDao(uriInfo,header).getBillingRowDeleted();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getBillingRowDeleted()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			 
			// Post all data changes in your grid for parent and child together
			@POST
			@Path("/post")
			@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
			@Produces({MediaType.APPLICATION_XML})
			public Rows postBilling(@Context UriInfo uriInfo,@Context  HttpHeaders header,@FormParam("body") String xml) {
				Rows rows = null;
				BillingDao post;
				try {
					post=new BillingDao(uriInfo,header);
					post.setPostXml(xml.trim());
					post.postBillingContainer();
					rows=post.getBillingRowModified();
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
			public Rows postFormDataBilling(@Context UriInfo uriInfo,@Context  HttpHeaders header,@FormDataParam("body") String xml) {
				Rows rows = null;
				BillingDao post;
				try {
					post=new BillingDao(uriInfo,header);
					post.setPostXml(xml.trim());
					if(post.postBillingContainer()){
						rows=post.getBillingRowModified();
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
