package com.tikeb.easyenchanter;

import org.bukkit.plugin.java.JavaPlugin;

import com.tikeb.easyenchanter.commands.CommandEnchant;
import com.tikeb.easyenchanter.interfaces.ICommandEnchant;

public class EasyEnchanter extends JavaPlugin {
	
	private ICommandEnchant _command = new CommandEnchant(); 
	
	@Override
	public void onEnable() {
		this.getCommand("ee").setExecutor(_command);
	}
	
	@Override
	public void onDisable() {
	}
}