package fr.verymc.manager;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class InventoryManager {
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
    }
}
