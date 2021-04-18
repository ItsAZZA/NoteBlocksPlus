package com.itsazza.noteblocksplus;

import org.bstats.bukkit.Metrics;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Set;

public final class Noteblocksplus extends JavaPlugin {
    public static Noteblocksplus INSTANCE;
    public static HashMap<Material, Sound> replacements = new HashMap<>();

    @Override
    public void onEnable() {
        INSTANCE = this;

        getServer().getPluginManager().registerEvents(new Event(), this);
        getCommand("noteblocksplus").setExecutor(new Command());
        saveDefaultConfig();
        loadNoteReplacements();

        new Metrics(this, 11083);
    }

    public boolean loadNoteReplacements() {
        replacements.clear();
        boolean response = true;

        Set<String> keys = getConfig().getConfigurationSection("sounds").getKeys(false);
        for (String key : keys) {
            String soundName = getConfig().getString("sounds." + key);
            Material material;
            Sound sound;

            try {
                material = Material.valueOf(key);
            } catch (IllegalArgumentException e) {
                getLogger().severe("Error setting sound " + soundName + " for block " + key + ": Invalid material!");
                response = false;
                continue;
            }

            try {
                sound = Sound.valueOf(soundName);
            } catch (IllegalArgumentException e) {
                getLogger().severe("Error setting sound " + soundName + " for block " + key + ": Invalid sound!");
                response = false;
                continue;
            }
            replacements.put(material, sound);
        }
        return response;
    }
}
