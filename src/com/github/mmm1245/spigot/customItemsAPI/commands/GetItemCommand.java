package com.github.mmm1245.spigot.customItemsAPI.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.github.mmm1245.spigot.customItemsAPI.items.CustomItemStorage;


public class GetItemCommand implements CommandExecutor, TabCompleter{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Only players can run this command");
			return true;
		}
		Player pl = (Player) sender;
		if(command.getName().equalsIgnoreCase("getitem") && args.length == 1) {
			if(!pl.hasPermission("customitemapi.getitem")) {
				pl.sendMessage(ChatColor.RED + "You dont have permission");
				return true;
			}
			NamespacedKey key = namespacedkeyFromString(args[0]);
			if(key == null) {
				pl.sendMessage(ChatColor.RED + "invalid item name");
				return true;
			}
			ItemStack is = CustomItemStorage.getInstance().getCustomItem(key);
			if(is == null) {
				pl.sendMessage(ChatColor.RED + "item not found");
				return true;
			}
			pl.getInventory().addItem(is);
		} else if(command.getName().equalsIgnoreCase("getitem") && args.length == 2){
			int num = -1;
			try {
				num = Integer.parseInt(args[1]);
			} catch(NumberFormatException e) {
				pl.sendMessage(ChatColor.RED + "Invalid number");
				return true;
			}
			NamespacedKey key = namespacedkeyFromString(args[0]);
			if(key == null) {
				pl.sendMessage(ChatColor.RED + "invalid item name");
				return true;
			}
			ItemStack is = CustomItemStorage.getInstance().getCustomItem(key);
			if(is == null) {
				pl.sendMessage(ChatColor.RED + "item not found");
				return true;
			}
			if(num < 1 || num > is.getMaxStackSize()) {
				pl.sendMessage(ChatColor.RED + "item count must be within bounds of 0 and max stack size");
				return true;
			}			
			is.setAmount(num);
			pl.getInventory().addItem(is);
			return true;
		}
		return true;
	}
	
	@SuppressWarnings("deprecation")
	private static NamespacedKey namespacedkeyFromString(String s) {
		if ((s == null) || s.isEmpty()) {
			return null;
		}
		String[] split = s.split(":", 2);
		if (split.length == 1) {
			return NamespacedKey.minecraft(split[0]);
		} else {
			return new NamespacedKey(split[0], split[1]);
		}
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		ArrayList<String> possible = new ArrayList<>();
		ArrayList<String> allNamespaceString = new ArrayList<>();
		Set<NamespacedKey> citems = CustomItemStorage.getInstance().getAll();
		for(NamespacedKey key : citems) {
			allNamespaceString.add(key.toString());
		}
		if(args.length == 2) {
			NamespacedKey key = namespacedkeyFromString(args[0]);
			if(key == null) {
				return possible;
			}
			ItemStack is = CustomItemStorage.getInstance().getCustomItem(key);
			if(is == null) {
				return possible;
			}
			possible.add("1");
			possible.add(Integer.toString(is.getMaxStackSize()));
			return possible;
		}
		if(args.length != 1)
			return possible;
		if(args[0].isEmpty()) {
			for(String full : allNamespaceString)	possible.add(full);
		} else {
			for(String full : allNamespaceString) {
				if(full.contains(args[0]))
					possible.add(full);
			}
		}
		return possible;
	}

}
