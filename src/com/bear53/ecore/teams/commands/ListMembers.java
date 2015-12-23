package com.bear53.ecore.teams.commands;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.bear53.ecore.teams.SubCommand;
import com.bear53.ecore.teams.TeamManager;

public class ListMembers extends SubCommand {

	public void onCommand(Player p, String[] args) {
		if (TeamManager.getInstance().getTeam(p) == null) {
			p.sendMessage(ChatColor.RED + "You are not on a team!");
			return;
		}

		StringBuilder stb = new StringBuilder(ChatColor.GREEN + "Members: "
				+ ChatColor.AQUA);
		int offline = 0;

		for (String player : TeamManager.getInstance().getTeam(p).getMembers()) {
			Player p1 = Bukkit.getServer().getPlayer(UUID.fromString(player));

			if (p1 != null) {
				stb.append(p1.getName() + "\n ");
			} else {
				offline++;
			}
		}

		stb.append("(" + offline + " offline.)");

		p.sendMessage(stb.toString());
	}

	public ListMembers() {
		super("List all members of your team.", "", "listm", "lm");
	}
}