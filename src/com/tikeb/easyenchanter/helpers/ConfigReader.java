package com.tikeb.easyenchanter.helpers;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.tikeb.easyenchanter.interfaces.IConfigReader;
import com.tikeb.easyenchanter.models.ConfigModel;

public class ConfigReader implements IConfigReader {

	private int xpRefundPercent = 70;
	private boolean ignoreLevelRestriction = false;
	private boolean allowNonStandardEnchants = true;
	private boolean debug = false;

	public ConfigModel getConfig() {

		Properties properties = readConfig();
		ConfigModel configModel = new ConfigModel();
		
		if (properties != null){
			configModel.setXpRefundPercent((int) properties.get("XpRefundPercent"));
			configModel.setIgnoreLevelRestriction((boolean) properties.get("IgnoreLevelRestriction"));
			configModel.setAllowNonStandardEnchants((boolean) properties.get("AllowNonStandardEnchants"));
			configModel.setDebug((boolean) properties.get("Debug"));
		}else {
			configModel.setXpRefundPercent(xpRefundPercent);
			configModel.setIgnoreLevelRestriction(ignoreLevelRestriction);
			configModel.setAllowNonStandardEnchants(allowNonStandardEnchants);
			configModel.setDebug(debug);
		}
		
		return configModel;
	}

	public int getXpRefundPercent() {
		Properties properties = readConfig();
		return properties != null ? (int) properties.get("XpRefundPercent") : xpRefundPercent;
	}

	public boolean getIgnoreLevelRestriction() {
		Properties properties = readConfig();
		return properties != null ? (boolean) properties.get("IgnoreLevelRestriction") : ignoreLevelRestriction;
	}

	public boolean getAllowNonStandardEnchants() {
		Properties properties = readConfig();
		return properties != null ? (boolean) properties.get("AllowNonStandardEnchants") : allowNonStandardEnchants;
	}

	public boolean getDebug() {
		Properties properties = readConfig();
		return properties != null ? (boolean) properties.get("Debug") : debug;
	}

	private Properties readConfig() {
		try {
			InputStream input = getClass().getClassLoader().getResourceAsStream("easyenchanter.properties");

			if (input == null)
				throw new IOException();

			Properties properties = new Properties();
			properties.load(input);
			return properties;
		} catch (IOException ex) {
			return null;
		}
	}
}