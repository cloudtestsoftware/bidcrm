       //https://dhtmlx.com/docs/products/visualDesigner/live/#a0m373 

        var skin;
		var main_layout;
		var Menu;
		var Menu_layout;
		var menu_layout_cell_1;
		var left_layout;
		var biderp_toolbar_cell;
		var biderp_toolbar;
		var left_cell;
		var fact_grid_layout;
		var fact_cell;
		var fact_grid;
		var fact_chart;
		var fact_name;
		var Workplace;
		var Workplace_cell;
		var Workplace_layout;
		var workplace_tool_layout;
		var workplace_tool_cell;
		var workplace_grid_cell;
		var workplace_grid_layout;
		var workplace_toolbar;
		var master_cell;  //project layout cell
		var master_layout;
		var master_grid_cell; //project grid cell
		var master_grid;  //project grid
		var activity_cell;
		var activity_layout;
		var panel_accordian;
		var panels={};
		var search_panel_form;
		var panel_forms={};
		var current_app_id;
		var active_panel_id;
		var my_activity_cell //Activity Cell
		var my_activity_grid; //Activity Grid
		var my_task_cell //Activity Cell
		var my_task_grid; //Activity Grid
		var child_nodes_cell;  //Child Node Cells
		var master_detail_toolbar;
		var child_node_tabbar; //Child tab bar
		var status_bar;
		var parent_url;
		var child_url;
		//html content
		var html_form;
		var html_layout;
		var html_cell;
		//initialize childs
		var childtabs= {};
		var childgrid = {};
		var xmldata={};
		var current_active_tab;
		var current_child_grid;
		var hidden_form;
		var post_reponse;
		
		var appid;
		var dateformat='%m/%d/%Y';
		
		//parent table initialized from workspace menu
		var parent_table;
		
		//PerentId when any record is selected from parent table
		var parent_objid='';
		var current_bqn_no='';
		//these varibles changes when child becomes parent
		var current_parent_table='';
		var current_parent_id='';
		var parent_rowid;
		
		//these are for current selected child
		var current_child_table;
		var current_child_id;
		var child_rowid;
		var activity_parent_id=null;
	    
	    //child tables
	    var childtables;
	    var childcaptions;
	
	   //action button
	    var action_btn_cell;
	    var action_form;
	    var disable_grid_action;
	    
	    //validation
	    var validation_status;
	    var validation_message;
		var reloadchild=false;
		//bookmark marker
		var last_grid;
		var last_table;
		var modules;
		var myPop;
		var myGrid;

       //console
		var consoleIsArchive=0;
		var isBookMarked=false;


function  addEvent(obj,objname, event){
   	  obj.attachEvent(event, function(id){
		eval(objname+"_callback(id)");
		return true;
	});
   }
   
 function addGridEvent(obj,objname,event){
	 var htmllabel;
    obj.attachEvent(event, function(rowId,cellIndex){
		eval(objname+"_callback(rowId,cellIndex)");
		add_html_content(obj);
		html_cell.setHeight("3000");
		if(objname=='master_onRowSelect'){
			htmllabel=master_grid_cell.getText();
			html_cell.setText(htmllabel);
		}else{
			 var active_tab_id=child_node_tabbar.getActiveTab();
			 var tab_label;
			 try{
				 tab_label=child_node_tabbar.getLabel(active_tab_id);
				 html_cell.setText(tab_label);
			 }catch(err){}
		}
			
		
		return false;
	});
    
 }

function addMainLayout(){
	dhtmlx.image_path='./src/codebase/imgs/';

	dhtmlx.skin = 'dhx_web';
	main_layout = new dhtmlXLayoutObject(document.getElementById('Container1') || document.body, '2U', 'dhx_web');
	main_layout.setEffect('collapse', true);
	
	Menu = main_layout.cells('a');
	Menu.setText('BidERP');
	Menu.setWidth('350');
	main_layout.setCollapsedText('a', 'Main Menu');
	Menu_layout = Menu.attachLayout('2E');
}

