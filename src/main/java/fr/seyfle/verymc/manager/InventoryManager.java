package fr.seyfle.verymc.manager;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;

public class InventoryManager {
    private static InventoryManager InvManager;

    private HashMap<String, Inventory> inventoryHasmap = new HashMap<>();

    public InventoryManager(){InvManager = this;}

    public void addInventoryMod(Player p){inventoryHasmap.put(p.getName(), p.getInventory());}
    public static InventoryManager getInvManager(){return InvManager;}
    public void removeInvManager(Player player){inventoryHasmap.remove(player.getName());}
    public Inventory getModInventory(Player p){return inventoryHasmap.get(p.getName());}
}
