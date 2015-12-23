package com.bear53.ecore.koth;

import java.util.Arrays;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import com.bear53.ecore.ActionBar;
import com.bear53.ecore.Core;

public class HillMoveEvent implements Listener {

	Core plugin;

	public HillMoveEvent(Core pl) {
		this.plugin = pl;
	}

	public static int task1;

	public static Boolean active = false;

	public static String activePlayer;

	public static HashMap<String, Integer> win = new HashMap<String, Integer>();

	@EventHandler
	public void onHillMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if (this.plugin.locu.isWithinHill(p.getLocation())) {
			if (win.isEmpty()) {
				activePlayer = p.getName();
				win.put(p.getName(), 0);
				for (Player online : Bukkit.getOnlinePlayers()) {
					ActionBar.sendActionbar(online, ChatColor.GOLD + "The "
							+ ChatColor.AQUA + "Castle " + ChatColor.GOLD
							+ "has been " + ChatColor.AQUA + "captured"
							+ ChatColor.GOLD + " by " + p.getName());
				}
				// Bukkit.getServer().broadcastMessage(
				// ChatColor.GRAY + "The " + ChatColor.AQUA + "Castle "
				// + ChatColor.GRAY + "has been " + ChatColor.AQUA
				// + "captured" + ChatColor.GRAY + " by "
				// + p.getName());
				addPoints(p);
				active = true;
				this.plugin.locu.capturedWool();
			} else if (!win.isEmpty() && (!win.containsKey(p.getName()))) {
				Bukkit.getServer().getScheduler().cancelTask(task1);
				this.plugin.locu.contestedWool();
				ActionBar.sendActionbar(p, ChatColor.GOLD
						+ "Hill Contested by " + ChatColor.DARK_AQUA
						+ activePlayer);
				ActionBar.sendActionbar(Bukkit.getPlayer(activePlayer),
						ChatColor.GOLD + "Hill Contested by "
								+ ChatColor.DARK_AQUA + p.getName());
				if (!this.plugin.locu.isWithinHill(p.getLocation())) {
					addPoints(Bukkit.getPlayer(activePlayer));
					active = false;
				}
			}
		} else if (win.containsKey(p.getName())) {
			win.remove(p.getName());
			p.sendMessage(ChatColor.RED
					+ "Left the Castle! You will no longer gain points from it!");
			for (Player online : Bukkit.getOnlinePlayers()) {
				ActionBar.sendActionbar(online, ChatColor.GOLD + "The "
						+ ChatColor.AQUA + "Castle " + ChatColor.GOLD
						+ "has been deserted!");
			}
			// Bukkit.getServer().broadcastMessage(
			// ChatColor.GRAY + "The " + ChatColor.AQUA + "Castle "
			// + ChatColor.GRAY + "has been deserted!");
			Bukkit.getServer().getScheduler().cancelTask(task1);
			active = false;
			activePlayer = "None";
			this.plugin.locu.neutralWool();
			for (Player o : Bukkit.getServer().getOnlinePlayers()) {
				ScoreboardManager manager = Bukkit.getScoreboardManager();
				Scoreboard board = manager.getNewScoreboard();

				Objective obj = board.registerNewObjective("Test", "Test2");
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
						"players." + o.getUniqueId().toString() + ".kills");
				double deaths = plugin.getConfig().getInt(
						"players." + o.getUniqueId().toString() + ".deaths");
				if (deaths == 0.0D) {
					deaths = 1.0D;
				}
				String KD = String.format("%.2f",
						new Object[] { Double.valueOf(kills / deaths) });

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
				Score killsneeded = obj.getScore("§8» §bKills Needed§7: 5");
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
					Score hilly = obj.getScore("§8» §aHill Points§7: " + pt);
					hilly.setScore(1);
				} else {
					Score hilly = obj.getScore("§8» §aHill Points§7: 0");
					hilly.setScore(1);
				}

