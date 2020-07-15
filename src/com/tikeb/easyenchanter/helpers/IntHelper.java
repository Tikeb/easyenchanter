package com.tikeb.easyenchanter.helpers;

import com.tikeb.easyenchanter.models.IntModel;

public class IntHelper {
	
	public static IntModel TryParse(String input) {
		
		IntModel i = new IntModel();
		try {
			i.SetNumber(Integer.parseInt(input));
			i.SetParsed(true);
		} catch (Exception ex) {
			i.SetParsed(false);
		}
		return i;
	}
}