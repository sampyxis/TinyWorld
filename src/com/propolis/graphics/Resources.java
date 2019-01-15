package com.propolis.graphics;

import java.io.InputStream;


public class Resources {

	public static InputStream getResource(String ref) {
		InputStream in = Resources.class.getClassLoader().getResourceAsStream(ref);
		if (in == null) {
			throw new RuntimeException("Unable to access resource: "+ref);
		}
		
		return in;
	}
	
	public static boolean exists(String ref) {
		InputStream in = Resources.class.getClassLoader().getResourceAsStream(ref);
	
		return in != null;
	}
}