package com.acn.mydata

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.util.URIUtil;
import org.apache.log4j.Logger;

import com.sun.media.sound.RealTimeSequencer.DataPump;

import grails.converters.JSON

class WizardService {
	
	def grailsApplication
	
	static Logger log = Logger.getLogger(WizardService.class);
	
	def getConceptIds(String query, DataSource ds) {
		def c = Concept.createCriteria() // Create a Concept criteria
		
		// Get the unique ids
		def conceptIDList = c.list{
			//http://grails.org/doc/1.1/ref/Domain%20Classes/createCriteria.html
			projections {
				distinct("conceptId")
			}
			eq('ds', ds)
			like("name", "%${query}%")
			order("name", "asc")
		}
		
		
		return conceptIDList
	}
	
	def getConceptByCategory(Long id) {
		def recordInstance = Record.get(id)
		def conceptIDList = []
		
		recordInstance?.categories?.each {category ->
			def c = Concept.createCriteria() // Create a Concept criteria
			
			// Get the unique ids
			conceptIDList.addAll(c.list{
				//http://grails.org/doc/1.1/ref/Domain%20Classes/createCriteria.html
				projections {
					distinct("conceptId")
				}
				like("name", "%${category}%")
				order("name", "asc")
			})
		}
			
		
		return conceptIDList
	}
	
	def requestData(Record rec) {
		String url = rec?.ds?.url
		String key = "aedb071102fc3aee50cbaeccef26793c788653cd"
		rec.entries.clear()
		
		
		rec.variables.each { var ->
			log.debug("Processing variable: " + var.variableId + " " + var.variable)
			rec.country.each { country ->
				String paramStr = "key=${key}&get=${var.variableId},NAME&for=us"
				log.debug("Requesting URL: " + url + " " + paramStr)
				
				def output = requestURL(url, paramStr)
				log.debug("requestURL returns: "+output)
				if (output == null || output.length() ==0)
					return;
				def o = JSON.parse(output)
				
				// add the entry
				o.each{ data ->
					log.debug("Processing data returned: " + data)
					
					// looks like this is a JSON list
					if(data.length() >= 3 && !data[1].equalsIgnoreCase("NAME")){
						Entry entry = new Entry()

						entry.variableId = var.variableId
						entry.variableDesc = var.variable
						entry.conceptDesc = var.name
						entry.geoLocation = data[1]
						entry.value = data[0]
						
						rec.entries.add(entry)
					}
				}
			}
			
			rec.states.each { state ->
				String paramStr = "key=${key}&get=${var.variableId},NAME&for=state:${state.fipsCode}"
				def output = requestURL(url, paramStr)
				if (output == null || output.length() ==0)
					return;
				def o = JSON.parse(output)
				
				o.each{ data ->
					// looks like this is a JSON list
					if(data.length() >= 3 && !data[1].equalsIgnoreCase("NAME")){
						Entry entry = new Entry()

						entry.variableId = var.variableId
						entry.variableDesc = var.variable
						entry.conceptDesc = var.name
						entry.geoLocation = data[1]
						entry.value = data[0]
						
						rec.entries.add(entry)
					}
				}
			}
			
			rec.counties.each { county ->
				String paramStr = "key=${key}&get=${var.variableId},NAME&for=county:${county.fipsCode}&in=state:${county.stateFipsCode}"
				def output = requestURL(url, paramStr)
				if (output == null || output.length() ==0)
					return;
				def o = JSON.parse(output)
				
				o.each{ data ->
					// looks like this is a JSON list
					if(data.length() >= 3 && !data[1].equalsIgnoreCase("NAME")){
						Entry entry = new Entry()

						entry.variableId = var.variableId
						entry.variableDesc = var.variable
						entry.conceptDesc = var.name
						entry.geoLocation = data[1]
						entry.value = data[0]
						
						rec.entries.add(entry)
					}
				}
			}
		}
		
		return rec
	}
	
	private static String requestURL(String url, String params){
		String result = "";
		HttpClient client = new HttpClient();
		GetMethod getMethod = new GetMethod(url);
		
		try {
			log.debug("Requesting URL: " + url + "?" + params)
			getMethod.setQueryString(URIUtil.encodeQuery(params));
			client.executeMethod(getMethod);
			
			if(getMethod.getStatusCode() == 200){
				result = getMethod.getResponseBodyAsString();
			}else{
				log.debug("Request for " + url + " failed with message: " + getMethod.getStatusText());
			}
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			getMethod.releaseConnection();
		}
		
		return result;
	}
	
}
