package fr.verymc.Commands;

import fr.verymc.manager.InventoryManager;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class CommandMod implements CommandExecutor {
    public static ArrayList<String> IsinMod = new ArrayList<String>();
    public static ArrayList<String> Vanish = new ArrayList<String>();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        InventoryManager.getInvManager().addInventoryMod(player);
        if(!player.hasPermission("mod.use")){
            player.sendMessage("§4 Vous n'avez pas la permission d'éxécuter cette commande");
            return true;
        }
        if(IsinMod.contains(player.getName())){
            player.sendMessage("§6§lModération §8» §fVous §csortez§f du mode Modération !");
            player.getInventory().clear();
            IsinMod.remove(player.getName());
        } else {
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
            player.getInventory().setItem(3, packedice);

            ItemStack stick = new ItemStack(Material.STICK);
            ItemMeta stickm = stick.getItemMeta();
            stickm.addEnchant(Enchantment.KNOCKBACK, 5, false);
            stickm.addEnchant(Enchantment.DURABILITY, 10, false);
            stickm.setDisplayName("§aKnockback");
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

            ItemStack greendye = new ItemStack(Material.GREEN_RECORD);
            ItemMeta greendyem = greendye.getItemMeta();
            greendyem.setDisplayName("§4Vanish");
            greendye.setItemMeta(greendyem);
            player.getInventory().setItem(7, greendye);

            ItemStack redstone = new ItemStack(Material.REDSTONE);
            ItemMeta redstonem = redstone.getItemMeta();
            redstonem.setDisplayName("§cQuitter");
            redstone.setItemMeta(redstonem);
            player.getInventory().setItem(8, redstone);

            player.sendMessage("§6§lModération §8» §fVous §aentrez §fen mode Modération !");
            IsinMod.add(player.getName());
        }


        return false;
    }
}
