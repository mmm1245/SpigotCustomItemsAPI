package com.github.mmm1245.spigot.customItemsAPI;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.craftbukkit.v1_16_R2.help.HelpYamlReader;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.github.mmm1245.spigot.customItemsAPI.commands.GetItemCommand;
import com.github.mmm1245.spigot.customItemsAPI.crafting.CraftingItem;
import com.github.mmm1245.spigot.customItemsAPI.crafting.CustomCraft;
import com.github.mmm1245.spigot.customItemsAPI.crafting.CustomCraftingRegistry;
import com.github.mmm1245.spigot.customItemsAPI.event.ItemCraftEvent;
import com.github.mmm1245.spigot.customItemsAPI.event.ItemEvent;
import com.github.mmm1245.spigot.customItemsAPI.items.CustomItemStorage;

public class CustomItemsMain extends JavaPlugin{
	@SuppressWarnings("deprecation")
	@Override
	public void onEnable() {
		GetItemCommand getItemCommand = new GetItemCommand();
		getCommand("getitem").setExecutor(getItemCommand);
		getCommand("getitem").setTabCompleter(getItemCommand);
		getServer().getPluginManager().registerEvents(new ItemEvent(), this);
		getServer().getPluginManager().registerEvents(new ItemCraftEvent(), this);
		CustomItemStorage.reload();
		CustomCraftingRegistry.reload();
		
		String fname = getDataFolder().getAbsolutePath()+getDataFolder().separatorChar+"customItemsDB.txt";
		File conf = new File(fname);
		try {
			CustomItemStorage.getInstance().load(new DataInputStream(new FileInputStream(conf)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
		
	}
	@Override
	public void onDisable() {
		String fname = getDataFolder().getAbsolutePath()+getDataFolder().separatorChar+"customItemsDB.txt";
		File conf = new File(fname);
		try {
				conf.getParentFile().mkdirs();
				conf.createNewFile();
		} catch (IOException e1) {
			
		}
		try {
			CustomItemStorage.getInstance().save(new DataOutputStream(new FileOutputStream(conf)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
