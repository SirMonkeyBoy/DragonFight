package com.github.sirmonkeyboy.dragonFight.Commands;

import com.github.sirmonkeyboy.dragonFight.DragonFight;
import com.github.sirmonkeyboy.dragonFight.Utils.ConfigManager;
import com.github.sirmonkeyboy.dragonFight.Utils.DragonFightSession;
import com.github.sirmonkeyboy.dragonFight.Utils.Utils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DragonFightCommand implements CommandExecutor, TabCompleter {
    private final DragonFight plugin;
    private final ConfigManager configManager;
    private final DragonFightSession dragonFightSession;

    public DragonFightCommand(DragonFight plugin, ConfigManager configManager, DragonFightSession dragonFightSession) {
        this.plugin = plugin;
        this.configManager = configManager;
        this.dragonFightSession = dragonFightSession;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] args) {
        if (!commandSender.hasPermission("dragonfight.command.use")) {
            commandSender.sendMessage("You do not have permission to use this command.");
            return true;
        }
        switch (args.length) {
            case 0 -> {
                commandSender.sendMessage("Usage: /dragonfight start|stop|list-participants|reset|reload");
                return true;
            }
            case 1 -> {
                switch (args[0].toLowerCase()) {
                    case "start" -> {
                        Utils.announcement(Component.text(configManager.getDragonFightStartingInMessage()).color(NamedTextColor.WHITE));
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                dragonFightSession.startSession();
                            }
                        }.runTaskLater(plugin, configManager.getDragonFightStartDelay());
                        return true;
                    }
                    case "stop" -> {
                        dragonFightSession.endSession();
                        commandSender.sendMessage("Dragon fight has ended.");
                        return true;
                    }
                    case "list-participants" -> {
                        commandSender.sendMessage("Participants in the dragon fight:");
                        dragonFightSession.getParticipants()
                                .forEach(participant -> {
                                    Player player = plugin.getServer().getPlayer(participant);
                                    if (player != null) {
                                        commandSender.sendMessage("Participant: " + player.getName());
                                    }
                                });
                        return true;
                    }
                    case "reset" -> {
                        dragonFightSession.resetSession();
                        commandSender.sendMessage("Dragon fight session has been reset.");
                        return true;
                    }
                    case "reload" -> {
                        configManager.reloadConfigManager(commandSender);
                        commandSender.sendMessage(Component.text("Config successfully reloaded").color(NamedTextColor.GREEN));
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] args) {
        if (args.length == 1) {
            return List.of("start", "stop", "list-participants", "reset", "reload");
        }
        return List.of();
    }
}
