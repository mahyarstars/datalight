package com.acn.mydata

import org.apache.log4j.Logger;

import grails.converters.JSON

class WizardController {

	def WizardService
	def springSecurityService
	
	
	static Logger log = Logger.getLogger(WizardController.class);
	
    def index() {
		redirect(action: "createRecord", params: params)
	}
	
	def createRecord() {
		render(view:"/wizard/createRecord.gsp")
	}
	
	def saveRecord(){
		Record rec = new Record();
		
		rec.user = springSecurityService.currentUser
		rec.name = params.recname
		rec.description = params.recdesc
		rec.isShared = params.isshareable ?: false
		rec.created = new Date()
		
		// Save the record
		if (!rec.save()) {
			rec.errors.each {
				log.error "Error saving record: " + it
			}
		}
		
		redirect(action: "createDatasource", id: rec.id)
	}
	
	
	def createDatasource(Long id){
		def recordInstance = Record.get(id)
		
		if (!recordInstance) {
			log.error "Record with ID: " + id + " does not exist!"
			redirect(action: "createRecord")
			return
		}

		[recordInstance: recordInstance]
	}
	
	def saveDatasource(){
		Record rec = Record.get(params.recid)
		rec.ds = DataSource.get(params.datasourceid)
		
		// Save the record
		if (!rec.save()) {
			rec.errors.each {
				log.error "Error saving record: " + it
			}
		}
		
//		redirect(action: "createConcept", id: rec.id)
		redirect(action: "selectCategories", id: rec.id)
	}
	
	def selectCategories(Long id){
		def recordInstance = Record.get(id)
		
		if (!recordInstance) {
			log.error "Record with ID: " + id + " does not exist!"
			redirect(action: "selectCategories")
			
			return
		}
		
		[recordInstance: recordInstance]
	}

	def saveCategories(){
		Record rec = Record.get(params.recid)

		// clear the old variables
		rec.categories = []
		
		if (!rec.save(flush: true)) {
			rec.errors.each {
				log.error "Error saving record: " + it
			}
		}
		
		log.debug("saveCategories()...saving: ")
		
		params.list('categories').each{ category ->
			rec.categories.add(category)
		}
		
		redirect(action: "createConcept", id: rec.id)
	}
	
	def createConcept(Long id){
		def recordInstance = Record.get(id)
		def conceptList = new ArrayList()
		DataSource ds = recordInstance.ds
		
		if (!recordInstance) {
			log.error "Record with ID: " + id + " does not exist!"
			redirect(action: "createConcept")
			
			return
		}
		
		WizardService.getConceptByCategory(id).each{ conceptId -> // Build the JSON response
			Concept c = Concept.findByConceptId(conceptId)
			conceptList.add(c)
		}
		
//		WizardService.getConceptIds("", ds).each{ cname -> // Build the JSON response
//			conceptList.add(Concept.findByConceptId(cname))
//		}

		[recordInstance: recordInstance, conceptList: conceptList]
	}
	
	def saveConcept(){
		Record rec = Record.get(params.recid)

		// clear the old variables
		rec.conceptIds = []
		
		if (!rec.save(flush: true)) {
			rec.errors.each {
				log.error "Error saving record: " + it
			}
		}
		
		log.debug("saveConcept()...saving: ")
		
		// Since i used a picklist these are comma seperated values instead of a param list
		params.conceptids.split(",").each{ conceptId ->
			log.debug("\tsaveConcept conceptId: " + conceptId)
			rec.addToConceptIds(conceptId)			
		}

		redirect(action: "createVariable", id: rec.id)
	}
	
	def createVariable(Long id){
		def recordInstance = Record.get(id)
		def variableMap = new HashMap();
		
		log.debug "createVariable()"
		
		if (!recordInstance) {
			log.error "Record with ID: " + id + " does not exist!"
			redirect(action: "createVariable")
			
			return
		}
		
		recordInstance.conceptIds.each{ conceptId ->
			if(variableMap.containsKey(conceptId)){
				def conList = variableMap.get(conceptId)
				
				Concept.findAllByConceptId(conceptId).each {concept ->
					log.debug "concept: " + concept
					conList.add(concept);
				}

				variableMap.put(conceptId, conList)
			}else{
				def conList = new ArrayList();

				Concept.findAllByConceptId(conceptId).each {concept ->
					log.debug "concept: " + concept
					conList.add(concept);
				}

				variableMap.put(conceptId, conList)
			}
		}
		
		
		[recordInstance: recordInstance, variableMap: variableMap]
	}
	
