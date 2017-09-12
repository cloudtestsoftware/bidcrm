
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
		import com.bidcrm.dao.CampaignDao;
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

		//Use this URI resource with Base URL to access Campaign
		@Path("/campaign")
		public class CampaignService {
			static Log logger = LogFactory.getLog(CampaignService.class);

			// Get all contextual objects for this class
			@Context UriInfo uriInfo;
			@Context  HttpHeaders header;
			 
			// Get all rows for Campaign
			@GET
			@Path("/rows")
			@Produces({"application/xml"})
			public Rows getCampaignRows() {
				Rows rows = null;
				try {
					rows=new CampaignDao(uriInfo,header).getCampaignRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					 logger.info( "Error calling getCampaignRows()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			// Get Campaign record by id
			@GET
			@Path("/{id}/record")
			@Produces({"application/xml"})
			public Rows getCampaignRecord() {
				Rows rows = null;
				try {
					rows=new CampaignDao(uriInfo,header).getCampaignRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					 logger.info( "Error calling getCampaignRecord()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			// Get Campaign form
			@GET
			@Path("/form")
			@Produces({"application/xml"})
			public Items getCampaignForm() {
				Items items = null;
				try {
					items=new CampaignDao(uriInfo,header).getCampaignForm();
				} catch (AuthenticationException e) {
					 items=new TemplateUtility().getFailedItemMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					 logger.info( "Error calling getCampaignRecord()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return items;
			}
			 
			// Get all rows with filter for Campaign
			@GET
			@Path("/filter")
			@Produces({"application/xml"})
			public Rows getCampaignRowsByFilter() {
				Rows rows = null;
				try {
					rows=new CampaignDao(uriInfo,header).getCampaignByFilter();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					 logger.info( "Error calling getCampaignRowsByFilter()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			// Get record count with filter for Campaign
			@GET
			@Path("/recordcount")
			@Produces({"application/json"})
			public Response getCampaignRecordCount() throws JSONException {
				JSONObject rows = new JSONObject();
				try {
					rows=new CampaignDao(uriInfo,header).getCampaignRecordCount();
				} catch (AuthenticationException e) {
					 rows.put("error",new TemplateUtility().getFailedMessage(e.getMessage()));
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getCampaignRows()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return Response.status(200).entity(rows.toString()).build();
			}
			 
			// Get summary row against object ID for Campaign
			@GET
			@Path("/{id}/summary")
			@Produces({"application/xml"})
			public Rows getCampaignSummaryRows() {
				Rows rows = null;
				try {
					rows=new CampaignDao(uriInfo,header).getCampaignSummaryRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getCampaignRows()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			// Get Campaign record deleted using id
			@GET
			@Path("/{id}/delete")
			@Produces({"application/xml"})
			public Rows getCampaignRowDeleted() {
				Rows rows = null;
				try {
					rows=new CampaignDao(uriInfo,header).getCampaignRowDeleted();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getCampaignRowDeleted()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			 
			// Get all Cmpmember rows against object ID for Campaign
			@GET
			@Path("/{id}/cmpmember")
			@Produces({"application/xml"})
			public Rows getCmpmemberRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new CampaignDao(uriInfo,header).getCmpmemberRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getCmpmemberRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Get all Contactlist rows against object ID for Campaign
			@GET
			@Path("/{id}/contactlist")
			@Produces({"application/xml"})
			public Rows getContactlistRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new CampaignDao(uriInfo,header).getContactlistRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getContactlistRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Get all Emailsetting rows against object ID for Campaign
			@GET
			@Path("/{id}/emailsetting")
			@Produces({"application/xml"})
			public Rows getEmailsettingRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new CampaignDao(uriInfo,header).getEmailsettingRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getEmailsettingRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Get all Automation rows against object ID for Campaign
			@GET
			@Path("/{id}/automation")
			@Produces({"application/xml"})
			public Rows getAutomationRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new CampaignDao(uriInfo,header).getAutomationRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getAutomationRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Get all Samplereserve rows against object ID for Campaign
			@GET
			@Path("/{id}/samplereserve")
			@Produces({"application/xml"})
			public Rows getSamplereserveRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new CampaignDao(uriInfo,header).getSamplereserveRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getSamplereserveRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Get all Emailresponse rows against object ID for Campaign
			@GET
			@Path("/{id}/emailresponse")
			@Produces({"application/xml"})
			public Rows getEmailresponseRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new CampaignDao(uriInfo,header).getEmailresponseRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getEmailresponseRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Get all Sampleorder rows against object ID for Campaign
			@GET
			@Path("/{id}/sampleorder")
			@Produces({"application/xml"})
			public Rows getSampleorderRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new CampaignDao(uriInfo,header).getSampleorderRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getSampleorderRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Get all Samples rows against object ID for Campaign
			@GET
			@Path("/{id}/samples")
			@Produces({"application/xml"})
			public Rows getSamplesRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new CampaignDao(uriInfo,header).getSamplesRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getSamplesRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Get all Campaignnote rows against object ID for Campaign
			@GET
			@Path("/{id}/campaignnote")
			@Produces({"application/xml"})
			public Rows getCampaignnoteRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new CampaignDao(uriInfo,header).getCampaignnoteRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getCampaignnoteRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Get all Surveyquestion rows against object ID for Campaign
			@GET
			@Path("/{id}/surveyquestion")
			@Produces({"application/xml"})
			public Rows getSurveyquestionRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new CampaignDao(uriInfo,header).getSurveyquestionRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getSurveyquestionRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Post all data changes in your grid for parent and child together
			@POST
			@Path("/post")
			@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
			@Produces({MediaType.APPLICATION_XML})
			public Rows postCampaign(@Context UriInfo uriInfo,@Context  HttpHeaders header,@FormParam("body") String xml) {
				Rows rows = null;
				CampaignDao post;
				try {
					post=new CampaignDao(uriInfo,header);
					post.setPostXml(xml.trim());
					post.postCampaignContainer();
					rows=post.getCampaignRowModified();
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
			public Rows postFormDataCampaign(@Context UriInfo uriInfo,@Context  HttpHeaders header,@FormDataParam("body") String xml) {
				Rows rows = null;
				CampaignDao post;
				try {
					post=new CampaignDao(uriInfo,header);
					post.setPostXml(xml.trim());
					if(post.postCampaignContainer()){
						rows=post.getCampaignRowModified();
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
