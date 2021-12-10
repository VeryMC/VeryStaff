package fr.verymc.Commands;

import fr.verymc.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class CommandCps implements CommandExecutor {

    public static HashMap<String, Integer> inTest = new HashMap < > ();

    public void RemoveFrom(String player, Player requester){
        requester.sendMessage("§6Le test a été lancé avec succès, les résultats seront disponnibles dans environ 2 secondes.");
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
            public void run() {
                if(inTest.containsKey(player)){
                    requester.sendMessage("§6Résultats du test cps sur "+player+", moyenne sur 2 secondes: "+
                            inTest.get(player)/2);
                    inTest.remove(player);
                }
            }
        }, 40);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)){
            sender.sendMessage("§cVous devez être un joueur !");
            return true;
        }
        if(args.length == 0 || args.length >= 2){
            sender.sendMessage("§cErreur, utilisation /cps <Joueur>");
            return true;
        }
        if(Bukkit.getPlayer(args[0]) != null){
            if(Bukkit.getPlayer(args[0]).isOnline()){
                Player player = Bukkit.getPlayer(args[0]);
                if(!inTest.containsKey(player.getName())){
                    inTest.put(player.getName(), 0);
                    RemoveFrom(player.getName(), (Player) sender);
                } else {
                    sender.sendMessage("§cErreur, ce joueur est déjà en test !");
                }
            } else{
                sender.sendMessage("§cErreur, ce joueur n'est pas en ligne !");
                return true;
            }
        } else{
            sender.sendMessage("§cErreur, ce Joueur n'existe pas !");
            return true;
        }

        return true;
    }
}
