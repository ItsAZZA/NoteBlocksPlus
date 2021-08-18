package com.itsazza.noteblocksplus;

import com.itsazza.noteblocksplus.api.NoteBlocksPlusAPI;
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
        if (!Noteblocksplus.getReplacements().containsKey(block.getType())) return;

        Location loc = block.getLocation();
        World world = loc.getWorld();
        assert world != null;

        float pitch = NoteBlocksPlusAPI.getNotePitch(e.getNote().getId());
        if (Noteblocksplus.hasSoundCategory()) {
            world.playSound(loc, Noteblocksplus.getReplacements().get(block.getType()), SoundCategory.RECORDS, 1.0F, pitch);
        } else {
            world.playSound(loc, Noteblocksplus.getReplacements().get(block.getType()), 1.0F, pitch);
        }
        e.setCancelled(true);

        if (Noteblocksplus.isParticlesEnabled()) {
            world.spawnParticle(Particle.NOTE, loc.add(0.5, 2.2, 0.5), 0, pitch, 0, 0);
        }
    }
}