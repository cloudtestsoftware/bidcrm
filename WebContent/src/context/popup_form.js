var dataforms={};
var formWin;
var formToolbar;
var popup_form;
var callerFormGrid;
var formdata={};
var form_table;
var isLocked=false;

var popup_form_toolbar_xml="<toolbar>"	
	 + '<item type="button" id="popup_form_action_add"  text="Add" img="add.gif"/>'
	 + '<item type="separator" id="button_spacer" />'
	 + '<item type="button" id="popup_form_action_save"  text="Save" img="save.gif"/>'
	
	 + '<item type="button" id="popup_form_action_close"  text="Close" img="close.gif"/>';


function addPopupForm(grid,table,title){
	if(isLocked){
		dhtmlx.alert("you are currently editing one record!<br>Please close it and try again.");
		return;
	}else{
		isLocked=true;
	}
	setStatusBar("Editing "+title);
	callerFormGrid=grid;
	form_table=table;
	formdata={};
	formWin = new dhtmlXWindows();
	formWin.setSkin(dhtmlx.skin);
	w2 = formWin.createWindow("w2", 200, 100, 800, 500);
	w2.setModal(false);
	w2.setText(title);
	w2.hideHeader();
	
	
	formToolbar =w2.attachToolbar();
	formToolbar.setIconsPath('./src/codebase/imgs/');
	formToolbar.loadXMLString(popup_form_toolbar_xml, function(){
		formToolbar.addSpacer("button_spacer");
	});
	
	addEvent(formToolbar,"action_button","onClick");
	formToolbar.setSkin(dhtmlx.skin);
	formToolbar.attachEvent("onClick", function(id){
	    // your code here
		popup_form_button_handler(id);
	});
 	var  furl=www_url+'/rest/'+table+'/form?token='+token;
    
	popup_form=w2.attachForm();
	
    popup_form.loadStruct(furl,"xml");
 
    popup_form.attachEvent("onFocus", function(name, value){
    	//setFormRelation(table);
	});
    
    popup_form.attachEvent("onChange", function(name, value){
    	//callPopupFormOnChange(table);
	});
    popup_form.attachEvent("onXLE", function(){
    	callPopupFormLoad(table);
    	hideEditorToolbar(popup_form);
    	callPopupFormLoad(table);
    	addGridDataToPopupForm(table);
    	
    	//setDataFormFieldWidth(table);
	});
	
    dataforms[table]=popup_form;
   
}


function popup_form_button_handler(id){
	//dhtmlx.alert("popup_form_button_handler="+id);
	if(id=="popup_form_action_add"){
		handle_add_popup();
	}else if(id=="popup_form_action_save"){
		isLocked=false;
		 popup_form_save_changes(form_table,null);
		
	}else if(id=="popup_form_action_search"){
		handle_popup_form_search()
	}else if(id=="popup_form_action_select"){
		isLocked=false;;
		//populate target grid
		if(fillupCallerCombo()){
			// close popup
			popup_form.clear();
			w2.close();
			formWin.unload();
		}
		
	}else if(id=="popup_form_action_close"){
		isLocked=false;
		if(callerFormGrid &&callerFormGrid!=null){
			addFormDataToGrid(form_table);
		}
		popup_form.clear();
		w2.close();
		formWin.unload();
		callerFormGrid=null;
		callerRowId=null;
		callerCellIndex=null;
		setStatusBar("Done "+form_table);
	}
}


function addGridDataToPopupForm(table){
	
    var grid=callerFormGrid;
    var form=popup_form;
    var rowId=callerFormGrid.getSelectedRowId();
    var colIdx=0;
    var focusitem;
    var objid=getGridColumnValueForSelectedRow(grid,"objid:false")
    if(objid &&objid.length==32){
    	grid.forEachCell(rowId,function(c){
            
            try{
            		var colid=grid.getColumnId(colIdx);
            		var formcolid=table.toLowerCase()+":"+colid;
            		var value=grid.cells(rowId,colIdx).getValue();
            		var type=form.getItemType(formcolid);
            		
        			formdata[colid]=value;
        			form.setItemValue(formcolid, value);
        			if(!focusitem && !form.isItemHidden(formcolid)){
        				focusitem=formcolid;
        			}
            
            }catch(err){
               dhtmlx.message('Error='+err +'  name='+ colid+ '  value='+value +'  type='+ type);
              
            }
            colIdx++;
            
    	});
    	form.setItemFocus(focusitem);
    }
	
	
}

function addFormDataToGrid(table){
	
    var grid=callerFormGrid;
    var form=popup_form;
    var rowId=grid.getSelectedRowId();
    var objid=getFormItemValue(form,":objid:false")
    if(objid &&objid.length==32){
    	 form.forEachItem(function(name){
			 val=getFormItemValue(form,name);
			 if(val){
				 var gridcol=name.split(table.toLowerCase()+":")[1];
				 setColumnValueByGridRowId(grid,gridcol,rowId, val);
			 }
		 });
    }else{
    	  var selId = grid.getSelectedRowId();
    	  grid.deleteRow(selId);
    }
	
	
}


