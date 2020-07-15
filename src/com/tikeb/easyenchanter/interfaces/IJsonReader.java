package com.tikeb.easyenchanter.interfaces;

import com.tikeb.easyenchanter.models.ItemModel;

public interface IJsonReader {
	
	public ItemModel GetDataForItem(ItemModel itemModel);
	public boolean GetAllowNonStandardEnchants();
}