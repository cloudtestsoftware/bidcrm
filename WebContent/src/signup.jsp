<div id="signinBlock">
			<h1>Registration</h1>
			<span class="formContainer">			    
 				<form id="signInForm" onsubmit="return registerUser();" action="/rest/profile/post" method="POST">
					<span class="field signinRow ">
						<input class="fillin validated " autofocus="true" name="username" value="" title="Email" type="email" required="required" placeholder="Email" />
						<a class="resetPassLink" href="./service">Login?</a>
						<span class="message"></span>
					</span>
					
					<span class="field signinRow passRow ">
						<input class="fillin validated " name="password" title="Password" value="" type="password" required="required" placeholder="Password" />
						
						<span class="message"></span>
					</span>
					
					<span class="field signinRow ">
						<input class="fillin validated "  name="firstname"  value="" title="First Name" type="text" required="required" placeholder="First Name" />
						
						<span class="message"></span>
					</span>
					
					<span class="field signinRow passRow ">
						<input class="fillin validated " name="lastname" title="Last Name" value="" type="text" required="required" placeholder="Last Name" />
						
						<span class="message"></span>
					</span>
				
				   <span class="field signinRow passRow ">
						<input class="fillin validated " name="country" title="Country" value="" type="text" required="required" placeholder="Country" />
						
						<span class="message"></span>
					</span>
					
				   <span class="field signinRow passRow ">
						<input class="fillin validated " name="contactno" title="Comtact No" value="" type="text" required="required" placeholder="Mobile Number" />
						
						<span class="message"></span>
					</span>
					
					<span class="field signinRow passRow ">
					<select id="reasoncode" name="reasoncode" class="blueText">
						<option selected="" value="0">---Choose Package---</option>
						<option value="1">Want BidCRM</option>
						<option value="2">Want Campaign Management</option>
					</select>
					<span class="message"></span>
					</span>
					
					<div class="error">
						<span class="message"></span>
					</div>

					<div class="buttonWrap">
						<input type="hidden" name="persist" value="true" />
						<input type="hidden" name="returnTo" value="/" />
						<input type="hidden"  name="body" value="" />
						<input type="hidden"  name="token" value="" />
						<input type="hidden" id="formfields" value="username,password,firstname,lastname,country,contactno,reasoncode" />
						<input class="button" type="submit"  alt="Register Now!" value="Register" />
					</div>
				</form>	
			</span>
		</div>
		
<div  id="xmldata" style="display:none;">
    <?xml version="1.0" encoding="UTF-8"?>
	<profile>
		<record id="0">
			<objid isRequired="true" type="RAW"></objid>
			<name isRequired="true" type="VARCHAR">@username</name>
			<password isRequired="true" type="VARCHAR">@password</password>
			<verifypassword isRequired="true" type="VARCHAR">@password</verifypassword>
			<firstname isRequired="true" type="VARCHAR">@firstname</firstname>
			<lastname isRequired="true" type="VARCHAR">@lastname</lastname>
			<company isRequired="true" type="VARCHAR">Your Company</company>
			<url isRequired="false" type="VARCHAR"></url>
			<street isRequired="true" type="VARCHAR">Your Street</street>
			<city isRequired="true" type="VARCHAR">Your City</city>
			<state isRequired="true" type="VARCHAR">Your State</state>
			<zipcode isRequired="true" type="VARCHAR">00000</zipcode>
			<countrycode isRequired="true" type="VARCHAR">@country</countrycode>
			<phone isRequired="true" type="VARCHAR">@contactno</phone>
			<phone2 isRequired="false" type="VARCHAR">@contactno</phone2>
			<fax isRequired="false" type="VARCHAR">000-000-0000</fax>
			<reasoncode isRequired="true" type="VARCHAR">@reasoncode</reasoncode>
			<mediacode isRequired="true" type="VARCHAR">1</mediacode>
		</record>
	</profile>
</div>


     