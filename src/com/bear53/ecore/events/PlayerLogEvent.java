package com.bear53.ecore.events;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import com.bear53.ecore.Core;
import com.bear53.ecore.commands.CommandSpawn;
import com.bear53.ecore.kitpvp.KitPvp;
import com.bear53.ecore.koth.HillMoveEvent;

public class PlayerLogEvent implements Listener {

	Core plugin;

	public PlayerLogEvent(Core pl) {
		this.plugin = pl;
	}

	@EventHandler
	public void onLog(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		if (CommandSpawn.move.contains(p)) {
			CommandSpawn.move.remove(p);
		}
		if (KitPvp.activeKit.contains(p.getName())) {
			KitPvp.activeKit.remove(p.getName());
		}
		if (KitPvp.scorp.contains(p.getName())) {
			KitPvp.scorp.remove(p.getName());
		}
		e.setQuitMessage(null);
		Bukkit.broadcastMessage(ChatColor.DARK_GRAY + "[" + ChatColor.RED + "-"
				+ ChatColor.DARK_GRAY + "] " + ChatColor.GRAY + p.getName());

		if (HillMoveEvent.win.containsKey(p.getName())) {
			HillMoveEvent.win.remove(p.getName());
		}
		HillMoveEvent.activePlayer = "none";
		for (Player o : Bukkit.getServer().getOnlinePlayers()) {
			ScoreboardManager manager = Bukkit.getScoreboardManager();
			Scoreboard board = manager.getNewScoreboard();

			Objective obj = board.registerNewObjective("Test", "Test2");
			obj.setDisplayName("§8> §4§nFlameNetwork§r §8<");
			obj.setDisplaySlot(DisplaySlot.SIDEBAR);
			Score score = obj
					.getScore("§8» §aKills§7: §7"
							+ plugin.getConfig().getInt(
									"players." + o.getUniqueId().toString()
											+ ".kills"));
			score.setScore(12);

			Score score2 = obj.getScore("§8» §aDeaths§7: §7"
					+ plugin.getConfig()
							.getInt("players." + o.getUniqueId().toString()
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

			Score level = obj
					.getScore("§8» §bLevel§7: §7"
							+ plugin.getConfig().getInt(
									"players." + o.getUniqueId().toString()
											+ ".level"));
			level.setScore(7);
			Score killsneeded = obj.getScore("§8» §bKills Needed§7: 5");
			killsneeded.setScore(6);
			Score score4 = obj
					.getScore("§8» §3Coins§7: "
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
