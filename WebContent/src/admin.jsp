
<!DOCTYPE html>
<html>
<head>
	<title>Sign in - BidERP</title>

	<link rel="stylesheet" type="text/css" href="./src/login.css" />
	<script language="JavaScript" src="./src/formaction.js" type="text/javascript"></script>
	
</head>

<body class="signin">
	<!--

-->

<div class="topHead">
	<a href="http://www.biderp.com/" class="close">Home</a>
	 <input type="hidden" id="token" value="<%=request.getParameter("token")%>"/>
	 <input type="hidden" id="baseurl" value="<%=request.getParameter("baseurl")%>"/>
</div>

<div id="content">
	<div id="inner">
		<h1 id="signinLogo"><a href="/"></a></h1>
		
		<div id="signinBlock">
			<h1>Sign in to BidERP</h1>
			<span class="formContainer">			    
 				<form id="signInForm" action="/bidcrm/service" method="POST">
					<span class="field signinRow ">
						<input class="fillin validated " autofocus="true" name="username" value="" title="Email" type="text" required="required" placeholder="Email" />
						<a class="resetPassLink"  onclick="replace('./src/signup.jsp');">Register?</a>
						<span class="message"></span>
					</span>
					
					<span class="field signinRow passRow ">
						<input class="fillin validated " name="password" title="Password" type="password" required="required" placeholder="Password" />
						<a class="resetPassLink" onclick="replace('./src/resetpwd.jsp');">Forgot?</a>
						<span class="message"></span>
					</span>
				
					<div class="error">
						<span class="message"></span>
					</div>

					<div class="buttonWrap">
						<input type="hidden" name="persist" value="true" />
						<input type="hidden" name="returnTo" value="/" />

						<input class="button" type="submit" alt="Sign in!" value="Login" />
					</div>
				</form>	
			</span>
		</div>
	</div>
</div>


	
</body>
</html>