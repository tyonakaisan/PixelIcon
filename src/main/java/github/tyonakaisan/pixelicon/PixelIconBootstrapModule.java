package github.tyonakaisan.pixelicon;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.multibindings.Multibinder;
import github.tyonakaisan.pixelicon.command.PixelIconCommand;
import github.tyonakaisan.pixelicon.command.commands.PixelIconRootCommand;
import github.tyonakaisan.pixelicon.listener.PixelIconListener;
import io.papermc.paper.plugin.bootstrap.BootstrapContext;
import io.papermc.paper.plugin.configuration.PluginMeta;
import net.kyori.adventure.text.logger.slf4j.ComponentLogger;
import org.bukkit.event.Listener;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.framework.qual.DefaultQualifier;

import java.nio.file.Path;

@SuppressWarnings("UnstableApiUsage")
@DefaultQualifier(NonNull.class)
public final class PixelIconBootstrapModule extends AbstractModule {

    private final BootstrapContext context;

    PixelIconBootstrapModule(
            final BootstrapContext context
    ) {
        this.context = context;
    }

    @Override
    public void configure() {
        this.bind(PluginMeta.class).toInstance(this.context.getPluginMeta());
        this.bind(ComponentLogger.class).toInstance(this.context.getLogger());
        this.bind(Path.class).toInstance(this.context.getDataDirectory());

        this.configureListener();
        this.configureCommand();
    }

    private void configureListener() {
        final Multibinder<Listener> listeners = Multibinder.newSetBinder(this.binder(), Listener.class);
        listeners.addBinding().to(PixelIconListener.class).in(Scopes.SINGLETON);
    }

    private void configureCommand() {
        final Multibinder<PixelIconCommand> commands = Multibinder.newSetBinder(this.binder(), PixelIconCommand.class);
        commands.addBinding().to(PixelIconRootCommand.class).in(Scopes.SINGLETON);
    }
}
