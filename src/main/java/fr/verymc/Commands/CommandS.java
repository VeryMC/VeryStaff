package fr.verymc.Commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;

public class CommandS implements CommandExecutor {

    public static HashMap<String, String> target = new HashMap < > ();

    public static void MakeMainSGUI(Player player){
        Inventory inv = Bukkit.createInventory(null, 27, "Sanctions pour "+target.get(player.getName()));

        ItemStack paper = new ItemStack(Material.PAPER);
        ItemMeta paperm = paper.getItemMeta();
        paperm.setDisplayName("§6Message");
        paper.setItemMeta(paperm);
        inv.setItem(10, paper);

        ItemStack ironsword = new ItemStack(Material.IRON_SWORD);
        ItemMeta ironswordm = ironsword.getItemMeta();
        ironswordm.setDisplayName("§6Gameplay");
        ironsword.setItemMeta(ironswordm);
        inv.setItem(12, ironsword);

        ItemStack gapple = new ItemStack(Material.GOLDEN_APPLE);
        ItemMeta gapplem = gapple.getItemMeta();
        gapplem.setDisplayName("§6Triche");
        gapple.setItemMeta(gapplem);
        inv.setItem(14, gapple);

        ItemStack lavab = new ItemStack(Material.LAVA_BUCKET);
        ItemMeta lavabm = lavab.getItemMeta();
        lavabm.setDisplayName("§6Abus");
        lavab.setItemMeta(lavabm);
        inv.setItem(16, lavab);

        ItemStack custom8 = new ItemStack(Material.STAINED_GLASS_PANE, 1);
        ItemMeta meta8 = custom8.getItemMeta();
        meta8.setDisplayName("§6");
        custom8.setItemMeta(meta8);
        for (int i = 0; i < inv.getSize(); i++) {
            if (inv.getItem(i) == null || inv.getItem(i).getType().equals(Material.AIR)) {
                inv.setItem(i, custom8);
            }}

        player.openInventory(inv);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)){
            return true;
        }
        Player player = (Player) sender;
        if(args.length == 0 || args.length > 1){
            player.sendMessage("§6§lModération §8» §cErreur, utilisation /s <Joueur>");
            return true;
        }
        if(!CommandMod.IsinMod.contains(player.getName())){
            return true;
        }
        if(args[0]==null || args[0].length() < 4){
            player.sendMessage("§6§lModération §8» §cErreur, pseudo incorrecte !");
        }
        if(target.containsKey(player.getName())){
            target.remove(player.getName());
        }
        target.put(player.getName(), args[0]);
        MakeMainSGUI(player);

        return true;
    }
}