function callPopupFormOnChange(table){
		hideFormCols(table);
		hideFormButton(table);
		disableFormCols(table);
		disableFormButton(table);
		//enableEditorToolbar(table);
		/*var custom_method=getFormOnChange(menuid, table);
	    if(custom_method){
	    	eval(custom_method+"(\""+table+"\")");
	    }
	    */
	}

	function callPopupFormLoad(table){
		hideFormCols(table);
		hideFormButton(table);
		disableFormCols(table);
		disableFormButton(table);
		//enableEditorToolbar(table);
		
	}
	
	function setFormItemValue(form,itemname,value){
		form.forEachItem(function(colid){
			if(colid.indexOf(itemname)>=0){
				form.setItemValue(name,value);
			}
		});
	}
	
	function getFormItemValue(form,itemname){
		var val;
		form.forEachItem(function(colid){
			 if(colid.indexOf(itemname)>=0 &&!val){

           	 
           	  try{
           		 val =popup_form.getEditor(colid).getContent();
           		 
           	  }catch(err){ }
           	 
           	  if(colid.indexOf('DATE')>=0){
            	   try{
            		   val= popup_form.getCalendar(colid).getDate(true);
            		   
            	   }catch(err){
            	   }
 			 }
           	  if(!val){
           		 val =popup_form.getItemValue(colid);
           	  }
           	 
             
              
			 }
		 });
		 if(val){
      		  return val;
      	  }
		return "";
	}
	
	function disableFormCols(table){
		var disablecols=callerFormGrid.getUserData("","disablecols").split(",");
		 var colNum=callerFormGrid.getColumnsNum();
		if(colNum==disablecols.length)	
			for( var i=0;i<colNum;i++){
				var colid=callerFormGrid.getColumnId(i);
				if(disablecols[i]=='yes'){
					popup_form.disableItem(table.toLowerCase()+":"+colid);
				}
			}
		
	}

	function disableFormButton(table){
		var disablebuttons=getFormButtonDisabled(menuid, table);
		 if(disablebuttons){
			 var form=popup_form
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
	function hideFormCols(table){
		var hidecols=getMenuObjectAttribute(table, "hideformcols");
		 if(hidecols){
			 var form=popup_form;
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
		var hidebuttons=getMenuObjectAttribute(table, "hideformbutton");
		 if(hidebuttons){
			 var form=popup_form
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
						 formToolbar.hideItem(butons[i]);
						 
					 }catch(err){}
					
				 }
				
			 }
			 
		 }
		
	}

	function disableFormButton(table){
		var disablebuttons=getMenuObjectAttribute(table, "disableformbuttons");
		 if(disablebuttons){
			 var form=popup_form
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
			 var form=popup_form
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
		var popup_form=dataforms[table];
		var validationerror="";
		validation_message="";
		formdata=null;
		formdata={};
		var xml="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"+"<"+(target&&target!=null?target:table)+">\n";
		 xml+="<record id=\"0\">\n"
		if(table=='undefined' ||!popup_form)
			return '\n';
		
		try{
		  popup_form.forEachItem(function(name){
	          var nulldate=false; 
	          var colid=name;
	          var excludecol=getMenuObjectAttributeByMenuId(table, table,"excludecolupdate");
		           if(colid){
		        	   
		              var tags=colid.split(":");
		             
		              if(tags.length>=4){
			               var col=tags[1];
			               if(!excludecol ||excludecol &&excludecol.indexOf(col)<0){
				               var isnullable=tags[2];
				               var type=tags[3].replace("2","");
				               var coltype=popup_form.getItemType(colid);
				               var inputlen=0
				              
				               var val =null;
				               try{
				            	  val =popup_form.getEditor(colid).getContent();
				            	  try{
				            		 
				            		  val=createTextVersion(val);
				            		  val = JSON.parse(val);
				            		  val=JSON.stringify(val);
				            		 
				            	  }catch(err){ }
				            	 
				            	  
				               }catch(err){
				            	   val =popup_form.getItemValue(colid);
				               }
				               if(tags[4]){
				            	   inputlen=tags[4]
				            	   
				               }
				               if(colid.indexOf('DATE')>=0){
				            	   try{
				            		   val= popup_form.getCalendar(colid).getDate(true);
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

	function hideEditorToolbar(form){
		form.forEachItem(function(name){
			try{
			  var editor=form.getEditor(name);
			  var eh=editor.tb.base.parentNode.parentNode.style.height
		      editor.tb.base.parentNode.style.height="0";
		      editor.base.style.height=eval(eh.split("px")[0]-30);
		     /*
			  editor.tb.base.parentNode.style.overflow="auto";
			  editor.tb.base.parentNode.style.resize="both";
			  editor.tb.base.style.overflow="auto";
			  editor.tb.base.style.resize="both"; 
			  */
			 
			}catch(err){}
			
		});
	}
function popup_form_save_changes(table, target){
		
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
        var objid;
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
      				objid=xmldata.getElementsByTagName("cell")[0].childNodes[0].nodeValue;
      			}catch(err){
      				
      			}
      			
        		
        		if( objid && objid.length==32 ){
        		  //create emailresponse
        			setFormItemValue(popup_form,":objid:false",objid);
        			setStatusBar("Updated "+form_table+":id:"+objid);
        		  
        		}else{
        			dhtmlx.alert("OPS! we can not save your record! ")
        		}
        		
   			}
		}
		
		var formData = new FormData();
		formData.append("body", xml);
		http.send(formData);
		
    }

function formatJSON(val) {
	try {
		var txt = JSON.parse(val);
		if (typeof txt == 'object') {
			// Json
			val = JSON.stringify(val, null, 4);
			val = val.trim().split("{").join("{<br>");
			val = val.trim().split("}").join("<br>}<br>");
			val = val.trim().split(",").join(",<br>");

			return val;
		}
	} catch (err) {
	}

	val = keepStringFormating(val);

	return val;

}