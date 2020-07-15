package com.tikeb.easyenchanter.interfaces;

import com.tikeb.easyenchanter.models.ConfigModel;

public interface IConfigReader {
	
	public ConfigModel getConfig();
	public int getXpRefundPercent();
	public boolean getIgnoreLevelRestriction();
	public boolean getAllowNonStandardEnchants();
	public boolean getDebug();
}