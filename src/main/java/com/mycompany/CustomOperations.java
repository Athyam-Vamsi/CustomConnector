package com.mycompany;

import org.mule.runtime.extension.api.annotation.Alias;
import org.mule.runtime.extension.api.annotation.param.Config;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import static org.mule.runtime.extension.api.annotation.param.MediaType.ANY;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomOperations {
	
	private static Logger LOGGER = LoggerFactory.getLogger(CustomConfinguration.class);
	
	@Alias(value = "Get Request")
	@MediaType(value = ANY, strict = false)
	public String getcall(@Config CustomConfinguration c) {
		LOGGER.info("Inside Operation Class");
		String response = null;
		String protocol =  (c.getProtocol() == "HTTPS") ? "https://" : "http://" ;
		try {
			URL url = new URL(protocol + c.getHost() + c.getBasepath());
			URLConnection con = url.openConnection();
			con.addRequestProperty("User-Agent", "Chrome");
			response = getHttpResponse(con);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		return response;
		
	}
	
	private String getHttpResponse(URLConnection con) throws UnsupportedEncodingException, IOException{
		StringBuilder response = null;
		try(BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(),"utf-8"))){
			response = new StringBuilder();
			String responseLine = null;
			while((responseLine = br.readLine())!= null) {
				response.append(responseLine.trim());
			}
		}
		return response.toString();
		}
	
}
