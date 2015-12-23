package com.bear53.ecore.teams.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.bear53.ecore.teams.SubCommand;
import com.bear53.ecore.teams.TeamManager;

public class JoinTeam extends SubCommand {

	public void onCommand(Player p, String[] args) {
		if (TeamManager.getInstance().getTeam(p) != null) {
			p.sendMessage(ChatColor.RED + "You already have a team!");
			return;
		}

		if (args.length == 0) {
			p.sendMessage(ChatColor.RED + "Error: You must specify a team!");
			return;
		}

		String team = args[0];

		if (TeamManager.getInstance().getTeam(team) == null) {
			p.sendMessage(ChatColor.RED + "That team does not exist!");
			return;
		}

		TeamManager.getInstance().getTeam(team).addMember(p);
		p.sendMessage(ChatColor.GREEN + "You have joined team " + team + "!");
	}

	public JoinTeam() {
		super("Join a team.", "<team>", "join", "j");
	}
}