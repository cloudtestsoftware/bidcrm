<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title></title>
	<link href="./src/campaign/css/artitelly.css" rel="stylesheet">
    <!-- Bootstrap -->
    <link href="./src/campaign/css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    
    
  </head>
  <body onload="getDataInPageLoad();"  >
  	<!--  nav class="navbar navbar-default"-->
      <div class="container-fluid">
        <div class="navbar-header">
        <!-- 
        <a class="navbar-brand" href="http://CloudTestSoftware.com">
          	<p>Home</p>
          </a>
          <img src="./src/campaign/img/logo.png" class="img-responsive">
          -->
          <input type="hidden" id="username" value="<%=request.getParameter("username")%>"/>
          <input type="hidden" id="token" value="<%=request.getParameter("usertoken")%>"/>
	 	  <input type="hidden" id="baseurl" value="<%=request.getParameter("baseurl")%>"/>
	 	  <input type="hidden" id="leadid" value=""/>
	 	   <input type="hidden" id="sendreference" value="<%=request.getParameter("sendreference")==null?2:request.getParameter("sendreference")%>"/>
	 	   <input type="hidden" id="campaignid" value="<%=request.getParameter("campaignid")%>"/>
	 	
        </div>
      </div>
     <!-- 
    </nav>
    -->

    <div class="container" id="container">
        	<div class="alert alert-info" id="header" role="alert"></div>
        	<div>
            <form class="form-group" id="myform"  name="myForm" data-toggle="validator"   >
                <label for="exampleInputEmail1">First Name</label>
                <div class="input-group" style="padding-bottom:10px">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
                    <input id="firstname" type="text" maxlength="50" name= "firstName" class="form-control" placeholder="First Name" required>
                </div>
                <label for="exampleInputEmail1">Last Name</label>
                <div class="input-group" style="padding-bottom:10px">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
                    <input id="lastname" type="text" maxlength="50" name= "lastName" class="form-control" placeholder="Last Name" required>
                </div>
                <label for="exampleInputEmail1">Email address</label>
                <div class="input-group" style="padding-bottom:10px">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-envelope"></span></span>
                    <input id="email" type="email" class="form-control" placeholder="Must be company e-mail" required>
                </div>
                <label for="exampleInputEmail1">Phone Number</label>
                <div class="input-group" style="padding-bottom:10px">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-earphone"></span></span>
                    <input id="phone" type="number" name="phone" minlength="10" class="form-control" placeholder="xxx-xxx-xxxx" required>
                </div>
                <label for="exampleInputEmail1">Job Title</label>
                <div class="input-group" style="padding-bottom:10px">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
                    <select id="jobtitle" type="" name='title' class="form-control" required >
                   		<option value="">Select one</option>
                    	<option>CTO</option>
                    	<option>VP Engineering</option>
                        <option>VP Quality Engineering</option>
                        <option>Director Engineering</option>
                        <option>Director Quality Engineering</option>
                        <option>Automation Manager</option>
                        <option>Automation Architect</option>
                        <option>Automation Lead</option>
                        <option>Sr. Automation Engineer</option>
                        <option>Sr. QE Engineer</option>
                    </select>
                </div>
              
              <button type="submit" class="btn btn-success btn-lg" style="float:right">Proceed</button>
              <input type="hidden" id="reference" value="no"/>
               <input type="hidden" id="formfields" value="campaignid,firstname,lastname,email,phone,jobtitle" />
            </form>
        </div>
        

        <div  id="xmldata" style="display:none;">
		    <?xml version="1.0" encoding="UTF-8"?>
			<lead>
				<record id="0">
					<objid isRequired="true" type="RAW"></objid>
					<lead2assignedto isRequired="false" type="RAW"></lead2assignedto>
					<lead2campaign isRequired="false" type="RAW">@campaignid</lead2campaign>
					<name isRequired="true" type="VARCHAR">@firstname</name>
					<middlename isRequired="false" type="VARCHAR"></middlename>
					<lastname isRequired="true" type="VARCHAR">@lastname</lastname>
					<jobtitle isRequired="false" type="VARCHAR">@jobtitle</jobtitle>
					<company isRequired="false" type="VARCHAR">Specify company</company>
					<address isRequired="true" type="VARCHAR">specify address</address>
					<country isRequired="false" type="VARCHAR">specify country</country>
					<state isRequired="false" type="VARCHAR">specify state</state>
					<city isRequired="false" type="VARCHAR">specify city</city>
					<zipcode isRequired="false" type="VARCHAR">xxxxx</zipcode>
					<officephone isRequired="false" type="VARCHAR">xxxxxxxxx</officephone>
					<mobile isRequired="true" type="VARCHAR">@phone</mobile>
					<fax isRequired="false" type="VARCHAR"></fax>
					<email isRequired="false" type="VARCHAR">@email</email>
					<url isRequired="false" type="VARCHAR">specify website</url>
					<othercontact isRequired="false" type="VARCHAR"></othercontact>
					<quicknote isRequired="false" type="VARCHAR">7 inch iPad</quicknote>
					<leadstage isRequired="false" type="VARCHAR">Raw</leadstage>
					<leadstatus isRequired="false" type="VARCHAR">None</leadstatus>
					<leadsource isRequired="false" type="VARCHAR">Internet Survey</leadsource>
					<referrer isRequired="false" type="VARCHAR"></referrer>
					<agentid isRequired="false" type="VARCHAR">@agentid</agentid>
					<leadid isRequired="false" type="VARCHAR"></leadid>
				</record>
			</lead>
    
