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
import org.bukkit.inventory.ItemStack;

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

    }
    public void onJoinEvent(PlayerJoinEvent e){
        for (String p : Vanish) e.getPlayer().hidePlayer(Bukkit.getPlayer(p));
    }
    public void onLeaveEvent(PlayerQuitEvent e){
        for (String p : Vanish) e.getPlayer().showPlayer(Bukkit.getPlayer(p));
    }
}
