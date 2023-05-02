package com.ajudaqui.app.pages;

import java.net.URI;
import java.util.Scanner;

import com.ajudaqui.api.entity.Address;
import com.ajudaqui.api.service.AddressService;

public class Page03_02_address {
	

	public void cep(Scanner scanner, String name,AddressService addressService) {
		String opcao= "";
		String sair="sair";
		while(!opcao.equalsIgnoreCase(sair)) {
		
			System.out.println("vamos lá, digiteo seu endereço: ");
			System.out.println("Digite o nome da rua:");
			String rua=scanner.next();
			System.out.println("Digite o nome da cidade:");
			String cidade=scanner.next();
			System.out.println("Digite a sigla do estado (Com 2 digitos):");
			String estado=scanner.next().toUpperCase();
			
			Address address = addressService.addressByCep(estado, cidade, rua);
			
			Page03_address.oQueFazer(scanner, name, addressService, address);
			
			
			
			
			
			System.out.println("\nSe desejar, digite sair e conseguirá.");
			opcao=scanner.next();
		}
	}

}
