<%@ page import="com.acn.mydata.Record" %>
<html>
	<head>
		<meta name="layout" content="wizard"/>
		<title>Welcome to My Data</title>
	</head>
	<body>
		<div id="wizContainer">
			<div class="wizTitle">Step 3/7: Select Categories</div>
			<div class="wizBody">
				<g:form name="myForm" url="[action:'saveCategories',controller:'wizard']">
					<g:hiddenField name="recid" value="${recordInstance?.id}" />
					<div id="categoryContainer">
						<div id="categoryList">
							<div><g:checkBox name="categories" value="alone" checked="false" /><span>alone</span></div>
							<div><g:checkBox name="categories" value="birth" checked="false" /><span>birth</span></div>
							<div><g:checkBox name="categories" value="children" checked="false" /><span>children</span></div>
							<div><g:checkBox name="categories" value="citizenship" checked="false" /><span>citizenship</span></div>
							<div><g:checkBox name="categories" value="civilian" checked="false" /><span>civilian</span></div>
							<div><g:checkBox name="categories" value="earnings" checked="false" /><span>earnings</span></div>
							<div><g:checkBox name="categories" value="employed" checked="false" /><span>employed</span></div>
							<div><g:checkBox name="categories" value="employment" checked="false" /><span>employment</span></div>
							<div><g:checkBox name="categories" value="food" checked="false" /><span>food</span></div>
							<div><g:checkBox name="categories" value="grandchildren" checked="false" /><span>grandchildren</span></div>
							<div><g:checkBox name="categories" value="householder" checked="false" /><span>householder</span></div>
							<div><g:checkBox name="categories" value="housing" checked="false" /><span>housing</span></div>
							<div><g:checkBox name="categories" value="imputation" checked="false" /><span>imputation</span></div>
							<div><g:checkBox name="categories" value="income" checked="false" /><span>income</span></div>
							<div><g:checkBox name="categories" value="marital" checked="false" /><span>marital</span></div>
							<div><g:checkBox name="categories" value="mobility" checked="false" /><span>mobility</span></div>
							<div><g:checkBox name="categories" value="mortgage" checked="false" /><span>mortgage</span></div>
							<div><g:checkBox name="categories" value="nonfamily" checked="false" /><span>nonfamily</span></div>
							<div><g:checkBox name="categories" value="occupation" checked="false" /><span>occupation</span></div>
							<div><g:checkBox name="categories" value="owner-occupied" checked="false" /><span>owner-occupied</span></div>
							<div><g:checkBox name="categories" value="parents" checked="false" /><span>parents</span></div>
							<div><g:checkBox name="categories" value="population" checked="false" /><span>population</span></div>
							<div><g:checkBox name="categories" value="poverty" checked="false" /><span>poverty</span></div>
							<div><g:checkBox name="categories" value="residence" checked="false" /><span>residence</span></div>
							<div><g:checkBox name="categories" value="school" checked="false" /><span>school</span></div>
							<div><g:checkBox name="categories" value="sex" checked="false" /><span>sex</span></div>
							<div><g:checkBox name="categories" value="tenure" checked="false" /><span>tenure</span></div>
							<div><g:checkBox name="categories" value="transportation" checked="false" /><span>transportation</span></div>
							<div><g:checkBox name="categories" value="work" checked="false" /><span>work</span></div>
							<div><g:checkBox name="categories" value="workers" checked="false" /><span>workers</span></div>
							<div><g:checkBox name="categories" value="workplace" checked="false" /><span>workplace</span></div>
						</div>
					</div>
					<div class="wizSubmitDiv">
						<g:submitButton name="next" class="wizNext" value="Next" />
					</div>
				</g:form>
			</div>
		</div>
	</body>
</html>
