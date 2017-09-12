// Grant
https://dhtmlx.com/docs/products/visualDesigner/live/#a0m373

CREATE TABLE `gantt_links` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `source` int(11) NOT NULL,
  `target` int(11) NOT NULL,
  `type` varchar(1) NOT NULL,
  PRIMARY KEY (`id`)
)
CREATE TABLE `gantt_tasks` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `text` varchar(255) NOT NULL,
  `start_date` datetime NOT NULL,
  `duration` int(11) NOT NULL,
  `progress` float NOT NULL,
  `sortorder` int(11) NOT NULL,
  `parent` int(11) NOT NULL,
  PRIMARY KEY (`id`)
)


text_filter - input box, which value is used as a filtering mask;
select_filter - select box, which value is used as a filtering mask;
combo_filter- dhtmlxcombo, which value is used as a filtering mask;
text_search - input box; the nearest row that contains inputed text, becomes selected;
numeric_filter - input box, which value is used as a filtering mask; allows to use comparison operators in it, such as:
equal to = N;
greater than > N;
lesser than < N;
lesser or equal =< N;
greater or equal >= N;
range of values N1 .. N2.
for linking filters with server side dhtmlxConnector see Filtering through Connector
mygrid.attachHeader("#text_filter,#select_filter,#numeric_filter");

try {
  if (isNaN(pos)){
    throw new TypeError("getEntry position must be a number");
  if (pos >= ary.length || pos < 0){
    throw new RangeError("getEntry position specified is outside the array");
  return ary[pos];
} catch (e) {
  if (e instanceof RangeError) {
    alert(e.name + ": " + e.message);
  } else throw e;
}

//References

http://docs.dhtmlx.com/doku.php?id=dhtmlxgrid:validation_extension

//Validator in Dhtmlx using XML

//By XML

<row id="11">
              <cell>NotEmpty</cell>
              <cell validate="NotEmpty">some</cell>
          </row>
          
          
 //Or By JS Code  
        
          grid.enableValidation(true );
          //related row must exists in grid on moment of command call
          grid.cells(id,index).setAttribute("validate","NotEmpty");
//or          
          grid.setColValidators("ValidInteger,,,ValidEmail");
//or
    grid.setColValidators(["ValidInteger",null,null,"ValidEmail"]);

Empty;
NotEmpty;
ValidAplhaNumeric;
ValidBoolean;
ValidCurrency;
ValidDate - 0000-00-00 to 9999-12-31;
ValidDatetime - 0000-00-00 00:00:00 to 9999:12:31 59:59:59;
ValidEmail;
ValidInteger;
ValidIPv4 - 0.0.0.0 to 255.255.255.255;
ValidNumeric;
ValidSIN - Social Security Number;
ValidSSN - Social Insurance Number;
ValidTime - 00:00:00 to 59:59:59.


// For column Type Calendar
http://docs.dhtmlx.com/doku.php?id=dhtmlxgrid:base_types
http://docs.dhtmlx.com/doku.php?id=dhtmlxgrid:toc_excell_types&s[]=column&s[]=types

//Simple Column Types
ro
ed
txt
ch
ra
co

//Formating EXCEL

link
img
price
dyn

//Complex Editor
cp
calck
dhxCalendar
dhxCalendarA
clist

//Using Other control as Editor
grid
stree
context
combo

<column width="200" type="dhxCalendar" format="%m-%d-%Y" align="center" sort="str">Date of Publication</column>

//How to add New Row
function addRow() {
var id = (new Date()).valueOf();
mygrid.addRow(id,[0,'','','',false,'na',false,'']); //inserts bottom of grid
mygrid.selectRowById(id);
var rowindex = mygrid.getRowIndex(id);
window.setTimeout(function(){
    mygrid.selectCell(rowindex,1,false,false,true,true);
    mygrid.editCell();
},1);
return true;
}

//Popup in Grid
var myPop = new dhtmlXPopup();
mygrid.attachEvent("onEditCell", function(stage,rId,cInd,nValue,oValue){
        if (stage==1){
            var x = getAbsoluteLeft(this.editor.obj);
            var y = getAbsoluteTop(this.editor.obj);
            var w = this.editor.obj.offsetWidth;
            var h = this.editor.obj.offsetHeight;
            myPop.show(x,y,w,h);
        }
        if (stage==2){
            myPop.hide();
        }

        return true;
    });
    
  //popup in tree
  tree = new dhtmlXTreeObject("treeboxbox_tree2", "100%", "100%", 0);
        tree.setSkin('dhx_skyblue');
        tree.setImagePath("../dhtmlx_pro_full/imgs/csh_bluebooks/");
        tree.setDataMode("json");
        tree.loadJSONObject({
            id:'0', item:[
                {id: '0-1',  text: 'Item 1-0', item:[
                    {id: '0-1-0',  text: 'Item 1-0-0'},
                    {id: '0-2-0',  text: 'Item 2-0-0'},
                    {id: '0-3-0',  text: 'Item 3-0-0'}
                ]},
                {id: '0-2', text: 'Item 2-0', child: '1'},
                {id: '0-3',  text: 'Item 3-0', child: '1'}
            ]});
        myPop = new dhtmlXPopup();
            tree.attachEvent("onClick",function(id){
                //var node = tree._globalIdStorageFind(id).htmlNode.firstChild.firstChild.lastChild;
                var node = tree._globalIdStorageFind(id).htmlNode;
                console.log(getOffset(node));
                var x = getOffset(node).left;
                var y = getOffset(node).top;
                var w = node.offsetWidth;
                var h = node.offsetHeight;
                myPop.show(x - w/2,y,w,h);
                myPop.attachHTML(id);
            });

  //Tree as cell Editor in Grid
  
  a) create tree object in default way 
    tree=new dhtmlXTreeObject("treeboxbox_tree","100%","100%",0); 
    ... 
b) define column type as stree
    mygrid.setColTypes("dyn,ed,stree,price,ch,co,ra,ro"); 
