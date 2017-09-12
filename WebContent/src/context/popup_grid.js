var popup_toolbar_xml="<toolbar>"	
	 + '<item type="button" id="popup_action_add"  text="Add" img="add.gif"/>'
	 +'<item type="separator" id="button_spacer" />'
	 + '<item type="button" id="popup_action_save"  text="Save" img="save.gif"/>'
	 + '<item type="separator" id="button_spacer" />'
	 +'<item type="text" id="button_text_1" text="Name" />'
	 + '<item type="buttonInput" id="popup_input" value="" />'
	 +'<item type="button" id="popup_action_search" text="Search" img="search.gif" />'
	 + '<item type="separator" id="button_spacer" />'
	 + '<item type="button" id="popup_action_select"  text="Select" img="select.gif"/>'
	 + '<item type="button" id="popup_action_close"  text="Close" img="close.gif"/>';

function getReminderToolbar(table){
	
	var popup_toolbar_email_xml="<toolbar>"	
		 + '<item type="button" id="popup_action_add"  text="Add" img="add.gif"/>'
		 +'<item type="separator" id="button_spacer" />'
		 + '<item type="button" id="popup_action_save"  text="Save" img="save.gif"/>'
		 + '<item type="separator" id="button_spacer" />'
		 +'<item type="text" id="button_text_1" text="Name" />'
		 + '<item type="buttonInput" id="popup_input" value="" />'
		 +'<item type="button" id="popup_action_search" text="Search" img="search.gif" />'
		 + '<item type="separator" id="button_spacer" />'
		 + '<item type="button" id="popup_action_preview"  text="Preview" img="select.gif"/>'
		 + '<item type="button" id="popup_action_send_reminder" text="Send" img="send.gif"/>'
		// + '<item type="button" id="popup_action_send_' +table+'" text="Send" img="send.gif"/>'
		 + '<item type="button" id="popup_action_close"  text="Close" img="close.gif"/>';
	
	return popup_toolbar_email_xml;
}


var popup_rowid;
var popup_objid;
var popup_table;
var popupGrid;
var myToolbar;
var callerGrid;
var callerRowId;
var callerCellIndex;
var dhxWins;
var popupexceptiontable="reminder, event, textemail";
var popupcustomfilter;
var popupemailresponseid;
var reminderid;

