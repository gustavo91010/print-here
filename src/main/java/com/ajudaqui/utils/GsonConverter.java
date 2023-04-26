package com.ajudaqui.utils;

import com.ajudaqui.api.entity.Address;
import com.ajudaqui.api.entity.Movie;
import com.google.gson.Gson;

public class GsonConverter {

	public static Address[] toAddress(String json) {
		Gson gson = new Gson();
		Address[] adress =(Address[]) gson.fromJson(json, Address[].class);
//
		return adress;
	}
	public static String addressToJson(Address adress) {
		Gson gson = new Gson();
		String json = gson.toJson(adress);
		
		return json;
	}
	
	
	public static Movie toMovie(String json) {
		Gson gson = new Gson();
		Movie filme = gson.fromJson(json, Movie.class);
		return filme;
	}
	public static String movieToJson(Movie movie) {
		Gson gson = new Gson();
		String json = gson.toJson(movie);
		
		return json;
	}
	

}
