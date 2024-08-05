package github.tyonakaisan.pixelicon;

import com.google.inject.*;
import github.tyonakaisan.pixelicon.command.CommandFactory;
import net.kyori.adventure.text.logger.slf4j.ComponentLogger;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.framework.qual.DefaultQualifier;

import java.util.Set;

@DefaultQualifier(NonNull.class)
@Singleton
public final class PixelIcon extends JavaPlugin {

    private final Injector injector;

    @Inject
    public PixelIcon(
            final Injector bootstrapInjector
    ) {
        this.injector = bootstrapInjector.createChildInjector(new PixelIconModule(this));

        PixelIconProvider.register(this);
    }

    @Override
    public void onEnable() {

        final Set<Listener> listeners = this.injector.getInstance(Key.get(new TypeLiteral<>() {}));
        listeners.forEach(listener -> this.getServer().getPluginManager().registerEvents(listener, this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
