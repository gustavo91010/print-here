package com.ajudaqui.app.pages;

import java.util.Scanner;

import com.ajudaqui.api.entity.Address;
import com.ajudaqui.api.service.AddressService;
import com.ajudaqui.utils.InputBroker;

public class Page03_address {
	private AddressService addressService= new AddressService();
			

public void address(Scanner scanner, String name) {
	String opcao= "";
	String sair="sair";
	while(!opcao.equalsIgnoreCase(sair)) {
		
		System.out.println("\nTudo bem, vamos lá,\nVoçe quer fazer a pesquisa pelo endereco ou pelo CEP?");
		System.out.println("\nDigite 1 para Endereço,");
		System.out.println("Digite 2 para CEP:\n");
	Integer menu1= InputBroker.isInt(scanner);
	if(menu1 ==1) {
		new Page03_02_address().cep(scanner, name, addressService);
	}
	if(menu1==2) {
		new Page03_01_cep().cep(scanner, name, addressService);
	}
		
		
		System.out.println("\nSe desejar, digite sair e conseguirá.");
		opcao=scanner.next();
	}
}

public static void oQueFazer(Scanner scanner, String name, AddressService addressService, Address address) {
	Integer menu;
	System.out.println("\nO que voce deseja fazer agora:\n");
	System.out.println("Digite 1 para somente salvar,");
	System.out.println("Digite 2 para somente abrir,");
	System.out.println("Digite 3 salvar e abrir.");
	
	  menu= InputBroker.isInt(scanner);
	  switch (menu) {
	  
	  
	case 1:
		addressService.inText(address, name);
		addressService.inSpreadsheet(address, name);
		break;
	case 2:
		addressService.openMaps(address.getCep());
		break;
	case 3:
		addressService.inText(address, name);
		addressService.inSpreadsheet(address, name);
		addressService.openMaps(address.getCep());
		
		break;

	
	}}

}
