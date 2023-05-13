package dev.josantonius.minecraft.ray

import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class RayManager(private val plugin: Main) {

    fun strikeRay(sender: Player) {
        val strikeLocation = sender.getTargetBlock(null, 500).location
        sender.world.strikeLightning(strikeLocation)
    }

    fun strikeRayToPlayer(player: Player, special: Boolean) {
        val world = player.world
        world.strikeLightningEffect(player.location)
        if (special) {
            plugin.downedPlayers.add(player.uniqueId)
            player.health = 0.0
        }
    }

    fun strikeRayToPlayers(sender: CommandSender, special: Boolean, args: Array<String>) {
        val uniqueTargets: MutableSet<String> = HashSet()
        val limit = args.size - if (special) 1 else 0
        for (i in 0 until limit) {
            val targetName = args[i]
            if (uniqueTargets.add(targetName)) {
                val target = Bukkit.getPlayer(targetName)
                if (target != null) {
                    if (sender is Player && plugin.configuration.isExcludePlayer(target)) {
                        plugin.sendMessage(sender, "error.player.target")
                        return
                    }
                    strikeRayToPlayer(target, special)
                } else {
                    plugin.sendMessage(sender, "error.player.offline", targetName)
                }
            }
        }
    }

    fun strikeRayToAllPlayers(sender: CommandSender, special: Boolean) {
        Bukkit.getOnlinePlayers().forEach { player ->
            if (sender is Player && plugin.configuration.isExcludePlayer(player)) {
                plugin.sendMessage(sender, "error.player.target")
                return@forEach
            }
            strikeRayToPlayer(player, special)
        }
    }

    fun showHelp(player: CommandSender) {
        plugin.sendMessage(player, "help.header")
        plugin.sendMessage(player, "help.ray", "/ray")
        plugin.sendMessage(player, "help.ray_player", "/ray [player...] [mortal]")
        plugin.sendMessage(player, "help.ray_all", "/ray * [mortal]")
        if (player.hasPermission("ray.admin")) {
            plugin.sendMessage(player, "help.reload", "/rayreload")
        }
    }
}
