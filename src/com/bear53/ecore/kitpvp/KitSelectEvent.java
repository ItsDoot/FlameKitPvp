package com.bear53.ecore.kitpvp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import com.bear53.ecore.Core;
import com.bear53.ecore.koth.HillMoveEvent;

public class KitSelectEvent implements Listener {

	Core plugin;

	public KitSelectEvent(Core pl) {
		this.plugin = pl;
	}

	private Inventory inv;

	public boolean hasEnough(Player p, int i) {
		int coins = plugin.getConfig().getInt(
				"players." + p.getUniqueId().toString() + ".coins");
		return (coins >= i);
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();

		ItemStack itemshop = new ItemStack(Material.BLAZE_POWDER);
		ItemMeta shopmeta = itemshop.getItemMeta();
		shopmeta.setDisplayName(ChatColor.GREEN + "Item Shop");
		ArrayList<String> shoplore = new ArrayList<String>();
		shoplore.add(ChatColor.RED + "Be sure to have space in your inventory!");
		shopmeta.setLore(shoplore);
		itemshop.setItemMeta(shopmeta);

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

		if (p.getItemInHand().equals(kit)) {
			Inventory select = Bukkit.createInventory(null, 45,
					ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Kit Selector");

			List<String> playerKits = plugin.getConfig().getStringList(
					"players." + p.getUniqueId() + ".kits");

			ItemStack pvp = new ItemStack(Material.DIAMOND_SWORD);
			ItemMeta pvpmeta = pvp.getItemMeta();
			pvpmeta.setDisplayName(ChatColor.GREEN + "Pvp");
			ArrayList<String> pvplore = new ArrayList<String>();
			pvplore.add("");
			pvplore.add(ChatColor.LIGHT_PURPLE + "Default Kit");
			pvplore.add(ChatColor.LIGHT_PURPLE + "Full Iron Armor");
			pvplore.add("");
			pvplore.add(ChatColor.GOLD + "--- Click to Equip! ---");
			pvpmeta.setLore(pvplore);
			pvp.setItemMeta(pvpmeta);
			select.setItem(1, pvp);

			ItemStack archer = new ItemStack(Material.BOW);
			ItemMeta archermeta = archer.getItemMeta();
			archermeta.setDisplayName(ChatColor.GREEN + "Archer");
			archer.addEnchantment(Enchantment.ARROW_DAMAGE, 4);
			archer.addEnchantment(Enchantment.ARROW_KNOCKBACK, 2);
			archer.addEnchantment(Enchantment.ARROW_INFINITE, 1);
			ArrayList<String> archerlore = new ArrayList<String>();
			archerlore.add("");
			archerlore.add(ChatColor.LIGHT_PURPLE + "Pew, Pew");
			archerlore.add(ChatColor.LIGHT_PURPLE + "360, No Scoppezz");
			archerlore.add("");
			archerlore.add(ChatColor.GOLD + "--- Click to Equip ---");
			archermeta.setLore(archerlore);
			archer.setItemMeta(archermeta);
			select.setItem(3, archer);

			if (playerKits.contains("Pyro")) {
				ItemStack pyro = new ItemStack(Material.BLAZE_ROD);
				ItemMeta pyrometa = pyro.getItemMeta();
				pyrometa.setDisplayName(ChatColor.GREEN + "Pyro");
				ArrayList<String> plore = new ArrayList<String>();
				plore.add("");
				plore.add(ChatColor.LIGHT_PURPLE + "Use the blazerod to");
				plore.add(ChatColor.LIGHT_PURPLE
						+ "Shoot fireballs at your enemies!");
				plore.add("");
				plore.add(ChatColor.GOLD + "--- Click to Equip ---");
				pyrometa.setLore(plore);
				pyro.setItemMeta(pyrometa);
				select.setItem(5, pyro);
			} else {
				ItemStack pyro = new ItemStack(Material.BLAZE_ROD);
				ItemMeta pyrometa = pyro.getItemMeta();
				pyrometa.setDisplayName(ChatColor.GREEN + "Pyro");
				ArrayList<String> plore = new ArrayList<String>();
				plore.add("");
				plore.add(ChatColor.LIGHT_PURPLE + "Use the blazerod to");
				plore.add(ChatColor.LIGHT_PURPLE
						+ "Shoot fireballs at your enemies!");
				plore.add("");
				plore.add(ChatColor.RED + "You need the "
						+ ChatColor.DARK_PURPLE + "VIP" + ChatColor.GREEN + "+"
						+ ChatColor.RED + " rank");
				pyrometa.setLore(plore);
				pyro.setItemMeta(pyrometa);
				select.setItem(5, pyro);
			}

			if (playerKits.contains("Porcupine")) {
				ItemStack c = new ItemStack(Material.CACTUS);
				ItemMeta cmeta = c.getItemMeta();
				cmeta.setDisplayName(ChatColor.GREEN + "Porcupine");
				ArrayList<String> clore = new ArrayList<String>();
				clore.add("");
				clore.add(ChatColor.LIGHT_PURPLE + "Poke all your enimies");
				clore.add(ChatColor.LIGHT_PURPLE + "using this kit!");
				clore.add("");
				clore.add(ChatColor.GOLD + "--- Click to Equip ---");
				cmeta.setLore(clore);
				c.setItemMeta(cmeta);
				select.setItem(7, c);
			} else {
				ItemStack c = new ItemStack(Material.CACTUS);
				ItemMeta cmeta = c.getItemMeta();
				cmeta.setDisplayName(ChatColor.GREEN + "Porcupine");
				ArrayList<String> clore = new ArrayList<String>();
				clore.add("");
				clore.add(ChatColor.LIGHT_PURPLE + "Poke all your enimies");
				clore.add(ChatColor.LIGHT_PURPLE + "using this kit!");
				clore.add("");
				clore.add(ChatColor.RED + "You need the "
						+ ChatColor.DARK_PURPLE + "VIP" + ChatColor.RED
						+ " rank");
				cmeta.setLore(clore);
				c.setItemMeta(cmeta);
				select.setItem(7, c);
			}

			if (playerKits.contains("Scorpion")) {
				ItemStack s = new ItemStack(Material.SPIDER_EYE);
				ItemMeta sme = s.getItemMeta();
				sme.setDisplayName(ChatColor.GREEN + "Scorpion");
				ArrayList<String> sl = new ArrayList<String>();
				sl.add("");
				sl.add(ChatColor.LIGHT_PURPLE + "Posion your enemys!");
				sl.add("");
				sl.add(ChatColor.GOLD + "--- Click to Equip ---");
				sme.setLore(sl);
				s.setItemMeta(sme);
				select.setItem(11, s);
			} else {
				ItemStack s = new ItemStack(Material.SPIDER_EYE);
				ItemMeta sme = s.getItemMeta();
				sme.setDisplayName(ChatColor.GREEN + "Scorpion");
				ArrayList<String> sl = new ArrayList<String>();
				sl.add("");
				sl.add(ChatColor.LIGHT_PURPLE + "Posion your enemys!");
				sl.add("");
				sl.add(ChatColor.RED + "You need the " + ChatColor.DARK_PURPLE
						+ "Donor" + ChatColor.RED + " rank");
				sme.setLore(sl);
				s.setItemMeta(sme);
				select.setItem(11, s);
			}

			if (playerKits.contains("Ninja")) {
				ItemStack n = new ItemStack(Material.STICK);
				ItemMeta nm = n.getItemMeta();
				nm.setDisplayName(ChatColor.GREEN + "Ninja");
				ArrayList<String> nl = new ArrayList<String>();
				nl.add("");
				nl.add(ChatColor.LIGHT_PURPLE + "Sneak around your enemys");
				nl.add("");
				nl.add(ChatColor.GOLD + "--- Click to Equip ---");
				nm.setLore(nl);
				n.setItemMeta(nm);
				select.setItem(13, n);
			} else {
				ItemStack n = new ItemStack(Material.STICK);
				ItemMeta nm = n.getItemMeta();
				nm.setDisplayName(ChatColor.GREEN + "Ninja");
				ArrayList<String> nl = new ArrayList<String>();
				nl.add("");
				nl.add(ChatColor.LIGHT_PURPLE + "Sneak around your enemys");
				nl.add("");
				nl.add(ChatColor.RED + "You need the " + ChatColor.DARK_PURPLE
						+ "Donor" + ChatColor.RED + " rank");
				nm.setLore(nl);
				n.setItemMeta(nm);
				select.setItem(13, n);
			}

			if (playerKits.contains("Heavy")) {
				ItemStack heavy = new ItemStack(Material.DIAMOND_CHESTPLATE);
				ItemMeta hm = heavy.getItemMeta();
				hm.setDisplayName(ChatColor.GREEN + "Heavy");
				ArrayList<String> hl = new ArrayList<String>();
				hl.add("");
				hl.add(ChatColor.LIGHT_PURPLE + "Use your heavy armor");
				hl.add(ChatColor.LIGHT_PURPLE + "To outlast your enemys!");
				hl.add("");
				hl.add(ChatColor.GOLD + "--- Click to Equip ---");
				hm.setLore(hl);
				heavy.setItemMeta(hm);
				select.setItem(15, heavy);
			} else {
				ItemStack heavy = new ItemStack(Material.DIAMOND_CHESTPLATE);
				ItemMeta hm = heavy.getItemMeta();
				hm.setDisplayName(ChatColor.GREEN + "Heavy");
				ArrayList<String> hl = new ArrayList<String>();
				hl.add("");
				hl.add(ChatColor.LIGHT_PURPLE + "Use your heavy armor");
				hl.add(ChatColor.LIGHT_PURPLE + "To outlast your enemys!");
				hl.add("");
				hl.add(ChatColor.RED + "You need the " + ChatColor.DARK_PURPLE
						+ "Flame" + ChatColor.RED + " rank");
				hm.setLore(hl);
				heavy.setItemMeta(hm);
				select.setItem(15, heavy);
			}

			if (playerKits.contains("Striker")) {
				ItemStack strike = new ItemStack(Material.SUGAR);
				ItemMeta strikemeta = strike.getItemMeta();
				strikemeta.setDisplayName(ChatColor.GREEN + "Striker");
				ArrayList<String> slore1 = new ArrayList<String>();
				slore1.add("");
				slore1.add(ChatColor.LIGHT_PURPLE + "Silent and deadly");
				slore1.add(ChatColor.LIGHT_PURPLE
						+ "Speed in to kill your enemies!");
				slore1.add("");
				slore1.add(ChatColor.GOLD + "--- Click to Equip ---");
				strikemeta.setLore(slore1);
				strike.setItemMeta(strikemeta);
				select.setItem(19, strike);
			} else {
				ItemStack strike = new ItemStack(Material.SUGAR);
				ItemMeta strikemeta = strike.getItemMeta();
				strikemeta.setDisplayName(ChatColor.GREEN + "Striker");
				ArrayList<String> slore1 = new ArrayList<String>();
				slore1.add("");
				slore1.add(ChatColor.LIGHT_PURPLE + "Silent and deadly");
				slore1.add(ChatColor.LIGHT_PURPLE
						+ "Speed in to kill your enemies!");
				slore1.add("");
				slore1.add(ChatColor.RED + "You need " + ChatColor.YELLOW
						+ "2500" + ChatColor.GOLD + " coins " + ChatColor.RED
						+ "for this kit!");
				strikemeta.setLore(slore1);
				strike.setItemMeta(strikemeta);
				select.setItem(19, strike);
			}

			if (playerKits.contains("Titan")) {
				ItemStack t = new ItemStack(Material.NETHER_STAR);
				ItemMeta tmeta = t.getItemMeta();
				tmeta.setDisplayName(ChatColor.GREEN + "Titan");
				ArrayList<String> tlore = new ArrayList<String>();
				tlore.add("");
				tlore.add(ChatColor.LIGHT_PURPLE + "Use the titans ability to");
				tlore.add(ChatColor.LIGHT_PURPLE
						+ "Go invincible for 10 seconds");
				tlore.add("");
				tlore.add(ChatColor.GOLD + "--- Click to Equip! ---");
				tmeta.setLore(tlore);
				t.setItemMeta(tmeta);
				select.setItem(21, t);
			} else {
				ItemStack t = new ItemStack(Material.NETHER_STAR);
				ItemMeta tmeta = t.getItemMeta();
				tmeta.setDisplayName(ChatColor.GREEN + "Titan");
				ArrayList<String> tlore = new ArrayList<String>();
				tlore.add("");
				tlore.add(ChatColor.LIGHT_PURPLE + "Use the titans ability to");
				tlore.add(ChatColor.LIGHT_PURPLE
						+ "Go invincible for 10 seconds");
				tlore.add("");
				tlore.add(ChatColor.RED + "You need " + ChatColor.YELLOW
						+ "5000" + ChatColor.GOLD + " coins " + ChatColor.RED
						+ "for this kit!");
				tmeta.setLore(tlore);
				t.setItemMeta(tmeta);
				select.setItem(21, t);
			}

			if (playerKits.contains("Prestige")) {
				ItemStack pre = new ItemStack(Material.GOLD_BLOCK);
				ItemMeta prem = pre.getItemMeta();
				prem.setDisplayName(ChatColor.GREEN + "Prestige");
				ArrayList<String> plore = new ArrayList<String>();
				plore.add("");
				plore.add(ChatColor.LIGHT_PURPLE + "The Prestige kit!");
				plore.add(ChatColor.LIGHT_PURPLE + "For masters only!");
				plore.add("");
				plore.add(ChatColor.GOLD + "--- Click to Equip! ---");
				prem.setLore(plore);
				pre.setItemMeta(prem);
				select.setItem(22, pre);
			} else {
				ItemStack pre = new ItemStack(Material.GOLD_BLOCK);
				ItemMeta prem = pre.getItemMeta();
				prem.setDisplayName(ChatColor.GREEN + "Prestige");
				ArrayList<String> plore = new ArrayList<String>();
				plore.add("");
				plore.add(ChatColor.LIGHT_PURPLE + "The Prestige kit!");
				plore.add(ChatColor.LIGHT_PURPLE + "For masters only!");
				plore.add("");
				plore.add(ChatColor.RED + "You must have " + ChatColor.GOLD
						+ "Prestiged " + ChatColor.RED + "to use this kit");
				prem.setLore(plore);
				pre.setItemMeta(prem);
				select.setItem(22, pre);
			}

			if (plugin.getConfig().getInt(
					"players." + p.getUniqueId().toString() + ".1v1wins") >= 10) {
				ItemStack f = new ItemStack(Material.FISHING_ROD);
				ItemMeta fm = f.getItemMeta();
				fm.setDisplayName(ChatColor.GREEN + "Fisherman");
				ArrayList<String> flore = new ArrayList<String>();
				flore.add("");
				flore.add(ChatColor.LIGHT_PURPLE + "Use the fishing rod");
				flore.add(ChatColor.LIGHT_PURPLE + "To hook players!");
				flore.add("");
				flore.add(ChatColor.GOLD + "--- Click to Equip! ---");
				fm.setLore(flore);
				f.setItemMeta(fm);
				select.setItem(37, f);
			} else {
				ItemStack f = new ItemStack(Material.FISHING_ROD);
				ItemMeta fm = f.getItemMeta();
				fm.setDisplayName(ChatColor.GREEN + "Fisherman");
				ArrayList<String> flore = new ArrayList<String>();
				flore.add("");
				flore.add(ChatColor.LIGHT_PURPLE + "Use the fishing rod");
				flore.add(ChatColor.LIGHT_PURPLE + "To hook players!");
				flore.add("");
				flore.add(ChatColor.RED + "You need 10 1v1 " + ChatColor.GREEN
						+ "wins" + ChatColor.RED + "!");
				fm.setLore(flore);
				f.setItemMeta(fm);
				select.setItem(37, f);
			}

			if (plugin.getConfig().getInt(
					"players." + p.getUniqueId().toString() + ".1v1wins") >= 20) {
				Potion g = new Potion(PotionType.INVISIBILITY);
				ItemStack gh = g.toItemStack(1);
				ItemMeta gm = gh.getItemMeta();
				gm.setDisplayName(ChatColor.GREEN + "Ghost");
				ArrayList<String> glore = new ArrayList<String>();
				glore.add("");
				glore.add(ChatColor.LIGHT_PURPLE + "Sneak up on your enemys!");
				glore.add("");
				glore.add(ChatColor.GOLD + "--- Click to Equip! ---");
				gm.setLore(glore);
				gh.setItemMeta(gm);
				select.setItem(39, gh);
			} else {
				Potion g = new Potion(PotionType.INVISIBILITY);
				ItemStack gh = g.toItemStack(1);
				ItemMeta gm = gh.getItemMeta();
				gm.setDisplayName(ChatColor.GREEN + "Ghost");
				ArrayList<String> glore = new ArrayList<String>();
				glore.add("");
				glore.add(ChatColor.LIGHT_PURPLE + "Sneak up on your enemys!");
				glore.add("");
				glore.add(ChatColor.RED + "You need 20 1v1 " + ChatColor.GREEN
						+ "wins" + ChatColor.RED + "!");
				gm.setLore(glore);
				gh.setItemMeta(gm);
				select.setItem(39, gh);
			}

			if (plugin.getConfig().getInt(
					"players." + p.getUniqueId().toString() + ".1v1wins") >= 30) {
				ItemStack snowman = new ItemStack(Material.SNOW_BALL);
				ItemMeta sm = snowman.getItemMeta();
				sm.setDisplayName(ChatColor.GREEN + "Snowman");
				ArrayList<String> slore1 = new ArrayList<String>();
				slore1.add("");
				slore1.add(ChatColor.LIGHT_PURPLE + "Throw Snowballs at");
				slore1.add(ChatColor.LIGHT_PURPLE + "Your enemys!");
				slore1.add("");
				slore1.add(ChatColor.GOLD + "--- Click to Equip! ---");
				sm.setLore(slore1);
				snowman.setItemMeta(sm);
				select.setItem(41, snowman);
			} else {
				ItemStack snowman = new ItemStack(Material.SNOW_BALL);
				ItemMeta sm = snowman.getItemMeta();
				sm.setDisplayName(ChatColor.GREEN + "Snowman");
				ArrayList<String> slore1 = new ArrayList<String>();
				slore1.add("");
				slore1.add(ChatColor.LIGHT_PURPLE + "Throw Snowballs at");
				slore1.add(ChatColor.LIGHT_PURPLE + "Your enemys!");
				slore1.add("");
				slore1.add(ChatColor.RED + "You need 30 1v1 " + ChatColor.GREEN
						+ "wins" + ChatColor.RED + "!");
				sm.setLore(slore1);
				snowman.setItemMeta(sm);
				select.setItem(41, snowman);
			}

			if (plugin.getConfig().getInt(
					"players." + p.getUniqueId().toString() + ".1v1wins") >= 40) {
				ItemStack thor = new ItemStack(Material.GOLD_AXE);
				ItemMeta thorm = thor.getItemMeta();
				thorm.setDisplayName(ChatColor.GREEN + "Thor");
				ArrayList<String> thlore = new ArrayList<String>();
				thlore.add("");
				thlore.add(ChatColor.LIGHT_PURPLE + "Bring down the sky!");
				thlore.add("");
				thlore.add(ChatColor.GOLD + "--- Click to Equip! ---");
				thorm.setLore(thlore);
				thor.setItemMeta(thorm);
				select.setItem(43, thor);
			} else {
				ItemStack thor = new ItemStack(Material.GOLD_AXE);
				ItemMeta thorm = thor.getItemMeta();
				thorm.setDisplayName(ChatColor.GREEN + "Thor");
				ArrayList<String> thlore = new ArrayList<String>();
				thlore.add("");
				thlore.add(ChatColor.LIGHT_PURPLE + "Bring down the sky!");
				thlore.add("");
				thlore.add(ChatColor.RED + "You need 40 1v1 " + ChatColor.GREEN
						+ "wins" + ChatColor.RED + "!");
				thorm.setLore(thlore);
				thor.setItemMeta(thorm);
				select.setItem(43, thor);
			}

			if (playerKits.contains("Kangaroo")) {
				ItemStack k = new ItemStack(Material.FIREWORK);
				ItemMeta kmeta = k.getItemMeta();
				kmeta.setDisplayName(ChatColor.GREEN + "Kangaroo");
				ArrayList<String> klore = new ArrayList<String>();
				klore.add("");
				klore.add(ChatColor.LIGHT_PURPLE + "Right click the firework");
				klore.add(ChatColor.LIGHT_PURPLE + "To jump like a Kangaroo!");
				klore.add("");
				klore.add(ChatColor.GOLD + "--- Click to Equip! ---");
				kmeta.setLore(klore);
				k.setItemMeta(kmeta);
				select.setItem(23, k);
			} else {
				ItemStack k = new ItemStack(Material.FIREWORK);
				ItemMeta kmeta = k.getItemMeta();
				kmeta.setDisplayName(ChatColor.GREEN + "Kangaroo");
				ArrayList<String> klore = new ArrayList<String>();
				klore.add("");
				klore.add(ChatColor.LIGHT_PURPLE + "Right click the firework");
				klore.add(ChatColor.LIGHT_PURPLE + "To jump like a Kangaroo!");
				klore.add("");
				klore.add(ChatColor.RED + "You need " + ChatColor.YELLOW
						+ "1500" + ChatColor.GOLD + " coins " + ChatColor.RED
						+ "for this kit!");
				kmeta.setLore(klore);
				k.setItemMeta(kmeta);
				select.setItem(23, k);
			}
			if (plugin.getConfig().getBoolean("isHalloween")) {
				ItemStack scare = new ItemStack(Material.JACK_O_LANTERN);
				ItemMeta scarem = scare.getItemMeta();
				scarem.setDisplayName(ChatColor.GOLD + "Halloween");
				ArrayList<String> hallow = new ArrayList<String>();
				hallow.add("");
				hallow.add(ChatColor.GOLD + "Become a spooky pumpkin!");
				hallow.add("");
				hallow.add(ChatColor.GOLD + "--- Click to Equip! ---");
				scarem.setLore(hallow);
				scare.setItemMeta(scarem);
				select.setItem(29, scare);
			} else {
				ItemStack scare = new ItemStack(Material.PUMPKIN);
				ItemMeta scarem = scare.getItemMeta();
				scarem.setDisplayName(ChatColor.GOLD + "Halloween");
				ArrayList<String> hallow = new ArrayList<String>();
				hallow.add("");
				hallow.add(ChatColor.GOLD + "Become a spooky pumpkin!");
				hallow.add("");
				hallow.add(ChatColor.RED + "It must be " + ChatColor.GOLD
						+ "Halloween " + ChatColor.RED + "to use this kit!");
				scarem.setLore(hallow);
				scare.setItemMeta(scarem);
				select.setItem(29, scare);
			}

			if (plugin.getConfig().getBoolean("isChristmas")) {
				ItemStack santa = new ItemStack(Material.SAPLING);
				ItemMeta santam = santa.getItemMeta();
				santam.setDisplayName(ChatColor.DARK_RED + "S"
						+ ChatColor.DARK_GREEN + "a" + ChatColor.DARK_RED + "n"
						+ ChatColor.DARK_GREEN + "t" + ChatColor.DARK_RED + "a");
				ArrayList<String> santalore = new ArrayList<String>();
				santalore.add("");
				santalore.add(ChatColor.DARK_RED + "S" + ChatColor.DARK_GREEN
						+ "a" + ChatColor.DARK_RED + "n" + ChatColor.DARK_GREEN
						+ "t" + ChatColor.DARK_RED + "a" + ChatColor.GRAY + "'"
						+ ChatColor.DARK_GREEN + "s " + ChatColor.DARK_RED
						+ "H" + ChatColor.DARK_GREEN + "e" + ChatColor.DARK_RED
						+ "l" + ChatColor.DARK_GREEN + "p" + ChatColor.DARK_RED
						+ "e" + ChatColor.DARK_GREEN + "r");
				santalore.add("");
				santalore.add(ChatColor.GOLD + "--- Click to Equip! ---");
				santam.setLore(santalore);
				santa.setItemMeta(santam);
				select.setItem(31, santa);
			} else {
				ItemStack santa = new ItemStack(Material.SAPLING);
				ItemMeta santam = santa.getItemMeta();
				santam.setDisplayName(ChatColor.DARK_RED + "S"
						+ ChatColor.DARK_GREEN + "a" + ChatColor.DARK_RED + "n"
						+ ChatColor.DARK_GREEN + "t" + ChatColor.DARK_RED + "a");
				ArrayList<String> santalore = new ArrayList<String>();
				santalore.add("");
				santalore.add(ChatColor.DARK_RED + "S" + ChatColor.DARK_GREEN
						+ "a" + ChatColor.DARK_RED + "n" + ChatColor.DARK_GREEN
						+ "t" + ChatColor.DARK_RED + "a" + ChatColor.GRAY + "'"
						+ ChatColor.DARK_GREEN + "s " + ChatColor.DARK_RED
						+ "H" + ChatColor.DARK_GREEN + "e" + ChatColor.DARK_RED
						+ "l" + ChatColor.DARK_GREEN + "p" + ChatColor.DARK_RED
						+ "e" + ChatColor.DARK_GREEN + "r");
				santalore.add("");
				santalore.add(ChatColor.RED + "It must be "
						+ ChatColor.DARK_RED + "Christmas " + ChatColor.RED
						+ "to use this kit!");
				santam.setLore(santalore);
				santa.setItemMeta(santam);
				select.setItem(31, santa);
			}

			if (plugin.getConfig().getBoolean("isThanksgiving")) {
				ItemStack turk = new ItemStack(Material.COOKED_CHICKEN);
				ItemMeta tm = turk.getItemMeta();
				tm.setDisplayName(ChatColor.YELLOW + "Thanksgiving");
				ArrayList<String> tl = new ArrayList<String>();
				tl.add("");
				tl.add(ChatColor.GOLD + "Thanksgiving Turckey!");
				tl.add("");
				tl.add(ChatColor.GOLD + "--- Click to Equip! ---");
				tm.setLore(tl);
				turk.setItemMeta(tm);
				select.setItem(33, turk);
			} else {
				ItemStack turk = new ItemStack(Material.COOKED_CHICKEN);
				ItemMeta tm = turk.getItemMeta();
				tm.setDisplayName(ChatColor.YELLOW + "Thanksgiving");
				ArrayList<String> tl = new ArrayList<String>();
				tl.add("");
				tl.add(ChatColor.GOLD + "Thanksgiving Turckey!");
				tl.add("");
				tl.add(ChatColor.RED + "It must be " + ChatColor.GOLD
						+ "Thanksgiving " + ChatColor.RED + "to use this kit!");
				tm.setLore(tl);
				turk.setItemMeta(tm);
				select.setItem(33, turk);
			}

			ItemStack warrior = new ItemStack(Material.IRON_SWORD);
			ItemMeta m = warrior.getItemMeta();
			m.setDisplayName(ChatColor.GREEN + "Warrior");
			ArrayList<String> lore = new ArrayList<String>();
			lore.add("");
			lore.add(ChatColor.LIGHT_PURPLE + "Your typical warrior");
			lore.add(ChatColor.LIGHT_PURPLE + "in shining armor!");
			lore.add("");
			lore.add(ChatColor.GOLD + "--- Click to Equip ---");
			m.setLore(lore);
			warrior.setItemMeta(m);
			select.setItem(25, warrior);

			ItemStack green = new ItemStack(Material.STAINED_GLASS_PANE, 1,
					(short) 15);
			ItemMeta greenm = green.getItemMeta();
			greenm.setDisplayName(" ");
			green.setItemMeta(greenm);

			for (int i = 0; i < select.getSize(); i++) {
				if (select.getItem(i) == null) {
					select.setItem(i, green);
				}
			}
			p.openInventory(select);
		} else if (p.getItemInHand().equals(shop)) {
			Bukkit.dispatchCommand(p, "buy");
			e.setCancelled(true);
		} else if (p.getItemInHand().equals(itemshop)) {
			Inventory ishop = Bukkit.createInventory(p, 27, ChatColor.GREEN
					+ "Item Shop");

			Potion leaping = new Potion(PotionType.JUMP, 2, false, false);
			ItemStack leapingPotion = leaping.toItemStack(1);
			ItemMeta pm = leapingPotion.getItemMeta();
			pm.setDisplayName(ChatColor.RED + "Potion of Leaping");
			ArrayList<String> plore = new ArrayList<String>();
			plore.add(ChatColor.GRAY + "Price: " + ChatColor.GOLD + "100");
			pm.setLore(plore);
			leapingPotion.setItemMeta(pm);
			ishop.setItem(0, leapingPotion);

			Potion speed = new Potion(PotionType.SPEED, 2, false, false);
			ItemStack speedPotion = speed.toItemStack(1);
			ItemMeta sm = speedPotion.getItemMeta();
			sm.setDisplayName(ChatColor.RED + "Potion of Speed");
			ArrayList<String> slore1 = new ArrayList<String>();
			slore1.add(ChatColor.GRAY + "Price: " + ChatColor.GOLD + "100");
			sm.setLore(slore1);
			speedPotion.setItemMeta(sm);
			ishop.setItem(1, speedPotion);

			Potion regen = new Potion(PotionType.REGEN, 2, false, false);
			ItemStack regenPotion = regen.toItemStack(1);
			ItemMeta regenm = regenPotion.getItemMeta();
			regenm.setDisplayName(ChatColor.RED + "Potion of Regen");
			ArrayList<String> slore11 = new ArrayList<String>();
			slore11.add(ChatColor.GRAY + "Price: " + ChatColor.GOLD + "100");
			regenm.setLore(slore11);
			regenPotion.setItemMeta(regenm);
			ishop.setItem(2, regenPotion);

			Potion posion = new Potion(PotionType.POISON, 2, true, false);
			ItemStack poison1 = posion.toItemStack(1);
			ItemMeta psm = poison1.getItemMeta();
			psm.setDisplayName(ChatColor.RED + "Potion of Poison");
			ArrayList<String> qlore = new ArrayList<String>();
			qlore.add(ChatColor.GRAY + "Price: " + ChatColor.GOLD + "100");
			psm.setLore(qlore);
			poison1.setItemMeta(psm);
			ishop.setItem(3, poison1);

			ItemStack golden = new ItemStack(Material.GOLDEN_APPLE);
			ItemMeta gm = golden.getItemMeta();
			gm.setDisplayName(ChatColor.RED + "Golden Apple");
			ArrayList<String> glore = new ArrayList<String>();
			glore.add(ChatColor.GRAY + "Price: " + ChatColor.GOLD + "75");
			gm.setLore(glore);
			golden.setItemMeta(gm);
			ishop.setItem(4, golden);

			ItemStack Grenade = new ItemStack(Material.SLIME_BALL);
			ItemMeta Grenademeta = Grenade.getItemMeta();
			Grenademeta.setDisplayName(ChatColor.RED + "Lethal Grenade");
			ArrayList<String> Grenadelore = new ArrayList<String>();
			Grenadelore
					.add(ChatColor.GRAY + "Price: " + ChatColor.GOLD + "250");
			Grenadelore.add(ChatColor.GREEN + "Damages all nearby players. "
					+ ChatColor.GRAY + "(+10 Damage)");
			Grenademeta.setLore(Grenadelore);
			Grenade.setItemMeta(Grenademeta);
			ishop.setItem(5, Grenade);

			ItemStack slow = new ItemStack(Material.MAGMA_CREAM);
			ItemMeta slowm = slow.getItemMeta();
			slowm.setDisplayName(ChatColor.RED + "Slowness Grenade");
			ArrayList<String> slowlore = new ArrayList<String>();
			slowlore.add(ChatColor.GRAY + "Price: " + ChatColor.GOLD + "150");
			slowlore.add(ChatColor.GREEN
					+ "Slows down all nearby players for 10 seconds.");
			slowm.setLore(slowlore);
			slow.setItemMeta(slowm);
			ishop.setItem(6, slow);

			ItemStack flash = new ItemStack(Material.SULPHUR);
			ItemMeta fm = flash.getItemMeta();
			fm.setDisplayName(ChatColor.RED + "Flash Grenade");
			ArrayList<String> flashlore = new ArrayList<String>();
			flashlore.add(ChatColor.GRAY + "Price: " + ChatColor.GOLD + "200");
			flashlore.add(ChatColor.GREEN
					+ "Blind all nearby players for 10 seconds.");
			fm.setLore(flashlore);
			flash.setItemMeta(fm);
			ishop.setItem(7, flash);

			ItemStack fix = new ItemStack(Material.ANVIL);
			ItemMeta fixmeta = fix.getItemMeta();
			fixmeta.setDisplayName(ChatColor.RED + "Armor Repair");
			ArrayList<String> fixlore = new ArrayList<String>();
			fixlore.add(ChatColor.GRAY + "Price: " + ChatColor.GOLD + "500");
			fixlore.add(ChatColor.GREEN + "Repair all of your armor!");
			fixmeta.setLore(fixlore);
			fix.setItemMeta(fixmeta);
			ishop.setItem(8, fix);

			ItemStack green = new ItemStack(Material.STAINED_GLASS_PANE, 1,
					(short) 15);
			ItemMeta greenm = green.getItemMeta();
			greenm.setDisplayName(ChatColor.RED + "Coming Soon...");
			green.setItemMeta(greenm);

			for (int i = 0; i < ishop.getSize(); i++) {
				if (ishop.getItem(i) == null) {
					ishop.setItem(i, green);
				}
			}
			p.openInventory(ishop);

		} else if (p.getItemInHand().equals(stats)) {
			ItemStack item = new ItemStack(Material.SKULL_ITEM);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.translateAlternateColorCodes('&',
					ChatColor.DARK_RED + "Deaths:"));
			meta.setLore(Arrays.asList(new String[] { "§7"
					+ this.plugin.getConfig().getInt(
							new StringBuilder("players.")
									.append(p.getUniqueId().toString())
									.append(".deaths").toString()) }));
			item.setItemMeta(meta);

			ItemStack item2 = new ItemStack(Material.DIAMOND_SWORD);
			ItemMeta meta2 = item2.getItemMeta();
			meta2.setDisplayName(ChatColor.translateAlternateColorCodes('&',
					ChatColor.GREEN + "Kills:"));
			meta2.setLore(Arrays.asList(new String[] { "§7"
					+ this.plugin.getConfig().getInt(
							new StringBuilder("players.")
									.append(p.getUniqueId().toString())
									.append(".kills").toString()) }));
			item2.setItemMeta(meta2);

			ItemStack item4 = new ItemStack(Material.DOUBLE_PLANT);
			ItemMeta meta4 = item4.getItemMeta();
			meta4.setDisplayName(ChatColor.translateAlternateColorCodes('&',
					ChatColor.GOLD + "Balance:"));
			int c = plugin.getConfig().getInt(
					"players." + p.getUniqueId().toString() + ".coins");
			meta4.setLore(Arrays.asList(new String[] { "§7" + c }));
			item4.setItemMeta(meta4);

			ItemStack item5 = new ItemStack(Material.NAME_TAG);
			ItemMeta meta5 = item5.getItemMeta();
			meta5.setDisplayName(ChatColor.translateAlternateColorCodes('&',
					ChatColor.DARK_AQUA + "Killstreak:"));
			meta5.setLore(Arrays.asList(new String[] { "§7"
					+ this.plugin.getConfig().getInt(
							new StringBuilder("players.")
									.append(p.getUniqueId().toString())
									.append(".killstreak").toString()) }));
			item5.setItemMeta(meta5);

			ItemStack green = new ItemStack(Material.STAINED_GLASS_PANE, 1,
					(short) 15);
			ItemMeta greenm = green.getItemMeta();
			greenm.setDisplayName(" ");
			green.setItemMeta(greenm);

			ItemStack skull = new ItemStack(397, 1, (short) 3);
			SkullMeta meta3 = (SkullMeta) skull.getItemMeta();
			meta3.setDisplayName(ChatColor.AQUA + "K/D Ratio:");
			double kills = this.plugin.getConfig().getInt(
					"players." + p.getUniqueId().toString() + ".kills");
			double deaths = this.plugin.getConfig().getInt(
					"players." + p.getUniqueId().toString() + ".deaths");
			if (deaths == 0.0D) {
				deaths = 1.0D;
			}
			String KD = String.format("%.2f",
					new Object[] { Double.valueOf(kills / deaths) });
			meta3.setLore(Arrays.asList(new String[] { "§7" + KD }));
			meta3.setOwner(p.getName());
			skull.setItemMeta(meta3);
			this.inv = Bukkit.createInventory(null, 27, ChatColor.DARK_RED
					+ "Stats");
			this.inv.setItem(11, item2);
			this.inv.setItem(13, skull);
			this.inv.setItem(15, item);
			this.inv.setItem(21, item4);
			this.inv.setItem(23, item5);
			for (int i = 0; i < this.inv.getSize(); i++) {
				if (this.inv.getItem(i) == null) {
					this.inv.setItem(i, green);
				}
			}
			p.openInventory(this.inv);
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		Inventory i = e.getInventory();
		Player p = (Player) e.getWhoClicked();

		Inventory inv2 = e.getInventory();
		if (inv2.getTitle().equalsIgnoreCase(ChatColor.DARK_RED + "Stats")) {
			e.setCancelled(true);
		}

		Inventory sh = e.getInventory();
		if (sh.getTitle().equalsIgnoreCase(ChatColor.GREEN + "Item Shop")) {
			if (e.getCurrentItem().getItemMeta().getDisplayName()
					.equalsIgnoreCase(ChatColor.RED + "Potion of Leaping")) {
				if (hasEnough(p, 100)) {
					if (p.getInventory().firstEmpty() == -1) {
						p.sendMessage(ChatColor.RED
								+ "Make room in your inventory!");
						e.setCancelled(true);
					} else {
						e.setCancelled(true);
						int coins = plugin.getConfig().getInt(
								"players." + p.getUniqueId().toString()
										+ ".coins");
						plugin.getConfig().set(
								"players." + p.getUniqueId().toString()
										+ ".coins",
								Integer.valueOf(coins - 100));
						plugin.saveConfig();
						p.sendMessage(ChatColor.GREEN
								+ "You have purchased a Potion of Leaping!");
						Potion leaping = new Potion(PotionType.JUMP, 2, false,
								false);
						ItemStack leapingPotion = leaping.toItemStack(1);
						p.getInventory().addItem(leapingPotion);
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
								"update");
					}
				} else {
					e.setCancelled(true);
					p.sendMessage(ChatColor.RED
							+ "You don't have enough coins to buy this!");
				}
			} else if (e.getCurrentItem().getItemMeta().getDisplayName()
					.equalsIgnoreCase(ChatColor.RED + "Coming Soon...")) {
				e.setCancelled(true);
			} else if (e.getCurrentItem().getItemMeta().getDisplayName()
					.equalsIgnoreCase(ChatColor.RED + "Armor Repair")) {
				if (hasEnough(p, 500)) {
					e.setCancelled(true);
					int coins = plugin.getConfig().getInt(
							"players." + p.getUniqueId().toString() + ".coins");
					plugin.getConfig().set(
							"players." + p.getUniqueId().toString() + ".coins",
							Integer.valueOf(coins - 500));
					plugin.saveConfig();
					p.sendMessage(ChatColor.GREEN
							+ "You have successfully repaired your armor!");
					if (p.getInventory().getHelmet() != null) {
						Material old = p.getInventory().getHelmet().getType();
						ItemStack helm = new ItemStack(old);
						p.getInventory().setHelmet(helm);
					}
					if (p.getInventory().getChestplate() != null) {
						Material old = p.getInventory().getChestplate()
								.getType();
						ItemStack chest = new ItemStack(old);
						p.getInventory().setChestplate(chest);
					}
					if (p.getInventory().getLeggings() != null) {
						Material old = p.getInventory().getLeggings().getType();
						ItemStack legs = new ItemStack(old);
						p.getInventory().setLeggings(legs);
					}
					if (p.getInventory().getBoots() != null) {
						Material old = p.getInventory().getBoots().getType();
						ItemStack boots = new ItemStack(old);
						p.getInventory().setBoots(boots);
					}
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "update");
				} else {
					e.setCancelled(true);
					p.sendMessage(ChatColor.RED
							+ "You don't have enough coins to buy this!");
				}
			} else if (e.getCurrentItem().getItemMeta().getDisplayName()
					.equalsIgnoreCase(ChatColor.RED + "Slowness Grenade")) {
				if (hasEnough(p, 150)) {
					if (p.getInventory().firstEmpty() == -1) {
						p.sendMessage(ChatColor.RED
								+ "Make room in your inventory!");
						e.setCancelled(true);
					} else {
						e.setCancelled(true);
						int coins = plugin.getConfig().getInt(
								"players." + p.getUniqueId().toString()
										+ ".coins");
						plugin.getConfig().set(
								"players." + p.getUniqueId().toString()
										+ ".coins",
								Integer.valueOf(coins - 150));
						plugin.saveConfig();
						p.sendMessage(ChatColor.GREEN
								+ "You have purchased a Slowness Grenade!");
						ItemStack slow = new ItemStack(Material.MAGMA_CREAM);
						ItemMeta slowm = slow.getItemMeta();
						slowm.setDisplayName(ChatColor.RED + "Slowness Grenade");
						slow.setItemMeta(slowm);
						p.getInventory().addItem(slow);
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
								"update");
					}
				} else {
					e.setCancelled(true);
					p.sendMessage(ChatColor.RED
							+ "You don't have enough coins to buy this!");
				}
			} else if (e.getCurrentItem().getItemMeta().getDisplayName()
					.equalsIgnoreCase(ChatColor.RED + "Flash Grenade")) {
				if (hasEnough(p, 200)) {
					if (p.getInventory().firstEmpty() == -1) {
						p.sendMessage(ChatColor.RED
								+ "Make room in your inventory!");
						e.setCancelled(true);
					} else {
						e.setCancelled(true);
						int coins = plugin.getConfig().getInt(
								"players." + p.getUniqueId().toString()
										+ ".coins");
						plugin.getConfig().set(
								"players." + p.getUniqueId().toString()
										+ ".coins",
								Integer.valueOf(coins - 200));
						plugin.saveConfig();
						p.sendMessage(ChatColor.GREEN
								+ "You have purchased a Flash Grenade!");
						ItemStack Grenade = new ItemStack(Material.SULPHUR);
						ItemMeta Grenademeta = Grenade.getItemMeta();
						Grenademeta.setDisplayName(ChatColor.RED
								+ "Flash Grenade");
						Grenade.setItemMeta(Grenademeta);
						p.getInventory().addItem(Grenade);
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
								"update");
					}
				} else {
					e.setCancelled(true);
					p.sendMessage(ChatColor.RED
							+ "You don't have enough coins to buy this!");
				}
			} else if (e.getCurrentItem().getItemMeta().getDisplayName()
					.equalsIgnoreCase(ChatColor.RED + "Lethal Grenade")) {
				if (hasEnough(p, 250)) {
					if (p.getInventory().firstEmpty() == -1) {
						p.sendMessage(ChatColor.RED
								+ "Make room in your inventory!");
						e.setCancelled(true);
					} else {
						e.setCancelled(true);
						int coins = plugin.getConfig().getInt(
								"players." + p.getUniqueId().toString()
										+ ".coins");
						plugin.getConfig().set(
								"players." + p.getUniqueId().toString()
										+ ".coins",
								Integer.valueOf(coins - 250));
						plugin.saveConfig();
						p.sendMessage(ChatColor.GREEN
								+ "You have purchased a Lethal Grenade!");
						ItemStack Grenade = new ItemStack(Material.SLIME_BALL);
						ItemMeta Grenademeta = Grenade.getItemMeta();
						Grenademeta.setDisplayName(ChatColor.RED
								+ "Lethal Grenade");
						Grenade.setItemMeta(Grenademeta);
						p.getInventory().addItem(Grenade);
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
								"update");
					}
				} else {
					e.setCancelled(true);
					p.sendMessage(ChatColor.RED
							+ "You don't have enough coins to buy this!");
				}
			} else if (e.getCurrentItem().getItemMeta().getDisplayName()
					.equalsIgnoreCase(ChatColor.RED + "Potion of Poison")) {
				if (hasEnough(p, 100)) {
					if (p.getInventory().firstEmpty() == -1) {
						p.sendMessage(ChatColor.RED
								+ "Make room in your inventory!");
						e.setCancelled(true);
					} else {
						e.setCancelled(true);
						int coins = plugin.getConfig().getInt(
								"players." + p.getUniqueId().toString()
										+ ".coins");
						plugin.getConfig().set(
								"players." + p.getUniqueId().toString()
										+ ".coins",
								Integer.valueOf(coins - 100));
						plugin.saveConfig();
						p.sendMessage(ChatColor.GREEN
								+ "You have purchased a Potion of Poison!");
						Potion posion = new Potion(PotionType.POISON, 2, true,
								false);
						ItemStack poison1 = posion.toItemStack(1);
						p.getInventory().addItem(poison1);
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
								"update");
					}
				} else {
					e.setCancelled(true);
					p.sendMessage(ChatColor.RED
							+ "You don't have enough coins to buy this!");
				}
			} else if (e.getCurrentItem().getItemMeta().getDisplayName()
					.equalsIgnoreCase(ChatColor.RED + "Golden Apple")) {
				if (hasEnough(p, 75)) {
					if (p.getInventory().firstEmpty() == -1) {
						p.sendMessage(ChatColor.RED
								+ "Make room in your inventory!");
						e.setCancelled(true);
					} else {
						e.setCancelled(true);
						int coins = plugin.getConfig().getInt(
								"players." + p.getUniqueId().toString()
										+ ".coins");
						plugin.getConfig()
								.set("players." + p.getUniqueId().toString()
										+ ".coins", Integer.valueOf(coins - 75));
						plugin.saveConfig();
						p.sendMessage(ChatColor.GREEN
								+ "You have purchased a Golden Apple!");
						p.getInventory().addItem(
								new ItemStack(Material.GOLDEN_APPLE));
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
								"update");
					}
				} else {
					e.setCancelled(true);
					p.sendMessage(ChatColor.RED
							+ "You don't have enough coins to buy this!");
				}
			} else if (e.getCurrentItem().getItemMeta().getDisplayName()
					.equalsIgnoreCase(ChatColor.RED + "Potion of Regen")) {
				if (hasEnough(p, 100)) {
					if (p.getInventory().firstEmpty() == -1) {
						p.sendMessage(ChatColor.RED
								+ "Make room in your inventory!");
						e.setCancelled(true);
					} else {
						e.setCancelled(true);
						int coins = plugin.getConfig().getInt(
								"players." + p.getUniqueId().toString()
										+ ".coins");
						plugin.getConfig().set(
								"players." + p.getUniqueId().toString()
										+ ".coins",
								Integer.valueOf(coins - 100));
						plugin.saveConfig();
						p.sendMessage(ChatColor.GREEN
								+ "You have purchased a Potion of Regen!");
						Potion regen = new Potion(PotionType.REGEN, 2, false,
								false);
						ItemStack regenPotion = regen.toItemStack(1);
						p.getInventory().addItem(regenPotion);
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
								"update");
					}
				} else {
					e.setCancelled(true);
					p.sendMessage(ChatColor.RED
							+ "You don't have enough coins to buy this!");
				}
			} else if (e.getCurrentItem().getItemMeta().getDisplayName()
					.equalsIgnoreCase(ChatColor.RED + "Potion of Speed")) {
				if (hasEnough(p, 100)) {
					if (p.getInventory().firstEmpty() == -1) {
						p.sendMessage(ChatColor.RED
								+ "Make room in your inventory!");
						e.setCancelled(true);
					} else {
						e.setCancelled(true);
						int coins = plugin.getConfig().getInt(
								"players." + p.getUniqueId().toString()
										+ ".coins");
						plugin.getConfig().set(
								"players." + p.getUniqueId().toString()
										+ ".coins",
								Integer.valueOf(coins - 100));
						plugin.saveConfig();
						p.sendMessage(ChatColor.GREEN
								+ "You have purchased a Potion of Speed!");
						Potion speed = new Potion(PotionType.SPEED, 2, false,
								false);
						ItemStack speedPotion = speed.toItemStack(1);
						p.getInventory().addItem(speedPotion);
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
								"update");
					}
					e.setCancelled(true);
				} else {
					e.setCancelled(true);
					p.sendMessage(ChatColor.RED
							+ "You don't have enough coins to buy this!");
				}
			} else {
				e.setCancelled(true);
			}
		} else if (i.getTitle().equalsIgnoreCase(
				ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Kit Selector")) {
			List<String> playerKits = plugin.getConfig().getStringList(
					"players." + p.getUniqueId() + ".kits");
			if (e.getCurrentItem().getItemMeta().getDisplayName()
					.equalsIgnoreCase(ChatColor.GREEN + "Pvp")) {
				if (playerKits.contains("Pvp")) {
					Bukkit.dispatchCommand(p, "pvp");
					e.setCancelled(true);
					p.closeInventory();
				} else {
					p.sendMessage(KitPvp.noPerm);
					e.setCancelled(true);
				}
			} else if (e.getCurrentItem().getItemMeta().getDisplayName()
					.equalsIgnoreCase(ChatColor.GREEN + "Fisherman")) {
				if (plugin.getConfig().getInt(
						"players." + p.getUniqueId().toString() + ".1v1wins") >= 10) {
					Bukkit.dispatchCommand(p, "fisherman");
					e.setCancelled(true);
					p.closeInventory();
				} else {
					p.sendMessage(ChatColor.RED + "You need 10 1v1 "
							+ ChatColor.GREEN + "wins" + ChatColor.RED + "!");
					e.setCancelled(true);
				}
			} else if (e.getCurrentItem().getItemMeta().getDisplayName()
					.equalsIgnoreCase(ChatColor.GREEN + "Ghost")) {
				if (plugin.getConfig().getInt(
						"players." + p.getUniqueId().toString() + ".1v1wins") >= 20) {
					Bukkit.dispatchCommand(p, "ghost");
					e.setCancelled(true);
					p.closeInventory();
				} else {
					p.sendMessage(ChatColor.RED + "You need 20 1v1 "
							+ ChatColor.GREEN + "wins" + ChatColor.RED + "!");
					e.setCancelled(true);
				}
			} else if (e.getCurrentItem().getItemMeta().getDisplayName()
					.equalsIgnoreCase(ChatColor.GREEN + "Snowman")) {
				if (plugin.getConfig().getInt(
						"players." + p.getUniqueId().toString() + ".1v1wins") >= 30) {
					Bukkit.dispatchCommand(p, "snowman");
					e.setCancelled(true);
					p.closeInventory();
				} else {
					p.sendMessage(ChatColor.RED + "You need 30 1v1 "
							+ ChatColor.GREEN + "wins" + ChatColor.RED + "!");
					e.setCancelled(true);
				}
			} else if (e.getCurrentItem().getItemMeta().getDisplayName()
					.equalsIgnoreCase(ChatColor.GREEN + "Thor")) {
				if (plugin.getConfig().getInt(
						"players." + p.getUniqueId().toString() + ".1v1wins") >= 40) {
					Bukkit.dispatchCommand(p, "thor");
					e.setCancelled(true);
					p.closeInventory();
				} else {
					p.sendMessage(ChatColor.RED + "You need 40 1v1 "
							+ ChatColor.GREEN + "wins" + ChatColor.RED + "!");
					e.setCancelled(true);
				}
			} else if (e.getCurrentItem().getItemMeta().getDisplayName()
					.equalsIgnoreCase(ChatColor.GREEN + "Prestige")) {
				if (playerKits.contains("Prestige")) {
					Bukkit.dispatchCommand(p, "prestigeclass");
					e.setCancelled(true);
					p.closeInventory();
				} else {
					p.sendMessage(KitPvp.noPerm);
					e.setCancelled(true);
				}
			} else if (e.getCurrentItem().getItemMeta().getDisplayName()
					.equalsIgnoreCase(ChatColor.GREEN + "Archer")) {
				if (playerKits.contains("Archer")) {
					Bukkit.dispatchCommand(p, "archer");
					e.setCancelled(true);
					p.closeInventory();
				} else {
					p.sendMessage(KitPvp.noPerm);
					e.setCancelled(true);
				}
			} else if (e.getCurrentItem().getItemMeta().getDisplayName()
					.equalsIgnoreCase(ChatColor.GOLD + "Halloween")) {
				if (plugin.getConfig().getBoolean("isHalloween")) {
					Bukkit.dispatchCommand(p, "halloween");
					e.setCancelled(true);
					p.closeInventory();
				} else {
					p.sendMessage(KitPvp.noPerm);
					e.setCancelled(true);
				}
			} else if (e
					.getCurrentItem()
					.getItemMeta()
					.getDisplayName()
					.equalsIgnoreCase(
							ChatColor.DARK_RED + "S" + ChatColor.DARK_GREEN
									+ "a" + ChatColor.DARK_RED + "n"
									+ ChatColor.DARK_GREEN + "t"
									+ ChatColor.DARK_RED + "a")) {
				if (plugin.getConfig().getBoolean("isChristmas")) {
					Bukkit.dispatchCommand(p, "christmas");
					e.setCancelled(true);
					p.closeInventory();
				} else {
					p.sendMessage(KitPvp.noPerm);
					e.setCancelled(true);
				}
			} else if (e.getCurrentItem().getItemMeta().getDisplayName()
					.equalsIgnoreCase(ChatColor.YELLOW + "Thanksgiving")) {
				if (plugin.getConfig().getBoolean("isThanksgiving")) {
					Bukkit.dispatchCommand(p, "thanksgiving");
					e.setCancelled(true);
					p.closeInventory();
				} else {
					p.sendMessage(KitPvp.noPerm);
					e.setCancelled(true);
				}
			} else if (e.getCurrentItem().getItemMeta().getDisplayName()
					.equalsIgnoreCase(ChatColor.GREEN + "Ninja")) {
				if (playerKits.contains("Ninja")) {
					Bukkit.dispatchCommand(p, "ninja");
					e.setCancelled(true);
					p.closeInventory();
				} else {
					p.sendMessage(KitPvp.noPerm);
					e.setCancelled(true);
				}
			} else if (e.getCurrentItem().getItemMeta().getDisplayName()
					.equalsIgnoreCase(ChatColor.GREEN + "Porcupine")) {
				if (playerKits.contains("Porcupine")) {
					Bukkit.dispatchCommand(p, "porcupine");
					e.setCancelled(true);
					p.closeInventory();
				} else {
					p.sendMessage(KitPvp.noPerm);
					e.setCancelled(true);
				}
			} else if (e.getCurrentItem().getItemMeta().getDisplayName()
					.equalsIgnoreCase(ChatColor.GREEN + "Pyro")) {
				if (playerKits.contains("Pyro")) {
					Bukkit.dispatchCommand(p, "pyro");
					e.setCancelled(true);
					p.closeInventory();
				} else {
					p.sendMessage(KitPvp.noPerm);
					e.setCancelled(true);
				}
			} else if (e.getCurrentItem().getItemMeta().getDisplayName()
					.equalsIgnoreCase(ChatColor.GREEN + "Scorpion")) {
				if (playerKits.contains("Scorpion")) {
					Bukkit.dispatchCommand(p, "scorpion");
					e.setCancelled(true);
					p.closeInventory();
				} else {
					p.sendMessage(KitPvp.noPerm);
					e.setCancelled(true);
				}
			} else if (e.getCurrentItem().getItemMeta().getDisplayName()
					.equalsIgnoreCase(ChatColor.GREEN + "Heavy")) {
				if (playerKits.contains("Heavy")) {
					Bukkit.dispatchCommand(p, "heavy");
					e.setCancelled(true);
					p.closeInventory();
				} else {
					p.sendMessage(KitPvp.noPerm);
					e.setCancelled(true);
				}
			} else if (e.getCurrentItem().getItemMeta().getDisplayName()
					.equalsIgnoreCase(ChatColor.GREEN + "Kangaroo")) {
				if (playerKits.contains("Kangaroo")) {
					Bukkit.dispatchCommand(p, "kangaroo");
					e.setCancelled(true);
					p.closeInventory();
				} else {
					if (hasEnough(p, 1500)) {
						e.setCancelled(true);
						int coins = plugin.getConfig().getInt(
								"players." + p.getUniqueId().toString()
										+ ".coins");
						plugin.getConfig().set(
								"players." + p.getUniqueId().toString()
										+ ".coins",
								Integer.valueOf(coins - 1500));
						playerKits.add("Kangaroo");
						plugin.getConfig().set(
								"players." + p.getUniqueId().toString()
										+ ".kits", playerKits);
						plugin.saveConfig();
						p.sendMessage(ChatColor.GREEN
								+ "You have purchased the Kangaroo kit!");
						Bukkit.dispatchCommand(p, "kangaroo");
						p.closeInventory();

						for (Player o : Bukkit.getServer().getOnlinePlayers()) {
							ScoreboardManager manager = Bukkit
									.getScoreboardManager();
							Scoreboard board = manager.getNewScoreboard();

							Objective obj = board.registerNewObjective("Test",
									"Test2");
							obj.setDisplayName("§8> §4§nFlameNetwork§r §8<");
							obj.setDisplaySlot(DisplaySlot.SIDEBAR);
							Score score = obj.getScore("§8» §aKills§7: §7"
									+ plugin.getConfig().getInt(
											"players."
													+ o.getUniqueId()
															.toString()
													+ ".kills"));
							score.setScore(12);

							Score score2 = obj.getScore("§8» §aDeaths§7: §7"
									+ plugin.getConfig().getInt(
											"players."
													+ o.getUniqueId()
															.toString()
													+ ".deaths"));
							score2.setScore(11);

							double kills = plugin.getConfig().getInt(
									"players." + o.getUniqueId().toString()
											+ ".kills");
							double deaths = plugin.getConfig().getInt(
									"players." + o.getUniqueId().toString()
											+ ".deaths");
							if (deaths == 0.0D) {
								deaths = 1.0D;
							}
							String KD = String.format(
									"%.2f",
									new Object[] { Double.valueOf(kills
											/ deaths) });

							Score kd = obj
									.getScore("§8» §aK/D§7: §7"
											+ Arrays.asList(new String[] { "§7"
													+ KD }));
							kd.setScore(10);

							Score curks = obj
									.getScore("§8» §aKill Streak§8: §7"
											+ plugin.getConfig().getInt(
													"players."
															+ o.getUniqueId()
																	.toString()
															+ ".curkillstreak"));
							curks.setScore(9);
							Score bl2 = obj.getScore(" ");
							bl2.setScore(8);

							Score level = obj.getScore("§8» §bLevel§7: §7"
									+ plugin.getConfig().getInt(
											"players."
													+ o.getUniqueId()
															.toString()
													+ ".level"));
							level.setScore(7);
							Score killsneeded = obj
									.getScore("§8» §bKills Needed§7: 5");
							killsneeded.setScore(6);
							Score score4 = obj.getScore("§8» §3Coins§7: "
									+ plugin.getConfig().getInt(
											"players."
													+ o.getUniqueId()
															.toString()
													+ ".coins"));
							score4.setScore(5);

							Score bl3 = obj.getScore("");
							bl3.setScore(4);

							Score s5 = obj.getScore("§8» §a1v1 Wins§7: "
									+ plugin.getConfig().getInt(
											"players."
													+ o.getUniqueId()
															.toString()
													+ ".1v1wins"));
							s5.setScore(3);

							Score s6 = obj.getScore("§8» §a1v1 Loses§7: "
									+ plugin.getConfig().getInt(
											"players."
													+ o.getUniqueId()
															.toString()
													+ ".1v1loses"));
							s6.setScore(2);

							if (HillMoveEvent.win.get(o.getName()) != null) {
								Integer pt = HillMoveEvent.win.get(o.getName());
								Score hilly = obj
										.getScore("§8» §aHill Points§7: " + pt);
								hilly.setScore(1);
							} else {
								Score hilly = obj
										.getScore("§8» §aHill Points§7: 0");
								hilly.setScore(1);
							}

							o.setScoreboard(board);
						}
					} else {
						p.sendMessage(KitPvp.noPerm);
						p.sendMessage(ChatColor.RED + "You need "
								+ ChatColor.YELLOW + "1500 " + ChatColor.RED
								+ "coins for this kit!");
						e.setCancelled(true);
					}
				}
			} else if (e.getCurrentItem().getItemMeta().getDisplayName()
					.equalsIgnoreCase(ChatColor.GREEN + "Striker")) {
				if (playerKits.contains("Striker")) {
					Bukkit.dispatchCommand(p, "striker");
					e.setCancelled(true);
					p.closeInventory();
				} else {
					if (hasEnough(p, 2500)) {
						e.setCancelled(true);
						int coins = plugin.getConfig().getInt(
								"players." + p.getUniqueId().toString()
										+ ".coins");
						plugin.getConfig().set(
								"players." + p.getUniqueId().toString()
										+ ".coins",
								Integer.valueOf(coins - 2500));
						playerKits.add("Striker");
						plugin.getConfig().set(
								"players." + p.getUniqueId().toString()
										+ ".kits", playerKits);
						plugin.saveConfig();
						p.sendMessage(ChatColor.GREEN
								+ "You have purchased the Striker kit!");
						Bukkit.dispatchCommand(p, "striker");
						p.closeInventory();

						for (Player o : Bukkit.getServer().getOnlinePlayers()) {
							ScoreboardManager manager = Bukkit
									.getScoreboardManager();
							Scoreboard board = manager.getNewScoreboard();

							Objective obj = board.registerNewObjective("Test",
									"Test2");
							obj.setDisplayName("§8> §4§nFlameNetwork§r §8<");
							obj.setDisplaySlot(DisplaySlot.SIDEBAR);
							Score score = obj.getScore("§8» §aKills§7: §7"
									+ plugin.getConfig().getInt(
											"players."
													+ o.getUniqueId()
															.toString()
													+ ".kills"));
							score.setScore(12);

							Score score2 = obj.getScore("§8» §aDeaths§7: §7"
									+ plugin.getConfig().getInt(
											"players."
													+ o.getUniqueId()
															.toString()
													+ ".deaths"));
							score2.setScore(11);

							double kills = plugin.getConfig().getInt(
									"players." + o.getUniqueId().toString()
											+ ".kills");
							double deaths = plugin.getConfig().getInt(
									"players." + o.getUniqueId().toString()
											+ ".deaths");
							if (deaths == 0.0D) {
								deaths = 1.0D;
							}
							String KD = String.format(
									"%.2f",
									new Object[] { Double.valueOf(kills
											/ deaths) });

							Score kd = obj
									.getScore("§8» §aK/D§7: §7"
											+ Arrays.asList(new String[] { "§7"
													+ KD }));
							kd.setScore(10);

							Score curks = obj
									.getScore("§8» §aKill Streak§8: §7"
											+ plugin.getConfig().getInt(
													"players."
															+ o.getUniqueId()
																	.toString()
															+ ".curkillstreak"));
							curks.setScore(9);
							Score bl2 = obj.getScore(" ");
							bl2.setScore(8);

							Score level = obj.getScore("§8» §bLevel§7: §7"
									+ plugin.getConfig().getInt(
											"players."
													+ o.getUniqueId()
															.toString()
													+ ".level"));
							level.setScore(7);
							Score killsneeded = obj
									.getScore("§8» §bKills Needed§7: 5");
							killsneeded.setScore(6);
							Score score4 = obj.getScore("§8» §3Coins§7: "
									+ plugin.getConfig().getInt(
											"players."
													+ o.getUniqueId()
															.toString()
													+ ".coins"));
							score4.setScore(5);

							Score bl3 = obj.getScore("");
							bl3.setScore(4);

							Score s5 = obj.getScore("§8» §a1v1 Wins§7: "
									+ plugin.getConfig().getInt(
											"players."
													+ o.getUniqueId()
															.toString()
													+ ".1v1wins"));
							s5.setScore(3);

							Score s6 = obj.getScore("§8» §a1v1 Loses§7: "
									+ plugin.getConfig().getInt(
											"players."
													+ o.getUniqueId()
															.toString()
													+ ".1v1loses"));
							s6.setScore(2);

							if (HillMoveEvent.win.get(o.getName()) != null) {
								Integer pt = HillMoveEvent.win.get(o.getName());
								Score hilly = obj
										.getScore("§8» §aHill Points§7: " + pt);
								hilly.setScore(1);
							} else {
								Score hilly = obj
										.getScore("§8» §aHill Points§7: 0");
								hilly.setScore(1);
							}

							o.setScoreboard(board);
						}
					} else {
						p.sendMessage(KitPvp.noPerm);
						p.sendMessage(ChatColor.RED + "You need "
								+ ChatColor.YELLOW + "2500 " + ChatColor.RED
								+ "coins for this kit!");
						e.setCancelled(true);
					}
				}
			} else if (e.getCurrentItem().getItemMeta().getDisplayName()
					.equalsIgnoreCase(ChatColor.GREEN + "Titan")) {
				if (playerKits.contains("Titan")) {
					Bukkit.dispatchCommand(p, "titan");
					e.setCancelled(true);
					p.closeInventory();
				} else {
					if (hasEnough(p, 5000)) {
						e.setCancelled(true);
						int coins = plugin.getConfig().getInt(
								"players." + p.getUniqueId().toString()
										+ ".coins");
						plugin.getConfig().set(
								"players." + p.getUniqueId().toString()
										+ ".coins",
								Integer.valueOf(coins - 5000));
						playerKits.add("Titan");
						plugin.getConfig().set(
								"players." + p.getUniqueId().toString()
										+ ".kits", playerKits);
						plugin.saveConfig();
						p.sendMessage(ChatColor.GREEN
								+ "You have purchased the Titan kit!");
						Bukkit.dispatchCommand(p, "titan");
						p.closeInventory();

						for (Player o : Bukkit.getServer().getOnlinePlayers()) {
							ScoreboardManager manager = Bukkit
									.getScoreboardManager();
							Scoreboard board = manager.getNewScoreboard();

							Objective obj = board.registerNewObjective("Test",
									"Test2");
							obj.setDisplayName("§8> §4§nFlameNetwork§r §8<");
							obj.setDisplaySlot(DisplaySlot.SIDEBAR);
							Score score = obj.getScore("§8» §aKills§7: §7"
									+ plugin.getConfig().getInt(
											"players."
													+ o.getUniqueId()
															.toString()
													+ ".kills"));
							score.setScore(12);

							Score score2 = obj.getScore("§8» §aDeaths§7: §7"
									+ plugin.getConfig().getInt(
											"players."
													+ o.getUniqueId()
															.toString()
													+ ".deaths"));
							score2.setScore(11);

							double kills = plugin.getConfig().getInt(
									"players." + o.getUniqueId().toString()
											+ ".kills");
							double deaths = plugin.getConfig().getInt(
									"players." + o.getUniqueId().toString()
											+ ".deaths");
							if (deaths == 0.0D) {
								deaths = 1.0D;
							}
							String KD = String.format(
									"%.2f",
									new Object[] { Double.valueOf(kills
											/ deaths) });

							Score kd = obj
									.getScore("§8» §aK/D§7: §7"
											+ Arrays.asList(new String[] { "§7"
													+ KD }));
							kd.setScore(10);

							Score curks = obj
									.getScore("§8» §aKill Streak§8: §7"
											+ plugin.getConfig().getInt(
													"players."
															+ o.getUniqueId()
																	.toString()
															+ ".curkillstreak"));
							curks.setScore(9);
							Score bl2 = obj.getScore(" ");
							bl2.setScore(8);

							Score level = obj.getScore("§8» §bLevel§7: §7"
									+ plugin.getConfig().getInt(
											"players."
													+ o.getUniqueId()
															.toString()
													+ ".level"));
							level.setScore(7);
							Score killsneeded = obj
									.getScore("§8» §bKills Needed§7: 5");
							killsneeded.setScore(6);
							Score score4 = obj.getScore("§8» §3Coins§7: "
									+ plugin.getConfig().getInt(
											"players."
													+ o.getUniqueId()
															.toString()
													+ ".coins"));
							score4.setScore(5);

							Score bl3 = obj.getScore("");
							bl3.setScore(4);

							Score s5 = obj.getScore("§8» §a1v1 Wins§7: "
									+ plugin.getConfig().getInt(
											"players."
													+ o.getUniqueId()
															.toString()
													+ ".1v1wins"));
							s5.setScore(3);

							Score s6 = obj.getScore("§8» §a1v1 Loses§7: "
									+ plugin.getConfig().getInt(
											"players."
													+ o.getUniqueId()
															.toString()
													+ ".1v1loses"));
							s6.setScore(2);

							if (HillMoveEvent.win.get(o.getName()) != null) {
								Integer pt = HillMoveEvent.win.get(o.getName());
								Score hilly = obj
										.getScore("§8» §aHill Points§7: " + pt);
								hilly.setScore(1);
							} else {
								Score hilly = obj
										.getScore("§8» §aHill Points§7: 0");
								hilly.setScore(1);
							}

							o.setScoreboard(board);
						}
					} else {
						p.sendMessage(KitPvp.noPerm);
						p.sendMessage(ChatColor.RED + "You need "
								+ ChatColor.YELLOW + "5000 " + ChatColor.RED
								+ "coins for this kit!");
						e.setCancelled(true);
					}
				}
			} else if (e.getCurrentItem().getItemMeta().getDisplayName()
					.equalsIgnoreCase(ChatColor.GREEN + "Warrior")) {
				if (playerKits.contains("Warrior")) {
					Bukkit.dispatchCommand(p, "warrior");
					e.setCancelled(true);
					p.closeInventory();
				} else {
					p.sendMessage(KitPvp.noPerm);
					e.setCancelled(true);
				}
			} else {
				e.setCancelled(true);
			}
		} else {
			return;
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void OnPlayerSoup(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		Damageable dplayer = player;
		double health = dplayer.getHealth();
		if (health != dplayer.getMaxHealth()) {
			Double soup = Double.valueOf(7.0D);
			if (((event.getAction() == Action.RIGHT_CLICK_AIR) || (event
					.getAction() == Action.RIGHT_CLICK_BLOCK))
					&& (player.getItemInHand().getType() == Material.MUSHROOM_SOUP)) {
				player.setHealth(dplayer.getHealth() + soup.doubleValue() > dplayer
						.getMaxHealth() ? dplayer.getMaxHealth() : dplayer
						.getHealth() + soup.doubleValue());
				event.getPlayer().getItemInHand().setType(Material.BOWL);
				event.getPlayer().updateInventory();
			} else {
				return;
			}
		} else {
			return;
		}
	}
}
