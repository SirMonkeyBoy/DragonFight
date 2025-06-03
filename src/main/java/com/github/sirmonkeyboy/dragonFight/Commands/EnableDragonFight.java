package com.github.sirmonkeyboy.dragonFight.Commands;

import com.github.sirmonkeyboy.dragonFight.DragonFight;
import com.github.sirmonkeyboy.dragonFight.Utils.ConfigManager;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class EnableDragonFight implements TabExecutor {

    private final DragonFight plugin;

    private final ConfigManager configManager;

    public EnableDragonFight(DragonFight plugin, ConfigManager configManager) {
        this.plugin = plugin;
        this.configManager = configManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] args) {

        if (args.length != 1) {
            sender.sendMessage("Usage: /enableragonfight <true|false>");
            return true;
        }

        String input = args[0].toLowerCase();
        if (!input.equals("true") && !input.equals("false")) {
            sender.sendMessage("Please enter either true or false.");
            return true;
        }

        boolean enabled = Boolean.parseBoolean(input);

        plugin.getConfig().set("Dragon-Fight", enabled);
        plugin.saveConfig();
        configManager.reloadConfigManager(sender);

        sender.sendMessage("Dragon fight has been " + (enabled ? "enabled" : "disabled") + ".");
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        return List.of();
    }
}
