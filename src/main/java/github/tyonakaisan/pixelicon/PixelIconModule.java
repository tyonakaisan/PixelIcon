package github.tyonakaisan.pixelicon;

import com.google.inject.AbstractModule;
import org.bukkit.Server;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.framework.qual.DefaultQualifier;

@DefaultQualifier(NonNull.class)
public final class PixelIconModule extends AbstractModule {

    private final PixelIcon pixelIcon;

    PixelIconModule(
            final PixelIcon pixelIcon
    ) {
        this.pixelIcon = pixelIcon;
    }

    @Override
    public void configure() {
        this.bind(Server.class).toInstance(this.pixelIcon.getServer());
    }
}
