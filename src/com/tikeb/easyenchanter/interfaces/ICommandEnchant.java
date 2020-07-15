package com.tikeb.easyenchanter.interfaces;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public interface ICommandEnchant extends CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args);
}