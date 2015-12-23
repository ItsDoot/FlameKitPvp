package com.bear53.ecore.kitpvp;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.util.Vector;

public class FishEvent implements Listener {

	@EventHandler
	public void onFisherFish(PlayerFishEvent event) {
		try {
			if ((event.getCaught() != null)
					&& ((event.getCaught() instanceof Player))) {
				Player player = event.getPlayer();
				Player fishedPlayer = (Player) event.getCaught();
				Vector fishedPlayerVelocity = player.getLocation().toVector()
						.subtract(fishedPlayer.getLocation().toVector())
						.multiply(0.2D);
				fishedPlayer.setVelocity(fishedPlayerVelocity);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
