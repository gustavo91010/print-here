package com.ajudaqui.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class WriterTxt {

//	java.nio.file.Files

	public void inTxt(String name, String json, String title) {
		String userHome = System.getProperty("user.home");
		String subDir = "print-here/generated-files/" + name;
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


			// Imprime o caminho absoluto do arquivo
//			System.err.println("Arquivo salvo em: " + root.toAbsolutePath());

		} catch (IOException e) {
			// TODO Auto-generated catch block
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
