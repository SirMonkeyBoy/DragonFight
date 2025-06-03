package com.github.sirmonkeyboy.dragonFight.Listeners;

import com.github.sirmonkeyboy.dragonFight.DragonFight;
import com.github.sirmonkeyboy.dragonFight.Utils.ConfigManager;

import net.kyori.adventure.text.Component;

import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class DragonAndCrystalDamage implements Listener {

    private final DragonFight plugin;

    private final ConfigManager configManager;

    public DragonAndCrystalDamage(DragonFight plugin, ConfigManager configManager) {
        this.plugin = plugin;
        this.configManager = configManager;
    }

    @EventHandler
    public void dragonDamage(EntityDamageByEntityEvent event) {
        if (configManager.getIsDragonFightEnabled()) {
            return;
        }

        boolean isDragon = event.getEntity() instanceof EnderDragon;
        boolean isCrystal = event.getEntity() instanceof EnderCrystal;

        if (!isDragon && !isCrystal) {
            return;
        }

        event.setCancelled(true);

        // Get the player who caused the damage
        Player damager = null;

        if (event.getDamager() instanceof Player p) {
            damager = p;
        } else if (event.getDamager() instanceof org.bukkit.entity.Projectile projectile) {
            if (projectile.getShooter() instanceof Player shooter) {
                damager = shooter;
            }
        }

        if (damager != null) {
            if (isDragon) {
                damager.sendMessage(Component.text("You can't damage the Ender Dragon before the event"));
                return;
            }
            damager.sendMessage(Component.text("You can't damage the Ender Crystals before the event"));
        }
    }
}
