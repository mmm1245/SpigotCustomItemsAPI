package com.github.mmm1245.spigot.customItemsAPI.crafting;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class CustomCraft {
	private CraftingItem craftingItems[];
	private ItemStack result;
	private NamespacedKey key;
	public CustomCraft(NamespacedKey key, ItemStack result) {
		this.result = result;
		this.craftingItems = new CraftingItem[9];
		for(int i = 0;i < 9;i++) {
			craftingItems[i] = null;
		}
		this.key = key;
	}
	public void setFirst(CraftingItem left, CraftingItem center, CraftingItem right) {
		if(left == null) {
			left = new CraftingItem(Material.AIR);
		}
		if(center == null) {
			center = new CraftingItem(Material.AIR);
		}
		if(right == null) {
			right = new CraftingItem(Material.AIR);
		}
		this.craftingItems[0] = left;
		this.craftingItems[1] = center;
		this.craftingItems[2] = right;
	}
	public void setSecond(CraftingItem left, CraftingItem center, CraftingItem right) {
		if(left == null) {
			left = new CraftingItem(Material.AIR);
		}
		if(center == null) {
			center = new CraftingItem(Material.AIR);
		}
		if(right == null) {
			right = new CraftingItem(Material.AIR);
		}
		this.craftingItems[3] = left;
		this.craftingItems[4] = center;
		this.craftingItems[5] = right;
	}
	public void setThird(CraftingItem left, CraftingItem center, CraftingItem right) {
		if(left == null) {
			left = new CraftingItem(Material.AIR);
		}
		if(center == null) {
			center = new CraftingItem(Material.AIR);
		}
		if(right == null) {
			right = new CraftingItem(Material.AIR);
		}
		this.craftingItems[6] = left;
		this.craftingItems[7] = center;
		this.craftingItems[8] = right;
	}
	
	public CraftingItem[] getCraftingItems() {
		return craftingItems;
	}
	public ItemStack getResult() {
		return result;
	}
	public boolean isValid() {
		for(int i = 0;i < 9;i++) {
			if(craftingItems[i] == null) {
				return false;
			}
		}
		return true;
	}
	public void register() {
		if(isValid()) {
			ShapedRecipe shapedRecipe = new ShapedRecipe(this.key, getResult());
			shapedRecipe.shape("ABC","DEF","GHI");
			for(int i = 0;i < 9;i++) {
				shapedRecipe.setIngredient((char) ('A'+i), craftingItems[i].getMaterial());
			}
			Bukkit.addRecipe(shapedRecipe);
			CustomCraftingRegistry.getInstance().register(this);
		}
	}
	public NamespacedKey getKey() {
		return key;
	}
}
