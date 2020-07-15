package com.tikeb.easyenchanter.helpers;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.tikeb.easyenchanter.interfaces.IChatHelper;

public class ChatHelper implements IChatHelper {

	public void SendMessage(Player player, String message, String[] args) {

		// Check if any arguments have been passed to the method.
		if (args != null && args.length > 0) {

			// For each argument passed replace the marker '{#}' with the argument.
			// Example text: '{0} is a {1}' with arguments ['This', 'test'] would result 
			// as a message: 'This is a test'.
			for (int i = 0; i < args.length; i++) {
				message = message.replace("{" + i + "}", args[i]);
			}
		}

		// Send the message to the player.
		player.sendMessage(ChatColor.YELLOW + "EE: " + message);
	}

	public void SendMessage(Player player, String message) {
		SendMessage(player, message, new String[] {});
	}

	public void SendMessage(Player player, String message, String param) {
		SendMessage(player, message, new String[] { param });
	}
}