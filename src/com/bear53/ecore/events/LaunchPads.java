package com.bear53.ecore.events;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.util.Vector;

public class LaunchPads implements Listener {

	@EventHandler
	public void onPlayerMove(org.bukkit.event.player.PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if (p.getLocation().getBlock().getType().equals(Material.STONE_PLATE)) {
			if (p.getLocation().getBlock().getRelative(BlockFace.DOWN)
					.getType().equals(Material.REDSTONE_BLOCK)) {
				p.setVelocity(new Vector(
						p.getLocation().getDirection().getX() * 3.5D, 1.5D, p
								.getLocation().getDirection().getZ() * 3.5D));
			} else {
				return;
			}
		} else {
			return;
		}
	}
}
