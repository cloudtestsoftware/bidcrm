

function grid2excel(grid){

  //use below commented line if you deploy the grid-excel.war
  
  var excel_url=(www_url.indexOf("localhost")>0?"https://www.biderp.com":www_url.replace("/bidcrm",""))+"/grid-excel/generate";
  grid.toExcel(excel_url);
 
  
}

function grid2pdf(grid){
   
	var pdf_url=(www_url.indexOf("localhost")>0?"https://www.biderp.com":www_url.replace("/bidcrm",""))+"/grid-pdf/generate";
	 
   grid.toPDF(pdf_url);
}

function compareAndExportBidQuizReply(grid,name){
	
	var expgrid;
	var expcolcount=0;
	var rowcount=-1;
	
	
		if(grid){
			 var divid="expgridId_1";

			 html_cell.attachHTMLString("<div id=\""+divid+"\" style=\"width:660px;height:600px;background-color:white;\"></div>");
				
			 grid.forEachRow(function(id){
				 rowcount++;
			 });
			 grid.forEachRow(function(id){
				 
				 var ggrid;
				
				 var colindex;
			
				 if(ggrid){
					 ggrid.clearAll();
				 }
				 ggrid= new dhtmlXGridObject(divid);
				
				 ggrid.enableAutoHeigth(true);
				 ggrid.enableAutoWidth(true);
				 ggrid.enableCollSpan(true);
				 ggrid.setInitWidths("150,350");
				 var bid_objid=getColumnValueByGridRowId(grid,"objid",id);
				 var collabel=getColumnValueByGridRowId(grid,"firstname",id)+" "+getColumnValueByGridRowId(grid,"lastname",id);
				 ggrid.setColumnMinWidth(100,0);
				 ggrid.attachEvent("onXLE", function(qgrid,count){
					 if(ggrid && id==0){
						 expgrid=ggrid;
						 
						 expcolcount=expgrid.getColumnsNum();
						 
						 colindex=getGridColumnIndex(ggrid,"iscomply");
						 if(colindex &&expgrid){
							 expgrid.setColLabel(colindex,collabel);
						 }
					 }else if(expgrid &&id>0){
						 colindex=getGridColumnIndex(ggrid,"iscomply");
						 expgrid.insertColumn(expcolcount,collabel,'ed',120,'str','left','top',null,null);
						
						 expgrid.setColumnId(expcolcount,"column"+expcolcount);
						 ggrid.forEachRow(function(gid){
							
								 var val=getColumnValueByGridRowId(ggrid,"iscomply",gid);
								
								 if(val!="Comply"){
									 var url= getColumnValueByGridRowId(ggrid,"url",gid);
									 if(url.indexOf("userdoc/")>=0){
										 val=val+"@"+url;
									 }
									 expgrid.cells(gid,expcolcount).setTextColor('red');
								 }
								 expgrid.cells(gid,expcolcount).setValue(val);
							 
							 
						 });
						 expcolcount=expgrid.getColumnsNum();
						
						 
						 if(id==rowcount){
							 
							 expgrid.forEachRow(function(gid){
								
									 var val=getColumnValueByGridRowId(expgrid,"iscomply",gid);
									 colindex=getGridColumnIndex(expgrid,"iscomply");
									 if(val!="Comply"){
										 var url= getColumnValueByGridRowId(expgrid,"url",gid);
										 if(url && url.indexOf("userdoc/")>=0){
											 val=val+"@"+url;
										 }
										 expgrid.cells(gid,colindex).setTextColor('red');
									 }
									 expgrid.cells(gid,colindex).setValue(val);
								 
								 
							 });
							 
							 var point=getGridColumnIndex(expgrid,"pointearned");
							 if(point){
								 expgrid.deleteColumn(point);
							 }
							 var url=getGridColumnIndex(expgrid,"url");
							 if(url){
								 expgrid.deleteColumn(url);
							 }
							 var objid=getGridColumnIndex(expgrid,"objid");
							 if(objid>=0){
								 expgrid.deleteColumn(objid);
							 }
							 grid2excel(expgrid);
							 html_cell.attachHTMLString("");
						
						 }
						
						 }
					 
						
				  	});
				  
				 var url=www_url+"/rest/bids/"+bid_objid+"/quizreply?token="+token;
				 ggrid.init();
				 try{
					 ggrid.load(url,'xml');
					 sleepnow(1000);
				 }catch(err){
					 dhtmlx.message("error:"+err)
				 }
			
				
			 });
			     
		}
}


