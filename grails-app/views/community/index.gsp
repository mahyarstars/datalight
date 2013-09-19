<%@ page import="com.acn.mydata.DataSource" %>
<%@ page import="com.acn.mydata.Concept" %>

<html>
	<head>
		<meta name="layout" content="community"/>
		<title>Welcome to My Data</title>
	</head>
	<body>
		<div id="comContainer">
			<div class="comTitle"><span>Community</span> Records</div>
			<div class="comBody">
				<div class="comRecordsDiv">
					<table id="recordsTable">
						<thead>
							<tr>
								<th>Copy Record</th>
								<th>Name</th>
								<th>Description</th>
								<th>User</th>
								<th>Created</th>
							</tr>
						</thead>
						<tbody>
							<g:each in="${myRecList}" status="i" var="comRec">
							<tr class="comRecord" >
								<td><g:img dir="images" class="copyRecord" recId="${comRec.id}" file="add.png" width="24" height="24"/></td>
								<td>
									<a href="${createLink(controller:'wizard', action: 'showData', id: comRec.id)}">
										${comRec.name}
									</a>
								</td>
								<td>
									<% if(comRec.description?.size() >= 100){%>
										${comRec.description?.substring(0,100).concat("...")}
									<%}else{ %>
										${comRec.description}
									<%}%>
									
								</td>
								<td>${comRec.user.username}</td>
								<td><g:formatDate date="${comRec.created}" type="date" style="Long"/></td>
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
