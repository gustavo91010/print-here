package com.ajudaqui.api.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.commons.io.FilenameUtils;

import com.ajudaqui.api.config.ConnectionFactory;
import com.ajudaqui.api.entity.Movie;
import com.ajudaqui.utils.GsonConverter;
import com.ajudaqui.utils.writer.MovieXlsx;
import com.ajudaqui.utils.writer.WriterTxt;

public class OmdbService {

//	@Value("${apiKey}")
	private String apiKey = "6a6e3aa7";

	private WriterTxt writer = new WriterTxt();

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
		String context = movie.getPlot();
		writer.movieInTxt(name, context, title);
		System.out.println("\nArquivo criado com sucesso!");
	}

	public void inSpreadsheet(Movie movie, String name) {
		try {
		 MovieXlsx.inXlsx(movie, name);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		ClassNotFoundException
//		IOException
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
			if (urlImage.equalsIgnoreCase("N/A") || (urlImage == null)) {

				System.err.println("Ahh que pena, desculpe mas o caminho da imagem veio vazio.");
				return;
			}
			Files.copy(new URL(urlImage).openStream(), destFile,StandardCopyOption.REPLACE_EXISTING);// se já ouver arquivo com o memso noem ele substitui

			System.out.println("Imagem salva em: " + titleDir.toAbsolutePath());

		} catch (FileAlreadyExistsException e) {
			e.printStackTrace();

		} catch (MalformedURLException e) {
			System.err.println(urlImage);
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
