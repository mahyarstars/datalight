var $ = jQuery.noConflict();

$(document).ready(function(){
	
	// Show the tooltips
//	$(function() {
//	    $( document ).tooltip({
//	      position: {
//	        my: "center bottom-20",
//	        at: "center top",
//	        using: function( position, feedback ) {
//	          $( this ).css( position );
//	          $( "<div>" )
//	            .addClass( "arrow" )
//	            .addClass( feedback.vertical )
//	            .addClass( feedback.horizontal )
//	            .appendTo( this );
//	        }
//	      }
//	    });
//	  });
	
	// Type filter
	$("#conceptFilter").keyup(function() {
		var txtVal = this.value;
		
		if(txtVal.length > 1){
			$("#availConcepts").children().each(function() {
				var conceptTxt = $(this).text().toLowerCase();
				
				if(conceptTxt.toLowerCase().indexOf(txtVal) != -1){
					$(this).show();
				}else{
					$(this).hide();
				}
		    });
		}else{
			$("#availConcepts").children().each(function() {
				$(this).show();
		    });
		}
	});
	
	// Select/Deselect concept elements
	$('.availConcept').dblclick(function() {
		var parentId = $(this).parent().attr("id");
				
		if(parentId == "availConcepts"){
			// Append the clicked element to the selected concepts
			$('#selectedConcepts').append($(this));
		}else if(parentId == "selectedConcepts"){
			// Append the clicked element to the selected concepts
			$('#availConcepts').append($(this));
		}
		
	});
	
	// Before submitting build the selected concepts input element
	$('#conceptForm').submit(function() {
		$("#selectedConcepts").children().each(function() {
			var conceptid = $(this).attr("conceptid");
			var conceptInputVal = $("#conceptIds").val()
			
			if(conceptInputVal == ""){
				$("#conceptIds").val(conceptid)
			}else{
				$("#conceptIds").val(conceptInputVal + "," + conceptid)
			}
	    });
		
		return true;
	});
	
	// Variable accordion
	$( "#variableList" ).accordion({
		collapsible: true,
		active: false,
		heightStyle: "content"
	});
	

	$('.countryItem .countrySpan').click(function() {
		$('.states').toggle();
	});

	
	$('.stateItem .stateSpan').click(function() {
		var stateItem = $(this);
		var stateFipsCode = stateItem.parent('.stateItem').attr("fipscode");
		var url = "/MyDataLite/wizard/retrieveCountyByStateCode";
		var data = {statefipscode : stateFipsCode}
		
		// If counties already pulled
		if(!stateItem.siblings('.countyItem').length > 0){
			$.getJSON(url, data, function(counties){
				$.each(counties, function(i, county){
					stateItem.parent('.stateItem').append("<div class='countyItem'>" +
							"<input type='checkbox' name='countyfipscode' value='" + county.stateFipsCode + "|" + county.fipsCode + "'>" +
							"<span class='countySpan'>" + county.name + "</span>" + 
							"</div>")
				});
			});
		}else{
			stateItem.siblings('.countyItem').remove();
		}
	});
	
});

