package com.bear53.ecore.events;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

import com.bear53.ecore.Core;

public class DonorLoginEvent implements Listener {

	Core plugin;

	public DonorLoginEvent(Core pl) {
		this.plugin = pl;
	}

	@EventHandler
	public void onPlayerLogin(PlayerLoginEvent e) {
		if (Bukkit.getOnlinePlayers().equals(Bukkit.getMaxPlayers())) {
			List<String> admins = plugin.getConfig().getStringList("admins");
			List<String> owners = plugin.getConfig().getStringList("owners");
			List<String> donors = plugin.getConfig().getStringList("donors");
			List<String> mods = plugin.getConfig().getStringList("moderators");
			List<String> vip = plugin.getConfig().getStringList("vip");
			List<String> vipplus = plugin.getConfig().getStringList("vip+");
			List<String> flame = plugin.getConfig().getStringList("flame");
			List<String> helpers = plugin.getConfig().getStringList("helpers");
			String s = e.getPlayer().getName();
			if (admins.contains(s) || owners.contains(s) || donors.contains(s)
					|| mods.contains(s) || vip.contains(s)
					|| vipplus.contains(s) || flame.contains(s)
					|| helpers.contains(s)) {
				for (Player p : Bukkit.getOnlinePlayers()) {
					if (!admins.contains(p) || !owners.contains(p)
							|| !donors.contains(p) || !mods.contains(p)
							|| !vip.contains(p) || !vipplus.contains(p)
							|| !flame.contains(p) || !helpers.contains(p)) {
						p.kickPlayer(ChatColor.DARK_RED
								+ "You were kicked to make room for a Staff member or Donor!");
						e.setResult(Result.ALLOWED);
						return;
					}
				}
			}
		}
	}
}
