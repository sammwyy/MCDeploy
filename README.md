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
| --file      |    -f     |          Jar file name         | (Read from .mcdeploy file) |
| --java      |    -j     |       Java runtime binary      |            java            |

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
