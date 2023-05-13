# Minecraft Ray Plugin

[![Release](https://jitpack.io/v/dev.josantonius/minecraft-ray.svg)](https://jitpack.io/#dev.josantonius/minecraft-ray)
[![License](https://img.shields.io/github/license/josantonius/minecraft-ray)](LICENSE)

Launches a lightning bolt to where the player is aiming, to specific players or to all players online.

Lightning strikes to players can be set as lethal or non-lethal.

## [Watch demo on YouTube](https://www.youtube.com/watch?v=JJ9kkE0BY4s)

## Requirements

- Java 17 or higher.
- Purpur server 1.19.3 or Bukkit/Spigot/Paper server compatible with the Purpur API version used.

## Installation

1. Download the JAR file: [ray-1.0.0-purpur-1.19.3.jar](/build/libs/ray-1.0.0-purpur-1.19.3.jar).

1. Place the JAR file in the plugins folder of your Minecraft server.

1. Restart the server to load the plugin.

## Building

To build the plugin yourself, follow these steps:

1. Make sure you have `Java 17` or higher and `Gradle` installed on your system.

1. Clone the plugin repository on your local machine:

    ```bash
    git clone https://github.com/josantonius/minecraft-ray.git
    ```

1. Navigate to the directory of the cloned repository:

    ```bash
    cd minecraft-ray
    ```

1. Use Gradle to compile the plugin:

    ```bash
    gradle build
    ```

## Commands

- `/ray` - Launches a lightning bolt to where the player is aiming

- `/ray *` - Launches a lightning bolt to all players online

- `/ray <player>` - Launches a lightning bolt to the player specified

- `/ray <player> [player...]` - Launches a lightning bolt to the players specified

- `/ray * mortal` - Launches a deadly lightning bolt to all players online

- `/ray <player> mortal` - Launches a deadly lightning bolt to the player specified

- `/ray <player> [player...] mortal` - Launches a deadly lightning bolt to the players specified

- `/rayhelp` - Display help for the plugin commands

- `/rayreload` - Reload the plugin

All commands requires the `ray.admin` permission to be used.

## Configuration

The `plugins/Ray/config.yml` file contains specific plugin configurations.

### Excluding players from the plugin's effects

A list of player names who are considered safe and will not be affected by the plugin. Set this to
'null' or an empty list '[]' if you don't want to have any safe players.

```yaml
safePlayers: null
```

### Excluding operators from the plugin's effects

A boolean value determining whether server operators (OPs) should be excluded from the plugin's
effects. Set this to 'true' to exclude operators, or 'false' to include them

```yaml
excludeOperators: false
```

## Messages

The `plugins/Ray/messages.yml` file contains all the messages that the plugin uses.
You can change the messages to your liking.

## TODO

- [ ] Add new feature
- [ ] Create tests
- [ ] Improve documentation

## Changelog

Detailed changes for each release are documented in the
[release notes](https://github.com/josantonius/minecraft-ray/releases).

## Contribution

Please make sure to read the [Contributing Guide](.github/CONTRIBUTING.md), before making a pull
request, start a discussion or report a issue.

Thanks to all [contributors](https://github.com/josantonius/minecraft-ray/graphs/contributors)! :heart:

## Sponsor

If this project helps you to reduce your development time,
[you can sponsor me](https://github.com/josantonius#sponsor) to support my open source work :blush:

## License

This repository is licensed under the [MIT License](LICENSE).

Copyright © 2023-present, [Josantonius](https://github.com/josantonius#contact)
