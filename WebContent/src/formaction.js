

 
/* Ajax Call */
function replace(strURL){
		var xmlHttpReq;
		// Mozilla/Safari
		if (window.XMLHttpRequest) {
			xmlHttpReq = new XMLHttpRequest();
		}
		// IE
		else if (window.ActiveXObject) {
			xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
		}	
		
			xmlHttpReq.open('GET', strURL, true);
			xmlHttpReq.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
			xmlHttpReq.onreadystatechange = function() {
				if (xmlHttpReq.readyState == 4) {	
					document.getElementById("content").innerHTML =  xmlHttpReq.responseText;						
				}
			}
			xmlHttpReq.send(null);
		
}

function registerUser(){
    var urldata;
    var www=document.getElementById("baseurl").value;
    var token=document.getElementById("token").value;
    
	var myform=document.forms['signInForm'];
	var formfields=myform.elements['formfields'].value.split(",");
	var xmldata=document.getElementById('xmldata').innerHTML;
	if(formfields!=null &&formfields.length>0){
		for(var i=0;i<formfields.length;i++){
			if(formfields[i]=='reasoncode'){
		       var reason=myform.elements['reasoncode'];
		       var code=reason.options[reason.selectedIndex].value;
		       xmldata=xmldata.split("@"+formfields[i]).join(code);
		       
		       //for get
		       urldata+="&"+formfields[i]+"="+code;
			}else{
		  		var val=myform.elements[formfields[i]].value;
		  		xmldata=xmldata.split("@"+formfields[i]).join(val);
		  		 urldata+="&"+formfields[i]+"="+val;
		  	}
		}
	}
	 
	
	  xmldata=xmldata.split("!--").join("");
	  xmldata=xmldata.split("--").join("");
	
	 //post URL
	 var url=www+"/rest/profile/formdata?token="+token;
	 var res=postData(xmldata,url,token);
	
	
	return false;
}

 
function getData(url) {
    	var http;
    	var xmlDoc;
    	
       if (window.XMLHttpRequest)
  			{// code for IE7+, Firefox, Chrome, Opera, Safari
  				http=new XMLHttpRequest();
  			}
			else
  			{// code for IE6, IE5
  				http=new ActiveXObject("Microsoft.XMLHTTP");
  			}
			http.open("GET",url,false);
			http.send(null);
			
			
			http.onreadystatechange = function() {//Call a function when the state changes.
   			if(http.readyState == 4 && http.status == 200) {
   			   var get_reponse=http.responseText;
      			
      			
      			if (window.DOMParser)
  				{
  					parser=new DOMParser();
  					xmlDoc=parser.parseFromString(post_reponse,"text/xml");
  				}
	   			else // Internet Explorer
  				{
  					xmlDoc=new ActiveXObject("Microsoft.XMLDOM");
  		   			xmlDoc.async=false;
  					xmlDoc.loadXML(get_reponse); 
        		} 
        		var objid=xmlDoc.getElementsByTagName("cell")[0].childNodes[0].nodeValue;
        		
        		if(objid.length==32){
        			alert("Registration successful! Please login using your email and password.");
        		}else{
        		  	alert("Registration Failed! Email already in use. Retrieve your password using your email.");
        		}
   			}
		}
}

    
function postData(xml,url,token){
    
        var http = new XMLHttpRequest();
   
		http.open("POST", url,true);
		
    
		http.onreadystatechange = function() {//Call a function when the state changes.
   			if(http.readyState == 4 && http.status == 200) {
   			   post_reponse=http.responseText;
      			
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
        		
        		if(objid.length==32){
        			alert("Registration successful! Please login using your email and password.");
        		}else{
        		  	alert("Registration Failed! Email already in use. Retrieve your password using your email.");
        		}
   			}
		}
		
		var formData = new FormData();
		formData.append("body", xml);
		http.send(formData);
		
    }

