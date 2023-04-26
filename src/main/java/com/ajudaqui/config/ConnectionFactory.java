package com.ajudaqui.config;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class ConnectionFactory {
	
	public static String request(URI uri) {
		HttpClient client = HttpClient.newHttpClient();
		
		  HttpRequest request = HttpRequest.newBuilder()
			         .uri(uri)
			         .build();
		  HttpResponse<String> response = null;
		  try {
			 response = client
					     .send(request, BodyHandlers.ofString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response.body();
		  
	}
	

}
