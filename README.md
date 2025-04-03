# Velocity Sign Link

A lightweight Bukkit/Spigot plugin for Velocity that allows players to use signs to connect to other servers.

## Features

- **Save Signs:** Use `/savesign <serverName>` to configure a sign with its target server.
- **Retrieve Sign Data:** Use `/getsign` to read a sign's data.
- **Cooldown Management:** Use `/signcooldown <milliseconds>` to set a global cooldown for sign usage.
- **Database Integration:** Utilizes SQLite to store sign data.
- **Velocity/BungeeCord Integration:** Leverages plugin messaging to send players between servers.

## Installation

1. Download the plugin jar.
2. Place the jar in your server's `plugins` folder.
3. Restart or reload your server.
4. Edit `plugins/VelocitySignLink/config.yml` as needed.

## Commands

- **/savesign `<serverName>`**  
  Saves the target server for the sign you’re looking at.

- **/getsign**  
  Retrieves the sign data.

- **/signcooldown `<milliseconds>`**  
  Sets the global cooldown for sign usage.

## Permissions

- **VeloSign.bypass.cooldown**  
  Allows players to bypass the cooldown. (Default: op)

## Building

To build from source:
1. Clone the repository.
2. Use your preferred build tool (e.g., Maven or Gradle) to compile the project.
3. Place the generated jar file in your server's `plugins` folder.

## Contributing

Contributions are welcome! Feel free to fork the project and submit pull requests. For major changes, please open an issue first to discuss your ideas.

## License

This project is licensed under the GPL-3.0License.

## Support

For issues or feature requests, please open an issue on GitHub.
