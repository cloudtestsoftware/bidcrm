/*
  * Available MenuConfig attributes are only configuration
  * 
  *  hidesearchcol,enterbqn,customsearchfield,customfilter,showmenu,showmore,
  *  allowadddelete,gridcolshow,gridcolhide,hidebuttons
  *  
  * Attributes for Custom methods to be implemented by user, use attribute=method name
  *  
  *  customonxle,customrowcreated,custompostsave ,custompostdelete, prebuttonaction, 
  *  postbuttonaction,removemethod,customselect
		
  */


var sampleorderitemxml="<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+
						"<sampleorderitem>"+
						"<record id=\"0\">"+
						"<objid isRequired=\"true\" type=\"RAW\"> </objid>"+
						"<sampleorderitem2samples isRequired=\"true\" type=\"RAW\">@sampleid</sampleorderitem2samples>"+
						"<sampleorderitem2sampleorder isRequired=\"true\" type=\"RAW\">@sampleorderid</sampleorderitem2sampleorder>"+
						"<qntordered isRequired=\"true\" type=\"NUMBER\">@quantity</qntordered>"+
						"<qntdispatched isRequired=\"false\" type=\"NUMBER\">null</qntdispatched>"+
						"</record>"+
						"</sampleorderitem>";	

function updateCurrencyMenu(grid){
		var url=www_url+"/rest/authorization/modules?token="+token+"&username="+username;
		modules=getSyncJsonResponse(url);
		biderp_toolbar.removeItem("btn_currency");
		setupCurrency();
		addCurrencyButtonSelect();
		
}


function sendEmailToContactList(grid,name){
	var targetstatus=0;
	var stage=0;
	var result;
	var isconfirmed=false;
	targetstatus=10;
	var myresult=false;
	stage=getGridColumnValueForSelectedRow(grid,"stagecode");
	if(stage && stage>=10){
		targetstatus= Number(stage) + 10;
	}
	
	dhtmlx.confirm("Confirm Email Campaign = Round-"+targetstatus/10, function(res){
		
		if(res){
			var objid=getGridColumnValueForSelectedRow(grid,"objid");
			var campaignid=getGridColumnValueForSelectedRow(grid,"contactlist2campaign");
			if(objid && objid.length==32){
				var url=www_url+"/rest/email/"+objid+"/contactlist?token="+token+"&status="+targetstatus 
						+ "&campaignid="+campaignid;
				result=getASyncJsonResponse(url);
				if(result){
					var message="Your request submitted for Round-"+targetstatus/10;
					message+=".<br><br>Please verify the the log after few min by selecting More.. button under ContactList tab.";
					dhtmlx.alert(message);
				}
			
				/*
				if(result){
					var items=JSON.parse(result);
					var error=items.errmessage;
					if(error){
						dhtmlx.alert(error);
					}else{
						dhtmlx.alert("Total email sent="+items.emailsent);
					}
				}
				*/
				
			}
		}
	});
	
	
}

function createPortalUrl(grid,name){
	var objid=getGridColumnValueForSelectedRow(grid,"objid");
	var url=www_url+"/rest/portal/"+objid+"/portalurl?token="+token;
	var data=getSyncJsonResponse(url);
	var data=JSON.parse(data);
	window.open(data.portalurl.replace("&#63;","?"),"_blank");
	//dhtmlx.alert(data.portalurl);
	
}

function openTemplateEditor(grid,name){
	var islocked=getGridColumnValueForSelectedRow(grid,"islocked");
	if(islocked==1){
		dhtmlx.alert("Your Template is locked, can not be opened! Please unlock it!")
		return false;
	}
	var objid=getGridColumnValueForSelectedRow(grid,"objid");
	var url=www_url+"/src/jstinker/editor.jsp?token="+token+"&baseurl="+www_url+"&username="+username
			+"&templateid="+objid;
	window.open(url);
	
}


function showCampaignCalendar(grid,name){
	var objid=getGridColumnValueForSelectedRow(grid,"objid");
	var url=www_url+"/src/calendar/campaign_calendar.jsp?token="+token+"&baseurl="+www_url
	+"&username="+username+"&campaignid="+objid+"&title=My Campaign Calendar";
	if(grid){
		window.open(url);
	}else{
		window.open(url,'_self');
	}

}

