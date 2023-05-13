package dev.josantonius.minecraft.ray

import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

class RayConfig(plugin: JavaPlugin) {
    private val config: FileConfiguration
    private val safePlayers: List<String>
    private val excludeOperators: Boolean

    init {
        plugin.saveDefaultConfig()
        plugin.reloadConfig()
        config = plugin.config
        safePlayers = config.getStringList("safePlayers")
        excludeOperators = config.getBoolean("excludeOperators")
    }

    fun isSafePlayer(player: Player): Boolean {
        return safePlayers.contains(player.name)
    }

    fun isExcludePlayer(player: Player): Boolean {
        if (isSafePlayer(player)) {
            return true
        } else if (player.isOp && excludeOperators()) {
            return true
        }
        return false
    }

    fun excludeOperators(): Boolean {
        return excludeOperators
    }
}
