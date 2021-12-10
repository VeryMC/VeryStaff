package fr.verymc.Listener;

import fr.verymc.Commands.CommandCps;
import fr.verymc.Commands.CommandMod;
import fr.verymc.manager.InventoryManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Random;

public class ListenerEvent implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void InteractAtEntity(PlayerInteractAtEntityEvent e){
        if(!e.getPlayer().hasPermission("mod.use")){
            return;
        }
        if(!(e.getRightClicked() instanceof Player)){
            return;
        }
        Player player = e.getPlayer();

        if(player.getItemInHand().getType() != Material.WATCH && player.getItemInHand().getType() != Material.STICK
        && player.getItemInHand().getType() != Material.BLAZE_ROD){
            return;
        }

        Player p = (Player) e.getRightClicked();

        if(player.getItemInHand().getType() == Material.WATCH) {
            player.chat("/cps " + p.getName());
        }
        if(player.getItemInHand().getType() == Material.STICK){
            e.setCancelled(false);
        }
        if(player.getItemInHand().getType() == Material.BLAZE_ROD){
            player.getWorld().createExplosion(player.getLocation().add(0,-0.5,0), 4,false);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onInteractEvent(PlayerInteractEvent e){
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
        if(e.getMaterial() == Material.INK_SACK){
            if(!CommandMod.Vanish.contains(e.getPlayer().getName())){
                CommandMod.setVanish(player, true);
                player.sendMessage("§6§lModération §8» §fVanish §aactivé §f!");
                ItemStack item = new ItemStack(Material.INK_SACK, 1, (short) 10);
                ItemMeta a = item.getItemMeta();
                a.setDisplayName("§aVanish actif");
                item.setItemMeta(a);
                player.getInventory().setItemInHand(item);
                return;
            } else {
                CommandMod.setVanish(player, false);
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
            player.chat("/mod");
            int id = 0;
            for(ItemStack i : InventoryManager.getInvManager().getModInventory(e.getPlayer())) {
                e.getPlayer().getInventory().setItem(id,i );
                id++;
            }
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
            if(joueurs.size() == 0){
                player.sendMessage("§6§lModération §8» §fIl n'y a personne sur qui se téléporter !");
                return;
            }
            Random number = new Random();
            int a = number.nextInt(joueurs.size()-1);
            if(joueurs.get(a) != null){
                player.teleport(Bukkit.getPlayer((String) joueurs.get(a)).getLocation());
            } else if(joueurs.get(0) != null){
                player.teleport(Bukkit.getPlayer((String) joueurs.get(0)).getLocation());
            }
        }
        if(e.getAction() == Action.RIGHT_CLICK_AIR && e.getMaterial() == Material.NAME_TAG){
            Inventory inv = Bukkit.createInventory(null, 54, "Sanctions.");
            e.getPlayer().openInventory(inv);
            ItemStack paper = new ItemStack(Material.PAPER);
            ItemMeta paperm = paper.getItemMeta();
            paperm.setDisplayName("&6Message");
            paper.setItemMeta(paperm);
            inv.setItem(1, paper);
            ItemStack ironsword = new ItemStack(Material.IRON_SWORD);
            ItemMeta ironswordm = ironsword.getItemMeta();
            ironswordm.setDisplayName("&6Gameplay");
            ironsword.setItemMeta(ironswordm);
            inv.setItem(2, ironsword);
            ItemStack gapple = new ItemStack(Material.GOLDEN_APPLE);
            ItemMeta gapplem = gapple.getItemMeta();
            gapplem.setDisplayName("&6Triche");
            gapple.setItemMeta(gapplem);
            inv.setItem(3, gapple);
            ItemStack lavab = new ItemStack(Material.LAVA_BUCKET);
            ItemMeta lavabm = lavab.getItemMeta();
            lavabm.setDisplayName("&6Abus");
            lavab.setItemMeta(lavabm);
            inv.setItem(4, lavab);
        }
    }
    @EventHandler
    public void onJoinEvent(PlayerJoinEvent e){
        for (String p : CommandMod.Vanish) e.getPlayer().hidePlayer(Bukkit.getPlayer(p));
    }
    @EventHandler
    public void onLeaveEvent(PlayerQuitEvent e){
        for (String p : CommandMod.Vanish) e.getPlayer().showPlayer(Bukkit.getPlayer(p));
    }
}
