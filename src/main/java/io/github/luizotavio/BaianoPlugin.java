package io.github.luizotavio;

import io.github.luizotavio.listener.ShopkeeperHandler;
import io.github.luizotavio.util.Entities;
import io.github.luizotavio.util.Shopkeeper;
import org.bukkit.plugin.java.JavaPlugin;

public class BaianoPlugin extends JavaPlugin {

    @Override
    public void onLoad() {

    }

    @Override
    public void onEnable() {
        Entities.register("Villager", 120, Shopkeeper.class);

        getServer().getPluginManager().registerEvents(new ShopkeeperHandler(), this);
    }

    @Override
    public void onDisable() {

    }
}
