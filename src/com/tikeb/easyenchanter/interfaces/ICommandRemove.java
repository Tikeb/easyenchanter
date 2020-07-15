package com.tikeb.easyenchanter.interfaces;

import com.tikeb.easyenchanter.models.ItemModel;
import com.tikeb.easyenchanter.models.RefundModel;

public interface ICommandRemove {
	
	public RefundModel cmdEnchantRemove(ItemModel itemModel);
}