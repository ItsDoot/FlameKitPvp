package com.bear53.ecore;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChannelEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import com.bear53.ecore.api.BossBarAPI;
import com.bear53.ecore.bungee.MessageListener;
import com.bear53.ecore.bungee.PreCommandListener;
import com.bear53.ecore.commands.CommandAddKit;
import com.bear53.ecore.commands.CommandAdmin;
import com.bear53.ecore.commands.CommandBan;
import com.bear53.ecore.commands.CommandBroadcast;
import com.bear53.ecore.commands.CommandClear;
import com.bear53.ecore.commands.CommandClearChat;
import com.bear53.ecore.commands.CommandGiveCoins;
import com.bear53.ecore.commands.CommandGroupSet;
import com.bear53.ecore.commands.CommandKillStreak;
import com.bear53.ecore.commands.CommandKit;
import com.bear53.ecore.commands.CommandMute;
import com.bear53.ecore.commands.CommandPing;
import com.bear53.ecore.commands.CommandProfileCode;
import com.bear53.ecore.commands.CommandReload;
import com.bear53.ecore.commands.CommandRemoveKit;
import com.bear53.ecore.commands.CommandReport;
import com.bear53.ecore.commands.CommandResetStats;
import com.bear53.ecore.commands.CommandSetHG;
import com.bear53.ecore.commands.CommandSetOriginal;
import com.bear53.ecore.commands.CommandSetSpawn;
import com.bear53.ecore.commands.CommandSkull;
import com.bear53.ecore.commands.CommandSpawn;
import com.bear53.ecore.commands.CommandStaff;
import com.bear53.ecore.commands.CommandStats;
import com.bear53.ecore.commands.CommandUnBan;
import com.bear53.ecore.commands.CommandUnMute;
import com.bear53.ecore.commands.CommandUpdateScore;
import com.bear53.ecore.duels.Duels;
import com.bear53.ecore.events.AchievementEvent;
import com.bear53.ecore.events.BannedLoginEvent;
import com.bear53.ecore.events.EntitySpawn;
import com.bear53.ecore.events.HeadShot;
import com.bear53.ecore.events.HungerEvent;
import com.bear53.ecore.events.ItemPickupEvent;
import com.bear53.ecore.events.LaunchPads;
import com.bear53.ecore.events.MOTDEvent;
import com.bear53.ecore.events.PlayerChatEvent;
import com.bear53.ecore.events.PlayerDie;
import com.bear53.ecore.events.PlayerLogEvent;
import com.bear53.ecore.events.RespawnEvent;
import com.bear53.ecore.events.SmallEvents;
import com.bear53.ecore.events.SoupSigns;
import com.bear53.ecore.kitpvp.CommandPrestige;
import com.bear53.ecore.kitpvp.FishEvent;
import com.bear53.ecore.kitpvp.GhostEvent;
import com.bear53.ecore.kitpvp.Grenades;
import com.bear53.ecore.kitpvp.HG;
import com.bear53.ecore.kitpvp.KangarooEvent;
import com.bear53.ecore.kitpvp.KitPvp;
import com.bear53.ecore.kitpvp.KitSelectEvent;
import com.bear53.ecore.kitpvp.PrestigeClass;
import com.bear53.ecore.kitpvp.PyroEvent;
import com.bear53.ecore.kitpvp.ScorpionEvent;
import com.bear53.ecore.kitpvp.SnowmanEvent;
import com.bear53.ecore.kitpvp.commands.ArcherClass;
import com.bear53.ecore.kitpvp.commands.ChristmasClass;
import com.bear53.ecore.kitpvp.commands.FishermanClass;
import com.bear53.ecore.kitpvp.commands.GhostClass;
import com.bear53.ecore.kitpvp.commands.HalloweenClass;
import com.bear53.ecore.kitpvp.commands.HeavyClass;
import com.bear53.ecore.kitpvp.commands.KangarooClass;
import com.bear53.ecore.kitpvp.commands.NinjaClass;
import com.bear53.ecore.kitpvp.commands.PorcupineClass;
import com.bear53.ecore.kitpvp.commands.PvpClass;
import com.bear53.ecore.kitpvp.commands.PyroClass;
import com.bear53.ecore.kitpvp.commands.ScorpionClass;
import com.bear53.ecore.kitpvp.commands.SnowmanClass;
import com.bear53.ecore.kitpvp.commands.StrikerClass;
import com.bear53.ecore.kitpvp.commands.ThanksgivingClass;
import com.bear53.ecore.kitpvp.commands.ThorClass;
import com.bear53.ecore.kitpvp.commands.TitanClass;
import com.bear53.ecore.kitpvp.commands.WarriorClass;
import com.bear53.ecore.kitpvp.exceptions.UnloadedPluginException;
import com.bear53.ecore.koth.CommandSetHill;
import com.bear53.ecore.koth.HillMoveEvent;
import com.bear53.ecore.koth.LocationUtils;
import com.bear53.ecore.permissions.CommandAddPerm;
import com.bear53.ecore.permissions.CommandListPerm;
import com.bear53.ecore.permissions.CommandRemovePerm;
import com.bear53.ecore.permissions.InjectEvents;
import com.bear53.ecore.permissions.PermissionSettings;
import com.bear53.ecore.teams.CommandManager;
import com.bear53.ecore.teams.SettingsManager;
import com.bear53.ecore.teams.TeamManager;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