function addMenuLayout(){
    
    biderp_toolbar_cell = Menu_layout.cells('a');
	biderp_toolbar_cell.setHeight('25');
	biderp_toolbar_cell.setWidth('350');
	biderp_toolbar_cell.hideHeader();
	
	
	menu_layout_cell_1 = Menu_layout.cells('b');
	menu_layout_cell_1.setText('Menu');
	
    left_layout = menu_layout_cell_1.attachLayout('2U');
    
    activity_cell = left_layout.cells('b');
    //activity_cell.setHeight('250');
    //activity_cell.setText('My');
	activity_layout = activity_cell.attachLayout('2E');

	
	
}
/*
function addMenuLayout(){
    menu_layout_cell_1 = Menu_layout.cells('a');
	menu_layout_cell_1.setText('Menu');
	menu_layout_cell_1.setHeight('25');
	menu_layout_cell_1.setWidth('350');
	menu_layout_cell_1.hideHeader();
	menu_layout_cell_1.fixSize(1,0);
    left_layout = menu_layout_cell_1.attachLayout('2E');

    biderp_toolbar_cell = left_layout.cells('a');
	biderp_toolbar_cell.setHeight('15');
	biderp_toolbar_cell.hideHeader();
	biderp_toolbar_cell.fixSize(0,1);
	
}
*/

function addMainLeftToolbar(){
    biderp_toolbar = biderp_toolbar_cell.attachToolbar();
	biderp_toolbar.setIconsPath('./src/codebase/imgs/');
	addEvent(biderp_toolbar,"biderp_toolbar","onClick");
	biderp_toolbar.loadXMLString(biderp_toolbar_xml, function(){});
	
}

function addLeftLayout(){
    left_cell = left_layout.cells('a');
	left_cell.setText('Selection');
}

function addTopActivityGrid(){
	
	my_activity_cell = activity_layout.cells('a');
	my_activity_cell.setText('My Activity');
	my_activity_cell.setWidth('300');
	my_activity_cell.setHeight('300');
    my_activity_grid = my_activity_cell.attachGrid();
    my_activity_grid.setImagePath("./src/codebase/imgs/csh_bluebooks/");
    my_activity_grid.enableTreeGridLines();
    my_activity_grid.enableMultiselect(false);
    my_activity_grid.enableAutoHeigth(true);
    
   	my_activity_grid.setHeader("Master Node,Description");
	my_activity_grid.setInitWidths("150,350");
	my_activity_grid.setColAlign("left,left");
	my_activity_grid.setColTypes("tree,txt");
	my_activity_grid.setColSorting("str,str");
    my_activity_grid.init();
    my_activity_grid.attachEvent('onRowSelect', function(id, ind){
        
		my_activity_grid_onRowSelect_callback(id,ind);
	});
    
    //my_activity_grid.setSkin("dhx_skyblue");
    //my_activity_grid.loadXML("./data/activitygrid.xml");
}

