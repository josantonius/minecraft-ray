package dev.josantonius.minecraft.ray.command

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter

class EmptyTabCompleter : TabCompleter {
    override fun onTabComplete(
            sender: CommandSender,
            command: Command,
            alias: String,
            args: Array<String>
    ): List<String>? {
        return emptyList()
    }
}
