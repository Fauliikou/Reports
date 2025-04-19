package org.fauli.reports;

import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Getter
    public static Main instance;

    @Override
    public void onEnable() {

        instance = this;

        PluginManager pluginManager = Bukkit.getPluginManager();

        getSLF4JLogger().info("Reports plugin by Fauli started!");
    }

    @Override
    public void onDisable() {

    }
}