function addBottomActivityGrid(){
	var html="<div id=\"btntreediv1\" style=\"width:300px;height:30px;background-color:white;\">"+
			 "<a href=\"#\" onClick='addBookmarkObjectToConsole()'><img border=\"0\" alt=\"Bookmark Object\" src=\"./src/codebase/imgs/bookmark2.gif\" width=\"16\" height=\"16\"></a>"+
			 "<img border=\"0\"  src=\"./src/codebase/imgs/left.gif\" width=\"5\" height=\"1\">"+
			 "<a href=\"#\" onClick='openConsole()'><img border=\"0\" alt=\"Open Console\" src=\"./src/codebase/imgs/rbmark2.gif\" width=\"16\" height=\"16\"></a>"+
			 "</div><div id=\"treegriddiv1\" ></div>";
	
	
	if(my_task_grid){
		my_task_grid.clearAll();
		
	}	
	my_task_cell = activity_layout.cells('b');
	my_task_cell.setText('My Tasks');
	my_task_cell.setWidth('300');
	my_task_cell.attachHTMLString(html);
	 my_task_grid = new dhtmlXGridObject("treegriddiv1");
   // my_task_grid = my_task_cell.attachGrid();
    my_task_grid.setImagePath("./src/codebase/imgs/csh_bluebooks/");
    my_task_grid.enableTreeGridLines();
    my_task_grid.enableMultiselect(false);
    my_task_grid.enableAutoHeigth(true);
    my_task_grid.enableAutoWidth(true);
    my_task_grid.enableCollSpan(true);
    
    //my_task_grid.enableSmartRendering(100);
   	my_task_grid.setHeader("Task Node,Description");
	my_task_grid.setInitWidths("150,350");
	my_task_grid.setColAlign("left,left");
	my_task_grid.setColTypes("tree,txt");
	my_task_grid.setColSorting("str,str");
    my_task_grid.init();
    my_task_grid.attachEvent('onRowSelect',my_task_grid_onRowSelect_callback);
    
    //my_task_grid.setStyle("background-color:navy;color:white; font-weight:bold;", "","color:red;", "");
   // my_task_grid.setSkin("dhx_skyblue");
    var url=www_url+'/rest/myconsole/rows?token='+token;
   
    my_task_grid.load(url,'xml');
    my_task_grid.expandAll();
    //my_task_grid.loadXML('./data/treegrid1.xml');
}

function addMyReminder(){
	reminder_cell = activity_layout.cells('c');
	reminder_cell.setText('My Reminder');
	
}

function addWorkPlace(){

    Workplace_cell = main_layout.cells('b');
	//Workplace.setText('Main Window');
   
    workplace_tool_layout = Workplace_cell.attachLayout('2E');

    workplace_tool_cell = workplace_tool_layout.cells('a');
	workplace_tool_cell.setText('Selections');
	workplace_tool_cell.setHeight('25');
	workplace_tool_cell.hideHeader();
	Workplace = workplace_tool_layout.cells('b');
    workplace_layout=Workplace.attachLayout('2U');
	workplace_grid_cell=workplace_layout.cells('a');    
	workplace_grid_layout=workplace_grid_cell.attachLayout('2E');  
	}
	
function addWorkplaceToolbar(){
    workplace_toolbar = workplace_tool_cell.attachToolbar();
	workplace_toolbar.setIconsPath('./src/codebase/imgs/');
	addEvent(workplace_toolbar,"action_button","onClick");
	workplace_toolbar.setSkin(dhtmlx.skin);
	
	

}

