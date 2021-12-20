package fr.verymc.Commands;

import fr.verymc.Listener.ListenerEvent;
import fr.verymc.manager.SanctionGuiCreator;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandFreeze implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)){
            return true;
        }
        Player player = (Player) sender;
        if(args.length == 0 || args.length > 1){
            player.sendMessage("§6§lModération §8» §cErreur, utilisation /freeze <Joueur>");
            return true;
        }
        if(args[0]==null || args[0].length() < 4){
            player.sendMessage("§6§lModération §8» §cErreur, pseudo incorrect !");
            return true;
        }
        if(Bukkit.getPlayer(args[0])!= null) {
            if (Bukkit.getPlayer(args[0]).isOnline()) {
                if(ListenerEvent.Freezed.contains(args[0])){
                    ListenerEvent.Freezed.remove(args[0]);
                } else{
                    ListenerEvent.Freezed.add(args[0]);
                }
            }
        }


        return true;
    }
}
