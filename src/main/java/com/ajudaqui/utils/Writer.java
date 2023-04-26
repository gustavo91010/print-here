package com.ajudaqui.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ajudaqui.api.entity.Movie;

public class Writer {

//	java.nio.file.Files

//	public void inTxt(String name, Movie movie) {
	public void inTxt(String name, String json, String title) {
		String userHome = System.getProperty("user.home");
		String subDir = "print-here/generated-files/" + name;
		Path root = Paths.get(userHome, subDir);
		try {
			if (!Files.exists(root)) {
				Files.createDirectories(root);
			}

			// Cria o arquivo dentro do diretório raiz
			Path filePath = root.resolve(title+".txt");
			 Files.write(filePath, json.getBytes());


			// Imprime o caminho absoluto do arquivo
//			System.err.println("Arquivo salvo em: " + root.toAbsolutePath());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	public static void planilhaFilme(String name, String content) {

//		Filme filme = (Filme) GsonConverter.toObject(content, Filme.class);
		Movie filme = GsonConverter.toMovie(content);
		// Crie uma nova planilha do Excel
		Workbook workbook = new XSSFWorkbook();

		// Crie uma nova folha na planilha
//		Sheet sheet = workbook.createSheet("Dados");
		Sheet sheet = workbook.createSheet(name);

		// Crie uma linha para os cabeçalhos das colunas
		Row headerRow = sheet.createRow(0);

		// Adicione os cabeçalhos das colunas à linha do cabeçalho
		Cell headerCell = headerRow.createCell(0);
		headerCell.setCellValue("Descrição");

		headerCell = headerRow.createCell(1);
		headerCell.setCellValue("Valor");
		alimentarPlanilhaFilme(sheet, filme);

		salvarPlanilha(workbook, name);

	}

	static Row alimentarPlanilhaFilme(Sheet sheet, Movie filme) {
		// Adicione os dados do objeto à planilha
//		Object objeto = null; // seu objeto Java
		int rowNum = 1;
		Row row = sheet.createRow(rowNum);
		row.createCell(0).setCellValue(filme.getTitle());
		row.createCell(1).setCellValue(filme.getYear());
		row.createCell(2).setCellValue(filme.getRuntime());
		row.createCell(3).setCellValue(filme.getPlot());
		row.createCell(4).setCellValue(filme.getPoster());
		row.createCell(5).setCellValue(filme.getImdbRating());
		row.createCell(6).setCellValue(filme.getImdbVotes());
		row.createCell(7).setCellValue(filme.getImdbID());

		return row;

	}

	static void salvarPlanilha(Workbook workbook, String name) {
		// Define o nome do arquivo com base no timestamp atual
		String fileName = name + "_" + System.currentTimeMillis() + ".xlsx";
		String userHome = System.getProperty("user.home");

		// Define o nome do subdiretório
		String subDir = "print-here/generated-files";
//		String path = userHome + File.separator + subDir + File.separator;
		String path = userHome + File.separator + subDir + File.separator + name + File.separator;

		// Cria o arquivo
		File dir = new File(path, fileName);
		if (!dir.exists()) {
			dir.mkdirs();
		}
	}

}
