package fr.verymc;

import fr.verymc.Commands.*;
import fr.verymc.Listener.ListenerEvent;
import fr.verymc.manager.InventoryManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import redis.clients.jedis.JedisPool;

import java.util.concurrent.TimeUnit;

public final class Main extends JavaPlugin {

    public static Main instance;
    public static boolean hasCitizens = false;

    public static JedisPool pool;


    @Override
    public void onEnable() {
        long time = System.currentTimeMillis();
        System.out.println("Démarrage du plugin...");
        instance = this;
        if(Bukkit.getPluginManager().getPlugin("Citizens") != null) hasCitizens = true;
        new InventoryManager();
        getServer().getPluginManager().registerEvents(new ListenerEvent(), this);
        getServer().getPluginManager().registerEvents(new InventoryManager(), this);
        getCommand("mod").setExecutor(new CommandMod());
        getCommand("cps").setExecutor(new CommandCps());
        //getCommand("s").setExecutor(new CommandS());
        getCommand("freeze").setExecutor(new CommandFreeze());
        getCommand("vanish").setExecutor(new CommandVanish());

        new CommandMod();
        pool = new JedisPool(System.getenv("REDIS_HOST"), 6379);

        System.out.println("Démarrage terminé en " + TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()-time)+"."+
                TimeUnit.MILLISECONDS.toMillis(System.currentTimeMillis()-time)+" !");

    }

    @Override
    public void onDisable() {
        CommandMod.IsinMod.clear();
        pool.close();
        System.out.println("Plugin éteint !");
    }
}
