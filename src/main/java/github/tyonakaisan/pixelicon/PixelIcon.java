package github.tyonakaisan.pixelicon;

import com.google.inject.*;
import github.tyonakaisan.pixelicon.integration.MiniPlaceholdersExpansion;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
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

        Provider.register(this);
    }

    @Override
    public void onEnable() {
        final Set<Listener> listeners = this.injector.getInstance(Key.get(new TypeLiteral<>() {}));
        listeners.forEach(listener -> this.getServer().getPluginManager().registerEvents(listener, this));

        this.injector.getInstance(MiniPlaceholdersExpansion.class).registerExpansion();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static boolean miniPlaceholdersLoaded() {
        return Bukkit.getPluginManager().isPluginEnabled("MiniPlaceholders");
    }

    @DefaultQualifier(NonNull.class)
    @Singleton
    public static final class Provider {

        private static @Nullable PixelIcon instance;

        private Provider() {
        }

        static void register(final PixelIcon instance) {
            Provider.instance = instance;
        }

        public static PixelIcon instance() {
            if (instance == null) {
                throw new IllegalStateException("PixelIcon not initialized!");
            } else {
                return instance;
            }
        }
    }
}
