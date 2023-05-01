package com.ajudaqui.teste.omdb;

import java.util.Scanner;

import com.ajudaqui.app.pages.Page02_movie;

public class Test2 {
	public static void main(String[] args) {
		Scanner scanner= new Scanner(System.in);
		String name = "Fiodor";
		
		new Page02_movie().filmes(scanner, name);
				
		
	}

}