function addPopupToGrid(grid,rId,cInd,nValue,oValue){
	callerGrid=null;
	callerGrid=grid;
	callerRowId=rId;
	callerCellIndex=cInd;
	 var colid=grid.getColumnId(cInd);
	   if(nValue=="null" &&(oValue==""||oValue=="null") && colid.indexOf(2)>=0){
		   var item=colid.split(":")[0];
		   if(item.indexOf("2")>=0){
			   var table=item.split("2")[1];
			   var title=grid.getColLabel(cInd);
			   addPopup(table,title);
		   }
		   
	   }
}
function addReminderPopup(grid,name){
	popupemailresponseid=null;
	popupcustomfilter=null;
	reminderid=null;
	callerGrid=null;
	callerGrid=grid;
	var table;
	var title;
	if(name ){
		table=name.split(":")[4];
		title=name.split(":")[2];
		
	}
	
	addPopup(table,title);
}
function addPopup(table,title){
	
	
	var toolbarxml=popup_toolbar_xml;
	var popwidth=500;
	 var  gurl=www_url+'/rest/'+table+'/rows?token='+token;
	 if(popupexceptiontable.indexOf(table)>=0){
			toolbarxml=getReminderToolbar(table);
			var popwidth=600;
			popupemailresponseid=getGridColumnValueForSelectedRow(callerGrid,"objid");
			var customfilter= getMenuObjectAttribute(table.toLowerCase(), "customfilter");
			 if(customfilter){
				 try{
					 popupcustomfilter=eval(customfilter);
					 gurl+="&searchfilter="+popupcustomfilter;
				 }catch(err){}
				
			 }
		}
	popup_table=table;
	dhxWins = new dhtmlXWindows();
	dhxWins.setSkin(dhtmlx.skin);
	w1 = dhxWins.createWindow("w1", 200, 100, 800, 500);
	w1.setModal(false);
	w1.setText(table);
	w1.hideHeader();
	
	
	myToolbar =w1.attachToolbar();
	myToolbar.setIconsPath('./src/codebase/imgs/');
	myToolbar.loadXMLString(toolbarxml, function(){
		myToolbar.addSpacer("button_spacer");
	});
	
	//addEvent(myToolbar,"action_button","onClick");
	myToolbar.setSkin(dhtmlx.skin);
	myToolbar.attachEvent("onClick", function(id){
	    // your code here
		popup_button_handler(id);
	});
	popupGrid =w1.attachGrid();
	popupGrid.setIconsPath('./src/codebase/imgs/');
	
	/*
	popupGrid.attachEvent("onRowDblClicked", function(){
		popupGrid.clearAll();
		w1.close();
		dhxWins.unload();
	});
	*/
	//popupGrid.enableEditEvents(true);

  
   popupGrid.setDateFormat(dateformat);
   popupGrid.init();
  
   popupGrid.attachEvent("onValidationError", validate_me);
	
	addGridEvent(popupGrid,'popup_onRowSelect','onRowSelect');
	
	popupGrid.attachEvent('onEditCell', function(stage, rId, cInd, nValue, oValue){
		
		//disableGridColumns(popupGrid, rId, cInd);
		var val=popupGrid.cells(rId,cInd).getValue();
	    if(stage==0  && (val=='new' ||val=='0')){
	  		popupGrid.cells(rId,cInd).setValue('');
	  	}
	   // popupGrid.selectCell(rId,cInd,0,0,true);
		popup_onEditCell_callback(stage, rId, cInd, nValue, oValue);
		return true;
		
	});
	
	popupGrid.attachEvent("onRowCreated", function(rId,rObj,rXml){
	    // your code here
		popupGrid.setColumnHidden(0,true);
		formatGridCurrency(popupGrid,rId);
		disableGridColumns(popupGrid, rId);
		
		//format currency
		
		 var customrowcreated= getMenuObjectAttribute(current_parent_table.toLowerCase(), "customrowcreated");
		   if(customrowcreated){
			   eval(customrowcreated+"(popupGrid,rId)");
		   }
		   greyOutRowWithDisableColumns(popupGrid,rId);
	});
	
  popupGrid.attachEvent("onXLE", function(grid,count){
 		
	    //addActionButtonToMasterToolbar(grid_obj);
	    hideToolbarItems(workplace_toolbar,popup_table.toLowerCase());
	    hideGridColumns(grid,popup_table.toLowerCase());
 		disableGridActionButtons();
 		 var customonxle= getMenuObjectAttribute(popup_table.toLowerCase(), "customonxle");
		   if(customonxle){
			   eval(customonxle+"(popupGrid,count)");
		   }
		   greyOutGridWithDisableColumns(popupGrid);
		  /* if(popupexceptiontable.indexOf(popup_table.toLowerCase())>=0 ){
			   popupGrid.setColumnHidden(3,true);
		   }
		   */
 		
 	}); 
 
	popupGrid.attachEvent('onSelectStateChanged', function(id){
	
		popup_onSelectStateChanged_callback(id);
		return true;
	});
	
	
	   popupGrid.load(gurl,'xml');
}

