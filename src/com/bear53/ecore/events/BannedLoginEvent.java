package com.bear53.ecore.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import com.bear53.ecore.Core;

public class BannedLoginEvent implements Listener {
	
	Core plugin;

	public BannedLoginEvent(Core pl) {
		this.plugin = pl;
	}

	@EventHandler
	public void onPlayerLogin(org.bukkit.event.player.PlayerLoginEvent e) {
		Player p = e.getPlayer();
		if (this.plugin.getConfig().getBoolean(
				"players." + p.getUniqueId() + ".isBanned")) {
			e.setResult(PlayerLoginEvent.Result.KICK_BANNED);
			e.setKickMessage(ChatColor.RED
					+ "You have been banned from the Flame Network!\n\n"
					+ ChatColor.GRAY
					+ this.plugin.getConfig().getString(
							new StringBuilder("players.")
									.append(p.getUniqueId())
									.append(".banReason").toString())
					+ "\n\n"
					+ ChatColor.RED
					+ "Banned by: "
					+ ChatColor.GRAY
					+ this.plugin.getConfig().getString(
							new StringBuilder("players.")
									.append(p.getUniqueId())
									.append(".bannedBy").toString()));
		}
	}
}
