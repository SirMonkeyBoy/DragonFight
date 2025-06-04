package com.github.sirmonkeyboy.dragonFight.Listeners;

import com.github.sirmonkeyboy.dragonFight.Utils.ConfigManager;
import com.github.sirmonkeyboy.dragonFight.Utils.DragonFightSession;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class DragonAndCrystalDamageListener implements Listener {
    private final ConfigManager configManager;
    private final DragonFightSession dragonFightSession;

    public DragonAndCrystalDamageListener(ConfigManager configManager, DragonFightSession dragonFightSession) {
        this.configManager = configManager;
        this.dragonFightSession = dragonFightSession;
    }

    @EventHandler
    public void dragonDamage(EntityDamageByEntityEvent event) {
        if (dragonFightSession.isActive()) {
            if (event.getDamager() instanceof Player player) {
                dragonFightSession.addParticipant(player.getUniqueId());
            } else if (event.getDamager() instanceof Projectile projectile && projectile.getShooter() instanceof Player shootingPlayer) {
                dragonFightSession.addParticipant(shootingPlayer.getUniqueId());
            }
            return;
        }

        if (!(event.getEntity() instanceof EnderDragon)) return;

        if (dragonFightSession.getHasHappened()) return;

        event.setCancelled(true);

        if (event.getDamager() instanceof Player player) {
            player.sendMessage(Component.text(configManager.getDragonDenyDamageMessage()));
        } else if (event.getDamager() instanceof Projectile projectile && projectile.getShooter() instanceof Player shootingPlayer) {
            shootingPlayer.sendMessage(Component.text(configManager.getDragonDenyDamageMessage()));
        }
    }

    // These methods are super-ugly but my brain isn't working right now.

    @EventHandler
    public void crystalDamage(EntityDamageByEntityEvent event) {
        if (dragonFightSession.isActive()) {
            if (event.getDamager() instanceof Player player) {
                dragonFightSession.addParticipant(player.getUniqueId());
            } else if (event.getDamager() instanceof Projectile projectile && projectile.getShooter() instanceof Player shootingPlayer) {
                dragonFightSession.addParticipant(shootingPlayer.getUniqueId());
            }
            return;
        }

        if (!(event.getEntity() instanceof EnderCrystal)) return;

        if (dragonFightSession.getHasHappened()) return;

        event.setCancelled(true);

        if (event.getDamager() instanceof Player player) {
            player.sendMessage(Component.text(configManager.getCrystalDenyDamageMessage()));
        } else if (event.getDamager() instanceof Projectile projectile && projectile.getShooter() instanceof Player shootingPlayer) {
            shootingPlayer.sendMessage(Component.text(configManager.getCrystalDenyDamageMessage()));
        }
    }
}
