package com.github.sirmonkeyboy.dragonFight.Commands;

import com.github.sirmonkeyboy.dragonFight.DragonFight;
import com.github.sirmonkeyboy.dragonFight.Utils.ConfigManager;
import com.github.sirmonkeyboy.dragonFight.Utils.Utils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class StartDragonFightEvent implements TabExecutor {

    private final DragonFight plugin;

    private final ConfigManager configManager;

    private final Utils utils;

    public StartDragonFightEvent(DragonFight plugin, ConfigManager configManager, Utils utils) {
        this.plugin = plugin;
        this.configManager = configManager;
        this.utils = utils;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] args) {
        utils.announcement(Component.text(configManager.getDragonFightStartingInMessage()).color(NamedTextColor.WHITE));

        new BukkitRunnable() {
            @Override
            public void run() {
                configManager.setDragonFight(sender, String.valueOf(true));
                utils.announcement(Component.text(configManager.getDragonFightStartedMessage()).color(NamedTextColor.WHITE));
            }
        }.runTaskLater(plugin, configManager.getDragonFightStartDelay());

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        return List.of();
    }
}
