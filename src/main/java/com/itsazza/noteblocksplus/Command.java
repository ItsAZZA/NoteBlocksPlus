package com.itsazza.noteblocksplus;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return true;

        if (args.length != 1 || !args[0].equals("reload")) {
            sender.sendMessage("§cUsage: /nbp reload");
            return true;
        }

        if (!sender.hasPermission("noteblocksplus.reload")) {
            sender.sendMessage("§cNo permission to reload!");
            return true;
        }

        Noteblocksplus plugin = Noteblocksplus.INSTANCE;
        plugin.reloadConfig();
        boolean response = plugin.loadNoteReplacements();
        if (response) {
            sender.sendMessage("§e[NoteBlocksPlus] Reloaded config!");
        } else {
            sender.sendMessage("§c[NoteBlocksPlus] An error occurred reloading the config. Please check the console for more information.");
        }
        return true;
    }
}
