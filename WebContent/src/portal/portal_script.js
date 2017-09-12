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
		var xmldata={};
		var dataforms={};
		var hidden_form;
		var post_reponse;
		var appid;
		var dateformat='%m/%d/%Y';
	    var formdata={};
		
		//parent table initialized from workspace menu
		var parent_table;
		
		//PerentId when any record is selected from parent table
		var parent_objid='';
		var current_bqn_no='';
		//these varibles changes when child becomes parent
		var current_parent_table='';
		var current_parent_id='';
		var parent_rowid;
		
		
	   //action button
	    var action_btn_cell;
	    var action_form;
	    var data_form;
	    var disable_grid_action;
	    
	    //validation
	    var validation_status;
	    var validation_message;
		var reloadchild=false;
		//bookmark marker
		var last_grid;
		var last_table;
		var modules;
		//public email
		var allowpublicemail;
		var ownername;
		var owneremail;
		var publicemail="gmail,yahoo,msn,live,comcast,aol,hotmail";
		


var portal_toolbar_save= 
		      '<item type="button" id="button_spacer" img="button_spacer.gif"/>'
		  	 + '<item type="button" id="grid_action_samples"  text="Place Order" img="save.gif"/>'
		  	+ '<item type="buttonSelect" id="export" text="Export" img="export.gif">'
			+ '<item type="button" id="export_grid_master" text="Master Grid XLS" img="master.gif"/>'
			+ '<item type="button" id="export_pdf_master" text="Master Grid PDF" img="pdfm.gif"/>'
			+ '</item>'
			 + '<item type="button" id="grid_crm"  text="BidCRM" img="help.gif"  />'
			 + '<item type="separator" id="button_separator_302" />';


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
		add_portal_content(obj);
		html_cell.setHeight("3000");
		if(objname=='portal_onRowSelect'){
			htmllabel=master_grid_cell.getText();
			html_cell.setText(htmllabel);
		}
			
		
		return true;
	});
    
 }

function addMainLayout(){
	dhtmlx.image_path='./src/codebase/imgs/';

	dhtmlx.skin = 'dhx_web';
	main_layout = new dhtmlXLayoutObject(document.getElementById('Container1') || document.body, '2U', 'dhx_web');
	main_layout.setEffect('collapse', true);
	
	Menu = main_layout.cells('a');
	Menu.setText('BidERP');
	Menu.setWidth('300');
	main_layout.setCollapsedText('a', 'Main Menu');
	Menu_layout = Menu.attachLayout('2E');
}

function addMenuLayout(){
    
    biderp_toolbar_cell = Menu_layout.cells('a');
	biderp_toolbar_cell.setHeight('25');
	biderp_toolbar_cell.setWidth('300');
	biderp_toolbar_cell.hideHeader();
	
	
	menu_layout_cell_1 = Menu_layout.cells('b');
	menu_layout_cell_1.setText('Menu');
	
    left_layout = menu_layout_cell_1.attachLayout('1C');
	
}

function addMainLeftToolbar(){
    biderp_toolbar = biderp_toolbar_cell.attachToolbar();
	biderp_toolbar.setIconsPath('./src/codebase/imgs/');
	addEvent(biderp_toolbar,"biderp_toolbar","onClick");
	//biderp_toolbar.loadXMLString(biderp_toolbar_xml, function(){});
	
}

function addLeftLayout(){
    left_cell = left_layout.cells('a');
	left_cell.setText('Help');
	
	var divstr="<div id=\"dataFormId\"  style=\"width:400px;height:100%;overflow:auto\"></div>"+
	"<div id=\"dataFormMessageId\" style='width:100%;height:100%;overflow:auto'></div>";
	//left_cell.attachHTMLString(divstr);
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
	workplace_grid_layout=workplace_grid_cell.attachLayout('1C');  
	}
	
function addWorkplaceToolbar(){
    workplace_toolbar = workplace_tool_cell.attachToolbar();
	workplace_toolbar.setIconsPath('./src/codebase/imgs/');
	addEvent(workplace_toolbar,"action_button","onClick");

}

