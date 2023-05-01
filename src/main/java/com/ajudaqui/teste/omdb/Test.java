package com.ajudaqui.teste.omdb;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import com.ajudaqui.api.entity.Movie;
import com.ajudaqui.utils.GsonConverter;
import com.ajudaqui.utils.writer.WriterTxt;

public class Test {
	public static void main(String[] args) throws IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();
		String apiKes="6a6e3aa7";
//		String title="aladin";
		String title="matrix";
//		String title="divertidamente";
//		String title="O%20Senhor%20dos%20Anéis";
		
		String uri=String.format("http://www.omdbapi.com/?t=%s&apikey=%s", title,apiKes);
		   HttpRequest request = HttpRequest.newBuilder()
		         .uri(URI.create(uri))
		         .build();
		   
		   HttpResponse<String> response = client
				     .send(request, BodyHandlers.ofString());
		   Movie filme=GsonConverter.toMovie(response.body());
		   System.out.println(filme.toString());
		   
//		   Writer.inTxt("bob", response.body());
//		   Writer.planilhaFilme("Gugs", response.body());
		   System.out.println("\nacabou!");
	}

}
