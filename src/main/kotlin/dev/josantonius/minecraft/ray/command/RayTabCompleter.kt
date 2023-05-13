package dev.josantonius.minecraft.ray

import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player
import org.bukkit.util.StringUtil

class RayTabCompleter : TabCompleter {
    override fun onTabComplete(
            sender: CommandSender,
            command: Command,
            alias: String,
            args: Array<String>
    ): List<String>? {
        if (sender !is Player || command.name != "ray") {
            return null
        }

        val completions: MutableList<String> = ArrayList()

        if (args.size >= 1) {
            val lastArg = args[args.size - 1]
            val onlinePlayerNames = getOnlinePlayerNames().filter { !args.contains(it) }

            if (args.size == 1) {
                val suggestions = mutableListOf("*")
                suggestions.addAll(onlinePlayerNames)
                StringUtil.copyPartialMatches(lastArg, suggestions, completions)
            } else if (!args.contains("mortal")) {
                if (args.size == 2 && args[0] == "*") {
                    StringUtil.copyPartialMatches(lastArg, getMortalList(), completions)
                } else {
                    StringUtil.copyPartialMatches(
                            lastArg,
                            onlinePlayerNames + getMortalList(),
                            completions
                    )
                }
            }
        }

        return completions
    }

    private fun getOnlinePlayerNames(): List<String> {
        val names: MutableList<String> = ArrayList()
        for (player in Bukkit.getOnlinePlayers()) {
            names.add(player.name)
        }
        return names
    }

    private fun getMortalList(): List<String> {
        val mortalList: MutableList<String> = ArrayList()
        mortalList.add("mortal")
        return mortalList
    }
}