function showAnnualCalendar(grid,name){
	var url=www_url+"/src/calendar/campaign_calendar.jsp?token="+token+"&baseurl="+www_url
	+"&username="+username+"&title=Yearly Campaigns By Month";;
	if(grid){
		window.open(url);
	}else{
		window.open(url,'_self');
	}

}

function showTravelCalendar(grid,name){
	var url=www_url+"/src/calendar/travel_calendar.jsp?token="+token+"&baseurl="+www_url+"&username="+username;
	if(grid){
		window.open(url);
	}else{
		window.open(url,'_self');
	}

}
function showTravelCampaignOverlayCalendar(grid,name){
	var url=www_url+"/src/calendar/travel_campaign_overlay_calendar.jsp?token="+token+"&baseurl="+www_url+"&username="+username;
	if(grid){
		window.open(url);
	}else{
		window.open(url,'_self');
	}
	

}

function sendEmailToReviewer(grid,name){
	var email=getGridColumnValueForSelectedRow(grid,"email");
	var name=getGridColumnValueForSelectedRowWithCombo(grid,"reviewer2employee");
	if(email){
		var title=getGridColumnValueForSelectedRow(master_grid,"name");
		var owner=getGridColumnValueForSelectedRow(master_grid,"owner");
		var objid=getGridColumnValueForSelectedRow(master_grid,"objid");
		var subject="Need Email Template Approval";
		var message;
		
	    message="<html><head><body><div>Hi "+ name+", <br> You are aprover for this campaign send by "+owner;
		message+=".<br> Master Template Name :"+title;
		//message+="<br> Template Id :"+objid;
		message+="Please click below link to approve this template <br>"+
				"https://www.bidcrm.com/bidcrm/service?refobjid=mastertemplate-"+objid;
		message+="<br><br>Thank you<br>BidCRM Support Team.</body></head></html>";
		
		var url=www_url+"/rest/email/sendmessage?token="+token+"&sendto="+email+"&subject="+subject+"&contenttype=html"
		+"&message="+message;
		var data=getSyncJsonResponse(url);
		var data=JSON.parse(data);
		dhtmlx.alert(data.result);
	}
	

}


function sendEmailToApprover(grid,name){
	var email=getGridColumnValueForSelectedRow(grid,"email");
	var name=getGridColumnValueForSelectedRowWithCombo(grid,"cmpmember2salesrep");
	if(email){
		var campaignname=getGridColumnValueForSelectedRow(master_grid,"name");
		var campaignowner=getGridColumnValueForSelectedRow(master_grid,"owner");
		var campaignid=getGridColumnValueForSelectedRow(master_grid,"campaignid");
		var subject="Need Campaign Approval";
		var message;
		if(name){
			name=name.split("(")[0];
		}else{
			name="";
		}
	    message="<html><head><body><div>Hi "+ name+", <br> You are aprover for this campaign send by "+campaignowner;
		message+=".<br>Campaign Name :"+campaignname;
		message+=".<br>Campaign Id :"+campaignid;
		message+=".<br>Please click below link and select member tab after opeining to approve.";
		message+="<br>https://www.bidcrm.com/bidcrm/service?refobjid=campaign-"+objid;
		message+=".</div></body></head></html>";
		
		var url=www_url+"/rest/email/sendmessage?token="+token+"&sendto="+email+"&subject="+subject+"&contenttype=html"
		+"&message="+message;
		var data=getSyncJsonResponse(url);
		var data=JSON.parse(data);
		dhtmlx.alert(data.result);
	}
	

}