c) attach tree to specific column of grid 
    mygrid.setSubTree(tree,2); // 2 - index of column
    
    //Cell Content Edit within Grid
    grid.attachEvent("onEditCell", function(stage,rowId,colIndex) {
 
    if(stage==1 && grid.editor && grid.editor.obj) 

        grid.editor.obj.select(); /* grid.editor.obj is the input object*/
 
    return true
});

// Combo filter in grid top showing diffeent text
http://forum.dhtmlx.com/viewtopic.php?f=21&t=18372


grid.attachEvent("onCollectValues",collectComboValues);
grid.attachEvent("onFilterStart",filterGrid);
...
function collectComboValues(index){
        if (index==7){
        
            var c={}; var f=[];
           
            for (var i=0; i<this.getRowsNum(); i++){
                this.render_row(i);
                var text=this.cellByIndex(i,7).getTitle();
                c[text]=true;
            }
        
           for (d in c) 
              if (c[d]===true) f.push(d);
            
           return f.sort();
        }
    }
    
    function filterGrid(indexes,values){
        var i=indexes.indexOf(7);
        if (values[i]){
            var combo=this.getColumnCombo(7);
            var combo_value=combo.getOptionByLabel(values[i]).value;
            this.filterBy(7,function(value){
                return (value.toString().toLowerCase()==combo_value.toString().toLowerCase()); 
            });
            return false;
        }
        return true;
    }
    
    //For custom filtering add below to line to the combo
//combo = grid.getColumnCombo(columnIndex);
//combo.enableFilteringMode(true,"dummy"); 
//combo.attachEvent("onDynXLS", myComboFilter);
 
function myComboFilter(text){//where 'text' is the text typed by the user in the combo
	combo.clearAll();
	dhtmlxAjax.get("data.php?mask="+text, function(xml){
	    combo.loadXMLString(xml.xmlDoc.responseText);
	})
};

function CreateGridRows(objArray, theme, enableHeader) {
    // set optional theme parameter
    if (theme === undefined) {
        theme = 'mediumTable'; //default theme
    }

    if (enableHeader === undefined) {
        enableHeader = true; //default enable headers
    }

    // If the returned data is an object do nothing, else try to parse
    var array = typeof objArray != 'object' ? JSON.parse(objArray) : objArray;

    var str = '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n'+
			 '<rows>';
      str+="\n<head>"+
		"\n<column width=\"*\" type=\"ro\" align=\"left\" sort=\"str\">ObjID</column>"+
		"\n<column width=\"50\" type=\"ed\" align=\"left\" sort=\"int\">Protect</column>"+
		"\n<column width=\"50\" type=\"price\" align=\"right\" sort=\"int\">Price</column>"+
	"\n</head>";
    
    // table body
    var id=0;
    var count=0;
    for (var i = 0; i < array.length; i++) {
    	id++;
    	
         str += (i % 2 == 0) ? '\n<row id=\"'+id+'\">' : '<row>';
        for (var index in array[i]) {
       
            if(count<3){
            	str += '\n<cell>' + array[i][index] + '</cell>';
            }
             count++;
        }
        str += '\n</row>';
    }
    str += '\n</rows>'
  
    return str;
}

function CreateTableView(objArray, theme, enableHeader) {
    // set optional theme parameter
    if (theme === undefined) {
        theme = 'mediumTable'; //default theme
    }

    if (enableHeader === undefined) {
        enableHeader = true; //default enable headers
    }

    // If the returned data is an object do nothing, else try to parse
    var array = typeof objArray != 'object' ? JSON.parse(objArray) : objArray;

    var str = '<table class="' + theme + '">';
    
    // table head
    if (enableHeader) {
        str += '<thead><tr>';
        for (var index in array[0]) {
            str += '<th scope="col">' + index + '</th>';
        }
        str += '</tr></thead>';
    }
    
    // table body
    str += '<tbody>';
    for (var i = 0; i < array.length; i++) {
        str += (i % 2 == 0) ? '<tr class="alt">' : '<tr>';
        for (var index in array[i]) {
            str += '<td>' + array[i][index] + '</td>';
        }
        str += '</tr>';
    }
    str += '</tbody>'
    str += '</table>';
    return str;
}

function CreateDetailView(objArray, theme, enableHeader) {
    // set optional theme parameter
    if (theme === undefined) {
        theme = 'mediumTable';  //default theme
    }

    if (enableHeader === undefined) {
        enableHeader = true; //default enable headers
    }
    
    var array = JSON.parse(objArray);

    var str = '<table class="' + theme + '">';
    str += '<tbody>';


    for (var i = 0; i < array.length; i++) {
        var row = 0;
        for (var index in array[i]) {
            str += (row % 2 == 0) ? '<tr class="alt">' : '<tr>';

            if (enableHeader) {
                str += '<th scope="row">' + index + '</th>';
            }
            
            str += '<td>' + array[i][index] + '</td>';
            str += '</tr>';
            row++;
        }
    }
    str += '</tbody>'
    str += '</table>';
    return str;
}
