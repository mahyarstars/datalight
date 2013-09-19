package com.acn.mydata

class DataSource {
	static hasMany = [concepts:Concept];
	
	String name
	String description
	String url
	
    static constraints = {
    }
	
	static mapping = {
		// http://stackoverflow.com/questions/4533248/wrong-data-type-in-mysql-for-grails-byte-property
		description column: "description", sqlType: "LONGTEXT"
	}
}
