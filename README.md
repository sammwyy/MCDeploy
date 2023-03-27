# 🌱 MCDeploy

Download and run minecraft servers with one command.

## ⚙️ Get started

### Create a server

Create a server in the current directory.

```bash
# Command
java -jar mcdeploy.jar install [version] [...options]

# Examples
java -jar mcdeploy.jar install vanilla@latest
java -jar mcdeploy.jar install paper@1.19.3
```

#### Install Options

|     Name    | Short key |           Description          | Default Value |
|:------------|:---------:|:------------------------------:| ------------: |
| --directory |    -d     | Directory where install server | (Current dir) |
| --java      |    -j     |       Java runtime binary      |     java      |

### Run server

Initialize the server in the current directory.

```bash
# Command
java -jar mcdeploy.jar run [...options]

# Examples
java -jar mcdeploy.jar run # Read jar name from ".mcdeploy" file.
java -jar mcdeploy.jar run --file server.jar # With specific jar file.
```

#### Run Options

|     Name    | Short key |           Description          |        Default Value       |
|:------------|:---------:|:------------------------------:| -------------------------: |
| --console   |    -c     |      Enable console output     |             ✔️             |
| --directory |    -d     | Directory where run the server |        (Current dir)       |
| --eula      |    -e     |     Accepts minecraft eula     |             ❌             |
| --file      |    -f     |          Jar file name         | (Read from .mcdeploy file) |
| --java      |    -j     |       Java runtime binary      |            java            |

## Artifacts (Server versions)

Server artifacts api provided by [Sammwy's Minecraft DB](https://github.com/sammwyy/minecraft-db)

- Servers:
  - Vanilla
  - [Bukkit](https://github.com/Bukkit/Bukkit)
  - [Glowstone](https://github.com/GlowstoneMC/Glowstone)
  - [Leaves](https://github.com/LeavesMC/Leaves)
  - [Paper](https://github.com/PaperMC/Paper)
  - [Plazma](https://github.com/PlazmaMC/Plazma)
  - [Pufferfish](https://github.com/pufferfish-gg/Pufferfish)
  - [Purpur](https://github.com/PurpurMC/Purpur)
  - [Reaper](https://github.com/ruViolence/Reaper)
  - [Spigot](https://hub.spigotmc.org/stash/projects/SPIGOT/repos/spigot/browse)
  - [Sponge](https://github.com/SpongePowered/Sponge)
  - [Sportpaper](https://github.com/Electroid/SportPaper)
  - [Tuinity](https://github.com/Tuinity/Tuinity)
- Proxies:
  - [BungeeCord](https://github.com/SpigotMC/BungeeCord)
  - [Velocity](https://github.com/PaperMC/Velocity)
  - [Waterfall](https://github.com/PaperMC/Waterfall)
- Modded:
  - [CatServer](https://github.com/Luohuayu/CatServer)
  - [Crucible](https://github.com/CrucibleMC/Crucible)
  - [Fabric](https://github.com/FabricMC/fabric)
  - [Forge](https://github.com/MinecraftForge/MinecraftForge)
  - [Magma](https://git.magmafoundation.org/magmafoundation/Magma)
  - [Mohist](https://github.com/MohistMC/Mohist)
  - [Quilt](https://github.com/QuiltMC/quilt-loader)
- Bedrock:
  - [Nukkit](https://github.com/CloudburstMC/Nukkit)
  - [Pocketmine](https://github.com/pmmp/PocketMine-MP)
- Misc:
  - [Geyser](https://github.com/GeyserMC/Geyser)
  - [Limbo](https://github.com/LOOHP/Limbo)

## 🤝 Contributing

Contributions, issues and feature requests are welcome!
Feel free to check [issues page](https://github.com/2lstudios-mc/mcdeploy/issues).

## ❤️ Show your support

Give a ⭐️ if this project helped you!

Or buy me a coffeelatte 🙌🏾

[Ko-fi](https://ko-fi.com/sammwy) | [Patreon](https://patreon.com/sammwy)

## 📝 License

Copyright © 2023 [2LStudios](https://github.com/2lstudios-mc).  
This project is [MIT](LICENSE) licensed.  
