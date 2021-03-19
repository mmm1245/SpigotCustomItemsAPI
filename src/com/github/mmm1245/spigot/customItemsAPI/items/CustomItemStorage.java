package com.github.mmm1245.spigot.customItemsAPI.items;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

import com.github.mmm1245.spigot.customItemsAPI.items.properties.IItemBlockInteract;
import com.github.mmm1245.spigot.customItemsAPI.items.properties.IItemEntityHit;
import com.github.mmm1245.spigot.customItemsAPI.items.properties.IItemEntityInteract;
import com.github.mmm1245.spigot.customItemsAPI.items.properties.IItemOnEat;

public class CustomItemStorage {
	private static CustomItemStorage instance = new CustomItemStorage();
	private HashMap<NamespacedKey, CustomItem> customItemRegistry;
	private HashMap<NamespacedKey, Integer> savedCids;
	private HashMap<Material, Integer> matCount;
	private ArrayList<CustomItem> eatEventItems;
	private ArrayList<CustomItem> entityHitEventItems;
	private ArrayList<CustomItem> blockInteractEventItems;
	private ArrayList<CustomItem> entityInteractEventItems;
	private CustomItemStorage() {
		CustomItemStorage.instance = this;
		customItemRegistry = new HashMap<NamespacedKey, CustomItem>();
		savedCids = new HashMap<NamespacedKey, Integer>();
		matCount = new HashMap<>();
		eatEventItems = new ArrayList<CustomItem>();
		entityHitEventItems = new ArrayList<CustomItem>();
		blockInteractEventItems = new ArrayList<CustomItem>();
		entityInteractEventItems = new ArrayList<CustomItem>();
	}
	public static void reload() {
		CustomItemStorage.instance = new CustomItemStorage();
	}
	public void registerCustomItem(CustomItem item) {
		if(!matCount.containsKey(item.getMaterial())) {
			matCount.put(item.getMaterial(), 1);
		}
		if(savedCids.containsKey(item.getKey())) {
			int cmd = savedCids.get(item.getKey());
			item.setCustomModelData(cmd);
		} else {
			int cmd = matCount.get(item.getMaterial());
			item.setCustomModelData(cmd);
			matCount.put(item.getMaterial(), cmd+1);
			savedCids.put(item.getKey(), item.getCustomModelData());
		}
		customItemRegistry.put(item.getKey(), item);
		if(item instanceof IItemOnEat) {
			eatEventItems.add(item);
		}
		if(item instanceof IItemEntityHit) {
			entityHitEventItems.add(item);
		}
		if(item instanceof IItemBlockInteract) {
			blockInteractEventItems.add(item);
		}
		if(item instanceof IItemEntityInteract) {
			entityInteractEventItems.add(item);
		}
	}
	public ItemStack getCustomItem(NamespacedKey key) {
		CustomItem citem = customItemRegistry.get(key);
		return citem != null ? citem.create() : null;
	}
	public CustomItem getCustomItem(ItemStack is) {
		for(Map.Entry<NamespacedKey, CustomItem> entry : customItemRegistry.entrySet()) {
			if(entry.getValue().isSame(is)) {
				return entry.getValue();
			}
		}
		return null;
	}
	public Set<NamespacedKey> getAll(){
		return customItemRegistry.keySet();
	}
	
	public static CustomItemStorage getInstance() {
		return instance;
	}
	
	public void onEat(PlayerItemConsumeEvent event) {
		for(CustomItem citem : eatEventItems) {
			if(citem.isSame(event.getItem())) {
				((IItemOnEat)citem).onEat(event);
			}
		}
	}
	public void onBlockInteract(PlayerInteractEvent event) {
		for(CustomItem citem : blockInteractEventItems) {
			if(citem.isSame(event.getItem())) {
				((IItemBlockInteract)citem).onBlockInteract(event);
			}
		}
	}
	public void onEntityHit(EntityDamageByEntityEvent event) {
		if(!(event.getDamager() instanceof Player)) return;
		Player pl = (Player) event.getDamager();
		for(CustomItem citem : entityHitEventItems) {
			if(citem.isSame(pl.getInventory().getItemInMainHand())) {
				((IItemEntityHit)citem).onHit(event);
			}
		}
	}
	public void onEntityInteract(PlayerInteractEntityEvent event) {
		for(CustomItem citem : entityInteractEventItems) {
			if(citem.isSame(event.getPlayer().getInventory().getItemInMainHand())) {
				((IItemEntityInteract)citem).onEntityInteract(event);
			}
		}
	}
	
	public void save(DataOutputStream stream) throws IOException {
		stream.writeInt(matCount.size());
		for(Entry<Material, Integer> entry : matCount.entrySet()) {
			stream.writeUTF(entry.getKey().name());
			stream.writeInt(entry.getValue());
		}
		stream.writeInt(savedCids.size());
		for(Entry<NamespacedKey, Integer> entry : savedCids.entrySet()) {
			stream.writeUTF(entry.getKey().toString());
			stream.writeInt(entry.getValue());
		}
	}
	public void load(DataInputStream stream) throws IOException {
		matCount = new HashMap<>();
		savedCids = new HashMap<>();
		int matC = stream.readInt();
		for(int i = 0;i < matC;i++) {
			matCount.put(Material.getMaterial(stream.readUTF()),stream.readInt());
		}
		int scidsC = stream.readInt();
		for(int i = 0;i < scidsC;i++) {
			savedCids.put(namespacedkeyFromString(stream.readUTF()),stream.readInt());
		}
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
}
