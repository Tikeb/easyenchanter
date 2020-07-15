package com.tikeb.easyenchanter.models;

public class ConfigModel {
	int XpRefundPercent;
	boolean IgnoreLevelRestriction;
	boolean AllowNonStandardEnchants;
	boolean Debug;

	// Getters
	public int getXpRefundPercent() {
		return XpRefundPercent;
	}

	public boolean getIgnoreLevelRestriction() {
		return IgnoreLevelRestriction;
	}

	public boolean getAllowNonStandardEnchants() {
		return AllowNonStandardEnchants;
	}

	public boolean getDebug() {
		return Debug;
	}

	// Setters
	public void setXpRefundPercent(int xpRefundPercent) {
		XpRefundPercent = xpRefundPercent;
	}

	public void setIgnoreLevelRestriction(boolean ignoreLevelRestriction) {
		IgnoreLevelRestriction = ignoreLevelRestriction;
	}

	public void setAllowNonStandardEnchants(boolean allowNonStandardEnchants) {
		AllowNonStandardEnchants = allowNonStandardEnchants;
	}

	public void setDebug(boolean debug) {
		Debug = debug;
	}
}