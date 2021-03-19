package com.github.mmm1245.spigot.customItemsAPI.items.properties;

import org.bukkit.event.entity.EntityDamageByEntityEvent;

public interface IItemEntityHit {
	void onHit(EntityDamageByEntityEvent event);
}
