package com.ajudaqui.teste.viaCep;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import com.ajudaqui.entity.Address;
import com.ajudaqui.utils.GsonConverter;
import com.google.gson.Gson;

public class PorNome {
	
//	viacep.com.br/ws/RS/Porto Alegre/Domingos Jose/json/
	public static void main(String[] args) throws IOException, InterruptedException {
		
		HttpClient cliente = HttpClient.newHttpClient();
		String uf="pe";
//		String cidade="recife";
		String cidade="Jaboatão dos Guararapes";
		String rua="Rua Professor Severino Tolentino";
		
		String uri3="https://viacep.com.br/ws/"+uf+"/"+cidade+"/"+rua+"/json/";
//		String uri3="https://viacep.com.br/ws/pe/Jaboatão dos Guararapes/Rua Professor Severino Tolentino/json/";
		HttpRequest requisicao = HttpRequest.newBuilder().uri(URI.create(uri3.replace(" ", "%20"))).build();
		HttpResponse<String> resposta = cliente.send(requisicao, BodyHandlers.ofString());
		
		System.out.println(resposta.body());
		
		Gson gson = new Gson();
//		
//		Endereco[] enderecos = gson.fromJson(resposta.body(), Endereco[].class);
		Address[] enderecos =(Address[]) gson.fromJson(resposta.body(), Address[].class);
		System.out.println(enderecos[0].getLogradouro());
		
	}

}
