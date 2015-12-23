package com.bear53.ecore.commands;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.bear53.ecore.Core;

public class CommandStats implements CommandExecutor {

	Core plugin;

	public CommandStats(Core pl) {
		this.plugin = pl;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Only players can use this command");
		}
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("stats")) {
			if (args.length == 0) {
				p.sendMessage(ChatColor.YELLOW + "" + ChatColor.STRIKETHROUGH
						+ "-----------------------" + ChatColor.RESET
						+ ChatColor.RED + " Stats " + ChatColor.YELLOW + ""
						+ ChatColor.STRIKETHROUGH + "-----------------------");
				p.sendMessage(ChatColor.YELLOW + p.getName() + "'s "
						+ ChatColor.RED + "Stats");
				p.sendMessage(ChatColor.GREEN
						+ "Kills: "
						+ plugin.getConfig().getInt(
								"players." + p.getUniqueId().toString()
										+ ".kills"));
				p.sendMessage(ChatColor.GOLD
						+ "Coins: "
						+ plugin.getConfig().getInt(
								"players." + p.getUniqueId().toString()
										+ ".coins"));
				p.sendMessage(ChatColor.RED
						+ "Deaths: "
						+ plugin.getConfig().getInt(
								"players." + p.getUniqueId().toString()
										+ ".deaths"));
				double kills = this.plugin.getConfig().getInt(
						"players." + p.getUniqueId().toString() + ".kills");
				double deaths = this.plugin.getConfig().getInt(
						"players." + p.getUniqueId().toString() + ".deaths");
				if (deaths == 0.0D) {
					deaths = 1.0D;
				}
				String KD = String.format("%.2f",
						new Object[] { Double.valueOf(kills / deaths) });
				p.sendMessage(ChatColor.AQUA + "K/D Ratio: " + ChatColor.GRAY
						+ Arrays.asList(new String[] { "§7" + KD }));
				p.sendMessage(ChatColor.GREEN
						+ "1v1 Wins: "
						+ plugin.getConfig().getInt(
								"players." + p.getUniqueId().toString()
										+ ".1v1wins"));
				p.sendMessage(ChatColor.RED
						+ "1v1 Loses: "
						+ plugin.getConfig().getInt(
								"players." + p.getUniqueId().toString()
										+ ".1v1loses"));
				double wins1 = plugin.getConfig().getInt(
						"players." + p.getUniqueId().toString() + ".1v1wins");
				double loses1 = plugin.getConfig().getInt(
						"players." + p.getUniqueId().toString() + ".1v1loses");
				if (loses1 == 0.0D) {
					loses1 = 1.0D;
				}

				String wl = String.format("%.2f",
						new Object[] { Double.valueOf(wins1 / loses1) });
				p.sendMessage(ChatColor.AQUA + "W/L Ratio: " + ChatColor.GRAY
						+ Arrays.asList(new String[] { "§7" + wl }));
				p.sendMessage(ChatColor.YELLOW
						+ ""
						+ ChatColor.STRIKETHROUGH
						+ "----------------------------------------------------");
			}
		} else if (args.length == 1) {
			Player statname = Bukkit.getPlayer(args[0]);
			if (statname == null) {
				p.sendMessage(ChatColor.RED + "Error: The player " + args[0]
						+ " is offline or cannot be found!");
			} else {
				p.sendMessage(ChatColor.YELLOW + "" + ChatColor.STRIKETHROUGH
						+ "-----------------------" + ChatColor.RESET
						+ ChatColor.RED + " Stats " + ChatColor.YELLOW + ""
						+ ChatColor.STRIKETHROUGH + "-----------------------");
				p.sendMessage(ChatColor.YELLOW + statname.getName() + "'s "
						+ ChatColor.RED + "Stats");
				p.sendMessage(ChatColor.GREEN
						+ "Kills: "
						+ plugin.getConfig().getInt(
								"players." + statname.getUniqueId().toString()
										+ ".kills"));
				p.sendMessage(ChatColor.GOLD
						+ "Coins: "
						+ plugin.getConfig().getInt(
								"players." + statname.getUniqueId().toString()
										+ ".coins"));
				p.sendMessage(ChatColor.RED
						+ "Deaths: "
						+ plugin.getConfig().getInt(
								"players." + statname.getUniqueId().toString()
										+ ".deaths"));
				double kills = this.plugin.getConfig().getInt(
						"players." + statname.getUniqueId().toString()
								+ ".kills");
				double deaths = this.plugin.getConfig().getInt(
						"players." + statname.getUniqueId().toString()
								+ ".deaths");
				if (deaths == 0.0D) {
					deaths = 1.0D;
				}
				String KD = String.format("%.2f",
						new Object[] { Double.valueOf(kills / deaths) });
				p.sendMessage(ChatColor.AQUA + "K/D Ratio: " + ChatColor.GRAY
						+ Arrays.asList(new String[] { "§7" + KD }));
				p.sendMessage(ChatColor.GREEN
						+ "1v1 Wins: "
						+ plugin.getConfig().getInt(
								"players." + statname.getUniqueId().toString()
										+ ".1v1wins"));
				p.sendMessage(ChatColor.RED
						+ "1v1 Loses: "
						+ plugin.getConfig().getInt(
								"players." + statname.getUniqueId().toString()
										+ ".1v1loses"));
				double wins1 = plugin.getConfig().getInt(
						"players." + statname.getUniqueId().toString()
								+ ".1v1wins");
				double loses1 = plugin.getConfig().getInt(
						"players." + statname.getUniqueId().toString()
								+ ".1v1loses");
				if (loses1 == 0.0D) {
					loses1 = 1.0D;
				}

				String wl = String.format("%.2f",
						new Object[] { Double.valueOf(wins1 / loses1) });
				p.sendMessage(ChatColor.AQUA + "W/L Ratio: " + ChatColor.GRAY
						+ Arrays.asList(new String[] { "§7" + wl }));
				p.sendMessage(ChatColor.YELLOW
						+ ""
						+ ChatColor.STRIKETHROUGH
						+ "----------------------------------------------------");
			}
		}
		return true;
	}
}
