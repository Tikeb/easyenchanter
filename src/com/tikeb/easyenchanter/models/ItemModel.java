package com.tikeb.easyenchanter.models;

import org.bukkit.inventory.ItemStack;

public class ItemModel {
	ItemStack Item;
	String Enchantment;
	int Level;
	float Cost;
	boolean Standard;
	boolean Refund;

	String Error;

	// Getters
	public ItemStack getItem() {
		return Item;
	}

	public String getEnchantment() {
		return Enchantment;
	}

	public int getLevel() {
		return Level;
	}

	public float getCost() {
		return Cost;
	}

	public boolean getStandard() {
		return Standard;
	}

	public boolean getRefund() {
		return Refund;
	}

	public String getError() {
		return Error;
	}

	// Setters
	public void setItem(ItemStack item) {
		Item = item;
	}

	public void setEnchantment(String enchantment) {
		Enchantment = enchantment;
	}

	public void setLevel(int level) {
		Level = level;
	}

	public void setCost(float cost) {
		Cost = cost;
	}

	public void setNonStandard(boolean standard) {
		Standard = standard;
	}

	public void setRefund(boolean refund) {
		Refund = refund;
	}

	public void setError(String error) {
		Error = error;
	}

	@Override
	public String toString() {
		return "ItemModel [Item=" + Item + ", Enchantment=" + Enchantment + ", Level=" + Level + ", Cost=" + Cost + ", Standard=" + Standard
				+ ", Refund=" + Refund + ", Error=" + Error + "]";
	}
}