function popup_onSelectStateChanged_callback(rowId){
		
		if(popupGrid.getUserData(parent_rowid,"status")=='dirty'){
			//save any changes
   		handle_save_changes();
   		//reset current master row
			popupGrid.setUserData(rowId,"status","clean");
			popup_rowid=rowId;
		}
		
		popupGrid.setColumnHidden(0,true);
		if(popupGrid.cells(rowId,0).getValue()!=''){
			popup_objid=popupGrid.cells(rowId,0).getValue();
			//current_parent_id=popupGrid.cells(rowId,0).getValue();
		}
}
function popup_onEditCell_callback(stage, rId, cInd, nValue, oValue){
	
	
	   if(nValue!=oValue){
			popupGrid.setUserData(rId,"status","dirty");
			popup_rowid=rId;
		}
	  
	  
	}

function popup_onRowSelect_callback(rowId,cellIndex){
	   
	   validation_status=true;
	   validation_message="";
	   popup_rowid=rowId;
	   var customselect= getMenuObjectAttribute(popup_table.toLowerCase(), "customselect");
	   if(customselect){
		   eval(customselect+"(last_grid,rowId,cellIndex)");
	   }
		
	}

function  popup_save_changes(){
	 if(popupexceptiontable.indexOf(popup_table.toLowerCase())>=0 ){
		 setColumnValueByGridRowId(popupGrid,"keyobjid",popupGrid.getSelectedRowId(), popupemailresponseid)
     }
  	var parent_data= collect_data(popup_table.toLowerCase(),popupGrid);
  	
  	 if(parent_data!='\n' ){  
		var posturl=www_url+"/rest/"+popup_table.toLowerCase()+"/post?token="+token;
		current_parent_id=null;
		ajax_popup_post_handler(parent_data+ "\n", posturl);
    }
    

}

function ajax_popup_post_handler(xml, url){
	  
	   if (xml=="\n"){
	    	return;
	   }else{
	   	//alert(xml);
		 setStatusBar("Saving a record on "+current_parent_table);
	     hidden_form.setItemValue("body",xml);
	   
	    hidden_form.send(url, "post", function(loader, response){
			post_reponse=response;
			//var x=loader.xmlDoc;
		
			if (window.DOMParser)
	  		{
	  			parser=new DOMParser();
	  			xmlDoc=parser.parseFromString(post_reponse,"text/xml");
	  		}
		   else // Internet Explorer
	  		{
	  			xmlDoc=new ActiveXObject("Microsoft.XMLDOM");
	  		    xmlDoc.async=false;
	  			xmlDoc.loadXML(post_reponse); 
	        } 
			reminderid=xmlDoc.getElementsByTagName("cell")[0].childNodes[0].nodeValue;
	        if(reminderid ){
				setStatusBar("Updated "+popup_table+":id:"+reminderid);
				var rowid=popupGrid.getSelectedRowId();
				setColumnValueByGridRowId(popupGrid,"objid:false",rowid, reminderid)
				
		    }
	        if(popup_table){
	     	   var customhandle=getMenuObjectAttribute(popup_table, "custompostsave");
	     	   var grid;
	     	   if(customhandle){
	     		   
	     		   eval(customhandle+"(popupGrid)");
	     	   }
	        }
		   
		});
	
		
		}
		

	}

