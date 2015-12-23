package com.bear53.ecore.kitpvp;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

import com.bear53.ecore.Core;

public class KangarooEvent implements Listener {

	Core plugin;

	public KangarooEvent(Core pl) {
		this.plugin = pl;
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		Player p = event.getPlayer();
		if ((p.getItemInHand().getType() == Material.FIREWORK)
				&& (plugin.getConfig().getStringList(
						"players." + p.getUniqueId().toString() + ".kits")
						.contains("Kangaroo"))) {
			event.setCancelled(true);
			Block b = p.getLocation().getBlock();
			if ((b.getType() != Material.AIR)
					|| (b.getRelative(BlockFace.DOWN).getType() != Material.AIR)) {
				if (!p.isSneaking()) {
					p.setFallDistance(-5.0F);
					Vector vector = p.getEyeLocation().getDirection();
					vector.multiply(0.6F);
					vector.setY(1.0F);
					p.setVelocity(vector);
				} else {
					p.setFallDistance(-5.0F);
					Vector v = p.getEyeLocation().getDirection();
					v.multiply(1.2F);
					v.setY(0.8D);
					p.setVelocity(v);
				}
			}
		}
	}
}
