
function getGridColumnValueForSelectedRow(grid,colname){
	 var colNum=grid.getColumnsNum();
	 var value;	
		for( var i=0;i<colNum;i++){
			var colid=grid.getColumnId(i);
			if(colid && colid.indexOf(colname+":")>=0||colid.toLowerCase()==colname.toLowerCase()){
				try{
					value=grid.cells(grid.getSelectedRowId(),i).getValue();
				}catch(err){
					dhtmlx.message("Could not read value for column="+colname, "Please select a row in grid!");
				}
				
			}
		}
	return value;
	
}


function getGridColumnValueForSelectedRowWithCombo(grid,colname){
	 var colNum=grid.getColumnsNum();
	 var value;	
		for( var i=0;i<colNum;i++){
			var colid=grid.getColumnId(i);
			if(colid && colid.indexOf(colname+":")>=0||colid.toLowerCase()==colname.toLowerCase()){
				try{
					var key=grid.cells(grid.getSelectedRowId(),i).getValue();
					var combotext;
					
					 var combo=grid.getCombo(i);
					 combotext=combo.get(key);
					 if(combotext){
						value=combotext;
					 }
					
				}catch(err){
					dhtmlx.message("Could not read value for column="+colname, "Please select a row in grid!");
				}
				
			}
		}
	return value;
	
}
function getGridColumnValueForSelectedRowWithoutColon(grid,colname){
	 var colNum=grid.getColumnsNum();
	 var value;	
		for( var i=0;i<colNum;i++){
			var colid=grid.getColumnId(i);
			if(colid && colid.indexOf(colname+":")>=0 ||colid.indexOf(":")<0 &&colid.indexOf(colname)>=0 ){
				try{
					value=grid.cells(grid.getSelectedRowId(),i).getValue();
				}catch(err){
					dhtmlx.message("could not read value for column="+colname);
				}
				
			}
		}
	return value;
	
}

function getColumnValueByGridRowId(grid,colname,rowid){
	 var colNum=grid.getColumnsNum();
	 var value;	
		for( var i=0;i<colNum;i++){
			if(grid.getColumnId(i).indexOf(colname+":")>=0 ){
				try{
					value=grid.cells(rowid,i).getValue();
				}catch(err){
					dhtmlx.message("could not read value for column="+colname);
				}
				
			}
		}
	return value;
	
}


function setColumnValueByGridRowId(grid,colname,rowid, value){
	 var colNum=grid.getColumnsNum();
	 var value;	
		for( var i=0;i<colNum;i++){
			if(grid.getColumnId(i).indexOf(colname+":")>=0 ){
				try{
					grid.cells(rowid,i).setValue(value);
					return true;
				}catch(err){
					dhtmlx.message("could not read value for column="+colname);
				}
				
			}
		}
	return false;
	
}

function disableEntireGrid(grid){
	if(grid){
		 var colNum=grid.getColumnsNum();
		 
		 grid.forEachRow(function(id){	
			for( var i=0;i<colNum;i++){
				grid.cells(id,i).setDisabled(true);
			}
		 });
	}
}

function disableSelectedRow(grid){
	if(grid &&grid.getSelectedRowId()>=0){
		 var colNum=grid.getColumnsNum();
		 var rowId=grid.getSelectedRowId();
		for( var i=0;i<colNum;i++){
			grid.cells(rowId,i).setDisabled(true);
		}
	}
	
}

function formatGridCurrency(grid,rowId){
	var moneycols=grid.getUserData("","grid.moneycols");
	if(grid && moneycols){
		 var colNum=grid.getColumnsNum();
		 for( var i=0;i<colNum;i++){
			 var colid=grid.getColumnId(i);
			 if(colid){
				 var col=colid.split(":")[0];
				 if(moneycols.indexOf(col)>=0){
					 var val=grid.cells(rowId,i).getValue();
					 var amt=accounting.formatMoney(val * conversion_rate, my_currency, 2, ",", ".");
					 grid.cells(rowId,i).setValue(amt);
				 }
			 }
			
		 }
		 
	}
}

function formatSummaryCurrency(grid,rowId){
	var moneycols=grid.getUserData("","grid.moneycols");
	if(grid && moneycols){
				 if(moneycols.indexOf(rowId)>=0){
					 var val=grid.cells(rowId,1).getValue();
					 var amt=accounting.formatMoney(val *conversion_rate, my_currency, 2, ",", ".");
					 grid.cells(rowId,1).setValue(amt);
				 }
	}
}

function getGridColumnIndex(grid,colname){
	 var colNum=grid.getColumnsNum();
		
		for( var i=0;i<colNum;i++){
			if(grid.getColumnId(i).indexOf(colname)>=0 ){
				return i;
			}
		}
	return -1;
	
}

function verifyColValueExistInGrid(grid, colname, val){
	 var colidx=getGridColumnIndex(grid,colname);
	 grid.forEachRow(function(id){
	      var itemval=getColumnValueByGridRowId(grid,colname,id);
	      if(itemval==val) return true;
	    });
	 return false;
}

function getColValueIsNotNullInGrid(grid, colname){
	 var colidx=getGridColumnIndex(grid,colname);
	 var val;
	 grid.forEachRow(function(id){
	      var itemval=getColumnValueByGridRowId(grid,colname,id);
	      if(itemval && itemval!=null && itemval!='') {
	    	  val=itemval
	      }
	    });
	 return val;
}





