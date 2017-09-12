//use this file to add a custom action to the existing button

var debug=false;



function callCustomAction(grid,name,table){
	var pos=0;
	try{
		if(table &&grid){
			var actions= customaction[table].action; 
			if(actions){				
				
				for(var action in actions){
					var vals=actions[action].split(".");
					var len=vals.length;
					if(len>=0 && name.indexOf(vals[0])>=0){
						eval(vals[len-1]+"(grid,table)");
					}
					
				}
			}
		}
	}catch(err){
		if(debug){
			dhtmlx.message(err +" "+vals[len-1]+"(grid,table) not implemented!");
		}
	}
	return null;
}



function getButtonHide(table){
	try{
		if(table){
			return customaction[table].buttonhide; 
		}
	}catch(err){
		if(debug){
			dhtmlx.message(err +" : getButtonHide : "+table);
		}
	}
	return null;
}

function getRowSelectAction(table){
	try{
		if(table){
			return customaction[table].rowselect; 
		}
	}catch(err){
		if(debug){
			dhtmlx.message(err +" : getRowSelectAction : "+table);
		}
	}
	return null;
}



function addPerformActionButtonSelect(toolbar,table,chlid_action){
	var pos=0;
	var opts=[];
	var data=eval(chlid_action);
	/*try{
		data=JSON.parse(chlid_action);
	}catch(err){}
	*/
	 
	//try{
		if(toolbar){
		
				
			toolbar.forEachItem(function(itemId){
				    // your code here
					var itemtext=toolbar.getItemText(itemId);
					if(itemtext.indexOf("Remove")>=0){
						 pos=toolbar.getPosition(itemId);
					}
				});
			 if(data){
				 var i=0;
				 try{
				    for( i=0;i<data.length;i++){
				    	
				    	 opts[i]=[data[i].id,"obj",data[i].text,"btn"+i+".gif"];
				    	 
				    }
				 }catch(err){}
				
			 }
			  opts=getCustomButtonOptions(table,opts,i);
			
			  if(opts.length>0){
				  toolbar.addButtonSelect("btn_perform", ++pos, "More Actions", opts, "action.gif", null);
			  }
				
			
		}
	/*}catch(err){
		if(debug){
			dhtmlx.message(err +" : getButton : "+table);
		}
	}*/
	return null;
}


function getCustomButtonOptions(table,opts,i){
	
	try{
		if(table ){
			var buttons= custombutton[table].button; 
			if(buttons){
				if(i>0){
					 ++i;
				}
				for(var button in buttons){
					var vals=buttons[button].split(":");
					
					opts[i]=["custom:"+table+":"+buttons[button],"obj",vals[0],"btn"+i+".gif"];
					i++;
				}
				
				
			}
		}
	}catch(err){
		if(debug){
			dhtmlx.message(err +" : getButton : "+table);
		}
	}
	return opts;
}