function addMasterGrid(){
    
	master_grid_cell = workplace_grid_layout.cells('a');
	
	master_grid_cell.setText("Workspace");
	master_grid_cell.setWidth('600');
    master_grid = master_grid_cell.attachGrid();
	master_grid.setIconsPath('./src/codebase/imgs/');
	//master_grid.setImagePath("./src/codebase/imgs/");
	master_grid.setColumnHidden(0,true);
	master_grid.setDateFormat(dateformat);
	//master_grid.enableEditEvents(true,false,true);
	master_grid.init();
	master_grid.attachEvent("onValidationError", validate_me);
	
	addGridEvent(master_grid,'master_onRowSelect','onRowSelect');
	
	master_grid.attachEvent('onEditCell', function(stage, rId, cInd, nValue, oValue){
		
		//disableGridColumns(master_grid, rId, cInd);
		var val=master_grid.cells(rId,cInd).getValue();
	    if(stage==0  && (val=='new' ||val=='0')){
	  		master_grid.cells(rId,cInd).setValue('');
	  	}
	   // master_grid.selectCell(rId,cInd,0,0,true);
		master_onEditCell_callback(stage, rId, cInd, nValue, oValue);
		return true;
		
	});
	
	master_grid.attachEvent("onRowCreated", function(rId,rObj,rXml){
	    // your code here
		if(master_grid.getRowsNum()==0){
			 dhtmlx.alert("Your session got expired! Please click Refresh button on the browser!");
		}
		master_grid.setColumnHidden(0,true);
		formatGridCurrency(master_grid,rId);
		disableGridColumns(master_grid, rId);
		last_grid=master_grid;
		//format currency
		
		 var customrowcreated= getMenuObjectAttribute(current_parent_table.toLowerCase(), "customrowcreated");
		   if(customrowcreated){
			   eval(customrowcreated+"(master_grid,rId)");
		   }
		   greyOutRowWithDisableColumns(master_grid,rId);
	});
	
	master_grid.attachEvent("onCheck", function(rId,cInd,state){
		master_grid.selectRow(rId);
		master_grid.setUserData(rId,"status","dirty");
		
	   });
	/*
	master_grid.attachEvent("onKeyPress", function(code,cInd,nValue){
		//to fillup combo autocomplete
  		fillupComboAuto(master_grid,code);
	});
  	*/
   master_grid.attachEvent("onXLE", function(grid_obj,count){
  		//addActionButtons(grid_obj);
	    addActionButtonToMasterToolbar(grid_obj);
	    hideToolbarItems(workplace_toolbar,current_parent_table.toLowerCase());
	    hideGridColumns(grid_obj,current_parent_table.toLowerCase());
  		disableGridActionButtons();
  		 var customonxle= getMenuObjectAttribute(current_parent_table.toLowerCase(), "customonxle");
		   if(customonxle){
			   eval(customonxle+"(master_grid,count)");
		   }
		   greyOutGridWithDisableColumns(master_grid);
  		
  	}); 
  
	master_grid.attachEvent('onSelectStateChanged', function(id){
		var objid=getGridColumnValueForSelectedRow(master_grid,"objid");
		if(objid && objid.length==32){
			master_grid.enableEditEvents(false,true,true);
		}
		master_onSelectStateChanged_callback(id);
		return true;
	});
	/*
	master_grid.attachEvent("onRowCreated", function(rId,rObj,rXml){
		master_grid.setColumnHidden(0,true);
	}); 
	*/
	
}	

function addHiddenForm(){

var str = [
		{ type:"hidden" , name:"body"  },
	];

   hidden_form = biderp_toolbar_cell.attachForm(str);
}



function addSummaryFacts(table){
   
   //define chart cells
    var  facturl=www_url+'/rest/'+current_parent_table+'/'+current_parent_id+'/summary?token='+token;   
    var targettab=childtabs[table];
    master_detail_toolbar.clearAll();
    if(!fact_name || fact_name!=(table+":"+current_parent_id)){
    	fact_grid_layout = targettab.attachLayout('2U');
    	fact_cell = fact_grid_layout.cells('a');
		fact_cell.setWidth('400');
		fact_cell.setText("Facts");
    	fact_grid = fact_cell.attachGrid();
    	fact_grid.attachEvent("onXLE", addChildActionButtons); 
    	fact_grid.attachEvent("onRowCreated", function(rId,rObj,rXml){
    	    // your code here
    		
    		formatSummaryCurrency(fact_grid,rId);
    		
    	});
    	
    	fact_grid.setImagePath("./src/codebase/imgs/csh_bluebooks/");
    	fact_grid.init();
    	if(current_parent_table){
        	var loader = dhtmlxAjax.getSync(facturl);
       	 	var response = loader.xmlDoc.responseText;
        	fact_grid.parse(response,'xml');
        	fact_name=table+":"+current_parent_id;
    		//fact_grid.load(facturl,'xml');
    	}

    	var fact_chart_cell = fact_grid_layout.cells('b');
		fact_chart_cell.setText('Chart');
    	var charts= fact_grid.getUserData("","charts").split(" ");
    	if(charts.length>0 && charts[0]!=''){
			var eachchart=fact_grid.getUserData("",charts[0]+".chart");
			var eachchartdata=fact_grid.getUserData("",charts[0]+".data");
			fact_chart = eval( "fact_chart_cell.attachChart("+eachchart+")");
			fact_chart.parse(eachchartdata, 'json');
		}
		
	 }

	
	}

