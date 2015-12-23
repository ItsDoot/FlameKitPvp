package com.bear53.ecore.teams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;

public class Team implements ConfigurationSerializable {

	private String name;
	private ArrayList<String> members;
	private boolean friendlyFire;

	public Team(String name) {
		this.name = name;
		this.members = new ArrayList<String>();
	}
	@SuppressWarnings("unchecked")
	public Team(String name, Map<String, Object> map) {
		this.name = name;
		this.members = new ArrayList<String>((List<String>) map.get("members"));
		this.friendlyFire = (Boolean) map.get("friendlyFire");
	}

	public String getName() {
		return name;
	}

	public boolean allowFriendlyFire() {
		return friendlyFire;
	}

	public void setFriendlyFireAllowed(boolean friendlyFire) {
		this.friendlyFire = friendlyFire;
	}

	public boolean hasMember(Player member) {
		return members.contains(member.getUniqueId().toString());
	}

	public void addMember(Player member) {
		members.add(member.getUniqueId().toString());
		save();
	}

	public void removeMember(Player member) {
		members.remove(member.getUniqueId().toString());
		save();
	}

	public String[] getMembers() {
		return members.toArray(new String[members.size()]);
	}

	@Override
	public Map<String, Object> serialize() {
		HashMap<String, Object> map = new HashMap<String, Object>();

		map.put("members", members);
		map.put("friendlyFire", friendlyFire);

		return map;
	}

	public void save() {
		SettingsManager.getInstance().set("teams." + getName(), serialize());
	}
}