public class Core extends JavaPlugin implements Listener {

	public static Core instance;
	public static PermissionSettings ps;
	public MessageListener ml;
	public String[] servers = null;

	public static String prefix = ChatColor.DARK_GRAY + "["
			+ ChatColor.DARK_RED + "Flame" + ChatColor.DARK_GRAY + "] "
			+ ChatColor.GRAY;
	public static List<String> dkits = new ArrayList<String>();
	public int broadcastnumber = 0;
	public LocationUtils locu;

	public WorldGuardPlugin getWG() {
		Plugin plugin = Bukkit.getServer().getPluginManager()
				.getPlugin("WorldGuard");
		if ((plugin == null) || (!(plugin instanceof WorldGuardPlugin))) {
			try {
				throw new UnloadedPluginException(
						"Unloaded plugin 'WorldGuard'");
			} catch (UnloadedPluginException e) {
				Bukkit.getServer().getConsoleSender()
						.sendMessage("Unloaded plugin 'WorldGuard'");
			}
		}
		return (WorldGuardPlugin) plugin;
	}

	public void onEnable() {
		ps = new PermissionSettings();
		PermissionSettings.getInstance().setupPermissions(this);
		Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		Bukkit.getMessenger().registerIncomingPluginChannel(this, "BungeeCord",
				new MessageListener(this));

		getServers();

		this.ml = new MessageListener(this);

		PluginManager pm = getServer().getPluginManager();

		pm.registerEvents(new PreCommandListener(this), this);
		instance = this;
		SettingsManager.getInstance().setup(this);
		TeamManager.getInstance().setup();
		this.locu = new LocationUtils(this);
		getConfig().addDefault("perms", getConfig().getStringList("perms"));
		getConfig().addDefault("moderators",
				getConfig().getStringList("moderators"));
		getConfig().addDefault("owners", getConfig().getStringList("owners"));
		getConfig().addDefault("admins", getConfig().getStringList("admins"));
		getConfig().addDefault("donors", getConfig().getStringList("donors"));
		getConfig().addDefault("vip", getConfig().getStringList("vip"));
		getConfig().addDefault("vip+", getConfig().getStringList("vip+"));
		getConfig().addDefault("helpers", getConfig().getStringList("helpers"));
		getConfig().addDefault("flame", getConfig().getStringList("flame"));
		getConfig().addDefault("defaults",
				getConfig().getStringList("defaults"));
		getConfig().addDefault("broadcasts",
				getConfig().getStringList("broadcasts"));
		getConfig().addDefault("reports", getConfig().getStringList("reports"));
		getConfig().addDefault("rules", getConfig().getStringList("rules"));
		getConfig().addDefault("potion-enabled", Boolean.valueOf(true));
		getConfig().addDefault("isChristmas", Boolean.valueOf(false));
		getConfig().addDefault("isHalloween", Boolean.valueOf(false));
		getConfig().addDefault("isThanksgiving", Boolean.valueOf(true));
		getConfig().addDefault("totalJoined", Integer.valueOf(0));
		getConfig().options().copyDefaults(true);
		saveConfig();
		getServer().getPluginManager().registerEvents(this, this);
		getServer().getPluginManager().registerEvents(
				new PlayerChatEvent(this), this);
		getServer().getPluginManager()
				.registerEvents(new PlayerDie(this), this);
		getServer().getPluginManager().registerEvents(new RespawnEvent(this),
				this);
		getServer().getPluginManager().registerEvents(new SmallEvents(), this);
		getServer().getPluginManager().registerEvents(new PyroEvent(), this);
		getServer().getPluginManager()
				.registerEvents(new ScorpionEvent(), this);
		getServer().getPluginManager().registerEvents(new PlayerLogEvent(this),
				this);
		getServer().getPluginManager().registerEvents(
				new BannedLoginEvent(this), this);
		getServer().getPluginManager().registerEvents(new HungerEvent(), this);
		getServer().getPluginManager().registerEvents(new EntitySpawn(), this);
		getServer().getPluginManager()
				.registerEvents(new SoupSigns(this), this);
		getServer().getPluginManager().registerEvents(new HeadShot(), this);
		getServer().getPluginManager().registerEvents(
				new ItemPickupEvent(this), this);
		getServer().getPluginManager().registerEvents(new KitSelectEvent(this),
				this);
		getServer().getPluginManager().registerEvents(new LaunchPads(), this);
		getServer().getPluginManager().registerEvents(new CommandAdmin(this),
				this);
		getServer().getPluginManager().registerEvents(new AchievementEvent(),
				this);
		getServer().getPluginManager().registerEvents(new MOTDEvent(), this);
		getServer().getPluginManager().registerEvents(new CommandSpawn(this),
				this);
		getServer().getPluginManager().registerEvents(new CommandSkull(this),
				this);
		getServer().getPluginManager().registerEvents(new TitanClass(this),
				this);
		getServer().getPluginManager().registerEvents(new InjectEvents(), this);
		getServer().getPluginManager().registerEvents(new KangarooEvent(this),
				this);
		getServer().getPluginManager().registerEvents(new HillMoveEvent(this),
				this);
		getServer().getPluginManager().registerEvents(new Duels(this), this);
		getServer().getPluginManager().registerEvents(new HG(this), this);
		getServer().getPluginManager().registerEvents(new SnowmanEvent(), this);
		getServer().getPluginManager().registerEvents(new GhostEvent(this),
				this);
		getServer().getPluginManager().registerEvents(new FishEvent(), this);
		getServer().getPluginManager().registerEvents(new Grenades(this), this);
		getServer().getPluginManager()
				.registerEvents(new ThorClass(this), this);
		getServer().getPluginManager().registerEvents(new PrestigeClass(this),
				this);
		getServer().getPluginManager().registerEvents(new BossBarAPI(), this);
		getCommand("archer").setExecutor(new ArcherClass(this));
		getCommand("ninja").setExecutor(new NinjaClass(this));
		getCommand("porcupine").setExecutor(new PorcupineClass(this));
		getCommand("pvp").setExecutor(new PvpClass(this));
		getCommand("pyro").setExecutor(new PyroClass(this));
		getCommand("scorpion").setExecutor(new ScorpionClass(this));
		getCommand("ban").setExecutor(new CommandBan(this));
		getCommand("groupset").setExecutor(new CommandGroupSet(this));
		getCommand("mute").setExecutor(new CommandMute(this));
		getCommand("profilecode").setExecutor(new CommandProfileCode(this));
		getCommand("unban").setExecutor(new CommandUnBan(this));
		getCommand("unmute").setExecutor(new CommandUnMute(this));
		getCommand("setspawn").setExecutor(new CommandSetSpawn(this));
		getCommand("addkit").setExecutor(new CommandAddKit(this));
		getCommand("removekit").setExecutor(new CommandRemoveKit(this));
		getCommand("kit").setExecutor(new CommandKit(this));
		getCommand("list").setExecutor(new CommandStaff(this));
		getCommand("clearchat").setExecutor(new CommandClearChat(this));
		getCommand("killstreak").setExecutor(new CommandKillStreak(this));
		getCommand("admin").setExecutor(new CommandAdmin(this));
		getCommand("reset").setExecutor(new CommandResetStats(this));
		getCommand("heavy").setExecutor(new HeavyClass(this));
		getCommand("report").setExecutor(new CommandReport(this));
		getCommand("stats").setExecutor(new CommandStats(this));
		getCommand("clear").setExecutor(new CommandClear());
		getCommand("spawn").setExecutor(new CommandSpawn(this));
		getCommand("skull").setExecutor(new CommandSkull(this));
		getCommand("alert").setExecutor(new CommandBroadcast(this));
		getCommand("titan").setExecutor(new TitanClass(this));
		getCommand("kangaroo").setExecutor(new KangarooClass(this));
		getCommand("striker").setExecutor(new StrikerClass(this));
		getCommand("warrior").setExecutor(new WarriorClass(this));
		getCommand("givecoins").setExecutor(new CommandGiveCoins(this));
		getCommand("updatescore").setExecutor(new CommandUpdateScore(this));
		getCommand("ping").setExecutor(new CommandPing());
		getCommand("sethill").setExecutor(new CommandSetHill(this));
		getCommand("team").setExecutor(new CommandManager());
		getCommand("christmas").setExecutor(new ChristmasClass(this));
		getCommand("halloween").setExecutor(new HalloweenClass(this));
		getCommand("thanksgiving").setExecutor(new ThanksgivingClass(this));
		getCommand("sethg").setExecutor(new CommandSetHG(this));
		getCommand("setpvp").setExecutor(new CommandSetOriginal(this));
		getCommand("snowman").setExecutor(new SnowmanClass(this));
		getCommand("fisherman").setExecutor(new FishermanClass(this));
		getCommand("ghost").setExecutor(new GhostClass(this));
		getCommand("thor").setExecutor(new ThorClass(this));
		getCommand("prestige").setExecutor(new CommandPrestige(this));
		getCommand("prestigeclass").setExecutor(new PrestigeClass(this));
		getCommand("flamereload").setExecutor(new CommandReload(this));
		getCommand("addperm").setExecutor(new CommandAddPerm(this));
		getCommand("removeperm").setExecutor(new CommandRemovePerm(this));
		getCommand("listperm").setExecutor(new CommandListPerm(this));
		broadcasts();
		for (Player p : Bukkit.getOnlinePlayers()) {
			try {
				ps.injectPlayer(p);
				getLogger().info("Enabled, Injected All Player Permissions");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		getLogger().info("|---------------------------------------|");
		getLogger().info("| KitPvp has successfully been enabled! |");
		getLogger().info("|---------------------------------------|");
	}

	public void onDisable() {
		getLogger().info("|----------------------------------------|");
		getLogger().info("| KitPvp has successfully been disabled! |");
		getLogger().info("|----------------------------------------|");
	}

	public void getServers() {
		final ByteArrayOutputStream b = new ByteArrayOutputStream();
		final DataOutputStream out = new DataOutputStream(b);

		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
		scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
			public void run() {
				if (Bukkit.getOnlinePlayers().size() == 0) {
					return;
				}
				for (Player player : Bukkit.getOnlinePlayers()) {
					try {
						out.writeUTF("GetServers");
						out.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					if (b.toByteArray().length > 32760) {
						b.reset();
						return;
					}
					player.sendPluginMessage(Core.instance, "BungeeCord",
							b.toByteArray());
				}
			}
		}, 0L, 80L);
	}

	public static String getRandomString(int length) {
		char[] chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
				.toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			char c = chars[random.nextInt(chars.length)];
			sb.append(c);
		}
		return sb.toString();
	}

