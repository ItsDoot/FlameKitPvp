package com.bear53.ecore.kitpvp;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ScorpionEvent implements Listener {

	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		try {
			if (event.getDamager() != null
					&& ((event.getEntity() instanceof Player))
					&& ((event.getDamager() instanceof Player))) {
				Player damaged = (Player) event.getEntity();
				Player damager = (Player) event.getDamager();
				if ((damager.getInventory().getItemInHand() != null)
						&& (KitPvp.scorp.contains(damager.getName())
								&& (KitPvp.activeKit
										.contains(damaged.getName())) && damager
								.getInventory().getItemInHand().getType() == Material.STONE_SWORD)
						&& (Math.random() > 0.4D) && (Math.random() < 0.6D)) {
					damaged.addPotionEffect(new PotionEffect(
							PotionEffectType.POISON, Integer.valueOf(5) * 20, 0));
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
