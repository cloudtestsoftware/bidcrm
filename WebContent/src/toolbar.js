var biderp_toolbar_xml = '<toolbar>'
       
		+ '<item type="buttonSelect" id="skin" img="biderp.gif" text="BidCRM" style="position:absolute;width:35px;height:18px;left:16px;">'
		+ '<item type="button" id="skin_blue" text="Blue" img="blue.gif"/>'
		+ '<item type="button" id="skin_brown" text="Brown" img="brown.gif"/>'
		+ '<item type="button" id="skin_deep_blue" text="Deep Blue" img="deepblue.gif"/>'
		+ '<item type="button" id="skin_grass" text="Grass" img="grass.gif"/>'
		+ '<item type="button" id="skin_ocean" text="Ocean" img="ocean.gif"/>'
		+ '<item type="button" id="skin_oak" text="Oak" img="oak.gif"/>'
		+ '<item type="button" id="skin_rose" text="Rose" img="rose.gif"/>'
		+ '<item type="button" id="skin_summer" text="Summer" img="summer.gif"/>'
		+ '<item type="button" id="skin_violet" text="Violet" img="violet.gif"/>'
		+ '</item>'
		+'<item type="separator" id="button_separator_20" />'
		+ '<item type="buttonSelect" id="apps" text="Apps" img="apps.gif">'
		+ '<item type="button" id="app_erpadmin" text="Admin" img="admin.gif"/>'
		+ '<item type="button" id="app_useradmin" text="Admin" img="admin.gif"/>'
		+ '<item type="button" id="app_groupadmin" text="Admin" img="admin.gif"/>'
		+ '<item type="button" id="app_setup" text="Data Setup" img="datasetup.gif"/>'
		+ '<item type="button" id="app_template" text="Template" img="template.gif"/>'
		+ '<item type="button" id="app_channels" text="Channels" img="channels.gif"/>'
		+ '<item type="button" id="app_campaign" text="Campaign" img="campaign.gif"/>'
		+ '<item type="button" id="app_crm" text="CRM" img="project.gif"/>'
		+ '<item type="button" id="app_rfq" text="RFQ" img="quote.gif"/>'
		+ '<item type="button" id="app_store" text="Item Store" img="store.gif"/>'
		+ '<item type="button" id="app_travel" text="Business Travel " img="travel.gif"/>'
		+ '<item type="button" id="app_calendar" text="Calendar" img="calendar.gif"/>'
		//+ '<item type="button" id="app_report" text="Reports" img="report.gif"/>'
		+ '<item type="button" id="app_profile" text="Profile" img="profile.gif"/>'
		+ '</item>'
		+'<item type="separator" id="button_separator_26" />'
		+ '<item type="buttonSelect" id="widgets" text="My" img="my.gif">'
		+ '<item type="button" id="my_console" text="My Console" img="console.gif" />'
		//+ '<item type="button" id="my_subscription" text="My Subscription" />'
		//+ '<item type="button" id="my_dashboard" text="My Dashboard" />'
		//+ '<item type="button" id="my_logout" text="Logout" />'
		+ '</item>'
		+'<item type="separator" id="button_separator_28" />'
		+ '</toolbar>';
		  
  
  var master_toolbar_save= 
	      '<item type="separator" id="button_spacer" />'
	  	 + '<item type="button" id="grid_action_save"  text="Save" img="save.gif"/>'
	  	+ '<item type="buttonSelect" id="export" text="Export" img="export.gif">'
		+ '<item type="button" id="export_grid_master" text="Master Grid XLS" img="master.gif"/>'
		+ '<item type="button" id="export_grid_detail" text="Child Grid XLS" img="child.gif"/>'
		+ '<item type="button" id="export_pdf_master" text="Master Grid PDF" img="pdfm.gif"/>'
		+ '<item type="button" id="export_pdf_detail" text="Child Grid PDF" img="pdfc.gif"/>'
		+ '</item>'
		 + '<item type="button" id="grid_action_help"  text="Help" img="help.gif"  />'
		 + '<item type="separator" id="button_separator_302" />';
  

 
 function getToolbarXML(table, tabletype, isparent){
	 var toolbar_xml='<toolbar>';
	 if(tabletype=='table'&& !isparent  && allowAddDelete(table)){
		 toolbar_xml+=  '<item type="button" id="show_grand_child" text="More.." img="more2.gif"/>'
			 
			+'<item type="button" id="add_child" text="Add"  img="add3.gif"/>'
			+ '<item type="button" id="edit_child" text="Edit" img="edit.gif"/>'
			
			+ '<item type="button" id="remove_child" text="Remove" img="delete.gif"/>';
			//+ '<item type="buttonSelect" id="btn_perform" text="Perform Actions" img="action.gif"/>'
			
	 }else if(tabletype=='table'&& isparent  && allowAddDelete(table)){
		 toolbar_xml+= '<item type="button" id="grid_action_add" text="Add"  img="add3.gif"/>'
			 + '<item type="button" id="grid_action_edit" text="Edit" img="edit.gif"/>'
			+ '<item type="button" id="grid_action_remove" text="Remove" img="delete.gif"/>';
			//+ '<item type="buttonSelect" id="btn_perform" text="Perform Actions" img="action.gif"/>'
			
	 }else if(tabletype=='table'&& !isparent &&showmore(table)){
		 toolbar_xml+= '<item type="button" id="show_grand_child" text="More.." img="green.gif"/>'
			
	 }else if(tabletype=='view' &&!isparent && showmore(table)){
			 toolbar_xml+= '<item type="button" id="show_grand_child" text="More.." img="more2.gif"/>'
				
	 }else{
		 toolbar_xml+=  '<item type="button" id="button_separator_begin" img="button_spacer.gif"/>';
	 }
	 return toolbar_xml;
 } 

 
 function getToolbarMoreXML(table, tabletype, isparent){
	 var toolbar_more_xml='<item type="separator" id="button_spacer" />';
	 if(tabletype=='table'&& !isparent  && allowAddDelete(table)){
		 toolbar_more_xml+=  '<item type="button" id="show_grand_child" text="More.." img="green.gif"/>'
		 
	 }else if(tabletype=='table'&& !isparent &&showmore(table)){
		 toolbar_more_xml+= '<item type="button" id="show_grand_child" text="More.." img="green.gif"/>'
			//+ '<item type="separator" id="button_separator_29" />';
	 }else if(tabletype=='view' &&!isparent && showmore(table)){
		 toolbar_more_xml+= '<item type="button" id="show_grand_child" text="More.." img="green.gif"/>'
				//+ '<item type="separator" id="button_separator_29" />';
	 }
	 return toolbar_more_xml;
 } 
 function allowAddDelete(table){
	 var val=getMenuObjectAttribute(table, "allowadddelete");
	 if(val==null || val==true){
		 return true;
	 }
	 return false;
 }
 
 function showmore(table){
	 var val=getMenuObjectAttribute(table, "showmore");
	 if(val==null || val==true){
		 return true;
	 }
	 return false;
 }
 
 function isAttributeTrue(table,attribute){
	 var val=getMenuObjectAttribute(table,  "showmenu");
	 if(val==null || val==true){
		 return true;
	 }
	 return false;
 }
 function addMenuSelection(appid){
	 
	 var appmenus=menuconfig.menues[appid];
	 
	 if(appmenus){
		 if(panel_accordian){
			 panel_accordian.unload();
		 }
		
		 panel_accordian = left_cell.attachAccordion();
		 panels=null;
		 panels={};
		 panel_forms={};
		 search_panel_form=null;
		 current_app_id=appid;
		
		 
		 panel_accordian.attachEvent("onActive", function(id){
				
				 addPannelForm(id,appid);
				 
		   });
				
		 
		 for(var key in appmenus){
			 if(isAttributeTrue(key,"showmenu")){
				 var value=appmenus[key].label;
				 panels[key] =  panel_accordian.addItem(key,value);
			 }
			
			 
		 }
		
		 for(var key in appmenus){
			 if( panels[key] && panels[key].isOpened()){
				 
				 panels[key].close(false);
			 }
		 }
		
	 }
	
	 
 }
 

 function addPannelForm(id,appid){
	
	 current_app_id=appid;
	 search_panel_form=panel_forms[id];
	
	 if(!search_panel_form){
		 var str = [
			 { type:"combo" , name:"bqn", label:"BQN",  inputWidth:90  },
				{ type:"input" , name:"name", label:"Name", inputWidth:90 },
				{ type:"button" , name:id, value:"Search"  }
			];
		 search_panel_form =panels[id].attachForm(str);
		 hideFormItems(search_panel_form,id, "hidesearchcol");
		
		 search_panel_form.attachEvent("onButtonClick", function(name){
			 if(master_grid){
				 master_grid.clearAll();
			 }
			 workplace_toolbar_callback(name);
			 var text = panel_accordian.cells(name).getText();
			 master_grid_cell.setText(text);
			 //dhtmlx.message("onButtonClick event called, item name '"+name+"'<br>");
			 });
		 panel_forms[id]=search_panel_form;
			//dhtmlx.message("onActive event, cell: "+id);
		 
	 }
	 if(!search_panel_form.isItemHidden("bqn") &&current_bqn_no!=''){
		 search_panel_form.setItemValue("bqn",current_bqn_no);
	 }
	 var enterbqn=getAttributeTrueFalse(current_app_id,id, "enterbqn");
	 if(!search_panel_form.isItemHidden("bqn") &&!enterbqn ){
		 search_panel_form.setItemValue("bqn","");
	 }
 }
 
 function getSearchFilter(table){
	 var form=panel_forms[table];
	 var searchcols="bqn,name";
	 var customsearch= getMenuObjectAttribute(table, "customsearchfield");
	 var customfilter= getMenuObjectAttribute(table, "customfilter");
	 var enterbqn=getAttributeTrueFalse(current_app_id,table, "enterbqn");
	 var filter;
	 /*
	 if(table=="console"){
		 filter="genuser"+String.fromCharCode(1)+"="+String.fromCharCode(1)+username;
	 }
	 */
	 if(customfilter){
		 try{
			 filter=eval(customfilter);
		 }catch(err){}
		
	 }
	 if(form){
		 form.forEachItem(function(name){
			    // your code here
			 if(searchcols.indexOf(name)>=0 ||customsearch &&customsearch.indexOf(name)>=0){
				 var val= convert(form.getItemValue(name));
				 var operator="slike";
				 if(form.isItemHidden(name)||name!='bqn' ||enterbqn &&val!=''||!enterbqn){
					 if(name=='bqn' && !form.isItemHidden(name) && !val.indexOf('BQN-')>=0){
						 current_bqn_no=val;
						 val="BQN-"+val;
						 if(enterbqn){
							 operator="=";
						 }
					 }
					 if(val && val!=''){
						 if(filter){
							 filter+=String.fromCharCode(2)+name+String.fromCharCode(1)+operator+String.fromCharCode(1)+val
						 }else{
							 filter=name+String.fromCharCode(1)+operator+String.fromCharCode(1)+val 
						 }
					 }
					 
				 }else{
						 filter="noquery";
						 return; 
					 
				 }
				 
			 }
			}); 
	 }
	 
	 
	 return filter;
 }
 
 

 function hideGridColumns(grid,table){
	 
	 var hideitems=getMenuObjectAttribute(table, "gridcolhide");
	
	 if(hideitems && grid){
		 var colNum=grid.getColumnsNum();	
		 var items=hideitems.split(",");
		 if(items &&items.length>0){
			 for(var i=0;i<items.length;i++){
				 for( var j=0;j<colNum;j++){
						if(grid.getColumnId(j).indexOf(items[i])>=0 ){
							
							grid.setColumnHidden(j,true);
						}
						
					}
			 	}
			 }
		 }
	 showGridColumns(grid,table);
		
 }
 
