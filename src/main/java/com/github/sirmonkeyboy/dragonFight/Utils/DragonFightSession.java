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

    public boolean isActive() {
        return isActive;
    }

    public void startSession() {
        this.isActive = true;
        this.startTime = System.currentTimeMillis();
        Utils.announcement(Component.text(configManager.getDragonFightStartedMessage()).color(NamedTextColor.WHITE));
    }

    public void endSession() {
        this.isActive = false;
        this.endTime = System.currentTimeMillis();
        Utils.announcement(Component.text(configManager.getDragonDeathMessage()).color(NamedTextColor.WHITE));
        dragonFightRewards();
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
        this.isActive = false;
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
