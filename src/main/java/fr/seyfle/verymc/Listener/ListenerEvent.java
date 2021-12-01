package fr.seyfle.verymc.Listener;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import fr.seyfle.verymc.manager.InventoryManager;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

import static fr.seyfle.verymc.Commands.CommandMod.Vanish;

public class ListenerEvent implements Listener {

    public void onInteractEvent(PlayerInteractEvent e){
        if(e.getAction() == Action.RIGHT_CLICK_AIR && e.getMaterial() == Material.GREEN_RECORD){
            if(!Vanish.contains(e.getPlayer().getName())){
                Vanish.add(e.getPlayer().getName());
                for (Player p : Bukkit.getOnlinePlayers()){
                    p.hidePlayer(e.getPlayer());
                }
                return;
            }else {
                Vanish.remove(e.getPlayer().getName());
                for (Player p : Bukkit.getOnlinePlayers()){
                    p.showPlayer(e.getPlayer());
                }
                return;
            }
        }
        if(e.getAction() == Action.RIGHT_CLICK_AIR && e.getMaterial() == Material.REDSTONE){
            int id = 0;
            for(ItemStack i : InventoryManager.getInvManager().getModInventory(e.getPlayer())) {
                e.getPlayer().getInventory().setItem(id,i );
                id++;
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
    public void onJoinEvent(PlayerJoinEvent e){
        for (String p : Vanish) e.getPlayer().hidePlayer(Bukkit.getPlayer(p));
    }
    public void onLeaveEvent(PlayerQuitEvent e){
        for (String p : Vanish) e.getPlayer().showPlayer(Bukkit.getPlayer(p));
    }
}
