package com.ajudaqui.api.service;

import java.net.URI;

import com.ajudaqui.api.config.ConnectionFactory;
import com.ajudaqui.api.entity.Address;
import com.ajudaqui.utils.GsonConverter;
import com.ajudaqui.utils.WriterTxt;

public class AddressService {
	
	public URI byAddress(String uf, String city, String street) {
		String uri3="https://viacep.com.br/ws/"+uf+"/"+city+"/"+street+"/json/";

		return URI.create(uri3.replace(" ", "%20"));
		
	}
	
	public Address calUp(URI uri) {
		String response = ConnectionFactory.request(uri);
		
//		Gson gson= new Gson();
//		Address[] enderecos =(Address[]) gson.fromJson(response, Address[].class);
		
		Address[] enderecos= GsonConverter.toAddress(response);
		return enderecos[0];
	}
	
	public void inText(Address address, String name) {
		String context = GsonConverter.addressToJson(address);
//		Writer.inTxt(name, context);
	}

	public void inSpreadsheet(Address address, String name) {
		String context = GsonConverter.addressToJson(address);;
//		WriterTxt.planilhaFilme(name, context);
	}

}
