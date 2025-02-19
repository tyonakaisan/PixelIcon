package github.tyonakaisan.pixelicon.command.commands;

import com.google.inject.Inject;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import github.tyonakaisan.pixelicon.command.PixelIconCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.plugin.configuration.PluginMeta;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.framework.qual.DefaultQualifier;

import java.util.List;

import static io.papermc.paper.command.brigadier.Commands.literal;

@SuppressWarnings("UnstableApiUsage")
@DefaultQualifier(NonNull.class)
public final class PixelIconRootCommand implements PixelIconCommand {

    private final PluginMeta pluginMeta;
    private final ReloadCommand reloadCommand;
    private final PreviewCommand previewCommand;

    @Inject
    public PixelIconRootCommand(
            final PluginMeta pluginMeta,
            final ReloadCommand reloadCommand,
            final PreviewCommand previewCommand
    ) {
        this.pluginMeta = pluginMeta;
        this.reloadCommand = reloadCommand;
        this.previewCommand = previewCommand;
    }

    @Override
    public LiteralArgumentBuilder<CommandSourceStack> init() {
        return literal(this.pluginMeta.getName().toLowerCase())
                .then(this.reloadCommand.root())
                .then(this.previewCommand.root());
    }

    @Override
    public List<String> aliases() {
        return List.of("pixel", "icon", "pi");
    }

    @Override
    public String description() {
        return "A PixelIcon provided command.";
    }
}