function compareAndExportBidItemCost(grid,name){
	
	var expgrid;
	var expcolcount=0;
	var rowcount=-1;
	
	
		if(grid){
			 var divid="expgridId_1";

			 html_cell.attachHTMLString("<div id=\""+divid+"\" style=\"width:660px;height:600px;background-color:white;\"></div>");
				
			 grid.forEachRow(function(id){
				 rowcount++;
			 });
			 grid.forEachRow(function(id){
				 
				 var ggrid;
				
				 var colindex;
				 var totalcost=0;
				
				 if(ggrid){
					 ggrid.clearAll();
				 }
				 ggrid= new dhtmlXGridObject(divid);
				 ggrid.attachEvent("onRowCreated", function(rId,rObj,rXml){
						
						formatGridCurrency(ggrid,rId);
						
					});
				
				 ggrid.enableAutoHeigth(true);
				 ggrid.enableAutoWidth(true);
				 ggrid.enableCollSpan(true);
				 ggrid.setInitWidths("150,350");
				 var bid_objid=getColumnValueByGridRowId(grid,"objid",id);
				 var collabel=getColumnValueByGridRowId(grid,"firstname",id)+" "+getColumnValueByGridRowId(grid,"lastname",id);
				 ggrid.setColumnMinWidth(100,0);
				 ggrid.attachEvent("onXLE", function(qgrid,count){
					 if(ggrid && id==0){
						 expgrid=ggrid;
						 
						 expcolcount=expgrid.getColumnsNum();
						 
						 colindex=getGridColumnIndex(ggrid,"total");
						 if(colindex &&expgrid){
							 expgrid.setColLabel(colindex,collabel);
						 }
					 }else if(expgrid &&id>0){
						 colindex=getGridColumnIndex(ggrid,"total");
						 expgrid.insertColumn(expcolcount,collabel,'ed',120,'str','left','top',null,null);
						 totalcost=0;
						 expgrid.setColumnId(expcolcount,"column"+expcolcount);
						 ggrid.forEachRow(function(gid){
							
								 var val=getColumnValueByGridRowId(ggrid,"total",gid);
								 expgrid.cells(gid,expcolcount).setHorAlign('c');
								 expgrid.cells(gid,expcolcount).setValue(val);
								 val=accounting.unformat(val, ".");
								 val=val/conversion_rate;
								 totalcost=Number(totalcost)+Number(val);
								
							 
							 
						 });
						 
						 expgrid.setColLabel(expcolcount,expgrid.getColLabel(expcolcount)+"\n Total Cost ("+accounting.formatMoney(totalcost * conversion_rate, my_currency, 2, ",", ".")+")");
						 expcolcount=expgrid.getColumnsNum();
						
						 
						 if(id==rowcount){
							 
							 totalcost=0;
							 
							 expgrid.forEachRow(function(gid){
								
									 var val=getColumnValueByGridRowId(expgrid,"total",gid);
									 colindex=getGridColumnIndex(expgrid,"total");
									 expgrid.cells(gid,colindex).setHorAlign('c');
									 expgrid.cells(gid,colindex).setValue(val);
									 val=accounting.unformat(val, ".");
									 val=val/conversion_rate;
									 totalcost=Number(totalcost)+Number(val);
								 
								 
							 });
							 
							 expgrid.setColLabel(colindex,expgrid.getColLabel(colindex)+"\n Total Cost ("+accounting.formatMoney(totalcost *conversion_rate, my_currency, 2, ",", ".")+")");
							 var bidder=getGridColumnIndex(expgrid,"bidder");
							 if(bidder){
								 expgrid.deleteColumn(bidder);
							 }
							 var unitprice=getGridColumnIndex(expgrid,"unitprice");
							 if(unitprice){
								 expgrid.deleteColumn(unitprice);
							 }
							 var objid=getGridColumnIndex(expgrid,"objid");
							 if(objid>=0){
								 expgrid.deleteColumn(objid);
							 }
							 grid2excel(expgrid);
							 html_cell.attachHTMLString("");
							
							 
						 }
						
						 }
					 
						
				  	});
				  
				 var url=www_url+"/rest/bids/"+bid_objid+"/itemcost?token="+token;
				 ggrid.init();
				 try{
					 ggrid.load(url,'xml');
					 sleepnow(1000);
				 }catch(err){
					 dhtmlx.message("error:"+err)
				 }
			
				
			 });
			     
		}
}

