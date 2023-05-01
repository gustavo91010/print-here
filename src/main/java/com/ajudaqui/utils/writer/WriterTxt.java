package com.ajudaqui.utils.writer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class WriterTxt {


	public void movieInTxt(String name, String json, String title) {
		String userHome = System.getProperty("user.home");
		String subDir = "print-here/generated-files/" + name+"/movie";
		Path root = Paths.get(userHome, subDir);
		try {
			if (!Files.exists(root)) {
				Files.createDirectories(root);
			}

			// Cria o diretório com o título
	        Path titleDir = root.resolve(title);
	        Files.createDirectories(titleDir);
	        
			// Cria o arquivo dentro do diretório raiz
			Path filePath = titleDir.resolve(title+".txt");
			 Files.write(filePath, json.getBytes());



		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void andressInTxt(String name, String json, String logradouro) {
		String userHome = System.getProperty("user.home");
		String subDir = "print-here/generated-files/" + name+"/andress";
		Path root = Paths.get(userHome, subDir);
		try {
			if (!Files.exists(root)) {
				Files.createDirectories(root);
			}

			// Cria o diretório com o título
	        Path titleDir = root.resolve(logradouro);
	        Files.createDirectories(titleDir);
	        
			// Cria o arquivo dentro do diretório raiz
			Path filePath = titleDir.resolve(logradouro+".txt");
			 Files.write(filePath, json.getBytes());
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