function showGridColumns(grid,table){
	 
	 var showitems=getMenuObjectAttribute(table, "gridcolshow");
	
	 if(showitems && grid){
		 var colNum=grid.getColumnsNum();	
		
				 for( var j=0;j<colNum;j++){
					 var col=grid.getColumnId(j);
					 var colname=col.split(":")[0];
					
						if(colname &&showitems.indexOf(colname)<0 ){
							
							grid.setColumnHidden(j,true);
						}
						
						
				}
		 }
		
 }


function greyOutGridWithDisableColumns(grid){
	 
	if(grid){
		 grid.forEachRow(function(id){
		      
				 var colNum=grid.getColumnsNum();	
					
				 for( var j=0;j<colNum;j++){
					
					var coltype=grid.getColType(j);
				
					if(coltype &&coltype=='ro' ){
						
						grid.setCellTextStyle(id,j,"color:green;");
					}
					
					var colid=grid.getColumnId(j);
					
					var items=colid.split(":");
					
					if(items.length>2 &&items[1]=='false'){
						grid.setCellTextStyle(id,j,"color:brown;");
					}
						
						
				}
		    });
	}
		
}

function greyOutRowWithDisableColumns(grid,rowid){
	 
	if(grid && rowid){
		
		var colNum=grid.getColumnsNum();	
			
		 for( var j=0;j<colNum;j++){
			
			var coltype=grid.getColType(j);
		
			if(coltype &&coltype=='ro' ){
				
				grid.setCellTextStyle(rowid,j,"color:green;");
			}
			 
			 var objid=grid.cells(rowid,0).getValue();
			if((objid=='newid'||objid.length<32) && coltype=='ch'){
				
				grid.cells(rowid,j).setValue("0");
			
			}
				
		}
		 
	}
		
}
 function hideFormItems(form,table, key){
		var hideitems=getMenuObjectAttribute(table, key);
		 if(hideitems && form){
			
			 var items=hideitems.split(",");
			 if(form &&items &&items.length>0){
				 for(var i=0;i<items.length;i++){
					 form.forEachItem(function(name){
						 if(items[i] &&items[i]!="" && name.indexOf(items[i])>=0){
							 form.hideItem(name);
						 }
					 });
					 
					
				 }
				
			 }
			 
		 }
		 
		 if(!form.isItemHidden("bqn") &&current_bqn_no!=''){
			 form.setItemValue("bqn",current_bqn_no);
		 } 
 }
		 
 function hideToolbarItems(toolbar,table){
		var hideitems=getMenuObjectAttribute(table, "hidebuttons");
		 if(hideitems &&toolbar){
			
			 var buttons=hideitems.split(",");
			 if(toolbar &&buttons &&buttons.length>0){
				 
					 try{
					
					    for(var i in buttons){
					    	 toolbar.forEachItem(function(itemId){
								  if(itemId.indexOf(buttons[i])>=0){
									  toolbar.hideItem(itemId);
								  } 
					    		  
								});
					    }
						
						 
					 }catch(err){}
					
				 }
				
			 }
			 
	}
		
function callCustomRemoveHook(table,grid,objid){
	var customremoval=getMenuObjectAttribute(table, "removemethod");
	if(customremoval){
		eval(customremoval +"(table,grid,objid)");
	}
	
}	

function biderp_toolbar_callback(id){
	
     if(id.indexOf("app_")>=0){
        appid=id;
        workplace_toolbar.clearAll();
     	call_biderp_toolbar_app(id);
     	
     }else if(id.indexOf("skin_")>=0){
     
       call_biderp_toolbar_skin(id);
       
     }else if(id.indexOf("my_")>=0){
    	 
    	 call_biderp_toolbar_app(id);
    	 
     }else if(id.indexOf("currency")>=0){
 		var vals=id.split(":");
 		my_currency=vals[3];
		conversion_rate=vals[4];
 	}
     
	

}	


		
function call_biderp_toolbar_app(id){
     workplace_toolbar.clearAll();
     
    if(id=="app_erpadmin"){
		
		addMenuSelection("erpadmin");
    }else if(id=="app_useradmin" ){
		
    	addMenuSelection("useradmin");
    }else if(id=="app_groupadmin" ){
	
    	addMenuSelection("groupadmin");
	}else if(id=="app_setup"){
		
		addMenuSelection("seeddata");
	}else if(id=="app_template"){
		addMenuSelection("template");
		
	}else if(id=="app_channels"){
		addMenuSelection("channels");	
		
	}else if(id=="app_campaign"){
		
		addMenuSelection("campaign");
	}else if(id=="app_rfq"){
		
		addMenuSelection("rfq");
	}else if(id=="app_crm"){
		
		addMenuSelection("crm");
	
	}else if(id=="app_store"){
		
		 addMenuSelection("store");
	}else if(id=="app_travel"){
		
		 addMenuSelection("travel");
	}else if(id=="app_calendar"){
		
		 addMenuSelection("calendar");
		 
	}else if(id=="app_report"){
		
		addMenuSelection("report");
	}else if(id=="app_profile"){
		
		addMenuSelection("profile");
	}else if(id=="my_console"){
		
		openConsole();
	}
}

function call_biderp_toolbar_skin(id){
    
    skin=id.replace("skin_","");
    var image_path="./src/codebase/skins/web_"+skin+"/imgs/";
	switch_style(skin);
	child_node_tabbar.setImagePath(image_path);
	if(master_grid!=null &&master_grid.getSelectedRowId()){
		master_onSelectStateChanged_callback(master_grid.getSelectedRowId());
	}
	return false;
}

function workplace_toolbar_callback(callerid){
   
    disable_grid_action=false;
    var ids=callerid.split(":");
    var id=ids[0];
    if(ids.length>1 && ids[1]=='disable'){
      disable_grid_action=true;
    }
    
  	var tooltext;
  	if(workplace_toolbar){
  		try{
  			tooltext = workplace_toolbar.getItemText(callerid);
  			if(tooltext)
  				master_grid_cell.setText(tooltext);
  		}catch(err){
  			
  		}
  	}
  	
	var  gurl=www_url+'/rest/'+id.toLowerCase()+'/rows?token='+token;
    var filters=getSearchFilter(id.toLowerCase());
    if(filters=='noquery'){
    	master_grid.clearAll();
    	dhtmlx.alert("You must enter a valid Build Quotation No (BQN) in search filter! <br><br> Please search Quotation with/wo name filter and find BQN selecting any Quote for your project.")
    	return false;
    }
    if(filters && filters!=''){
    	gurl+="&searchfilter="+filters+"&bqn=BQN-"+current_bqn_no;
    }
	
	parent_table=id;
	current_parent_table=id;
	activity_parent_id=id;
	master_grid.clearAll();
	child_node_tabbar.clearAll();
    childtabs= {};
	childgrid = {};
	
    //master_grid.attachHeader("#select_filter,#select_filter,#numeric_filter,#text_filter,#text_filter,#select_filter");
	master_grid.load(gurl,'xml');
	
	
	// activity
	my_activity_grid.clearAll();
	my_activity_grid.addRow(id, [id,'Main Node='+id], null);
	master_detail_toolbar.clearAll();

}




