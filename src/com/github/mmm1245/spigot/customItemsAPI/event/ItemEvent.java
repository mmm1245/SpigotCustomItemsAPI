package com.github.mmm1245.spigot.customItemsAPI.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;

import com.github.mmm1245.spigot.customItemsAPI.items.CustomItemStorage;

public class ItemEvent implements Listener{
	@EventHandler
	public void onItemEat(PlayerItemConsumeEvent event) {
		CustomItemStorage.getInstance().onEat(event);
	}
	@EventHandler
	public void onItemEntityInteract(PlayerInteractEntityEvent event) {
		CustomItemStorage.getInstance().onEntityInteract(event);
	}
	@EventHandler
	public void onItemBlockInteract(PlayerInteractEvent event) {
		CustomItemStorage.getInstance().onBlockInteract(event);
	}
	@EventHandler
	public void onItemEntityHit(EntityDamageByEntityEvent event) {
		CustomItemStorage.getInstance().onEntityHit(event);
	}
}
