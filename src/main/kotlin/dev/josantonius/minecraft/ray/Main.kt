package dev.josantonius.minecraft.ray

import dev.josantonius.minecraft.messaging.Message
import dev.josantonius.minecraft.ray.command.EmptyTabCompleter
import dev.josantonius.minecraft.ray.command.RayCommandExecutor
import dev.josantonius.minecraft.ray.command.RayHelpCommandExecutor
import dev.josantonius.minecraft.ray.command.RayReloadCommandExecutor
import java.io.File
import java.util.HashSet
import java.util.UUID
import net.kyori.adventure.text.Component
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.HandlerList
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin(), Listener {
    lateinit var message: Message
    lateinit var configuration: RayConfig
    val downedPlayers: MutableSet<UUID> = HashSet()

    override fun onEnable() {
        load()
    }

    @EventHandler
    fun onPlayerDeath(event: PlayerDeathEvent) {
        val player = event.entity
        if (downedPlayers.contains(player.uniqueId)) {
            event.deathMessage(
                    Component.text(message.getString("announcement.death_reason", player.name))
            )
            downedPlayers.remove(player.uniqueId)
        }
    }

    fun load() {
        val messagesFile = File(dataFolder, "messages.yml")
        if (!messagesFile.exists()) {
            saveResource("messages.yml", false)
        }

        message = Message(messagesFile, this)
        message.setConsolePrefix("<blue>[<aqua>Ray<blue>] <white>")
        message.setChatPrefix("<blue>[<aqua>Ray<blue>] <white>")

        configuration = RayConfig(this)

        HandlerList.getHandlerLists().forEach { handlerList ->
            handlerList.unregister(this as Listener)
        }
        server.pluginManager.registerEvents(this, this)

        val rayManager = RayManager(this)

        val rayCommandExecutor = RayCommandExecutor(this, rayManager)
        val rayHelpCommandExecutor = RayHelpCommandExecutor(this, rayManager)
        val rayReloadCommandExecutor = RayReloadCommandExecutor(this)

        val rayTabCompleter = RayTabCompleter()
        val emptyTabCompleter = EmptyTabCompleter()

        getCommand("ray")?.setTabCompleter(rayTabCompleter)
        getCommand("ray")?.setExecutor(rayCommandExecutor)
        getCommand("rayhelp")?.setTabCompleter(emptyTabCompleter)
        getCommand("rayhelp")?.setExecutor(rayHelpCommandExecutor)
        getCommand("rayreload")?.setTabCompleter(emptyTabCompleter)
        getCommand("rayreload")?.setExecutor(rayReloadCommandExecutor)
    }

    fun reload(sender: CommandSender) {
        load()
        sendMessage(sender, "plugin.reloaded")
    }

    fun sendMessage(sender: CommandSender, key: String, vararg params: String) {
        if (sender is Player) {
            message.sendToPlayer(sender, key, *params)
        } else {
            message.sendToConsole(key, *params)
        }
    }
}
