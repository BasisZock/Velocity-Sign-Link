# Velocity Sign Link

A lightweight Paper/Spigot plugin for Velocity that allows players to use signs to connect to other servers.

## Features

- **Save Signs:** Use `/savesign <serverName>` to configure a sign with its target server.
- **Retrieve Sign Data:** Use `/getsign` to read a sign's data.
- **Delete Sign Data:** Use `/deletesign` to remove the configuration from a sign.
- **Cooldown Management:** Use `/signcooldown <milliseconds>` to set a global cooldown for sign usage.
- **Customizable Cooldown Display:** Option in `config.yml` (`display_below_one_second_as_one`) to show cooldowns less than 1 second as "1 second".
- **Conditional "No Data Found" Message:** Control the visibility of the "No Data found for this sign" message using the `VeloSign.nomessage` permission.
- **Database Integration:** Utilizes SQLite to store sign data.
- **Velocity/BungeeCord Integration:** Leverages plugin messaging to send players between servers.

## Installation

1. Download the plugin jar.
2. Place the jar in your server's `plugins` folder.
3. Restart or reload your server.
4. Edit `plugins/VelocitySignLink/config.yml` as needed.

## Configuration

Located in `plugins/VelocitySignLink/config.yml`:

- **`display_below_one_second_as_one`**: (Default: `true`)
  If set to `true`, any cooldown that is less than 1 second (e.g., 0.2, 0.6 seconds) will be displayed to the player as "1 second". If `false`, it will display as "0 seconds".
- **`cooldown`**: (Default: `1000`)
  The cooldown between the usage of a sign.

## Commands

- **/savesign `<serverName>`**
  Saves the target server for the sign you’re looking at. Requires `VeloSign.command.savesign` or `VeloSign.admin`.

- **/getsign**
  Retrieves the data of the sign you are looking at. Requires `VeloSign.command.getsign` or `VeloSign.admin`.

- **/deletesign**
  Deletes the data of the sign you are looking at. Requires `VeloSign.command.deletesign` or `VeloSign.admin`.

- **/signcooldown `<milliseconds>`**
  Sets the global cooldown for sign usage. Requires `VeloSign.command.signcooldown` or `VeloSign.admin`.

## Permissions

- **`VeloSign.use`**
  Allows players to use the signs to teleport. (Default: all/true)
- **`VeloSign.nomessage`**
  Players with this permission will **not** see the "No Data found for this sign" message when interacting with an unconfigured sign. (Default: all/true - message is hidden by default). Revoke this permission from groups/players if you want them to see the message.
- **`VeloSign.bypass.cooldown`**
  Allows players to bypass the sign usage cooldown. (Default: op)
- **`VeloSign.admin`**
  Grants access to all `VeloSign.command.*` permissions. (Default: op)
- **`VeloSign.command.savesign`**
  Permission to use the `/savesign` command. (Default: op)
- **`VeloSign.command.getsign`**
  Permission to use the `/getsign` command. (Default: op)
- **`VeloSign.command.deletesign`**
  Permission to use the `/deletesign` command. (Default: op)
- **`VeloSign.command.signcooldown`**
  Permission to use the `/signcooldown` command. (Default: op)

## Building

To build from source:
1. Clone the repository.
2. Use your preferred build tool (e.g., Maven or Gradle) to compile the project.
3. Place the generated jar file in your server's `plugins` folder.

## Contributing

Contributions are welcome! Feel free to fork the project and submit pull requests. For suggestions, please open an issue first to discuss your ideas.

## License

This project is licensed under the GPL-3.0 License.

## Support

For issues or feature requests, please open an issue on GitHub.