function approveTask(grid,name){
	var email=getGridColumnValueForSelectedRow(grid,"email");
	if(email && email==username){
		var rowid=grid.getSelectedRowId();
		 setColumnValueByGridRowId(grid,"isapproved",rowid, "1");
		 grid.setUserData(rowid,"status","dirty");
		 
		 handle_save_changes();
		 var sendto="";
		 var aprovername="";
		 if(current_parent_table.toLowerCase()=="mastertemplate"){
			 sendto=getGridColumnValueForSelectedRow(master_grid,"owneremail");
			 aprovername=getGridColumnValueForSelectedRowWithCombo(grid,"reviewer2mastertemplate");
		 }else if(current_parent_table.toLowerCase()=="campaign"){
			 sendto=getGridColumnValueForSelectedRow(master_grid,"email");
			 aprovername=getGridColumnValueForSelectedRowWithCombo(grid,"cmpmember2salesrep");
		 }
		 if(sentto!=""){
			 var subject=getGridColumnValueForSelectedRow(grid,"name");
			 var message=" \n Your "+subject+ " got approved by " +aprovername;
			 sendApprovedEmail(sendto,subject,message) ;
			 dhtmlx.alert("Approval email sent to "+sendto);
		 }
		
	}else{
		dhtmlx.alert("Ops! you are not approver! Your login email does not match with approver email in this record!")
	}

}
function assignConsoleObject(grid,name){
	var assignto;
	var item=getGridColumnValueForSelectedRowWithCombo(grid,"console2assignto");
	if(item && item.indexOf("(")>0){
		assignto=item.split("(")[1].replace(")","").trim();
	}
	if(assignto && assignto.length>0){
		var userid=getGridColumnValueForSelectedRow(grid,"console2assignto");
		var consoleid=getGridColumnValueForSelectedRow(grid,"objid:false");
		
		if(userid){
			var url=www_url+"/rest/myconsole/"+consoleid+"/assign?token="+token+"&assignto="+assignto+"&userid="+userid
			var data=getSyncJsonResponse(url);
			var data=JSON.parse(data);
		}
		if(data.result==true){
			
			 if(assignto!=""){
				 var subject=getGridColumnValueForSelectedRow(grid,"title");
				 var description=getGridColumnValueForSelectedRow(grid,"description");
				 var message=" \n "+fullname+" has assigned you a task "+description+ ". Please login and look at this item at console!" 
				  +"https://www.bidcrm.com/bidcrm/service?refobjid=console-"+consoleid;
				 sendApprovedEmail(assignto,subject,message) ;
				 dhtmlx.alert("Email sent to "+assignto);
			 }
			
		}
	}else{
		dhtmlx.message("Please select a person to assign this task!")
	}
	
	
}
function approveTravel(grid,name){
	var email=fact_grid.cells(1,1).getValue();
		//getColumnValueByGridRowId(fact_grid,"value",1)
	var travelleremail=getGridColumnValueForSelectedRow(grid,"travelleremail");
	var travellername=getGridColumnValueForSelectedRow(grid,"firstname");
	var subject=getGridColumnValueForSelectedRow(grid,"firstname") + ": Travel Approved!";
	var approvername=getGridColumnValueForSelectedRowWithCombo(grid,"biztravel2approver");
	if(email && email==username){
		var rowid=grid.getSelectedRowId();
		 setColumnValueByGridRowId(grid,"isapproved",rowid, "1");
		 grid.setUserData(rowid,"status","dirty");
		 handle_save_changes();
		 sendApprovedEmail(travelleremail,subject," \n Your travel got approved by "+approvername);
		 dhtmlx.alert("Approval email sent to "+travelleremail);
	}else{
		dhtmlx.alert("Ops! you are not approver! Your login email does not match with approver email in this record!")
	}

}

function notifyTravelApprover(grid,name){
	
	var email=fact_grid.cells(1,1).getValue();
	var name=getGridColumnValueForSelectedRowWithCombo(grid,"biztravel2approver");
	if(email){
		var title=getGridColumnValueForSelectedRow(grid,"firstname")+ " " + getGridColumnValueForSelectedRow(grid,"lastname");
		var traveller=getGridColumnValueForSelectedRow(grid,"firstname");
		var objid=getGridColumnValueForSelectedRow(grid,"objid");
		var subject="Need Travel Approval";
		var message;
		message="<html><head><body><div>Hi "+ name+", <br> You are aprover for this Business Travel send by "+traveller;
		message+=".<br> Traveler Name :"+title;
		message+="<br>Please click the below link to approve.<br>https://www.bidcrm.com/bidcrm/service?refobjid=biztravel-"+objid;
		message+="<div></body></head></html>";
			
		/*
	    message="<html><head><body><div>Hi "+ name+", <br> You are aprover for this Business Travel send by "+traveller;
		message+=".<br> Traveler Name :"+title;
		message+="<br><br></div><div><a href=\"https://www.bidcrm.com/bidcrm/service?refobjid=biztravel-"+objid+">Click here to approve</a><div>";
		message+="<br><div>Please click approve button to Approve!!<div></body></head></html>";
		*/
		var url=www_url+"/rest/email/sendmessage?token="+token+"&sendto="+email+"&subject="+subject+"&contenttype=html"
		+"&message="+message;
		var data=getSyncJsonResponse(url);
		var data=JSON.parse(data);
		dhtmlx.alert(data.result);
	}
	

}

