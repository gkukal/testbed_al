package com.jg.gk.dynamic;

public class Util {

	
	public static String print( String s , int l ){
		StringBuffer buffer = new StringBuffer();
		for( int i =0 ; i < l ; i++){
			buffer.append("+");
		}
		buffer.append( s );
		System.out.println( buffer.toString());
		return buffer.toString();
	}
}
