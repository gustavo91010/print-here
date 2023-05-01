package com.ajudaqui.teste.omdb;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import com.ajudaqui.api.entity.Movie;
import com.ajudaqui.utils.GsonConverter;
import com.ajudaqui.utils.writer.MovieXlsx;

public class Teste3 {
	public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
		System.err.println("comecei");		

		HttpClient client = HttpClient.newHttpClient();
		String apiKes="6a6e3aa7";
		String title="aladin";
//		String title="matrix";
//		String title="divertidamente";
//		String title="O%20Senhor%20dos%20Anéis";
		
		String uri=String.format("http://www.omdbapi.com/?t=%s&apikey=%s", title,apiKes);
		   HttpRequest request = HttpRequest.newBuilder()
		         .uri(URI.create(uri))
		         .build();
		   
		   HttpResponse<String> response = client
				     .send(request, BodyHandlers.ofString());
		   Movie movie=GsonConverter.toMovie(response.body());
		   
		   MovieXlsx.inXlsx(movie, "opa");
			System.err.println("acabou");		

		
	}

}
