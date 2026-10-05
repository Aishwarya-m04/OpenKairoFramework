package com.openkairo.genericUtilty;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyUtility {
	public String toReadDataFromPropertyFile( String key) throws Exception {

		FileInputStream fis=new FileInputStream("./src/test/resources/commondata.properties");
		Properties p=new Properties();
		p.load(fis);
		String value = p.getProperty(key);
		return value;
		
		}
}
