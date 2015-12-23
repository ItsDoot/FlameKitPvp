package com.bear53.ecore.commands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.bear53.ecore.Core;

public class CommandGroupSet implements CommandExecutor {

	Core plugin;

	public CommandGroupSet(Core pl) {
		this.plugin = pl;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		List<String> moderators = plugin.getConfig()
				.getStringList("moderators");
		List<String> owners = plugin.getConfig().getStringList("owners");
		List<String> admins = plugin.getConfig().getStringList("admins");
		List<String> donors = plugin.getConfig().getStringList("donors");
		List<String> defaults = plugin.getConfig().getStringList("defaults");
		List<String> helpers = plugin.getConfig().getStringList("helpers");
		List<String> vip = plugin.getConfig().getStringList("vip");
		List<String> vipplus = plugin.getConfig().getStringList("vip+");
		List<String> flame = plugin.getConfig().getStringList("flame");
		if ((sender.isOp()) || (!(sender instanceof Player))) {
			if (args.length != 2) {
				sender.sendMessage(ChatColor.RED
						+ "Invalid arguments! Usage: /groupset <player> <group>");
			} else {
				Player p = Bukkit.getServer().getPlayer(args[0]);
				if (p == null) {
					sender.sendMessage(ChatColor.RED + "Error: The player '"
							+ args[0] + "' cannot be found.");
				} else if (args[1].equalsIgnoreCase("helper")) {
					if (admins.contains(p.getName())) {
						admins.remove(p.getName());
					}
					if (donors.contains(p.getName())) {
						donors.remove(p.getName());
					}
					if (moderators.contains(p.getName())) {
						moderators.remove(p.getName());
					}
					if (defaults.contains(p.getName())) {
						defaults.remove(p.getName());
					}
					if (vip.contains(p.getName())) {
						vip.remove(p.getName());
					}
					if (vipplus.contains(p.getName())) {
						vipplus.remove(p.getName());
					}
					if (flame.contains(p.getName())) {
						flame.remove(p.getName());
					}
					if (owners.contains(p.getName())) {
						owners.remove(p.getName());
					}
					helpers.add(p.getName());
					sender.sendMessage(ChatColor.GREEN + "You set "
							+ ChatColor.YELLOW + args[0] + "'s"
							+ ChatColor.GREEN + " group to " + ChatColor.YELLOW
							+ args[1]);

					plugin.getConfig().set("moderators", moderators);
					plugin.getConfig().set("owners", owners);
					plugin.getConfig().set("admins", admins);
					plugin.getConfig().set("donors", donors);
					plugin.getConfig().set("defaults", defaults);
					plugin.getConfig().set("vip", vip);
					plugin.getConfig().set("vip+", vipplus);
					plugin.getConfig().set("flame", flame);
					plugin.getConfig().set("helpers", helpers);

					plugin.saveConfig();

					if (moderators.contains(p.getName())) {
						p.setPlayerListName(ChatColor.GOLD + p.getName());
					} else if (owners.contains(p.getName())) {
						p.setPlayerListName(ChatColor.DARK_RED + p.getName());
					} else if (admins.contains(p.getName())) {
						p.setPlayerListName(ChatColor.RED + p.getName());
					} else if (helpers.contains(p.getName())) {
						p.setPlayerListName(ChatColor.DARK_AQUA + p.getName());
					} else if (donors.contains(p.getName())
							|| (vip.contains(p.getName()) || (vipplus
									.contains(p.getName()) || (flame
									.contains(p.getName()))))) {
						p.setPlayerListName(ChatColor.DARK_PURPLE + p.getName());
					} else {
						p.setPlayerListName(ChatColor.GRAY + p.getName());
					}
				} else if (args[1].equalsIgnoreCase("owner")) {
					if (admins.contains(p.getName())) {
						admins.remove(p.getName());
					}
					if (donors.contains(p.getName())) {
						donors.remove(p.getName());
					}
					if (moderators.contains(p.getName())) {
						moderators.remove(p.getName());
					}
					if (defaults.contains(p.getName())) {
						defaults.remove(p.getName());
					}
					if (vip.contains(p.getName())) {
						vip.remove(p.getName());
					}
					if (vipplus.contains(p.getName())) {
						vipplus.remove(p.getName());
					}
					if (flame.contains(p.getName())) {
						flame.remove(p.getName());
					}
					if (helpers.contains(p.getName())) {
						helpers.remove(p.getName());
					}
					owners.add(p.getName());
					sender.sendMessage(ChatColor.GREEN + "You set "
							+ ChatColor.YELLOW + args[0] + "'s"
							+ ChatColor.GREEN + " group to " + ChatColor.YELLOW
							+ args[1]);

					plugin.getConfig().set("moderators", moderators);
					plugin.getConfig().set("owners", owners);
					plugin.getConfig().set("admins", admins);
					plugin.getConfig().set("donors", donors);
					plugin.getConfig().set("defaults", defaults);
					plugin.getConfig().set("vip", vip);
					plugin.getConfig().set("vip+", vipplus);
					plugin.getConfig().set("flame", flame);
					plugin.getConfig().set("helpers", helpers);

					plugin.saveConfig();

					if (moderators.contains(p.getName())) {
						p.setPlayerListName(ChatColor.GOLD + p.getName());
					} else if (owners.contains(p.getName())) {
						p.setPlayerListName(ChatColor.DARK_RED + p.getName());
					} else if (admins.contains(p.getName())) {
						p.setPlayerListName(ChatColor.RED + p.getName());
					} else if (helpers.contains(p.getName())) {
						p.setPlayerListName(ChatColor.DARK_AQUA + p.getName());
					} else if (donors.contains(p.getName())
							|| (vip.contains(p.getName()) || (vipplus
									.contains(p.getName()) || (flame
									.contains(p.getName()))))) {
						p.setPlayerListName(ChatColor.DARK_PURPLE + p.getName());
					} else {
						p.setPlayerListName(ChatColor.GRAY + p.getName());
					}
				} else if (args[1].equalsIgnoreCase("admin")) {
					if (owners.contains(p.getName())) {
						owners.remove(p.getName());
					}
					if (donors.contains(p.getName())) {
						donors.remove(p.getName());
					}
					if (moderators.contains(p.getName())) {
						moderators.remove(p.getName());
					}
					if (defaults.contains(p.getName())) {
						defaults.remove(p.getName());
					}
					if (vip.contains(p.getName())) {
						vip.remove(p.getName());
					}
					if (vipplus.contains(p.getName())) {
						vipplus.remove(p.getName());
					}
					if (flame.contains(p.getName())) {
						flame.remove(p.getName());
					}
					if (helpers.contains(p.getName())) {
						helpers.remove(p.getName());
					}
					admins.add(p.getName());
					sender.sendMessage(ChatColor.GREEN + "You set "
							+ ChatColor.YELLOW + args[0] + "'s"
							+ ChatColor.GREEN + " group to " + ChatColor.YELLOW
							+ args[1]);

					plugin.getConfig().set("moderator", moderators);
					plugin.getConfig().set("owners", owners);
					plugin.getConfig().set("admins", admins);
					plugin.getConfig().set("donors", donors);
					plugin.getConfig().set("defaults", defaults);
					plugin.getConfig().set("vip", vip);
					plugin.getConfig().set("vip+", vipplus);
					plugin.getConfig().set("flame", flame);
					plugin.getConfig().set("helpers", helpers);

					plugin.saveConfig();

					if (moderators.contains(p.getName())) {
						p.setPlayerListName(ChatColor.GOLD + p.getName());
					} else if (owners.contains(p.getName())) {
						p.setPlayerListName(ChatColor.DARK_RED + p.getName());
					} else if (admins.contains(p.getName())) {
						p.setPlayerListName(ChatColor.RED + p.getName());
					} else if (donors.contains(p.getName())
							|| (vip.contains(p.getName()) || (vipplus
									.contains(p.getName()) || (flame
									.contains(p.getName()))))) {
						p.setPlayerListName(ChatColor.DARK_PURPLE + p.getName());
					} else if (helpers.contains(p.getName())) {
						p.setPlayerListName(ChatColor.DARK_AQUA + p.getName());
					} else {
						p.setPlayerListName(ChatColor.GRAY + p.getName());
					}
				} else if (args[1].equalsIgnoreCase("donor")) {
					if (owners.contains(p.getName())) {
						owners.remove(p.getName());
					}
					if (admins.contains(p.getName())) {
						admins.remove(p.getName());
					}
					if (moderators.contains(p.getName())) {
						moderators.remove(p.getName());
					}
					if (defaults.contains(p.getName())) {
						defaults.remove(p.getName());
					}
					if (vip.contains(p.getName())) {
						vip.remove(p.getName());
					}
					if (vipplus.contains(p.getName())) {
						vipplus.remove(p.getName());
					}
					if (flame.contains(p.getName())) {
						flame.remove(p.getName());
					}
					if (helpers.contains(p.getName())) {
						helpers.remove(p.getName());
					}
					donors.add(p.getName());
					sender.sendMessage(ChatColor.GREEN + "You set "
							+ ChatColor.YELLOW + args[0] + "'s"
							+ ChatColor.GREEN + " group to " + ChatColor.YELLOW
							+ args[1]);

					plugin.getConfig().set("moderators", moderators);
					plugin.getConfig().set("owners", owners);
					plugin.getConfig().set("admins", admins);
					plugin.getConfig().set("donors", donors);
					plugin.getConfig().set("defaults", defaults);
					plugin.getConfig().set("vip", vip);
					plugin.getConfig().set("vip+", vipplus);
					plugin.getConfig().set("flame", flame);
					plugin.getConfig().set("helpers", helpers);

					plugin.saveConfig();

					if (moderators.contains(p.getName())) {
						p.setPlayerListName(ChatColor.GOLD + p.getName());
					} else if (owners.contains(p.getName())) {
						p.setPlayerListName(ChatColor.DARK_RED + p.getName());
					} else if (admins.contains(p.getName())) {
						p.setPlayerListName(ChatColor.RED + p.getName());
					} else if (donors.contains(p.getName())
							|| (vip.contains(p.getName()) || (vipplus
									.contains(p.getName()) || (flame
									.contains(p.getName()))))) {
						p.setPlayerListName(ChatColor.DARK_PURPLE + p.getName());
					} else if (helpers.contains(p.getName())) {
						p.setPlayerListName(ChatColor.DARK_AQUA + p.getName());
					} else {
						p.setPlayerListName(ChatColor.GRAY + p.getName());
					}
				} else if (args[1].equalsIgnoreCase("moderator")) {
					if (admins.contains(p.getName())) {
						admins.remove(p.getName());
					}
					if (donors.contains(p.getName())) {
						donors.remove(p.getName());
					}
					if (owners.contains(p.getName())) {
						owners.remove(p.getName());
					}
					if (defaults.contains(p.getName())) {
						defaults.remove(p.getName());
					}
					if (vip.contains(p.getName())) {
						vip.remove(p.getName());
					}
					if (vipplus.contains(p.getName())) {
						vipplus.remove(p.getName());
					}
					if (flame.contains(p.getName())) {
						flame.remove(p.getName());
					}
					if (helpers.contains(p.getName())) {
						helpers.remove(p.getName());
					}
					moderators.add(p.getName());
					sender.sendMessage(ChatColor.GREEN + "You set "
							+ ChatColor.YELLOW + args[0] + "'s"
							+ ChatColor.GREEN + " group to " + ChatColor.YELLOW
							+ args[1]);

					plugin.getConfig().set("moderators", moderators);
					plugin.getConfig().set("owners", owners);
					plugin.getConfig().set("admins", admins);
					plugin.getConfig().set("donors", donors);
					plugin.getConfig().set("defaults", defaults);
					plugin.getConfig().set("vip", vip);
					plugin.getConfig().set("vip+", vipplus);
					plugin.getConfig().set("flame", flame);
					plugin.getConfig().set("helpers", helpers);

					plugin.saveConfig();

					if (moderators.contains(p.getName())) {
						p.setPlayerListName(ChatColor.GOLD + p.getName());
					} else if (owners.contains(p.getName())) {
						p.setPlayerListName(ChatColor.DARK_RED + p.getName());
					} else if (admins.contains(p.getName())) {
						p.setPlayerListName(ChatColor.RED + p.getName());
					} else if (donors.contains(p.getName())
							|| (vip.contains(p.getName()) || (vipplus
									.contains(p.getName()) || (flame
									.contains(p.getName()))))) {
						p.setPlayerListName(ChatColor.DARK_PURPLE + p.getName());
					} else if (helpers.contains(p.getName())) {
						p.setPlayerListName(ChatColor.DARK_AQUA + p.getName());
					} else {
						p.setPlayerListName(ChatColor.GRAY + p.getName());
					}
				} else if ((args[1].equalsIgnoreCase("norank"))
						|| (args[1].equalsIgnoreCase("default"))
						|| (args[1].equalsIgnoreCase("defaults"))
						|| (args[1].equalsIgnoreCase("member"))) {
					if (admins.contains(p.getName())) {
						admins.remove(p.getName());
					}
					if (donors.contains(p.getName())) {
						donors.remove(p.getName());
					}
					if (owners.contains(p.getName())) {
						owners.remove(p.getName());
					}
					if (vip.contains(p.getName())) {
						vip.remove(p.getName());
					}
					if (vipplus.contains(p.getName())) {
						vipplus.remove(p.getName());
					}
					if (flame.contains(p.getName())) {
						flame.remove(p.getName());
					}
					if (moderators.contains(p.getName())) {
						moderators.remove(p.getName());
					}
					if (helpers.contains(p.getName())) {
						helpers.remove(p.getName());
					}

					sender.sendMessage(ChatColor.GREEN + "You set "
							+ ChatColor.YELLOW + args[0] + "'s"
							+ ChatColor.GREEN + " group to " + ChatColor.YELLOW
							+ args[1]);
					plugin.getConfig().set("moderators", moderators);
					plugin.getConfig().set("owners", owners);
					plugin.getConfig().set("admins", admins);
					plugin.getConfig().set("donors", donors);
					plugin.getConfig().set("defaults", defaults);
					plugin.getConfig().set("vip", vip);
					plugin.getConfig().set("vip+", vipplus);
					plugin.getConfig().set("flame", flame);
					plugin.getConfig().set("helpers", helpers);

					plugin.saveConfig();

					if (moderators.contains(p.getName())) {
						p.setPlayerListName(ChatColor.GOLD + p.getName());
					} else if (owners.contains(p.getName())) {
						p.setPlayerListName(ChatColor.DARK_RED + p.getName());
					} else if (admins.contains(p.getName())) {
						p.setPlayerListName(ChatColor.RED + p.getName());
					} else if (donors.contains(p.getName())
							|| (vip.contains(p.getName()) || (vipplus
									.contains(p.getName()) || (flame
									.contains(p.getName()))))) {
						p.setPlayerListName(ChatColor.DARK_PURPLE + p.getName());
					} else if (helpers.contains(p.getName())) {
						p.setPlayerListName(ChatColor.DARK_AQUA + p.getName());
					} else {
						p.setPlayerListName(ChatColor.GRAY + p.getName());
					}
				} else if (args[1].equalsIgnoreCase("vip")) {
					if (admins.contains(p.getName())) {
						admins.remove(p.getName());
					}
					if (donors.contains(p.getName())) {
						donors.remove(p.getName());
					}
					if (owners.contains(p.getName())) {
						owners.remove(p.getName());
					}
					if (defaults.contains(p.getName())) {
						defaults.remove(p.getName());
					}
					if (vipplus.contains(p.getName())) {
						vipplus.remove(p.getName());
					}
					if (flame.contains(p.getName())) {
						flame.remove(p.getName());
					}
					if (helpers.contains(p.getName())) {
						helpers.remove(p.getName());
					}
					vip.add(p.getName());
					sender.sendMessage(ChatColor.GREEN + "You set "
							+ ChatColor.YELLOW + args[0] + "'s"
							+ ChatColor.GREEN + " group to " + ChatColor.YELLOW
							+ args[1]);

					plugin.getConfig().set("moderators", moderators);
					plugin.getConfig().set("owners", owners);
					plugin.getConfig().set("admins", admins);
					plugin.getConfig().set("donors", donors);
					plugin.getConfig().set("defaults", defaults);
					plugin.getConfig().set("vip", vip);
					plugin.getConfig().set("vip+", vipplus);
					plugin.getConfig().set("flame", flame);
					plugin.getConfig().set("helpers", helpers);

					plugin.saveConfig();

					if (moderators.contains(p.getName())) {
						p.setPlayerListName(ChatColor.GOLD + p.getName());
					} else if (owners.contains(p.getName())) {
						p.setPlayerListName(ChatColor.DARK_RED + p.getName());
					} else if (admins.contains(p.getName())) {
						p.setPlayerListName(ChatColor.RED + p.getName());
					} else if (donors.contains(p.getName())
							|| (vip.contains(p.getName()) || (vipplus
									.contains(p.getName()) || (flame
									.contains(p.getName()))))) {
						p.setPlayerListName(ChatColor.DARK_PURPLE + p.getName());
					} else if (helpers.contains(p.getName())) {
						p.setPlayerListName(ChatColor.DARK_AQUA + p.getName());
					} else {
						p.setPlayerListName(ChatColor.GRAY + p.getName());
					}
				} else if (args[1].equalsIgnoreCase("vip+")) {
					if (admins.contains(p.getName())) {
						admins.remove(p.getName());
					}
					if (donors.contains(p.getName())) {
						donors.remove(p.getName());
					}
					if (owners.contains(p.getName())) {
						owners.remove(p.getName());
					}
					if (defaults.contains(p.getName())) {
						defaults.remove(p.getName());
					}
					if (vip.contains(p.getName())) {
						vip.remove(p.getName());
					}
					if (flame.contains(p.getName())) {
						flame.remove(p.getName());
					}
					if (helpers.contains(p.getName())) {
						helpers.remove(p.getName());
					}
					vipplus.add(p.getName());
					sender.sendMessage(ChatColor.GREEN + "You set "
							+ ChatColor.YELLOW + args[0] + "'s"
							+ ChatColor.GREEN + " group to " + ChatColor.YELLOW
							+ args[1]);

					plugin.getConfig().set("moderators", moderators);
					plugin.getConfig().set("owners", owners);
					plugin.getConfig().set("admins", admins);
					plugin.getConfig().set("donors", donors);
					plugin.getConfig().set("defaults", defaults);
					plugin.getConfig().set("vip", vip);
					plugin.getConfig().set("vip+", vipplus);
					plugin.getConfig().set("flame", flame);
					plugin.getConfig().set("helpers", helpers);

					plugin.saveConfig();

					if (moderators.contains(p.getName())) {
						p.setPlayerListName(ChatColor.GOLD + p.getName());
					} else if (owners.contains(p.getName())) {
						p.setPlayerListName(ChatColor.DARK_RED + p.getName());
					} else if (admins.contains(p.getName())) {
						p.setPlayerListName(ChatColor.RED + p.getName());
					} else if (donors.contains(p.getName())
							|| (vip.contains(p.getName()) || (vipplus
									.contains(p.getName()) || (flame
									.contains(p.getName()))))) {
						p.setPlayerListName(ChatColor.DARK_PURPLE + p.getName());
					} else if (helpers.contains(p.getName())) {
						p.setPlayerListName(ChatColor.DARK_AQUA + p.getName());
					} else {
						p.setPlayerListName(ChatColor.GRAY + p.getName());
					}
				} else if (args[1].equalsIgnoreCase("flame")) {
					if (admins.contains(p.getName())) {
						admins.remove(p.getName());
					}
					if (donors.contains(p.getName())) {
						donors.remove(p.getName());
					}
					if (owners.contains(p.getName())) {
						owners.remove(p.getName());
					}
					if (defaults.contains(p.getName())) {
						defaults.remove(p.getName());
					}
					if (vip.contains(p.getName())) {
						vip.remove(p.getName());
					}
					if (vipplus.contains(p.getName())) {
						vipplus.remove(p.getName());
					}
					if (helpers.contains(p.getName())) {
						helpers.remove(p.getName());
					}
					flame.add(p.getName());
					sender.sendMessage(ChatColor.GREEN + "You set "
							+ ChatColor.YELLOW + args[0] + "'s"
							+ ChatColor.GREEN + " group to " + ChatColor.YELLOW
							+ args[1]);

					plugin.getConfig().set("moderators", moderators);
					plugin.getConfig().set("owners", owners);
					plugin.getConfig().set("admins", admins);
					plugin.getConfig().set("donors", donors);
					plugin.getConfig().set("defaults", defaults);
					plugin.getConfig().set("vip", vip);
					plugin.getConfig().set("vip+", vipplus);
					plugin.getConfig().set("flame", flame);
					plugin.getConfig().set("helpers", helpers);

					plugin.saveConfig();

					if (moderators.contains(p.getName())) {
						p.setPlayerListName(ChatColor.GOLD + p.getName());
					} else if (owners.contains(p.getName())) {
						p.setPlayerListName(ChatColor.DARK_RED + p.getName());
					} else if (admins.contains(p.getName())) {
						p.setPlayerListName(ChatColor.RED + p.getName());
					} else if (donors.contains(p.getName())
							|| (vip.contains(p.getName()) || (vipplus
									.contains(p.getName()) || (flame
									.contains(p.getName()))))) {
						p.setPlayerListName(ChatColor.DARK_PURPLE + p.getName());
					} else if (helpers.contains(p.getName())) {
						p.setPlayerListName(ChatColor.DARK_AQUA + p.getName());
					} else {
						p.setPlayerListName(ChatColor.GRAY + p.getName());
					}
				} else {
					sender.sendMessage(ChatColor.RED + "Error: The rank '"
							+ args[1] + "' cannot be found.");
				}
			}
		} else {
			sender.sendMessage(ChatColor.RED + "No permission.");
		}
		return true;
	}
}