	def saveVariable(){
		Record rec = Record.get(params.recid)
		
		// clear the old variables
		// clear the old variables
		rec.variables = []

		log.debug("saveVariable()...saving: ")
		
		params.list('variableids').each{ variableId ->
			log.debug("\tvariableId: " + variableId)
			Concept concept = Concept.findByVariableId(variableId)
			rec.variables.add(concept)
		}

		redirect(action: "addGeography", id: rec.id)
	}
	
	def addGeography(Long id){
		def recordInstance = Record.get(id)
		def states = new ArrayList()
		Country country = Country.findByName("United States")
		
		State.findAll().each{ state ->
			states.add(state)
		}
		
		if (!recordInstance) {
			log.error "Record with ID: " + id + " does not exist!"
			redirect(action: "addGeography")
			
			return
		}
		
		[recordInstance: recordInstance, country: country, states: states]
	}
	
	
	def saveGeography(){
		Record rec = Record.get(params.recid)
		
		// Clear the old geos
		rec.country = []
		rec.states = []
		rec.counties = []
		
		log.debug("saveGeography()...saving: ")
		params.list('countryfipscode').each{ countryFipsCode ->
			log.debug("\tcountryFipsCode: " + countryFipsCode)
			Country country = Country.findByFipsCode(countryFipsCode)
			rec.country.add(country)
		}
			
		params.list('statefipscode').each{ stateFipsCode ->
			log.debug("\t stateFipsCode: " + stateFipsCode)
			State state = State.findByFipsCode(stateFipsCode)
			rec.states.add(state)
		}
		
		params.list('countyfipscode').each{ countyFipsCode ->
			log.debug("\t countyFipsCode: " + countyFipsCode)
			def codes = countyFipsCode.split("\\|")
			log.debug("\t codes: " + codes)
			log.debug("\t codes.length: " + codes.length)
			
			if(codes.length > 1){
				County county = County.findByStateFipsCodeAndFipsCode(codes[0], codes[1])
				rec.counties.add(county)
			}else{
				log.debug("\t State Fips code not with county fipscode!")
			}
			
		}

		rec = WizardService.requestData(rec)
		
		redirect(action: "showData", id: rec.id)
	}
	
	def showData(Long id){
		def recordInstance = Record.get(id)
		
		if (!recordInstance) {
			log.error "Record with ID: " + id + " does not exist!"
			redirect(action: "addGeography")
			
			return
		}
		
		
		[recordInstance: recordInstance]
	}
	
	
		
	///////////////////////////////////
	//
	// AJAX methods
	//
	///////////////////////////////////
	def conceptFilter = {
		def conceptList = new ArrayList()
		def query = params.q ?: ""
		def ds = DataSource.get(params.dsid)
		
		if(ds){
			WizardService.getConceptIds(query, ds).each{ cname -> // Build the JSON response
				def conceptMap = new HashMap()
				conceptMap.put("conceptName", Concept.findByConceptId(cname)?.name)
				conceptMap.put("conceptId", cname)
				
				conceptList.add(conceptMap)
			}
		}else{
			log.error("conceptFilter: Please provide a valid datasource id!")
		}
		
		def output = [
			concepts: conceptList
		]
	 
		render output as JSON
	}
	
	def retrieveStates = {
		def states = new ArrayList()
		
		State.findAll().each{ state ->
			states.add(state)
		}
		
		render states as JSON
	}
	
	def retrieveCountyByStateCode = {
		def stateFipsCode = params.statefipscode
		def counties = new ArrayList()
		
		County.findAllByStateFipsCode(stateFipsCode).each{ county ->
			counties.add(county)
		}
		
		render counties as JSON
	}
	
