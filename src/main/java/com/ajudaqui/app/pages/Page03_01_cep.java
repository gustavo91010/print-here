package com.ajudaqui.app.pages;

import java.util.Scanner;

import com.ajudaqui.api.entity.Address;
import com.ajudaqui.api.service.AddressService;
import com.ajudaqui.utils.InputBroker;

public class Page03_01_cep {
	

	public void cep(Scanner scanner, String name,AddressService addressService) {
		String opcao= "";
		String sair="sair";
		while(!opcao.equalsIgnoreCase(sair)) {
			System.out.println("\nVamos lá, digiteo cep com 7 digitos:\n");
			Integer menu= InputBroker.isInt(scanner);
			Address address = addressService.addressByCep(menu);
			System.out.println("\nAqui esta seu Endereço: ");
			System.out.println(address.toString());
			
			Page03_address.oQueFazer(scanner, name, addressService, address);
			
			
			
			System.out.println("\nSe desejar, digite sair e conseguirá.");
			opcao=scanner.next();
		}
	}

	
	

}
