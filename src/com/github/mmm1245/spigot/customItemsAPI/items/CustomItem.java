package com.github.mmm1245.spigot.customItemsAPI.items;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

public abstract class CustomItem {
	private int customMData;
	private NamespacedKey key;
	public CustomItem(NamespacedKey key) {
		this.key = key;
		this.customMData = 0;
	}
	public abstract ItemStack create(int count);
	public ItemStack create() {
		return create(1);
	}
	public boolean isSame(ItemStack is) {
		if(!is.getItemMeta().hasCustomModelData())
			return false;
		if(is.getType() != getMaterial())
			return false;
		return (is.getItemMeta().getCustomModelData() == getCustomModelData());
	}
	
	void setCustomModelData(int customModelData) {
		this.customMData = customModelData;
	}
	
	public abstract Material getMaterial();
	public int getCustomModelData() {
		return customMData;
	}
	public NamespacedKey getKey() {
		return key;
	}
}
