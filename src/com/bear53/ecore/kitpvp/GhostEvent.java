package com.bear53.ecore.kitpvp;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.bear53.ecore.Core;

public class GhostEvent implements Listener {

	Core plugin;

	public GhostEvent(Core pl) {
		this.plugin = pl;
	}

	public List<String> ghostPlayers = new ArrayList<String>();
	public List<String> ghostDelay = new ArrayList<String>();

	@SuppressWarnings("unused")
	@EventHandler
	public void onGhostInteract(PlayerInteractEvent event) {
		try {
			if (((event.getAction() == Action.RIGHT_CLICK_AIR) || (event
					.getAction() == Action.RIGHT_CLICK_BLOCK))
					&& (event.getItem() != null)
					&& (event.getItem().getType() == Material.FEATHER)) {
				if (!this.ghostDelay.contains(event.getPlayer().getName())) {
					if (!event.getPlayer().getAllowFlight()) {
						final Player p = event.getPlayer();
						p.setAllowFlight(true);
						p.sendMessage(ChatColor.RED + "You can now fly for "
								+ 5 + " second" + (5 != 1 ? "s" : "") + ".");
						if (5 > 0) {
							p.getServer().getScheduler()
									.runTaskLater(plugin, new Runnable() {
										public void run() {
											if (p != null) {
												p.setAllowFlight(false);
												p.sendMessage(ChatColor.RED
														+ "Fly effects have worn off.");
											}
										}
									}, 5 * 20L);
						} else {
							p.setAllowFlight(false);
							p.sendMessage(ChatColor.RED
									+ "Fly effects have worn off.");
						}
						if (5 > 3) {
							p.getServer().getScheduler()
									.runTaskLater(this.plugin, new Runnable() {
										public void run() {
											if (p != null) {
												p.sendMessage(ChatColor.RED
														+ "You have 3 seconds of flight remaining.");
											}
										}
									}, 5 * 20L - 60L);

							p.getServer().getScheduler()
									.runTaskLater(this.plugin, new Runnable() {
										public void run() {
											if (p != null) {
												p.sendMessage(ChatColor.RED
														+ "You have 2 seconds of flight remaining.");
											}
										}
									}, 5 * 20L - 40L);

							p.getServer().getScheduler()
									.runTaskLater(this.plugin, new Runnable() {
										public void run() {
											if (p != null) {
												p.sendMessage(ChatColor.RED
														+ "You have 1 second of flight remaining.");
											}
										}
									}, 5 * 20L - 20L);
						}
						if (5 > 0) {
							final String pName = p.getName();
							this.ghostPlayers.add(pName);
							this.ghostDelay.add(pName);
							p.getServer().getScheduler()
									.runTaskLater(this.plugin, new Runnable() {
										public void run() {
											ghostDelay.remove(pName);
											ghostPlayers.remove(pName);
										}
									}, 5 * 20L);
						}
					} else {
						event.getPlayer().sendMessage(
								ChatColor.RED + "You can already fly.");
					}
				} else {
					event.getPlayer().sendMessage(
							ChatColor.RED + "There is a " + 5
									+ " second cooldown before each flight.");
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