function handle_add_popup(){

    //current_parent_id='newid';
    var newId = (new Date()).valueOf();
    popupGrid.addRow(newId,"",0);
    popupGrid.selectRow(master_grid.getRowIndex(newId),false,false,true);
    popupGrid.cells(newId,0).setValue("newid");
    var validator=popupGrid.getUserData("","grid.validator").split(",");
    var colIdx=1;
    popupGrid.forEachCell(newId,function(c){
	           var colid=popupGrid.getColumnId(colIdx);
	           if(colid){
	               if(validator.length>=colIdx &&validator[colIdx]=='ValidInteger'){
	            	   popupGrid.cells(newId,colIdx).setValue("0");
	           		}else{
	           			popupGrid.cells(newId,colIdx).setValue("new");
	           		}
	           	}
	           if(popupexceptiontable.indexOf(popup_table.toLowerCase())>=0 &&colid.indexOf("keyobjid")){
	        	   popupGrid.cells(newId,colIdx).setValue(popupemailresponseid);
	           }
	           
	            colIdx++;
	        });

}
function handle_popup_search(){
	var  gurl=www_url+'/rest/'+popup_table.toLowerCase()+'/rows?token='+token;
	var  val=myToolbar.getValue("popup_input");
	var filter
	if(val){
		filter="name"+String.fromCharCode(1)+"Like"+String.fromCharCode(1)+val+"%" ;
	}
	
	
	if(filter && filter!=''){
    	gurl+="&searchfilter="+filter;
    }
	 popupGrid.clearAll();
	 popupGrid.load(gurl,'xml');
}
function popup_button_handler(id){
	//dhtmlx.alert("popup_button_handler="+id);
	if(id=="popup_action_add"){
		handle_add_popup();
	}else if(id=="popup_action_save"){
		 popup_save_changes();
	}else if(id=="popup_action_search"){
		handle_popup_search()
	}else if(id=="popup_action_preview"){
		showTemplatePreview()
	}else if(id=="popup_action_send_reminder"){
		handle_popup_send_reminder()
	}else if(id=="popup_action_select"){
		//populate target grid
		if(fillupCallerCombo()){
			// close popup
			popupGrid.clearAll();
			w1.close();
			dhxWins.unload();
		}
		
	}else if(id=="popup_action_close"){
		callerGrid=null;
		callerRowId=null;
		callerCellIndex=null;
		popupGrid.clearAll();
		w1.close();
		dhxWins.unload();
	}
}


function fillupCallerCombo(){
	
	 var optiontext;
	 var optionvalue;
	
	  var combo=callerGrid.getCombo(callerCellIndex);
	  
	  optiontext=getGridColumnValueForSelectedRow(popupGrid,"name");
	 
	  if(current_parent_id==null){
		  dhtmlx.message("Wait! Until data is saved. Please click select button again!");
		  sleepnow(500);
		  return false;
		 
	  }else{
		  optionvalue=current_parent_id;
		  var rowid=popupGrid.getSelectedRowId();
		  popupGrid.cells(rowid,0).setValue(current_parent_id);
	 
		  combo.put(current_parent_id,optiontext);
		  combo.save();
		  callerGrid.cells(callerRowId,callerCellIndex).setValue(current_parent_id);
		  callerGrid=null;
		  callerRowId=null;
		  callerCellIndex=null;
	  }
		  
	return true;
	
}

function showTemplatePreview(){
		var templateid=getGridColumnValueForSelectedRow(popupGrid,"reminder2template");
		var html="";
		try{
			var ww=www_url.split(".com")[0]+".com/userdoc/mastertemplate/"+templateid+".html";
			if(ww.indexOf("localhost")>=0){
				ww="https://www.bidcrm.com/userdoc/mastertemplate/"+templateid+".html";
			}
			if(ww.toLowerCase().indexOf(".htm")>=0){
				
				html="<iframe width=\"100%\" height=\"100%\" src=\""+ww+"\"/>";
				
			}
		}catch(err){}
		html_cell.attachHTMLString(html);
	
}

function handle_popup_send_reminder(){
	if(!reminderid){
		 reminderid=getGridColumnValueForSelectedRow(popupGrid,"objid:false");
	}
	var keyobjid=getGridColumnValueForSelectedRow(popupGrid,"keyobjid");
	if(reminderid &&reminderid.length !=32 &&keyobjid &&keyobjid.length !=32){
		dhtmlx.alert("Please save Reminder record and then send email!");
		return false;
	}
	var url=www_url+"/rest/email/"+reminderid+"/"+popup_table.toLowerCase()+"?token="+token;
	var result=getSyncJsonResponse(url);
	var data=JSON.parse(result);
	if(data &&data.emailsent=='1'){
		dhtmlx.alert("Email Reminder Sent To ="+data.sentto)
	}else{
		dhtmlx.alert("Failed to send Email Reminder!")
	}
	reminderid=null;
}