function master_onEditCell_callback(stage, rId, cInd, nValue, oValue){
	
	
   if(nValue!=oValue){
		master_grid.setUserData(rId,"status","dirty");
		parent_rowid=rId;
	}
   
   addPopupToGrid(master_grid,rId,cInd,nValue,oValue);
   
  
}





function master_onSelectStateChanged_callback(rowId){
  		
  		if(master_grid.getUserData(parent_rowid,"status")=='dirty'){
  			//save any changes
       		handle_save_changes();
       		//reset current master row
   			master_grid.setUserData(rowId,"status","clean");
   			parent_rowid=rowId;
  		}
  		
   		master_grid.setColumnHidden(0,true);
   		if(master_grid.cells(rowId,0).getValue()!=''){
   			parent_objid=master_grid.cells(rowId,0).getValue();
   			current_parent_id=master_grid.cells(rowId,0).getValue();
   		}
   		child_node_tabbar.clearAll();
   		
   		if(current_parent_id!="newid"){
   		  //alert('master_onSelectStateChanged_callback');
   			addChildTabs();
   		}
   		
   		
}

function master_onRowSelect_callback(rowId,cellIndex){
  
  // reset anything need to change if any ceel of the row is selected
  
   validation_message="";
   last_grid=master_grid;
   last_table=current_parent_table;
   var enterbqn=getAttributeTrueFalse(current_app_id,current_parent_table, "enterbqn");
   if(master_grid &&!enterbqn ){
		 var val=getGridColumnValueForSelectedRow(master_grid,"bidquoteno");
		 if(val){
			 current_bqn_no=val.substring(4) ;
		 }
	 }
   var customselect= getMenuObjectAttribute(last_table.toLowerCase(), "customselect");
   if(customselect){
	   eval(customselect+"(last_grid,rowId,cellIndex)");
   }
  
	
}

function detail_onRowSelect_callback(rowId,cellIndex){
   
   validation_status=true;
   validation_message="";
   child_rowid=rowId;
   current_child_grid=childgrid[current_child_table];
  
   current_child_id=current_child_grid.cells(rowId,0).getValue();
   last_grid=current_child_grid;
   last_table=current_child_table;
   var customselect= getMenuObjectAttribute(last_table.toLowerCase(), "customselect");
   if(customselect){
	   eval(customselect+"(last_grid,rowId,cellIndex)");
   }
 
	
}

function my_activity_grid_onRowSelect_callback(id,ind){
   var objs=id.split(":");
  activity_parent_id=id;
  if(objs.length ==1){
  	parent_activity_callback(id);
  }else if(objs.length ==2){
	  openLink(objs[0].toLowerCase(),objs[1]);
  }else if(objs.length>=3){
  	  current_child_table=objs[0];
  	  current_child_id=objs[1];
  	  current_parent_id=objs[2];
  	  current_parent_table=my_activity_grid.getUserData(id,"parent");
  	  label=my_activity_grid.getUserData(id,"label");
      child_activity_callback(label);
	
  }
 
}

function my_task_grid_onRowSelect_callback(id,ind){
    var val=my_task_grid.cells(id,0).getValue();
    if(val.indexOf("(")>=0){
    	var objs=val.split("(");
    	if(objs.length==2){
    		parent_table="console";
      	    current_parent_table="console";
      	    var filter="name"+String.fromCharCode(1)+"="+String.fromCharCode(1)+objs[0].toLowerCase(); 
      		var gurl=www_url+"/rest/console/rows?token="+token+"&searchfilter="+filter;
      		master_grid.clearAll();
      		child_node_tabbar.clearAll();
      		master_detail_toolbar.clearAll();
      	    workplace_toolbar.clearAll();
      		childtabs= {};
	  		childgrid = {};
	  		master_grid_cell.setText("Console");
	  		master_detail_toolbar.clearAll();
	  		master_grid.load(gurl,'xml');
	  		checkEnableDisableButton();
	  		
    	}
    }
}

function child_activity_callback(label){
 
  var url=www_url+"/rest/"+current_child_table+"/filter?token="+token+"&filters="+current_parent_table+"='"+current_parent_id+"'";
  
  master_grid_cell.setText(label);
  master_grid.clearAll();
  master_detail_toolbar.clearAll();
  workplace_toolbar.clearAll();
  var xml=getSyncResponse(url);
  if(xml){
	  current_parent_table=current_child_table;
	  current_parent_id=current_child_id; 
  }
  master_grid.parse(xml);
  master_grid.setColumnHidden(0,true);

  //load child grid
  
  //set selected row in the new master grid
   master_grid.forEachRow(function(id){
      
       if(master_grid.cells(id,0).getValue()==current_child_id){
       		current_parent_id=current_child_id;
       	
       		master_grid.selectRow(id);
       }
    });
    
    //check buuton disable
    checkEnableDisableButton();
    
    child_node_tabbar.clearAll();
   		addChildTabs();

}

function parent_activity_callback(id){
	var tooltext;
	if(workplace_toolbar){
  		try{
  			tooltext = workplace_toolbar.getItemText(callerid);
  		}catch(err){
  			
  		}
  	}
	if(!tooltext){
		tooltext=id.charAt(0).toUpperCase()+id.substring(1);
	}
	var  gurl=www_url+'/rest/'+id+'/rows?token='+token;
	var filters=getSearchFilter(id);
	if(filters){
		gurl+="&searchfilter="+filters;
	}
	
	parent_table=id;
	current_parent_table=id;
	master_grid.clearAll();
	child_node_tabbar.clearAll();
	master_detail_toolbar.clearAll();
    workplace_toolbar.clearAll();
    childtabs= {};
	childgrid = {};
	master_grid_cell.setText(tooltext);
	master_detail_toolbar.clearAll();
	master_grid.load(gurl,'xml');
	checkEnableDisableButton();
	

}


function addChildTabs(){
     //below varibles will be initialized by ajax call
      
      childtables=master_grid.getUserData("","tabs").split(",");
      childcaptions=master_grid.getUserData("","tabnames").split(",");
    
	  
	  child_node_tabbar.clearAll();
	
	  
	  for (var i=0;i<childtables.length;i++){
		  
		 if(childtables[i] && childtables[i]!=""){
		 	if(i==0){
		 	    
			 	addNewTab(childtables[i],childcaptions[i],true);
			 	if(childtables[i]==current_parent_table){
			 	   addSummaryFacts(childtables[i]);
			 	
			 	}
			 	/*else{
			 		current_child_table=childtables[i];
			 		addGridToTabbar(childtables[i]);
			 	}*/
			 	
			 }else{
			 	addNewTab(childtables[i],childcaptions[i],false);
			 }
		  }else{
		  		//no tab exists
		  		master_detail_toolbar.clearAll();
		  }
	   }
		  
		
 }



function addNewTab(table,caption,isactive){
   
	child_node_tabbar.addTab(table,caption,'');
	 var target= child_node_tabbar.cells(table);
	 childtabs[table]=target;
	if(isactive &&child_node_tabbar.getActiveTab()!==table){
	    fact_name=null;
		child_node_tabbar.setTabActive(table);
	}
	

}
function tabbar_callback(id,last_id){
   var parent_data="\n";
   var child_data="\n";
   current_child_table=id;
   
   if(master_grid.getUserData(parent_rowid,"status")=='dirty'){
   		parent_data=collect_data(current_parent_table);
   }
  //collect data change
   if(last_id!=null && last_id!=current_parent_table){
		child_data=collect_data(last_id);
	}
	
	current_active_tab=childtabs[id];
	if(id==current_parent_table ){
	    fact_name=null;
		addSummaryFacts(id);
	}else{
		addGridToTabbar(id);
		
	}
	
	//save data after lodaing the selected grid
	if(parent_data!='\n' && child_data!='\n' ){
		ajax_post_handler(parent_data+ '\n'+child_data );
	}
	
  
}

function detail_onSelectStateChanged_callback(rowId){

	 
  		if(current_child_table){
  		   current_child_grid=childgrid[current_child_table];
  		 }
  		var objid=getGridColumnValueForSelectedRow(current_child_grid,"objid");
		if(objid && objid.length==32){
			current_child_grid.enableEditEvents(false,true,true);
		}
  		
  		if(child_rowid!='undefined' &&current_child_grid!=null && current_child_grid.getUserData(child_rowid,"status")=='dirty'){
  			//save any changes
  			reloadchild=true;
       		handle_save_changes();
  		}
   		
   		//reset current child row
   		current_child_grid.setUserData(rowId,"status","clean");
   	    child_rowid=rowId;
   	    enableChildToolbarButton('add_child');
}

function reloadChildGrid(){
     var objid=null;
     var val=null;
    
     try{
    	 var rowId=current_child_grid.getSelectedRowId();
         if(rowId){
        	 if(rowId.length>10){
        	       objid=current_child_grid.cells(rowId,0).getValue();
    	     }else{
    	        val=current_child_grid.cells(rowId,1).getValue();
    	     }
         }
     }catch(err){} 
     
     child_node_tabbar.setTabActive(current_child_table);
	 //current_child_grid.clearAll();
	 //var url=www_url+"/rest/"+current_parent_table+"/"+current_parent_id+"/"+current_child_table+"?token="+token;
     //var xml=getSyncResponse(url);
  	 //current_child_grid.parse(xml);
	try{
     current_child_grid.setColumnHidden(0,true);
     current_child_grid.forEachRow(function(id){
        if(objid!=null &&current_child_grid.cells(id,0).getValue()==objid){
       		current_child_grid.selectRow(id);
       		if(objid.length==32){
       		 setStatusBar("Updated "+current_child_table+":id:"+objid);
       		}
       		
       		 reloadchild=false;
       }else if(val!=null &&current_child_grid.cells(id,1).getValue()==val){
       		current_child_grid.selectRow(id);
       		if(val.length==32){
       			setStatusBar("Updated "+current_child_table+":id:"+val);
       		}
       		reloadchild=false;
       }
    });
    
     }catch(err){}
    
}


