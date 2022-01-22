package fr.verymc;

import fr.verymc.Commands.*;
import fr.verymc.Listener.ListenerEvent;
import fr.verymc.manager.InventoryManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.TimeUnit;

public final class Main extends JavaPlugin {

    public static Main instance;
    public static boolean isSkyblock = false;
    public static boolean hasCitizens = false;


    @Override
    public void onEnable() {
        long time = System.currentTimeMillis();
        System.out.println("Démarrage du plugin...");
        instance = this;
        if(Bukkit.getPluginManager().getPlugin("IridiumSkyblock")!= null) isSkyblock = true;
        if(Bukkit.getPluginManager().getPlugin("Citizens") != null) hasCitizens = true;
        new InventoryManager();
        getServer().getPluginManager().registerEvents(new ListenerEvent(), this);
        getServer().getPluginManager().registerEvents(new InventoryManager(), this);
        getCommand("mod").setExecutor(new CommandMod());
        getCommand("cps").setExecutor(new CommandCps());
        getCommand("s").setExecutor(new CommandS());
        getCommand("freeze").setExecutor(new CommandFreeze());
        getCommand("vanish").setExecutor(new CommandVanish());
        System.out.println("Démarrage terminé en " + TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()-time)+"."+
                TimeUnit.MILLISECONDS.toMillis(System.currentTimeMillis()-time)+" !");

    }

    @Override
    public void onDisable() {
        CommandMod.IsinMod.clear();
        System.out.println("Plugin éteint !");
    }
}
