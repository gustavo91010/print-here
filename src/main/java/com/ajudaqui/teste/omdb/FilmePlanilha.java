package com.ajudaqui.teste.omdb;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ajudaqui.api.entity.Movie;
import com.ajudaqui.utils.GsonConverter;

public class FilmePlanilha {
	public static void main(String[] args) throws IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();
		String apiKes="6a6e3aa7";
		String title="rush";
//		String title="matrix";
//		String title="divertidamente";
//		String title="O%20Senhor%20dos%20Anéis";
		
		String uri=String.format("http://www.omdbapi.com/?t=%s&apikey=%s", title,apiKes);
		   HttpRequest request = HttpRequest.newBuilder()
		         .uri(URI.create(uri))
		         .build();
		   
		   HttpResponse<String> response = client
				     .send(request, BodyHandlers.ofString());
		   Movie movie=GsonConverter.toMovie(response.body());

		   
//		   ---------------------- Planilha ----------------------------
		// cria uma nova planilha
		Workbook planilha = new XSSFWorkbook();
		String sheetName = movie.getTitle().replaceAll("[:*?/\\[\\]]", "");
		Sheet folha = planilha.createSheet(sheetName);

		// Formatar cabeçalho
		CellStyle estiloCabecalho = styloCabecalho(planilha);
		
		
		//--------------------------------------- Conteudo ----------------------------------------
		CellStyle estiloConteudo = estiloConteudo(planilha);
		
		String caminho= "com.ajudaqui.api.entity.Movie";
		List<String> atributoList = atributos(caminho);
		 Map<String, String> valoresList = valores(movie);
		
		
		int posicaoLinha = 1;
		for( String atb : atributoList) {
			 Row linha = folha.createRow(posicaoLinha++);
			 Cell celula;
			 
			 // Atributo:
			 celula= linha.createCell(0);
//			 celula.setCellStyle(estiloConteudo);
			 celula.setCellStyle(estiloCabecalho);
			 celula.setCellValue(atb);
			 folha.autoSizeColumn(0);
			 
			 
			 
			 
			 //Valor:
			 celula= linha.createCell(1);
			 celula.setCellStyle(estiloConteudo);
			 celula.setCellValue(valoresList.get(atb));
			 folha.autoSizeColumn(1);
		}
		
		String userHome = System.getProperty("user.home");
		String subDir = "print-here/generated-files/fred";
//		Path root = Paths.get(userHome, subDir);
		Path path = Paths.get(userHome, subDir,sheetName+".xlsx");
//		FileOutputStream arquivoSaida = new FileOutputStream(movie.getTitle()+".xlsx");
		FileOutputStream arquivoSaida = new FileOutputStream(path.toString());
		planilha.write(arquivoSaida);
		arquivoSaida.flush();
	
		System.out.println("Seu arquivo se encontra em:\n"+path.toAbsolutePath());
		
		planilha.close();
	}

	
public void filmePlanilha(String caminho, Movie movie) {
	
}
	public static CellStyle estiloConteudo(Workbook planilha) {
		CellStyle estiloConteudo = planilha.createCellStyle();
		estiloConteudo.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
		estiloConteudo.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		// definindo a borda
		estiloConteudo.setBorderBottom(BorderStyle.MEDIUM);
		estiloConteudo.setBorderTop(BorderStyle.MEDIUM);
		estiloConteudo.setBorderLeft(BorderStyle.MEDIUM);
		estiloConteudo.setBorderRight(BorderStyle.MEDIUM);
		return estiloConteudo;
	}

	public static CellStyle styloCabecalho(Workbook planilha) {
		CellStyle estiloCabecalho = planilha.createCellStyle();
		estiloCabecalho.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
		estiloCabecalho.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		// definindo a borda
		estiloCabecalho.setBorderBottom(BorderStyle.MEDIUM);
		estiloCabecalho.setBorderTop(BorderStyle.MEDIUM);
		estiloCabecalho.setBorderLeft(BorderStyle.MEDIUM);
		estiloCabecalho.setBorderRight(BorderStyle.MEDIUM);

		XSSFFont fonte = arial(planilha);
		estiloCabecalho.setFont(fonte);
		return estiloCabecalho;
	}

	public static XSSFFont arial(Workbook planilha) {
		XSSFFont fonte = ((XSSFWorkbook) planilha).createFont();
		fonte.setFontName("Arial");
		fonte.setFontHeightInPoints((short) 12);
		fonte.setColor(IndexedColors.LIGHT_BLUE.getIndex());
		fonte.setBold(true);
		return fonte;
	}

	public static List<String> atributos(String caminho) { // "com.ajudaqui.api.entity.Movie"
List<String> atributos= new ArrayList<String>();
		Class<?> cls;
		try {
			cls = Class.forName(caminho);

			Field fieldList[] = cls.getDeclaredFields();
			for (int i = 0; i < fieldList.length; i++) {
				Field fld = fieldList[i];
					
					atributos.add(fld.getName());

			}
			atributos.remove("Plot");
			atributos.remove("Poster");
			atributos.remove("serialVersionUID");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return atributos;

	}
	private static Map<String, String > valores(Movie movie) {
		 Map<String, String> valores= new HashMap<String, String>();
		 valores.put("title", movie.getTitle());
		 valores.put("Year",movie.getYear());
		 valores.put("Runtime",movie.getRuntime());
		 valores.put("Plot",movie.getPlot());
		 valores.put("Poster",movie.getPoster());
		 valores.put("imdbRating",movie.getImdbRating());
		 valores.put("imdbVotes",movie.getImdbVotes());
		 valores.put("imdbID",movie.getImdbID());
		return valores;
	}

}
