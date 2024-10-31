package github.tyonakaisan.pixelicon.listener;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import github.tyonakaisan.pixelicon.PixelIcon;
import github.tyonakaisan.pixelicon.pixel.IconManager;
import github.tyonakaisan.pixelicon.pixel.head.HeadIconImpl;
import net.kyori.adventure.key.Key;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.framework.qual.DefaultQualifier;

@SuppressWarnings("PatternValidation")
@DefaultQualifier(NonNull.class)
@Singleton
public final class PixelIconListener implements Listener {

    private final PixelIcon pixelIcon;
    private final IconManager iconManager;

    @Inject
    public PixelIconListener(
            final PixelIcon pixelIcon,
            final IconManager iconManager
    ) {
        this.pixelIcon = pixelIcon;
        this.iconManager = iconManager;
    }

    @EventHandler
    public void onEvent(final PlayerJoinEvent event) {
        final var player = event.getPlayer();
        final var uuid = player.getUniqueId();
        final var key = Key.key("head_icon", uuid.toString());
        this.iconManager.register(key, HeadIconImpl.of(uuid));
    }
}