function addActionButtonContainer(){

    html_cell =  workplace_layout.cells('b');
    html_cell.setText('Help');
    html_cell.setWidth('300');
    //html_cell.appendObject("html_data");
}	

/*
function addActionButtons(grid_obj){
    if(grid_obj!=null){
    	action_btn_cell = workplace_layout.cells('b');
		action_btn_cell.setText('Actions');
		action_btn_cell.setWidth('300');
		var button_action=grid_obj.getUserData("","button.action");
		var validator=grid_obj.getUserData("","grid.validator");
		var filters=grid_obj.getUserData("","filters");
		grid_obj.enableValidation(true);
        grid_obj.setColValidators(validator);
        if(filters){
        	grid_obj.attachHeader(filters);
        	grid_obj.filterByAll();
        }
       
    	action_form = eval("action_btn_cell.attachForm("+button_action+")");
       
		action_form.attachEvent('onButtonClick', function(name, command){
			action_button_callback(name,command,grid_obj);
		});
	}
}
*/
function addActionButtonToMasterToolbar(grid_obj){
	var grid_action=master_grid.getUserData("","child.action");
	var button_action;
	var tabletype=grid_obj.getUserData("","tabletype");
	var toolbar_xml=getToolbarXML(current_parent_table, tabletype, true);
	button_action=toolbar_xml+master_toolbar_save+"</toolbar>";
	/*if(grid_action){
		button_action=toolbar_xml+master_grid.getUserData("","child.action")+master_toolbar_save+"</toolbar>";
		
	}else{
		button_action=toolbar_xml+master_toolbar_save+"</toolbar>";
	}*/
	
	workplace_toolbar.clearAll();
	workplace_toolbar.loadXMLString(button_action, function(){
		workplace_toolbar.addSpacer("button_spacer");
	});
	
	var chlid_action=master_grid.getUserData("","child.action");
	addPerformActionButtonSelect(workplace_toolbar,current_parent_table.toLowerCase(),chlid_action);
	//addButton(workplace_toolbar,current_parent_table.toLowerCase());
	
	var button_action=grid_obj.getUserData("","button.action");
	var validator=grid_obj.getUserData("","grid.validator");
	var filters=grid_obj.getUserData("","filters");
	grid_obj.enableValidation(true);
    grid_obj.setColValidators(validator);
    if(filters){
    	grid_obj.attachHeader(filters);
    	grid_obj.filterByAll();
    	
    }
}

function disableGridColumns(grid,rowId,cellId){
	var disablecols=grid.getUserData("","disablecols");
	var coltypes='';
	if(disablecols){
		var cols=disablecols.split(",");
		for(var i=0; i<cols.length; i++){
			if(cols[i] &&cols[i]=='yes'){
				grid.cells(rowId,i).setDisabled(true);
			}
			/*else{
				var label=grid.getColLabel(i)+" *";
				grid.setColLabel(i,label);
			}*/
		}
	}
		
	
}


function disableGridActionButtons(){
      if(disable_grid_action==true){
	  	 //action_form.disableItem("grid_action_add");
	  	 //action_form.disableItem("grid_action_remove");
	 	 //action_form.disableItem("grid_action_save");
	  }
}

function enableGridActionButtons(){
         disable_grid_action=false;
	  	 //action_form.enableItem("grid_action_add");
	  	 //action_form.enableItem("grid_action_remove");
	 	 //action_form.enableItem("grid_action_save");
	 
}

function disableChildToolbarButton(id){
	try{
		if(master_detail_toolbar.isVisible(id)){
			master_detail_toolbar.disableItem(id);
		}
	}catch(err){}
 	
}
function enableChildToolbarButton(id){
	try{
		if(master_detail_toolbar.isVisible(id)){
			master_detail_toolbar.enableItem(id);
		}
	}catch(err){}
	
 	
}

