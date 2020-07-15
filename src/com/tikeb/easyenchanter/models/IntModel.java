package com.tikeb.easyenchanter.models;

public class IntModel {
	int Num;
	boolean Parsed;

	// Getters
	public int GetNumber() {
		return Num;
	}

	public boolean GetParsed() {
		return Parsed;
	}
	
	// Setters
	public void SetNumber(int num) {
		Num = num;
	}

	public void SetParsed(boolean parsed) {
		Parsed = parsed;
	}
}