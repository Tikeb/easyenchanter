package com.tikeb.easyenchanter.commands;

import org.bukkit.entity.Player;

import com.tikeb.easyenchanter.helpers.ChatHelper;
import com.tikeb.easyenchanter.interfaces.IChatHelper;
import com.tikeb.easyenchanter.interfaces.ICommandHelp;

public class CommandHelp implements ICommandHelp {

	private IChatHelper _chat = new ChatHelper();

	public void cmdHelp(Player player) {
		_chat.SendMessage(player, "Available commands:");
		_chat.SendMessage(player, "/ee stats | Displays your levels/experience");
		_chat.SendMessage(player, "/ee bottle | Gives you bottle o' enchanting in exchange for levels (must be holding an empty bottle)");
		_chat.SendMessage(player, "/ee <enchantment> | Displays the available levels/costs for the specified enchantment (must be holding a valid item - i.e. golden axe)");
		_chat.SendMessage(player, "/ee <enchantment> <level> | Enchants the currently held item with the requested enchantment/level if available");
		_chat.SendMessage(player, "/ee <enchantment> refund | Refunds the partial experience cost and removes the enchantment from the currently held item");
	}

	public void cmdStats(Player player) {
		_chat.SendMessage(player, "Levels: {0}", Integer.toString(player.getLevel()));
		_chat.SendMessage(player, "Experience: {0}", Integer.toString(player.getTotalExperience()));
	}
}