function addGridToTabbar(table){
   
  if(current_parent_id!=''){
    var url=www_url+"/rest/"+current_parent_table+"/"+current_parent_id+"/"+table+"?token="+token;
    var targettab=childtabs[table];
    var targetgrid = targettab.attachGrid();
	targetgrid.setIconsPath('./src/codebase/imgs/')
	targetgrid.setImagePath('./src/codebase/imgs/');;
	targetgrid.init();

	targetgrid.setDateFormat(dateformat);
	targetgrid.setUserData("","name",table);
	//targetgrid.enableEditEvents(true,false,true);
	dhtmlxError.catchError("LoadXML", function(){ return true; });
	addGridEvent(targetgrid,'detail_onSelectStateChanged','onSelectStateChanged');
	addGridEvent(targetgrid,'detail_onRowSelect','onRowSelect');
	targetgrid.attachEvent('onEditCell', function(stage, rId, cInd, nValue, oValue){
	  
	  var val=targetgrid.cells(rId,cInd).getValue();
		
	  if(stage==0  && (val=='new'||val=='0')){
	  		targetgrid.cells(rId,cInd).setValue('');
	  	}
	  	master_grid.setUserData(master_grid.getSelectedRowId(),"status","dirty");
	  
  		
	  	if(nValue!=oValue){
	  		
	  		
			detail_onEditCell_callback(stage, rId, cInd, nValue, oValue,table);
		}
		return true;
	});
	
	targetgrid.attachEvent("onRowCreated", function(rId,rObj,rXml){
	    // your code here
		if(targetgrid.getRowsNum()==0){
			 dhtmlx.alert("Your session got expired! Please click Refresh button on the browser!");
		}
		formatGridCurrency(targetgrid,rId);
		disableGridColumns(targetgrid, rId);
		 var customrowcreated= getMenuObjectAttribute(table, "customrowcreated");
		   if(customrowcreated){
			   eval(customrowcreated+"(targetgrid,rId)");
		   }
		   greyOutRowWithDisableColumns(targetgrid,rId);
	});
	/*
	targetgrid.attachEvent("onKeyPress", function(code,cFlag,sFlag){
		//to fillup combo autocomplete
  		fillupComboAuto(targetgrid,code);
	});
	*/
	targetgrid.attachEvent("onCheck", function(rId,cInd,state){
		targetgrid.selectRow(rId);
		targetgrid.setUserData(rId,"status","dirty");
		
	 });
	
	
	targetgrid.enableValidation(true );
	targetgrid.attachEvent("onValidationError", validate_me);
	targetgrid.attachEvent("onXLE", addChildActionButtons); 
	 
		if(current_child_table==table){
		    targetgrid.clearAll();
			targetgrid.load(url,'xml');
			targetgrid.setColumnHidden(0,true);
			childgrid[table]=targetgrid;
			
		}
	
	}
	
}

function fillupComboAuto(grid,input){
	 var colindex=grid.getSelectedCellIndex();
	 var rowid=grid.getSelectedRowId();
	 var value=grid.cells(rowid,colindex).getValue();
	 var combo = grid.getColumnCombo(colindex);
	 var colId= grid.getColumnId(colindex);
	 //dhtmlx.message(combo.getComboText() );
	 var chr = String.fromCharCode( input);
	 dhtmlx.message(chr);
	 //var value=combo.getComboText();
	 //make sure the id of the combo contains :auto in the gridColId
	 if(combo &&colId && colId.indexOf(":auto")>0 &&value){
		 var url=www_url+"/rest/"+current_parent_table+"/filter?token="+token+"&filters=name like '"+value+"%'";

		    combo.enableFilteringMode(true);
			combo.enableAutocomplete();
			combo.loadXML(url );
	 }
	
}
function validate_me(id,index, value,  rule){
		 var col=this.cell.parentNode.grid.getColLabel(index);
		 validation_status=false;
		 if(validation_message==""){
   		 	validation_message=this.cell.parentNode.grid.getColLabel(index)+"="+rule;
   		 }else if(validation_message.indexOf(col)<0){
   		    validation_message+=",\n"+ this.cell.parentNode.grid.getColLabel(index)+"="+rule;
   		 }
   		 return true;
        
}
	
function addChildActionButtons(grid_obj,count){
    if(grid_obj!=null){
    	var tabletype=grid_obj.getUserData("","tabletype");
    	var child_toolbar_xml=getToolbarXML(current_child_table, tabletype, false);
    	//var child_toolbar_more_xml=getToolbarMoreXML(current_child_table, tabletype, false);
		//var button_action=child_toolbar_xml+grid_obj.getUserData("","child.action")+child_toolbar_more_xml+"</toolbar>";
    	//var button_action=child_toolbar_xml+grid_obj.getUserData("","child.action")+"</toolbar>";
    	var button_action=child_toolbar_xml+"</toolbar>";
		var validator=grid_obj.getUserData("","grid.validator");
		var filters=grid_obj.getUserData("","filters");
		grid_obj.enableValidation(true);
        grid_obj.setColValidators(validator);
         if(filters){
        	grid_obj.attachHeader(filters);
        	grid_obj.filterByAll();
        }
        master_detail_toolbar.clearAll();
   		
   		if(current_child_table==current_parent_table){
   			var charts= fact_grid.getUserData("","charts").split(" ");
   			var toolbar_xml;
   			if(charts.length>0){
   			   toolbar_xml='<toolbar>'
						+ '<item type="buttonSelect" id="charts" text="Charts" img="green.gif">';
				for (var i=0;i<charts.length; i++){
				   toolbar_xml+="\n<item type=\"button\" id=\"chart:"+charts[i]+"\" text=\""+charts[i]+"\" />";
				}
				toolbar_xml+="\n</toolbar>";
   			}
   			master_detail_toolbar.loadXMLString(toolbar_xml, function(){});
   		
   		}else{
   		
   			master_detail_toolbar.loadXMLString(button_action, function(){});
   		}
   		
   		//add custom button
   		var chlid_action=grid_obj.getUserData("","child.action");
   		addPerformActionButtonSelect(master_detail_toolbar,current_child_table.toLowerCase(),chlid_action);
   	   // addButton(master_detail_toolbar,current_child_table.toLowerCase());
   	    hideToolbarItems(master_detail_toolbar,current_child_table.toLowerCase());
   	    hideGridColumns(grid_obj,current_child_table.toLowerCase());
   	    
   	    //custom method call
   	    var customonxle= getMenuObjectAttribute(current_child_table.toLowerCase(), "customonxle");
	   if(customonxle){
		   eval(customonxle+"(grid_obj,count)");
	   }
	   greyOutGridWithDisableColumns(grid_obj);
   		//draw bar for project plan or milestone
   		drawBarLine(grid_obj);
    	
	}
}

function detail_onEditCell_callback(stage, rId, cInd, nValue, oValue,table){
	reloadchild=true;
	current_child_grid=childgrid[table];
    current_child_grid.setUserData(rId,"status","dirty");
    //current_child_grid.selectCell(rId,cInd,0,0,true);
    child_rowid=rId;
    disableChildToolbarButton('add_child');
    addPopupToGrid(current_child_grid,rId,cInd,nValue,oValue);
}

function action_button_callback(name,command,grid){
	if(!grid) grid=master_grid;
	
	if(name=='grid_action_add'){
	  
	  handle_add_parent();
	  //handle_edit_parent(grid);
	
	}else if(name=='grid_action_save'){
	   handle_save_changes();
	   
	}else if(name=='grid_action_edit'){
		   handle_edit_parent(grid);
	
	}else if(name=='grid_action_samples'){
		   handle_save_samples();
		   
	}else if(name=='grid_action_remove'){
	   handle_remove_parent();
	
	//}else if(name=='grid_action_duplicate'){
	 //  handle_duplicate_parent();
	}else if(name=='export_grid_master'){ 
		   grid2excel(master_grid);
	}else if(name=='export_grid_detail'){
		   if(current_child_grid!=null&&current_child_grid.doesRowExist(0)==true){ 
		   		grid2excel(current_child_grid);
		   }else{
		        alert(' Please select a row in the child grid and then click this button');
		   }
	}else if(name=='export_pdf_master'){ 
		   grid2pdf(master_grid);
	}else if(name=='export_pdf_detail'){
		   if(current_child_grid!=null&&current_child_grid.doesRowExist(0)==true){ 
		   		grid2pdf(current_child_grid);
		   }else{
		        alert(' Please select a row in the child grid and then click this button');
		   }
	}else if(name=='grid_action_help'){
		
		openHelpLink('./src/help/help.html');
		   
	}else if(name.indexOf(current_parent_table)>=0 ||name.indexOf("custom:")>=0){
		
		 execute_button_action(grid,name,current_parent_table);
	
	}
}	

