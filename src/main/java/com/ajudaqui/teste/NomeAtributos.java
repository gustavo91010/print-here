package com.ajudaqui.teste;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
//import java.lang.reflect.Modifier;

public class NomeAtributos {
	public static void main(String[] args) throws ClassNotFoundException {
		
		
		
//		Class cls = Class.forName("Movie");
//		Class<?> cls = Class.forName("com.ajudaqui.teste.Movie");
		 Class<?> cls = Class.forName("com.ajudaqui.api.entity.Movie");
		 Field fieldList[] = cls.getDeclaredFields();
		 for( int i=0; i< fieldList.length; i++) {
			 Field fld= fieldList[i];
			 System.out.println("nome= "+fld.getName());
			 System.out.println("cecl class= "+fld.getDeclaringClass());
			 System.out.println("Type= "+fld.getType());
			 int mod = fld.getModifiers();
	           System.out.println("modifiers = " +
	                      Modifier.toString(mod));
	           System.out.println("-----");
		 }
		 
		    
	}

}
