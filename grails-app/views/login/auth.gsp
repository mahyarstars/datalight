<html>
<head>
	<meta name="layout" content="login"/>
	<title><g:message code="springSecurity.login.title"/></title>
</head>

<body>
<div id='login'>
	<div class='inner'>
		<div class='fheader'>Log In</div>

		<g:if test='${flash.message}'>
			<div class='login_message'>${flash.message}</div>
		</g:if>

		<form action='${postUrl}' method='POST' id='loginForm' class='cssform' autocomplete='off'>
			<div class="userDiv">
				<span>Username</span>
				<input type='text' class='text_' name='j_username' id='username'/>
			</div>
			<div class="passDiv">
				<span>Password</span>
				<input type='password' class='text_' name='j_password' id='password'/>
			</div>
			<div class="loginSubmitDiv">
				<g:submitButton name="login" id="loginButton" class="loginButton" value="Log In" />
			</div>
		</form>
	</div>
</div>
<script type='text/javascript'>
	<!--
	(function() {
		document.forms['loginForm'].elements['j_username'].focus();
	})();
	// -->
</script>
</body>
</html>
