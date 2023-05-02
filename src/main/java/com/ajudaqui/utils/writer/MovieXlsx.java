package com.ajudaqui.utils.writer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
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

public class MovieXlsx {

	public static Path inXlsx(Movie movie, String name) throws ClassNotFoundException, IOException {
		// cria uma nova planilha
		Workbook planilha = new XSSFWorkbook();
		
		// Retira caracters especiais que nao podem estar no titulo da planilha
		String sheetName = movie.getTitle().replaceAll("[:*?/\\[\\]]", "");
		Sheet folha = planilha.createSheet(sheetName );

		// Formatar cabeçalho
		CellStyle estiloCabecalho = styloCabecalho(planilha);

		// --------------------------------------- Conteudo
		// ----------------------------------------
		CellStyle estiloConteudo = estiloConteudo(planilha);

		String caminho = "com.ajudaqui.api.entity.Movie";
		List<String> atributoList = movieAtb(caminho);
		Map<String, String> valoresList = movieValues(movie);

		int posicaoLinha = 1;
		for (String atb : atributoList) {
			Row linha = folha.createRow(posicaoLinha++);
			Cell celula;

			// Atributo:
			celula = linha.createCell(0);
			celula.setCellStyle(estiloCabecalho);
			celula.setCellValue(atb);
			folha.autoSizeColumn(0);

			// Valor:
			celula = linha.createCell(1);
			celula.setCellStyle(estiloConteudo);
			celula.setCellValue(valoresList.get(atb));
			folha.autoSizeColumn(1);
		}
		Path path = escreverPanilha(planilha, movie, name);
		return path.toAbsolutePath(); 

	}

	private static Path escreverPanilha(Workbook planilha, Movie movie, String name) throws IOException {
			// Retira caracters especiais que nao podem estar no titulo da planilha
			String sheetName = movie.getTitle().replaceAll("[:*?/\\[\\]]", "");
			String userHome = System.getProperty("user.home");
			String subDir = "print-here/generated-files/" + name+"/movie";
			Path pat0h = Paths.get(userHome, subDir,sheetName);
			
			Path path = pat0h.resolve(sheetName + ".xlsx");
			
			// Cria o diretório com o título
			if (!Files.exists(pat0h)) {
				Files.createDirectories(pat0h);
			}
			
			FileOutputStream arquivoSaida = new FileOutputStream(path.toString());
			planilha.write(arquivoSaida);
			arquivoSaida.flush();
			

			System.out.println("Seu arquivo se encontra em:\n" + path.toAbsolutePath());

		return path;

	}

	private static CellStyle estiloConteudo(Workbook planilha) {
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

	private static CellStyle styloCabecalho(Workbook planilha) {
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

	private static XSSFFont arial(Workbook planilha) {
		XSSFFont fonte = ((XSSFWorkbook) planilha).createFont();
		fonte.setFontName("Arial");
		fonte.setFontHeightInPoints((short) 12);
		fonte.setColor(IndexedColors.LIGHT_BLUE.getIndex());
		fonte.setBold(true);
		return fonte;
	}

	private static List<String> movieAtb(String caminho) throws ClassNotFoundException { // "com.ajudaqui.api.entity.Movie"
		List<String> atributos = new ArrayList<String>();
		Class<?> cls;
			cls = Class.forName(caminho);

			Field fieldList[] = cls.getDeclaredFields();
			for (int i = 0; i < fieldList.length; i++) {
				Field fld = fieldList[i];

				atributos.add(fld.getName());

			}
			atributos.remove("Plot");
			atributos.remove("Poster");
			atributos.remove("serialVersionUID");
		
		return atributos;

	}

	private static Map<String, String> movieValues(Movie movie) {
		Map<String, String> valores = new HashMap<String, String>();
		valores.put("title", movie.getTitle());
		valores.put("Year", movie.getYear());
		valores.put("Runtime", movie.getRuntime());
		valores.put("Plot", movie.getPlot());
		valores.put("Poster", movie.getPoster());
		valores.put("imdbRating", movie.getImdbRating());
		valores.put("imdbVotes", movie.getImdbVotes());
		valores.put("imdbID", movie.getImdbID());
		return valores;
	}

}
