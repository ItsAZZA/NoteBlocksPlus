package com.itsazza.noteblocksplus.api;

import com.itsazza.noteblocksplus.Noteblocksplus;
import org.bukkit.Material;

import java.util.HashMap;

@SuppressWarnings("unused")
public class NoteBlocksPlusAPI {
    public static HashMap<Material, String> getSounds() {
        return Noteblocksplus.getReplacements();
    }

    public static float getNotePitch(float value) {
        return 0.5f + 0.0625f * value;
    }
}
