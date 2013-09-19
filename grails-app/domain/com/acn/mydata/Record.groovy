package com.acn.mydata

class Record {
	static belongsTo = [user:User];
	
	static hasMany = [	conceptIds: String, 
						variables: Concept,
						country: Country,
						states: State,
						counties: County,
						entries: Entry,
						categories: String
					]
	
	DataSource ds
	String name
	String description
	Date created
	boolean isShared
	
	static mapping = {
		columns {
			description type:'text'
		}
	}
	
    static constraints = {
		country blank: true, nullable: true
		states blank: true, nullable: true
		counties blank: true, nullable: true
		ds blank: true, nullable: true
    }
}
