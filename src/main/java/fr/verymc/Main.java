package fr.verymc;

import fr.verymc.Commands.CommandCps;
import fr.verymc.Commands.CommandMod;
import fr.verymc.Listener.ListenerEvent;
import fr.verymc.manager.InventoryManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    public static Main instance;

    @Override
    public void onEnable() {
        instance = this;
        System.out.println("PLugin VeryStaff on");
        new InventoryManager();
        getServer().getPluginManager().registerEvents(new ListenerEvent(), this);
        getCommand("mod").setExecutor(new CommandMod());
        getCommand("cps").setExecutor(new CommandCps());

    }

    @Override
    public void onDisable() {
        System.out.println("PLugin VeryStaff off");
    }
}