</div>
    </div>
   
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    
    <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.11.1/jquery.validate.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="./src/campaign/js/artitelly.js"></script>
    <script src="./src/campaign/js/bootstrap.min.js"></script>
    
    <script>
    /*  
        the url must be called from browsers
         <BASE_URL>/testrepo/service?service=c2phbmFAY2xvdWR0ZXN0c29mdHdhcmUuY29tO3NyaWppdDk2O2NhbXBhaWdu&refobjid=<CAMPAIGNID>

         http://localhost:8080/testrepo/service?servicekey=c2phbmFAY2xvdWR0ZXN0c29mdHdhcmUuY29tO3NyaWppdDk2O2NhbXBhaWdu&refobjid=1BABDCE73114A17AE050B90A27932A0A
             
    	http://sandbox.artitelly.com/testrepo/service?servicekey=c2phbmFAY2xvdWR0ZXN0c29mdHdhcmUuY29tO3NyaWppdDk2O2NhbXBhaWdu&refobjid=<CAMPAIGNID>

    		http://sandbox.artitelly.com/testrepo/rest/campaign/filter?token=MTQzNzk1NDc4MjM1NDsxN0EzMTFCMjE0QUQ1RjNFRTA1MEI5MEEyNzkzMTNCMg==;127.0.0.1;sjana@cloudtestsoftware.com&filters=objid=%271BABDCE73114A17AE050B90A27932A0A%27
    */
    var campaignDoc;
    var surveyQuestionDoc;
    var questionCount=0;
    var surveycount=1;
    var referencecount=0;
    var agentid;
    var agentemail;
    var campaignname;
    var agentname;
    var referrer;
    var leadreference;
    var today;
    var leaddetails="";
    var myobject="";

    $('form').on('submit', function(e) {
        e.preventDefault();
        if (validator()){
        	createObject('lead','./src/campaign/survey.jsp');
        	if(myobject=='lead' &&leadreference=='yes'){
        		createReferenceNote();
            }
        }
    });

    function validator(){
         if($(".has-error").length>0 ) return false;
         return true;
        }	 
 
    function createObject(objectname, fileToReplace){
    	myobject=objectname;
        var www=document.getElementById("baseurl").value;
        var token=document.getElementById("token").value;
        
    	var myform=document.forms['myform'];
    	var formfields=myform.elements['formfields'].value.split(",");
    	var xmldata=document.getElementById('xmldata').innerHTML;
    	if(formfields!=null &&formfields.length>0){
    		for(var i=0;i<formfields.length;i++){
    			
    		  		var val;
    		  		if(myform.elements[formfields[i]]){
    		  			val=myform.elements[formfields[i]].value;
        		  	}else{
        		  		val=document.getElementById(formfields[i]).value;
            		}
    		  		if(formfields[i].toLowerCase()=='question' &&val.length>100){
    		  			val=val.substr(0,100);
    		  		}
    		  		xmldata=xmldata.split("@"+formfields[i]).join(htmlencode(val));
    		  		leaddetails+="  "+formfields[i]+"="+val;
    		  	}
    		}
    	 if(agentid){
    		 xmldata=xmldata.split("@agentid").join(agentid); 
    		 if(!referrer){
    			 referrer=document.getElementById('firstname').value +" "+ document.getElementById('lastname').value;
        	 }
    		
    		xmldata=xmldata.split("@referrer").join(referrer); 
        	
          }
    	 
    	  xmldata=xmldata.split("@entrydate").join(today);
    	  xmldata=xmldata.split("!--").join("");
    	  xmldata=xmldata.split("--").join("");
    	
    	 //post URL
    	 var url=www+"/rest/"+objectname+"/formdata?token="+token;
    	 
    	 var res=postObjectData(xmldata,url,token,objectname,fileToReplace);
    	
    	
    	return false;
    }

    function postObjectData(xml,url,token,objectname,fileToReplace){
        
        var http = new XMLHttpRequest();
        var www=document.getElementById("baseurl").value;
        var token=document.getElementById("token").value;
        var email=document.getElementById("username").value;
        var sendreference=document.getElementById("sendreference").value;
        var val;
      
      
        
		http.open("POST", url,true);
		
    
		http.onreadystatechange = function() {//Call a function when the state changes.
   			if(http.readyState == 4 && http.status == 200) {
   			   post_reponse=http.responseText;
      			//alert(post_reponse);
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
        		var objid=xmlDoc.getElementsByTagName("cell")[1].childNodes[0].nodeValue;
        	
        		if(objid){
        			if(objectname=='lead' && document.getElementById("leadid").value==''){
        				 document.getElementById("leadid").value=objid;
        				 sendEmail();
            		}
                    if(objectname=='lead' ){
                    	leadreference=document.getElementById("reference").value;
                    }else if(objectname=='leadnote'){
                    	surveycount= surveycount+1;
                    }
                   
                    
            		//Use this objid to send to leadnote xml as leadnote2lead value
            		if(objectname=='lead' && leadreference=='yes'){
            			referencecount=referencecount+1;
                		val="You referred "+referencecount + " person"
                		if(referencecount< sendreference){
            			   replace('./src/campaign/reference.jsp?isfirstRef=no&&header='+val);
                		}else{
                			val+="<br><br><b>";
                    		val+=document.getElementById("header").innerHTML=campaignDoc.getElementsByTagName("cell")[8].childNodes[0].nodeValue;
                			replace('./src/campaign/final.jsp?header='+val);
                		}
            		
            		}else if(objectname=='leadnote' && surveycount>questionCount &&sendreference==0){
            			val="You are done!"
            			val+="<br><br><b>";
                		val+=document.getElementById("header").innerHTML=campaignDoc.getElementsByTagName("cell")[8].childNodes[0].nodeValue;
            			replace('./src/campaign/final.jsp?header='+val);
            			
            		}else if(objectname=='leadnote' && surveycount>questionCount){
                		val=document.getElementById("header").innerHTML=campaignDoc.getElementsByTagName("cell")[6].childNodes[0].nodeValue;
                		val+="<br><br><b>";
                		val+=document.getElementById("header").innerHTML=campaignDoc.getElementsByTagName("cell")[7].childNodes[0].nodeValue;
                		val+="<span style=\"float:right\">"+
                        	"<a href=\"http://www.cloudtestsoftware.com/\">"+
                        	"<button type=\"button\" class=\"btn btn-success btn-lg\">No other referrals at this time</button>"+
                        	"</a>"+
          				    "</span>"
            			replace('./src/campaign/reference.jsp?isfirstRef=yes&&header='+val+"</b>");
            		}else {
            			try{
            				 val="Item No "+surveycount+": "+surveyQuestionDoc.getElementsByTagName("row")[surveycount-1].getElementsByTagName("cell")[4].childNodes[0].nodeValue;
               	          
            			}catch(err){
            				val="Thank you for participating in this event. We will contact you soon!"
            			}
                		var btnlabel=(surveycount<questionCount?"To Item "+(surveycount+1):"Finish");
            			replace(fileToReplace +"?header="+val+"&btnlabel="+btnlabel+"&progress="+((surveycount-1)/questionCount)*100);
            			
                        
            			//alert("Question "+surveycount+":"+surveyQuestionDoc.getElementsByTagName("row")[surveycount-1].getElementsByTagName("cell")[4].childNodes[0].nodeValue);
            			//document.getElementById("header").innerHTML="Question "+surveycount+":"+surveyQuestionDoc.getElementsByTagName("row")[surveycount-1].getElementsByTagName("cell")[2].childNodes[0].nodeValue;
                	}
            		 
        			
        		}
        		
   			}
		}
		
		var formData = new FormData();
		formData.append("body", xml);
		http.send(formData);
		
    }

    
    
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
					document.getElementById("container").innerHTML =  xmlHttpReq.responseText;						
				}
			}
			xmlHttpReq.send(null);
		
	}

    function getData(url,table) {
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
			http.open("GET",url,true);
			http.send(null);
			
			
			http.onreadystatechange = function() {//Call a function when the state changes.
   			if(http.readyState == 4 && http.status == 200) {
   			   var get_reponse=http.responseText;
      			
      			
      			if (window.DOMParser)
  				{
  					parser=new DOMParser();
  					xmlDoc=parser.parseFromString(get_reponse,"text/xml");
  				}
	   			else // Internet Explorer
  				{
  					xmlDoc=new ActiveXObject("Microsoft.XMLDOM");
  		   			xmlDoc.async=false;
  					xmlDoc.loadXML(get_reponse); 
        		} 
        		if(table=='campaign' && xmlDoc){
        			campaignDoc=xmlDoc;
        			document.getElementById("header").innerHTML=campaignDoc.getElementsByTagName("cell")[3].childNodes[0].nodeValue;
        			agentemail=campaignDoc.getElementsByTagName("cell")[16].childNodes[0].nodeValue;
        			agentid=campaignDoc.getElementsByTagName("cell")[19].childNodes[0].nodeValue;
        			campaignname=campaignDoc.getElementsByTagName("cell")[2].childNodes[0].nodeValue;
        			agentname=campaignDoc.getElementsByTagName("cell")[15].childNodes[0].nodeValue;
                }else if(table=='surveyquestion' && xmlDoc){
                	surveyQuestionDoc=xmlDoc;
                	questionCount=surveyQuestionDoc.getElementsByTagName("row").length;
                }
        		
   			}
		}
	}

    function getDataInPageLoad(){
        
    	 var www=document.getElementById("baseurl").value;
         var token=document.getElementById("token").value;
         var campaignid=document.getElementById('campaignid').value;
         
         //creatre campaign url
    	 var url=www+"/rest/campaign/filter?token="+token+"&filters=objid='"+campaignid+"'";
    	 //call service
    	 getData(url,'campaign');
    	
    	//creatre campaign url
    	 var url=www+"/rest/surveyquestion/filter?token="+token+"&filters=surveyquestion2campaign='"+campaignid+"'";
    	 //call service
    	 getData(url,'surveyquestion');

    	   today = new Date();
    	    var dd = today.getDate();
    	    var mm = today.getMonth()+1; //January is 0!

    	    var yyyy = today.getFullYear();
    	    if(dd<10){
    	        dd='0'+dd
    	    } 
    	    if(mm<10){
    	        mm='0'+mm
    	    } 
    	     today = mm+'/'+dd+'/'+yyyy;
    	   
    	 
     }



    function createReferenceNote(){
        var leadid;
        var reftitle;
        var refnote;
      	var myform=document.forms['myform'];
      	var www=document.getElementById("baseurl").value;
        var token=document.getElementById("token").value;
      	var xmldata=document.getElementById('xmldata2').innerHTML;

      	if(myform){

      		leadid=document.getElementById('leadid').value;
          	
      		xmldata=xmldata.split("@leadid").join(leadid);
      		
            reftitle=" Reference for " + myform.elements['firstname'].value + " " +  myform.elements['lastname'].value;
            
            xmldata=xmldata.split("@reftitle").join(reftitle);
            
            refnote= "Name: " +myform.elements['firstname'].value + " " +  myform.elements['lastname'].value +"  Phone: "+myform.elements['phone'].value
            +"  Email: "+myform.elements['email'].value +"  Job Title: "+myform.elements['jobtitle'].value;

            xmldata=xmldata.split("@refnote").join(refnote);
            xmldata=xmldata.split("@entrydate").join(today);
            
         }
      	 
      	
      	  xmldata=xmldata.split("!--").join("");
      	  xmldata=xmldata.split("--").join("");
      	
      	 //post URL
      	 var url=www+"/rest/leadnote/formdata?token="+token;
      	 var res=postData(xmldata,url);
      	
      	
      	return false;
      }

  function postData(xml,url,token){
	    
      var http = new XMLHttpRequest();
     
      
		http.open("POST", url,true);
		
  
		http.onreadystatechange = function() {//Call a function when the state changes.
 			if(http.readyState == 4 && http.status == 200) {
 			   post_reponse=http.responseText;
    			//alert(post_reponse);
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
      		
 			}
		}
		
		var formData = new FormData();
		formData.append("body", xml);
		http.send(formData);
		
  }

  function sendEmail() {
		var http;
		var xmlDoc;

		var www=document.getElementById("baseurl").value;
		var token=document.getElementById("token").value;
		var subject=" New lead from "+campaignname;
		var message="\n Hi "+agentname+",    Please look at the following lead who just signed up today    "+leaddetails +"  CRM Administrator";
		var url=www+"/rest/email/sendmessage?token="+token+"&sendto="+agentemail +"&message="+message+"&subject="+subject;
		
	   if (window.XMLHttpRequest)
				{// code for IE7+, Firefox, Chrome, Opera, Safari
					http=new XMLHttpRequest();
				}
			else
				{// code for IE6, IE5
					http=new ActiveXObject("Microsoft.XMLHTTP");
				}
			http.open("GET",url,true);
			http.send(null);
			
			
			http.onreadystatechange = function() {//Call a function when the state changes.
				if(http.readyState == 4 && http.status == 200) {
				   var get_reponse=http.responseText;
				   
	  			}
	    		
			}
	}

  function htmlencode(str) {
		try{
			return str.replace(/[&<>"?']/g, function($0) {
		        return "&" + {"&":"amp", "<":"lt", ">":"gt", '"':"quot", "'":"#39","?":"#63"}[$0] + ";";
		    });
			
		}catch(err){
			if(debug){
				dhtmlx.message("Error in encoding of string="+str);
			}
			return(str);
		}
	    
	}
		
    </script>
    
    <script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-65821618-1', 'auto');
  ga('send', 'pageview');

</script>
  </body>
</html>