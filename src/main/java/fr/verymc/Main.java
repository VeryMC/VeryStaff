package fr.verymc;

import fr.verymc.Commands.CommandCps;
import fr.verymc.Commands.CommandMod;
import fr.verymc.Commands.CommandS;
import fr.verymc.Listener.ListenerEvent;
import fr.verymc.manager.InventoryManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.TimeUnit;

public final class Main extends JavaPlugin {

    public static Main instance;

    @Override
    public void onEnable() {
        long time = System.currentTimeMillis();
        System.out.println("Démarrage du plugin...");
        instance = this;
        new InventoryManager();
        getServer().getPluginManager().registerEvents(new ListenerEvent(), this);
        getServer().getPluginManager().registerEvents(new InventoryManager(), this);
        getCommand("mod").setExecutor(new CommandMod());
        getCommand("cps").setExecutor(new CommandCps());
        getCommand("s").setExecutor(new CommandS());
        System.out.println("Démarrage terminé en " + TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()-time)+"."+
                TimeUnit.MILLISECONDS.toMillis(System.currentTimeMillis()-time)+" !");

    }

    @Override
    public void onDisable() {
        System.out.println("Plugin éteint !");
    }
}
