package com.bear53.ecore.teams.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.bear53.ecore.teams.SubCommand;
import com.bear53.ecore.teams.Team;
import com.bear53.ecore.teams.TeamManager;

public class CreateTeam extends SubCommand {

	public void onCommand(Player p, String[] args) {
		if (TeamManager.getInstance().getTeam(p) != null) {
			p.sendMessage(ChatColor.RED + "You already have a team!");
			return;
		}

		if (args.length == 0) {
			p.sendMessage(ChatColor.RED + "You must specify a name!");
			return;
		}

		String name = args[0];
		if (TeamManager.getInstance().getTeam(name) != null) {
			p.sendMessage(ChatColor.RED + "That team already exists!");
			return;
		}

		Team team = new Team(name);
		TeamManager.getInstance().addTeam(team);
		team.addMember(p);
		p.sendMessage(ChatColor.GREEN + "Created team " + name + "!");
		Bukkit.broadcastMessage(ChatColor.AQUA + p.getName() + ChatColor.GRAY
				+ " Has created the team " + ChatColor.AQUA + team.getName());
	}

	public CreateTeam() {
		super("Create a team.", "<name>", "create", "ct", "new");
	}
}