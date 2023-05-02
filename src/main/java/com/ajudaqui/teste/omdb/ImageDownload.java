package com.ajudaqui.teste.omdb;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FilenameUtils;

////java.net.MalformedURLException // url ta mal formada
////java.nio.file.FileAlreadyExistsException // já existe um arquivo com esse nome
////java.nio.file.AccessDeniedException // nao tem oremissao para criar nesta pagina

public class ImageDownload {
	public static void main(String[] args) throws MalformedURLException, IOException {
//		String urlImage="https://m.media-amazon.com/images/M/MV5BM2MyMzQyYTYtZWFmNi00YTRjLTk4ZmYtNDZmN2E4ZmIyNDM0XkEyXkFqcGdeQXVyMTI0Nzk5NTQ2._V1_SX300.jpg";
		String urlImage = "https://m.media-amazon.com/images/M/MV5BNTEwNmRiYzgtODlhZS00ZmE0LWJkNDYtMTkxZDAyY2ZkOGNiXkEyXkFqcGdeQXVyMTk5MjAyMjM@._V1_SX300.jpg";
//		String urlImage="https://m.media-amazon.com/images/M/MV5BMzFkM2U3NGEtOWI2MC00M2U0LWFjNjItYWUzZmFiNWE3OTA5XkEyXkFqcGdeQXVyODAxMTA0NjA@._V1_SX300.jpg";

		imageDownload(urlImage, "mila", "teste");

	}

	public static void imageDownload(String urlImage, String name, String title) {

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
