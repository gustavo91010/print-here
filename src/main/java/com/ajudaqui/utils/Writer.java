package com.ajudaqui.utils;

import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ajudaqui.entity.Movie;

public class Writer {

//	java.nio.file.Files

	public void inTxt(String name, Movie movie) {
		String userHome = System.getProperty("user.home");
		String subDir = "print-here/generated-files/" + name;
		Path root = Paths.get(userHome, subDir);
		try {
			if (!Files.exists(root)) {
				Files.createDirectories(root);
			}

			// Cria o arquivo dentro do diretório raiz
			Path filePath = root.resolve(movie.getTitle()+".txt");
			Files.createFile(filePath);

			// Cria um objeto OutputStream para o arquivo
			OutputStream outputStream = Files.newOutputStream(filePath);

			// Cria um objeto ObjectOutputStream para escrever o objeto no arquivo
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

			// Escreve o objeto no arquivo
			objectOutputStream.writeObject(movie);
			objectOutputStream.close();

			// Imprime o caminho absoluto do arquivo
			System.err.println("Arquivo salvo em: " + root.toAbsolutePath());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//
//	try {
//		creatingFolders(subDir, root);
//
//		// Define o nome do novo arquivo
//		String originalFilename = file.getOriginalFilename();
//		String extension = StringUtils.getFilenameExtension(originalFilename);
//		String newName = name + "." + extension;
//
//		// Define o caminho completo do arquivo de destino
//		Path dest = root.resolve(newName);
//
//		// Salva o arquivo em um diretório temporário
//		Path tempFile = Files.createTempFile("temp-", "-" + originalFilename);
//		Files.copy(file.getInputStream(), tempFile, StandardCopyOption.REPLACE_EXISTING);
//
//		// Move o arquivo para o novo nome e destino final
//		Files.move(tempFile, dest, StandardCopyOption.REPLACE_EXISTING);
//
//	} catch (FileAlreadyExistsException e) {
//
//		throw new RuntimeException("Um arquivo com esse nome já existe.");
//	} catch (IOException e) {
//
//		throw new RuntimeException("Ocorreu um erro ao salvar o arquivo: " + e.getMessage());
//	} catch (Exception e) {
//
//		throw new RuntimeException("Ocorreu um erro inesperado: " + e.getMessage());
//	}
//
//}
//
	private void creatingFolders(String subDir, Path root) throws IOException {
		if (!Files.exists(root)) {
			Files.createDirectories(root);
		}

		if (!Files.exists(root.resolve(subDir))) {
			Files.createDirectories(root.resolve(subDir));
		}
	}

	public static void inTxst(String name, String content) {

//		// Obtem o caminho do diretório do usuário
//		String userHome = System.getProperty("user.home");
//
//		// Define o nome do subdiretório
//		String subDir = "print-here/generated-files";
//
//		// Define o caminho completo do diretório de arquivos
//		String path = userHome + File.separator + subDir + File.separator + name + File.separator;
//
//		// Cria o diretório se não existir
//		File dir = new File(path);
//		if (!dir.exists()) {
//			dir.mkdirs();
//		}
//
//		// Define o nome do arquivo com base no timestamp atual
//		String fileName = name + "_" + System.currentTimeMillis() + ".txt";
//
//		// Cria o arquivo
//		File file = new File(dir, fileName);
//
//		try {
//			// Cria um FileWriter para escrever no arquivo
//			FileWriter writer = new FileWriter(file);
//
//			// Escreve o conteúdo no arquivo
//			writer.write(content);
//
//			// Fecha o FileWriter
//			writer.close();
//
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			System.err.println("Erro ao escrever o arquivo: " + e.getMessage());
//
//		}

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
//
//
//		// Cria o arquivo
//		File file = new File(dir, fileName);
//		try {
//			FileOutputStream outputStream = new FileOutputStream(file);
//
//			// Escreve a planilha no FileOutputStream
//			workbook.write(outputStream);
//
//			// Fecha a planilha e o FileOutputStream
//			workbook.close();
//			outputStream.close();
//
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

}
