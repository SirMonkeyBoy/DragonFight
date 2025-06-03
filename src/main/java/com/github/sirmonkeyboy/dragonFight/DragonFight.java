package com.github.sirmonkeyboy.dragonFight;

import com.github.sirmonkeyboy.dragonFight.Commands.DragonFightCommand;
import com.github.sirmonkeyboy.dragonFight.Listeners.DragonAndCrystalDamage;
import com.github.sirmonkeyboy.dragonFight.Listeners.EnderDragonDeath;
import com.github.sirmonkeyboy.dragonFight.Utils.ConfigManager;
import com.github.sirmonkeyboy.dragonFight.Utils.DragonFightSession;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class DragonFight extends JavaPlugin {

    @Override
    public void onEnable() {

        this.saveDefaultConfig();

        ConfigManager configManager = new ConfigManager(this);
        DragonFightSession dragonFightSession = new DragonFightSession(configManager);

        Objects.requireNonNull(getCommand("DragonFight")).setExecutor(new DragonFightCommand(this, configManager, dragonFightSession));

        Bukkit.getPluginManager().registerEvents(new DragonAndCrystalDamage(configManager, dragonFightSession), this);
        Bukkit.getPluginManager().registerEvents(new EnderDragonDeath(dragonFightSession), this);

        getLogger().info("Dragon Fight started");
    }

    @Override
    public void onDisable() {
        getLogger().info("Dragon Fight shut down");
    }
}
