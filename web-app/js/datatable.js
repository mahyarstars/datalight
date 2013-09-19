$(document).ready(function () {
	
	$('#recordsTable').dataTable({
		"bJQueryUI": true,
		"aaSorting": [[ 3, "desc" ]]
	});
	
	$( "#hiddenMsg" ).dialog({
		autoOpen: false,
		resizable: false,
		height: 175,
	    buttons: {
	        "Okay": function() {
	          $( this ).dialog( "close" );
	    	}
	    }
    });
	
	$('.copyRecord').click(function() {
		var recId = $(this).attr("recId");
		var url = "/MyDataLite/community/copyRecord";
		var data = {recId : recId}
		
		$.get(url, data).done(function( json ) {
			console.log("Record Saved.");
			$( "#hiddenMsg" ).text("Record: " + recId + " successfully copied!").dialog( "open" );
		}).fail(function( jqxhr, textStatus, error ) {
			var err = textStatus + ', ' + error;
			console.log( "Request Failed: " + err);
			$( "#hiddenMsg" ).text(textStatus + ', ' + error).dialog( "open" );
		});
		
	});
	
	$('.shareRec').click(function() {
		var recId = $(this).attr("recId");
		var url = "/MyDataLite/record/shareRecord";
		var checked = false;

		if($(this).is(':checked')){
			checked = true;
		}
		
		var data = {recId : recId, checked : checked};
		
		$.get(url, data).done(function( json ) {
			console.log("Record Shared.");
			$( "#hiddenMsg" ).text("Record: " + recId + " successfully shared!").dialog( "open" );
		}).fail(function( jqxhr, textStatus, error ) {
			var err = textStatus + ', ' + error;
			console.log( "Request Failed: " + err);
			$( "#hiddenMsg" ).text(textStatus + ', ' + error).dialog( "open" );
		});
		
	});
	
});