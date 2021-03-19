package com.github.mmm1245.spigot.customItemsAPI.items.properties;

import org.bukkit.event.player.PlayerItemConsumeEvent;

public interface IItemOnEat {
	void onEat(PlayerItemConsumeEvent event);
}