function execute_button_action(grid_obj,name,table){
	
	try{
		if(grid_obj && !grid_obj.getSelectedRowId()){
			dhtmlx.alert("Please select a row first and then click the button!");
			return false;
		}
	}catch(err){
		dhtmlx.alert("Please select a row first and then click the button!");
		return false;
	}
	
	var table=current_parent_table;
	if(name.indexOf("custom:")>=0){
		table=name.split(":")[1];
	}else if(name.indexOf(":")>=0 && name.split(":").length==3){
		table=name.split(":")[0];
	}
	
   var preaction=getMenuObjectAttribute(table, "prebuttonaction");
	  
   var postaction=getMenuObjectAttribute(table, "postbuttonaction");
	  
   var result;
   
   if(preaction){
	   result=eval(preaction+"(grid_obj,table,name)");
	   if(!result){
		  return false;
	   }
   
   }
	if(grid_obj){
		var rowId=grid_obj.getSelectedRowId();
	 	var colIdx=1;
	 	var ids=name.split(":");
	 	if(ids.length==3 && rowId){
    	 grid_obj.forEachCell(rowId,function(c){
	           var colid=grid_obj.getColumnId(colIdx);
	           if(colid &&colid.indexOf(ids[1])>=0){
	              grid_obj.cells(rowId,colIdx).setValue(ids[2]);
	              grid_obj.setUserData(rowId,"status","dirty");
	                //alert('action_button_callback='+colid+"="+ids[2]);
	           }
	            colIdx++;
	        });
    	 	if(current_child_table==table){
    	 		reloadchild=true;
    	 	}
	        handle_save_changes();
	        grid_obj.setUserData(rowId,"status","clean");
	    }else if(ids.length>=4 && rowId){
	    	try{
	    		eval(ids[3]+"(grid_obj,name)");
	    	}catch(err){
	    		dhtmlx.message("custom method :"+ids[3]+"(grid_obj,name)" + " not found!" + " err:"+err);
	    	}
	    } 
	 	
	 	if(postaction){
			   if(!preaction){
				   eval(postaction+"(grid_obj,table,name)");
			   }else if(preaction && result){
				   eval(postaction+"(grid_obj,table,name)");
			   }
			  
		   }
	 	
	 	//finally call custom action, this call is applicable to button which are defined in button_action.js
	 	
	 	callCustomAction(grid_obj,name,table);
	 }
}


function add_html_help_topic(cell, url){
	cell.attachHTMLString("");
	cell.attachURL(url);
}

function openHelpLink( url){
	html_cell.setText('Help');
	html_cell.attachHTMLString("");
	html_cell.attachURL(url);
}

function add_html_content(grid_obj){
	var data="<div style='width:100%;height:100%;overflow:auto'><table>" ;
	 
	 //html_cell.setWidth('300');
	 html_cell.attachHTMLString("");
	if(grid_obj){
		var rowId=grid_obj.getSelectedRowId();
	 	var colIdx=1;
	 	
	 	if( rowId){
    	 grid_obj.forEachCell(rowId,function(c){
	           var colid=grid_obj.getColumnId(colIdx);
	           var name=getColumnValueByGridRowId(grid_obj,"name",rowId)+"-"+rowId;
	           if(colid && !grid_obj.isColumnHidden(colIdx)){
	        	  var label=grid_obj.getColLabel(colIdx)+":&nbsp;";
		          var value=grid_obj.cells(rowId,colIdx).getValue();
		          var objid=grid_obj.cells(rowId,colIdx).getValue();
		          var coltype=grid_obj.getColType(colIdx);
		          if(colid.indexOf("password")>=0||colid.indexOf("secret")>=0){
		        	  value="******";
		          }
		          if(coltype=='ch'){
		        	  value="False (0)";
		        	  if(value=='1'){
		        		  value="True (1)";
		        	  }
		          }
	        	  var rel=colid.split(":")[0];
	        	  if(rel && rel.indexOf("2")>0){
	        		  var parenttable=rel.split("2")[1];
	        		  if(parenttable){
	        			
	        			  var combotext;
	        			  try{
	        				  var combo=grid_obj.getCombo(colIdx);
	        				  combotext=combo.get(objid);
	        				  if(combotext){
	        					  value=combotext;
	        				  }
	        				
	        			  }catch(err){
	        				  
	        			  }
	        			  if(value){
	        				  data+=getHtmlLink(parenttable,label,value, objid);
	        			  }else{
	        				  data+=getHtml(label,value, colid);
	        			  }
	        			  
	        		  }
	        	  }else if(value.indexOf("userdoc/")>=0){
	        		  data+=getHtmlDownloadLink(label,value, name);
	        		  
	        	  }else{
	        		
		             data+=getHtml(label,value, colid);
	        	  }
	        	 
	           }
	            colIdx++;
	        });
	       
	    }
	 }
	data+="</table></div>";
	html_cell.attachHTMLString(data);
}

