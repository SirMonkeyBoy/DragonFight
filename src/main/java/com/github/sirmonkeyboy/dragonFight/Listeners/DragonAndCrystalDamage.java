package com.github.sirmonkeyboy.dragonFight.Listeners;

import com.github.sirmonkeyboy.dragonFight.Utils.ConfigManager;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class DragonAndCrystalDamage implements Listener {

    private final ConfigManager configManager;

    public DragonAndCrystalDamage(ConfigManager configManager) {
        this.configManager = configManager;
    }

    @EventHandler
    public void dragonDamage(EntityDamageByEntityEvent event) {
        if (configManager.getIsDragonFightEnabled()) {
            return;
        }

        boolean isDragon = event.getEntity() instanceof EnderDragon;

        if (!isDragon) return;

        event.setCancelled(true);

        if (event.getDamager() instanceof Player player) {
            player.sendMessage(Component.text(configManager.getDragonDenyDamageMessage()));
        } else if (event.getDamager() instanceof Projectile projectile && projectile.getShooter() instanceof Player shootingPlayer) {
            shootingPlayer.sendMessage(Component.text(configManager.getDragonDenyDamageMessage()));
        }
    }

    @EventHandler
    public void crystalDamage(EntityDamageByEntityEvent event) {
        if (configManager.getIsDragonFightEnabled()) {
            return;
        }
        boolean isCrystal = event.getEntity() instanceof EnderCrystal;

        if (!isCrystal) return;

        event.setCancelled(true);

        if (event.getDamager() instanceof Player player) {
            player.sendMessage(Component.text(configManager.getCrystalDenyDamageMessage()));
        } else if (event.getDamager() instanceof Projectile projectile && projectile.getShooter() instanceof Player shootingPlayer) {
            shootingPlayer.sendMessage(Component.text(configManager.getCrystalDenyDamageMessage()));
        }
    }
}
