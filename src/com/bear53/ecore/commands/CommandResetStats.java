package com.bear53.ecore.commands;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import com.bear53.ecore.Core;
import com.bear53.ecore.koth.HillMoveEvent;

public class CommandResetStats implements CommandExecutor {

	Core plugin;

	public CommandResetStats(Core pl) {
		this.plugin = pl;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED
					+ "Only players can use this command!");
		}
		List<String> owners = plugin.getConfig().getStringList("owners");
		if (owners.contains(sender.getName())) {
			if (args.length == 0) {
				sender.sendMessage(ChatColor.RED
						+ "Invalid arguments! Usage: /reset <player>");
			} else if (args.length == 1) {
				Player p = Bukkit.getServer().getPlayer(args[0]);
				if (p == null) {
					sender.sendMessage(ChatColor.RED + "Error: The player '"
							+ args[0] + "' cannot be found.");
				} else {
					plugin.getConfig().set(
							"players." + p.getUniqueId().toString() + ".name",
							p.getName().toString());
					plugin.getConfig().set(
							"players." + p.getUniqueId().toString()
									+ ".isBanned", Boolean.valueOf(false));
					plugin.getConfig().set(
							"players." + p.getUniqueId().toString()
									+ ".bannedReason", "");
					plugin.getConfig().set(
							"players." + p.getUniqueId().toString()
									+ ".bannedBy", "");
					plugin.getConfig().set(
							"players." + p.getUniqueId().toString()
									+ ".isMuted", Boolean.valueOf(false));
					plugin.getConfig().set(
							"players." + p.getUniqueId().toString() + ".coins",
							Integer.valueOf(100));
					plugin.getConfig().set(
							"players." + p.getUniqueId().toString() + ".kills",
							Integer.valueOf(0));
					plugin.getConfig()
							.set("players." + p.getUniqueId().toString()
									+ ".deaths", Integer.valueOf(0));
					plugin.getConfig().set(
							"players." + p.getUniqueId().toString()
									+ ".killstreak", Integer.valueOf(0));
					plugin.getConfig().set(
							"players." + p.getUniqueId().toString()
									+ ".curkillstreak", Integer.valueOf(0));
					plugin.getConfig().set(
							"players." + p.getUniqueId().toString()
									+ ".1v1wins", Integer.valueOf(0));
					plugin.getConfig().set(
							"players." + p.getUniqueId().toString()
									+ ".1v1loses", Integer.valueOf(0));
					plugin.getConfig().set(
							"players." + p.getUniqueId().toString() + ".level",
							Integer.valueOf(1));
					plugin.saveConfig();
					if (sender.getName() == p.getName()) {
						sender.sendMessage(ChatColor.GREEN
								+ "You have reset your stats!");
					} else {
						sender.sendMessage(ChatColor.GREEN + "You have reset "
								+ p.getName() + "'s stats!");
						p.sendMessage(ChatColor.GREEN
								+ "Your stats have been reset by "
								+ sender.getName() + "!");
					}
					for (Player o : Bukkit.getServer().getOnlinePlayers()) {
						ScoreboardManager manager = Bukkit
								.getScoreboardManager();
						Scoreboard board = manager.getNewScoreboard();

						Objective obj = board.registerNewObjective("Test",
								"Test2");
						obj.setDisplayName("§8> §4§nFlameNetwork§r §8<");
						obj.setDisplaySlot(DisplaySlot.SIDEBAR);
						Score score = obj.getScore("§8» §aKills§7: §7"
								+ plugin.getConfig().getInt(
										"players." + o.getUniqueId().toString()
												+ ".kills"));
						score.setScore(12);

						Score score2 = obj.getScore("§8» §aDeaths§7: §7"
								+ plugin.getConfig().getInt(
										"players." + o.getUniqueId().toString()
												+ ".deaths"));
						score2.setScore(11);

						double kills = plugin.getConfig().getInt(
								"players." + o.getUniqueId().toString()
										+ ".kills");
						double deaths = plugin.getConfig().getInt(
								"players." + o.getUniqueId().toString()
										+ ".deaths");
						if (deaths == 0.0D) {
							deaths = 1.0D;
						}
						String KD = String
								.format("%.2f",
										new Object[] { Double.valueOf(kills
												/ deaths) });

						Score kd = obj.getScore("§8» §aK/D§7: §7"
								+ Arrays.asList(new String[] { "§7" + KD }));
						kd.setScore(10);

						Score curks = obj.getScore("§8» §aKill Streak§8: §7"
								+ plugin.getConfig().getInt(
										"players." + o.getUniqueId().toString()
												+ ".curkillstreak"));
						curks.setScore(9);
						Score bl2 = obj.getScore(" ");
						bl2.setScore(8);

						Score level = obj.getScore("§8» §bLevel§7: §7"
								+ plugin.getConfig().getInt(
										"players." + o.getUniqueId().toString()
												+ ".level"));
						level.setScore(7);
						Score killsneeded = obj
								.getScore("§8» §bKills Needed§7: 5");
						killsneeded.setScore(6);
						Score score4 = obj.getScore("§8» §3Coins§7: "
								+ plugin.getConfig().getInt(
										"players." + o.getUniqueId().toString()
												+ ".coins"));
						score4.setScore(5);

						Score bl3 = obj.getScore("");
						bl3.setScore(4);

						Score s5 = obj.getScore("§8» §a1v1 Wins§7: "
								+ plugin.getConfig().getInt(
										"players." + o.getUniqueId().toString()
												+ ".1v1wins"));
						s5.setScore(3);

						Score s6 = obj.getScore("§8» §a1v1 Loses§7: "
								+ plugin.getConfig().getInt(
										"players." + o.getUniqueId().toString()
												+ ".1v1loses"));
						s6.setScore(2);

						if (HillMoveEvent.win.get(o.getName()) != null) {
							Integer pt = HillMoveEvent.win.get(o.getName());
							Score hilly = obj.getScore("§8» §aHill Points§7: "
									+ pt);
							hilly.setScore(1);
						} else {
							Score hilly = obj
									.getScore("§8» §aHill Points§7: 0");
							hilly.setScore(1);
						}

						o.setScoreboard(board);
					}
				}
			} else {
				sender.sendMessage(ChatColor.RED
						+ "Invalid arguments! Usage: /reset <player>");
			}
		} else {
			sender.sendMessage(ChatColor.RED + "No permission.");
		}
		return true;

	}
}
