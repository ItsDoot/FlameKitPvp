package com.bear53.ecore.koth;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import com.bear53.ecore.Core;

public class LocationUtils {

	Core plugin;

	public LocationUtils(Core pl) {
		this.plugin = pl;
	}

	public void setHill(String name, String w, double x, double y, double z) {
		plugin.getConfig().set("Hills." + name + ".world", w);
		plugin.getConfig().set("Hills." + name + ".x", x);
		plugin.getConfig().set("Hills." + name + ".y", y);
		plugin.getConfig().set("Hills." + name + ".z", z);
		plugin.saveConfig();
	}

	public boolean isWithinHill(Location loc) {
		World w = Bukkit.getWorld(this.plugin.getConfig().getString(
				"Hills." + "castle" + ".world"));
		double x = this.plugin.getConfig()
				.getDouble("Hills." + "castle" + ".x");
		double y = this.plugin.getConfig()
				.getDouble("Hills." + "castle" + ".y");
		double z = this.plugin.getConfig()
				.getDouble("Hills." + "castle" + ".z");
		if (w != null) {
			Location loc2 = new Location(w, x, y, z);
			Location hill1 = loc2;
			boolean isWithin = false;
			if ((loc.getBlockX() > hill1.getBlockX() - 10)
					&& (loc.getBlockX() < hill1.getBlockX() + 10)
					&& (loc.getBlockY() > hill1.getBlockY() - 10)
					&& (loc.getBlockY() < hill1.getBlockY() + 10)
					&& (loc.getBlockZ() > hill1.getBlockZ() - 10)
					&& (loc.getBlockZ() < hill1.getBlockZ() + 10)) {
				isWithin = true;
			}
			return isWithin;
		}
		return false;
	}

	@SuppressWarnings("deprecation")
	public void contestedWool() {
		World w = Bukkit.getWorld(this.plugin.getConfig().getString(
				"Hills." + "castle" + ".world"));
		double x = this.plugin.getConfig()
				.getDouble("Hills." + "castle" + ".x");
		double y = this.plugin.getConfig()
				.getDouble("Hills." + "castle" + ".y");
		double z = this.plugin.getConfig()
				.getDouble("Hills." + "castle" + ".z");
		Location loc2 = new Location(w, x, y, z);
		Location hill = loc2;
		int hillX = hill.getBlockX();
		int hillY = hill.getBlockY();
		int hillZ = hill.getBlockZ();

		int bigX = hillX + 10;
		int bigY = hillY + 10;
		int bigZ = hillZ + 10;
		byte by = 14;
		byte glass = 14;
		for (int smallX = hillX - 10; smallX < bigX; smallX++) {
			for (int smallY = hillY - 10; smallY < bigY; smallY++) {
				for (int smallZ = hillZ - 10; smallZ < bigZ; smallZ++) {
					Block b = hill.getWorld()
							.getBlockAt(smallX, smallY, smallZ);
					if (b.getType() == Material.WOOL) {
						hill.getWorld().getBlockAt(smallX, smallY, smallZ)
								.setData(by);
					}
					Block s = hill.getWorld()
							.getBlockAt(smallX, smallY, smallZ);
					if (s.getType() == Material.STAINED_GLASS) {
						hill.getWorld().getBlockAt(smallX, smallY, smallZ)
								.setData(glass, true);
					}
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	public void capturedWool() {
		World w = Bukkit.getWorld(this.plugin.getConfig().getString(
				"Hills." + "castle" + ".world"));
		double x = this.plugin.getConfig()
				.getDouble("Hills." + "castle" + ".x");
		double y = this.plugin.getConfig()
				.getDouble("Hills." + "castle" + ".y");
		double z = this.plugin.getConfig()
				.getDouble("Hills." + "castle" + ".z");
		Location loc2 = new Location(w, x, y, z);
		Location hill = loc2;
		int hillX = hill.getBlockX();
		int hillY = hill.getBlockY();
		int hillZ = hill.getBlockZ();

		int bigX = hillX + 10;
		int bigY = hillY + 10;
		int bigZ = hillZ + 10;
		byte by = 5;
		byte glass = 5;
		for (int smallX = hillX - 10; smallX < bigX; smallX++) {
			for (int smallY = hillY - 10; smallY < bigY; smallY++) {
				for (int smallZ = hillZ - 10; smallZ < bigZ; smallZ++) {
					Block b = hill.getWorld()
							.getBlockAt(smallX, smallY, smallZ);
					if (b.getType() == Material.WOOL) {
						hill.getWorld().getBlockAt(smallX, smallY, smallZ)
								.setData(by);
					}
					Block s = hill.getWorld()
							.getBlockAt(smallX, smallY, smallZ);
					if (s.getType() == Material.STAINED_GLASS) {
						hill.getWorld().getBlockAt(smallX, smallY, smallZ)
								.setData(glass, true);
					}
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	public void neutralWool() {
		World w = Bukkit.getWorld(this.plugin.getConfig().getString(
				"Hills." + "castle" + ".world"));
		double x = this.plugin.getConfig()
				.getDouble("Hills." + "castle" + ".x");
		double y = this.plugin.getConfig()
				.getDouble("Hills." + "castle" + ".y");
		double z = this.plugin.getConfig()
				.getDouble("Hills." + "castle" + ".z");
		Location loc2 = new Location(w, x, y, z);
		Location hill = loc2;
		int hillX = hill.getBlockX();
		int hillY = hill.getBlockY();
		int hillZ = hill.getBlockZ();

		int bigX = hillX + 10;
		int bigY = hillY + 10;
		int bigZ = hillZ + 10;
		byte by = 3;
		byte glass = 3;
		for (int smallX = hillX - 10; smallX < bigX; smallX++) {
			for (int smallY = hillY - 10; smallY < bigY; smallY++) {
				for (int smallZ = hillZ - 10; smallZ < bigZ; smallZ++) {
					Block b = hill.getWorld()
							.getBlockAt(smallX, smallY, smallZ);
					if (b.getType() == Material.WOOL) {
						hill.getWorld().getBlockAt(smallX, smallY, smallZ)
								.setData(by);
					}
					Block s = hill.getWorld()
							.getBlockAt(smallX, smallY, smallZ);
					if (s.getType() == Material.STAINED_GLASS) {
						hill.getWorld().getBlockAt(smallX, smallY, smallZ)
								.setData(glass, true);
					}
				}
			}
		}
	}

	public Integer distanceFromHill(Player p) {
		World w = Bukkit.getWorld(this.plugin.getConfig().getString(
				"Hills." + "castle" + ".world"));
		double x = this.plugin.getConfig()
				.getDouble("Hills." + "castle" + ".x");
		double y = this.plugin.getConfig()
				.getDouble("Hills." + "castle" + ".y");
		double z = this.plugin.getConfig()
				.getDouble("Hills." + "castle" + ".z");
		Location loc2 = new Location(w, x, y, z);
		Location hill = loc2;
		Location pLoc = p.getLocation();
		int distance = (int) pLoc.distance(hill);
		return Integer.valueOf(distance);
	}
}
