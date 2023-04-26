package com.ajudaqui.app.pages;

import java.util.Scanner;

import com.ajudaqui.api.entity.Movie;
import com.ajudaqui.api.service.OmdbService;
import com.ajudaqui.utils.InputBroker;

public class Page02 {
	
	private OmdbService omdbService = new OmdbService();
	
	public void filmes(Scanner scanner, String name) {
		String opcao= "";
		String sair="sair";
		while(!opcao.equalsIgnoreCase(sair)) {
			System.out.println("Tudo bem, vamos lá, me diga, qual titulo voce quer encontrar? ");
		String title="";
		title= scanner.next();
			System.out.println("\nOk, tudo bem, vou buscar o filme "+title+" para voce, só um instante...");
		
			Movie movie = omdbService.calUp(title);
			while(movie.getTitle()== null) {
				System.out.println("Seu filme não foi encontrado,\nporfavor, tente outro.");
				title= scanner.next();
				movie = omdbService.calUp(title);
			}
			
			
			
			
			
			System.out.println("Aqui esta seu Filme: \n");
			System.out.println(movie.toString());
			
			
			System.out.println("\nagora, digite:\n");
			System.out.println("1 - Para salvá-lo em um arquivo txt,");
			System.out.println("2 - Para salvá-lo em uma planilha Excel,");
			System.out.println("3 - Somente consulta mesmo.");
			Integer menu= InputBroker.isInt(scanner);
			
			if(menu ==1) {
				omdbService.inText(movie, name, title);
				
				if(!(movie.getPoster()==null)) {
					omdbService.imageDownload(movie.getPoster(), name, title);
					
				}
			}
			if(menu ==2) {
				omdbService.inSpreadsheet(movie, name);
			}
			System.out.println("");
			
			
			
			
			
			System.out.println("\nSe desejar, digite sair e conseguirá,");
			System.out.println("Clique em qualquer tecla para continuar.");
			opcao=scanner.next();
		}
	}

}
