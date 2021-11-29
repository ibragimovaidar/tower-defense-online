package ru.kpfu.itis.ibragimovaidar.util;

import java.io.IOException;
import java.util.Properties;

public final class PropertiesUtil {

	private static final Properties PROPERTIES;

	static {
		try {
			PROPERTIES = new Properties();
			PROPERTIES.load(PropertiesUtil.class.getClassLoader().getResourceAsStream("application.properties"));
		} catch (IOException e) {
			throw new RuntimeException("Load properties error", e);
		}
	}

	public static String getProperty(String key){
		String property = PROPERTIES.getProperty(key);
		if (property != null){
			return property;
		}
		throw new PropertiesUtilException("Property " + key + "does not exists");
	}

	public static int getIntProperty(String key){
		try {
			return Integer.parseInt(PROPERTIES.getProperty(key));
		} catch (NumberFormatException e) {
			throw new PropertiesUtilException("Property \"" + key + "\" can not be converted to int" , e);
		}
	}

	private static class PropertiesUtilException extends RuntimeException {

		public PropertiesUtilException(String message) {
			super(message);
		}

		public PropertiesUtilException(String message, Throwable cause) {
			super(message, cause);
		}
	}

	private PropertiesUtil(){
		throw new RuntimeException();
	}
}
