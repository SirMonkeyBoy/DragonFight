package com.github.sirmonkeyboy.dragonFight.Utils;

import com.github.sirmonkeyboy.dragonFight.DragonFight;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

import org.bukkit.command.CommandSender;


public class ConfigManager {

    private final DragonFight plugin;

    private boolean isDragonFightDisabled;

    public ConfigManager(DragonFight plugin) {
        this.plugin = plugin;
        load();
    }

    public void reloadConfigManager(CommandSender sender) {
        plugin.reloadConfig();
        load();
        sender.sendMessage(Component.text("Config successfully reloaded").color(NamedTextColor.GREEN));
    }

    public void load() {
        isDragonFightDisabled = plugin.getConfig().getBoolean("Dragon-Fight");
    }

    public boolean getIsDragonFightEnabled() {
        return isDragonFightDisabled;
    }
}
