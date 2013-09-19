package com.acn.mydata

class State {

	String name
	String abbreviation
	String fipsCode
	
	static constraints = {
		name(nullable: false)
		abbreviation(nullable: false)
		fipsCode(nullable: false)
	}
	
}
