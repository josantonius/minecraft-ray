package dev.josantonius.minecraft.ray.command

import dev.josantonius.minecraft.ray.Main
import dev.josantonius.minecraft.ray.RayManager
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class RayCommandExecutor(private val plugin: Main, private val rayManager: RayManager) :
        CommandExecutor {
    override fun onCommand(
            sender: CommandSender,
            command: Command,
            label: String,
            args: Array<String>
    ): Boolean {
        if (!sender.hasPermission("ray.admin")) {
            plugin.sendMessage(sender, "error.command.permission")
            return true
        }

        when {
            args.isEmpty() -> handleNoArgsCommand(sender)
            args[0] == "*" -> handleAsteriskCommand(sender, args)
            else -> handleTargetedPlayersCommand(sender, args)
        }

        return true
    }

    private fun handleNoArgsCommand(sender: CommandSender) {
        if (sender is Player) {
            rayManager.strikeRay(sender)
        } else {
            plugin.sendMessage(sender, "error.command.for_players")
        }
    }

    private fun handleAsteriskCommand(sender: CommandSender, args: Array<String>) {
        val mortal = args.size > 1 && args[1].equals("mortal", ignoreCase = true)
        rayManager.strikeRayToAllPlayers(sender, mortal)
    }

    private fun handleTargetedPlayersCommand(sender: CommandSender, args: Array<String>) {
        val mortal = args.last().equals("mortal", ignoreCase = true)
        rayManager.strikeRayToPlayers(sender, mortal, args)
    }
}
