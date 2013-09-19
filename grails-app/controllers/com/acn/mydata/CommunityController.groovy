package com.acn.mydata

import java.util.Date;

class CommunityController {

	def springSecurityService
	
    def index() { 
		List myRecList = Record.findAllByIsShared(true)
		
		[myRecList: myRecList]
	}

	
	///////////////////////////////////
	//
	// AJAX methods
	//
	///////////////////////////////////
	def copyRecord = {
		Record rec = Record.get(params.recId)
		Record newRec = new Record()
		
		if (!rec) {
			log.error "Record with ID: " + params.recId + " does not exist!"
			render(status: 503, text:  "Record with ID: " + params.recId + " does not exist!")
		}

		newRec.user = springSecurityService.currentUser
		newRec.name = rec.name
		newRec.description = rec.description
		newRec.isShared = false
		newRec.created = new Date()
		
		newRec.conceptIds = new ArrayList()
		newRec.conceptIds.addAll(rec.conceptIds)
		
		newRec.variables = new ArrayList()
		newRec.variables.addAll(rec.variables)
		
		newRec.country = new ArrayList()
		newRec.country.addAll(rec.country)
		
		newRec.states = new ArrayList()
		newRec.states.addAll(rec.states)
		
		newRec.counties = new ArrayList()
		newRec.counties.addAll(rec.counties)
		
		newRec.entries = new ArrayList()
		newRec.entries.addAll(rec.entries)
		
		newRec.ds = rec.ds
		
		// Save the record
		if (!newRec.save()) {
			newRec.errors.each {
				log.error "Error saving record: " + it
			}
			
			render(status: 503, text:  "Error saving record ID: " + params.recId)
		}
		
		render("Success")
	}

}