	public String generateProfileCode(Player player) {
		String code = getRandomString(10);
		getConfig().set(
				"players." + player.getUniqueId().toString() + ".profilecode",
				code);
		saveConfig();
		return code;
	}

	@EventHandler
	public void onJ(PlayerJoinEvent e) {
		final Player p = e.getPlayer();
		List<String> moderators = getConfig().getStringList("moderators");
		List<String> owners = getConfig().getStringList("owners");
		List<String> admins = getConfig().getStringList("admins");
		List<String> donors = getConfig().getStringList("donors");
		List<String> vip = getConfig().getStringList("vip");
		List<String> vipplus = getConfig().getStringList("vip+");
		List<String> flame = getConfig().getStringList("flame");
		List<String> helpers = getConfig().getStringList("helpers");
		e.setJoinMessage(null);
		if (!p.hasPlayedBefore()) {
			int s = getConfig().getInt("totalJoined");
			getConfig().set("totalJoined", s + 1);
			saveConfig();
			Bukkit.broadcastMessage(ChatColor.DARK_AQUA + "Welcome player "
					+ ChatColor.GRAY + p.getName() + ChatColor.DARK_AQUA
					+ " To the server " + ChatColor.DARK_GRAY + "> "
					+ ChatColor.GREEN + s + ChatColor.DARK_GRAY + " <");
			if (getConfig().getInt("totalJoined") % 50 == 0) {
				Bukkit.broadcastMessage(ChatColor.GREEN
						+ "We reached a milestone of " + ChatColor.BLUE
						+ getConfig().getInt("totalJoined") + ChatColor.GREEN
						+ " unique players!");
				Bukkit.broadcastMessage(ChatColor.GREEN + "Enjoy "
						+ ChatColor.YELLOW + "500 " + ChatColor.GOLD + "Coins!");
			}
		} else {
			Bukkit.broadcastMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GREEN
					+ "+" + ChatColor.DARK_GRAY + "] " + ChatColor.GRAY
					+ p.getName());
		}
		if (moderators.contains(p.getName())) {
			p.setPlayerListName(ChatColor.GOLD + p.getName());
		} else if (owners.contains(p.getName())) {
			p.setPlayerListName(ChatColor.DARK_RED + p.getName());
		} else if (admins.contains(p.getName())) {
			p.setPlayerListName(ChatColor.RED + p.getName());
		} else if (helpers.contains(p.getName())) {
			p.setPlayerListName(ChatColor.DARK_AQUA + p.getName());
		} else if (donors.contains(p.getName())
				|| (vip.contains(p.getName()) || (vipplus.contains(p.getName()) || (flame
						.contains(p.getName()))))) {
			p.setPlayerListName(ChatColor.DARK_PURPLE + p.getName());
		} else {
			p.setPlayerListName(ChatColor.GRAY + p.getName());
		}
		p.getInventory().clear();
		p.getInventory().setHelmet(null);
		p.getInventory().setChestplate(null);
		p.getInventory().setLeggings(null);
		p.getInventory().setBoots(null);
		KitPvp.clearEffects(p);
		if (!getConfig().contains(
				"players." + p.getUniqueId().toString() + ".1v1loses")) {
			getConfig().set("players." + p.getUniqueId().toString() + ".name",
					p.getName().toString());
			dkits.add("Archer");
			dkits.add("Warrior");
			dkits.add("Pvp");
			generateProfileCode(p);
			getConfig().set(
					"players." + p.getUniqueId().toString() + ".isBanned",
					Boolean.valueOf(false));
			getConfig().set(
					"players." + p.getUniqueId().toString() + ".bannedReason",
					"");
			getConfig().set(
					"players." + p.getUniqueId().toString() + ".bannedBy", "");
			getConfig().set(
					"players." + p.getUniqueId().toString() + ".isMuted",
					Boolean.valueOf(false));
			getConfig().set("players." + p.getUniqueId().toString() + ".coins",
					Integer.valueOf(500));
			getConfig().set("players." + p.getUniqueId().toString() + ".kills",
					Integer.valueOf(0));
			getConfig().set(
					"players." + p.getUniqueId().toString() + ".deaths",
					Integer.valueOf(0));
			getConfig().set(
					"players." + p.getUniqueId().toString() + ".killstreak",
					Integer.valueOf(0));
			getConfig().set(
					"players." + p.getUniqueId().toString() + ".curkillstreak",
					Integer.valueOf(0));
			getConfig().set("players." + p.getUniqueId() + ".kits", dkits);
			getConfig().set("players." + p.getUniqueId() + ".1v1wins", 0);
			getConfig().set("players." + p.getUniqueId() + ".1v1loses", 0);
			getConfig().set("players." + p.getUniqueId() + ".level",
					Integer.valueOf(1));
			saveConfig();
			getLogger().info("Player Data for " + p.getName() + " created!");
		} else {
			getLogger().warning(
					"Player Data creation for " + p.getName() + " has failed!");
			if (!getConfig().contains(
					"players." + p.getUniqueId().toString() + ".level")) {
				getConfig().set("players." + p.getUniqueId() + ".level",
						Integer.valueOf(1));
				if (!getConfig().contains(
						"players." + p.getUniqueId().toString() + ".joined")) {
					getConfig()
							.set("players." + p.getUniqueId().toString()
									+ ".joined", Boolean.valueOf(true));
					int tj = getConfig().getInt("totalJoined");
					getConfig().set("totalJoined", tj + 1);
					saveConfig();
				}
			}
		}
		double x = getConfig().getDouble("spawn.x");
		double y = getConfig().getDouble("spawn.y");
		double z = getConfig().getDouble("spawn.z");
		float pitch = (float) getConfig().getDouble("spawn.pitch");
		float yaw = (float) getConfig().getDouble("spawn.yaw");
		String world = getConfig().getString("spawn.world");
		Location loc = new Location(Bukkit.getServer().getWorld(world), x, y,
				z, yaw, pitch);
		p.teleport(loc);

