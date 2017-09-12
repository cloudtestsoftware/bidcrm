

function sendEmailWithMesage(sendto,subject,message) {
	var http;
	var xmlDoc;

	var url=www_url+"/rest/email/sendmessage?token="+token+"&sendto="+sendto +"&message="+message+"&subject="+subject;
	
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

function sendEmailWithReason(sendto,reason,objid) {
	var http;
	var xmlDoc;

	var url=www_url+"/rest/email/"+objid+"/sendemail?token="+token+"&sendto="+sendto+"&reason="+reason;
	
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

function send_email_to_bidder(grid,table){
	
	var sendto=getGridColumnValueForSelectedRow(grid,"email");
 	var objid=getGridColumnValueForSelectedRow(grid,"objid");
 	
 	sendEmailWithReason(sendto,"invitation",objid);
 	
}

function send_rfq_email(grid,table){
	
	
 	var objid=getGridColumnValueForSelectedRow(grid,"objid");
 	
 	var http;
	var xmlDoc;

	var url=www_url+"/rest/email/"+objid+"/rfqemail?token="+token;
	
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



