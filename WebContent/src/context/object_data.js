/*
  * Available MenuConfig attributes are only configuration
  * 
  *  hidesearchcol,enterbqn,customsearchfield,customfilter,showmenu,showmore,allowadddelete,gridcolshow,gridcolhide,hidebuttons
  *  
  * Attributes for Custom methods to be implemented by user, use attribute=method name
  *  
  *  customonxle,customrowcreated,custompostsave ,custompostdelete, prebuttonaction, postbuttonaction,removemethod,customselect
		
  */
 
 var menuconfig={
		    "menues": {
		    	"erpadmin": { "company":{"label":"Company", "hidesearchcol":"bqn"},
		    		 	 "profile":{"label":"Profile", "hidesearchcol":"bqn" },
		    			 "privilegegroup":{"label":"Privilege", "hidesearchcol":"bqn" },
		    		     "user":{"label":"User","hidesearchcol":"bqn" },
		    		     "messagequeue":{"label":"Queue","hidesearchcol":"bqn" },
		    		     "object":{"label":"Object", "hidesearchcol":"bqn"},
		    		     "attribute":{"label":"Attribute","hidesearchcol":"bqn" },
		    		     "listproperty":{"label":"List Property", "hidesearchcol":"bqn"},
		    		     "codeattribute":{"label":"Code Attribute", "hidesearchcol":"bqn"},
		    		     "genericcode":{"label":"Generic Code", "hidesearchcol":"bqn" },
		    		     "objectrule":{"label":"Object Rule", "hidesearchcol":"bqn" },
		    		     "actionquery":{"label":"Action Query", "hidesearchcol":"bqn"},
		    	},
		    	
		    	"useradmin": {"profile":{"label":"Profile", "hidesearchcol":"bqn" },
	    		     		 "user":{"label":"User", "hidesearchcol":"bqn" ,"gridcolshow":"loginname,password",
	    		     			 "customfilter":"\"loginname\"+String.fromCharCode(1)+\"=\"+String.fromCharCode(1)+username",
	    		     			 "gridcolhide":"user2supplier",
	    		     			 "customonxle":"setUserProfile",
	    		     			 "customrowcreated":"setExcelPassword"},
	    		     		 
		    	},
		    	"groupadmin": { "company":{"label":"Company", "hidesearchcol":"bqn" },
		    		"privilegegroup":{"label":"User Group", "hidesearchcol":"bqn" },
		    		"customlist":{"label":"Custom List", "hidesearchcol":"bqn"},
		    		"customcode":{"label":"Custom Code", "hidesearchcol":"bqn"},
	    		    "user":{"label":"Profile Update", "hidesearchcol":"bqn",
	    		    	"customfilter":"\"loginname\"+String.fromCharCode(1)+\"=\"+String.fromCharCode(1)+username",
	    		    	"gridcolhide":"user2supplier",
	    		    	"customonxle":"setUserProfile",
	    		    	"customrowcreated":"setExcelPassword"},
	    		   
	    		    
		    	},
		         "seeddata":{
		        	 "company":{"label":"Company", "hidesearchcol":"bqn"},
		        	 "territory":{"label":"Territory", "hidesearchcol":"bqn"},
		        	 "site":{"label":"Site", "hidesearchcol":"bqn"},
		        	 "bizdomain":{"label":"Biz Domain", "hidesearchcol":"bqn" },
	    		     "supplier":{"label":"Supplier", "hidesearchcol":"bqn" },
	    		     "partlist":{"label":"PartList", "hidesearchcol":"bqn" },
	    		     "bizprofile":{"label":"Biz Profile", "hidesearchcol":"bqn" },
	    		     "dataimport":{"label":"Data Import", "hidesearchcol":"bqn" },
	    		     "currency":{"label":"Currency", "hidesearchcol":"bqn", "custompostsave":"updateCurrencyMenu","custompostdelete":"updateCurrencyMenu"},
	    		    
		         },
		         "template":{
		        	 "imagelist":{"label":"Image List", "hidesearchcol":"bqn" },
		        	 "mastertemplate":{"label":"Master Template","removemethod":"deleteTemplateFiles", "hidesearchcol":"bqn", },
		        	 "publictemplate":{"label":"Public Template","removemethod":"deleteTemplateFiles", "hidesearchcol":"bqn"},
		        	 "emailsetting":{"label":"Email Setting", "hidesearchcol":"bqn"},
		         },
		         "channels":{
		        	 "channel":{"label":"Marketing Channel", "hidesearchcol":"bqn", },
		        	 "forms":{"label":"Forms", "hidesearchcol":"bqn"},
		        	 "samplesportal":{"label":"Samples Portal", "hidesearchcol":"bqn" },
		        	 "surveyportal":{"label":"Survey Portal", "hidesearchcol":"bqn" },
		        	 "eventportal":{"label":"Event Portal", "hidesearchcol":"bqn" },
		        	 "vedioportal":{"label":"Vedio Portal", "hidesearchcol":"bqn" },
		        	 "customportal":{"label":"Custom Portal", "hidesearchcol":"bqn" },
		         },
		         "campaign":{
		        	 "campaign":{"label":"Campaign", "hidesearchcol":"bqn" },
		        	 "emailsetting":{"label":"Email Template", "removemethod":"deleteAttachedFile","hidesearchcol":"bqn" },
		        	 "samplereserve":{"label":"Sample Reservation", "hidesearchcol":"bqn" },
		        	 "samples":{"label":"Samples", "hidesearchcol":"bqn" },
		        	 "emaillist":{"label":"Master Email List", "hidesearchcol":"bqn" },
		        	 "contactlist":{ "label":"Contact List","hidesearchcol":"bqn" },
		        	 "automation":{ "label":"Automation","hidesearchcol":"bqn" },
		        	
		         },
		         "crm":{
		        	 "account":{"label":"Account","hidesearchcol":"bqn" },
		        	 "contact":{"label":"Contact","gridcolhide":"extcontactid,extaccountid,contact2lead","hidesearchcol":"bqn,"	 },
		             "lead":{"label":"Lead","hidesearchcol":"bqn" },
	    		     "opportunity":{"label":"Opportunity","hidesearchcol":"bqn" },
	    		     "deal":{"label":"Pipe Line","hidesearchcol":"bqn" },
	    		     "customer":{"label":"Customer","hidesearchcol":"bqn" },
	    		     "employee":{"label":"Employee", "hidesearchcol":"bqn" },
		        	 
		         },
		         "rfq": {
		        	    "rfq":{"label":"RFQ","hidesearchcol":"bqn"},
		          	 	 "rfqparts":{"label":"RFQ Parts", "hidesearchcol":"bqn" },
		        	     "trtasks":{"label":"TR Tasks", "hidesearchcol":"bqn" },
		        	     "qrtasks":{"label":"QR Tasks", "hidesearchcol":"bqn" },
		        	     "rfqemail":{"label":"RFQ Email",  "hidesearchcol":"bqn"},
		        	     "bidtasks":{"label":"Bid Tasks",  "hidesearchcol":"bqn"},
		        	   
		           },
		         "store": {
		        	 "store":{"label":"Store ","hidesearchcol":"bqn" },
		        	 "storepartlist":{"label":"Store Part List ", "hidesearchcol":"bqn" },
		        	 "storeparts":{"label":"Store Parts","hidesearchcol":"bqn", },
	    		     "samplereserve":{"label":"Sample Reserve","hidesearchcol":"bqn", "allowadddelete":false,},
	    		     "sampleorder":{"label":"Sample Order","hidesearchcol":"bqn" },
	    		     "addinventory":{"label":"Add Inventory", "hidesearchcol":"bqn"},
	    		     "orderdelivery":{"label":"Order Delivery", },
	    		     "storeagent":{"label":"Store Agent", },
	    		     
		         },
		        
		         "travel":{
		        	 "biztravel":{"label":"Business Travel", "gridcolhide":"approvedby,approveddate","hidesearchcol":"bqn"},
	    		     "travelbooking":{"label":"Travel Booking", "hidesearchcol":"bqn"},
	    		     "travelexpense":{"label":"Travel Expense","hidesearchcol":"bqn" },
	    		    
		         },
		         "calendar":{
		        	 "financialyear":{"label":"Financial Year","hidesearchcol":"bqn"},
		        	 "campaigncalendar":{"label":"Campaign Calendar","hidesearchcol":"bqn"},
	    		     "travelcalendar":{"label":"Travel Calendar", "hidesearchcol":"bqn"},
	    		     "eventcalendar":{"label":"Event Calendar","hidesearchcol":"bqn" },
		         },	
		        
		         "report": {
		        	 	"bom":{"label":"Bill Of Material"},
		        	    "inventory":{"label":"Inventory", "hidesearchcol":"bqn" },
		        	    "partorder":{"label":"Part Order", },
		        	    "partplan":{"label":"Part Plan", },
		        	    "installpending":{"label":"Install Pending", "hidesearchcol":"bqn" },
		        	    "orderpending":{"label":"Order Pending", },
		        	    "currentplan":{"label":"Current Plan", },
		        	},
		         "profile":{
		        	 "profile":{"label":"Profile", "hidesearchcol":"bqn"  },
		        	    "profilesetting":{"label":"Setting", "hidesearchcol":"bqn"  },
		        	    "adduser":{"label":"Add User", "hidesearchcol":"bqn"  },
	        	},
	        	"portal":{
		        	 	"sampleportal":{"label":"SamplePortal", "excludecolupdate":"sampleorder2campaign"  },
		        	   
		        	},
	        	"console":{
	        	 	"console":{"label":"Console", "hidebuttons":"add" ,"gridcolhide":"keyobjid,mqid","excludecolupdate":"mqid", 
	        	 		"customfilter":"\"genuser\"+String.fromCharCode(1)+\"=\"+String.fromCharCode(1)+username+"+
	        	 		"String.fromCharCode(2)+\"nvl(console.isarchived,0)\"+String.fromCharCode(1)+\"=\"+String.fromCharCode(1)+consoleIsArchive",},
	        	 	
	        	 	"reminder":{"label":"Reminder","gridcolhide":"keyobjid", "customfilter":"\"keyobjid\"+String.fromCharCode(1)+\"=\"+String.fromCharCode(1)+popupemailresponseid" },
	        	 	"textemail":{"label":"Email","gridcolhide":"keyobjid", "customfilter":"\"keyobjid\"+String.fromCharCode(1)+\"=\"+String.fromCharCode(1)+popupemailresponseid" },
		        	   
	        	}
		        
		    }
}

