package com.acn.mydata

class Country {

	String name
	String abbreviation
	String fipsCode
	
	static constraints = {
		name(nullable: false)
		abbreviation(nullable: false)
		fipsCode(nullable: false)
	}
}