		int coins = getConfig().getInt("players." + p.getUniqueId() + ".coins");
		;
		p.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH
				+ "----------------------------------------------------");
		p.sendMessage(ChatColor.YELLOW + "Welcome " + ChatColor.RED
				+ p.getName() + ChatColor.YELLOW + " to the "
				+ ChatColor.DARK_RED + "Flame " + ChatColor.YELLOW
				+ "KitPvp server!");
		p.sendMessage(ChatColor.YELLOW + "You have: " + ChatColor.GOLD + coins
				+ ChatColor.YELLOW + " Coins" + ChatColor.GOLD + "!");
		p.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH
				+ "----------------------------------------------------");
		p.sendMessage("");

		p.setFireTicks(0);
		p.setGameMode(GameMode.SURVIVAL);
		p.setFoodLevel(20);
		p.setHealthScale(20.0D);
		p.setHealth(20.0D);

		ItemStack stats = new ItemStack(Material.PAPER);
		ItemMeta statsmeta = stats.getItemMeta();
		statsmeta.setDisplayName(ChatColor.RED + "Stats");
		ArrayList<String> statslore = new ArrayList<String>();
		statslore.add(ChatColor.GREEN + "Click to see your");
		statslore.add(ChatColor.GREEN + "Kitpvp stats!");
		statsmeta.setLore(statslore);
		stats.setItemMeta(statsmeta);

		ItemStack kit = new ItemStack(Material.WATCH);
		ItemMeta kitmeta = kit.getItemMeta();
		kitmeta.setDisplayName(ChatColor.AQUA + "Kit Selector");
		ArrayList<String> kitlore = new ArrayList<String>();
		kitlore.add(ChatColor.AQUA + "Right Click to");
		kitlore.add(ChatColor.AQUA + "Chose Kit!");
		kitmeta.setLore(kitlore);
		kit.setItemMeta(kitmeta);

		ItemStack shop = new ItemStack(Material.CHEST);
		ItemMeta shopp = shop.getItemMeta();
		shopp.setDisplayName(ChatColor.YELLOW + "Donor Ranks!");
		ArrayList<String> slore = new ArrayList<String>();
		slore.add(ChatColor.YELLOW + "Right Click to");
		slore.add(ChatColor.YELLOW + "View all donation ranks!");
		shopp.setLore(slore);
		shop.setItemMeta(shopp);

		ItemStack mode = new ItemStack(Material.DIAMOND_SWORD);
		ItemMeta modemeta = mode.getItemMeta();
		modemeta.setDisplayName(ChatColor.DARK_RED + "PvP Modes");
		ArrayList<String> modelore = new ArrayList<String>();
		modelore.add(ChatColor.AQUA + "Right click to view");
		modelore.add(ChatColor.AQUA + "Avaliable Pvp modes!");
		modemeta.setLore(modelore);
		mode.setItemMeta(modemeta);

		ItemStack pvp = new ItemStack(Material.CLAY_BALL);
		ItemMeta pvpmeta = pvp.getItemMeta();
		pvpmeta.setDisplayName(ChatColor.YELLOW + "1v1");
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.AQUA + "Right click a player to 1v1!");
		pvpmeta.setLore(lore);
		pvp.setItemMeta(pvpmeta);

		TabUtils.sendTabHeaderFooter(p, ChatColor.RED + "Flame"
				+ ChatColor.AQUA + " Network", ChatColor.GRAY + "IP "
				+ ChatColor.GOLD + "mc.flamesurvival.com" + ChatColor.GRAY
				+ "\nShop @ " + ChatColor.GREEN + "store.flamehub.net");

		ItemStack itemshop = new ItemStack(Material.BLAZE_POWDER);
		ItemMeta shopmeta = itemshop.getItemMeta();
		shopmeta.setDisplayName(ChatColor.GREEN + "Item Shop");
		ArrayList<String> shoplore = new ArrayList<String>();
		shoplore.add(ChatColor.RED + "Be sure to have space in your inventory!");
		shopmeta.setLore(shoplore);
		itemshop.setItemMeta(shopmeta);
		p.getInventory().setItem(8, itemshop);

		p.getInventory().addItem(new ItemStack[] { kit });
		p.getInventory().addItem(new ItemStack[] { stats });
		p.getInventory().addItem(new ItemStack[] { shop });
		p.getInventory().addItem(new ItemStack[] { pvp });
		p.getInventory().addItem(new ItemStack[] { mode });

		BossBarAPI.setMessage(p, ChatColor.DARK_RED + "" + ChatColor.BOLD
				+ "Flame");
		for (Player o : Bukkit.getServer().getOnlinePlayers()) {
			ScoreboardManager manager = Bukkit.getScoreboardManager();
			Scoreboard board = manager.getNewScoreboard();

			Objective obj = board.registerNewObjective("Test", "Test2");
			obj.setDisplayName("§8> §4§nFlameNetwork§r §8<");
			obj.setDisplaySlot(DisplaySlot.SIDEBAR);
			Score score = obj
					.getScore("§8» §aKills§7: §7"
							+ getConfig().getInt(
									"players." + o.getUniqueId().toString()
											+ ".kills"));
			score.setScore(12);

			Score score2 = obj.getScore("§8» §aDeaths§7: §7"
					+ getConfig()
							.getInt("players." + o.getUniqueId().toString()
									+ ".deaths"));
			score2.setScore(11);

			double kills = getConfig().getInt(
					"players." + o.getUniqueId().toString() + ".kills");
			double deaths = getConfig().getInt(
					"players." + o.getUniqueId().toString() + ".deaths");
			if (deaths == 0.0D) {
				deaths = 1.0D;
			}
			String KD = String.format("%.2f",
					new Object[] { Double.valueOf(kills / deaths) });

			Score kd = obj.getScore("§8» §aK/D§7: §7"
					+ Arrays.asList(new String[] { "§7" + KD }));
			kd.setScore(10);

			Score curks = obj.getScore("§8» §aKill Streak§8: §7"
					+ getConfig().getInt(
							"players." + o.getUniqueId().toString()
									+ ".curkillstreak"));
			curks.setScore(9);
			Score bl2 = obj.getScore(" ");
			bl2.setScore(8);

			Score level = obj
					.getScore("§8» §bLevel§7: §7"
							+ getConfig().getInt(
									"players." + o.getUniqueId().toString()
											+ ".level"));
			level.setScore(7);
			Score killsneeded = obj.getScore("§8» §bKills Needed§7: 5");
			killsneeded.setScore(6);
			Score score4 = obj
					.getScore("§8» §3Coins§7: "
							+ getConfig().getInt(
									"players." + o.getUniqueId().toString()
											+ ".coins"));
			score4.setScore(5);

			Score bl3 = obj.getScore("");
			bl3.setScore(4);

			Score s5 = obj.getScore("§8» §a1v1 Wins§7: "
					+ getConfig().getInt(
							"players." + o.getUniqueId().toString()
									+ ".1v1wins"));
			s5.setScore(3);

			Score s6 = obj.getScore("§8» §a1v1 Loses§7: "
					+ getConfig().getInt(
							"players." + o.getUniqueId().toString()
									+ ".1v1loses"));
			s6.setScore(2);

			if (HillMoveEvent.win.get(o.getName()) != null) {
				Integer pt = HillMoveEvent.win.get(o.getName());
				Score hilly = obj.getScore("§8» §aHill Points§7: " + pt);
				hilly.setScore(1);
			} else {
				Score hilly = obj.getScore("§8» §aHill Points§7: 0");
				hilly.setScore(1);
			}

			o.setScoreboard(board);
		}
	}

	public void broadcasts() {
		int time = 30;
		Bukkit.getServer().getScheduler()
				.scheduleSyncRepeatingTask(this, new Runnable() {
					public void run() {
						if (Core.this.broadcastnumber == 0) {
							int broadcasts = Core.this.getConfig()
									.getStringList("broadcasts").size();
							Core.this.reloadConfig();
							Core.this.broadcastnumber = broadcasts;
						}
						String message = ChatColor
								.translateAlternateColorCodes(
										'&',

										(String) Core.this
												.getConfig()
												.getStringList("broadcasts")
												.get(Core.this.broadcastnumber - 1));
						Core.this.reloadConfig();
						Bukkit.getServer().broadcastMessage(message);
						for (Player p : Bukkit.getOnlinePlayers()) {
							ps.injectPlayer(p);
						}
						Core.this.broadcastnumber -= 1;
					}
				}, 0L, 60 * time);
	}

	HashMap<String, String> lastSentMessages = new HashMap<String, String>();
	public ArrayList<String> afk = new ArrayList<String>();

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		Player player = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("gmc")) {
			if (player.isOp()) {
				player.setGameMode(GameMode.CREATIVE);
				player.sendMessage(ChatColor.GOLD
						+ "Your gamemode has been updated!");
			} else {
				player.sendMessage(ChatColor.RED + "No permission!");
			}
		} else if (cmd.getName().equalsIgnoreCase("rules")) {
			for (String rules : getConfig().getStringList("rules")) {
				player.sendMessage(ChatColor.translateAlternateColorCodes('&',
						rules));
			}
			// } else if (cmd.getName().equalsIgnoreCase("setspawn1")) {
			// if (sender.isOp()) {
			// Location loc = player.getLocation();
			//
			// getConfig().set("1v1.spawn.1.x", Double.valueOf(loc.getX()));
			// getConfig().set("1v1.spawn.1.y", Double.valueOf(loc.getY()));
			// getConfig().set("1v1.spawn.1.z", Double.valueOf(loc.getZ()));
			// getConfig().set("1v1.spawn.1.pitch",
			// Double.valueOf(loc.getPitch()));
			// getConfig()
			// .set("1v1.spawn.1.yaw", Double.valueOf(loc.getYaw()));
			// getConfig().set("1v1.spawn.1.world",
			// player.getWorld().getName());
			//
			// saveConfig();
			//
			// player.sendMessage(ChatColor.GREEN
			// + "First 1v1 spawn has been set at your location.");
			// } else {
			// sender.sendMessage(ChatColor.RED + "No permission.");
			// }
			// } else if (cmd.getName().equalsIgnoreCase("setspawn2")) {
			// if (sender.isOp()) {
			// Location loc = player.getLocation();

			// getConfig().set("1v1.spawn.2.x", Double.valueOf(loc.getX()));
			// getConfig().set("1v1.spawn.2.y", Double.valueOf(loc.getY()));
			// getConfig().set("1v1.spawn.2.z", Double.valueOf(loc.getZ()));
			// getConfig().set("1v1.spawn.2.pitch",
			// Double.valueOf(loc.getPitch()));
			// getConfig()
			// .set("1v1.spawn.2.yaw", Double.valueOf(loc.getYaw()));
			// getConfig().set("1v1.spawn.2.world",
			// player.getWorld().getName());
			//
			// saveConfig();
			//
			// player.sendMessage(ChatColor.GREEN
			// + "Second 1v1 spawn has been set at your location.");
			// } else {
			// sender.sendMessage(ChatColor.RED + "No permission.");
			// }
		} else if (cmd.getName().equalsIgnoreCase("afk")) {
			List<String> moderators = getConfig().getStringList("moderators");
			List<String> owners = getConfig().getStringList("owners");
			List<String> admins = getConfig().getStringList("admins");
			List<String> helpers = getConfig().getStringList("helpers");
			if (moderators.contains(player.getName())
					|| (owners.contains(player.getName()) || (admins
							.contains(player.getName()) || (helpers
							.contains(player.getName()))))) {
				if (afk.contains(player.getName())) {
					afk.remove(player.getName());
					Bukkit.broadcastMessage(ChatColor.GRAY + "* "
							+ ChatColor.YELLOW + player.getName()
							+ ChatColor.GRAY + " is no longer AFK");
				} else if (!afk.contains(player.getName())) {
					afk.add(player.getName());
					Bukkit.broadcastMessage(ChatColor.GRAY + "* "
							+ ChatColor.YELLOW + player.getName()
							+ ChatColor.GRAY + " is now AFK");
				}
			} else {
				player.sendMessage(ChatColor.RED + "Error: No Permission");
			}
		} else if (cmd.getName().equalsIgnoreCase("gms")) {
			if (player.isOp()) {
				player.setGameMode(GameMode.SURVIVAL);
				player.sendMessage(ChatColor.GOLD
						+ "Your gamemode has been updated!");
			} else {
				player.sendMessage(ChatColor.RED + "No permission!");
			}
		} else {
			if (cmd.getName().equalsIgnoreCase("msg")) {
				Player p = Bukkit.getPlayer(sender.getName());
				if (args.length < 2) {
					p.sendMessage(ChatColor.RED + "Too few arguments");
					p.sendMessage(ChatColor.RED + "/msg <player> <message>");
					return true;
				}
				if (Bukkit.getPlayer(args[0]) == null) {
					p.sendMessage(ChatColor.RED + "That player is not online");
					return true;
				}
				String msg = "";
				for (String s : args) {
					msg = msg + " " + s;
				}
				Bukkit.getPlayer(args[0]).sendMessage(
						ChatColor.GOLD
								+ "["
								+ ChatColor.GRAY
								+ p.getName()
								+ ChatColor.GOLD
								+ " -> "
								+ ChatColor.RED
								+ "me"
								+ ChatColor.GOLD
								+ "]"
								+ ChatColor.WHITE
								+ " "
								+ msg.replaceFirst(new StringBuilder(" ")
										.append(args[0]).toString(), ""));
				p.sendMessage(ChatColor.GOLD
						+ "["
						+ ChatColor.RED
						+ "me"
						+ ChatColor.GOLD
						+ " -> "
						+ ChatColor.GRAY
						+ args[0]
						+ ChatColor.GOLD
						+ "] "
						+ ChatColor.WHITE
						+ msg.replaceFirst(
								new StringBuilder(" ").append(args[0])
										.toString(), ""));
				if (afk.contains(Bukkit.getPlayer(args[0]).getName())) {
					p.sendMessage(ChatColor.YELLOW
							+ "The player "
							+ ChatColor.GREEN
							+ Bukkit.getPlayer(args[0]).getName().toString()
							+ ChatColor.YELLOW
							+ " is currently AFK and may not recive your message.");
				}
				this.lastSentMessages.put(p.getName(), args[0]);
				this.lastSentMessages.put(args[0], p.getName());
				return true;
			}
			if (cmd.getName().equalsIgnoreCase("r")) {
				if (args.length < 1) {
					player.sendMessage(ChatColor.RED + "Too few arguments.");
					player.sendMessage(ChatColor.RED + "/r <message>");
					return true;
				}
				if (!this.lastSentMessages.containsKey(player.getName())) {
					player.sendMessage(ChatColor.RED
							+ "You do not have anyone to reply to.");
					return true;
				}
				if (Bukkit.getPlayer((String) this.lastSentMessages.get(player
						.getName())) == null) {
					player.sendMessage(ChatColor.RED
							+ "That player is not online.");
					return true;
				}
				String msg = "";
				for (String s : args) {
					msg = msg + " " + s;
				}
				Bukkit.getPlayer(
						(String) this.lastSentMessages.get(player.getName()))
						.sendMessage(
								ChatColor.GOLD + "[" + ChatColor.GRAY
										+ player.getName() + ChatColor.GOLD
										+ " -> " + ChatColor.RED + "me"
										+ ChatColor.GOLD + "]"
										+ ChatColor.WHITE + " " + msg);
				player.sendMessage(ChatColor.GOLD + "[" + ChatColor.RED + "me"
						+ ChatColor.GOLD + " -> " + ChatColor.GRAY
						+ (String) this.lastSentMessages.get(player.getName())
						+ ChatColor.GOLD + "] " + ChatColor.WHITE + msg);
			}
		}
		return true;
	}

	@EventHandler
	public void AFKMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		Location from = e.getFrom();
		Location to = e.getTo();
		double fromx = from.getX();
		double fromy = from.getY();
		double fromz = from.getZ();
		double tox = to.getX();
		double toy = to.getY();
		double toz = to.getZ();
		if (afk.contains(p.getName())) {
			if (fromx > tox || fromy > toy || fromz > toz || fromx < tox
					|| fromy < toy || fromz < toz) {
				afk.remove(p.getName());
				Bukkit.broadcastMessage(ChatColor.GRAY + "* "
						+ ChatColor.YELLOW + p.getName() + ChatColor.GRAY
						+ " is no longer AFK");
			}
		} else if (!afk.contains(p.getName())) {
			return;
		}
	}

	@EventHandler
	public void onC(PlayerChannelEvent e) {
		Player p = e.getPlayer();
		if (afk.contains(p.getName())) {
			Bukkit.broadcastMessage(ChatColor.GRAY + "* " + ChatColor.YELLOW
					+ p.getName() + ChatColor.GRAY + " is no longer AFK");
		} else if (!afk.contains(p.getName())) {
			return;
		}
	}
}
