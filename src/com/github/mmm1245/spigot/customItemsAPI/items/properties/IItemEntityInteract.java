package com.github.mmm1245.spigot.customItemsAPI.items.properties;

import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public interface IItemEntityInteract {
	void onEntityInteract(PlayerInteractEntityEvent event);
}
