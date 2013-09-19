package com.acn.mydata;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.util.URIUtil;
import org.apache.log4j.Logger;

public class RequestHelper {
	private static final Logger log = Logger.getLogger(RequestHelper.class);
	
	public static String requestURL(String url, String params){
        String result = "";
        HttpClient client = new HttpClient();
		GetMethod getMethod = new GetMethod(url);
		 		
        try {
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
