package com.ajudaqui.app.pages;

import java.util.Scanner;

import com.ajudaqui.utils.InputBroker;

public class Page01 {

	public void page01(Scanner scanner) {
		System.out.println("comecou");
		String opcao = "";
		String sair = "sair";
		while (!opcao.equalsIgnoreCase(sair)) {
			
			System.out.println("Olá,  qual o seu nome?\n");
			String name = scanner.next();

			System.out.println("\nVamos lá " + name + " o que voce deseja fazer?\n");
			System.out.println("Digite 1 para ver Buscar Filmes.");
			System.out.println("Digite 2 para ver Buscar Enderecços.");
			
			Integer menu = InputBroker.isInt(scanner);
			if (menu == 1) {
				
				new Page02_movie().filmes(scanner, name);
			}
			if (menu == 2) {
				new Page03_address().address(scanner, name);

			}

			System.out.println("\nSe desejar, digite sair e conseguirá.");
			opcao = scanner.next();

		}
		System.out.println("encerrou");
	}

}
