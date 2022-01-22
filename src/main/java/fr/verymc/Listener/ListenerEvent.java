package fr.verymc.Listener;

import fr.verymc.Commands.CommandCps;
import fr.verymc.Commands.CommandMod;
import fr.verymc.Main;
import fr.verymc.manager.InventoryManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.Random;

public class ListenerEvent implements Listener {

    public static ArrayList<String> Freezed = new ArrayList<String>();
    public static ArrayList<String> tmp = new ArrayList<String>();
    public static ArrayList<String> tmp1 = new ArrayList<String>();

    Jedis j = null;

    @EventHandler
    public void PlayerMove(PlayerMoveEvent e){
        if(CommandMod.IsinMod.contains(e.getPlayer().getName())){
            return;
        }
        if(Freezed.contains(e.getPlayer().getName())){
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void PreCommand(PlayerCommandPreprocessEvent e){
        if(CommandMod.IsinMod.contains(e.getPlayer().getName())){
            return;
        }
        if(Freezed.contains(e.getPlayer().getName())){
            if(!e.getMessage().contains("/freeze")) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void InteractAtEntity(PlayerInteractAtEntityEvent e){
        if(!CommandMod.IsinMod.contains(e.getPlayer().getName())) return;
        if(!e.getPlayer().hasPermission("mod.use")){
            return;
        }
        if(!(e.getRightClicked() instanceof Player)){
            return;
        }
        if(e.getRightClicked().hasMetadata("NPC")){
            e.setCancelled(true);
            return;
        }
        Player player = e.getPlayer();

        if(player.getItemInHand().getType() != Material.WATCH && player.getItemInHand().getType() != Material.STICK
                && player.getItemInHand().getType() != Material.CHEST
        && player.getItemInHand().getType() != Material.PACKED_ICE && player.getItemInHand().getType() != Material.NAME_TAG){
            return;
        }

        Player p = (Player) e.getRightClicked();

        if(player.getItemInHand().getType() == Material.WATCH) {
            if (tmp1.contains(player.getName())) {
                return;
            } else {
                tmp1.add(player.getName());
                player.chat("/cps " + p.getName());
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
                    public void run() {
                        tmp1.remove(player.getName());
                    }
                }, 1);
            }
        }
            if (player.getItemInHand().getType() == Material.STICK) {
                e.setCancelled(true);
            }
            if (player.getItemInHand().getType() == Material.CHEST) {
                Inventory inv = Bukkit.createInventory(null, 54, p.getName());
                inv.setContents(p.getInventory().getContents());
                player.openInventory(inv);
            }
            if (player.getItemInHand().getType() == Material.PACKED_ICE) {
                if (tmp.contains(player.getName())) {
                    return;
                } else {
                    tmp.add(player.getName());
                    if (!Freezed.contains(p.getName())) {
                        Freezed.add(p.getName());
                        p.sendMessage("§6§lModération §8» §fVous avez été §cfreeze §fpar un membre du staff !");
                        player.sendMessage("§6§lModération §8» §fLe joueur §6" + p.getName() + " §fa été §cfreeze §f!");
                    } else {
                        Freezed.remove(p.getName());
                        p.sendMessage("§6§lModération §8» §fVous avez été §adéfreeze §fpar un membre du staff !");
                        player.sendMessage("§6§lModération §8» §fLe joueur §6" + p.getName() + " §fa été §adéfreeze §f!");
                    }
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
                        public void run() {
                            tmp.remove(player.getName());
                        }
                    }, 1);
                }
            }
                if (player.getItemInHand().getType() == Material.NAME_TAG) {
                    player.chat("/s " + p.getName());
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onInteractEvent(PlayerInteractEvent e){
        if(Freezed.contains(e.getPlayer().getName())){
            e.setCancelled(true);
        }
        if(!e.getPlayer().hasPermission("mod.use")){
            return;
        }
        Player player = e.getPlayer();
        if (CommandCps.inTestright.containsKey(player.getName()) && CommandCps.inTestleft.containsKey(player.getName())) {
            if (e.getAction() == Action.LEFT_CLICK_BLOCK || e.getAction() == Action.LEFT_CLICK_AIR) {
                CommandCps.inTestright.put(player.getName(), CommandCps.inTestright.get(player.getName()) + 1);
                return;
            }
            if (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) {
                CommandCps.inTestleft.put(player.getName(), CommandCps.inTestleft.get(player.getName()) + 1);
                return;
            }
        }
        if(!CommandMod.IsinMod.contains(player.getName())){
            return;
        }
        if(e.getMaterial() == Material.INK_SACK){
            if(!CommandMod.Vanish.contains(e.getPlayer().getName())){
                CommandMod.instance.setVanish(player, true);
                player.sendMessage("§6§lModération §8» §fVanish §aactivé §f!");
                ItemStack item = new ItemStack(Material.INK_SACK, 1, (short) 10);
                ItemMeta a = item.getItemMeta();
                a.setDisplayName("§aVanish actif");
                item.setItemMeta(a);
                player.getInventory().setItemInHand(item);
                return;
            } else {
                CommandMod.instance.setVanish(player, false);
                player.sendMessage("§6§lModération §8» §fVanish §cdéactivé §f!");
                ItemStack item = new ItemStack(Material.INK_SACK, 1, (short) 8);
                ItemMeta a = item.getItemMeta();
                a.setDisplayName("§cVanish inactif");
                item.setItemMeta(a);
                player.getInventory().setItemInHand(item);
                return;
            }
        }
        if(e.getMaterial() == Material.REDSTONE){
            player.chat("/mod off");
        }
        if(e.getMaterial() == Material.ARROW){
            ArrayList joueurs = new ArrayList();
            for(Player p : Bukkit.getOnlinePlayers()){
                if(player == p){
                    continue;
                }
                if(CommandMod.IsinMod.contains(p)){
                    continue;
                }
                joueurs.add(p.getName());
            }
            if(joueurs.size() <= 0){
                player.sendMessage("§6§lModération §8» §fIl n'y a personne sur qui se téléporter !");
                return;
            }
            Random number = new Random();
            int a = number.nextInt(joueurs.size());
            if(joueurs.get(a) != null){
                player.teleport(Bukkit.getPlayer((String) joueurs.get(a)).getLocation());
                player.sendMessage("§6§lModération §8» §fTéléportation sur §6"+(String) joueurs.get(a)+"§f !");
            } else if(joueurs.get(0) != null){
                player.teleport(Bukkit.getPlayer((String) joueurs.get(0)).getLocation());
                player.sendMessage("§6§lModération §8» §fTéléportation sur §6"+(String) joueurs.get(0)+"§f !");
            }
        }
    }
    @EventHandler
    public void onJoinEvent(PlayerJoinEvent e){
        Player player = e.getPlayer();
        for (String p : CommandMod.Vanish) player.hidePlayer(Bukkit.getPlayer(p));
        try {
            j = Main.pool.getResource();
            // If you want to use a password, use
            j.auth(System.getenv("REDIS_PASSWORD"));
            String returned = j.get("Mod:"+player.getUniqueId());
            if(returned != null){
                CommandMod.instance.ToggleMod(player, true);
            }
            e.setJoinMessage(null);

        } finally {
            // Be sure to close it! It can and will cause memory leaks.
            j.close();
        }
    }
    @EventHandler
    public void onLeaveEvent(PlayerQuitEvent e){
        if(CommandMod.IsinMod.contains(e.getPlayer().getName())){
            InventoryManager.getInvManager().restoreInv(e.getPlayer());
            CommandMod.IsinMod.remove(e.getPlayer().getName());
            if(CommandMod.Vanish.contains(e.getPlayer().getName())) CommandMod.Vanish.remove(e.getPlayer().getName());
        }
    }
}
