package fr.seyfle.verymc;

import fr.seyfle.verymc.Commands.CommandMod;
import fr.seyfle.verymc.Listener.ListenerEvent;
import fr.seyfle.verymc.manager.InventoryManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("PLugin VeryStaff on");
        new InventoryManager();
        getServer().getPluginManager().registerEvents(new ListenerEvent(), this);
        getCommand("mod").setExecutor(new CommandMod());

    }

    @Override
    public void onDisable() {
        System.out.println("PLugin VeryStaff off");
    }
}
