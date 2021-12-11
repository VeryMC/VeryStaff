package fr.verymc.manager;

import fr.verymc.Commands.CommandS;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;

public class SanctionGuiCreator {

    public static void MakeMainSGUI(Player player){
        String cible = CommandS.target.get(player.getName());
        Inventory inv = Bukkit.createInventory(null, 27, "§8Sanctions pour "+ CommandS.target.get(player.getName()));

        ItemStack stack = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
        ItemMeta meta = stack.getItemMeta();
        ((SkullMeta) meta).setOwner(cible);
        meta.setDisplayName("§6" + cible);
        stack.setItemMeta(meta);
        inv.setItem(13, stack);

        ItemStack paper = new ItemStack(Material.PAPER);
        ItemMeta paperm = paper.getItemMeta();
        paperm.setDisplayName("§6Mutes");
        paper.setItemMeta(paperm);
        inv.setItem(10, paper);

        ItemStack ironsword = new ItemStack(Material.ARROW);
        ItemMeta ironswordm = ironsword.getItemMeta();
        ironswordm.setDisplayName("§6Bans");
        ironsword.setItemMeta(ironswordm);
        inv.setItem(11, ironsword);

        ItemStack gapple = new ItemStack(Material.BOW);
        ItemMeta gapplem = gapple.getItemMeta();
        gapplem.setDisplayName("§6Bans-ip");
        gapple.setItemMeta(gapplem);
        inv.setItem(15, gapple);

        ItemStack lavab = new ItemStack(Material.BEACON);
        ItemMeta lavabm = lavab.getItemMeta();
        lavabm.setDisplayName("§6Blanchissement");
        lavab.setItemMeta(lavabm);
        inv.setItem(16, lavab);

        player.openInventory(inv);
    }

