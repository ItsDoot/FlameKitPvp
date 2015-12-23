package com.bear53.ecore.teams.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.bear53.ecore.teams.SubCommand;
import com.bear53.ecore.teams.TeamManager;

public class ToggleFriendlyFire extends SubCommand {

	public void onCommand(Player p, String[] args) {
		if (TeamManager.getInstance().getTeam(p) == null) {
			p.sendMessage(ChatColor.RED + "You are not on a team!");
			return;
		}

		boolean newValue = !TeamManager.getInstance().getTeam(p)
				.allowFriendlyFire();

		if (args.length > 0) {
			if (args[0].equalsIgnoreCase("true")) {
				newValue = true;
			} else if (args[0].equalsIgnoreCase("false")) {
				newValue = false;
			}
		}

		TeamManager.getInstance().getTeam(p).setFriendlyFireAllowed(newValue);
		p.sendMessage(ChatColor.GREEN + "Friendly fire is now "
				+ (newValue ? "" : "not ") + "allowed.");
	}

	public ToggleFriendlyFire() {
		super("Toggle the ability for teammates to harm each other.",
				"[true | false]", "toggle", "tff", "ff", "friendlyfire");
	}
}