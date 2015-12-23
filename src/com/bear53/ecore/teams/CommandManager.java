package com.bear53.ecore.teams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.bear53.ecore.teams.commands.CreateTeam;
import com.bear53.ecore.teams.commands.JoinTeam;
import com.bear53.ecore.teams.commands.LeaveTeam;
import com.bear53.ecore.teams.commands.ListMembers;
import com.bear53.ecore.teams.commands.ListTeams;
import com.bear53.ecore.teams.commands.ToggleFriendlyFire;
import com.bear53.ecore.teams.commands.ToggleTeamChat;

public class CommandManager implements CommandExecutor {

	private ArrayList<SubCommand> cmds = new ArrayList<SubCommand>();

	public CommandManager() {
		cmds.add(new CreateTeam());
		cmds.add(new JoinTeam());
		cmds.add(new LeaveTeam());
		cmds.add(new ListMembers());
		cmds.add(new ListTeams());
		cmds.add(new ToggleFriendlyFire());
		cmds.add(new ToggleTeamChat());
	}

	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Only players can use Teams!");
			return true;
		}

		Player p = (Player) sender;

		if (cmd.getName().equalsIgnoreCase("team")) {
			if (args.length == 0) {
				p.sendMessage(ChatColor.AQUA
						+ "----------------- [FlameTeams Help] -----------------");
				for (SubCommand c : cmds) {
					p.sendMessage(ChatColor.GRAY + "/team " + ChatColor.YELLOW
							+ aliases(c) + " " + c.getUsage() + " - "
							+ c.getMessage());
				}
				return true;
			}

			SubCommand c = getCommand(args[0]);

			if (c == null) {
				sender.sendMessage(ChatColor.RED
						+ "That command doesn't exist!");
				return true;
			}

			Vector<String> a = new Vector<String>(Arrays.asList(args));
			a.remove(0);
			args = a.toArray(new String[a.size()]);

			c.onCommand(p, args);

			return true;
		}
		return true;
	}

	private String aliases(SubCommand cmd) {
		String fin = "";

		for (String a : cmd.getAliases()) {
			fin += a + " | ";
		}

		return fin.substring(0, fin.lastIndexOf(" | "));
	}

	private SubCommand getCommand(String name) {
		for (SubCommand cmd : cmds) {
			if (cmd.getClass().getSimpleName().equalsIgnoreCase(name))
				return cmd;
			for (String alias : cmd.getAliases())
				if (name.equalsIgnoreCase(alias))
					return cmd;
		}
		return null;
	}
}