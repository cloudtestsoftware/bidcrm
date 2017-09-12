
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
		import com.bidcrm.dao.RfqDao;
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

		//Use this URI resource with Base URL to access Rfq
		@Path("/rfq")
		public class RfqService {
			static Log logger = LogFactory.getLog(RfqService.class);

			// Get all contextual objects for this class
			@Context UriInfo uriInfo;
			@Context  HttpHeaders header;
			 
			// Get all rows for Rfq
			@GET
			@Path("/rows")
			@Produces({"application/xml"})
			public Rows getRfqRows() {
				Rows rows = null;
				try {
					rows=new RfqDao(uriInfo,header).getRfqRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					 logger.info( "Error calling getRfqRows()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			// Get Rfq record by id
			@GET
			@Path("/{id}/record")
			@Produces({"application/xml"})
			public Rows getRfqRecord() {
				Rows rows = null;
				try {
					rows=new RfqDao(uriInfo,header).getRfqRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					 logger.info( "Error calling getRfqRecord()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			// Get Rfq form
			@GET
			@Path("/form")
			@Produces({"application/xml"})
			public Items getRfqForm() {
				Items items = null;
				try {
					items=new RfqDao(uriInfo,header).getRfqForm();
				} catch (AuthenticationException e) {
					 items=new TemplateUtility().getFailedItemMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					 logger.info( "Error calling getRfqRecord()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return items;
			}
			 
			// Get all rows with filter for Rfq
			@GET
			@Path("/filter")
			@Produces({"application/xml"})
			public Rows getRfqRowsByFilter() {
				Rows rows = null;
				try {
					rows=new RfqDao(uriInfo,header).getRfqByFilter();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					 logger.info( "Error calling getRfqRowsByFilter()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			// Get record count with filter for Rfq
			@GET
			@Path("/recordcount")
			@Produces({"application/json"})
			public Response getRfqRecordCount() throws JSONException {
				JSONObject rows = new JSONObject();
				try {
					rows=new RfqDao(uriInfo,header).getRfqRecordCount();
				} catch (AuthenticationException e) {
					 rows.put("error",new TemplateUtility().getFailedMessage(e.getMessage()));
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getRfqRows()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return Response.status(200).entity(rows.toString()).build();
			}
			 
			// Get summary row against object ID for Rfq
			@GET
			@Path("/{id}/summary")
			@Produces({"application/xml"})
			public Rows getRfqSummaryRows() {
				Rows rows = null;
				try {
					rows=new RfqDao(uriInfo,header).getRfqSummaryRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getRfqRows()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			// Get Rfq record deleted using id
			@GET
			@Path("/{id}/delete")
			@Produces({"application/xml"})
			public Rows getRfqRowDeleted() {
				Rows rows = null;
				try {
					rows=new RfqDao(uriInfo,header).getRfqRowDeleted();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getRfqRowDeleted()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			 
			// Get all Rfqparts rows against object ID for Rfq
			@GET
			@Path("/{id}/rfqparts")
			@Produces({"application/xml"})
			public Rows getRfqpartsRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new RfqDao(uriInfo,header).getRfqpartsRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getRfqpartsRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Get all Rfqemail rows against object ID for Rfq
			@GET
			@Path("/{id}/rfqemail")
			@Produces({"application/xml"})
			public Rows getRfqemailRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new RfqDao(uriInfo,header).getRfqemailRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getRfqemailRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Get all Trtasks rows against object ID for Rfq
			@GET
			@Path("/{id}/trtasks")
			@Produces({"application/xml"})
			public Rows getTrtasksRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new RfqDao(uriInfo,header).getTrtasksRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getTrtasksRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Get all Qrtasks rows against object ID for Rfq
			@GET
			@Path("/{id}/qrtasks")
			@Produces({"application/xml"})
			public Rows getQrtasksRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new RfqDao(uriInfo,header).getQrtasksRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getQrtasksRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Get all Bidtasks rows against object ID for Rfq
			@GET
			@Path("/{id}/bidtasks")
			@Produces({"application/xml"})
			public Rows getBidtasksRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new RfqDao(uriInfo,header).getBidtasksRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getBidtasksRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Get all Potasks rows against object ID for Rfq
			@GET
			@Path("/{id}/potasks")
			@Produces({"application/xml"})
			public Rows getPotasksRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new RfqDao(uriInfo,header).getPotasksRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getPotasksRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Get all Emaildocs rows against object ID for Rfq
			@GET
			@Path("/{id}/emaildocs")
			@Produces({"application/xml"})
			public Rows getEmaildocsRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new RfqDao(uriInfo,header).getEmaildocsRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getEmaildocsRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Post all data changes in your grid for parent and child together
			@POST
			@Path("/post")
			@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
			@Produces({MediaType.APPLICATION_XML})
			public Rows postRfq(@Context UriInfo uriInfo,@Context  HttpHeaders header,@FormParam("body") String xml) {
				Rows rows = null;
				RfqDao post;
				try {
					post=new RfqDao(uriInfo,header);
					post.setPostXml(xml.trim());
					post.postRfqContainer();
					rows=post.getRfqRowModified();
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
			public Rows postFormDataRfq(@Context UriInfo uriInfo,@Context  HttpHeaders header,@FormDataParam("body") String xml) {
				Rows rows = null;
				RfqDao post;
				try {
					post=new RfqDao(uriInfo,header);
					post.setPostXml(xml.trim());
					if(post.postRfqContainer()){
						rows=post.getRfqRowModified();
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
