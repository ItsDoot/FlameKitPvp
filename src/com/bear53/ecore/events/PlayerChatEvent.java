package com.bear53.ecore.events;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.bear53.ecore.Core;
import com.bear53.ecore.teams.Team;
import com.bear53.ecore.teams.TeamManager;

public class PlayerChatEvent implements Listener {

	Core plugin;

	public PlayerChatEvent(Core pl) {
		this.plugin = pl;
	}

	public static ArrayList<Player> chatters = new ArrayList<Player>();

	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent e) {
		if (chatters.contains(e.getPlayer())) {
			e.setCancelled(true);

			Team team = TeamManager.getInstance().getTeam(e.getPlayer());

			if (team == null) {
				chatters.remove(e.getPlayer());
			}

			for (String m : team.getMembers()) {
				Player member = Bukkit.getServer()
						.getPlayer(UUID.fromString(m));

				if (member != null) {
					member.sendMessage(ChatColor.GRAY + "[" + ChatColor.AQUA
							+ "Teams" + ChatColor.GRAY + "] "
							+ ChatColor.YELLOW + e.getPlayer().getName()
							+ ChatColor.GRAY + ": " + ChatColor.WHITE
							+ e.getMessage());
				}
			}
		}
		List<String> admins = plugin.getConfig().getStringList("admins");
		List<String> owners = plugin.getConfig().getStringList("owners");
		List<String> donors = plugin.getConfig().getStringList("donors");
		List<String> mods = plugin.getConfig().getStringList("moderators");
		List<String> vip = plugin.getConfig().getStringList("vip");
		List<String> vipplus = plugin.getConfig().getStringList("vip+");
		List<String> flame = plugin.getConfig().getStringList("flame");
		List<String> helpers = plugin.getConfig().getStringList("helpers");

		Player p = e.getPlayer();
		if (plugin.getConfig().getBoolean(
				"players." + p.getUniqueId() + ".isMuted")) {
			p.sendMessage(Core.prefix + ChatColor.GRAY
					+ "You cannot talk while you're muted.");
			e.setCancelled(true);
		} else {
			if (p.isOp()) {
				e.setMessage(ChatColor.translateAlternateColorCodes('&',
						e.getMessage()));
			}
			Integer level = plugin.getConfig().getInt(
					"players." + p.getUniqueId().toString() + ".level");
			int x;
			if (plugin.getConfig().contains("")) {
				x = plugin.getConfig().getInt(
						"players." + p.getUniqueId().toString() + ".prestige");
			} else {
				x = 0;
			}
			if (admins.contains(p.getName())) {
				Team team = TeamManager.getInstance().getTeam(e.getPlayer());

				if (team != null) {
					if (x == 0) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.GRAY
								+ "] " + ChatColor.GRAY + "[" + ChatColor.RED
								+ "Admin" + ChatColor.GRAY + "] <"
								+ ChatColor.YELLOW + team.getName()
								+ ChatColor.GRAY + ">" + ChatColor.RED + " "
								+ p.getName() + ChatColor.DARK_GRAY + " >>"
								+ ChatColor.WHITE + " " + e.getMessage());
					} else if (x == 1) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "*" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.RED + "Admin"
								+ ChatColor.GRAY + "] <" + ChatColor.YELLOW
								+ team.getName() + ChatColor.GRAY + ">"
								+ ChatColor.RED + " " + p.getName()
								+ ChatColor.DARK_GRAY + " >>" + ChatColor.WHITE
								+ " " + e.getMessage());
					} else if (x == 2) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "**" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.RED + "Admin"
								+ ChatColor.GRAY + "] <" + ChatColor.YELLOW
								+ team.getName() + ChatColor.GRAY + ">"
								+ ChatColor.RED + " " + p.getName()
								+ ChatColor.DARK_GRAY + " >>" + ChatColor.WHITE
								+ " " + e.getMessage());
					} else if (x == 3) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❸" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.RED + "Admin"
								+ ChatColor.GRAY + "] <" + ChatColor.YELLOW
								+ team.getName() + ChatColor.GRAY + ">"
								+ ChatColor.RED + " " + p.getName()
								+ ChatColor.DARK_GRAY + " >>" + ChatColor.WHITE
								+ " " + e.getMessage());
					} else if (x == 4) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❹" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.RED + "Admin"
								+ ChatColor.GRAY + "] <" + ChatColor.YELLOW
								+ team.getName() + ChatColor.GRAY + ">"
								+ ChatColor.RED + " " + p.getName()
								+ ChatColor.DARK_GRAY + " >>" + ChatColor.WHITE
								+ " " + e.getMessage());
					} else if (x == 5) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❺" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.RED + "Admin"
								+ ChatColor.GRAY + "] <" + ChatColor.YELLOW
								+ team.getName() + ChatColor.GRAY + ">"
								+ ChatColor.RED + " " + p.getName()
								+ ChatColor.DARK_GRAY + " >>" + ChatColor.WHITE
								+ " " + e.getMessage());
					} else if (x == 6) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❻" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.RED + "Admin"
								+ ChatColor.GRAY + "] <" + ChatColor.YELLOW
								+ team.getName() + ChatColor.GRAY + ">"
								+ ChatColor.RED + " " + p.getName()
								+ ChatColor.DARK_GRAY + " >>" + ChatColor.WHITE
								+ " " + e.getMessage());
					} else if (x == 7) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❼" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.RED + "Admin"
								+ ChatColor.GRAY + "] <" + ChatColor.YELLOW
								+ team.getName() + ChatColor.GRAY + ">"
								+ ChatColor.RED + " " + p.getName()
								+ ChatColor.DARK_GRAY + " >>" + ChatColor.WHITE
								+ " " + e.getMessage());
					} else if (x == 8) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❽" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.RED + "Admin"
								+ ChatColor.GRAY + "] <" + ChatColor.YELLOW
								+ team.getName() + ChatColor.GRAY + ">"
								+ ChatColor.RED + " " + p.getName()
								+ ChatColor.DARK_GRAY + " >>" + ChatColor.WHITE
								+ " " + e.getMessage());
					} else if (x == 9) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❾" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.RED + "Admin"
								+ ChatColor.GRAY + "] <" + ChatColor.YELLOW
								+ team.getName() + ChatColor.GRAY + ">"
								+ ChatColor.RED + " " + p.getName()
								+ ChatColor.DARK_GRAY + " >>" + ChatColor.WHITE
								+ " " + e.getMessage());
					} else if (x >= 10) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "✪" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.RED + "Admin"
								+ ChatColor.GRAY + "] <" + ChatColor.YELLOW
								+ team.getName() + ChatColor.GRAY + ">"
								+ ChatColor.RED + " " + p.getName()
								+ ChatColor.DARK_GRAY + " >>" + ChatColor.WHITE
								+ " " + e.getMessage());
					}
				} else {
					if (x == 0) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.GRAY
								+ "] " + ChatColor.GRAY + "[" + ChatColor.RED
								+ "Admin" + ChatColor.GRAY + "]"
								+ ChatColor.RED + " " + p.getName()
								+ ChatColor.DARK_GRAY + " >>" + ChatColor.WHITE
								+ " " + e.getMessage());
					} else if (x == 1) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "*" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.RED + "Admin"
								+ ChatColor.GRAY + "]" + ChatColor.RED + " "
								+ p.getName() + ChatColor.DARK_GRAY + " >>"
								+ ChatColor.WHITE + " " + e.getMessage());
					} else if (x == 2) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "**" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.RED + "Admin"
								+ ChatColor.GRAY + "]" + ChatColor.RED + " "
								+ p.getName() + ChatColor.DARK_GRAY + " >>"
								+ ChatColor.WHITE + " " + e.getMessage());
					} else if (x == 3) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❸" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.RED + "Admin"
								+ ChatColor.GRAY + "]" + ChatColor.RED + " "
								+ p.getName() + ChatColor.DARK_GRAY + " >>"
								+ ChatColor.WHITE + " " + e.getMessage());
					} else if (x == 4) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❹" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.RED + "Admin"
								+ ChatColor.GRAY + "]" + ChatColor.RED + " "
								+ p.getName() + ChatColor.DARK_GRAY + " >>"
								+ ChatColor.WHITE + " " + e.getMessage());
					} else if (x == 5) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❺" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.RED + "Admin"
								+ ChatColor.GRAY + "]" + ChatColor.RED + " "
								+ p.getName() + ChatColor.DARK_GRAY + " >>"
								+ ChatColor.WHITE + " " + e.getMessage());
					} else if (x == 6) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❻" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.RED + "Admin"
								+ ChatColor.GRAY + "]" + ChatColor.RED + " "
								+ p.getName() + ChatColor.DARK_GRAY + " >>"
								+ ChatColor.WHITE + " " + e.getMessage());
					} else if (x == 7) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❼" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.RED + "Admin"
								+ ChatColor.GRAY + "]" + ChatColor.RED + " "
								+ p.getName() + ChatColor.DARK_GRAY + " >>"
								+ ChatColor.WHITE + " " + e.getMessage());
					} else if (x == 8) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❽" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.RED + "Admin"
								+ ChatColor.GRAY + "]" + ChatColor.RED + " "
								+ p.getName() + ChatColor.DARK_GRAY + " >>"
								+ ChatColor.WHITE + " " + e.getMessage());
					} else if (x == 9) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❾" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.RED + "Admin"
								+ ChatColor.GRAY + "]" + ChatColor.RED + " "
								+ p.getName() + ChatColor.DARK_GRAY + " >>"
								+ ChatColor.WHITE + " " + e.getMessage());
					} else if (x >= 10) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "✪" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.RED + "Admin"
								+ ChatColor.GRAY + "]" + ChatColor.RED + " "
								+ p.getName() + ChatColor.DARK_GRAY + " >>"
								+ ChatColor.WHITE + " " + e.getMessage());
					}
				}
			} else if (owners.contains(p.getName())) {
				Team team = TeamManager.getInstance().getTeam(e.getPlayer());

				if (team != null) {
					if (x == 0) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.GRAY
								+ "] " + ChatColor.GRAY + "["
								+ ChatColor.DARK_RED + "Owner" + ChatColor.GRAY
								+ "] <" + ChatColor.YELLOW + team.getName()
								+ ChatColor.GRAY + ">" + ChatColor.DARK_RED
								+ " " + p.getName() + ChatColor.DARK_GRAY
								+ " >>" + ChatColor.WHITE + " "
								+ e.getMessage());
					} else if (x == 1) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "*" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_RED + "Owner"
								+ ChatColor.GRAY + "] <" + ChatColor.YELLOW
								+ team.getName() + ChatColor.GRAY + ">"
								+ ChatColor.DARK_RED + " " + p.getName()
								+ ChatColor.DARK_GRAY + " >>" + ChatColor.WHITE
								+ " " + e.getMessage());
					} else if (x == 2) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "**" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_RED + "Owner"
								+ ChatColor.GRAY + "] <" + ChatColor.YELLOW
								+ team.getName() + ChatColor.GRAY + ">"
								+ ChatColor.DARK_RED + " " + p.getName()
								+ ChatColor.DARK_GRAY + " >>" + ChatColor.WHITE
								+ " " + e.getMessage());
					} else if (x == 3) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❸" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_RED + "Owner"
								+ ChatColor.GRAY + "] <" + ChatColor.YELLOW
								+ team.getName() + ChatColor.GRAY + ">"
								+ ChatColor.DARK_RED + " " + p.getName()
								+ ChatColor.DARK_GRAY + " >>" + ChatColor.WHITE
								+ " " + e.getMessage());
					} else if (x == 4) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❹" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_RED + "Owner"
								+ ChatColor.GRAY + "] <" + ChatColor.YELLOW
								+ team.getName() + ChatColor.GRAY + ">"
								+ ChatColor.DARK_RED + " " + p.getName()
								+ ChatColor.DARK_GRAY + " >>" + ChatColor.WHITE
								+ " " + e.getMessage());
					} else if (x == 5) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❺" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_RED + "Owner"
								+ ChatColor.GRAY + "] <" + ChatColor.YELLOW
								+ team.getName() + ChatColor.GRAY + ">"
								+ ChatColor.DARK_RED + " " + p.getName()
								+ ChatColor.DARK_GRAY + " >>" + ChatColor.WHITE
								+ " " + e.getMessage());
					} else if (x == 6) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❻" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_RED + "Owner"
								+ ChatColor.GRAY + "] <" + ChatColor.YELLOW
								+ team.getName() + ChatColor.GRAY + ">"
								+ ChatColor.DARK_RED + " " + p.getName()
								+ ChatColor.DARK_GRAY + " >>" + ChatColor.WHITE
								+ " " + e.getMessage());
					} else if (x == 7) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❼" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_RED + "Owner"
								+ ChatColor.GRAY + "] <" + ChatColor.YELLOW
								+ team.getName() + ChatColor.GRAY + ">"
								+ ChatColor.DARK_RED + " " + p.getName()
								+ ChatColor.DARK_GRAY + " >>" + ChatColor.WHITE
								+ " " + e.getMessage());
					} else if (x == 8) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❽" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_RED + "Owner"
								+ ChatColor.GRAY + "] <" + ChatColor.YELLOW
								+ team.getName() + ChatColor.GRAY + ">"
								+ ChatColor.DARK_RED + " " + p.getName()
								+ ChatColor.DARK_GRAY + " >>" + ChatColor.WHITE
								+ " " + e.getMessage());
					} else if (x == 9) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❾" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_RED + "Owner"
								+ ChatColor.GRAY + "] <" + ChatColor.YELLOW
								+ team.getName() + ChatColor.GRAY + ">"
								+ ChatColor.DARK_RED + " " + p.getName()
								+ ChatColor.DARK_GRAY + " >>" + ChatColor.WHITE
								+ " " + e.getMessage());
					} else if (x >= 10) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "✪" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_RED + "Owner"
								+ ChatColor.GRAY + "] <" + ChatColor.YELLOW
								+ team.getName() + ChatColor.GRAY + ">"
								+ ChatColor.DARK_RED + " " + p.getName()
								+ ChatColor.DARK_GRAY + " >>" + ChatColor.WHITE
								+ " " + e.getMessage());
					}
				} else {
					if (x == 0) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.GRAY
								+ "] " + ChatColor.GRAY + "["
								+ ChatColor.DARK_RED + "Owner" + ChatColor.GRAY
								+ "]" + ChatColor.DARK_RED + " " + p.getName()
								+ ChatColor.DARK_GRAY + " >>" + ChatColor.WHITE
								+ " " + e.getMessage());
					} else if (x == 1) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "*" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_RED + "Owner"
								+ ChatColor.GRAY + "]" + ChatColor.DARK_RED
								+ " " + p.getName() + ChatColor.DARK_GRAY
								+ " >>" + ChatColor.WHITE + " "
								+ e.getMessage());
					} else if (x == 2) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "**" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_RED + "Owner"
								+ ChatColor.GRAY + "]" + ChatColor.DARK_RED
								+ " " + p.getName() + ChatColor.DARK_GRAY
								+ " >>" + ChatColor.WHITE + " "
								+ e.getMessage());
					} else if (x == 3) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❸" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_RED + "Owner"
								+ ChatColor.GRAY + "]" + ChatColor.DARK_RED
								+ " " + p.getName() + ChatColor.DARK_GRAY
								+ " >>" + ChatColor.WHITE + " "
								+ e.getMessage());
					} else if (x == 4) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❹" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_RED + "Owner"
								+ ChatColor.GRAY + "]" + ChatColor.DARK_RED
								+ " " + p.getName() + ChatColor.DARK_GRAY
								+ " >>" + ChatColor.WHITE + " "
								+ e.getMessage());
					} else if (x == 5) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❺" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_RED + "Owner"
								+ ChatColor.GRAY + "]" + ChatColor.DARK_RED
								+ " " + p.getName() + ChatColor.DARK_GRAY
								+ " >>" + ChatColor.WHITE + " "
								+ e.getMessage());
					} else if (x == 6) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❻" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_RED + "Owner"
								+ ChatColor.GRAY + "]" + ChatColor.DARK_RED
								+ " " + p.getName() + ChatColor.DARK_GRAY
								+ " >>" + ChatColor.WHITE + " "
								+ e.getMessage());
					} else if (x == 7) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❼" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_RED + "Owner"
								+ ChatColor.GRAY + "]" + ChatColor.DARK_RED
								+ " " + p.getName() + ChatColor.DARK_GRAY
								+ " >>" + ChatColor.WHITE + " "
								+ e.getMessage());
					} else if (x == 8) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❽" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_RED + "Owner"
								+ ChatColor.GRAY + "]" + ChatColor.DARK_RED
								+ " " + p.getName() + ChatColor.DARK_GRAY
								+ " >>" + ChatColor.WHITE + " "
								+ e.getMessage());
					} else if (x == 9) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❾" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_RED + "Owner"
								+ ChatColor.GRAY + "]" + ChatColor.DARK_RED
								+ " " + p.getName() + ChatColor.DARK_GRAY
								+ " >>" + ChatColor.WHITE + " "
								+ e.getMessage());
					} else if (x >= 10) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "✪" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_RED + "Owner"
								+ ChatColor.GRAY + "]" + ChatColor.DARK_RED
								+ " " + p.getName() + ChatColor.DARK_GRAY
								+ " >>" + ChatColor.WHITE + " "
								+ e.getMessage());
					}
				}
			} else if (helpers.contains(p.getName())) {
				Team team = TeamManager.getInstance().getTeam(e.getPlayer());

				if (team != null) {
					if (x == 0) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.GRAY
								+ "] " + ChatColor.GRAY + "["
								+ ChatColor.DARK_AQUA + "Helper"
								+ ChatColor.GRAY + "] <" + ChatColor.YELLOW
								+ team.getName() + ChatColor.GRAY + ">"
								+ ChatColor.DARK_AQUA + " " + p.getName()
								+ ChatColor.DARK_GRAY + " >>" + ChatColor.WHITE
								+ " " + e.getMessage());
					} else if (x == 1) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "*" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_AQUA + "Helper"
								+ ChatColor.GRAY + "] <" + ChatColor.YELLOW
								+ team.getName() + ChatColor.GRAY + ">"
								+ ChatColor.DARK_AQUA + " " + p.getName()
								+ ChatColor.DARK_GRAY + " >>" + ChatColor.WHITE
								+ " " + e.getMessage());
					} else if (x == 2) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "*" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_AQUA + "Helper"
								+ ChatColor.GRAY + "] <" + ChatColor.YELLOW
								+ team.getName() + ChatColor.GRAY + ">"
								+ ChatColor.DARK_AQUA + " " + p.getName()
								+ ChatColor.DARK_GRAY + " >>" + ChatColor.WHITE
								+ " " + e.getMessage());
					} else if (x == 3) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❸" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_AQUA + "Helper"
								+ ChatColor.GRAY + "] <" + ChatColor.YELLOW
								+ team.getName() + ChatColor.GRAY + ">"
								+ ChatColor.DARK_AQUA + " " + p.getName()
								+ ChatColor.DARK_GRAY + " >>" + ChatColor.WHITE
								+ " " + e.getMessage());
					} else if (x == 4) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❹" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_AQUA + "Helper"
								+ ChatColor.GRAY + "] <" + ChatColor.YELLOW
								+ team.getName() + ChatColor.GRAY + ">"
								+ ChatColor.DARK_AQUA + " " + p.getName()
								+ ChatColor.DARK_GRAY + " >>" + ChatColor.WHITE
								+ " " + e.getMessage());
					} else if (x == 5) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❺" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_AQUA + "Helper"
								+ ChatColor.GRAY + "] <" + ChatColor.YELLOW
								+ team.getName() + ChatColor.GRAY + ">"
								+ ChatColor.DARK_AQUA + " " + p.getName()
								+ ChatColor.DARK_GRAY + " >>" + ChatColor.WHITE
								+ " " + e.getMessage());
					} else if (x == 6) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❻" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_AQUA + "Helper"
								+ ChatColor.GRAY + "] <" + ChatColor.YELLOW
								+ team.getName() + ChatColor.GRAY + ">"
								+ ChatColor.DARK_AQUA + " " + p.getName()
								+ ChatColor.DARK_GRAY + " >>" + ChatColor.WHITE
								+ " " + e.getMessage());
					} else if (x == 7) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❼" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_AQUA + "Helper"
								+ ChatColor.GRAY + "] <" + ChatColor.YELLOW
								+ team.getName() + ChatColor.GRAY + ">"
								+ ChatColor.DARK_AQUA + " " + p.getName()
								+ ChatColor.DARK_GRAY + " >>" + ChatColor.WHITE
								+ " " + e.getMessage());
					} else if (x == 8) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❽" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_AQUA + "Helper"
								+ ChatColor.GRAY + "] <" + ChatColor.YELLOW
								+ team.getName() + ChatColor.GRAY + ">"
								+ ChatColor.DARK_AQUA + " " + p.getName()
								+ ChatColor.DARK_GRAY + " >>" + ChatColor.WHITE
								+ " " + e.getMessage());
					} else if (x == 9) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❾" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_AQUA + "Helper"
								+ ChatColor.GRAY + "] <" + ChatColor.YELLOW
								+ team.getName() + ChatColor.GRAY + ">"
								+ ChatColor.DARK_AQUA + " " + p.getName()
								+ ChatColor.DARK_GRAY + " >>" + ChatColor.WHITE
								+ " " + e.getMessage());
					} else if (x >= 10) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "✪" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_AQUA + "Helper"
								+ ChatColor.GRAY + "] <" + ChatColor.YELLOW
								+ team.getName() + ChatColor.GRAY + ">"
								+ ChatColor.DARK_AQUA + " " + p.getName()
								+ ChatColor.DARK_GRAY + " >>" + ChatColor.WHITE
								+ " " + e.getMessage());
					}
				} else {
					if (x == 0) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.GRAY
								+ "] " + ChatColor.GRAY + "["
								+ ChatColor.DARK_AQUA + "Helper"
								+ ChatColor.GRAY + "]" + ChatColor.DARK_AQUA
								+ " " + p.getName() + ChatColor.DARK_GRAY
								+ " >>" + ChatColor.WHITE + " "
								+ e.getMessage());
					} else if (x == 1) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "*" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_AQUA + "Helper"
								+ ChatColor.GRAY + "]" + ChatColor.DARK_AQUA
								+ " " + p.getName() + ChatColor.DARK_GRAY
								+ " >>" + ChatColor.WHITE + " "
								+ e.getMessage());
					} else if (x == 2) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "**" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_AQUA + "Helper"
								+ ChatColor.GRAY + "]" + ChatColor.DARK_AQUA
								+ " " + p.getName() + ChatColor.DARK_GRAY
								+ " >>" + ChatColor.WHITE + " "
								+ e.getMessage());
					} else if (x == 3) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❸" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_AQUA + "Helper"
								+ ChatColor.GRAY + "]" + ChatColor.DARK_AQUA
								+ " " + p.getName() + ChatColor.DARK_GRAY
								+ " >>" + ChatColor.WHITE + " "
								+ e.getMessage());
					} else if (x == 4) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❹" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_AQUA + "Helper"
								+ ChatColor.GRAY + "]" + ChatColor.DARK_AQUA
								+ " " + p.getName() + ChatColor.DARK_GRAY
								+ " >>" + ChatColor.WHITE + " "
								+ e.getMessage());
					} else if (x == 5) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❺" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_AQUA + "Helper"
								+ ChatColor.GRAY + "]" + ChatColor.DARK_AQUA
								+ " " + p.getName() + ChatColor.DARK_GRAY
								+ " >>" + ChatColor.WHITE + " "
								+ e.getMessage());
					} else if (x == 6) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❻" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_AQUA + "Helper"
								+ ChatColor.GRAY + "]" + ChatColor.DARK_AQUA
								+ " " + p.getName() + ChatColor.DARK_GRAY
								+ " >>" + ChatColor.WHITE + " "
								+ e.getMessage());
					} else if (x == 7) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❼" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_AQUA + "Helper"
								+ ChatColor.GRAY + "]" + ChatColor.DARK_AQUA
								+ " " + p.getName() + ChatColor.DARK_GRAY
								+ " >>" + ChatColor.WHITE + " "
								+ e.getMessage());
					} else if (x == 8) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❽" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_AQUA + "Helper"
								+ ChatColor.GRAY + "]" + ChatColor.DARK_AQUA
								+ " " + p.getName() + ChatColor.DARK_GRAY
								+ " >>" + ChatColor.WHITE + " "
								+ e.getMessage());
					} else if (x == 9) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❾" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_AQUA + "Helper"
								+ ChatColor.GRAY + "]" + ChatColor.DARK_AQUA
								+ " " + p.getName() + ChatColor.DARK_GRAY
								+ " >>" + ChatColor.WHITE + " "
								+ e.getMessage());
					} else if (x >= 10) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "✪" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_AQUA + "Helper"
								+ ChatColor.GRAY + "]" + ChatColor.DARK_AQUA
								+ " " + p.getName() + ChatColor.DARK_GRAY
								+ " >>" + ChatColor.WHITE + " "
								+ e.getMessage());
					}
				}
			} else if (donors.contains(p.getName())) {
				Team team = TeamManager.getInstance().getTeam(e.getPlayer());

				if (team != null) {
					if (x == 0) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.GRAY
								+ "] " + ChatColor.GRAY + "["
								+ ChatColor.DARK_PURPLE + "Donor"
								+ ChatColor.GRAY + "] <" + ChatColor.YELLOW
								+ team.getName() + ChatColor.GRAY + ">"
								+ ChatColor.DARK_PURPLE + " " + p.getName()
								+ ChatColor.DARK_GRAY + " >>" + ChatColor.GRAY
								+ " " + e.getMessage());
					} else if (x == 1) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "*" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_PURPLE + "Donor"
								+ ChatColor.GRAY + "] <" + ChatColor.YELLOW
								+ team.getName() + ChatColor.GRAY + ">"
								+ ChatColor.DARK_PURPLE + " " + p.getName()
								+ ChatColor.DARK_GRAY + " >>" + ChatColor.GRAY
								+ " " + e.getMessage());
					} else if (x == 2) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "**" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_PURPLE + "Donor"
								+ ChatColor.GRAY + "] <" + ChatColor.YELLOW
								+ team.getName() + ChatColor.GRAY + ">"
								+ ChatColor.DARK_PURPLE + " " + p.getName()
								+ ChatColor.DARK_GRAY + " >>" + ChatColor.GRAY
								+ " " + e.getMessage());
					} else if (x == 3) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❸" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_PURPLE + "Donor"
								+ ChatColor.GRAY + "] <" + ChatColor.YELLOW
								+ team.getName() + ChatColor.GRAY + ">"
								+ ChatColor.DARK_PURPLE + " " + p.getName()
								+ ChatColor.DARK_GRAY + " >>" + ChatColor.GRAY
								+ " " + e.getMessage());
					} else if (x == 4) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❹" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_PURPLE + "Donor"
								+ ChatColor.GRAY + "] <" + ChatColor.YELLOW
								+ team.getName() + ChatColor.GRAY + ">"
								+ ChatColor.DARK_PURPLE + " " + p.getName()
								+ ChatColor.DARK_GRAY + " >>" + ChatColor.GRAY
								+ " " + e.getMessage());
					} else if (x == 5) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❺" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_PURPLE + "Donor"
								+ ChatColor.GRAY + "] <" + ChatColor.YELLOW
								+ team.getName() + ChatColor.GRAY + ">"
								+ ChatColor.DARK_PURPLE + " " + p.getName()
								+ ChatColor.DARK_GRAY + " >>" + ChatColor.GRAY
								+ " " + e.getMessage());
					} else if (x == 6) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❻" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_PURPLE + "Donor"
								+ ChatColor.GRAY + "] <" + ChatColor.YELLOW
								+ team.getName() + ChatColor.GRAY + ">"
								+ ChatColor.DARK_PURPLE + " " + p.getName()
								+ ChatColor.DARK_GRAY + " >>" + ChatColor.GRAY
								+ " " + e.getMessage());
					} else if (x == 7) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❼" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_PURPLE + "Donor"
								+ ChatColor.GRAY + "] <" + ChatColor.YELLOW
								+ team.getName() + ChatColor.GRAY + ">"
								+ ChatColor.DARK_PURPLE + " " + p.getName()
								+ ChatColor.DARK_GRAY + " >>" + ChatColor.GRAY
								+ " " + e.getMessage());
					} else if (x == 8) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❽" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_PURPLE + "Donor"
								+ ChatColor.GRAY + "] <" + ChatColor.YELLOW
								+ team.getName() + ChatColor.GRAY + ">"
								+ ChatColor.DARK_PURPLE + " " + p.getName()
								+ ChatColor.DARK_GRAY + " >>" + ChatColor.GRAY
								+ " " + e.getMessage());
					} else if (x == 9) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❾" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_PURPLE + "Donor"
								+ ChatColor.GRAY + "] <" + ChatColor.YELLOW
								+ team.getName() + ChatColor.GRAY + ">"
								+ ChatColor.DARK_PURPLE + " " + p.getName()
								+ ChatColor.DARK_GRAY + " >>" + ChatColor.GRAY
								+ " " + e.getMessage());
					} else if (x >= 10) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "✪" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_PURPLE + "Donor"
								+ ChatColor.GRAY + "] <" + ChatColor.YELLOW
								+ team.getName() + ChatColor.GRAY + ">"
								+ ChatColor.DARK_PURPLE + " " + p.getName()
								+ ChatColor.DARK_GRAY + " >>" + ChatColor.GRAY
								+ " " + e.getMessage());
					}
				} else {
					if (x == 0) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.GRAY
								+ "] " + ChatColor.GRAY + "["
								+ ChatColor.DARK_PURPLE + "Donor"
								+ ChatColor.GRAY + "]" + ChatColor.DARK_PURPLE
								+ " " + p.getName() + ChatColor.DARK_GRAY
								+ " >>" + ChatColor.GRAY + " " + e.getMessage());
					} else if (x == 1) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "*" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_PURPLE + "Donor"
								+ ChatColor.GRAY + "]" + ChatColor.DARK_PURPLE
								+ " " + p.getName() + ChatColor.DARK_GRAY
								+ " >>" + ChatColor.GRAY + " " + e.getMessage());
					} else if (x == 2) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "**" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_PURPLE + "Donor"
								+ ChatColor.GRAY + "]" + ChatColor.DARK_PURPLE
								+ " " + p.getName() + ChatColor.DARK_GRAY
								+ " >>" + ChatColor.GRAY + " " + e.getMessage());
					} else if (x == 3) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❸" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_PURPLE + "Donor"
								+ ChatColor.GRAY + "]" + ChatColor.DARK_PURPLE
								+ " " + p.getName() + ChatColor.DARK_GRAY
								+ " >>" + ChatColor.GRAY + " " + e.getMessage());
					} else if (x == 4) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❹" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_PURPLE + "Donor"
								+ ChatColor.GRAY + "]" + ChatColor.DARK_PURPLE
								+ " " + p.getName() + ChatColor.DARK_GRAY
								+ " >>" + ChatColor.GRAY + " " + e.getMessage());
					} else if (x == 5) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❺" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_PURPLE + "Donor"
								+ ChatColor.GRAY + "]" + ChatColor.DARK_PURPLE
								+ " " + p.getName() + ChatColor.DARK_GRAY
								+ " >>" + ChatColor.GRAY + " " + e.getMessage());
					} else if (x == 6) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❻" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_PURPLE + "Donor"
								+ ChatColor.GRAY + "]" + ChatColor.DARK_PURPLE
								+ " " + p.getName() + ChatColor.DARK_GRAY
								+ " >>" + ChatColor.GRAY + " " + e.getMessage());
					} else if (x == 7) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❼" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_PURPLE + "Donor"
								+ ChatColor.GRAY + "]" + ChatColor.DARK_PURPLE
								+ " " + p.getName() + ChatColor.DARK_GRAY
								+ " >>" + ChatColor.GRAY + " " + e.getMessage());
					} else if (x == 8) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❽" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_PURPLE + "Donor"
								+ ChatColor.GRAY + "]" + ChatColor.DARK_PURPLE
								+ " " + p.getName() + ChatColor.DARK_GRAY
								+ " >>" + ChatColor.GRAY + " " + e.getMessage());
					} else if (x == 9) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❾" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_PURPLE + "Donor"
								+ ChatColor.GRAY + "]" + ChatColor.DARK_PURPLE
								+ " " + p.getName() + ChatColor.DARK_GRAY
								+ " >>" + ChatColor.GRAY + " " + e.getMessage());
					} else if (x >= 10) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "✪" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_PURPLE + "Donor"
								+ ChatColor.GRAY + "]" + ChatColor.DARK_PURPLE
								+ " " + p.getName() + ChatColor.DARK_GRAY
								+ " >>" + ChatColor.GRAY + " " + e.getMessage());
					}
				}
			} else if (mods.contains(p.getName())) {
				Team team = TeamManager.getInstance().getTeam(e.getPlayer());

				if (team != null) {
					if (x == 0) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.GRAY
								+ "] " + ChatColor.GOLD + "["
								+ ChatColor.DARK_PURPLE + "Moderator"
								+ ChatColor.GOLD + "] " + ChatColor.GRAY + "<"
								+ ChatColor.YELLOW + team.getName()
								+ ChatColor.GRAY + ">" + ChatColor.GOLD + " "
								+ p.getName() + ChatColor.DARK_GRAY + " >>"
								+ ChatColor.WHITE + " " + e.getMessage());
					} else if (x == 1) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "*" + ChatColor.GRAY + "] " + ChatColor.GOLD
								+ "[" + ChatColor.DARK_PURPLE + "Moderator"
								+ ChatColor.GOLD + "] " + ChatColor.GRAY + "<"
								+ ChatColor.YELLOW + team.getName()
								+ ChatColor.GRAY + ">" + ChatColor.GOLD + " "
								+ p.getName() + ChatColor.DARK_GRAY + " >>"
								+ ChatColor.WHITE + " " + e.getMessage());
					} else if (x == 2) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "**" + ChatColor.GRAY + "] " + ChatColor.GOLD
								+ "[" + ChatColor.DARK_PURPLE + "Moderator"
								+ ChatColor.GOLD + "] " + ChatColor.GRAY + "<"
								+ ChatColor.YELLOW + team.getName()
								+ ChatColor.GRAY + ">" + ChatColor.GOLD + " "
								+ p.getName() + ChatColor.DARK_GRAY + " >>"
								+ ChatColor.WHITE + " " + e.getMessage());
					} else if (x == 3) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❸" + ChatColor.GRAY + "] " + ChatColor.GOLD
								+ "[" + ChatColor.DARK_PURPLE + "Moderator"
								+ ChatColor.GOLD + "] " + ChatColor.GRAY + "<"
								+ ChatColor.YELLOW + team.getName()
								+ ChatColor.GRAY + ">" + ChatColor.GOLD + " "
								+ p.getName() + ChatColor.DARK_GRAY + " >>"
								+ ChatColor.WHITE + " " + e.getMessage());
					} else if (x == 4) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❹" + ChatColor.GRAY + "] " + ChatColor.GOLD
								+ "[" + ChatColor.DARK_PURPLE + "Moderator"
								+ ChatColor.GOLD + "] " + ChatColor.GRAY + "<"
								+ ChatColor.YELLOW + team.getName()
								+ ChatColor.GRAY + ">" + ChatColor.GOLD + " "
								+ p.getName() + ChatColor.DARK_GRAY + " >>"
								+ ChatColor.WHITE + " " + e.getMessage());
					} else if (x == 5) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❺" + ChatColor.GRAY + "] " + ChatColor.GOLD
								+ "[" + ChatColor.DARK_PURPLE + "Moderator"
								+ ChatColor.GOLD + "] " + ChatColor.GRAY + "<"
								+ ChatColor.YELLOW + team.getName()
								+ ChatColor.GRAY + ">" + ChatColor.GOLD + " "
								+ p.getName() + ChatColor.DARK_GRAY + " >>"
								+ ChatColor.WHITE + " " + e.getMessage());
					} else if (x == 6) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❻" + ChatColor.GRAY + "] " + ChatColor.GOLD
								+ "[" + ChatColor.DARK_PURPLE + "Moderator"
								+ ChatColor.GOLD + "] " + ChatColor.GRAY + "<"
								+ ChatColor.YELLOW + team.getName()
								+ ChatColor.GRAY + ">" + ChatColor.GOLD + " "
								+ p.getName() + ChatColor.DARK_GRAY + " >>"
								+ ChatColor.WHITE + " " + e.getMessage());
					} else if (x == 7) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❼" + ChatColor.GRAY + "] " + ChatColor.GOLD
								+ "[" + ChatColor.DARK_PURPLE + "Moderator"
								+ ChatColor.GOLD + "] " + ChatColor.GRAY + "<"
								+ ChatColor.YELLOW + team.getName()
								+ ChatColor.GRAY + ">" + ChatColor.GOLD + " "
								+ p.getName() + ChatColor.DARK_GRAY + " >>"
								+ ChatColor.WHITE + " " + e.getMessage());
					} else if (x == 8) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❽" + ChatColor.GRAY + "] " + ChatColor.GOLD
								+ "[" + ChatColor.DARK_PURPLE + "Moderator"
								+ ChatColor.GOLD + "] " + ChatColor.GRAY + "<"
								+ ChatColor.YELLOW + team.getName()
								+ ChatColor.GRAY + ">" + ChatColor.GOLD + " "
								+ p.getName() + ChatColor.DARK_GRAY + " >>"
								+ ChatColor.WHITE + " " + e.getMessage());
					} else if (x == 9) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❾" + ChatColor.GRAY + "] " + ChatColor.GOLD
								+ "[" + ChatColor.DARK_PURPLE + "Moderator"
								+ ChatColor.GOLD + "] " + ChatColor.GRAY + "<"
								+ ChatColor.YELLOW + team.getName()
								+ ChatColor.GRAY + ">" + ChatColor.GOLD + " "
								+ p.getName() + ChatColor.DARK_GRAY + " >>"
								+ ChatColor.WHITE + " " + e.getMessage());
					} else if (x >= 10) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "✪" + ChatColor.GRAY + "] " + ChatColor.GOLD
								+ "[" + ChatColor.DARK_PURPLE + "Moderator"
								+ ChatColor.GOLD + "] " + ChatColor.GRAY + "<"
								+ ChatColor.YELLOW + team.getName()
								+ ChatColor.GRAY + ">" + ChatColor.GOLD + " "
								+ p.getName() + ChatColor.DARK_GRAY + " >>"
								+ ChatColor.WHITE + " " + e.getMessage());
					}
				} else {
					if (x == 0) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.GRAY
								+ "] " + ChatColor.GOLD + "["
								+ ChatColor.DARK_PURPLE + "Moderator"
								+ ChatColor.GOLD + "]" + ChatColor.GOLD + " "
								+ p.getName() + ChatColor.DARK_GRAY + " >>"
								+ ChatColor.WHITE + " " + e.getMessage());
					} else if (x == 1) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "*" + ChatColor.GRAY + "] " + ChatColor.GOLD
								+ "[" + ChatColor.DARK_PURPLE + "Moderator"
								+ ChatColor.GOLD + "]" + ChatColor.GOLD + " "
								+ p.getName() + ChatColor.DARK_GRAY + " >>"
								+ ChatColor.WHITE + " " + e.getMessage());
					} else if (x == 2) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "**" + ChatColor.GRAY + "] " + ChatColor.GOLD
								+ "[" + ChatColor.DARK_PURPLE + "Moderator"
								+ ChatColor.GOLD + "]" + ChatColor.GOLD + " "
								+ p.getName() + ChatColor.DARK_GRAY + " >>"
								+ ChatColor.WHITE + " " + e.getMessage());
					} else if (x == 3) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❸" + ChatColor.GRAY + "] " + ChatColor.GOLD
								+ "[" + ChatColor.DARK_PURPLE + "Moderator"
								+ ChatColor.GOLD + "]" + ChatColor.GOLD + " "
								+ p.getName() + ChatColor.DARK_GRAY + " >>"
								+ ChatColor.WHITE + " " + e.getMessage());
					} else if (x == 4) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❹" + ChatColor.GRAY + "] " + ChatColor.GOLD
								+ "[" + ChatColor.DARK_PURPLE + "Moderator"
								+ ChatColor.GOLD + "]" + ChatColor.GOLD + " "
								+ p.getName() + ChatColor.DARK_GRAY + " >>"
								+ ChatColor.WHITE + " " + e.getMessage());
					} else if (x == 5) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❺" + ChatColor.GRAY + "] " + ChatColor.GOLD
								+ "[" + ChatColor.DARK_PURPLE + "Moderator"
								+ ChatColor.GOLD + "]" + ChatColor.GOLD + " "
								+ p.getName() + ChatColor.DARK_GRAY + " >>"
								+ ChatColor.WHITE + " " + e.getMessage());
					} else if (x == 6) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❻" + ChatColor.GRAY + "] " + ChatColor.GOLD
								+ "[" + ChatColor.DARK_PURPLE + "Moderator"
								+ ChatColor.GOLD + "]" + ChatColor.GOLD + " "
								+ p.getName() + ChatColor.DARK_GRAY + " >>"
								+ ChatColor.WHITE + " " + e.getMessage());
					} else if (x == 7) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❼" + ChatColor.GRAY + "] " + ChatColor.GOLD
								+ "[" + ChatColor.DARK_PURPLE + "Moderator"
								+ ChatColor.GOLD + "]" + ChatColor.GOLD + " "
								+ p.getName() + ChatColor.DARK_GRAY + " >>"
								+ ChatColor.WHITE + " " + e.getMessage());
					} else if (x == 8) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❽" + ChatColor.GRAY + "] " + ChatColor.GOLD
								+ "[" + ChatColor.DARK_PURPLE + "Moderator"
								+ ChatColor.GOLD + "]" + ChatColor.GOLD + " "
								+ p.getName() + ChatColor.DARK_GRAY + " >>"
								+ ChatColor.WHITE + " " + e.getMessage());
					} else if (x == 9) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❾" + ChatColor.GRAY + "] " + ChatColor.GOLD
								+ "[" + ChatColor.DARK_PURPLE + "Moderator"
								+ ChatColor.GOLD + "]" + ChatColor.GOLD + " "
								+ p.getName() + ChatColor.DARK_GRAY + " >>"
								+ ChatColor.WHITE + " " + e.getMessage());
					} else if (x >= 10) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "✪" + ChatColor.GRAY + "] " + ChatColor.GOLD
								+ "[" + ChatColor.DARK_PURPLE + "Moderator"
								+ ChatColor.GOLD + "]" + ChatColor.GOLD + " "
								+ p.getName() + ChatColor.DARK_GRAY + " >>"
								+ ChatColor.WHITE + " " + e.getMessage());
					}
				}
			} else if (vip.contains(p.getName())) {
				Team team = TeamManager.getInstance().getTeam(e.getPlayer());

				if (team != null) {
					if (x == 0) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.GRAY
								+ "] " + ChatColor.GRAY + "["
								+ ChatColor.DARK_PURPLE + "VIP"
								+ ChatColor.GRAY + "] <" + ChatColor.YELLOW
								+ team.getName() + ChatColor.GRAY + ">" + " "
								+ ChatColor.DARK_PURPLE + p.getName()
								+ ChatColor.DARK_GRAY + " >> "
								+ ChatColor.WHITE + e.getMessage());
					} else if (x == 1) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "*" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_PURPLE + "VIP"
								+ ChatColor.GRAY + "] <" + ChatColor.YELLOW
								+ team.getName() + ChatColor.GRAY + ">" + " "
								+ ChatColor.DARK_PURPLE + p.getName()
								+ ChatColor.DARK_GRAY + " >> "
								+ ChatColor.WHITE + e.getMessage());
					} else if (x == 2) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "**" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_PURPLE + "VIP"
								+ ChatColor.GRAY + "] <" + ChatColor.YELLOW
								+ team.getName() + ChatColor.GRAY + ">" + " "
								+ ChatColor.DARK_PURPLE + p.getName()
								+ ChatColor.DARK_GRAY + " >> "
								+ ChatColor.WHITE + e.getMessage());
					} else if (x == 3) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❸" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_PURPLE + "VIP"
								+ ChatColor.GRAY + "] <" + ChatColor.YELLOW
								+ team.getName() + ChatColor.GRAY + ">" + " "
								+ ChatColor.DARK_PURPLE + p.getName()
								+ ChatColor.DARK_GRAY + " >> "
								+ ChatColor.WHITE + e.getMessage());
					} else if (x == 4) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❹" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_PURPLE + "VIP"
								+ ChatColor.GRAY + "] <" + ChatColor.YELLOW
								+ team.getName() + ChatColor.GRAY + ">" + " "
								+ ChatColor.DARK_PURPLE + p.getName()
								+ ChatColor.DARK_GRAY + " >> "
								+ ChatColor.WHITE + e.getMessage());
					} else if (x == 5) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❺" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_PURPLE + "VIP"
								+ ChatColor.GRAY + "] <" + ChatColor.YELLOW
								+ team.getName() + ChatColor.GRAY + ">" + " "
								+ ChatColor.DARK_PURPLE + p.getName()
								+ ChatColor.DARK_GRAY + " >> "
								+ ChatColor.WHITE + e.getMessage());
					} else if (x == 6) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❻" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_PURPLE + "VIP"
								+ ChatColor.GRAY + "] <" + ChatColor.YELLOW
								+ team.getName() + ChatColor.GRAY + ">" + " "
								+ ChatColor.DARK_PURPLE + p.getName()
								+ ChatColor.DARK_GRAY + " >> "
								+ ChatColor.WHITE + e.getMessage());
					} else if (x == 7) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❼" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_PURPLE + "VIP"
								+ ChatColor.GRAY + "] <" + ChatColor.YELLOW
								+ team.getName() + ChatColor.GRAY + ">" + " "
								+ ChatColor.DARK_PURPLE + p.getName()
								+ ChatColor.DARK_GRAY + " >> "
								+ ChatColor.WHITE + e.getMessage());
					} else if (x == 8) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❽" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_PURPLE + "VIP"
								+ ChatColor.GRAY + "] <" + ChatColor.YELLOW
								+ team.getName() + ChatColor.GRAY + ">" + " "
								+ ChatColor.DARK_PURPLE + p.getName()
								+ ChatColor.DARK_GRAY + " >> "
								+ ChatColor.WHITE + e.getMessage());
					} else if (x == 9) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❾" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_PURPLE + "VIP"
								+ ChatColor.GRAY + "] <" + ChatColor.YELLOW
								+ team.getName() + ChatColor.GRAY + ">" + " "
								+ ChatColor.DARK_PURPLE + p.getName()
								+ ChatColor.DARK_GRAY + " >> "
								+ ChatColor.WHITE + e.getMessage());
					} else if (x >= 10) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "✪" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_PURPLE + "VIP"
								+ ChatColor.GRAY + "] <" + ChatColor.YELLOW
								+ team.getName() + ChatColor.GRAY + ">" + " "
								+ ChatColor.DARK_PURPLE + p.getName()
								+ ChatColor.DARK_GRAY + " >> "
								+ ChatColor.WHITE + e.getMessage());
					}
				} else {
					if (x == 0) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.GRAY
								+ "] " + ChatColor.GRAY + "["
								+ ChatColor.DARK_PURPLE + "VIP"
								+ ChatColor.GRAY + "] " + ChatColor.DARK_PURPLE
								+ p.getName() + ChatColor.DARK_GRAY + " >> "
								+ ChatColor.WHITE + e.getMessage());
					} else if (x == 1) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "*" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_PURPLE + "VIP"
								+ ChatColor.GRAY + "] " + ChatColor.DARK_PURPLE
								+ p.getName() + ChatColor.DARK_GRAY + " >> "
								+ ChatColor.WHITE + e.getMessage());
					} else if (x == 2) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "**" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_PURPLE + "VIP"
								+ ChatColor.GRAY + "] " + ChatColor.DARK_PURPLE
								+ p.getName() + ChatColor.DARK_GRAY + " >> "
								+ ChatColor.WHITE + e.getMessage());
					} else if (x == 3) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❸" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_PURPLE + "VIP"
								+ ChatColor.GRAY + "] " + ChatColor.DARK_PURPLE
								+ p.getName() + ChatColor.DARK_GRAY + " >> "
								+ ChatColor.WHITE + e.getMessage());
					} else if (x == 4) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❹" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_PURPLE + "VIP"
								+ ChatColor.GRAY + "] " + ChatColor.DARK_PURPLE
								+ p.getName() + ChatColor.DARK_GRAY + " >> "
								+ ChatColor.WHITE + e.getMessage());
					} else if (x == 5) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❺" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_PURPLE + "VIP"
								+ ChatColor.GRAY + "] " + ChatColor.DARK_PURPLE
								+ p.getName() + ChatColor.DARK_GRAY + " >> "
								+ ChatColor.WHITE + e.getMessage());
					} else if (x == 6) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❻" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_PURPLE + "VIP"
								+ ChatColor.GRAY + "] " + ChatColor.DARK_PURPLE
								+ p.getName() + ChatColor.DARK_GRAY + " >> "
								+ ChatColor.WHITE + e.getMessage());
					} else if (x == 7) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❼" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_PURPLE + "VIP"
								+ ChatColor.GRAY + "] " + ChatColor.DARK_PURPLE
								+ p.getName() + ChatColor.DARK_GRAY + " >> "
								+ ChatColor.WHITE + e.getMessage());
					} else if (x == 8) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❽" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_PURPLE + "VIP"
								+ ChatColor.GRAY + "] " + ChatColor.DARK_PURPLE
								+ p.getName() + ChatColor.DARK_GRAY + " >> "
								+ ChatColor.WHITE + e.getMessage());
					} else if (x == 9) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❾" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_PURPLE + "VIP"
								+ ChatColor.GRAY + "] " + ChatColor.DARK_PURPLE
								+ p.getName() + ChatColor.DARK_GRAY + " >> "
								+ ChatColor.WHITE + e.getMessage());
					} else if (x >= 10) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "✪" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_PURPLE + "VIP"
								+ ChatColor.GRAY + "] " + ChatColor.DARK_PURPLE
								+ p.getName() + ChatColor.DARK_GRAY + " >> "
								+ ChatColor.WHITE + e.getMessage());
					}
				}
			} else if (vipplus.contains(p.getName())) {
				Team team = TeamManager.getInstance().getTeam(e.getPlayer());

				if (team != null) {
					if (x == 0) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.GRAY
								+ "] " + ChatColor.GRAY + "["
								+ ChatColor.DARK_PURPLE + "VIP"
								+ ChatColor.GREEN + "+" + ChatColor.GRAY
								+ "] <" + ChatColor.YELLOW + team.getName()
								+ ChatColor.GRAY + ">" + " "
								+ ChatColor.DARK_PURPLE + p.getName()
								+ ChatColor.DARK_GRAY + " >> "
								+ ChatColor.WHITE + e.getMessage());
					} else if (x == 1) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "*" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_PURPLE + "VIP"
								+ ChatColor.GREEN + "+" + ChatColor.GRAY
								+ "] <" + ChatColor.YELLOW + team.getName()
								+ ChatColor.GRAY + ">" + " "
								+ ChatColor.DARK_PURPLE + p.getName()
								+ ChatColor.DARK_GRAY + " >> "
								+ ChatColor.WHITE + e.getMessage());
					} else if (x == 2) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "**" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_PURPLE + "VIP"
								+ ChatColor.GREEN + "+" + ChatColor.GRAY
								+ "] <" + ChatColor.YELLOW + team.getName()
								+ ChatColor.GRAY + ">" + " "
								+ ChatColor.DARK_PURPLE + p.getName()
								+ ChatColor.DARK_GRAY + " >> "
								+ ChatColor.WHITE + e.getMessage());
					} else if (x == 3) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❸" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_PURPLE + "VIP"
								+ ChatColor.GREEN + "+" + ChatColor.GRAY
								+ "] <" + ChatColor.YELLOW + team.getName()
								+ ChatColor.GRAY + ">" + " "
								+ ChatColor.DARK_PURPLE + p.getName()
								+ ChatColor.DARK_GRAY + " >> "
								+ ChatColor.WHITE + e.getMessage());
					} else if (x == 4) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❹" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_PURPLE + "VIP"
								+ ChatColor.GREEN + "+" + ChatColor.GRAY
								+ "] <" + ChatColor.YELLOW + team.getName()
								+ ChatColor.GRAY + ">" + " "
								+ ChatColor.DARK_PURPLE + p.getName()
								+ ChatColor.DARK_GRAY + " >> "
								+ ChatColor.WHITE + e.getMessage());
					} else if (x == 5) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❺" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_PURPLE + "VIP"
								+ ChatColor.GREEN + "+" + ChatColor.GRAY
								+ "] <" + ChatColor.YELLOW + team.getName()
								+ ChatColor.GRAY + ">" + " "
								+ ChatColor.DARK_PURPLE + p.getName()
								+ ChatColor.DARK_GRAY + " >> "
								+ ChatColor.WHITE + e.getMessage());
					} else if (x == 6) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❻" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_PURPLE + "VIP"
								+ ChatColor.GREEN + "+" + ChatColor.GRAY
								+ "] <" + ChatColor.YELLOW + team.getName()
								+ ChatColor.GRAY + ">" + " "
								+ ChatColor.DARK_PURPLE + p.getName()
								+ ChatColor.DARK_GRAY + " >> "
								+ ChatColor.WHITE + e.getMessage());
					} else if (x == 7) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❼" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_PURPLE + "VIP"
								+ ChatColor.GREEN + "+" + ChatColor.GRAY
								+ "] <" + ChatColor.YELLOW + team.getName()
								+ ChatColor.GRAY + ">" + " "
								+ ChatColor.DARK_PURPLE + p.getName()
								+ ChatColor.DARK_GRAY + " >> "
								+ ChatColor.WHITE + e.getMessage());
					} else if (x == 8) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❽" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_PURPLE + "VIP"
								+ ChatColor.GREEN + "+" + ChatColor.GRAY
								+ "] <" + ChatColor.YELLOW + team.getName()
								+ ChatColor.GRAY + ">" + " "
								+ ChatColor.DARK_PURPLE + p.getName()
								+ ChatColor.DARK_GRAY + " >> "
								+ ChatColor.WHITE + e.getMessage());
					} else if (x == 9) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❾" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_PURPLE + "VIP"
								+ ChatColor.GREEN + "+" + ChatColor.GRAY
								+ "] <" + ChatColor.YELLOW + team.getName()
								+ ChatColor.GRAY + ">" + " "
								+ ChatColor.DARK_PURPLE + p.getName()
								+ ChatColor.DARK_GRAY + " >> "
								+ ChatColor.WHITE + e.getMessage());
					} else if (x >= 10) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "✪" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_PURPLE + "VIP"
								+ ChatColor.GREEN + "+" + ChatColor.GRAY
								+ "] <" + ChatColor.YELLOW + team.getName()
								+ ChatColor.GRAY + ">" + " "
								+ ChatColor.DARK_PURPLE + p.getName()
								+ ChatColor.DARK_GRAY + " >> "
								+ ChatColor.WHITE + e.getMessage());
					}
				} else {
					if (x == 0) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.GRAY
								+ "] " + ChatColor.GRAY + "["
								+ ChatColor.DARK_PURPLE + "VIP"
								+ ChatColor.GREEN + "+" + ChatColor.GRAY + "] "
								+ ChatColor.DARK_PURPLE + p.getName()
								+ ChatColor.DARK_GRAY + " >> "
								+ ChatColor.WHITE + e.getMessage());
					} else if (x == 1) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "*" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_PURPLE + "VIP"
								+ ChatColor.GREEN + "+" + ChatColor.GRAY + "] "
								+ ChatColor.DARK_PURPLE + p.getName()
								+ ChatColor.DARK_GRAY + " >> "
								+ ChatColor.WHITE + e.getMessage());
					} else if (x == 2) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "**" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_PURPLE + "VIP"
								+ ChatColor.GREEN + "+" + ChatColor.GRAY + "] "
								+ ChatColor.DARK_PURPLE + p.getName()
								+ ChatColor.DARK_GRAY + " >> "
								+ ChatColor.WHITE + e.getMessage());
					} else if (x == 3) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❸" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_PURPLE + "VIP"
								+ ChatColor.GREEN + "+" + ChatColor.GRAY + "] "
								+ ChatColor.DARK_PURPLE + p.getName()
								+ ChatColor.DARK_GRAY + " >> "
								+ ChatColor.WHITE + e.getMessage());
					} else if (x == 4) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❹" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_PURPLE + "VIP"
								+ ChatColor.GREEN + "+" + ChatColor.GRAY + "] "
								+ ChatColor.DARK_PURPLE + p.getName()
								+ ChatColor.DARK_GRAY + " >> "
								+ ChatColor.WHITE + e.getMessage());
					} else if (x == 5) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❺" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_PURPLE + "VIP"
								+ ChatColor.GREEN + "+" + ChatColor.GRAY + "] "
								+ ChatColor.DARK_PURPLE + p.getName()
								+ ChatColor.DARK_GRAY + " >> "
								+ ChatColor.WHITE + e.getMessage());
					} else if (x == 6) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❻" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_PURPLE + "VIP"
								+ ChatColor.GREEN + "+" + ChatColor.GRAY + "] "
								+ ChatColor.DARK_PURPLE + p.getName()
								+ ChatColor.DARK_GRAY + " >> "
								+ ChatColor.WHITE + e.getMessage());
					} else if (x == 7) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❼" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_PURPLE + "VIP"
								+ ChatColor.GREEN + "+" + ChatColor.GRAY + "] "
								+ ChatColor.DARK_PURPLE + p.getName()
								+ ChatColor.DARK_GRAY + " >> "
								+ ChatColor.WHITE + e.getMessage());
					} else if (x == 8) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❽" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_PURPLE + "VIP"
								+ ChatColor.GREEN + "+" + ChatColor.GRAY + "] "
								+ ChatColor.DARK_PURPLE + p.getName()
								+ ChatColor.DARK_GRAY + " >> "
								+ ChatColor.WHITE + e.getMessage());
					} else if (x == 9) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❾" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_PURPLE + "VIP"
								+ ChatColor.GREEN + "+" + ChatColor.GRAY + "] "
								+ ChatColor.DARK_PURPLE + p.getName()
								+ ChatColor.DARK_GRAY + " >> "
								+ ChatColor.WHITE + e.getMessage());
					} else if (x >= 10) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "✪" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_PURPLE + "VIP"
								+ ChatColor.GREEN + "+" + ChatColor.GRAY + "] "
								+ ChatColor.DARK_PURPLE + p.getName()
								+ ChatColor.DARK_GRAY + " >> "
								+ ChatColor.WHITE + e.getMessage());
					}
				}
			} else if (flame.contains(p.getName())) {
				Team team = TeamManager.getInstance().getTeam(e.getPlayer());

				if (team != null) {
					if (x == 0) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.GRAY
								+ "] " + ChatColor.GRAY + "["
								+ ChatColor.DARK_PURPLE + "Flame"
								+ ChatColor.GRAY + "] <" + ChatColor.YELLOW
								+ team.getName() + ChatColor.GRAY + ">" + " "
								+ ChatColor.DARK_PURPLE + p.getName()
								+ ChatColor.DARK_GRAY + " >> "
								+ ChatColor.WHITE + e.getMessage());
					} else if (x == 1) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "*" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_PURPLE + "Flame"
								+ ChatColor.GRAY + "] <" + ChatColor.YELLOW
								+ team.getName() + ChatColor.GRAY + ">" + " "
								+ ChatColor.DARK_PURPLE + p.getName()
								+ ChatColor.DARK_GRAY + " >> "
								+ ChatColor.WHITE + e.getMessage());
					} else if (x == 2) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "**" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_PURPLE + "Flame"
								+ ChatColor.GRAY + "] <" + ChatColor.YELLOW
								+ team.getName() + ChatColor.GRAY + ">" + " "
								+ ChatColor.DARK_PURPLE + p.getName()
								+ ChatColor.DARK_GRAY + " >> "
								+ ChatColor.WHITE + e.getMessage());
					} else if (x == 3) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❸" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_PURPLE + "Flame"
								+ ChatColor.GRAY + "] <" + ChatColor.YELLOW
								+ team.getName() + ChatColor.GRAY + ">" + " "
								+ ChatColor.DARK_PURPLE + p.getName()
								+ ChatColor.DARK_GRAY + " >> "
								+ ChatColor.WHITE + e.getMessage());
					} else if (x == 4) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❹" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_PURPLE + "Flame"
								+ ChatColor.GRAY + "] <" + ChatColor.YELLOW
								+ team.getName() + ChatColor.GRAY + ">" + " "
								+ ChatColor.DARK_PURPLE + p.getName()
								+ ChatColor.DARK_GRAY + " >> "
								+ ChatColor.WHITE + e.getMessage());
					} else if (x == 5) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❺" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_PURPLE + "Flame"
								+ ChatColor.GRAY + "] <" + ChatColor.YELLOW
								+ team.getName() + ChatColor.GRAY + ">" + " "
								+ ChatColor.DARK_PURPLE + p.getName()
								+ ChatColor.DARK_GRAY + " >> "
								+ ChatColor.WHITE + e.getMessage());
					} else if (x == 6) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❻" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_PURPLE + "Flame"
								+ ChatColor.GRAY + "] <" + ChatColor.YELLOW
								+ team.getName() + ChatColor.GRAY + ">" + " "
								+ ChatColor.DARK_PURPLE + p.getName()
								+ ChatColor.DARK_GRAY + " >> "
								+ ChatColor.WHITE + e.getMessage());
					} else if (x == 7) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❼" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_PURPLE + "Flame"
								+ ChatColor.GRAY + "] <" + ChatColor.YELLOW
								+ team.getName() + ChatColor.GRAY + ">" + " "
								+ ChatColor.DARK_PURPLE + p.getName()
								+ ChatColor.DARK_GRAY + " >> "
								+ ChatColor.WHITE + e.getMessage());
					} else if (x == 8) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❽" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_PURPLE + "Flame"
								+ ChatColor.GRAY + "] <" + ChatColor.YELLOW
								+ team.getName() + ChatColor.GRAY + ">" + " "
								+ ChatColor.DARK_PURPLE + p.getName()
								+ ChatColor.DARK_GRAY + " >> "
								+ ChatColor.WHITE + e.getMessage());
					} else if (x == 9) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❾" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_PURPLE + "Flame"
								+ ChatColor.GRAY + "] <" + ChatColor.YELLOW
								+ team.getName() + ChatColor.GRAY + ">" + " "
								+ ChatColor.DARK_PURPLE + p.getName()
								+ ChatColor.DARK_GRAY + " >> "
								+ ChatColor.WHITE + e.getMessage());
					} else if (x >= 10) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "✪" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_PURPLE + "Flame"
								+ ChatColor.GRAY + "] <" + ChatColor.YELLOW
								+ team.getName() + ChatColor.GRAY + ">" + " "
								+ ChatColor.DARK_PURPLE + p.getName()
								+ ChatColor.DARK_GRAY + " >> "
								+ ChatColor.WHITE + e.getMessage());
					}
				} else {
					if (x == 0) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.GRAY
								+ "] " + ChatColor.GRAY + "["
								+ ChatColor.DARK_PURPLE + "Flame"
								+ ChatColor.GRAY + "] " + ChatColor.DARK_PURPLE
								+ p.getName() + ChatColor.DARK_GRAY + " >> "
								+ ChatColor.WHITE + e.getMessage());
					} else if (x == 1) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "*" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_PURPLE + "Flame"
								+ ChatColor.GRAY + "] " + ChatColor.DARK_PURPLE
								+ p.getName() + ChatColor.DARK_GRAY + " >> "
								+ ChatColor.WHITE + e.getMessage());
					} else if (x == 2) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "**" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_PURPLE + "Flame"
								+ ChatColor.GRAY + "] " + ChatColor.DARK_PURPLE
								+ p.getName() + ChatColor.DARK_GRAY + " >> "
								+ ChatColor.WHITE + e.getMessage());
					} else if (x == 3) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❸" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_PURPLE + "Flame"
								+ ChatColor.GRAY + "] " + ChatColor.DARK_PURPLE
								+ p.getName() + ChatColor.DARK_GRAY + " >> "
								+ ChatColor.WHITE + e.getMessage());
					} else if (x == 4) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❹" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_PURPLE + "Flame"
								+ ChatColor.GRAY + "] " + ChatColor.DARK_PURPLE
								+ p.getName() + ChatColor.DARK_GRAY + " >> "
								+ ChatColor.WHITE + e.getMessage());
					} else if (x == 5) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❺" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_PURPLE + "Flame"
								+ ChatColor.GRAY + "] " + ChatColor.DARK_PURPLE
								+ p.getName() + ChatColor.DARK_GRAY + " >> "
								+ ChatColor.WHITE + e.getMessage());
					} else if (x == 6) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❻" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_PURPLE + "Flame"
								+ ChatColor.GRAY + "] " + ChatColor.DARK_PURPLE
								+ p.getName() + ChatColor.DARK_GRAY + " >> "
								+ ChatColor.WHITE + e.getMessage());
					} else if (x == 7) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❼" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_PURPLE + "Flame"
								+ ChatColor.GRAY + "] " + ChatColor.DARK_PURPLE
								+ p.getName() + ChatColor.DARK_GRAY + " >> "
								+ ChatColor.WHITE + e.getMessage());
					} else if (x == 8) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❽" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_PURPLE + "Flame"
								+ ChatColor.GRAY + "] " + ChatColor.DARK_PURPLE
								+ p.getName() + ChatColor.DARK_GRAY + " >> "
								+ ChatColor.WHITE + e.getMessage());
					} else if (x == 9) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❾" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_PURPLE + "Flame"
								+ ChatColor.GRAY + "] " + ChatColor.DARK_PURPLE
								+ p.getName() + ChatColor.DARK_GRAY + " >> "
								+ ChatColor.WHITE + e.getMessage());
					} else if (x >= 10) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "✪" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "[" + ChatColor.DARK_PURPLE + "Flame"
								+ ChatColor.GRAY + "] " + ChatColor.DARK_PURPLE
								+ p.getName() + ChatColor.DARK_GRAY + " >> "
								+ ChatColor.WHITE + e.getMessage());
					}
				}
			} else {
				Team team = TeamManager.getInstance().getTeam(e.getPlayer());

				if (team != null) {
					if (x == 0) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.GRAY
								+ "] " + ChatColor.GRAY + "<"
								+ ChatColor.YELLOW + team.getName()
								+ ChatColor.GRAY + ">" + " " + ChatColor.GRAY
								+ p.getName() + ChatColor.DARK_GRAY + " >> "
								+ ChatColor.GRAY + e.getMessage());
					} else if (x == 1) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "*" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "<" + ChatColor.YELLOW + team.getName()
								+ ChatColor.GRAY + ">" + " " + ChatColor.GRAY
								+ p.getName() + ChatColor.DARK_GRAY + " >> "
								+ ChatColor.GRAY + e.getMessage());
					} else if (x == 2) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "**" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "<" + ChatColor.YELLOW + team.getName()
								+ ChatColor.GRAY + ">" + " " + ChatColor.GRAY
								+ p.getName() + ChatColor.DARK_GRAY + " >> "
								+ ChatColor.GRAY + e.getMessage());
					} else if (x == 3) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❸" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "<" + ChatColor.YELLOW + team.getName()
								+ ChatColor.GRAY + ">" + " " + ChatColor.GRAY
								+ p.getName() + ChatColor.DARK_GRAY + " >> "
								+ ChatColor.GRAY + e.getMessage());
					} else if (x == 4) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❹" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "<" + ChatColor.YELLOW + team.getName()
								+ ChatColor.GRAY + ">" + " " + ChatColor.GRAY
								+ p.getName() + ChatColor.DARK_GRAY + " >> "
								+ ChatColor.GRAY + e.getMessage());
					} else if (x == 5) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❺" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "<" + ChatColor.YELLOW + team.getName()
								+ ChatColor.GRAY + ">" + " " + ChatColor.GRAY
								+ p.getName() + ChatColor.DARK_GRAY + " >> "
								+ ChatColor.GRAY + e.getMessage());
					} else if (x == 6) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❻" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "<" + ChatColor.YELLOW + team.getName()
								+ ChatColor.GRAY + ">" + " " + ChatColor.GRAY
								+ p.getName() + ChatColor.DARK_GRAY + " >> "
								+ ChatColor.GRAY + e.getMessage());
					} else if (x == 7) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❼" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "<" + ChatColor.YELLOW + team.getName()
								+ ChatColor.GRAY + ">" + " " + ChatColor.GRAY
								+ p.getName() + ChatColor.DARK_GRAY + " >> "
								+ ChatColor.GRAY + e.getMessage());
					} else if (x == 8) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❽" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "<" + ChatColor.YELLOW + team.getName()
								+ ChatColor.GRAY + ">" + " " + ChatColor.GRAY
								+ p.getName() + ChatColor.DARK_GRAY + " >> "
								+ ChatColor.GRAY + e.getMessage());
					} else if (x == 9) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❾" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "<" + ChatColor.YELLOW + team.getName()
								+ ChatColor.GRAY + ">" + " " + ChatColor.GRAY
								+ p.getName() + ChatColor.DARK_GRAY + " >> "
								+ ChatColor.GRAY + e.getMessage());
					} else if (x >= 10) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "✪" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ "<" + ChatColor.YELLOW + team.getName()
								+ ChatColor.GRAY + ">" + " " + ChatColor.GRAY
								+ p.getName() + ChatColor.DARK_GRAY + " >> "
								+ ChatColor.GRAY + e.getMessage());
					}
				} else {
					if (x == 0) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.GRAY
								+ "] " + ChatColor.GRAY + p.getName()
								+ ChatColor.DARK_GRAY + " >> " + ChatColor.GRAY
								+ e.getMessage());
					} else if (x == 1) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "*" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ p.getName() + ChatColor.DARK_GRAY + " >> "
								+ ChatColor.GRAY + e.getMessage());
					} else if (x == 2) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "**" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ p.getName() + ChatColor.DARK_GRAY + " >> "
								+ ChatColor.GRAY + e.getMessage());
					} else if (x == 3) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❸" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ p.getName() + ChatColor.DARK_GRAY + " >> "
								+ ChatColor.GRAY + e.getMessage());
					} else if (x == 4) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❹" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ p.getName() + ChatColor.DARK_GRAY + " >> "
								+ ChatColor.GRAY + e.getMessage());
					} else if (x == 5) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❺" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ p.getName() + ChatColor.DARK_GRAY + " >> "
								+ ChatColor.GRAY + e.getMessage());
					} else if (x == 6) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❻" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ p.getName() + ChatColor.DARK_GRAY + " >> "
								+ ChatColor.GRAY + e.getMessage());
					} else if (x == 7) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❼" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ p.getName() + ChatColor.DARK_GRAY + " >> "
								+ ChatColor.GRAY + e.getMessage());
					} else if (x == 8) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❽" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ p.getName() + ChatColor.DARK_GRAY + " >> "
								+ ChatColor.GRAY + e.getMessage());
					} else if (x == 9) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "❾" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ p.getName() + ChatColor.DARK_GRAY + " >> "
								+ ChatColor.GRAY + e.getMessage());
					} else if (x >= 10) {
						e.setFormat(ChatColor.GRAY + "[" + ChatColor.GOLD
								+ "Lv. " + level.toString() + ChatColor.AQUA
								+ "✪" + ChatColor.GRAY + "] " + ChatColor.GRAY
								+ p.getName() + ChatColor.DARK_GRAY + " >> "
								+ ChatColor.GRAY + e.getMessage());
					}
				}
			}
		}
	}
}