package com.ajudaqui.utils;

import java.util.Scanner;
import static java.lang.Character.isLetter;

public class InputBroker {
	
	public static int isInt(Scanner scanner) {
		String valorRecebido = "";
		try {

			boolean sair = true;

			while (sair) {
				valorRecebido = scanner.next();

				char[] array = valorRecebido.toCharArray();
				if (!isLetter(array[0])) {
					sair = false;
				}
				if (sair) {
					System.err.println("\nNão pode ser texto, só numeros,\nPor favor, tente novamente:");
				}
			}
			return Integer.valueOf(valorRecebido);

		} catch (Exception e) {

		}
		return Integer.valueOf(valorRecebido);
	} 

}
