package com.acn.mydata

class Entry {
	
	static belongsTo = Record
	
	String variableId
	String conceptDesc
	String variableDesc
	String value
	String geoLocation
	String geoLocationId
	
	static constraints = {
		conceptDesc blank: true, nullable: true
		variableId blank: true, nullable: true
		variableDesc blank: true, nullable: true
		value blank: true, nullable: true
		geoLocation blank: true, nullable: true
		geoLocationId blank: true, nullable: true
	}
	
//	static mapping = {
//		sort geoLocationId: "desc" // or "asc"
//	}
	
	String toString() {
		return "Entry: ${geoLocation} - ${conceptDesc} || ${variableDesc}: ${value}";
	}
	
	
}
