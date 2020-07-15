package com.tikeb.easyenchanter.helpers;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
//import java.io.FileReader;
import java.io.InputStreamReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.tikeb.easyenchanter.interfaces.IJsonReader;
import com.tikeb.easyenchanter.models.ItemModel;

public class JsonReader implements IJsonReader {

	public ItemModel GetDataForItem(ItemModel itemModel) {

		try {
			// Get all the data from the config file.
			JSONObject rawJson = GetJson();

			// See if there was an error reading the config file.
			Object error = rawJson.get("Error");

			// If an error exists set the error on the item model and
			// return the model.
			if (error != null) {
				itemModel.setError(error.toString());
				return itemModel;
			}

			// Get the name of the item that's to be enchanted while ignoring
			// the material used. I.E. If the name is GOLDEN_AXE. We just want
			// the AXE part.
			String itemName = itemModel.getItem().getType().name();
			String[] itemParts = itemName.split("_");
			// Get the item details from the JSON data.
			Object itemObj = rawJson.get(itemParts.length > 1 ? itemParts[1] : itemParts[0]);
			// I might be paranoid but I want to release system resources as
			// soon as possible, hence set the unused raw JSON variable to null.
			// GC probably does this anyway.
			rawJson = null;
			// Set the error message for missing items/enchantment's.
			String errMsg = "This item/enchantment does not exist or has been disabled.";

			// Check to make sure the item object contains valid data. Otherwise
			// throw the error above as the item/enchantment does not exist.
			if (itemObj != null && itemObj instanceof JSONObject) {

				// As the item object is not null and it's an instance of
				// JSONObject cast as a new variable.
				JSONObject itemJson = (JSONObject) itemObj;
				// Get the enchantment from the item JSON.
				Object enchantmentObj = itemJson.get(itemModel.getEnchantment());

				// Check the required enchantment exists and if it does is an
				// instance of a JSON object.
				if (enchantmentObj != null && enchantmentObj instanceof JSONObject) {

					// As the enchantment object is not null and it's an
					// instance of JSONObject cast as a new variable.
					JSONObject enchantmentJson = (JSONObject) enchantmentObj;
					// Get the cost of the enchantment based on the requested level.
					float cost = (float) enchantmentJson.get(itemModel.getLevel());
					// Check if the enchantment is standard or not.
					Object nonStandard = itemJson.get("Standard");

					// Update the item model.
					itemModel.setCost(cost);
					itemModel.setNonStandard(nonStandard != null ? (boolean) nonStandard : true);
				} else {
					throw new Exception(errMsg);
				}
			} else {
				throw new Exception(errMsg);
			}
		} catch (Exception ex) {
			itemModel.setError(ex.getMessage());
		}
		return itemModel;
	}

	public boolean GetAllowNonStandardEnchants() {

		// Get all the data from the config file.
		JSONObject rawJson = GetJson();
		// Check for an error.
		Object error = rawJson.get("Error");

		// Return the default value of false if there was an error.
		if (error != null) {
			return false;
		}
		// Return the actual result read from the config file.
		return (boolean) rawJson.get("AllowNonStandardEnchants");
	}

	@SuppressWarnings("unchecked")
	private static JSONObject GetJson() {

		// Declare the JSON parser used to read the config file.
		JSONParser parser = new JSONParser();

		try {
			
			String path = "./plugins/easyenchanter.json";
			FileInputStream fis = new FileInputStream(path);
			BufferedReader in = new BufferedReader(new InputStreamReader(fis));
			
			Object rawJson = parser.parse(in);
			
			
			// Read the JSON from the config file.
			//Object rawJson = parser.parse(new FileReader("./easyenchanter.json"));

			// Check to see if the file was read and that the object is an instance 
			// of a JSON object.
			if (rawJson != null && rawJson instanceof JSONObject) {
				return (JSONObject) rawJson;
			} else {
				throw new Exception("Failed to parse config file");
			}
		} catch (FileNotFoundException ex) {
			JSONObject jsonError = new JSONObject();
			jsonError.put("Error", ex.getMessage());
			return jsonError;
		} catch (Exception ex) {
			JSONObject jsonError = new JSONObject();
			jsonError.put("Error", ex.getMessage());
			return jsonError;
		}
	}
}