function compareAndExportBidPoints(grid,name){
	
	var expgrid;
	var expcolcount=0;
	var rowcount=-1;
	
	
		if(grid){
			 var divid="expgridId_1";

			 html_cell.attachHTMLString("<div id=\""+divid+"\" style=\"width:660px;height:600px;background-color:white;\"></div>");
				
			 grid.forEachRow(function(id){
				 rowcount++;
			 });
			 grid.forEachRow(function(id){
				 
				 var ggrid;
				
				 var colindex;
			     var totalpoints=0;
			     var points=0;
				 if(ggrid){
					 ggrid.clearAll();
				 }
				 ggrid= new dhtmlXGridObject(divid);
				
				 ggrid.enableAutoHeigth(true);
				 ggrid.enableAutoWidth(true);
				 ggrid.enableCollSpan(true);
				 ggrid.setInitWidths("150,350");
				 var bid_objid=getColumnValueByGridRowId(grid,"objid",id);
				 var collabel=getColumnValueByGridRowId(grid,"firstname",id)+" "+getColumnValueByGridRowId(grid,"lastname",id);
				 ggrid.setColumnMinWidth(100,0);
				 ggrid.attachEvent("onXLE", function(qgrid,count){
					 if(ggrid && id==0){
						 expgrid=ggrid;
						 
						 expcolcount=expgrid.getColumnsNum();
						 
						 colindex=getGridColumnIndex(ggrid,"iscomply");
						 if(colindex &&expgrid){
							 expgrid.setColLabel(colindex,collabel);
						 }
					 }else if(expgrid &&id>0){
						 colindex=getGridColumnIndex(ggrid,"iscomply");
						 expgrid.insertColumn(expcolcount,collabel,'ed',120,'str','left','top',null,null);
						
						 expgrid.setColumnId(expcolcount,"column"+expcolcount);
						 
						 ggrid.forEachRow(function(gid){
							
								 var val=getColumnValueByGridRowId(ggrid,"iscomply",gid);
								 points=getColumnValueByGridRowId(ggrid,"pointearned",gid);
								
								 if(points>=0){
									 val=val+" ("+points+")";
									 totalpoints=Number(totalpoints)+Number(points);
								 }
								 var url= getColumnValueByGridRowId(ggrid,"url",gid);
								 if(url.indexOf("userdoc/")>=0){
									 val=val+"@"+url;
								 }
								 expgrid.cells(gid,expcolcount).setValue(val);
							 
							 
						 });
						 expgrid.setColLabel(expcolcount,expgrid.getColLabel(expcolcount)+"\n Total Points ("+totalpoints+")");
						 
						 expcolcount=expgrid.getColumnsNum();
						
						 
						 if(id==rowcount){
							 
							 totalpoints=0;
							 
							 expgrid.forEachRow(function(gid){
								
									 var val=getColumnValueByGridRowId(expgrid,"iscomply",gid);
									 points=getColumnValueByGridRowId(expgrid,"pointearned",gid);
									 if(points>=0){
										 val=val+" ("+points+")";
										 totalpoints=Number(totalpoints)+Number(points);
									 }
									 colindex=getGridColumnIndex(expgrid,"iscomply");
									
									 var url= getColumnValueByGridRowId(expgrid,"url",gid);
									 if(url && url.indexOf("userdoc/")>=0){
										 val=val+"@"+url;
									 }
										
									 expgrid.cells(gid,colindex).setValue(val);
								 
								 
							 });
							 expgrid.setColLabel(colindex,expgrid.getColLabel(colindex)+"\n Total Points ("+totalpoints+")");
							 var point=getGridColumnIndex(expgrid,"pointearned");
							 if(point){
								 expgrid.deleteColumn(point);
							 }
							 var url=getGridColumnIndex(expgrid,"url");
							 if(url){
								 expgrid.deleteColumn(url);
							 }
							 var objid=getGridColumnIndex(expgrid,"objid");
							 if(objid>=0){
								 expgrid.deleteColumn(objid);
							 }
							 grid2excel(expgrid);
							 html_cell.attachHTMLString("");
							
							 
						 }
						
						 }
						
				  	});
				  
				 var url=www_url+"/rest/bids/"+bid_objid+"/quizreply?token="+token;
				 ggrid.init();
				 try{
					 ggrid.load(url,'xml');
					 sleepnow(1000);
				 }catch(err){
					 dhtmlx.message("error:"+err)
				 }
				
			 });
			     
		}
}