				o.setScoreboard(board);
			}
		}
	}

	public void addPoints(final Player p) {
		if (!active) {
			task1 = Bukkit.getServer().getScheduler()
					.scheduleSyncRepeatingTask(plugin, new Runnable() {
						public void run() {
							if (win.get(p.getName()) == null) {
								win.put(p.getName(), 1);
							} else {
								Integer point = win.get(p.getName());
								win.put(p.getName(), point + 1);

								for (Player o : Bukkit.getServer()
										.getOnlinePlayers()) {
									ScoreboardManager manager = Bukkit
											.getScoreboardManager();
									Scoreboard board = manager
											.getNewScoreboard();

									Objective obj = board.registerNewObjective(
											"Test", "Test2");
									obj.setDisplayName("§8> §4§nFlameNetwork§r §8<");
									obj.setDisplaySlot(DisplaySlot.SIDEBAR);
									Score score = obj
											.getScore("§8» §aKills§7: §7"
													+ plugin.getConfig()
															.getInt("players."
																	+ o.getUniqueId()
																			.toString()
																	+ ".kills"));
									score.setScore(12);

									Score score2 = obj
											.getScore("§8» §aDeaths§7: §7"
													+ plugin.getConfig()
															.getInt("players."
																	+ o.getUniqueId()
																			.toString()
																	+ ".deaths"));
									score2.setScore(11);

									double kills = plugin.getConfig().getInt(
											"players."
													+ o.getUniqueId()
															.toString()
													+ ".kills");
									double deaths = plugin.getConfig().getInt(
											"players."
													+ o.getUniqueId()
															.toString()
													+ ".deaths");
									if (deaths == 0.0D) {
										deaths = 1.0D;
									}
									String KD = String.format(
											"%.2f",
											new Object[] { Double.valueOf(kills
													/ deaths) });

									Score kd = obj.getScore("§8» §aK/D§7: §7"
											+ Arrays.asList(new String[] { "§7"
													+ KD }));
									kd.setScore(10);

									Score curks = obj
											.getScore("§8» §aKill Streak§8: §7"
													+ plugin.getConfig()
															.getInt("players."
																	+ o.getUniqueId()
																			.toString()
																	+ ".curkillstreak"));
									curks.setScore(9);
									Score bl2 = obj.getScore(" ");
									bl2.setScore(8);

									Score level = obj
											.getScore("§8» §bLevel§7: §7"
													+ plugin.getConfig()
															.getInt("players."
																	+ o.getUniqueId()
																			.toString()
																	+ ".level"));
									level.setScore(7);
									Score killsneeded = obj
											.getScore("§8» §bKills Needed§7: 5");
									killsneeded.setScore(6);
									Score score4 = obj
											.getScore("§8» §3Coins§7: "
													+ plugin.getConfig()
															.getInt("players."
																	+ o.getUniqueId()
																			.toString()
																	+ ".coins"));
									score4.setScore(5);

									Score bl3 = obj.getScore("");
									bl3.setScore(4);

									Score s5 = obj
											.getScore("§8» §a1v1 Wins§7: "
													+ plugin.getConfig()
															.getInt("players."
																	+ o.getUniqueId()
																			.toString()
																	+ ".1v1wins"));
									s5.setScore(3);

									Score s6 = obj
											.getScore("§8» §a1v1 Loses§7: "
													+ plugin.getConfig()
															.getInt("players."
																	+ o.getUniqueId()
																			.toString()
																	+ ".1v1loses"));
									s6.setScore(2);

									if (HillMoveEvent.win.get(o.getName()) != null) {
										Integer pt = HillMoveEvent.win.get(o
												.getName());
										Score hilly = obj
												.getScore("§8» §aHill Points§7: "
														+ pt);
										hilly.setScore(1);
									} else {
										Score hilly = obj
												.getScore("§8» §aHill Points§7: 0");
										hilly.setScore(1);
									}

									o.setScoreboard(board);
								}
								if (point == 60) {
									p.sendMessage(ChatColor.YELLOW
											+ "You have held the hill for 1 minute!");
									Integer previous = Integer.valueOf(plugin
											.getConfig().getInt(
													"players."
															+ p.getUniqueId()
																	.toString()
															+ ".coins"));
									plugin.getConfig().set(
											"players."
													+ p.getUniqueId()
															.toString()
													+ ".coins",
											previous.intValue() + 100);
									plugin.saveConfig();
									p.sendMessage(ChatColor.GREEN
											+ "Your coins are now "
											+ ChatColor.GOLD
											+ plugin.getConfig().getInt(
													"players."
															+ p.getUniqueId()
																	.toString()
															+ ".coins"));
								} else if (point == 180) {
									p.sendMessage(ChatColor.YELLOW
											+ "You have held the hill for 3 minutes!");
									Integer previous = Integer.valueOf(plugin
											.getConfig().getInt(
													"players."
															+ p.getUniqueId()
																	.toString()
															+ ".coins"));
									plugin.getConfig().set(
											"players."
													+ p.getUniqueId()
															.toString()
													+ ".coins",
											previous.intValue() + 100);
									plugin.saveConfig();
									p.sendMessage(ChatColor.GREEN
											+ "Your coins are now "
											+ ChatColor.GOLD
											+ plugin.getConfig().getInt(
													"players."
															+ p.getUniqueId()
																	.toString()
															+ ".coins"));
								} else if (point == 300) {
									p.sendMessage(ChatColor.YELLOW
											+ "You have held the hill for 5 minutes!");
									Integer previous = Integer.valueOf(plugin
											.getConfig().getInt(
													"players."
															+ p.getUniqueId()
																	.toString()
															+ ".coins"));
									plugin.getConfig().set(
											"players."
													+ p.getUniqueId()
															.toString()
													+ ".coins",
											previous.intValue() + 100);
									plugin.saveConfig();
									p.sendMessage(ChatColor.GREEN
											+ "Your coins are now "
											+ ChatColor.GOLD
											+ plugin.getConfig().getInt(
													"players."
															+ p.getUniqueId()
																	.toString()
															+ ".coins"));
								} else if (point == 600) {
									p.sendMessage(ChatColor.YELLOW
											+ "You have held the hill for 10 minutes!");
									Integer previous = Integer.valueOf(plugin
											.getConfig().getInt(
													"players."
															+ p.getUniqueId()
																	.toString()
															+ ".coins"));
									plugin.getConfig().set(
											"players."
													+ p.getUniqueId()
															.toString()
													+ ".coins",
											previous.intValue() + 100);
									plugin.saveConfig();
									p.sendMessage(ChatColor.GREEN
											+ "Your coins are now "
											+ ChatColor.GOLD
											+ plugin.getConfig().getInt(
													"players."
															+ p.getUniqueId()
																	.toString()
															+ ".coins"));
								} else if (point == 900) {
									p.sendMessage(ChatColor.YELLOW
											+ "You have held the hill for 15 minutes!");
									Integer previous = Integer.valueOf(plugin
											.getConfig().getInt(
													"players."
															+ p.getUniqueId()
																	.toString()
															+ ".coins"));
									plugin.getConfig().set(
											"players."
													+ p.getUniqueId()
															.toString()
													+ ".coins",
											previous.intValue() + 100);
									plugin.saveConfig();
									p.sendMessage(ChatColor.GREEN
											+ "Your coins are now "
											+ ChatColor.GOLD
											+ plugin.getConfig().getInt(
													"players."
															+ p.getUniqueId()
																	.toString()
															+ ".coins"));
								} else if (point == 1200) {
									p.sendMessage(ChatColor.YELLOW
											+ "You have held the hill for 20 minutes!");
									Integer previous = Integer.valueOf(plugin
											.getConfig().getInt(
													"players."
															+ p.getUniqueId()
																	.toString()
															+ ".coins"));
									plugin.getConfig().set(
											"players."
													+ p.getUniqueId()
															.toString()
													+ ".coins",
											previous.intValue() + 100);
									plugin.saveConfig();
									p.sendMessage(ChatColor.GREEN
											+ "Your coins are now "
											+ ChatColor.GOLD
											+ plugin.getConfig().getInt(
													"players."
															+ p.getUniqueId()
																	.toString()
															+ ".coins"));
								} else if (point == 1500) {
									p.sendMessage(ChatColor.YELLOW
											+ "You have held the hill for 25 minutes!");
									Integer previous = Integer.valueOf(plugin
											.getConfig().getInt(
													"players."
															+ p.getUniqueId()
																	.toString()
															+ ".coins"));
									plugin.getConfig().set(
											"players."
													+ p.getUniqueId()
															.toString()
													+ ".coins",
											previous.intValue() + 100);
									plugin.saveConfig();
									p.sendMessage(ChatColor.GREEN
											+ "Your coins are now "
											+ ChatColor.GOLD
											+ plugin.getConfig().getInt(
													"players."
															+ p.getUniqueId()
																	.toString()
															+ ".coins"));
								} else if (point == 1800) {
									p.sendMessage(ChatColor.YELLOW
											+ "You have held the hill for 30 minutes! Resetting time!");
									Integer previous = Integer.valueOf(plugin
											.getConfig().getInt(
													"players."
															+ p.getUniqueId()
																	.toString()
															+ ".coins"));
									plugin.getConfig().set(
											"players."
													+ p.getUniqueId()
															.toString()
													+ ".coins",
											previous.intValue() + 100);
									plugin.saveConfig();
									p.sendMessage(ChatColor.GREEN
											+ "Your coins are now "
											+ ChatColor.GOLD
											+ plugin.getConfig().getInt(
													"players."
															+ p.getUniqueId()
																	.toString()
															+ ".coins"));
									win.put(p.getName(), 0);
								}
							}
						}
					}, 20, 20);
		}
	}
}
