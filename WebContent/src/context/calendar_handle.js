
	function postCalandarData(event,sentto,timezone,url,table){
	        var post_reponse;
	        var http = new XMLHttpRequest();
	        var objid;
			http.open("POST", url,true);
			
	    
			http.onreadystatechange = function() {//Call a function when the state changes.
	   			if(http.readyState == 4 && http.status == 200) {
	   			   post_reponse=http.responseText;
	   			   
	        		
	   			}
			}
			
			var formData = new FormData();
			formData.append("event", event);
			formData.append("sendto", sendto);
			ormData.append("timezone", timezone);
			http.send(formData);
			
	    }