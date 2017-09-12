
function postData(url,data,codetype,objid){
    
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
      			/*
        		var objid=xmlDoc.getElementsByTagName("cell")[0].childNodes[0].nodeValue;
        		
        		if(objid.length==32){
        			//alert("Registration successful! Please login using your email and password.");
        		}else{
        		  	//alert("Registration Failed! Email already in use. Retrieve your password using your email.");
        		}
        		*/
   			}
		}
		
		var formData = new FormData();
		formData.append("body", data);
		formData.append("codetype", codetype);
		formData.append("objid", templateid);
		http.send(formData);
		
    }


function SaveJS(markup){
    
	 var css    = ace.edit("css-editor").getSession().getValue();
     var script = ace.edit("js-editor").getSession().getValue();
     var html   = ace.edit("html-editor").getSession().getValue();
     var posturl=www_url+"/rest/editor/"+templateid+"/editordata?token="+token;
	//post doc
     postData( posturl,markup,"htmldoc");
     if(css){
    	 postData( posturl,css,"css");
     }
     if(script){
    	 postData( posturl,script,"js");
     }
     if(html){
    	 postData( posturl,html,"html");
     }
}    
 function refresh(){
	 var code_url="";
	 if(www_url.indexOf("localhost")>=0){
		 code_url=www_url.replace("bidcrm","")+"userdoc/mastertemplate/"+templateid+".code";
	 }else{
		 code_url=www_url.replace("bidcrm.com/bidcrm","bidcrm.com/")+"userdoc/mastertemplate/"+templateid+".code";
	 }
	 
	 var code = $.get(code_url, function(data) {
		 callbackSucess(data,"html");
		})
		  .done(function() {
			  
			  callbackSucess(data,"html");
		  })
		  .fail(function() {
		    //alert( "error" );
		  })
		  .always(function() {
			  callbackSucess(data,"html");
		  });
	 
	 //refresh css
	 var css_url
	 if(www_url.indexOf("localhost")>=0){
		 css_url=www_url.replace("bidapp","")+"/userdoc/mastertemplate/"+templateid+".css";
	 }else{
		 css_url=www_url.replace("bidcrm.com/bidcrm","bidcrm.com/")+"userdoc/mastertemplate/"+templateid+".css";
	 }
	 var css = $.get(css_url, function(data) {
		 callbackSucess(data,"css");
		})
		  .done(function() {
			  callbackSucess(data,"css");
		  })
		  .fail(function() {
		    //alert( "error" );
		  })
		  .always(function() {
			  callbackSucess(data,"css");
		    //alert( "finished" );
		  });
	//refresh css
	 var js_url
	 if(www_url.indexOf("localhost")>=0){
		 js_url=www_url.replace("bidapp","")+"/userdoc/mastertemplate/"+templateid+".js";
	 }else{
		 js_url=www_url.replace("bidcrm.com/bidcrm","bidcrm.com/")+"userdoc/mastertemplate/"+templateid+".js";
	 }
	 var css = $.get(js_url, function(data) {
		 callbackSucess(data,"js");
		})
		  .done(function() {
			  callbackSucess(data,"js");
		    //alert( "second success" );
		  })
		  .fail(function() {
		    //alert( "error" );
		  })
		  .always(function() {
			  callbackSucess(data,"js");
		    //alert( "finished" );
		  });
 }
 function callbackSucess(data,type){
	 var typedata;
	 if(!data) return false;
	 if(type=="html"){
		 typedata=style_html(data);
		 ace.edit("html-editor").getSession().setValue(typedata);
	 }else if(type=="css"){
		 typedata = css_beautify(data);
		 ace.edit("css-editor").getSession().setValue(typedata);
	 }else if(type=="js"){
		 typedata = js_beautify(typedata);
		 ace.edit("js-editor").getSession().setValue(typedata);
	 }
	 return true;
 }
    




