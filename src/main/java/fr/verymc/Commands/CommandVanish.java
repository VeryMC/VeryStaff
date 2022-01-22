package fr.verymc.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandVanish implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)){
            return true;
        }
        Player player = (Player) sender;
        if(args.length == 0 || args.length > 1){
            player.sendMessage("§6§lModération §8» §cErreur, utilisation /vanish <on/off>");
            return true;
        }
        if(args[0]!="on" && args[0]!="off"){
            return true;
        }
        if(args[0]=="on"){
            CommandMod.instance.setVanish(player, true);
        }
        if(args[0]=="off"){
            CommandMod.instance.setVanish(player, false);
        }


        return true;
    }
}
