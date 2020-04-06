package com.inetbanking.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfigPropertiesFile  {

	Properties properties; 
	
	public ReadConfigPropertiesFile()
	{
		
		File src=new File("./ConfigurationsFiles/config.properties");
		
		try
		{
			FileInputStream readFile=new FileInputStream(src);
			properties=new Properties();
			properties.load(readFile);
		}
		catch(Exception e)
		{
			System.out.println("Exception while reading config properties file: "+e);
			
		}
	}
	
	public String getApplicationURL()
	{
		String appURL=properties.getProperty("applicationURLParameter");
		
		return appURL;
	}
	
	public String getUserName()
	{
		String username=properties.getProperty("userNameParameter");
		
		return username;
	}
	
	public String getPassword()
	{
		String password=properties.getProperty("passwordParameter");
		
		return password;
	}
	
	public String getChromeDriverPath()
	{
		String chromeDriverPath=properties.getProperty("chromeDriverPathParameter");
		
		return chromeDriverPath;
	}
	
	public String getIEDriverPath()
	{
		String ieDriverPath=properties.getProperty("ieDriverPathParameter");
		
		return ieDriverPath;
	}
	
}
