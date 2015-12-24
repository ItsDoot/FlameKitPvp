package com.bear53.ecore.kitpvp.commands;

import com.bear53.ecore.Core;
import com.bear53.ecore.kitpvp.KitPvp;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class KangarooClass extends ClassBase implements CommandExecutor {

	private Core plugin;

	public KangarooClass(Core pl) {
		this.plugin = pl;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player p = (Player) sender;
		if (commandLabel.equalsIgnoreCase("Kangaroo")) {
			if (!KitPvp.activeKit.contains(p.getName())) {
				if (plugin.getConfig().getStringList("players." + p.getUniqueId().toString() + ".kits").contains("Kangaroo")) {
					KitPvp.activeKit.add(p.getName());
					p.sendMessage(ChatColor.GREEN + "You have activated the Kangaroo Kit!");

					KitPvp.clearEffects(p);
					p.getInventory().clear();

					p.getInventory().setItem(0, getPrimaryWeapon());
					p.getInventory().setItem(1, getFirework());
                    p.getInventory().setItem(8, getItemShop());

					p.getInventory().setHelmet(getHelmet());
					p.getInventory().setChestplate(getChestplate());
					p.getInventory().setLeggings(getLeggings());
					p.getInventory().setBoots(getBoots());

					p.playSound(p.getLocation(), Sound.LEVEL_UP, 3.0F, 2.0F);

					if (plugin.getConfig().getBoolean("potion-enabled")) {
						KitPvp.giveHeath(p);
					} else {
						KitPvp.giveSoup(p);
					}
				} else {
					p.sendMessage(KitPvp.noPerm);
				}
			} else {
				p.sendMessage(KitPvp.activekit);
			}
		}
		return true;
	}

    @Override
    public ItemStack getPrimaryWeapon() {
        ItemStack sword = new ItemStack(Material.STONE_SWORD);
        sword.addEnchantment(Enchantment.DAMAGE_ALL, 3);
        return sword;
    }

    public ItemStack getFirework() {
        return new ItemStack(Material.FIREWORK);
    }

    @Override
    public ItemStack getHelmet() {
        ItemStack helmet = new ItemStack(Material.IRON_HELMET);
        helmet.addEnchantment(Enchantment.THORNS, 1);
        return helmet;
    }

    @Override
    public ItemStack getChestplate() {
        ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
        chestplate.addEnchantment(Enchantment.THORNS, 1);
        return chestplate;
    }

    @Override
    public ItemStack getLeggings() {
        ItemStack leggings = new ItemStack(Material.IRON_LEGGINGS);
        leggings.addEnchantment(Enchantment.THORNS, 1);
        return leggings;
    }

    @Override
    public ItemStack getBoots() {
        ItemStack boots = new ItemStack(Material.IRON_BOOTS);
        boots.addEnchantment(Enchantment.THORNS, 1);
        return boots;
    }
}
