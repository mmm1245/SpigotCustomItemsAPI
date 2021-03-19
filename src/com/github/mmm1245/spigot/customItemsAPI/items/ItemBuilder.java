package com.github.mmm1245.spigot.customItemsAPI.items;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemBuilder {
	public static ItemStack create(Material material, String displayName, String... lore) {
		ItemStack is = new ItemStack(material);
		ItemMeta im = is.getItemMeta();
		if(displayName != null && (!displayName.isEmpty())) {
			im.setDisplayName(ChatColor.RESET + displayName);
		}
		if(lore.length > 0) {
			ArrayList<String> lores = new ArrayList<>();
			for(int i = 0;i < lore.length;i++) {
				lores.add(lore[i]);
			}
			im.setLore(lores);
		}
		is.setItemMeta(im);
		return is;
	}
	public static ItemStack create(Material material, int amount, String displayName, String... lore) {
		ItemStack is = new ItemStack(material, amount);
		ItemMeta im = is.getItemMeta();
		if(displayName != null && (!displayName.isEmpty())) {
			im.setDisplayName(ChatColor.RESET + displayName);
		}
		if(lore.length > 0) {
			ArrayList<String> lores = new ArrayList<>();
			for(int i = 0;i < lore.length;i++) {
				lores.add(lore[i]);
			}
			im.setLore(lores);
		}
		is.setItemMeta(im);
		return is;
	}
	public static ItemStack create(Material material, String displayName, int customModelData, String... lore) {
		ItemStack is = new ItemStack(material);
		ItemMeta im = is.getItemMeta();
		if(displayName != null && (!displayName.isEmpty())) {
			im.setDisplayName(ChatColor.RESET + displayName);
		}
		if(lore.length > 0) {
			ArrayList<String> lores = new ArrayList<>();
			for(int i = 0;i < lore.length;i++) {
				lores.add(lore[i]);
			}
			im.setLore(lores);
		}
		im.setCustomModelData(customModelData);
		is.setItemMeta(im);
		return is;
	}
	public static ItemStack create(Material material, int amount, String displayName, int customModelData, String... lore) {
		ItemStack is = new ItemStack(material, amount);
		ItemMeta im = is.getItemMeta();
		if(displayName != null && (!displayName.isEmpty())) {
			im.setDisplayName(ChatColor.RESET + displayName);
		}
		if(lore.length > 0) {
			ArrayList<String> lores = new ArrayList<>();
			for(int i = 0;i < lore.length;i++) {
				lores.add(lore[i]);
			}
			im.setLore(lores);
		}
		im.setCustomModelData(customModelData);
		is.setItemMeta(im);
		return is;
	}
}
