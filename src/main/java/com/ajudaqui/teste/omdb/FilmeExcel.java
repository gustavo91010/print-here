package com.ajudaqui.teste.omdb;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class FilmeExcel {
	public static void main(String[] args) throws IOException, InterruptedException {
		
//		HttpClient client = HttpClient.newHttpClient();
//		String apiKes="6a6e3aa7";
////		String title="aladin";
////		String title="matrix";
////		String title="divertidamente";
//		String title="aladin";
////		String title="O%20Senhor%20dos%20Anéis";
//		
//		String uri=String.format("http://www.omdbapi.com/?t=%s&apikey=%s", title,apiKes);
//		   HttpRequest request = HttpRequest.newBuilder()
//		         .uri(URI.create(uri))
//		         .build();
//		   
//		   HttpResponse<String> response = client
//				     .send(request, BodyHandlers.ofString());
//		   System.out.println();
//		   Movie filme=GsonConverter.toMovie(response.body());
//		   System.out.println(filme.toString());
//		   
//		   Writer.planilhaFilme(title, uri);
		
		// criar um novo objeto Workbook
//		Workbook workbook = new XSSFWorkbook();

		// criar uma nova planilha
//		Sheet sheet = workbook.createSheet("Minha Planilha");
//
//		// criar a primeira linha na planilha
//		Row row = sheet.createRow(0);
//
//		// criar as células na primeira linha
//		Cell cell1 = row.createCell(0);
//		Cell cell2 = row.createCell(1);
//
//		// adicionar valores às células
//		cell1.setCellValue("Valor da célula 1");
//		cell2.setCellValue("Valor da célula 2");
//
//		// salvar a planilha em um arquivo
//		try {
//		    FileOutputStream outputStream = new FileOutputStream("caminho/para/o/arquivo/nome-do-arquivo.xlsx");
//		    workbook.write(outputStream);
//		    workbook.close();
//		} catch (IOException e) {
//		    e.printStackTrace();
//		}
//
//		   
		
		// criar um novo objeto Workbook
		Workbook workbook = new XSSFWorkbook();

		// criar uma nova planilha
		Sheet sheet = workbook.createSheet("Minha Planilha");

		// criar a primeira linha na planilha
		Row row = sheet.createRow(0);

		// criar as células na primeira linha
		Cell cell1 = row.createCell(0);
		cell1.setCellValue("Title");
		Cell cell2 = row.createCell(1);
		cell2.setCellValue("Year");
		Cell cell3 = row.createCell(2);
		cell3.setCellValue("Runtime");
		Cell cell4 = row.createCell(3);
		cell4.setCellValue("Plot");
		Cell cell5 = row.createCell(4);
		cell5.setCellValue("Poster");
		Cell cell6 = row.createCell(5);
		cell6.setCellValue("imdbRating");
		Cell cell7 = row.createCell(6);
		cell7.setCellValue("imdbVotes");
		Cell cell8 = row.createCell(7);
		cell8.setCellValue("imdbID");

		// adicionar valores às células
//		Row row2 = sheet.createRow(1);
//		row2.createCell(0).setCellValue(title);
//		row2.createCell(1).setCellValue(Year);
//		row2.createCell(2).setCellValue(Runtime);
//		row2.createCell(3).setCellValue(Plot);
//		row2.createCell(4).setCellValue(Poster);
//		row2.createCell(5).setCellValue(imdbRating);
//		row2.createCell(6).setCellValue(imdbVotes);
//		row2.createCell(7).setCellValue(imdbID);

		// salvar a planilha em um arquivo
		try {
		    FileOutputStream outputStream = new FileOutputStream("caminho/para/o/arquivo/nome-do-arquivo.xlsx");
		    workbook.write(outputStream);
		    workbook.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}
		   System.err.println("fim");
		
	}

}
