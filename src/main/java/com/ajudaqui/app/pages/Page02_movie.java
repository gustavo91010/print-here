package com.ajudaqui.app.pages;

import java.util.Scanner;

import com.ajudaqui.api.entity.Movie;
import com.ajudaqui.api.service.OmdbService;
import com.ajudaqui.utils.InputBroker;

public class Page02_movie {
	
	private OmdbService omdbService = new OmdbService();
	
	public void filmes(Scanner scanner, String name) {
		String opcao= "";
		String sair="sair";
		while(!opcao.equalsIgnoreCase(sair)) {
			System.out.println("Tudo bem, vamos lá, me diga, qual titulo voce quer encontrar? ");
		String title="";
		  title= scanner.next();
		    scanner.nextLine(); // consumir a nova linha
			System.out.println("\nOk, tudo bem, vou buscar o filme "+title+" para voce, só um instante...");
		
			Movie movie = omdbService.calUp(title.replace(" ", "%20"));
			while(movie.getTitle()== null) {
				System.out.println("Seu filme não foi encontrado,\nporfavor, tente outro.");
				title= scanner.next();
				movie = omdbService.calUp(title);
			}

			System.out.println("Aqui esta seu Filme: \n");
			System.out.println(movie.toString());
			
			
			System.out.println("\nagora, digite:\n");
			System.out.println("1 - Para salvá-lo. ");
			System.out.println("2 - Somente consulta.");
			Integer menu= InputBroker.isInt(scanner);
			
			if(menu ==1) {
				omdbService.inText(movie, name, title);
				omdbService.inSpreadsheet(movie, name);
				
				
				if(!(movie.getPoster()==null)) {
					omdbService.imageDownload(movie.getPoster(), name, title);
					
				}
			}
			
			System.out.println("");
			
			System.out.println("\nSe desejar, digite sair e conseguirá,");
			System.out.println("Clique em qualquer tecla para continuar.");
			opcao=scanner.next();
		}
	}

}
