package com.itsazza.noteblocksplus;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.NotePlayEvent;

@SuppressWarnings("deprecation")
public class Event implements Listener {
    @EventHandler
    public void onNotePlay(NotePlayEvent e) {
        Block block = e.getBlock().getRelative(BlockFace.DOWN);
        if (!Noteblocksplus.replacements.containsKey(block.getType())) return;

        Location loc = block.getLocation();
        World world = loc.getWorld();
        assert world != null;

        float pitch = getNotePitch(e.getNote().getId());
        world.playSound(loc, Noteblocksplus.replacements.get(block.getType()), 1.0F, pitch);
        e.setCancelled(true);

        if (!Bukkit.getVersion().contains("1.8") && Noteblocksplus.INSTANCE.getConfig().getBoolean("particles.enabled")) {
            world.spawnParticle(Particle.NOTE, loc.add(0.5, 2.2, 0.5), 0, pitch, 0, 0);
        }
    }

    private float getNotePitch(float value) {
        return 0.5f + 0.0625f * value;
    }
}