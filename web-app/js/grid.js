$(document).ready(function () {
	
	var recId = $('#recid').attr("value");
    var url = "/MyDataLite/wizard/retrieveEntriesByRecId";
	var params = {recid : recId}
	
	$.getJSON( url, params).done(function( json ) {
		var colArray = [];
		var colTypeArray = [];
		
	    // Assign headers
	    $.each(json.headers, function( i, col ) {
	    	var colObj = new Object();
	    	var colType =  new Object();
	    	
	    	colObj.text = col.name;
	    	colObj.datafield = col.dataField;
	    	
	    	if(colObj.text == "Variable" || colObj.text == "Concept"){
	    		colObj.width = 250;
	    	}else{
	    		colObj.width = 100;
	    	}
	    	
	    	
	    	colType.name = col.dataField;
	    	
	    	// Add the source types for the data fields. Could probably do this in the controller more efficiently
	    	if(col.dataField == "Variable" || col.dataField == "Concept"){
	    		colType.type = 'string';
	    	}else{
	    		colType.type = 'number';
	    	}
	    	
	    	colArray.push(colObj);
	    	colTypeArray.push(colType);
	    });
	    
		// Assign data source
		var source = {
	        localdata: json.data,
	        datatype: "array",
	        datafields: colTypeArray
	    };
	
	    var dataAdapter = new $.jqx.dataAdapter(source, {
	        loadComplete: function (data) { },
	        loadError: function (xhr, status, error) { }      
	    });
	    
	    $("#jqxgrid").jqxGrid({
	    	width: 975,
	    	height: 440,
            theme: 'energyblue',
            sortable: true,
            columnsresize: true,
            columnsreorder: true,
	        source: dataAdapter,
	        columns: colArray,
	        showdefaultloadelement: true
	    });		
	    
	}).fail(function( jqxhr, textStatus, error ) {
		var err = textStatus + ', ' + error;
		console.log( "Request Failed: " + err);
	});
	
	
	$("#excelExport").jqxButton({ theme: 'energyblue' });
    $("#csvExport").jqxButton({ theme: 'energyblue' });
    
    $("#excelExport").click(function () {
        $("#jqxgrid").jqxGrid('exportdata', 'xls', 'jqxGrid');           
    });
    
    $("#csvExport").click(function () {
        $("#jqxgrid").jqxGrid('exportdata', 'csv', 'jqxGrid');
    });
	
});