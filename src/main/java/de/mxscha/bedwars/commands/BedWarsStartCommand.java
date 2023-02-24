package de.mxscha.bedwars.commands;

import de.mxscha.bedwars.BedwarsCore;
import de.mxscha.bedwars.utils.game.Game;
import de.mxscha.bedwars.utils.text.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class BedWarsStartCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            if (player.hasPermission("bedwars.start")) {
                 if (args.length == 0) {
                     if (BedwarsCore.getInstance().getLobbyCountdown().isRunning()) {
                         if (BedwarsCore.getInstance().getLobbyCountdown().getSeconds() > 5) {
                             BedwarsCore.getInstance().getLobbyCountdown().setSeconds(5);
                             player.sendMessage(Messages.PREFIX.get() + "§7Du hast das Spiel gestartet!");
                         } else
                             player.sendMessage(Messages.PREFIX.get() + "§cDu kannst das Spiel derzeit nicht starten!");
                     } else
                         player.sendMessage(Messages.PLS_USE.get() + "§e/start confirm");
                 } else if (args.length == 1) {
                     if (args[0].toLowerCase().equals("confirm")) {
                         BedwarsCore.getInstance().getLobbyCountdown().start();
                         BedwarsCore.getInstance().getLobbyCountdown().setSeconds(5);
                         player.sendMessage(Messages.PREFIX.get() + "§7Du hast das Spiel gestartet!");
                     }
                 } else
                     player.sendMessage(Messages.PLS_USE.get() + "§e/start");
            } else
                player.sendMessage(Messages.NO_PERM.get());
        }
        return false;
    }
}
