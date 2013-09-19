package com.acn.mydata

class Concept {

	static belongsTo = [ds:DataSource];
	
	static hasMany = [category: String]
	
	String name
	String conceptId
	String variable
	String variableId
	
	
    static constraints = {
    }
	
	
	static namedQueries = {
		
		distinctConcept{
			projections {
				distinct("name")
			}
			order('name','desc')
		}

		
//		.createCriteria().list {
//			projections {
//				distinct("number")
//			}
//		}
		
		// http://grails.org/doc/2.1.0/ref/Domain%20Classes/createCriteria.html
//		distinctConcept{ int numResults ->
//			projections {
//				groupProperty('query')
//				rowCount('cnt')
//			}
//			maxResults(numResults)
//			order('cnt','desc')
//		}
		
//		distinctConcept{ int numResults ->
//			projections {
//				groupProperty('query')
//				rowCount('cnt')
//			}
//			maxResults(numResults)
//			order('cnt','desc')
//		}
//		
//		def results = c.list (max: 10, offset: 10) {
//			like("holderFirstName", "Fred%")
//			and {
//				between("balance", 500, 1000)
//				eq("branch", "London")
//			}
//			order("holderLastName", "desc")
//		}
	}
}