function drawBarLine(grid){
  var targets="projectplan, milestone";
  var val;
  var colwidth=500;
  var w1;
  var w2;
  var colIdx=0;
  var barfound=false;
  var color;
  if(targets.indexOf(current_child_table.toLowerCase())>=0){
  
   	if(skin=='blue'||skin=='deep blue'){
    		color='#77cc88';
    		
   }else if(skin=='brown'){
    		color='#ff66cc';
    		
   }else if(skin=='rose'){
   
    		color='#88cc55';
    		
   }else if(skin=='oak'){
   
    		color='#22ee99';
   }else{
   		  color='#ee77cc';
   }
 
  grid.forEachRow(function(id){
      var cid=0;
      if(barfound==false){
            grid.forEachCell(id,function(colId){
                var bars=grid.cells(id,cid).getValue().split("@");
                if(bars.length>=2){
                   barfound=true;
                   colIdx=cid;
                }
                cid++;
            });
        }
     
     if(barfound==true){
     	grid.setColWidth(colIdx,colwidth);
      	var listval=grid.cells(id,colIdx).getValue().split("@");
      	if(listval.length>2){
      	    w1=colwidth*(listval[0]/listval[2]);
      	    w2=colwidth*(listval[1]/listval[2]);
      	    val="<span><font size='1'>"+listval[0]+"/"+listval[2]+"</font></span><img src='./src/codebase/imgs/line.gif' style='position:relative;width:20px;height:1px;'/><img src='./src/codebase/imgs/line.gif' style='position:relative;width:"+w1+"px;height:1px;'/><img src='./src/codebase/imgs/line.gif' style='position:relative;width:"+w2+"px;height:20px;background-color:"+color+";'/>";
      	   	//val="<span><font size='1'>"+listval[0]+"/"+listval[2]+"</font></span><img src='./src/codebase/imgs/line.gif' style='position:relative;width:"+w1+"px;height:1px;'/><img src='./src/codebase/imgs/red.gif' style='position:relative;width:"+w2+"px;height:30px;'/>";
      		grid.cells(id,colIdx).setValue(val);
      	}
      }
      
    }); 
 } 
 
}

function addTabBar(){

    child_nodes_cell = workplace_grid_layout.cells('b');
	child_nodes_cell.setText('Details');
	child_node_tabbar = child_nodes_cell.attachTabbar();
	child_node_tabbar.setImagePath('./src/codebase/skins/web_ocean/imgs/');
	child_node_tabbar.setAlign('left');
	child_node_tabbar.attachEvent("onTabClick", tabbar_callback);
	
	
}


function addMasterDetailToolbar(){

    master_detail_toolbar = child_nodes_cell.attachToolbar();
	master_detail_toolbar.setIconsPath('./src/codebase/imgs/');
	//master_detail_toolbar.setSkin('blue');
	addEvent(master_detail_toolbar,"master_detail_toolbar","onClick");
	
  

}


 function addStatusBar(){
    status_bar = main_layout.attachStatusBar();
	status_bar.setText('Success');
}	
 
 function addIntroduction(){
	 add_html_help_topic(left_cell, './src/help/welcome.jsp?fullname='+fullname);
	 add_html_help_topic(html_cell, './src/help/introduction.html');
	 
 }
 
 
 function eXcell_Pasw( cell )
 {
    if ( cell )
    {
       this.cell = cell;
       this.grid = this.cell.parentNode.grid;
    }
    this.setValue = function( val ){ this.setCValue( Array( 11 ).join( "*" ), val ); }
    this.getValue = function(){ return this.cell.innerHTML; }
    this.edit     = function()
    { 
       this.val = this.getValue();
       this.cell.innerHTML = "<input type='text' value='" + this.val + "' style='width:100px; height:18px;'>";
       this.cell.childNodes[0].onclick=function(e){ (e||event).cancelBubble=true; } //block onclick event
    }    
    this.detach   = function()
    {
       this.setValue( this.cell.value ); //set the new value
       return this.val!=this.getValue();
    }
 }

	

