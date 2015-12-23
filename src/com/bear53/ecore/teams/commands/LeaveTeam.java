package com.bear53.ecore.teams.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.bear53.ecore.teams.SubCommand;
import com.bear53.ecore.teams.Team;
import com.bear53.ecore.teams.TeamManager;

public class LeaveTeam extends SubCommand {

	public void onCommand(Player p, String[] args) {
		if (TeamManager.getInstance().getTeam(p) == null) {
			p.sendMessage(ChatColor.RED + "You are not on a team!");
			return;
		}

		Team old = TeamManager.getInstance().getTeam(p);
		old.removeMember(p);
		p.sendMessage(ChatColor.GREEN + "You have left team " + old.getName()
				+ "!");
		if (old.getMembers().length == 0) {
			Bukkit.broadcastMessage(ChatColor.GRAY + "The team "
					+ ChatColor.AQUA + old.getName() + ChatColor.GRAY
					+ " has been disbanded!");

			TeamManager.getInstance().removeTeam(old);
		}
	}

	public LeaveTeam() {
		super("Leave a team.", "", "leave");
	}
}