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

public class ArcherClass extends ClassBase implements CommandExecutor {

    private Core plugin;

    public ArcherClass(Core pl) {
        this.plugin = pl;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        Player p = (Player) sender;
        if (commandLabel.equalsIgnoreCase("Archer")) {
            if (!KitPvp.activeKit.contains(p.getName())) {
                if (plugin.getConfig().getStringList("players." + p.getUniqueId().toString() + ".kits").contains("Archer")) {
                    KitPvp.activeKit.add(p.getName());
                    p.sendMessage(ChatColor.GREEN + "You have activated the Archer Kit!");

                    KitPvp.clearEffects(p);
                    p.getInventory().clear();

                    p.getInventory().setItem(0, getPrimaryWeapon());
                    p.getInventory().setItem(1, getBow());
                    p.getInventory().setItem(2, new ItemStack(Material.ARROW));
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
        sword.addEnchantment(Enchantment.DAMAGE_ALL, 2);
        sword.addUnsafeEnchantment(Enchantment.DURABILITY, 100);
        return sword;
    }

    public ItemStack getBow() {
        ItemStack bow = new ItemStack(Material.BOW);
        bow.addEnchantment(Enchantment.ARROW_DAMAGE, 2);
        bow.addEnchantment(Enchantment.ARROW_INFINITE, 1);
        return bow;
    }

    @Override
    public ItemStack getHelmet() {
        ItemStack helmet = new ItemStack(Material.CHAINMAIL_HELMET);
        helmet.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
        return helmet;
    }

    @Override
    public ItemStack getChestplate() {
        ItemStack chestplate = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
        chestplate.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
        return chestplate;
    }

    @Override
    public ItemStack getLeggings() {
        ItemStack leggings = new ItemStack(Material.CHAINMAIL_LEGGINGS);
        leggings.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
        return leggings;
    }

    @Override
    public ItemStack getBoots() {
        ItemStack boots = new ItemStack(Material.CHAINMAIL_BOOTS);
        boots.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
        return boots;
    }
}
