package com.ajudaqui.teste.viaCep;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class porCep {
	public static void main(String[] args) throws IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();
		
		int cep=54310275;
		String uri=String.format("https://viacep.com.br/ws/%d/json/", cep);
		

		
		   HttpRequest request = HttpRequest.newBuilder()
		         .uri(URI.create(uri))
		         .build();
		   
		   HttpResponse<String> response = client
				     .send(request, BodyHandlers.ofString());
		   System.out.println(response.body());
	}

}
