package com.github.sirmonkeyboy.dragonFight.Utils;

import com.github.sirmonkeyboy.dragonFight.DragonFight;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.CommandSender;

public class ConfigManager {

    private final DragonFight plugin;

    private boolean isDragonFightDisabled;
    private String dragonDenyDamageMessage;
    private String crystalDenyDamageMessage;
    private String dragonDeathMessage;
    private String dragonFightStartingInMessage;
    private String dragonFightStartedMessage;
    private int dragonFightStartDelay;

    public ConfigManager(DragonFight plugin) {
        this.plugin = plugin;
        load();
    }

    public void reloadConfigManager(CommandSender sender) {
        plugin.reloadConfig();
        load();
        sender.sendMessage(Component.text("Config successfully reloaded").color(NamedTextColor.GREEN));
    }

    public void load() {
        isDragonFightDisabled = plugin.getConfig().getBoolean("Dragon-Fight");
        dragonDenyDamageMessage = plugin.getConfig().getString("Dragon-Deny-Damage-Message", "You can't damage the Ender Dragon before the event.");
        crystalDenyDamageMessage = plugin.getConfig().getString("Crystal-Deny-Damage-Message", "You can't damage the Ender Crystal before the event.");
        dragonFightStartingInMessage = plugin.getConfig().getString("Dragon-Fight-Starting-In-Message", "The Ender Dragon Fight will be starting in 5 minutes get ready.");
        dragonFightStartedMessage = plugin.getConfig().getString("Dragon-Fight-Started-Message", "The Ender Dragon Fight has started.");
        dragonDeathMessage = plugin.getConfig().getString("Dragon-Death-Message", "The Ender Dragon has been slain.");
        dragonFightStartDelay = plugin.getConfig().getInt("Dragon-Fight-Start-Delay", 6000);
    }

    public void setDragonFight(CommandSender sender, String  trueFalse) {
        boolean enabled = Boolean.parseBoolean(trueFalse);
        plugin.getConfig().set("Dragon-Fight", enabled);
        plugin.saveConfig();
        reloadConfigManager(sender);
    }

    public boolean getIsDragonFightEnabled() {
        return isDragonFightDisabled;
    }

    public String getDragonDenyDamageMessage() {
        return dragonDenyDamageMessage;
    }

    public String getCrystalDenyDamageMessage() {
        return crystalDenyDamageMessage;
    }

    public String getDragonFightStartingInMessage() {
        return dragonFightStartingInMessage;
    }

    public String getDragonFightStartedMessage() {
        return dragonFightStartedMessage;
    }

    public String getDragonDeathMessage() {
        return dragonDeathMessage;
    }

    public int getDragonFightStartDelay() {
        return dragonFightStartDelay;
    }
}