function renderImage(item){
	var image="";
	try{
		var ww=www_url.split(".com")[0]+".com/";
		if(item.toLowerCase().indexOf(".jpg")>=0||item.toLowerCase().indexOf(".jpeg")>=0||
				item.toLowerCase().indexOf(".gif")>=0 || item.toLowerCase().indexOf(".bmp")>=0 
				||item.toLowerCase().indexOf(".tiff")>=0
				||item.indexOf(".jfif")>=0){
			var src=ww+item;
			image="<img src=\""+src+"\"/>";
			
		}
	}catch(err){}
	
	return image;
}
function renderHTMLFile(item){
	var html="";
	try{
		var ww=www_url.split(".com")[0]+".com/";
		if(item.toLowerCase().indexOf(".htm")>=0){
			var src=ww+item;
			html="<iframe src=\""+src+"\"/></iframe>";
			html+="<div class=\"recordlabel\"><a href=\""+src+"\" target=\"_blank\">See Full</a><div><br>";
		}
	}catch(err){}

	return html;
}
function getHtmlDownloadLink(label,value, name){
	var content="";
	
	var name=name.replace(/'/g ,"");
	if(value.indexOf("userdoc/")>=0){
		var items=value.split(";");
		if(items && items.length>0){
			 content="<tr><td class=\"recordlabel\">"+label+
				"</td><td class=\"recordtext\"><div id=\"downloadId\"></div>  Download :<br>";
			for(var i=0; i<items.length; i++){
				 content+=" <a href=\"#\" onClick=\"downloadLink('"+items[i]+"','"+name+"-"+i+"'); return false;\"> "+name+"-"+i+"<a><br>" ;
			}
			content+="</td></tr>";
			
			
			
		}else{
			 content="<tr><td class=\"recordlabel\">"+label+
				"</td><td class=\"recordtext\"><div id=\"downloadId\"></div><a href=\"#\" onClick=\"downloadLink('"+value+"','"+name+"'); return false;\">Download "+name+"<a></td></tr>" ;
			 
		}
		// render files
		if(items &&items.length>0){
			for(var i=0; i<items.length; i++){
				content+=renderImage(items[i]);
				content+=renderHTMLFile(items[i]);
				 
			}
		}else{
			content+=renderImage(value);
			content+=renderHTMLFile(value);
		}
	}
	
	return content;
}
function getHtml(label,value, colid){
	var content="<tr><td class=\"recordlabel\">"+label+"</td><td class=\"recordtext\">"+value+"</td></tr>" ;
		
	return content;
}

function getHtmlLink(parenttable,label,value, objid){
	var content="<tr><td class=\"recordlabel\">"+label+
	"</td><td class=\"recordtext\"><a href=\"#\" onClick=\"openLink('"+parenttable+"','"+objid+"'); return false;\">"+value+"<a></td></tr>" ;
	return content;
}
function master_detail_toolbar_callback(id){
	 if(id=='add_child' && handle_add_child()==true){
	   disableChildToolbarButton('add_child');
	   current_child_grid.enableEditEvents(true,false,true);
	   //handle_edit_child(current_child_grid);
	}else if(id=='show_grand_child' ){		
	   handle_show_grand_child();
	}else if(id=='remove_child'){
	   handle_remove_child();
	}else if(id=='edit_child') {
		handle_edit_child(current_child_grid);
	}else if(id.indexOf('chart:')>=0){
	
	   var charts= id.split(":");
    	if(charts.length>0 && charts[1]!=''){
    		var fact_chart_cell = fact_grid_layout.cells('b');
			fact_chart_cell.setText('Chart');
    		fact_chart.clearAll();
			var eachchart=fact_grid.getUserData("",charts[1]+".chart");
			var eachchartdata=fact_grid.getUserData("",charts[1]+".data");
			fact_chart = eval( "fact_chart_cell.attachChart("+eachchart+")");
			fact_chart.parse(eachchartdata, 'json');
		}
		
	   
	}else if(id.indexOf(current_child_table)>=0 || id.indexOf("custom:")>=0){
	   master_grid.setUserData(master_grid.getSelectedRowId(),"status","dirty");
	   execute_button_action(current_child_grid,id,current_child_table);
	}
	
}	
function handle_duplicate_parent(){
	dhtmlx.alert("Not Implemented!");
	/*
	var rowId=grid_obj.getSelectedRowId();
	 current_parent_id='newid';
	    var newId = (new Date()).valueOf();
	    master_grid.addRow(newId,"",0);
	    master_grid.selectRow(master_grid.getRowIndex(newId),false,false,true);
	    master_grid.cells(newId,0).setValue("newid");
	    var validator=master_grid.getUserData("","grid.validator").split(",");
	    var colIdx=1;
	     master_grid.forEachCell(newId,function(c){
		           var colid=master_grid.getColumnId(colIdx);
		           if(colid){
		                if(colid.indexOf('mainjobcode')>=0||colid.indexOf('subjobcode')>=0){
		                    master_grid.cells(newId,colIdx).setValue("0");
		                }else if(validator.length>=colIdx &&validator[colIdx]=='ValidInteger'){
		               		master_grid.cells(newId,colIdx).setValue("0");
		           		}else{
		             		 master_grid.cells(newId,colIdx).setValue("new");
		           		}
		           	}
		           
		            colIdx++;
		        });
		        */
}
function handle_add_parent(){
	
	master_grid.selectRow(0,false,false,true);
    current_parent_id='newid';
    var newId = (new Date()).valueOf();
    master_grid.addRow(newId,"",0);
    master_grid.selectRow(master_grid.getRowIndex(newId),false,false,true);
    master_grid.cells(newId,0).setValue("newid");
    var validator=master_grid.getUserData("","grid.validator").split(",");
    var colIdx=1;
    master_grid.enableEditEvents(true,false,true);
     master_grid.forEachCell(newId,function(c){
	           var colid=master_grid.getColumnId(colIdx);
	           var coltype=master_grid.getColType(colIdx);
	           if(colid){
	                if(colid.indexOf('mainjobcode')>=0||colid.indexOf('subjobcode')>=0){
	                    master_grid.cells(newId,colIdx).setValue("0");
	                }else if(validator.length>=colIdx &&validator[colIdx]=='ValidInteger'||coltype=='ch'){
	               		master_grid.cells(newId,colIdx).setValue("0");
	           		}else{
	             		 master_grid.cells(newId,colIdx).setValue("new");
	           		}
	           	}
	          
	            colIdx++;
	        });

}


function handle_add_child(){
   current_child_grid.selectRow(0,false,false,true);
   if(child_rowid==null||child_rowid=='undefined'){
   		alert('Please select a row first in the detail grid and then click add button!');
   		return false;
   }
    var newId = (new Date()).valueOf();
    current_child_grid.addRow(newId,"",0);
    current_child_grid.selectRow(current_child_grid.getRowIndex(newId),false,false,true);
    current_child_grid.cells(newId,0).setValue("newid");
    var colIdx=1;
    var validator=current_child_grid.getUserData("","grid.validator").split(",");
    
     current_child_grid.forEachCell(newId,function(c){
	           var colid=current_child_grid.getColumnId(colIdx);
	           var coltype=current_child_grid.getColType(colIdx);
	           if(colid){
	                if(colid.indexOf('mainjobcode')>=0||colid.indexOf('subjobcode')>=0){
	                    current_child_grid.cells(newId,colIdx).setValue("0");
	                }else if(validator.length>=colIdx &&validator[colIdx]=='ValidInteger'||coltype=='ch' ){
	               		current_child_grid.cells(newId,colIdx).setValue("0");
	           		}else{
	              		current_child_grid.cells(newId,colIdx).setValue("new");
	           		}
	           	}
	          
	            colIdx++;
	        });
	        
	 return true;
    
}
function handle_edit_parent(grid){
	addPopupForm(grid,current_parent_table, current_parent_table);
}

function handle_edit_child(grid){
	addPopupForm(grid,current_child_table, current_child_table);
}
/*
function handle_edit_grid_parent(){
	 var edit=false;
	 var id=master_grid.getSelectedRowId();
	 var colNum=master_grid.getColumnsNum();	
		
	 for( var j=0;j<colNum;j++){
		 var colid=master_grid.getColumnId(j);
          
           if(!edit && !master_grid.isColumnHidden(j) && master_grid.cells(id,j).isDisabled()){
        	   master_grid.selectCell(id,j);
        	   master_grid.enableEditEvents(true,false,true);
        	   master_grid.editCell();
        	   edit=true;
        	  
           }
	 } 
		 
	
}
function handle_edit_grid_child(){
	 var edit=false;
	 var id=current_child_grid.getSelectedRowId();
	 var colNum=current_child_grid.getColumnsNum();	
		
	 for( var j=0;j<colNum;j++){
		 var colid=current_child_grid.getColumnId(j);
          
           if(!edit && !current_child_grid.isColumnHidden(j) && current_child_grid.cells(id,j).isDisabled()){
        	   current_child_grid.selectCell(id,j);
        	   current_child_grid.enableEditEvents(true,false,true);
        	   current_child_grid.editCell();
        	   edit=true;
        	
           }
	 } 
}
*/
function handle_remove_parent(){
	dhtmlx.confirm("Confirm Delete! <br> If your current record has children, " +
			"can not be deleted. Delete children first.", function(result){
		
	if(result){
	    var selId = master_grid.getSelectedRowId();
	    var objid=master_grid.cells(selId,0).getValue();
	    var deletetabs=master_grid.getUserData("","deletetabs");
	    if(deletetabs!=null &&deletetabs.indexOf(current_parent_table.toLowerCase())>=0 &&objid!='newid'){
	       	var url=www_url+"/rest/"+current_parent_table+"/"+objid+"/delete?token="+token;
	 		var xml=getSyncResponse(url);
	       	var xmlDoc=getXMLDoc(xml);
	       	var objid=xmlDoc.getElementsByTagName("cell")[0].childNodes[0].nodeValue;
	       	if(objid!=-1){
	       		callCustomRemoveHook(current_parent_table,master_grid,objid);
	       		master_grid.deleteRow(selId);
	       		child_node_tabbar.clearAll();
	       		master_detail_toolbar.clearAll();
	       		setStatusBar("Deleted "+current_parent_table+":id:"+objid);
	       		
	       	}else{
	       		setStatusBar("Can't Delete "+current_parent_table+":id:"+master_grid.cells(selId,0).getValue());
	       	}
	    }else if(objid=='newid'){
	    	master_grid.deleteRow(selId);
	    }
	    if(last_table=='console'){
			   addBottomActivityGrid();
		}
	    
	    if(last_table){
	 	   var customhandle=getMenuObjectAttribute(last_table, "custompostdelete");
	 	   var grid;
	 	   if(customhandle){
	 		   if(last_table==current_parent_table){
	 			 	//markParentNodeAsDirty();
	 			   grid=master_grid;
	 				
	 			}else{
	 				grid=childgrid[table];
	 			}
	 		   eval(customhandle+"(grid)");
	 	   }
	    }
	  }
		
	});
   
}


function handle_remove_child(){
	dhtmlx.confirm("Confirm Delete! <br> If your current record has children, " +
			"can not be deleted. Delete children first!", function(result){
		if(result){
			 var selId = current_child_grid.getSelectedRowId();
			    var objid=current_child_grid.cells(selId,0).getValue();
			    var deletetabs=master_grid.getUserData("","deletetabs");
			    if(deletetabs!=null && deletetabs.indexOf(current_child_table.toLowerCase())>=0 &&objid!='newid'){
			       	var url=www_url+"/rest/"+current_child_table+"/"+objid+"/delete?token="+token;
			 		var xml=getSyncResponse(url);
			       	var xmlDoc=getXMLDoc(xml);
			       	var objid=xmlDoc.getElementsByTagName("cell")[0].childNodes[0].nodeValue;
			       	if(objid!=-1){
			       		callCustomRemoveHook(current_child_table,current_child_grid,objid);
			       		current_child_grid.deleteRow(selId);
			       		setStatusBar("Deleted "+current_child_table+":id:"+objid);
			       		
			       	}else{
			       		setStatusBar("Can't Delete "+current_child_table+":id:"+current_child_grid.cells(selId,0).getValue());
			       	}
			    }else if(objid=='newid'){
			    	current_child_grid.deleteRow(selId);
			    }
			     enableChildToolbarButton('add_child');
			     if(last_table=='console'){
					 addBottomActivityGrid();
				 }
		}
		
	});
   
    
}


function handle_show_grand_child(){
    if(activity_parent_id==null){
    	activity_parent_id=my_activity_grid.getParentId(current_parent_table);
    }
  if(!current_child_id){
	   current_child_grid.cells(current_child_grid.getSelectedRowId(),0).getValue();
   }
   
	var grand_child_id=current_child_table+ ":"+ current_child_id +":" +current_parent_id;
  
  if(my_activity_grid.getParentId(grand_child_id)==null &&current_child_id &&current_child_id.length>=32){
     var active_tab_id=child_node_tabbar.getActiveTab();
  	 var tab_label=child_node_tabbar.getLabel(active_tab_id);
  	 master_grid_cell.setText(tab_label);
  	
	 if(activity_parent_id && activity_parent_id.length<32){
    	my_activity_grid.addRow(grand_child_id,[grand_child_id,'Parent Node ID ='+current_parent_table+":"+current_parent_id +'; Child Node ID='+current_child_table+":"+current_child_id],my_activity_grid.getParentId(current_parent_table),current_parent_table,'folder.gif',false);
    }else{
     	my_activity_grid.addRow(grand_child_id,[grand_child_id,'Parent Node ID ='+current_parent_table+":"+current_parent_id +'; Child Node ID='+current_child_table+":"+current_child_id],null,activity_parent_id,'folder.gif',false);
     }

     //my_activity_grid.expandGroup(current_parent_table);
     //my_activity_grid.selectRow(grand_child_id,true,true,true);
     my_activity_grid.showRow(grand_child_id);
     my_activity_grid.selectRowById(grand_child_id);
     my_activity_grid.setUserData(grand_child_id,"parent",current_parent_table);
     my_activity_grid.setUserData(grand_child_id,"label",tab_label);
	
  	
   
     
  }
 
  // get selected rowid in child tab
  current_child_id= current_child_grid.cells(current_child_grid.getSelectedRowId(),0).getValue();
  activity_parent_id=grand_child_id;
  call_switch_child2parent();
}

function call_switch_child2parent(){

  enableGridActionButtons();
  
  var url=www_url+"/rest/"+current_child_table+"/filter?token="+token+"&filters="+current_parent_table+"='"+current_parent_id+"'";
  master_grid.clearAll();
  var xml=getSyncResponse(url);
   if(xml!=''){
	 master_detail_toolbar.clearAll();
	 workplace_toolbar.clearAll();
	 current_parent_table=current_child_table;
   	 var active_tab_id=child_node_tabbar.getActiveTab();
  	 var tab_label=child_node_tabbar.getLabel(active_tab_id);
   	 master_grid_cell.setText(tab_label);
 	 master_grid.parse(xml);
  	 master_grid.setColumnHidden(0,true);
  }
 
  //master_grid.load(url,'xml');
  current_parent_table=current_child_table;
  current_parent_id=current_child_id;
  
  //for maincode and subcode disable action buttons
  checkEnableDisableButton();
  
  //load child grid
  
  //set selected row in the new master grid
   master_grid.forEachRow(function(id){
      
       if(master_grid.cells(id,0).getValue()==current_child_id){
       		current_parent_id=current_child_id;
       		
       		 //reset
    		child_node_tabbar.clearAll();
            master_grid.selectRow(id);
            
   			
       }
    });

}

function checkEnableDisableButton(){

  var enabletables="projectcode";
  var disabletables="maincode subcode";
  
	if(enabletables.indexOf(current_parent_table.toLowerCase())>=0){
		 enableGridActionButtons();
	}else if(disabletables.indexOf(current_parent_table.toLowerCase())>=0){
  		disable_grid_action=true;
  		disableGridActionButtons();
    }
}

function collect_data(table,dataGrid){
	//var dataGrid;
	var isDirty=false;
	var validationerror='';
	var xml="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"+"<"+table+">\n";
	
	if(table=='undefined')
		return '\n';
	if(!dataGrid && table){
		if(table==current_parent_table){
		 	//markParentNodeAsDirty();
			dataGrid=master_grid;
			
		}else{
			dataGrid=childgrid[table];
			current_child_grid=dataGrid;
		}
	}	
	
	try{
		 var excludecol=getMenuObjectAttribute( table,"excludecolupdate");
		if(dataGrid!=null && (dataGrid.doesRowExist(0)==true ||dataGrid.getSelectedRowId()>=0)){
		 	var record=0;
		 	var moneycols=dataGrid.getUserData("","grid.moneycols");
		    dataGrid.forEachRow(function(id){
	      
	        var colIdx=0;
	        rowId=id;
	     
	       if(dataGrid.getUserData(id,"status")=='dirty'){
	         xml+="<record id=\""+record+"\">\n"
	         isDirty=true;
	         dataGrid.forEachCell(id,function(c){
	           var colid=dataGrid.getColumnId(colIdx);
	           if(colid){
	              var tags=colid.split(":");
	              if(tags.length==4){
	               var col=tags[0];
	               if(!excludecol ||excludecol &&excludecol.indexOf(col)<0){
		               var isnullable=tags[1];
		               var type=tags[2].replace("2","");
		               var colsize=tags[3];
		               var val=c.getValue();
		               val=convert(val);
		               if(moneycols && moneycols.indexOf(col)>=0){
		            	   try{
		            		   val=accounting.unformat(val, ".");
		            		   val=val/conversion_rate;
		            		 
		            	   }catch(err){
		            		   val=0;
		            	   }
		            	 
		               }
		               if(colsize && val.length>colsize && type.indexOf('VARCHAR')>=0){
		            	   var len=(val.length -colsize);
		            	   validationerror+= (validationerror==""?dataGrid.getColLabel(colIdx)  : ","+ dataGrid.getColLabel(colIdx) );
		            	   validationerror+= " Text size exceeds by "+len+ " allowable size="+colsize + ". Saved only "+ colsize + "charecters!";
		            	   val=val.substring(0,colsize);
		               }
		               if(isnullable=='false' &&  val.length==0 &&!moneycols){
		               	  validationerror+= (validationerror==""?dataGrid.getColLabel(colIdx) : ","+ dataGrid.getColLabel(colIdx) );
		               }
		               if(colIdx==0 && val=='newid'){
		               	val='';
		               }
		               if(val=='new' &&colid.indexOf(2)>0) {
		            	   val='null';
			               
		               }
		               xml+="<"+col+" isRequired=\""+(isnullable=='false'?"true":"false")+"\" type=\""+type+ "\">"+val+"</"+col+">\n"
		               
		               /*if(table==current_child_table &&col=="objid"){
		               	  xml+="<"+current_child_table+"2"+current_parent_table+" isRequired=\""+required+"\" type=\""+type+ "\">"+current_parent_id+"</"+col+">\n"
		               }
		               */
		              }
	              }
	           }
	           
	            colIdx++;
	        });
	        xml+="</record>\n";
	        record++;
	        dataGrid.setUserData(id,"status","clean");
	      } 
	      
	   });
	   
	    if(isDirty==true && validationerror==''){
	     	xml+="</"+table+">\n";
	    	return xml;
	    }else if(isDirty==true && validationerror!=''){
	    	 alert('Required fileds not set with value for object='+table+'.\n\n  Fields: '+ validationerror + "\n\n Please correct your data as below\n " +validation_message);
	    	 validation_message="";
	    	 return null;
	    }
    
      }
    }catch(e){
       return '\n';
    }
    return '\n';

}
function convert( str ) {
	  c = {'<':'!L', '>':'!G', '&':'!M', '"':'!D', "'":'!S',
	       '#':'!H' };
	  return str.replace( /[<&>'"#]/g, function(s) { return c[s]; } );
}



function markParentNodeAsDirty(){
	//set selected row in the new master grid
   master_grid.forEachRow(function(id){
       
        if(master_grid.getSelectedRowId()==id){
       	
       		master_grid.setUserData(id,"status","dirty");
       }
    });

}

function markRowSelected(objid){
	//set selected row in the new master grid
   master_grid.forEachRow(function(id){
     
        if(master_grid.cells(id,0).getValue()==objid){
       		
       		master_grid.selectRow(id);
       		
       }
    });

}

function markParentNodeAsClean(objid){
	//set selected row in the new master grid
   master_grid.forEachRow(function(id){
     try{
    	 if(master_grid.getSelectedRowId()){
    		 var rowid=master_grid.getSelectedRowId();
    		 
        		//alert(master_grid.cells(id,0).getValue()+"="+current_parent_id);
        		master_grid.cells(rowid,0).setValue(objid);
        		master_grid.setUserData(id,"status","clean");
        		master_grid.selectRow(rowid,false,false,false);
        		master_grid.selectRow(rowid,true,true,true);
        		if(current_parent_id!="newid"){
        	   		  //alert('master_onSelectStateChanged_callback');
        	   			addChildTabs();
        	   			current_parent_id=objid;
        	   	}
        		
        } 
     }catch(err){}
        
    });

}

function clearCell (rid,ind){
	  if( master_grid.cells(rid,ind).getValue()=='new'){
	  	master_grid.cells(rid,ind).setValue('');
	  }
	}

function  handle_save_changes(){
	reloadchild=true;
  	var parent_data= collect_data(current_parent_table);
  	
  	var child_data="\n";
  	
  	if(current_child_table!=current_parent_table){
    	child_data=collect_data(current_child_table);
    }
	
   if(parent_data=='\n' && child_data!='\n' ){  
		var posturl=www_url+"/rest/"+current_child_table+"/post?token="+token;
	   
    	ajax_post_handler(child_data, posturl);
  
    }else if(parent_data!='\n' ){  
		var posturl=www_url+"/rest/"+current_parent_table+"/post?token="+token;
	
    	ajax_post_handler(parent_data+ "\n"+child_data, posturl);
    }
   if(last_table){
	   var customhandle=getMenuObjectAttribute(last_table, "custompostsave");
	   var grid;
	   if(customhandle){
		   if(last_table==current_parent_table){
			 	//markParentNodeAsDirty();
			   grid=master_grid;
				
			}else{
				grid=childgrid[table];
			}
		   eval(customhandle+"(grid)");
	   }
   }
    

}

function ajax_post_handler(xml, url){
  
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
        var objid=xmlDoc.getElementsByTagName("cell")[0].childNodes[0].nodeValue;
        if(objid && !isBookMarked){
			current_parent_id=objid;
			setStatusBar("Updated "+current_parent_table+":id:"+current_parent_id);
			if(reloadchild==false){
				
				if(current_parent_id!="newid"){
			   		  //alert('master_onSelectStateChanged_callback');
			   			addChildTabs();
			   	}
			}else{
				
				 if(last_table!=current_parent_table){
					  reloadChildGrid();
				 }else{
					  markParentNodeAsClean(objid);
				 }
			}
	    
	    	
	    }else{
	    	if(!isBookMarked){
	    		setStatusBar(current_parent_table+":Update Failed!");
	    	}else{
	    		setStatusBar(current_parent_table+":Bookmarked!");
	    	}
	    	isBookMarked=false;
	    	
	    }
	    if(current_parent_table.toLowerCase()=='projectcode'){
	        var rowId=master_grid.getSelectedRowId();
	        if(rowId.length >10){
   				var  gurl=www_url+'/rest/'+current_parent_table.toLowerCase()+'/rows?token='+token;
   				master_grid.clearAll();
   				var xml=getSyncResponse(gurl);
  				master_grid.parse(xml);
  				if(objid.length>=32){
  					markRowSelected(objid);
  				}
  			}
   		}
	});
	
	//remove all data collection
	//xmldata={};
	
	}
	

}

