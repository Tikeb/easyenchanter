package com.tikeb.easyenchanter.commands;

import org.bukkit.entity.Player;

import com.tikeb.easyenchanter.helpers.ChatHelper;
import com.tikeb.easyenchanter.helpers.ConfigReader;
import com.tikeb.easyenchanter.interfaces.*;
import com.tikeb.easyenchanter.models.ConfigModel;

public class CommandBottle implements ICommandBottle {
	
	private IChatHelper _chat = new ChatHelper();
	private IConfigReader _config = new ConfigReader();

	public void cmdBottleExperience(Player player) {

		ConfigModel configModel = _config.getConfig();
		boolean allowNonStandardEnchants = configModel.getAllowNonStandardEnchants();

		// Must hold bottle to fill up

		if (allowNonStandardEnchants) {
			// do stuff
		} else {
			_chat.SendMessage(player, "Non standard enchanting has been disabled on this server.");
		}
	}
}