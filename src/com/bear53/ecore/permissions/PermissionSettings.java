package com.bear53.ecore.permissions;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.Plugin;

public class PermissionSettings {

	private HashMap<UUID, PermissionAttachment> attachments = new HashMap<UUID, PermissionAttachment>();

	private Plugin p;
	private FileConfiguration config;
	private File cfile;

	public PermissionSettings() {
	}

	private static PermissionSettings instance = new PermissionSettings();

	public static PermissionSettings getInstance() {
		return instance;
	}

	public void setupPermissions(Plugin p) {
		this.p = p;

		if (!p.getDataFolder().exists()) {
			p.getDataFolder().mkdirs();
		}

		cfile = new File(p.getDataFolder(), "permissions.yml");

		if (!cfile.exists()) {
			try {
				cfile.createNewFile();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		config = YamlConfiguration.loadConfiguration(cfile);
	}

	public void addPerm(Player player, String perm) {
		UUID uuid = player.getUniqueId();
		if (player != null) {
			attachments.get(uuid).setPermission(perm, true);
			if (config.contains("players." + uuid)) {
				List<String> perms = config.getStringList("players." + uuid
						+ ".perms");
				perms.add(perm);
				config.set("players." + uuid + ".perms", null);
				config.set("players." + uuid + ".perms", perms);
			} else {
				config.set("players." + uuid + ".perms",
						new ArrayList<String>());
				config.set("players." + uuid + ".name", player.getName());
			}
		}
		save();
		p.getLogger().info(
				"Injected Permissions " + perm + " to " + player.getName());
	}

	public void removePerm(Player player, String perm) {
		UUID uuid = player.getUniqueId();
		if (player != null) {
			attachments.get(uuid).setPermission(perm, false);
			if (config.contains("players." + uuid)) {
				List<String> perms = config.getStringList("players." + uuid
						+ ".perms");
				perms.remove(perm);
				config.set("players." + uuid + ".perms", null);
				config.set("players." + uuid + ".perms", perms);
			} else {
				config.set("players." + uuid + ".perms",
						new ArrayList<String>());
				config.set("players." + uuid + ".name", player.getName());
			}
		}
		save();
		p.getLogger().info(
				"Uninjected Permissions " + perm + " to " + player.getName());
	}

	public List<String> getPerms(Player player) {
		UUID uuid = player.getUniqueId();
		if (config.contains("players." + uuid)) {
			List<String> perms = config.getStringList("players." + uuid
					+ ".perms");
			return perms;
		} else {
			config.set("players." + uuid + ".perms", new ArrayList<String>());
			config.set("players." + uuid + ".name", player.getName());
			save();
			List<String> perms = config.getStringList("players." + uuid
					+ ".perms");
			return perms;
		}
	}

	public void injectPlayer(Player pl) {
		attachments.put(pl.getUniqueId(), pl.addAttachment(p));
		for (String perms : getPerms(pl)) {
			attachments.get(pl.getUniqueId()).setPermission(perms, true);
		}
	}

	public void uninjectPlayer(Player pl) {
		pl.removeAttachment(attachments.get(pl.getUniqueId()));
		attachments.remove(pl.getUniqueId());
	}

	private void save() {
		try {
			config.save(cfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Plugin getPlugin() {
		return p;
	}
}
