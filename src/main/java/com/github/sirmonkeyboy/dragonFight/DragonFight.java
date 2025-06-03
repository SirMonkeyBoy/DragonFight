package com.github.sirmonkeyboy.dragonFight;

import com.github.sirmonkeyboy.dragonFight.Commands.EnableDragonFight;
import com.github.sirmonkeyboy.dragonFight.Listeners.DragonAndCrystalDamage;
import com.github.sirmonkeyboy.dragonFight.Utils.ConfigManager;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class DragonFight extends JavaPlugin {

    @Override
    public void onEnable() {

        this.saveDefaultConfig();

        ConfigManager configManager = new ConfigManager(this);

        Objects.requireNonNull(getCommand("EnableDragonFight")).setExecutor(new EnableDragonFight(this, configManager));

        Bukkit.getPluginManager().registerEvents(new DragonAndCrystalDamage(this, configManager), this);

        getLogger().info("Dragon Fight started");
    }

    @Override
    public void onDisable() {
        getLogger().info("Dragon Fight shut down");
    }
}