	def retrieveEntriesByRecId = {
		def recId = params.recid
		def rec = Record.get(recId)
		def uniqueHeaders = new ArrayList()
		def uniqueVarIds = new ArrayList()
		def headerList = new ArrayList()
		def dataList = new ArrayList()
		
		if (!rec) {
			log.error "Record with ID: " + recId + " does not exist!"
			
			def output = [
				headers: "",
				data: ""
			]
		 
			render output as JSON
		}
		
		// Build the headers list
//		uniqueHeaders.add("variableId") // add the variable header manually
		uniqueHeaders.add("Concept")
		uniqueHeaders.add("Variable")
		
		rec.entries.sort({ it.geoLocation }).each{ entry ->
			if(!uniqueHeaders.contains(entry.geoLocation)){
				uniqueHeaders.add(entry.geoLocation)
			}
		}
		
		uniqueHeaders.each{ header ->
			def headerMap = new HashMap()
			
			headerMap.put("name", header)
			headerMap.put("dataField", header)
			
			headerList.add(headerMap)
		}

		// flatten the entry data so it can be grouped by the variable id
		def dataMap = new HashMap()

		rec.entries.each{ entry ->
			log.debug(entry)
			
			if(dataMap.containsKey(entry.variableId)){
				def entryMap = new HashMap()
				List mapList = dataMap.get(entry.variableId)
				entryMap.put(entry.geoLocation, entry.value)
				mapList.add(entryMap)
				dataMap.put(entry.variableId, mapList)
				
			}else{
				def entryMap = new HashMap()
				List mapList = new ArrayList()
				entryMap.put(entry.geoLocation, entry.value)
				// Just add the description once
				entryMap.put("Variable", entry.variableDesc)
				entryMap.put("Concept", entry.conceptDesc)
				mapList.add(entryMap)
				dataMap.put(entry.variableId, mapList)
			}
		}	

		// Build data list
		dataMap.each{ variableId, entryMapList ->
			def variableMap = new HashMap()
//			variableMap.put("variableId", variableId)
//			variableMap.put("Description", variableId)
			entryMapList.each{ entityMap ->
				entityMap.keySet().each { key ->
					// Add the flat data to the dataMap so we can turn it into a table
					variableMap.put(key, entityMap.get(key))
				}
			}
			
			dataList.add(variableMap)
		}
		
		def output = [
			headers: headerList,
			data: dataList
		]
	 
		render output as JSON
	}
	
	
	
	// Early attempt to auto-tag categories
//	def addCategory = {
//		// tag : regex
//		def catMap = [alone:"alone",
//						birth:"birth",
//						children:"children",
//						citizenship:"citizenship",
//						civilian:"civilian",
//						earnings:"earnings",
//						employed:"employed",
//						employment:"employment",
//						food:"food",
//						grandchildren:"grandchildren|grndChldn",
//						householder:"householder",
//						housing:"housing",
//						imputation:"imputation",
//						income:"income",
//						marital:"marital",
//						mobility:"mobility",
//						mortgage:"mortgage",
//						nonfamily:"nonfamily",
//						occupation:"occupation",
//						'owner-occupied':"owner-occupied",
//						parents:"parents",
//						population:"population|pop",
//						poverty:"poverty",
//						residence:"residence",
//						school:"school",
//						sex:"sex",
//						tenure:"tenure",
//						transportation:"transportation",
//						work:"work",
//						workers:"workers",
//						workplace:"workplace"]
//
//		
//
//		
//		Concept.findAll().each(){concept ->
//			log.info "Processing name: ${concept.name.toLowerCase()}"
//			// clear the old Concept categories
//			concept.category = []
//			
//			if (!concept.save(flush: true)) {
//				concept.errors.each {
//					log.error "Error saving record: " + it
//				}
//			}
//			
//			catMap.each(){ key, regex ->
//					if(concept.name.toLowerCase() =~ /\b${regex.toLowerCase()}\b/){
//						concept.category.add(key.toLowerCase())
//					}
//			}
//			
//		}
//		
//		
//		render "buy!"
//	}
	
	
}





