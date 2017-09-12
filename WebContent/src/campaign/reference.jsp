
        	


<div class="alert alert-info" id="header" role="alert"><%=request.getParameter("header") %></div>


        <div>
            <form class="form-group" id="myform"  name="myForm" data-toggle="validator" onsubmit="createObject('lead','./src/campaign/survey.jsp'); return createReferenceNote();" >
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
                    <input id="phone" type="number" minlength="10"  name="phone" class="form-control" placeholder="xxx-xxx-xxxx" required>
                </div>
                <label for="exampleInputEmail1">Job Title</label>
                <div class="input-group" style="padding-bottom:10px">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
                    <select id="jobtitle" type="" class="form-control" required>
                   		<option value="" >Select one</option>
                    	<option>CTO</option>
                        <option>Automation Manager</option>
                    	<option>VP Engineering</option>
                        <option>VP Quality Engineering</option>
                        <option>Director Engineering</option>
                        <option>Director Quality Engineering</option>
                    </select>
                </div>
                
              <button type="submit" class="btn btn-success btn-lg" style="float:right">Submit</button>
              
              <input type="hidden" id="reference" value="yes"/>
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
					<referrer isRequired="false" type="VARCHAR">@referrer</referrer>
					<agentid isRequired="false" type="VARCHAR">@agentid</agentid>
					<leadid isRequired="false" type="VARCHAR"></leadid>
				</record>
			</lead>
     </div>

     <div  id="xmldata2" style="display:none;">
	          <?xml version="1.0" encoding="UTF-8"?>
				<leadnote>
				<record id="0">
				<objid isRequired="true" type="RAW"></objid>
				<leadnote2lead isRequired="false" type="RAW">@leadid</leadnote2lead>
				<name isRequired="true" type="VARCHAR">@reftitle</name>
				<note isRequired="true" type="VARCHAR">@refnote</note>
				<entrydate isRequired="false" type="DATE">@entrydate</entrydate>
				</record>
				</leadnote>
      </div>
      
     