package com.github.sirmonkeyboy.dragonFight.Listeners;

import com.github.sirmonkeyboy.dragonFight.Utils.ConfigManager;
import com.github.sirmonkeyboy.dragonFight.Utils.Utils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.EnderDragon;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;


public class EnderDragonDeath implements Listener {

    private final ConfigManager configManager;

    private final Utils utils;

    public EnderDragonDeath(ConfigManager configManager, Utils utils) {
        this.configManager = configManager;
        this.utils = utils;
    }

    @EventHandler
    public void dragonDeath(EntityDeathEvent event) {
        if (!(event.getEntity() instanceof EnderDragon)) return;
        utils.announcement(Component.text(configManager.getDragonDeathMessage()).color(NamedTextColor.WHITE));
    }
}
