package com.github.sirmonkeyboy.dragonFight.Utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;

public class Utils {

    public void announcement(Component message) {
        Bukkit.broadcast(Component.text("[").color(NamedTextColor.GOLD)
                .append(Component.text("Theatria").color(NamedTextColor.DARK_RED))
                .append(Component.text("] ").color(NamedTextColor.GOLD))
                .append(message));
    }
}
