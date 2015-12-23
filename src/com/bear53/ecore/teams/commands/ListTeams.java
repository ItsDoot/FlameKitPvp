package com.bear53.ecore.teams.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.bear53.ecore.teams.SubCommand;
import com.bear53.ecore.teams.Team;
import com.bear53.ecore.teams.TeamManager;

public class ListTeams extends SubCommand {

	public void onCommand(Player p, String[] args) {
		StringBuilder stb = new StringBuilder(ChatColor.GREEN + "");

		for (Team t : TeamManager.getInstance().getTeams()) {
			stb.append(t.getName() + "\n ");
		}
		p.sendMessage(ChatColor.AQUA
				+ "---------------- [FlameTeams Teams] ----------------");
		p.sendMessage(stb.toString());
	}

	public ListTeams() {
		super("List all teams.", "", "list", "l");
	}
}