function attachComoboEvent(combo,eventName, relation, filter){
	
	var loaded = false;
	combo.attachEvent(eventName,function(){
	    if(!loaded){
	         combo.loadXML(url);
	         loaded = true;
	    }
	})
}

function getXMLDoc(xml){
  var parser;
  var xmlDoc;
   if (window.DOMParser)
  		{
  			parser=new DOMParser();
  			xmlDoc=parser.parseFromString(xml,"text/xml");
  		}
	   else // Internet Explorer
  		{
  			xmlDoc=new ActiveXObject("Microsoft.XMLDOM");
  		    xmlDoc.async=false;
  			xmlDoc.loadXML(xml); 
        }
      return xmlDoc; 
}
 function getSyncResponse(url){
         var loader = dhtmlxAjax.getSync(url);
         var response = loader.xmlDoc.responseText;
         return(response);
 }
   
 
 function getSyncJsonResponse(url){
	 var loader = dhtmlxAjax.getSync(url);
	 var response = loader.xmlDoc.responseText;
	 var isjson=IsJsonString(response);
	 if(isjson) return response;
	 if (window.DOMParser)
		{
			parser=new DOMParser();
			xmlDoc=parser.parseFromString(response,"text/xml");
		}
	   else // Internet Explorer
		{
			xmlDoc=new ActiveXObject("Microsoft.XMLDOM");
		    xmlDoc.async=false;
			xmlDoc.loadXML(response); 
     } 
	
    
     return(xmlToJson(xmlDoc));
    
     
}
 
 function getASyncJsonResponse(url){
	 var loader = dhtmlxAjax.get(url);
	 var response = loader.xmlDoc.responseText;
	 var isjson=IsJsonString(response);
	 if(isjson) return response;
	 if (window.DOMParser)
		{
			parser=new DOMParser();
			xmlDoc=parser.parseFromString(response,"text/xml");
		}
	   else // Internet Explorer
		{
			xmlDoc=new ActiveXObject("Microsoft.XMLDOM");
		    xmlDoc.async=false;
			xmlDoc.loadXML(response); 
     } 
	
    
     return(xmlToJson(xmlDoc));
    
     
}
 
 function IsJsonString(str) {
	    try {
	        JSON.parse(str);
	    } catch (e) {
	        return false;
	    }
	    return true;
	}
     
