package com.github.sirmonkeyboy.dragonFight.Utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

import java.util.Set;
import java.util.UUID;

public class DragonFightSession {
    private final ConfigManager configManager;
    private boolean isActive;
    private long startTime;
    private long endTime;
    private Set<UUID> participants;


    public DragonFightSession(ConfigManager configManager) {
        this.configManager = configManager;
        this.isActive = false;
        this.startTime = 0;
        this.endTime = 0;
        this.participants = Set.of();
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
}
