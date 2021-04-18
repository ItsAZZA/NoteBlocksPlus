package com.itsazza.noteblocksplus;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Set;
import java.util.logging.Level;

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
    }

    public void loadNoteReplacements() {
        replacements.clear();

        Set<String> keys = getConfig().getConfigurationSection("sounds").getKeys(false);
        for (String key : keys) {
            String soundName = getConfig().getString("sounds." + key);
            Sound sound;
            Material material;

            try {
                sound = Sound.valueOf(soundName);
                material = Material.valueOf(key);
            } catch (IllegalArgumentException e) {
                getLogger().log(Level.WARNING, "§cError setting sound " + soundName + " for block " + key);
                return;
            }

            replacements.put(material, sound);
        }
    }
}
