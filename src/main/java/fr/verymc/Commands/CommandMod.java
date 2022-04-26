package fr.verymc.Commands;


import fr.verymc.Main;
import fr.verymc.manager.InventoryManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;

public class CommandMod implements CommandExecutor {
    public static ArrayList<String> IsinMod = new ArrayList<String>();
    public static ArrayList<String> Vanish = new ArrayList<String>();

    public static CommandMod instance;

    public CommandMod(){
        instance=this;
    }

    Jedis j = null;

    public void setVanish(Player player, Boolean ison){
        if(ison==false){
            if(Vanish.contains(player.getName())) {
                CommandMod.Vanish.remove(player.getName());
            }
            for (Player p : Bukkit.getOnlinePlayers()){
                p.showPlayer(player);
            }
            player.removeMetadata("vanished", Main.instance);
            try {
                j = Main.pool.getResource();
                // If you want to use a password, use

                j.set("Mod:"+player.getUniqueId(), "false");
            } finally {
                // Be sure to close it! It can and will cause memory leaks.
                j.close();
            }
            player.sendMessage("§6§lModération §8» §fVanish §cdésactivé §f!");

        } else{
            if(!Vanish.contains(player.getName())) {
                CommandMod.Vanish.add(player.getName());
            }
            for (Player p : Bukkit.getOnlinePlayers()){
                p.hidePlayer(player);
            }
            player.setMetadata("vanished", new FixedMetadataValue(Main.instance,"true"));
            try {
                j = Main.pool.getResource();
                // If you want to use a password, use
                j.auth(System.getenv("REDIS_PASSWORD"));
                j.set("Mod:"+player.getUniqueId(), "true");
            } finally {
                // Be sure to close it! It can and will cause memory leaks.
                j.close();
            }
            player.sendMessage("§6§lModération §8» §fVanish §aactivé §f!");
        }
    }

    public void ToggleMod(Player player, boolean type){
        if(type==true){
            InventoryManager.getInvManager().saveInv(player);

            player.setAllowFlight(true);
            player.setFlying(true);

            player.setNoDamageTicks(999999999);
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("VeryStaff"), new Runnable() {
                public void run() {
                    player.getInventory().clear();

                    ItemStack watch = new ItemStack(Material.WATCH);
                    ItemMeta watchm = watch.getItemMeta();
                    watchm.setDisplayName("§aCPS Test");
                    watch.setItemMeta(watchm);
                    player.getInventory().setItem(0, watch);

                    ItemStack chest = new ItemStack(Material.CHEST);
                    ItemMeta chestm = chest.getItemMeta();
                    chestm.setDisplayName("§aInvsee");
                    chest.setItemMeta(chestm);
                    player.getInventory().setItem(1, chest);

                    ItemStack packedice = new ItemStack(Material.PACKED_ICE);
                    ItemMeta packedicem = packedice.getItemMeta();
                    packedicem.setDisplayName("§bGel");
                    packedice.setItemMeta(packedicem);
                    player.getInventory().setItem(2, packedice);

                    ItemStack stick = new ItemStack(Material.STICK);
                    ItemMeta stickm = stick.getItemMeta();
                    stickm.addEnchant(Enchantment.KNOCKBACK, 2, true);
                    stickm.addEnchant(Enchantment.DURABILITY, 3, true);
                    stickm.setDisplayName("§aKnockback 2");
                    stick.setItemMeta(stickm);
                    player.getInventory().setItem(4, stick);

                    ItemStack arrow = new ItemStack(Material.ARROW);
                    ItemMeta arrowM = arrow.getItemMeta();
                    arrowM.setDisplayName("§aRandom TP");
                    arrow.setItemMeta(arrowM);
                    player.getInventory().setItem(5, arrow);

                    ItemStack nametag = new ItemStack(Material.NAME_TAG);
                    ItemMeta nametagm = nametag.getItemMeta();
                    nametagm.setDisplayName("§4Sanctions");
                    nametag.setItemMeta(nametagm);
                    player.getInventory().setItem(6, nametag);

                    ItemStack greendye = new ItemStack(Material.INK_SACK, 1, (short) 10);
                    ItemMeta greendyem = greendye.getItemMeta();
                    greendyem.setDisplayName("§aVanish actif");
                    greendye.setItemMeta(greendyem);
                    player.getInventory().setItem(7, greendye);

                    ItemStack redstone = new ItemStack(Material.REDSTONE);
                    ItemMeta redstonem = redstone.getItemMeta();
                    redstonem.setDisplayName("§cQuitter");
                    redstone.setItemMeta(redstonem);
                    player.getInventory().setItem(8, redstone);
                }
            }, 2);

            player.sendMessage("§6§lModération §8» §fVous §aentrez §fen mode Modération !");
            setVanish(player, true);
            IsinMod.add(player.getName());

            player.setMetadata("mod", new FixedMetadataValue(Main.instance,"true"));

            try {
                j = Main.pool.getResource();
                // If you want to use a password, use
                j.auth(System.getenv("REDIS_PASSWORD"));
                j.set("Mod:"+player.getUniqueId(), "true");

            } finally {
                // Be sure to close it! It can and will cause memory leaks.
                j.close();
            }
        } else{
            player.sendMessage("§6§lModération §8» §fVous §csortez§f du mode Modération !");
            player.removeMetadata("mod", Main.instance);
            player.getInventory().clear();
            player.setAllowFlight(false);
            player.setFlying(false);
            setVanish(player, false);
            player.setNoDamageTicks(1);
            IsinMod.remove(player.getName());
            InventoryManager.getInvManager().restoreInv(player);

            try {
                j = Main.pool.getResource();
                // If you want to use a password, use
                j.auth(System.getenv("REDIS_PASSWORD"));
                j.del("Mod:"+player.getUniqueId());

            } finally {
                // Be sure to close it! It can and will cause memory leaks.
                j.close();
            }
        }

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if(!player.hasPermission("mod.use")){
            player.sendMessage("§4 Vous n'avez pas la permission d'éxécuter cette commande");
            return true;
        }
        if(args.length == 0){
            player.sendMessage("§4 Veuillez indiquer si vous souhaitez enter ou sortir du /mod. Ex : /mod <on/off>");
            return true;
        }
        if(args[0].equalsIgnoreCase("on") && IsinMod.contains(player.getName())){
            player.sendMessage(ChatColor.RED+"Vous êtes déja en mode modération.");
            return true;
        }
        if(args[0].equalsIgnoreCase("off") && !IsinMod.contains(player.getName())){
            player.sendMessage(ChatColor.RED+"Vous n'êtes pas en mode modération.");
            return true;
        }
        if(args[0].equalsIgnoreCase("on") && !IsinMod.contains(player.getName())){
            ToggleMod(player, true);
        }

        if(args[0].equalsIgnoreCase("off") && IsinMod.contains(player.getName())){
            ToggleMod(player, false);
        }



        return false;
    }
}
