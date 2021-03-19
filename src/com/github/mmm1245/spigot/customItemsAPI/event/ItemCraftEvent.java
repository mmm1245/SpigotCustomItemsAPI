package com.github.mmm1245.spigot.customItemsAPI.event;

import java.util.Set;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import com.github.mmm1245.spigot.customItemsAPI.crafting.CraftingItem;
import com.github.mmm1245.spigot.customItemsAPI.crafting.CustomCraft;
import com.github.mmm1245.spigot.customItemsAPI.crafting.CustomCraftingRegistry;

public class ItemCraftEvent implements Listener{
	@EventHandler
	public void onPrepareItemCraft(PrepareItemCraftEvent e) {
		if(e.getRecipe() instanceof ShapedRecipe) {
			Set<CustomCraft> customCraftings = CustomCraftingRegistry.getInstance().get();
			for(CustomCraft customCraft : customCraftings) {
				if(customCraft.getKey().equals(((ShapedRecipe)e.getRecipe()).getKey())) {
					if(e.getInventory().getMatrix().length != 9) {
						e.getInventory().setResult(new ItemStack(Material.AIR));
						return;
					}
					CraftingItem[] craftingItems = customCraft.getCraftingItems();
					for(int i = 0;i < e.getInventory().getMatrix().length;i++) {
						if(!craftingItems[i].test(e.getInventory().getMatrix()[i])) {
							e.getInventory().setResult(new ItemStack(Material.AIR));
							return;
						}
					}
					return;
				}
			} 
			if(e.getInventory().getMatrix().length != 9) 
				return;
			for(int i = 0;i < e.getInventory().getMatrix().length;i++) {
				if(e.getInventory().getMatrix()[i] != null && e.getInventory().getMatrix()[i].hasItemMeta() && e.getInventory().getMatrix()[i].getItemMeta().hasCustomModelData()) {
					e.getInventory().setResult(new ItemStack(Material.AIR));
					break;
				}
			}
		}
	}
	/*@EventHandler
	void onInventoryInteract(InventoryClickEvent event) {
		if(!event.isShiftClick())	return;
		if(event.getClickedInventory() instanceof CraftingInventory) {
			CraftingInventory inv = (CraftingInventory) event.getInventory();
			if(inv.getRecipe() == null) {
				event.setCancelled(true);
			}
		}
	}*/
}
