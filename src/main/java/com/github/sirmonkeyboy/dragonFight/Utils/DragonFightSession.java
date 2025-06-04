package com.github.sirmonkeyboy.dragonFight.Utils;

import com.github.sirmonkeyboy.dragonFight.DragonFight;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class DragonFightSession {
    private final DragonFight plugin;
    private final ConfigManager configManager;
    private boolean isActive;
    private boolean isStarting;
    private boolean hasHappened;
    private long startTime;
    private long endTime;
    private Set<UUID> participants;


    public DragonFightSession(DragonFight plugin, ConfigManager configManager) {
        this.plugin = plugin;
        this.configManager = configManager;
        this.isActive = false;
        this.startTime = 0;
        this.endTime = 0;
        this.participants = ConcurrentHashMap.newKeySet();
    }

    public boolean isStarting() {
        return isStarting;
    }

    public boolean isActive() {
        return isActive;
    }

    public boolean getHasHappened() {
        return hasHappened;
    }

    public void startingSession() {
        this.hasHappened = false;
        this.isStarting = true;
        Utils.announcement(Component.text(configManager.getDragonFightStartingInMessage()).color(NamedTextColor.WHITE));
    }

    public void startSession() {
        if (!this.isStarting) return;
        this.isActive = true;
        this.startTime = System.currentTimeMillis();
        Utils.announcement(Component.text(configManager.getDragonFightStartedMessage()).color(NamedTextColor.WHITE));
        this.isStarting = false;
    }

    public void endSession() {
        this.isActive = false;
        this.hasHappened = true;
        this.endTime = System.currentTimeMillis();
        Utils.announcement(Component.text(configManager.getDragonDeathMessage()).color(NamedTextColor.WHITE));
        dragonFightRewards();
    }

    public boolean stopSession() {
        if (this.isStarting) {
            this.isStarting = false;
            Utils.announcement(Component.text("The Dragon Fight is canceled").color(NamedTextColor.WHITE));
            return true;
        }
        if (this.isActive) {
            this.isActive = false;
            this.endTime = System.currentTimeMillis();
            Utils.announcement(Component.text("The Dragon fight has been stopped"));
            return true;
        }
        return false;
    }

    public long getDuration() {
        if (isActive) {
            return System.currentTimeMillis() - startTime;
        } else {
            return endTime - startTime;
        }
    }

    public Set<UUID> getParticipants() {
        return participants;
    }

    public void addParticipant(UUID participant) {
        participants.add(participant);
    }

    public void removeParticipant(UUID participant) {
        participants.remove(participant);
    }

    public void clearParticipants() {
        participants.clear();
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void resetSession() {
        this.isStarting = false;
        this.isActive = false;
        this.hasHappened = false;
        this.startTime = 0;
        this.endTime = 0;
        clearParticipants();
    }

    public void dragonFightRewards() {
        getParticipants().forEach(participants -> {
           Player player = plugin.getServer().getPlayer(participants);
            if (player != null) {
                player.sendMessage(Component.text(configManager.getDragonFightParticipationMessage()));
                List<String> rewardsList = configManager.getDragonFightRewards();
                if (rewardsList != null && !rewardsList.isEmpty()) {
                    for (String rewards : rewardsList) {
                        rewards = rewards.replace("%Player%", player.getName());
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), rewards);
                    }
                }
            }
        });
    }
}
