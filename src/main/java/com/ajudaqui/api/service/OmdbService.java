package com.ajudaqui.api.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;

import com.ajudaqui.api.config.ConnectionFactory;
import com.ajudaqui.api.entity.Movie;
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

	public void inText(Movie movie, String name, String title) {
		String context = GsonConverter.movieToJson(movie);
		writer.inTxt(name, context,title);
		System.out.println("\nArquivo criado com sucesso!");
	}

	public void inSpreadsheet(Movie movie, String name) {
		String context = GsonConverter.movieToJson(movie);
		Writer.planilhaFilme(name, context);
		System.out.println("\nPlanilha criada com sucesso!");
	}
	
	public void imageDownload(String urlImage, String name, String title) {

		String userHome = System.getProperty("user.home");
		String subDir = "print-here/generated-files/" + name;
		Path root = Paths.get(userHome, subDir);
		try {

			if (!Files.exists(root)) {
				Files.createDirectories(root);
			}

			Path titleDir = root.resolve(title);
			if (!Files.exists(titleDir)) {
				// Cria o diretório com o título
				Files.createDirectories(titleDir);
			}

			String fileName = title + " " + FilenameUtils.getName(urlImage);
			Path destFile = titleDir.resolve(fileName);
			Files.copy(new URL(urlImage).openStream(), destFile);

			System.out.println("Imagem salva em: " + titleDir.toAbsolutePath());

		} catch (FileAlreadyExistsException e) {
			e.printStackTrace();

		} catch (MalformedURLException e) {
			System.out.println();
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
