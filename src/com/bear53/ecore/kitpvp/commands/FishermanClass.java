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

public class FishermanClass extends ClassBase implements CommandExecutor {

	private Core plugin;

	public FishermanClass(Core pl) {
		this.plugin = pl;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player p = (Player) sender;
		if (commandLabel.equalsIgnoreCase("fisherman")) {
			if (!KitPvp.activeKit.contains(p.getName())) {
				if (plugin.getConfig().getInt("players." + p.getUniqueId().toString() + ".1v1wins") >= 10) {
					KitPvp.activeKit.add(p.getName());
					p.sendMessage(ChatColor.GREEN + "You have activated the Fisherman Kit!");

                    KitPvp.clearEffects(p);
					p.getInventory().clear();

					p.getInventory().setItem(0, getPrimaryWeapon());
					p.getInventory().setItem(1, getFishingHook());
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
        return new ItemStack(Material.IRON_SWORD);
    }

    public ItemStack getFishingHook() {
        ItemStack hook = new ItemStack(Material.FISHING_ROD);
        hook.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
        return hook;
    }

    @Override
    public ItemStack getHelmet() {
        ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
        helmet.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
        return helmet;
    }

    @Override
    public ItemStack getChestplate() {
        ItemStack chestplate = new ItemStack(Material.IRON_HELMET);
        chestplate.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
        return chestplate;
    }

    @Override
    public ItemStack getLeggings() {
        ItemStack leggings = new ItemStack(Material.GOLD_LEGGINGS);
        leggings.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
        return leggings;
    }

    @Override
    public ItemStack getBoots() {
        ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);
        boots.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
        return boots;
    }
}
