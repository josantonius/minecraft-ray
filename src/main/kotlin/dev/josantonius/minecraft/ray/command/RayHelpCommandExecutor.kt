package dev.josantonius.minecraft.ray.command

import dev.josantonius.minecraft.ray.Main
import dev.josantonius.minecraft.ray.RayManager
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class RayHelpCommandExecutor(private val plugin: Main, private val rayManager: RayManager) :
        CommandExecutor {
    override fun onCommand(
            sender: CommandSender,
            cmd: Command,
            label: String,
            args: Array<String>
    ): Boolean {
        if (sender.hasPermission("ray.admin")) {
            rayManager.showHelp(sender)
        } else {
            plugin.sendMessage(sender, "error.command.for_players")
        }
        return true
    }
}
