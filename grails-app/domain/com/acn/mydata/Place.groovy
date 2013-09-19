package com.acn.mydata

class Place {

    String name
	String fipsCode
	String stateFipsCode
	
	static constraints = {
		name(nullable: false)
		fipsCode(nullable: false)
		stateFipsCode(nullable: false)
	}
	
}