function addMasterGrid(){
    
	master_grid_cell = workplace_grid_layout.cells('a');
	
	master_grid_cell.setText("Workspace");
	master_grid_cell.setWidth('600');
    master_grid = master_grid_cell.attachGrid();
	master_grid.setIconsPath('./src/codebase/imgs/');
	master_grid.setColTypes("ro,ro,ro,ed,ch,ro,ro,ro,ro");
	master_grid.setColumnHidden(0,true);
	master_grid.setColumnHidden(4,true);
	master_grid.setColumnHidden(5,true);
	master_grid.setColumnHidden(6,true);
	master_grid.setColumnHidden(7,true);
	master_grid.setColumnHidden(8,true);
	
	master_grid.setDateFormat(dateformat);
	master_grid.init();
	//master_grid.attachEvent("onValidationError", validate_me);
	
	addGridEvent(master_grid,'portal_onRowSelect','onRowSelect');
	
	master_grid.attachEvent('onEditCell', function(stage, rId, cInd, nValue, oValue){
		
		var val=master_grid.cells(rId,cInd).getValue();
	    if(stage==0  && (val=='new' ||val=='0')){
	  		master_grid.cells(rId,cInd).setValue('');
	  	}
	 
		return true;
		
	});
	
	master_grid.attachEvent("onRowCreated", function(rId,rObj,rXml){
	   
		formatGridCurrency(master_grid,rId);
		
	});
	
   master_grid.attachEvent("onXLE", function(grid_obj,count){
	  // master_grid.setInitWidths("1,250,350,100,1,1,1,1,1");
	    addActionButtonToMasterToolbar(grid_obj);
	   
		  greyOutPortalGridWithDisableColumns(master_grid);
  		
  	}); 
  
   master_grid.attachEvent("onCheck", function(rId,cInd,state){
		
		master_grid.setUserData(rId,"status","dirty");
		
	   });
	master_grid.attachEvent('onSelectStateChanged', function(id){
	
		//master_onSelectStateChanged_callback(id);
		return true;
	});
	/*
	master_grid.attachEvent("onRowCreated", function(rId,rObj,rXml){
		master_grid.setColumnHidden(0,true);
	}); 
	*/
	
}


function addDataForm(table){
   
	if(dataforms[table]){
		data_form=dataforms[table];
	}
  
	if(data_form){
		data_form.unload();
	}
	
   	var  furl=www_url+'/rest/'+table+'/form?token='+token;
   
	data_form=html_cell.attachForm();
	
    data_form.loadStruct(furl,"xml");
 
    data_form.attachEvent("onFocus", function(name, value){
    	//setFormRelation(table);
	});
    
    data_form.attachEvent("onChange", function(name, value){
    	//callCustomFormOnChange(table);
	});
    data_form.attachEvent("onXLE", function(){
    	callCustomFormLoad(table);
    	hideEditorToolbar(data_form);
    	//setDataFormFieldWidth(table);
	});
	
    dataforms[table]=data_form;
	
   	
}


function portal_onRowSelect_callback(rowId,cellIndex){
	  
	  // reset anything need to change if any ceel of the row is selected
	 var grid=master_grid;
	 grid.forEachRow(function(id){
	      
		 var colNum=grid.getColumnsNum();	
			
		 for( var j=0;j<colNum;j++){
			 var coltype=grid.getColType(j)
			 if(coltype=="ed"){
				 	grid.selectCell(id,j);
				 	grid.editCell();
				}
		 } 
		 
	 });
		
	}

function greyOutPortalGridWithDisableColumns(grid){
	 var coltypes="";
	if(grid){
		 grid.forEachRow(function(id){
		      
				 var colNum=grid.getColumnsNum();	
					
				 for( var j=0;j<colNum;j++){
					
					 if(j==0){
						 coltypes="ro";
					 }
					var colid=grid.getColumnId(j);
					var items=colid.split(":");
					if(colid.indexOf("addtocart")>=0){
						coltypes+=",ch";
						grid.cells(id,j).setValue(""); 
						
					}else if(colid.indexOf("qntorder")>=0){
						coltypes+=",ed";
						
					}else if(j>0){
						coltypes+=",ro";
					}
				
						
				}
				 grid.setColTypes(coltypes);
				 grid.cells(id,4).setValue("0"); 
				 grid.setCellTextStyle(id,3,"color:red;");
				 //grid.cells(id,3).setValue("?"); 
				 
		    });
		 
		 grid.forEachRow(function(id){
		      
			 var colNum=grid.getColumnsNum();	
				
			 for( var j=0;j<colNum;j++){
				 var coltype=grid.getColType(j)
				 if(coltype=="ed"){
					 	grid.selectCell(id,j);
					 	grid.editCell();
					}
			 } 
			 
		 });
	}
		
}

