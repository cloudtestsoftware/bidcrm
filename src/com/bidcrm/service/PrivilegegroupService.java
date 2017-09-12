
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
		import com.bidcrm.dao.PrivilegegroupDao;
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

		//Use this URI resource with Base URL to access Privilegegroup
		@Path("/privilegegroup")
		public class PrivilegegroupService {
			static Log logger = LogFactory.getLog(PrivilegegroupService.class);

			// Get all contextual objects for this class
			@Context UriInfo uriInfo;
			@Context  HttpHeaders header;
			 
			// Get all rows for Privilegegroup
			@GET
			@Path("/rows")
			@Produces({"application/xml"})
			public Rows getPrivilegegroupRows() {
				Rows rows = null;
				try {
					rows=new PrivilegegroupDao(uriInfo,header).getPrivilegegroupRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					 logger.info( "Error calling getPrivilegegroupRows()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			// Get Privilegegroup record by id
			@GET
			@Path("/{id}/record")
			@Produces({"application/xml"})
			public Rows getPrivilegegroupRecord() {
				Rows rows = null;
				try {
					rows=new PrivilegegroupDao(uriInfo,header).getPrivilegegroupRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					 logger.info( "Error calling getPrivilegegroupRecord()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			// Get Privilegegroup form
			@GET
			@Path("/form")
			@Produces({"application/xml"})
			public Items getPrivilegegroupForm() {
				Items items = null;
				try {
					items=new PrivilegegroupDao(uriInfo,header).getPrivilegegroupForm();
				} catch (AuthenticationException e) {
					 items=new TemplateUtility().getFailedItemMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					 logger.info( "Error calling getPrivilegegroupRecord()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return items;
			}
			 
			// Get all rows with filter for Privilegegroup
			@GET
			@Path("/filter")
			@Produces({"application/xml"})
			public Rows getPrivilegegroupRowsByFilter() {
				Rows rows = null;
				try {
					rows=new PrivilegegroupDao(uriInfo,header).getPrivilegegroupByFilter();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					 logger.info( "Error calling getPrivilegegroupRowsByFilter()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			// Get record count with filter for Privilegegroup
			@GET
			@Path("/recordcount")
			@Produces({"application/json"})
			public Response getPrivilegegroupRecordCount() throws JSONException {
				JSONObject rows = new JSONObject();
				try {
					rows=new PrivilegegroupDao(uriInfo,header).getPrivilegegroupRecordCount();
				} catch (AuthenticationException e) {
					 rows.put("error",new TemplateUtility().getFailedMessage(e.getMessage()));
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getPrivilegegroupRows()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return Response.status(200).entity(rows.toString()).build();
			}
			 
			// Get summary row against object ID for Privilegegroup
			@GET
			@Path("/{id}/summary")
			@Produces({"application/xml"})
			public Rows getPrivilegegroupSummaryRows() {
				Rows rows = null;
				try {
					rows=new PrivilegegroupDao(uriInfo,header).getPrivilegegroupSummaryRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getPrivilegegroupRows()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			// Get Privilegegroup record deleted using id
			@GET
			@Path("/{id}/delete")
			@Produces({"application/xml"})
			public Rows getPrivilegegroupRowDeleted() {
				Rows rows = null;
				try {
					rows=new PrivilegegroupDao(uriInfo,header).getPrivilegegroupRowDeleted();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getPrivilegegroupRowDeleted()"+ ex.getMessage());
					 ex.printStackTrace();
				}
				return rows;
			}
			 
			 
			// Get all User rows against object ID for Privilegegroup
			@GET
			@Path("/{id}/user")
			@Produces({"application/xml"})
			public Rows getUserRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new PrivilegegroupDao(uriInfo,header).getUserRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getUserRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Get all Module rows against object ID for Privilegegroup
			@GET
			@Path("/{id}/module")
			@Produces({"application/xml"})
			public Rows getModuleRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new PrivilegegroupDao(uriInfo,header).getModuleRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getModuleRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Get all Objectprivilege rows against object ID for Privilegegroup
			@GET
			@Path("/{id}/objectprivilege")
			@Produces({"application/xml"})
			public Rows getObjectprivilegeRows(@Context UriInfo uriInfo,@Context  HttpHeaders header) {
				Rows rows = null;
				try {
					rows=new PrivilegegroupDao(uriInfo,header).getObjectprivilegeRows();
				} catch (AuthenticationException e) {
					 rows=new TemplateUtility().getFailedMessage(e.getMessage());
					 e.printStackTrace();
				} catch (Exception ex) {
					logger.info( "Error calling getObjectprivilegeRows()"+ ex.getMessage());
				}
				return rows;
			}
			 
			// Post all data changes in your grid for parent and child together
			@POST
			@Path("/post")
			@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
			@Produces({MediaType.APPLICATION_XML})
			public Rows postPrivilegegroup(@Context UriInfo uriInfo,@Context  HttpHeaders header,@FormParam("body") String xml) {
				Rows rows = null;
				PrivilegegroupDao post;
				try {
					post=new PrivilegegroupDao(uriInfo,header);
					post.setPostXml(xml.trim());
					post.postPrivilegegroupContainer();
					rows=post.getPrivilegegroupRowModified();
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
			public Rows postFormDataPrivilegegroup(@Context UriInfo uriInfo,@Context  HttpHeaders header,@FormDataParam("body") String xml) {
				Rows rows = null;
				PrivilegegroupDao post;
				try {
					post=new PrivilegegroupDao(uriInfo,header);
					post.setPostXml(xml.trim());
					if(post.postPrivilegegroupContainer()){
						rows=post.getPrivilegegroupRowModified();
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
