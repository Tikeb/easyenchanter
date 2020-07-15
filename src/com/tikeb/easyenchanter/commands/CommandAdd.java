package com.tikeb.easyenchanter.commands;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.tikeb.easyenchanter.helpers.ConfigReader;
import com.tikeb.easyenchanter.interfaces.ICommandAdd;
import com.tikeb.easyenchanter.interfaces.IConfigReader;
import com.tikeb.easyenchanter.models.ConfigModel;
import com.tikeb.easyenchanter.models.ItemModel;

public class CommandAdd implements ICommandAdd {

	private IConfigReader _config = new ConfigReader();
	
	public ItemModel cmdEnchantAdd(ItemModel itemModel) {

		try {
			// Get the item stack and item meta.
			ItemStack itemStack = itemModel.getItem();
			ItemMeta itemMeta = itemStack.getItemMeta();

			ConfigModel configModel = _config.getConfig();
			
			// Get the enchantment and add to the item meta.
			Enchantment enchantment = Enchantment.getByName(itemModel.getEnchantment());
			itemMeta.addEnchant(enchantment, itemModel.getLevel(), configModel.getIgnoreLevelRestriction());

			// Add the modified item meta back to the item stack and update the item model.
			itemStack.setItemMeta(itemMeta);
			itemModel.setItem(itemStack);

		} catch (Exception ex) {
			itemModel.setError(ex.getMessage());
		}

		return itemModel;
	}
}