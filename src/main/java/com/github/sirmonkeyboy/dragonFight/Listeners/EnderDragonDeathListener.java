package com.github.sirmonkeyboy.dragonFight.Listeners;

import com.github.sirmonkeyboy.dragonFight.Utils.DragonFightSession;
import org.bukkit.entity.EnderDragon;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;


public class EnderDragonDeathListener implements Listener {
    private final DragonFightSession dragonFightSession;

    public EnderDragonDeathListener(DragonFightSession dragonFightSession) {
        this.dragonFightSession = dragonFightSession;
    }

    @EventHandler
    public void dragonDeath(EntityDeathEvent event) {
        if (!(event.getEntity() instanceof EnderDragon)) return;
        if (dragonFightSession.getHasHappened()) return;
        dragonFightSession.endSession();
    }
}
