package com.ajudaqui.teste.viaCep;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;

public class TestMaps {
	public static void main(String[] args) throws IOException, URISyntaxException {
//		String cep = "51340-350";
		String cep = "51340722";
		String url = "http://maps.google.com/maps?q=" + URLEncoder.encode(cep, "UTF-8");

		  Desktop.getDesktop().browse(new URI(url));


	}

}

//if (Desktop.isDesktopSupported()) {
//try {
//    Desktop.getDesktop().browse(new URI(url));
//} catch (IOException e) {
//    e.printStackTrace();
//} catch (URISyntaxException e) {
//    e.printStackTrace();
//}
//}
