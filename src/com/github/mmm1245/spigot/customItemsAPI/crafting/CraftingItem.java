package com.github.mmm1245.spigot.customItemsAPI.crafting;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.github.mmm1245.spigot.customItemsAPI.items.CustomItem;

public class CraftingItem {
	private Material mat;
	private CustomItem citem;
	public CraftingItem(Material material) {
		this.mat = material;
		this.citem = null;
	}
	
	public CraftingItem(CustomItem citem) {
		this.mat = null;
		this.citem = citem;
	}
	
	public boolean test(ItemStack is) {
		if(is == null) {
			return mat != null && mat == Material.AIR;
		}
		if(mat != null) {
			if(is.getItemMeta().hasCustomModelData())
				return false;
			return is.getType() == mat;
		}
		if(citem != null) {
			return citem.isSame(is);
		}
		return false;
	}
	public Material getMaterial() {
		if(mat != null)
			return mat;
		if(citem != null) 
			return citem.getMaterial();
		
		return null;
	}
}
