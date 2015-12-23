package com.bear53.ecore.teams;

import java.util.ArrayList;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class TeamManager {

	private TeamManager() {
	}

	private static TeamManager instance = new TeamManager();

	public static TeamManager getInstance() {
		return instance;
	}

	private ArrayList<Team> teams;

	public void setup() {
		teams = new ArrayList<Team>();

		if (!SettingsManager.getInstance().contains("teams")) {
			SettingsManager.getInstance().createConfigurationSection("teams");
		}

		for (String teamName : SettingsManager.getInstance()
				.<ConfigurationSection> get("teams").getKeys(false)) {
			teams.add(new Team(teamName, SettingsManager.getInstance()
					.<ConfigurationSection> get("teams." + teamName)
					.getValues(true)));
		}
	}

	public Team[] getTeams() {
		return teams.toArray(new Team[teams.size()]);
	}

	public void addTeam(Team team) {
		teams.add(team);
		team.save();
	}

	public void removeTeam(Team team) {
		teams.remove(team);
		SettingsManager.getInstance().set("teams." + team.getName(), null);
	}

	public Team getTeam(String name) {
		for (Team team : teams) {
			if (team.getName().equals(name)) {
				return team;
			}
		}
		return null;
	}

	public Team getTeam(Player player) {
		for (Team team : teams) {
			if (team.hasMember(player)) {
				return team;
			}
		}
		return null;
	}
}