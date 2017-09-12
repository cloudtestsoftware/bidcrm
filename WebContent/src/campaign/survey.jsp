
    
    <div>
    	<div class="progress">
          <div class="progress-bar" role="progressbar" aria-valuenow="<%=request.getParameter("progress") %>" aria-valuemin="0" aria-valuemax="100" style="width: <%=request.getParameter("progress") %>%;">
            <%=request.getParameter("progress") %>%
          </div>
        </div>
       
         <div class="alert alert-info" id="header"  role="alert"><%=request.getParameter("header") %></div>
        <div>
       
	        <form class="form-group" id="myform"  onsubmit="return createObject('leadnote','./src/campaign/survey.jsp');">
	        	<input type="hidden" id="question" value="<%=request.getParameter("header") %>"/>
	        	<textarea class="form-control embed-responsive" id="answer" minlength="500"  maxlength="4000" name="textarea" rows="8" placeholder="Input your answer with minimum 200 words" required ></textarea>
	            <div id="characterLeft"></div>
            	<br>
	            <button type="submit" id="reply" class="btn btn-success btn-lg res"  style="float:right"><%=request.getParameter("btnlabel") %></button>
	             <input type="hidden" id="formfields" value="leadid,question,answer" />
	        </form>
	     
	          
	        <div  id="xmldata" style="display:none;">
	          <?xml version="1.0" encoding="UTF-8"?>
				<leadnote>
				<record id="0">
				<objid isRequired="true" type="RAW"></objid>
				<leadnote2lead isRequired="false" type="RAW">@leadid</leadnote2lead>
				<name isRequired="true" type="VARCHAR">@question</name>
				<note isRequired="true" type="VARCHAR">@answer</note>
				<entrydate isRequired="false" type="DATE">@entrydate</entrydate>
				</record>
				</leadnote>
	        
	        </div>
        </div>	
        
    </div>
   
  