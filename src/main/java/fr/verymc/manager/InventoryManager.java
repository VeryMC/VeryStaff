package fr.verymc.manager;

import fr.verymc.Commands.CommandMod;
import fr.verymc.Commands.CommandS;
import fr.verymc.Listener.ListenerEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class InventoryManager implements Listener {
    private static InventoryManager InvManager;

    private HashMap<String, ItemStack[] []> inventoryHasmap = new HashMap<>();

    public InventoryManager(){InvManager = this;}

    public static InventoryManager getInvManager(){return InvManager;}

    public void removeInvManager(Player player){inventoryHasmap.remove(player.getName());}

    public void saveInv(Player p){
        ItemStack[] [] store = new ItemStack[2][1];
        store[0] = p.getInventory().getContents();
        store[1] = p.getInventory().getArmorContents();
        inventoryHasmap.put(p.getName(), store);
    }
    @SuppressWarnings("deprecation")
    public void restoreInv(Player p){
        p.getInventory().clear();
        p.getInventory().setContents(inventoryHasmap.get(p.getName())[0]);
        p.getInventory().setArmorContents(inventoryHasmap.get(p.getName())[1]);
        inventoryHasmap.remove(p.getName());
        p.updateInventory();
        removeInvManager(p);
    }
    @EventHandler
    public void InventoryClickEvent(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();
        if(!CommandMod.IsinMod.contains(player.getName())){
            return;
        }
        if(!CommandS.target.containsKey(player.getName())){
            return;
        }
        e.setCancelled(true);

        ItemStack current = e.getCurrentItem();

        if(current == null){
            return;
        }
        if(current.getItemMeta() == null){
            return;
        }
    }
}
