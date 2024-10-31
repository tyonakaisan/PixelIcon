package github.tyonakaisan.pixelicon.command.commands;

import com.google.inject.Inject;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import github.tyonakaisan.pixelicon.api.Icon;
import github.tyonakaisan.pixelicon.command.RootCommand;
import github.tyonakaisan.pixelicon.pixel.IconManager;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.argument.ArgumentTypes;
import io.papermc.paper.command.brigadier.argument.resolvers.selector.PlayerSelectorArgumentResolver;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.DefaultQualifier;
import org.intellij.lang.annotations.Subst;

import static io.papermc.paper.command.brigadier.Commands.argument;
import static io.papermc.paper.command.brigadier.Commands.literal;

@SuppressWarnings("UnstableApiUsage")
@DefaultQualifier(NonNull.class)
public final class PreviewCommand implements RootCommand {

    private final IconManager iconManager;

    @Inject
    public PreviewCommand(
            final IconManager iconManager
    ) {
        this.iconManager = iconManager;
    }

    @Override
    public ArgumentBuilder<CommandSourceStack, ?> root() {
        return literal("preview")
                .requires(source -> source.getSender().hasPermission("pixelicon.command.preview"))
                .then(literal("head")
                        .executes(this::headCommand)
                        .then(argument("player", ArgumentTypes.player())
                                .executes(this::headCommand)))
                .then(literal("custom")
                        .executes(context -> {
                            context.getSource().getSender().sendMessage(MiniMessage.miniMessage().deserialize("<red>This feature is not implemented."));
                            return Command.SINGLE_SUCCESS;
                        }));
    }

    private int headCommand(final CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        if (context.getSource().getSender() instanceof Player player) {
            final var preview = context.getNodes().size() >= 3
                    ? context.getArgument("player", PlayerSelectorArgumentResolver.class).resolve(context.getSource()).get(0)
                    : player;
            final @Subst("uuid") var uuid = preview.getUniqueId().toString();
            final @Nullable Icon icon = this.iconManager.get(Key.key("head_icon", uuid));
            final var head = icon != null
                    ? icon.render()
                    : MiniMessage.miniMessage().deserialize("<red>Icon not found.");

            player.sendMessage(head);
        } else {
            context.getSource().getSender().sendMessage(MiniMessage.miniMessage().deserialize("<red>Only the player can execute this command."));
        }
        return Command.SINGLE_SUCCESS;
    }
}
