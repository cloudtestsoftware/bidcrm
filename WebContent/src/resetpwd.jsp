
		<div id="signinBlock">
			<span class="formContainer">
				<form id="passwordResetForm" action="/passwordresets/create" method="POST">
					<h2>Reset your password</h2>
					
					<p class="description">Enter your email below and we'll send you<br />instructions to reset your password.</p>
					
					<span class="signinRow field ">
						<input class="fillin" id="email" name="email" title="Email" type="email" type="email" required="required" autocomplete="off" autofocus="autofocus" placeholder="Your Email Address" />
						<a class="resetPassLink" href="./service">Login?</a>
						<span class="message"></span>
					</span>
				
					<div class="error">
						<span class="message"></span>
					</div>

					<input class="button" type="submit" alt="Sign in!" value="Submit" />				
				</form>	
			</span>
		</div>

	