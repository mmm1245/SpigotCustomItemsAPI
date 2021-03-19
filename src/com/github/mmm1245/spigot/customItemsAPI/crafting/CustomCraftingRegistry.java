package com.github.mmm1245.spigot.customItemsAPI.crafting;

import java.util.HashSet;
import java.util.Set;

public class CustomCraftingRegistry {
	private Set<CustomCraft> customCrafts;
	private static CustomCraftingRegistry instance;
	private CustomCraftingRegistry() {
		customCrafts = new HashSet<>();
	}
	public static void reload() {
		CustomCraftingRegistry.instance = new CustomCraftingRegistry();
	}
	public boolean register(CustomCraft craft) {
		return this.customCrafts.add(craft);
	}
	public Set<CustomCraft> get(){
		return customCrafts;
	}
	public static CustomCraftingRegistry getInstance() {
		return instance;
	}
}
