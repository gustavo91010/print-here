package com.ajudaqui.teste.omdb;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Tradutor {
//	https://mymemory.translated.net/doc/spec.php
	
	 public static void main(String[] args) throws IOException {
		 String sourceText = "hello world";
	        String sourceLanguage = "en";
	        String targetLanguage = "pt-BR";

	        String endpoint = String.format("https://api.mymemory.translated.net/get?q=%s&langpair=%s|%s", 
	                URLEncoder.encode(sourceText, StandardCharsets.UTF_8), 
	                sourceLanguage, 
	                targetLanguage);

	        URL url = new URL(endpoint);
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        connection.setRequestMethod("GET");

	        Scanner scanner = new Scanner(connection.getInputStream());
	        StringBuilder response = new StringBuilder();
	        while (scanner.hasNextLine()) {
	            response.append(scanner.nextLine());
	        }
	        scanner.close();

	        String translatedText = response.toString().split("\"")[11];

	        System.out.println("Texto original: " + sourceText);
	        System.out.println("Texto traduzido: " + translatedText);
	    }

}
