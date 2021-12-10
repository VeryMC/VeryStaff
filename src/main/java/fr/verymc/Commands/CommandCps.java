package fr.verymc.Commands;

import fr.verymc.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class CommandCps implements CommandExecutor {

    public static HashMap<String, Long> inTestright = new HashMap < > ();
    public static HashMap<String, Long> inTestleft = new HashMap < > ();

    public void RemoveFrom(String player, Player requester){
        requester.sendMessage("§6§lModération §8» §aTest CPS lancé sur §6"+player);
        Bukkit.getServer().getScheduler().scheduleAsyncDelayedTask(Main.instance, new Runnable() {
            public void run() {
                if(inTestright.containsKey(player) && inTestleft.containsKey(player)){
                    requester.sendMessage("§6§lModération §8» §6Résultats CPS de "+player+": "+
                            "\n§6Click droit: §b" + inTestright.get(player) + "\n§6Click gauche: §b" + inTestleft.get(player));
                    inTestright.remove(player);
                    inTestleft.remove(player);
                }
            }
        }, 20);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)){
            sender.sendMessage("§6§lModération §8» §cVous devez être un joueur !");
            return true;
        }
        if(args.length == 0 || args.length >= 2){
            sender.sendMessage("§6§lModération §8» §cErreur, utilisation /cps <Joueur>");
            return true;
        }
        if(Bukkit.getPlayer(args[0]) != null){
            if(Bukkit.getPlayer(args[0]).isOnline()){
                Player player = Bukkit.getPlayer(args[0]);
                if(!inTestright.containsKey(player.getName()) && !inTestleft.containsKey(player.getName())){
                    inTestright.put(player.getName(), 0L);
                    inTestleft.put(player.getName(), 0L);
                    RemoveFrom(player.getName(), (Player) sender);
                } else {
                    sender.sendMessage("§6§lModération §8» §cErreur, ce joueur est déjà en test !");
                }
            } else{
                sender.sendMessage("§6§lModération §8» §cErreur, ce joueur n'est pas en ligne !");
                return true;
            }
        } else{
            sender.sendMessage("§6§lModération §8» §cErreur, ce Joueur n'existe pas !");
            return true;
        }

        return true;
    }
}
