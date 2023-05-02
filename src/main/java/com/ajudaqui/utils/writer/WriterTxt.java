package com.ajudaqui.utils.writer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.ajudaqui.api.entity.Address;
import com.ajudaqui.api.entity.Movie;
import com.ajudaqui.utils.GsonConverter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class WriterTxt {

	public void movieInTxt(String name, Movie movie) {
//		String userHome = System.getProperty("user.home");
//		String subDir = "print-here/generated-files/" + name+"/movie";

//		String sheetName = movie.getTitle().replaceAll("[:*?/\\[\\]]", "");
//		String userHome = System.getProperty("user.home");
//		String subDir = "print-here/generated-files/" + name+"/movie";
//		Path root = Paths.get(userHome, subDir);

		String userHome = System.getProperty("user.home");
		String subDir = "print-here/generated-files/" + name + "/movie";
		String sheetName = movie.getTitle().replaceAll("[:*?/\\[\\]]", "");
		
		Path root = Paths.get(userHome, subDir, sheetName);
		try {
			if (!Files.exists(root)) {
				Files.createDirectories(root);
			}

//			// Cria o diretório com o título
//			Path titleDir = root.resolve(sheetName);
//			Files.createDirectories(titleDir);

			// Cria o arquivo dentro do diretório raiz
			Path filePath = root.resolve(sheetName + ".txt");

			Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
			String texto = prettyGson.toJson(movie);

			Files.write(filePath, texto.getBytes());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void plotInTxt(String name, Movie movie, String plot) {
//		String userHome = System.getProperty("user.home");
//		String subDir = "print-here/generated-files/" + name+"/movie";
		
//		String sheetName = movie.getTitle().replaceAll("[:*?/\\[\\]]", "");
//		String userHome = System.getProperty("user.home");
//		String subDir = "print-here/generated-files/" + name+"/movie";
//		Path root = Paths.get(userHome, subDir);
		
		String sheetName = movie.getTitle().replaceAll("[:*?/\\[\\]]", "");
		String userHome = System.getProperty("user.home");
		String subDir = "print-here/generated-files/" + name + "/movie";
		Path root = Paths.get(userHome, subDir, sheetName);
		try {
			if (!Files.exists(root)) {
				Files.createDirectories(root);
			}

//			// Cria o diretório com o título
//			Path titleDir = root.resolve(sheetName);
//			Files.createDirectories(titleDir);

			// Cria o arquivo dentro do diretório raiz
			Path filePath = root.resolve(sheetName + ".txt");
			
			
			Files.write(filePath, plot.getBytes());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void andressInTxt(String name, String json, String logradouro) {
		String userHome = System.getProperty("user.home");
		String subDir = "print-here/generated-files/" + name + "/andress";
		Path root = Paths.get(userHome, subDir);
		try {
			if (!Files.exists(root)) {
				Files.createDirectories(root);
			}
			
			// Criar um objeto Gson com formatado
			Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
			Address opa = GsonConverter.toAddressString(json);
			String texto = prettyGson.toJson(opa);

			// Cria o diretório com o título
			Path titleDir = root.resolve(logradouro);
			Files.createDirectories(titleDir);

			// Cria o arquivo dentro do diretório raiz
			Path filePath = titleDir.resolve(logradouro + ".txt");
			// Files.write(filePath, json.getBytes());
			Files.write(filePath, texto.getBytes());
			System.out.println(filePath.toAbsolutePath());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

//	static void salvarPlanilha(Workbook workbook, String name) {
//		// Define o nome do arquivo com base no timestamp atual
//		String fileName = name + "_" + System.currentTimeMillis() + ".xlsx";
//		String userHome = System.getProperty("user.home");
//
//		// Define o nome do subdiretório
//		String subDir = "print-here/generated-files";
////		String path = userHome + File.separator + subDir + File.separator;
//		String path = userHome + File.separator + subDir + File.separator + name + File.separator;
//
//		// Cria o arquivo
//		File dir = new File(path, fileName);
//		if (!dir.exists()) {
//			dir.mkdirs();
//		}
//	}

}
