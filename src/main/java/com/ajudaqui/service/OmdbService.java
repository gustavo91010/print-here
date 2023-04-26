package com.ajudaqui.service;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;

import com.ajudaqui.config.ConnectionFactory;
import com.ajudaqui.entity.Movie;
import com.ajudaqui.utils.GsonConverter;
import com.ajudaqui.utils.Writer;

public class OmdbService {

//	@Value("${apiKey}")
	private String apiKey="6a6e3aa7";
	
	private Writer writer= new Writer();

	public URI byTitle(String title) {
		String uri = String.format("http://www.omdbapi.com/?t=%s&apikey=%s", title, apiKey);

		return URI.create(uri);
	}

	public Movie calUp(String title) {
		String response = ConnectionFactory.request(byTitle(title));
		System.err.println(response);
		Movie movie = GsonConverter.toMovie(response);

		return movie;
	}

	public void inText(Movie movie, String name) {
		String context = GsonConverter.movieToJson(movie);
		writer.inTxt(name, movie);
		System.out.println("\nArquivo criado com sucesso!");
	}

	public void inSpreadsheet(Movie movie, String name) {
		String context = GsonConverter.movieToJson(movie);
		Writer.planilhaFilme(name, context);
		System.out.println("\nPlanilha criada com sucesso!");
	}

}
