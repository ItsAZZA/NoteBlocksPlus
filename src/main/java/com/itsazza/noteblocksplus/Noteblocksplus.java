package com.itsazza.noteblocksplus;

import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Set;
import java.util.logging.Level;

public final class Noteblocksplus extends JavaPlugin {
    private static Noteblocksplus INSTANCE;
    private static final HashMap<Material, String> replacements = new HashMap<>();
    private static boolean hasSoundCategory = true;
    private static boolean particlesEnabled = false;

    @Override
    public void onEnable() {
        INSTANCE = this;

        getServer().getPluginManager().registerEvents(new Event(), this);
        getCommand("noteblocksplus").setExecutor(new Command());
        saveDefaultConfig();
        loadNoteReplacements();

        if (Bukkit.getVersion().contains("1.10")) {
            hasSoundCategory = false;
        }

        if (getConfig().getBoolean("particles.enabled")) {
            particlesEnabled = true;
        }

        new Metrics(this, 11083);
    }

    public static Noteblocksplus getInstance() {
        return INSTANCE;
    }

    public static HashMap<Material, String> getReplacements() {
        return replacements;
    }

    public static boolean hasSoundCategory() {
        return hasSoundCategory;
    }

    public static boolean isParticlesEnabled() {
        return particlesEnabled;
    }

    public boolean loadNoteReplacements() {
        replacements.clear();
        boolean response = true;

        Set<String> keys = getConfig().getConfigurationSection("sounds").getKeys(false);
        for (String key : keys) {
            String soundName = getConfig().getString("sounds." + key);
            Material material;

            try {
                material = Material.valueOf(key);
            } catch (IllegalArgumentException e) {
                getLogger().log(Level.SEVERE, "Error setting sound " + soundName + " for block " + key + ": Invalid material!");
                response = false;
                continue;
            }

            replacements.put(material, soundName);
        }
        getLogger().log(Level.INFO, "Added " + replacements.size() + " new sound(s) to note blocks!");
        return response;
    }
}
