package com.bear53.ecore.teams.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.bear53.ecore.events.PlayerChatEvent;
import com.bear53.ecore.teams.SubCommand;
import com.bear53.ecore.teams.TeamManager;

public class ToggleTeamChat extends SubCommand {

	public void onCommand(Player p, String[] args) {
		if (TeamManager.getInstance().getTeam(p) == null) {
			p.sendMessage(ChatColor.RED + "You are not on a team!");
			return;
		}

		boolean newValue = !PlayerChatEvent.chatters.contains(p);

		if (args.length > 0) {
			if (args[0].equalsIgnoreCase("true")) {
				newValue = true;
			} else if (args[0].equalsIgnoreCase("false")) {
				newValue = false;
			}
		}

		if (newValue) {
			PlayerChatEvent.chatters.add(p);
		} else {
			PlayerChatEvent.chatters.remove(p);
		}

		p.sendMessage(ChatColor.GREEN + "You are now "
				+ (newValue ? "in" : "not in") + " team chat.");
	}

	public ToggleTeamChat() {
		super("Toggle chatting to team members only.", "[true | false]",
				"chat", "tc");
	}
}