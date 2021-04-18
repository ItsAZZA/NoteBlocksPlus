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
        plugin.loadNoteReplacements();
        sender.sendMessage("§eReloaded config!");
        return true;
    }
}