function strtrim(str) {
   return str.replace(/^\s+|\s+$/g,"");
}

function setStatusBar(status){
	status_bar.setText(status);
}	

function openPortalLink(table,objid){
	    var filter="sampleportal2campaign"+String.fromCharCode(1)+"="+String.fromCharCode(1)+objid;
		var gurl=www_url+"/rest/"+table.toLowerCase()+"/rows?token="+token+"&searchfilter="+filter;
		master_grid.clearAll();
		current_parent_table=table;
		master_grid_cell.setText(table.charAt(0).toUpperCase()+table.substring(1));
		master_grid.load(gurl,'xml');
}

function openLink(table,objid){
      		var gurl=www_url+"/rest/"+table.toLowerCase()+"/"+objid+"/record?token="+token;
      		master_grid.clearAll();
      		child_node_tabbar.clearAll();
      		childtabs= {};
	  		childgrid = {};
	  		current_parent_table=table;
	  		master_grid_cell.setText(table.charAt(0).toUpperCase()+table.substring(1));
	  		master_detail_toolbar.clearAll();
	  		master_grid.load(gurl,'xml');
}

function setupMenu(){
		var url=www_url+"/rest/authorization/modules?token="+token+"&username="+username;
		modules=getSyncJsonResponse(url);
		setupMenuItems();
		setupCurrency();
		addCurrencyButtonSelect();
		fullname=modules.authorizations.authorization.fullname;
		
		
}
function setupMenuItems(){
	
		 if(biderp_toolbar &&modules){
			 var item=modules.authorizations.authorization.modules;
			 if(item){
				 var buttons=item.split(",");
				 try{
				
				    	biderp_toolbar.forEachItem(function(itemId){
							  if(itemId.indexOf("apps")>=0 ){
								  for(var i in buttons){
									  biderp_toolbar.hideListOption(itemId, buttons[i]);
								  }
							  } 
				    		  
							});
					 
				 }catch(err){}
				
			 }
			}
		
}

function setupCurrency(){
	
	 if(modules){
		 var item=modules.authorizations.authorization.currency;
		 if(item &&item.length>0){
			 var items=item.split(",");
			 try{
			    for(var i=0;i<items.length;i++){
			    	 if(items[i].indexOf(":default")>=0 ){
			    		 my_currency=items[i].split(":")[2];
			    		 conversion_rate=items[i].split(":")[3]
			    		 return;
			    	 }
			    }
			    	
				 
			 }catch(err){}
			
		 }
		}
	
}

function addCurrencyButtonSelect(){
	var pos=0;
	
	try{
		if(biderp_toolbar){
		
				
				biderp_toolbar.forEachItem(function(itemId){
				    // your code here
					var itemtext=biderp_toolbar.getItemText(itemId);
					if(itemtext.indexOf("My")>=0){
						 pos=biderp_toolbar.getPosition(itemId);
					}
				});
				
				if(modules){
					 var item=modules.authorizations.authorization.currency;
					 if(item){
						 var items=item.split(",");
						 var opts=[];
						 
						 try{
						    for(var i=0;i<items.length-1;i++){
						    	 var vals=items[i].split(":");
						    	 var val=vals[0]+" ("+vals[2]+")"
						    	 opts[i]=["currency:"+items[i],"obj",val,vals[0].toLowerCase()+".gif"];
						    }
						    	
							 
						 }catch(err){}
						
					 }
					}
				
				biderp_toolbar.addButtonSelect("btn_currency", pos, "Currency", opts, "currency.gif", null);
				
			
		}
	}catch(err){
		if(debug){
			dhtmlx.message(err +" : getButton : "+table);
		}
	}
	return null;
}


function openRefObject(){
	//dhtmlx.alert(refobjid);
	if(refobjid && refobjid!=null &&refobjid.indexOf("-")>=0){
		var vals=refobjid.split("-");
		if(refobjid.toLowerCase().indexOf("sampleportal")>=0){
			openPortalLink(vals[0],vals[1]);
			updateClickStatus();
		}else if(vals.length>1){
			openLink(vals[0],vals[1]);
		}
		
	}else{
		 current_app_id="console";
		 master_grid_cell.setText("Console");
		 master_grid.clearAll();
		 workplace_toolbar_callback("console");
	}
}

function sleepnow(millis)
{
    var date = new Date();
    var curDate = null;
    do { curDate = new Date(); }
    while(curDate-date < millis);
}

//Changes XML to JSON
//Modified version from here: http://davidwalsh.name/convert-xml-json
function xmlToJson(xml) {
	

	// Create the return object
	var obj = {};

	if (xml.nodeType == 1) { // element
		// do attributes
		if (xml.attributes.length > 0) {
		obj["@attributes"] = {};
			for (var j = 0; j < xml.attributes.length; j++) {
				var attribute = xml.attributes.item(j);
				obj["@attributes"][attribute.nodeName] = attribute.nodeValue;
			}
		}
	} else if (xml.nodeType == 3) { // text
		obj = xml.nodeValue;
	}

	// do children
	// If just one text node inside
	if (xml.hasChildNodes() && xml.childNodes.length === 1 && xml.childNodes[0].nodeType === 3) {
		obj = xml.childNodes[0].nodeValue;
	}
	else if (xml.hasChildNodes()) {
		for(var i = 0; i < xml.childNodes.length; i++) {
			var item = xml.childNodes.item(i);
			var nodeName = item.nodeName;
			if (typeof(obj[nodeName]) == "undefined") {
				obj[nodeName] = xmlToJson(item);
			} else {
				if (typeof(obj[nodeName].push) == "undefined") {
					var old = obj[nodeName];
					obj[nodeName] = [];
					obj[nodeName].push(old);
				}
				obj[nodeName].push(xmlToJson(item));
			}
		}
	}
	return obj;
}

function setExcelPassword(grid,rowId){
	var colIdx=0;
	if(grid && rowId){
		grid.forEachCell(rowId,function(c){
	         var colid=grid.getColumnId(colIdx);
	         if(colid &&colid.indexOf("password")>=0){
	        	 grid.setCellExcellType(rowId,colIdx,"passw");

	         }
	         colIdx++;
	         
	      });
	}
		
}

function setUserProfile(grid,count){

	var label=master_grid_cell.getText();
	var hideitems="user2company,user2supplier,user2privilegegroup,status,usertype";
	if(label &&label.indexOf("Profile Update")>=0){
		if(hideitems && grid){
			 var colNum=grid.getColumnsNum();	
			
				 for( var j=0;j<colNum;j++){
					    var id=grid.getColumnId(j);
					    var col=id.split(":")[0];
						if( col && hideitems.indexOf(col)>=0 ){
							 
							grid.setColumnHidden(j,true);
						}
						
					}
			 }
	}
}


function getToday(){
	var today = new Date();
	var dd = today.getDate();
	var mm = today.getMonth()+1; //January is 0!
	var yyyy = today.getFullYear();

	if(dd<10) {
	    dd = '0'+dd
	} 

	if(mm<10) {
	    mm = '0'+mm
	} 

	today = mm + '/' + dd + '/' + yyyy;
	return today;
}

function htmlencode(str) {
	try{
		return str.replace(/[&<>"?']/g, function($0) {
	        return "&" + {"&":"amp", "<":"lt", ">":"gt", '"':"quot", "'":"#39","?":"#63"}[$0] + ";";
	    });
		
	}catch(err){
		if(debug){
			dhtmlx.message("Error in encoding of string="+str);
		}
		return(str);
	}
    
}