//use this to add a custom button 
var custombutton=  {
	            
	            "emaillist": {"button":["Upload:doUpload","Download:doDownload"],"upload_column":"url"},
	            
	            "parts": {"button":["Upload:doUpload","Download:doDownload"],"upload_column":"url"},
	            
	            "images": {"button":["Upload:doUpload","Download:doDownload"],"upload_column":"url"},
	            
	            "console": {"button":["Open:openConsoleObject","Assign:assignConsoleObject","Archive:archiveConsoleObject","Load Archive:loadArchiveConsoleObject","Un Archive:unarchiveConsoleObject"],},
	            
	            "publictemplate": {"button":["Clone:cloneTemplate"],},
	            
	            "contactlist": {"button":["Send Email:sendEmailToContactList"],},
		           
	            "travelexpense": {"button":["Upload:doUpload","Download:doDownload"],"upload_column":"url"},
	            
	            "biztravel": {"button":["Travel Calender:showTravelCalendar","Overlay Calender:showTravelCampaignOverlayCalendar","Notify Approver:notifyTravelApprover","Approve:approveTravel"]},
	            
	            "cmpmember": {"button":["Notify Approver:sendEmailToApprover","Approve:approveTask"],},
	            
	            "reviewer": {"button":["Notify Approver:sendEmailToReviewer","Approve:approveTask"],},
	            
	            "emailsetting": {"button":["Upload:doUpload","Download:doDownload"],"upload_column":"url",
	            	"instruction":"<p class=\"recordtext\">Please upload <b>Text or HTML</b> file only! Download below template <a href=\"https://www.bidcrm.com/userdoc/template/emailsetting.txt\">Email Template</a></p>",
	            },
	            "eventtemplate": {"button":["Upload:doUpload","Download:doDownload","Generate Event File:generateEventFile","Publish Event:publishEvent"],"upload_column":"url",
	            	"instruction":"<p class=\"recordtext\">Please upload <b>Text or HTML</b> file only! Download below template <a href=\"https://www.bidcrm.com/userdoc/template/emailsetting.txt\">Email Template</a></p>",
	            },
	            "dataimport": {"button":["Upload:doUpload","Import Data:importData"],"upload_column":"url",
	            	"instruction":"<p class=\"recordtext\">Please upload <b>CSV</b> file only! Download below template <a href=\"https://www.bidcrm.com/bidcrm/userdoc/contactlist.csv\">Contact Template</a></p>"
	            },
	          
	            "emailresponse": {"button":["Send Reminder:addReminderPopup:reminder","Send Email:addReminderPopup:textemail","Create Lead:createLead",],"upload_column":"url"},
	            
	            "campaign": {"button":["Create Portal URL:createPortalUrl","Campaign Calender:showCampaignCalendar","Annual Calender:showAnnualCalendar"],},
	            
	            "mastertemplate": {"button":["Edit Template:openTemplateEditor","Download:doDownload","Clone Template:cloneTemplate","Publish To World:publishTemplate","Un Publish:unpublishTemplate","Lock:lockTemplate","UnLock:unlockTemplate"],"upload_column":"url"},
	            
	            "qrinfo": {"button":["Upload:doUpload","Download:doDownload"],"upload_column":"url"},
				};

 
 // [button id].method

 var customaction=  {
 	            "artifacts": {"action":["Upload:doUpload.notify_all_bidder"]},
 	            "invitation": {"action":["invitation:status:2.send_email_to_bidder"]},
 	            "rfqemail": {"action":["rfqemail:stage:100.send_rfq_email"]},
 	            "campaign": {"action":["campaign:status:10.createCampaignUrl"]},
 	           
 	        }; 
 
 
 var customalias={
		 "emailsetting": {"alias":"emailsetting"},
		 "emailsetting": {"alias":"emailsetting"},
 }
 
 
 function getObjectCustomAliasAttrVal(table,attribute){
		try{
			if(table){
				return customalias[table][attribute]; 
			}
		}catch(err){
			if(debug){
				dhtmlx.message(err +" : getCustomDataObjectAttrVal : "+table);
			}
		}
		return null;
	}

 function getMenuObjectAttributeByMenuId(menuid,table, key){
		try{
			if(table){
				var val=menuconfig.menues[menuid][table][key] ;
				if(!val){
					menuconfig.menues["console"][table][key] ;
				}
				return val;
			}
		}catch(err){
			if(debug){
				dhtmlx.message(err);
				//dhtmlx.message(err +" : getMenuObjectAttribute : "+table + " key :" +key);
			}
		}
		return null;
	}
 function getMenuObjectAttribute(table, key){
		try{
			if(table){
				var val=menuconfig.menues[current_app_id][table][key] ;
				if(!val){
					val=menuconfig.menues["console"][table][key] ;
				}
				return val;
			}
		}catch(err){
			if(debug){
				dhtmlx.message(err);
				//dhtmlx.message(err +" : getMenuObjectAttribute : "+table + " key :" +key);
			}
		}
		return null;
	}
 
 function getAttributeTrueFalse(menuid,table, key){
		try{
			if(table){
				
				var val= menuconfig.menues[menuid][table][key] ;
				if(!val){
					val= menuconfig.menues["console"][table][key] ;
				}
				if(!val){
					return true;
				}
			}
		}catch(err){
			if(debug){
				dhtmlx.message(err);
				//dhtmlx.message(err +" : getMenuObjectAttribute : "+table + " key :" +key);
			}
		}
		return false;
	}
 
 