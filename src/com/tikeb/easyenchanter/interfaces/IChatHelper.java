package com.tikeb.easyenchanter.interfaces;

import org.bukkit.entity.Player;

public interface IChatHelper {
	
	public void SendMessage(Player player, String message, String[] args);
	public void SendMessage(Player player, String message);
	public void SendMessage(Player player, String message, String param);
}