<%@ page import="com.acn.mydata.DataSource" %>
<%@ page import="com.acn.mydata.Concept" %>
<%@ page import="com.acn.mydata.Country" %>


<html>
	<head>
		<meta name="layout" content="wizard"/>
		<title>Welcome to My Data</title>
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'jqx.base.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'jqx.energyblue.css')}" type="text/css">
		<script language="javascript" src="${resource(dir: 'js', file: 'grid.js')}"></script>
		<script language="javascript" src="${resource(dir: 'js/jqwidgets', file: 'jqxcore.js')}"></script>
		<script language="javascript" src="${resource(dir: 'js/jqwidgets', file: 'jqxdata.js')}"></script>
		<script language="javascript" src="${resource(dir: 'js/jqwidgets', file: 'jqxbuttons.js')}"></script>
		<script language="javascript" src="${resource(dir: 'js/jqwidgets', file: 'jqxscrollbar.js')}"></script>
		<script language="javascript" src="${resource(dir: 'js/jqwidgets', file: 'jqxmenu.js')}"></script>
		<script language="javascript" src="${resource(dir: 'js/jqwidgets', file: 'jqxgrid.js')}"></script>
		<script language="javascript" src="${resource(dir: 'js/jqwidgets', file: 'jqxgrid.selection.js')}"></script>
		<script language="javascript" src="${resource(dir: 'js/jqwidgets', file: 'jqxcheckbox.js')}"></script>
		<script language="javascript" src="${resource(dir: 'js/jqwidgets', file: 'jqxgrid.columnsresize.js')}"></script>
		<script language="javascript" src="${resource(dir: 'js/jqwidgets', file: 'jqxgrid.columnsreorder.js')}"></script>
		<script language="javascript" src="${resource(dir: 'js/jqwidgets', file: 'jqxdata.export.js')}"></script>
		<script language="javascript" src="${resource(dir: 'js/jqwidgets', file: 'jqxgrid.export.js')}"></script>
		<script language="javascript" src="${resource(dir: 'js/jqwidgets', file: 'jqxgrid.sort.js')}"></script>
	</head>
	<body>
		<div id="wizContainer">
			<div class="wizTitle">Step 7/7: Show Data</div>
			<div class="wizBody">
				<g:hiddenField id="recid" name="recid" value="${recordInstance?.id}" />
				<div id='jqxWidget' style="font-size: 13px; font-family: Verdana; float: left;">
			        <div id="jqxgrid"></div>
			    </div>
			    <div class='exportDiv'>
	                <input type="button" value="Export to Excel" id='excelExport' />
	                <input type="button" value="Export to CSV" id='csvExport' />
	            </div>
	            <a id="wizFinishLink" href="${createLink(controller:"record", action: 'myRecords')}">
	            	<span class="wizFinishDiv">Finish</span>
				</a>
			</div>
		</div>
	</body>
</html>
