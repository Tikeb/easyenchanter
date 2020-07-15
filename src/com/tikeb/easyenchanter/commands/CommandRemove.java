package com.tikeb.easyenchanter.commands;

import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.tikeb.easyenchanter.interfaces.ICommandRemove;
import com.tikeb.easyenchanter.models.ItemModel;
import com.tikeb.easyenchanter.models.RefundModel;

public class CommandRemove implements ICommandRemove {

	public RefundModel cmdEnchantRemove(ItemModel itemModel) {

		RefundModel refundModel = new RefundModel();
		try {
			// Get the item stack and item meta.
			ItemStack itemStack = itemModel.getItem();
			ItemMeta itemMeta = itemStack.getItemMeta();

			Map<Enchantment, Integer> map = itemMeta.getEnchants();
			Enchantment enchantment = Enchantment.getByName(itemModel.getEnchantment());

			for (Entry<Enchantment, Integer> entry : map.entrySet()) {
				// 
				if (entry.getKey() == enchantment) {
					// Remove the enchantment.
					itemMeta.removeEnchant(enchantment);

					// Add the modified item meta back to the item stack and update the item model.
					itemStack.setItemMeta(itemMeta);
					itemModel.setItem(itemStack);

					// Set the refund model.
					refundModel.setItem(itemStack);
					refundModel.setRefundAvailable(true);
					refundModel.setRefundLevel(entry.getValue());

					// Once we've found the enchantment break out of the loop.
					break;
				}
			}
		} catch (Exception ex) {
			refundModel.setRefundAvailable(false);
			refundModel.setError(ex.getMessage());
		}
		return refundModel;
	}
	
	public void Blah(Player player, ItemModel itemModel, RefundModel refundModel) {
		
	}
}