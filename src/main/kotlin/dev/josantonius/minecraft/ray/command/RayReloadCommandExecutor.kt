package dev.josantonius.minecraft.ray.command

import dev.josantonius.minecraft.ray.Main
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class RayReloadCommandExecutor(private val plugin: Main) : CommandExecutor {
    override fun onCommand(
            sender: CommandSender,
            cmd: Command,
            label: String,
            args: Array<String>
    ): Boolean {
        if (sender.hasPermission("ray.admin")) {
            plugin.reload(sender)
        } else {
            plugin.sendMessage(sender, "error.command.permission")
        }
        return true
    }
}
