package com.ajudaqui.api.service;

import java.awt.Desktop;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;

import com.ajudaqui.api.config.ConnectionFactory;
import com.ajudaqui.api.entity.Address;
import com.ajudaqui.utils.GsonConverter;
import com.ajudaqui.utils.writer.AddressXlsx;
import com.ajudaqui.utils.writer.WriterTxt;

public class AddressService {
	
	private URI byAddress(String uf, String city, String street) {
		String uri3="https://viacep.com.br/ws/"+uf+"/"+city+"/"+street+"/json/";

		return URI.create(uri3.replace(" ", "%20"));
		
	}
	public Address addressByCep(int cep) {
		URI uri = byCep(cep);
		Address address = calUp(uri);
	return address;	
	}
	public Address addressByCep(String uf, String city, String street) {
		URI uri = byAddress(uf, city, street);
		Address address = calUp(uri);
		return address;	
	}
	
	private URI byCep(int cep) {
		String uri=String.format("https://viacep.com.br/ws/%d/json/", cep);
		
		return URI.create(uri.replace(" ", "%20"));
		
	}
	
	private Address calUp(URI uri) {
		String response = ConnectionFactory.request(uri);
		
//		Gson gson= new Gson();
//		Address[] enderecos =(Address[]) gson.fromJson(response, Address[].class);
		
		 Address enderecos = GsonConverter.toAddressString(response);
		return enderecos;
	}
	
	public void inText(Address address, String name) {
		String context = GsonConverter.addressToJson(address);
		WriterTxt.andressInTxt(name, context, address.getLogradouro());
	}

	public void inSpreadsheet(Address address, String name) {
//		String context = GsonConverter.addressToJson(address);;
		try {
			AddressXlsx.inXlsx(address, name);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void openMaps(String cep) {
		try {
			String url = "http://maps.google.com/maps?q=" + URLEncoder.encode(cep, "UTF-8");
			
			Desktop.getDesktop().browse(new URI(url));
			System.out.println("Ve la que ja abriu");
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