    public static void MakeMuteGUI(Player player){
        String cible = CommandS.target.get(player.getName());
        Inventory inv = Bukkit.createInventory(null, 27, "§8Mutes pour "+cible);

        ItemStack stack = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
        ItemMeta meta = stack.getItemMeta();
        ((SkullMeta) meta).setOwner(cible);
        meta.setDisplayName("§6" + cible);
        stack.setItemMeta(meta);
        inv.setItem(13, stack);

        ItemStack custom1 = new ItemStack(Material.BOW, 1);
        ItemMeta customS = custom1.getItemMeta();
        customS.setDisplayName("§6Spam (20 minutes)");
        custom1.setItemMeta(customS);
        inv.setItem(10, custom1);

        ItemStack custom2 = new ItemStack(Material.DIAMOND_SWORD, 1);
        ItemMeta customa = custom2.getItemMeta();
        customa.setDisplayName("§6Flood (20 minutes)");
        custom2.setItemMeta(customa);
        inv.setItem(11, custom2);

        ItemStack custom3 = new ItemStack(Material.ANVIL, 1);
        ItemMeta customb = custom3.getItemMeta();
        customb.setDisplayName("§6Insultes (30 minutes)");
        custom3.setItemMeta(customb);
        inv.setItem(15, custom3);

        ItemStack custom4 = new ItemStack(Material.REDSTONE_BLOCK, 1);
        ItemMeta customc = custom4.getItemMeta();
        customc.setDisplayName("§6Provocation (30 minutes)");
        custom4.setItemMeta(customc);
        inv.setItem(16, custom4);

        ItemStack custom7 = new ItemStack(Material.ACTIVATOR_RAIL, 1);
        ItemMeta customf = custom7.getItemMeta();
        customf.setDisplayName("§6Incitation à la saction (1 heure)");
        custom7.setItemMeta(customf);
        inv.setItem(4, custom7);

        ItemStack custom8 = new ItemStack(Material.ARMOR_STAND, 1);
        ItemMeta customg = custom8.getItemMeta();
        customg.setDisplayName("§6Contournement (1 heure)");
        custom8.setItemMeta(customg);
        inv.setItem(22, custom8);

        ItemStack custom9 = new ItemStack(Material.COMPASS, 1);
        ItemMeta customh = custom9.getItemMeta();
        customh.setDisplayName("§6Publicité (1 jour)");
        custom9.setItemMeta(customh);
        inv.setItem(12, custom9);

        ItemStack custom10 = new ItemStack(Material.FLINT_AND_STEEL, 1);
        ItemMeta customi = custom10.getItemMeta();
        customi.setDisplayName("§6Menaces (1 jour)");
        custom10.setItemMeta(customi);
        inv.setItem(14, custom10);

        ItemStack custom6 = new ItemStack(Material.IRON_DOOR, 1);
        ItemMeta custome = custom6.getItemMeta();
        custome.setDisplayName("§6Retour en arrière");
        custom6.setItemMeta(custome);
        inv.setItem(26, custom6);
        inv.setItem(18, custom6);

        player.openInventory(inv);
    }
    public static void makeBanGUI(Player player){
        String cible = CommandS.target.get(player.getName());
        Inventory inv = Bukkit.createInventory(null, 27, "§8Bans pour "+cible);

        ItemStack stack = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
        ItemMeta meta = stack.getItemMeta();
        ((SkullMeta) meta).setOwner(cible);
        meta.setDisplayName("§6" + cible);
        stack.setItemMeta(meta);
        inv.setItem(13, stack);

        ItemStack custom1 = new ItemStack(Material.DIAMOND_SWORD, 1);
        ItemMeta customS = custom1.getItemMeta();
        customS.setDisplayName("§6Kill aura (7 jours)");
        custom1.setItemMeta(customS);
        inv.setItem(10, custom1);

        ItemStack custom2 = new ItemStack(Material.WOOD_SWORD, 1);
        ItemMeta customa = custom2.getItemMeta();
        customa.setDisplayName("§6CPS (7 jours)");
        custom2.setItemMeta(customa);
        inv.setItem(11, custom2);

        ItemStack custom3 = new ItemStack(Material.FEATHER, 1);
        ItemMeta customb = custom3.getItemMeta();
        customb.setDisplayName("§6Fly (7 jours)");
        custom3.setItemMeta(customb);
        inv.setItem(15, custom3);

        ItemStack custom4 = new ItemStack(Material.IRON_BOOTS, 1);
        ItemMeta customc = custom4.getItemMeta();
        customc.setDisplayName("§6Anti Knockback (7 jours)");
        custom4.setItemMeta(customc);
        inv.setItem(16, custom4);

        ItemStack custom7 = new ItemStack(Material.GOLD_AXE, 1);
        ItemMeta customf = custom7.getItemMeta();
        customf.setDisplayName("§6Reach (7 jours)");
        custom7.setItemMeta(customf);
        inv.setItem(4, custom7);

        ItemStack custom8 = new ItemStack(Material.ARMOR_STAND, 1);
        ItemMeta customg = custom8.getItemMeta();
        customg.setDisplayName("§6Autre hack (7 jours)");
        custom8.setItemMeta(customg);
        inv.setItem(22, custom8);

        ItemStack custom9 = new ItemStack(Material.TNT, 1);
        ItemMeta customh = custom9.getItemMeta();
        customh.setDisplayName("§6Pseudo incorrect (999 ans)");
        custom9.setItemMeta(customh);
        inv.setItem(12, custom9);

        ItemStack custom10 = new ItemStack(Material.LEATHER, 1);
        ItemMeta customi = custom10.getItemMeta();
        customi.setDisplayName("§6Skin incorrect (1 jour)");
        custom10.setItemMeta(customi);
        inv.setItem(14, custom10);

        ItemStack custom11 = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
        ItemMeta customj = custom11.getItemMeta();
        customj.setDisplayName("§6Autre (7 jours)");
        custom11.setItemMeta(customj);
        inv.setItem(9, custom11);

        ItemStack custom12 = new ItemStack(Material.DIRT, 1);
        ItemMeta customk = custom12.getItemMeta();
        customk.setDisplayName("§6Build incorrect (7 jours)");
        custom12.setItemMeta(customk);
        inv.setItem(17, custom12);

        ItemStack custom6 = new ItemStack(Material.IRON_DOOR, 1);
        ItemMeta custome = custom6.getItemMeta();
        custome.setDisplayName("§6Retour en arrière");
        custom6.setItemMeta(custome);
        inv.setItem(26, custom6);
        inv.setItem(18, custom6);

        player.openInventory(inv);
    }
    public static void MakeBanIpGui(Player player){
        String cible = CommandS.target.get(player.getName());
        Inventory inv = Bukkit.createInventory(null, 27, "§8Bans-ip pour "+cible);

        ItemStack stack = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
        ItemMeta meta = stack.getItemMeta();
        ((SkullMeta) meta).setOwner(cible);
        meta.setDisplayName("§6" + cible);
        stack.setItemMeta(meta);
        inv.setItem(13, stack);

        ItemStack custom1 = new ItemStack(Material.NAME_TAG, 1);
        ItemMeta customS = custom1.getItemMeta();
        customS.setDisplayName("§6Usurpation d'identité");
        custom1.setItemMeta(customS);
        inv.setItem(12, custom1);

        ItemStack custom2 = new ItemStack(Material.CLAY_BALL, 2);
        ItemMeta customa = custom2.getItemMeta();
        customa.setDisplayName("§6Double compte");
        custom2.setItemMeta(customa);
        inv.setItem(14, custom2);

        ItemStack custom6 = new ItemStack(Material.IRON_DOOR, 1);
        ItemMeta custome = custom6.getItemMeta();
        custome.setDisplayName("§6Retour en arrière");
        custom6.setItemMeta(custome);
        inv.setItem(26, custom6);
        inv.setItem(18, custom6);

        player.openInventory(inv);
    }
    public static void MakeBlanchissementGUI(Player player){
        String cible = CommandS.target.get(player.getName());
        Inventory inv = Bukkit.createInventory(null, 27, "§8Blanchissements pour "+cible);

        ItemStack stack = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
        ItemMeta meta = stack.getItemMeta();
        ((SkullMeta) meta).setOwner(cible);
        meta.setDisplayName("§6" + cible);
        stack.setItemMeta(meta);
        inv.setItem(13, stack);

        ItemStack custom1 = new ItemStack(Material.PAPER, 1);
        ItemMeta customS = custom1.getItemMeta();
        customS.setDisplayName("§6Démute");
        custom1.setItemMeta(customS);
        inv.setItem(11, custom1);

        ItemStack custom2 = new ItemStack(Material.ARROW, 1);
        ItemMeta customa = custom2.getItemMeta();
        customa.setDisplayName("§6Déban");
        custom2.setItemMeta(customa);
        inv.setItem(12, custom2);

        ItemStack custom3 = new ItemStack(Material.BOW, 1);
        ItemMeta customb = custom3.getItemMeta();
        customb.setDisplayName("§6Déban-ip");
        custom3.setItemMeta(customb);
        inv.setItem(14, custom3);

        ItemStack custom4 = new ItemStack(Material.BEACON, 1);
        ItemMeta customc = custom4.getItemMeta();
        customc.setDisplayName("§6La totale :D");
        custom4.setItemMeta(customc);
        inv.setItem(15, custom4);


        ItemStack custom6 = new ItemStack(Material.IRON_DOOR, 1);
        ItemMeta custome = custom6.getItemMeta();
        custome.setDisplayName("§6Retour en arrière");
        custom6.setItemMeta(custome);
        inv.setItem(26, custom6);
        inv.setItem(18, custom6);

        player.openInventory(inv);
    }
}