function sendApprovedEmail(emailto,subject,message){
	
	if(emailto){
		var url=www_url+"/rest/email/sendmessage?token="+token+"&sendto="+emailto+"&subject="+subject+
		+"&message="+message;
		var data=getSyncJsonResponse(url);
		var data=JSON.parse(data);
	}
	

}
function removeBold(str){
	var tmp="";
	if(str&&str!=null){
		tmp=str.replace(/<b( [^>]*)*>/gi,"");
		tmp = tmp.replace(/<\/b( [^>]*)*>/gi,"");
		tmp = tmp.replace(/!L|!G|\/b|!Lb/gi,"");
	}
	
	return tmp;
}


function publishTemplate(grid,name){
	try{
		var rowid=grid.getSelectedRowId();
		if(grid && rowid){
			setColumnValueByGridRowId(grid,"isforworld",rowid, "1");
			grid.setUserData(rowid,"status","dirty");
			master_grid.setUserData(rowid,"status","dirty");
			handle_save_changes();
		}
			
	} catch (err){
		
	}
}

function unpublishTemplate(grid,name){
	try{
		var rowid=grid.getSelectedRowId();
		if(grid && rowid){
			setColumnValueByGridRowId(grid,"isforworld",rowid, "0");
			grid.setUserData(rowid,"status","dirty");
			master_grid.setUserData(rowid,"status","dirty");
			handle_save_changes();
		}
			
	} catch (err){
		
	}
}


function lockTemplate(grid,name){
	try{
		var rowid=grid.getSelectedRowId();
		if(grid && rowid){
			setColumnValueByGridRowId(grid,"islocked",rowid, "1");
			setColumnValueByGridRowId(grid,"lockedby",rowid, username);
			setColumnValueByGridRowId(grid,"lockeddate",rowid, getToday());
			grid.setUserData(rowid,"status","dirty");
			master_grid.setUserData(rowid,"status","dirty");
			handle_save_changes();
		}
		 
	} catch (err){
		
	}
}

function unlockTemplate(grid,name){
	try{
		var rowid=grid.getSelectedRowId();
		if(grid && rowid){
			setColumnValueByGridRowId(grid,"islocked",rowid, "0");
			setColumnValueByGridRowId(grid,"unlockedby",rowid, username);
			setColumnValueByGridRowId(grid,"unlockeddate",rowid, getToday());
			grid.setUserData(rowid,"status","dirty");
			master_grid.setUserData(rowid,"status","dirty");
			handle_save_changes();
		}
			
	} catch (err){
		
	}
}

function cloneTemplate(grid,name){
	var objid=getGridColumnValueForSelectedRow(grid,"objid");
	var url=www_url+"/rest/editor/"+objid+"/clonetemplate?token="+token+"&table="+last_table;
	var data=getSyncJsonResponse(url);
	var data=JSON.parse(data);
	dhtmlx.alert("Total number of file cloned="+data.filecreated);
	
}

function deleteTemplateFiles(table,grid,objid){
	if(table && grid){
		var  filepath=getGridColumnValueForSelectedRow(grid,"url");
	    var htmlurl=www_url+'/rest/file/delete?token='+token+"&table="+table.toLowerCase() +"&filename="+filepath;
		var htmlres=getSyncResponse(htmlurl);
		htmlurl=www_url+'/rest/file/delete?token='+token+"&table="+table.toLowerCase() +"&filename="+filepath.replace(".html",".code");
		htmlres=getSyncResponse(htmlurl);
		htmlurl=www_url+'/rest/file/delete?token='+token+"&table="+table.toLowerCase() +"&filename="+filepath.replace(".html",".js");
		htmlres=getSyncResponse(htmlurl);
		htmlurl=www_url+'/rest/file/delete?token='+token+"&table="+table.toLowerCase() +"&filename="+filepath.replace(".html",".css");
		htmlres=getSyncResponse(htmlurl);
	}
	
}

function generateEventFile(grid,name){
	
}

function publishEvent(grid,name){
	
}



