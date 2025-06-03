package com.github.sirmonkeyboy.dragonFight;

import com.github.sirmonkeyboy.dragonFight.Commands.EnableDragonFight;
import com.github.sirmonkeyboy.dragonFight.Commands.StartDragonFightEvent;
import com.github.sirmonkeyboy.dragonFight.Listeners.DragonAndCrystalDamage;
import com.github.sirmonkeyboy.dragonFight.Listeners.EnderDragonDeath;
import com.github.sirmonkeyboy.dragonFight.Utils.ConfigManager;
import com.github.sirmonkeyboy.dragonFight.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class DragonFight extends JavaPlugin {

    @Override
    public void onEnable() {

        this.saveDefaultConfig();

        ConfigManager configManager = new ConfigManager(this);

        Utils utils = new Utils();

        Objects.requireNonNull(getCommand("EnableDragonFight")).setExecutor(new EnableDragonFight(this, configManager));
        Objects.requireNonNull(getCommand("StartDragonFightEvent")).setExecutor(new StartDragonFightEvent(this, configManager, utils));

        Bukkit.getPluginManager().registerEvents(new DragonAndCrystalDamage(configManager), this);
        Bukkit.getPluginManager().registerEvents(new EnderDragonDeath(configManager, utils), this);

        getLogger().info("Dragon Fight started");
    }

    @Override
    public void onDisable() {
        getLogger().info("Dragon Fight shut down");
    }
}