function addHiddenForm(){

var str = [
		{ type:"hidden" , name:"body"  },
	];

   hidden_form = biderp_toolbar_cell.attachForm(str);
}




function addActionButtonContainer(){

    html_cell =  workplace_layout.cells('b');
    html_cell.setText('Order Delivery Form');
    html_cell.setWidth('350');
    //html_cell.appendObject("html_data");
}	

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
function addActionButtonToMasterToolbar(grid_obj){
	var grid_action=master_grid.getUserData("","child.action");
	var button_action;
	var tabletype=grid_obj.getUserData("","tabletype");
	var toolbar_xml=getToolbarXML(current_parent_table, tabletype, true);
	if(grid_action){
		button_action=toolbar_xml+master_grid.getUserData("","child.action")+portal_toolbar_save+"</toolbar>";
	}else{
		button_action=toolbar_xml+portal_toolbar_save+"</toolbar>";
	}
	
	
	workplace_toolbar.clearAll();
	workplace_toolbar.loadXMLString(button_action, function(){
		workplace_toolbar.addSpacer("button_spacer");
	});
	
	addButton(workplace_toolbar,current_parent_table.toLowerCase());
	
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
 	master_detail_toolbar.disableItem(id);
}
function enableChildToolbarButton(id){
	if(master_detail_toolbar.isVisible(id)){
		master_detail_toolbar.enableItem(id);
	}
 	
}

 function addStatusBar(){
    status_bar = main_layout.attachStatusBar();
	status_bar.setText('Success');
}	
 
 function addIntroduction(){
	 addDataForm("sampleorder");
	 add_html_help_topic(left_cell, './src/help/sampleorder.html');
	 //add_html_help_topic(html_cell, './src/help/sampleorder.html');
	 
 }
 
 
 function callCustomFormOnChange(table){
		hideFormCols(table);
		hideFormButton(table);
		disableFormCols(table);
		disableFormButton(table);
		enableEditorToolbar(table);
		var custom_method=getFormOnChange(menuid, table);
	    if(custom_method){
	    	eval(custom_method+"(\""+table+"\")");
	    }
	}

	function callCustomFormLoad(table){
		hideFormCols(table);
		//hideFormButton(table);
		//disableFormCols(table);
		//disableFormButton(table);
		//enableEditorToolbar(table);
		
	}
	function hideFormCols(table){
		var hidecols="sampleorder2campaign,billingaddress,billingcity,billingstate,billingcountry,billingzipcode,quicknote,status,agentid";
		 if(hidecols){
			 var form=data_form;
			 var cols=hidecols.split(",");
			 if(form &&cols &&cols.length>0){
				 for(var i=0;i<cols.length;i++){
					 form.forEachItem(function(name){
						 if(name.indexOf(cols[i])>=0){
							 form.hideItem(name);
						 }
					 });
				 }
				
			 }
			 
		 }
	}


	function hideFormButton(table){
		var hidebuttons=getFormButtonHide(menuid, table);
		 if(hidebuttons){
			 var form=getWidgetForm(table);
			 var butons=hidebuttons.split(",");
			 if(form &&butons &&butons.length>0){
				 for(var i=0;i<butons.length;i++){
					 form.forEachItem(function(name){
						 if(butons[i] &&butons[i]!="" && name.indexOf(butons[i])>=0){
							 form.hideItem(name);
						 }
					 });
					 
					 //hide toolbar button if any
					 try{
						 widget_toolbar.hideItem(butons[i]);
						 
					 }catch(err){}
					
				 }
				
			 }
			 
		 }
		
	}

	function disableFormButton(table){
		var disablebuttons=getFormButtonDisabled(menuid, table);
		 if(disablebuttons){
			 var form=getWidgetForm(table);
			 var butons=disablebuttons.split(",");
			 if(form &&butons &&butons.length>0){
				 for(var i=0;i<butons.length;i++){
					 form.forEachItem(function(name){
						 if(name.indexOf(butons[i])>=0){
							 form.disableItem(name);
						 }
					 });
				 }
				
			 }
			 
		 }
		
	}
	function showFormButton(table, button){
		
		 if(button){
			 var form=getWidgetForm(table);
			 var butons=button.split(",");
			 if(form &&butons &&butons.length>0){
				 for(var i=0;i<butons.length;i++){
					 form.forEachItem(function(name){
						 if(name.indexOf(butons[i])>=0){
							 form.showItem(name);
						 }
					 });
				 }
				
			 }
			 
		 }
		
	}



	function collect_form_data(table,target){
		var data_form=dataforms[table];
		var validationerror="";
		validation_message="";
		formdata=null;
		formdata={};
		var xml="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"+"<"+(target&&target!=null?target:table)+">\n";
		 xml+="<record id=\"0\">\n"
		if(table=='undefined' ||!data_form)
			return '\n';
		
		try{
		  data_form.forEachItem(function(name){
	          var nulldate=false; 
	          var colid=name;
	          var excludecol=getMenuObjectAttributeByMenuId("portal", table,"excludecolupdate");
		           if(colid){
		        	   
		              var tags=colid.split(":");
		             
		              if(tags.length>=4){
			               var col=tags[1];
			               if(!excludecol ||excludecol &&excludecol.indexOf(col)<0){
				               var isnullable=tags[2];
				               var type=tags[3].replace("2","");
				               var coltype=data_form.getItemType(colid);
				               var inputlen=0
				              
				               var val =null;
				               try{
				            	  val =data_form.getEditor(colid).getContent();
				            	  try{
				            		 
				            		  val=createTextVersion(val);
				            		  val = JSON.parse(val);
				            		  val=JSON.stringify(val);
				            		 
				            	  }catch(err){ }
				            	 
				            	  
				               }catch(err){
				            	   val =data_form.getItemValue(colid);
				               }
				               if(tags[4]){
				            	   inputlen=tags[4]
				            	   
				               }
				               if(colid.indexOf('DATE')>=0){
				            	   try{
				            		   val= data_form.getCalendar(colid).getDate(true);
				            		   if(val==null){
				            			   nulldate=true;  
				            		   }
				            	   }catch(err){
				            		   validation_message="Please enter date for "+col;
					            	   validationerror+= (validationerror==""?col : ","+col );
				            	   }
				               }else if(colid.toLowerCase().indexOf('email')>=0){
				            	  if(allowpublicemail && allowpublicemail=='0'){
				            		 var items=publicemail.split(",");
				            		 for(var i in items){
				            			 var item=items[i];
				            			 if(val && val.toLowerCase().indexOf(item.toLowerCase())>=0){
				            				 validation_message="Please do not use public email. Use your office email only! "+col;
							            	 validationerror+= (validationerror==""?col : ","+col );
				            			 }
				            		 }
				            	  }
				            	  
				               }else if(colid.indexOf('NUMBER')>=0&&val!='' && !isNumber(val)){
				            	   validation_message="Please enter a number for "+col;
				            	   validationerror+= (validationerror==""?col : ","+col );
				            	  
				               }
				               if(col=='objid' && val=='newid'||colid.indexOf('DATE')>=0 &&val==null){
				                 val='';
				               }
				               if(isnullable=='false' && val=='' && col!='objid'){
				               	  validationerror+= (validationerror==""?col : ","+col );
				               }
				               val=htmlencode(val);
				               if(val.length>inputlen &&colid.indexOf(":VARCHAR")>0){
			            		   val=val.substr(0,inputlen);
			            		   dhtmlx.message("Your size of text for col="+col+" cut shot to the limit="+inputlen);
			            	   }
				               if(col.indexOf("2")<0 &&nulldate==false||col.indexOf("2")>0 &&val!=''){
				               		xml+="<"+col+" isRequired=\""+(isnullable=='false'?"true":"false")+"\" type=\""+type+ "\">"+val+"</"+col+">\n"
				               		formdata[col]=val;
				               }
			               }
		              }
		           }
		           
		          
		        });
		        xml+="</record>\n";
	      
			
			if(validationerror==''){
		     	xml+="</"+(target&&target!=null?target:table)+">\n";
		    	return xml;
		    }else if( validationerror!=''){
		    	 var error_msg='Required fields not set with value for object='+(target&&target!=null?target:table)+'.\n\n  Fields: '+ validationerror + ".\n\n Please correct your data as below.\n " +validation_message;
		    	 dhtmlx.alert(error_msg);
		    	 validation_message="";
		    	 return null;
		    }
		}catch(err){
		  dhtmlx.alert(err);
		  return null;
		}	
		
	    return null;

	}
    

	function hideEditorToolbarButton(form){
		form.forEachItem(function(name){
			try{
				var editor=form.getEditor(name);
				toolbar = editor.tb;
			      toolbar.forEachItem(function(itemId){
			         toolbar.hideItem(itemId);
			      });
			    
			}catch(err){}
			
		});
	}
	function hideEditorToolbar(form){
		form.forEachItem(function(name){
			try{
			  var editor=form.getEditor(name);
			  var eh=editor.tb.base.parentNode.parentNode.style.height
		      editor.tb.base.parentNode.style.height="0";
		      editor.base.style.height=eval(eh.split("px")[0]-30);
		      //editor.height=eval(eh.split("px")[0]-80);
			  /*editor.tb.base.parentNode.style.overflow="auto";
			  editor.tb.base.parentNode.style.resize="both";
			  editor.tb.base.style.overflow="auto";
			  editor.tb.base.style.resize="both"; 
			  */
			}catch(err){}
			
		});
	}
	
	function  handle_save_samples(){
		var rowid=master_grid.getSelectedRowId();
		if(rowid){
			allowpublicemail=getGridColumnValueForSelectedRow(master_grid,"allowpublicemail");
			ownername=getGridColumnValueForSelectedRow(master_grid,"owner");
			owneremail=getGridColumnValueForSelectedRow(master_grid,"email");
			var qnt=getColValueIsNotNullInGrid(master_grid, "qntorder");
			var add2cart=getColValueIsNotNullInGrid(master_grid, "addtocart");
			//if(qnt==null ||add2cart==null ){
			if(qnt==null ){
				dhtmlx.alert("Please select at least one item, enter quantity to order!");
			}else{
				//if(add2cart!='0' &&qnt!='0'){
				if(qnt!='0'){
					saveChanges("sampleorder", null);
				}else{
					dhtmlx.alert("You have selected an item whose quantity is 0. Please keep item quantity EMPTY or greater than 0!");

				}
				
			}
			
		}else{
			dhtmlx.alert("Please select a row and enter quantity to order!");
		}
		
	}
	function saveChanges(table, target){
		
		var parent_data= collect_form_data(table,target);
	  	
	  	if(parent_data && parent_data!=null ){  
			var posturl=www_url+"/rest/"+table+"/formdata?token="+token;
			 if(target &&target!=null){
				 posturl=www_url+"/rest/"+target+"/formdata?token="+token;
			 }
			
			postData(parent_data, posturl,table);
	    	
	    }
	}


	function postData(xml,url,table){
	        var xmldata
	        var http = new XMLHttpRequest();
	        var orderid;
			http.open("POST", url,true);
			
	    
			http.onreadystatechange = function() {//Call a function when the state changes.
	   			if(http.readyState == 4 && http.status == 200) {
	   			   post_reponse=http.responseText;
	   			   if(url.indexOf("/formdata")<0){
	   				 updateServiceResponseToTable(table,null,post_reponse);
	   			   }
	   			  
	      			if (window.DOMParser)
	  				{
	  					parser=new DOMParser();
	  					xmldata=parser.parseFromString(post_reponse,"text/xml");
	  				}
		   			else // Internet Explorer
	  				{
		   				xmldata=new ActiveXObject("Microsoft.XMLDOM");
		   				xmldata.async=false;
		   				xmldata.loadXML(post_reponse); 
	        		} 
	      			try{
	      				orderid=xmldata.getElementsByTagName("cell")[0].childNodes[0].nodeValue;
	      			}catch(err){
	      				
	      			}
	      			
	        		
	        		if( orderid && orderid.length==32 ){
	        		  //create emailresponse
	        		   var itemcount=saveOrderLineItem(orderid);
	        		   if(itemcount && itemcount>0){
	        			   sleepnow(1000);
	        			   createEmailResponse();
	        		   }
	        		  
	        		}else{
	        			dhtmlx.alert("OPS! You already placed an order! Please verify your email to validate your identity! ")
	        		}
	        		
	   			}
			}
			
			var formData = new FormData();
			formData.append("body", xml);
			http.send(formData);
			
	    }
    function saveOrderLineItem(orderid){
    	 var grid=master_grid;
    	 var table="sampleorderitem";
    	 var doc;
    	 var itemcount=0;
    	 var result;
    	//dhtmlx.alert(" orderid="+orderid);
    	 grid.forEachRow(function(id){
    		 var addtocart=getGridColumnValueForSelectedRow(grid,"addtocart");
    		 var quantity=getGridColumnValueForSelectedRow(grid,"qntorder");
    		 //if(addtocart && addtocart=='1'){
    		 if(quantity && Number(quantity)>0){
    			 var sample_objid=getGridColumnValueForSelectedRow(grid,"objid");
        		
        		 var xml=sampleorderitemxml;
        		 xml=xml.replace("@sampleid",sample_objid);
        		 xml=xml.replace("@sampleorderid",orderid);
        		 xml=xml.replace("@quantity",quantity);
        		 parser=new DOMParser();
        		 doc=parser.parseFromString(xml,"text/xml");
				 var xmlString = (new XMLSerializer()).serializeToString(doc);
        		 if(xml!='\n' ){  
        			var posturl=www_url+"/rest/"+table+"/post?token="+token;
        			
        			result=post_portal_data(xmlString, posturl);
        			itemcount++;
        		 }
    		 }
		 });
    	 status_bar.setText('Order Item Count='+itemcount);
    	 sleepnow(500);
    	
    	 if(itemcount>0){
    		 dhtmlx.alert("Your order is placed successfully! You have ordered "+itemcount+ " items! You will receive an email to verify your identity!")
    	 }
    	 return itemcount;
    }
    
    function createEmailResponse(){
   	 var grid=master_grid;
   	 var table="emailresponse";
   	 var doc;
   	 var itemcount=0;
   	 var result;
   	 var campaignid;
   	 var url=www_url+"/src/portal/xml/emailresponse.xml";
   	 var xml=getSyncResponse(url);
   	 var parser=new DOMParser();
	 var doc=parser.parseFromString(xml,"text/xml");
	 var xmlString = (new XMLSerializer()).serializeToString(doc);
	 var date=getToday();
   	 if(refobjid){
   		 campaignid=refobjid.split("-")[1];
   		 xmlString=xmlString.replace("@campaignid",campaignid);
   		 xmlString=xmlString.replace("@today",date);
   		 xmlString=xmlString.replace("@destinitionid",referer);
   		 if(setter&&setter.length==32){
   			 xmlString=xmlString.replace("@originid",setter);
   		 }else{
   			 xmlString=xmlString.replace("@originid","");
   		 }
   		
   		 if(formdata ){
   			 for(var key in formdata){
   				xmlString=xmlString.replace("@"+key,formdata[key]);
   			 }
   			 
   		 }
   		
   		 if(xmlString!='\n' ){  
   			var posturl=www_url+"/rest/"+table+"/post?token="+token;
   			
   			result=post_portal_data(xmlString, posturl);
   			
   		 }
   	 }
   	
   }
   

	function add_portal_content(grid_obj){
		var data="<div style='width:100%;height:100%;overflow:auto'><table>" ;
		 
		 //html_cell.setWidth('300');
		 left_cell.attachHTMLString("");
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
			          if(colid.indexOf("password")>=0||colid.indexOf("secret")>=0){
			        	  value="******";
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
		        		  data+=renderImage(value);
		        		  data+=renderHTMLFile(value);
		        	  }else{
		        		
			             data+=getHtml(label,value, colid);
		        	  }
		        	 
		           }else if(colid && grid_obj.isColumnHidden(colIdx)){
		        	   var value=grid_obj.cells(rowId,colIdx).getValue();
		        	   if(value.indexOf("userdoc/")>=0){
			        		 // data+=getHtmlDownloadLink("Items",value, name);
			        		  data+=renderImage(value);
			        		  data+=renderHTMLFile(value);
		        	   }
		           }
		            colIdx++;
		        });
		       
		    }
		 }
		
		data+="</table></div>";
		left_cell.attachHTMLString(data);
	}
	
	
	function post_portal_data(xml, url){
		  var ajaxdata;
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
		  			ajaxdata=parser.parseFromString(post_reponse,"text/xml");
		  		}
			   else // Internet Explorer
		  		{
				   ajaxdata=new ActiveXObject("Microsoft.XMLDOM");
				   ajaxdata.async=false;
				   ajaxdata.loadXML(post_reponse); 
		        } 
		        var objid=ajaxdata.getElementsByTagName("cell")[0].childNodes[0].nodeValue;
		        if(objid){
					current_parent_id=objid;
					
			    	markParentNodeAsClean(objid);
			    	
			    	status_bar.setText('Order Item Create! Id='+objid);
			    }else{
			    	setStatusBar(current_parent_table+":Update Failed!");
			    }
			    
			});
			
			}
			

		}
	
	function updateClickStatus(){
		var campaignid=refobjid.split("-")[1];
		if(campaignid && referer){
			var url=www_url+"/rest/portal/"+campaignid+"-"+referer+"/click.gif?token="+token;
			var data=getSyncJsonResponse(url);
			//var data=JSON.parse(data);
			//dhtmlx.alert(data.portalurl);
		}
		
	}

