package com.luca.moviereviews.core.utils;

public class CsvUtils {
	
	
	private CsvUtils() {}
	
	
	public static String escape(String data) {
		String escaped="";
		
		if(data==null) {
			return escaped;
		}
		escaped=data.replaceAll("\\R", " ");
		if(data.contains(",") || data.contains("\"") || data.contains("'")) {
			data=data.replace("\"", "\"\"");
			escaped="\""+data+"\"";
		}
		return escaped;
	}

}
