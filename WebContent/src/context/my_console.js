
var console_xml="<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
+"<console>"
+"<record id=\"0\">"
+"<objid isRequired=\"true\" type=\"RAW\"> </objid>"
+"<mqid isRequired=\"false\" type=\"RAW\">@mqid</mqid>"
+"<name isRequired=\"true\" type=\"VARCHAR\">@table</name>"
+"<title isRequired=\"true\" type=\"VARCHAR\">!Lb!G@name!L/b!G</title>"
+"<description isRequired=\"true\" type=\"VARCHAR\">!Lb!G@name!L/b!G</description>"
+"<status isRequired=\"true\" type=\"VARCHAR\">New</status>"
+"<keyobjid isRequired=\"false\" type=\"RAW\">@objid</keyobjid>"
+"<entrydate isRequired=\"false\" type=\"DATE\">"+GetFormattedDate()+"</entrydate>"
+"</record>"
+"</console>";

function GetFormattedDate() {
	var todayTime = new Date();
	var month = todayTime.getMonth() ;
	var day = todayTime.getDate();
	var year = todayTime.getFullYear();
	return month + "/" + day + "/" + year;
}

function addBookmarkObjectToConsole(){
	if(last_grid && last_table ){
		var recordcount= getConsoleRecordCount();
		if(recordcount && recordcount>0) {
			dhtmlx.alert("You already bookmarked this record!")
			return false;
		}
		consoleIsArchive=0;
		isBookMarked=true;
		var name=getGridColumnValueForSelectedRow(last_grid,"name");
		var objid=getGridColumnValueForSelectedRow(last_grid,"objid");
		var data=console_xml;
		data=data.replace("@objid",objid);
		data=data.replace(/@name/g,name);
		data=data.replace("@mqid",mqid);
		data=data.replace("@table",last_table.charAt(0).toUpperCase()+last_table.substring(1));
		var posturl=www_url+"/rest/console/post?token="+token;
		reloadchild=false;
		ajax_post_handler(data, posturl);
		sleepnow(2000);
		addBottomActivityGrid();
	}
	
}

function getConsoleRecordCount(){
	var keyobjid=getGridColumnValueForSelectedRow(last_grid,"objid");
	var filters=" keyobjid='"+keyobjid+"' and mqid='"+mqid+"'"
	var url=www_url+"/rest/console/recordcount?token="+token+"&filters="+filters;
	var data=getSyncJsonResponse(url);
	var data=JSON.parse(data);
	return(data.rowcount);
	
}


function openConsole(){
	consoleIsArchive=0;
	current_app_id="console";
	master_grid_cell.setText("Console");
	workplace_toolbar_callback("console");
	
}


function openConsoleObject(grid,name){
	var rowid=grid.getSelectedRowId();
	if(rowid){
		setColumnValueByGridRowId(grid,"isread",rowid, "1");
		var title=getGridColumnValueForSelectedRow(grid,"title");
		var description=getGridColumnValueForSelectedRow(grid,"description");
		title=removeBold(title);
		description=removeBold(description);
		setColumnValueByGridRowId(grid,"title",rowid, title);
		setColumnValueByGridRowId(grid,"status",rowid, "Opened");
		setColumnValueByGridRowId(grid,"description",rowid, description);
		grid.setUserData(rowid,"status","dirty");
		handle_save_changes();
	}
	current_child_id=getGridColumnValueForSelectedRow(grid,"keyobjid");
	current_child_table=getGridColumnValueForSelectedRow(grid,"name");
	grand_child_id=current_child_table+ ":"+ current_child_id;
	if(my_activity_grid.getParentId(grand_child_id)==null ){
		my_activity_grid.addRow(grand_child_id,[grand_child_id,'Parent Node ID ='+current_parent_table+":"+current_parent_id +'; Child Node ID='+current_child_table+":"+current_child_id],
				null,'console','folder.gif',false);
	}
	my_activity_grid.showRow(grand_child_id);
	my_activity_grid.selectRowById(grand_child_id);
	openLink(current_child_table.toLowerCase(),current_child_id);
	activity_parent_id=grand_child_id;
	
}

function archiveConsoleObject(grid,name){
	
	try{
		var rowid=grid.getSelectedRowId();
		if(grid && rowid){
			setColumnValueByGridRowId(grid,"isarchived",rowid, "1");
			grid.setUserData(rowid,"status","dirty");
			handle_save_changes();
			sleepnow(500);
			openConsole();
			addBottomActivityGrid();
		}
			
	} catch (err){
		
	}
}

function unarchiveConsoleObject(grid,name){
	
	try{
		var rowid=grid.getSelectedRowId();
		if(grid && rowid){
			setColumnValueByGridRowId(grid,"isarchived",rowid, "0");
			grid.setUserData(rowid,"status","dirty");
			handle_save_changes();
			sleepnow(500);
			openConsole();
			addBottomActivityGrid();
		}
			
	} catch (err){
		
	}
}

function loadArchiveConsoleObject(grid,name){
	   
	consoleIsArchive=1;
	current_app_id="console";
	master_grid_cell.setText("Archived Console");
	workplace_toolbar_callback("console");

}

