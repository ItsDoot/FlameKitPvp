package com.bear53.ecore.teams;

import java.io.File;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class SettingsManager {

	private SettingsManager() {
	}

	private static SettingsManager instance = new SettingsManager();

	public static SettingsManager getInstance() {
		return instance;
	}

	public void setup(Plugin p) {
		if (!p.getDataFolder().exists())
			p.getDataFolder().mkdir();

		file = new File(p.getDataFolder(), "teams.yml");

		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		config = YamlConfiguration.loadConfiguration(file);
	}

	private File file;
	private FileConfiguration config;

	public void set(String path, Object value) {
		config.set(path, value);
		save();
	}

	public ConfigurationSection createConfigurationSection(String path) {
		ConfigurationSection cs = config.createSection(path);
		save();
		return cs;
	}

	@SuppressWarnings("unchecked")
	public <T> T get(String path) {
		return (T) config.get(path);
	}

	public boolean contains(String path) {
		return config.contains(path);
	}

	public void save() {
		try {
			config.save(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}