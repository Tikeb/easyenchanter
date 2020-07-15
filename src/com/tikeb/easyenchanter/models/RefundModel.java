package com.tikeb.easyenchanter.models;

import org.bukkit.inventory.ItemStack;

public class RefundModel {
	ItemStack Item;
	boolean RefundAvailable;
	int RefundLevel;
	String Error;

	// Getters
	public ItemStack getItem() {
		return Item;
	}

	public boolean getRefundAvailable() {
		return RefundAvailable;
	}

	public int getRefundLevel() {
		return RefundLevel;
	}

	public String getError() {
		return Error;
	}

	// Setters
	public void setItem(ItemStack item) {
		Item = item;
	}

	public void setRefundAvailable(boolean refundAvailable) {
		RefundAvailable = refundAvailable;
	}

	public void setRefundLevel(int refundLevel) {
		RefundLevel = refundLevel;
	}

	public void setError(String error) {
		Error = error;
	}
}