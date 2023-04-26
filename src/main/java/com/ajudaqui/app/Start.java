package com.ajudaqui.app;

import java.util.Scanner;

import com.ajudaqui.app.pages.Page01;

public class Start {
	
	public static void init() {
		Scanner scanner = new Scanner(System.in);
		new Page01().page01(scanner);
	}
	
//	System.out.println("comecou");
//	String opcao= "";
//	String sair="sair";
//	while(!opcao.equalsIgnoreCase(sair)) {
//		
//		
//		
//		System.out.println("\nSe desejar, digite sair e conseguirá.");
//		opcao=scanner.next();
//	}

}
