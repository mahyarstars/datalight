<%@ page import="com.acn.mydata.DataSource" %>
<%@ page import="com.acn.mydata.Concept" %>

<html>
	<head>
		<meta name="layout" content="records"/>
		<title>Welcome to My Data</title>
	</head>
	<body>
		<div id="recContainer">
			<div class="recTitle"><span><sec:username />'s</span> Records</div>
			<div class="recBody">
				<div class="myRecordsDiv">
					<table id="recordsTable">
						<thead>
							<tr>
								<!--<th></th>  -->
								<th>Name</th>
								<th>Description</th>
								<th>User</th>
								<th>Created</th>
								<th>Is Shared</th>
							</tr>
						</thead>
						<tbody>
							<g:each in="${myRecList}" status="i" var="myRec">
							<tr class="myRecord" >
								<!-- <td><g:img dir="images" file="Table.png" width="24" height="24"/></td>  -->
								<td>
									<a href="${createLink(controller:'wizard', action: 'showData', id: myRec.id)}">
										${myRec.name}
									</a>
								</td>
								<td>
									<% if(myRec.description?.size() >= 100){%>
										${myRec.description?.substring(0,100).concat("...")}
									<%}else{ %>
										${myRec.description}
									<%}%>
									
								</td>
								<td>${myRec.user.username}</td>
								<td><g:formatDate date="${myRec.created}" type="date" style="Long"/></td>
								<td><g:checkBox name="myCheckbox" class="shareRec" recId="${myRec.id}" value="${myRec.isShared}" /></td>
							</tr>
							</g:each>
						</tbody>
					</table>
					<div id="hiddenMsg"></div>	
				</div>
			</div>
		</div>
		
	</body>
</html>
