package fr.verymc.Commands;

import fr.verymc.manager.SanctionGuiCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class CommandS implements CommandExecutor {

    public static HashMap<String, String> target = new HashMap < > ();

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
        if(args[0]==null || args[0].length() < 4){
            player.sendMessage("§6§lModération §8» §cErreur, pseudo incorrecte !");
        }
        if(target.containsKey(player.getName())){
            target.remove(player.getName());
        }
        target.put(player.getName(), args[0]);
        SanctionGuiCreator.MakeMainSGUI(player);

        return true;
    }
}
