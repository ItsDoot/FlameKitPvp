package com.bear53.ecore.kitpvp;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SnowmanEvent implements Listener {

	@EventHandler
	public void onSnowmanDamage(EntityDamageByEntityEvent event) {
		try {
			if ((event.getEntity() instanceof Player)
					&& ((event.getDamager() instanceof Snowball))) {
				Snowball snowball = (Snowball) event.getDamager();
				if ((snowball.getShooter() instanceof Player)) {
					Player player = (Player) event.getEntity();
					player.addPotionEffect(new PotionEffect(
							PotionEffectType.BLINDNESS, 2 * 20, 1));
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@EventHandler
	public void onSnowBallRelease(PlayerInteractEvent e) {
		if (e.getItem() != null) {
			if (e.getItem().getItemMeta().getDisplayName() != null) {
				if (e.getItem().getItemMeta().getDisplayName()
						.equalsIgnoreCase(ChatColor.AQUA + "Snowball Launcher")) {
					e.getPlayer().launchProjectile(Snowball.class);
				}
			}
		}
	}
}
