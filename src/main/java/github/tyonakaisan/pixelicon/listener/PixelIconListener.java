package github.tyonakaisan.pixelicon.listener;

import com.google.inject.Inject;
import github.tyonakaisan.pixelicon.PixelIcon;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Formatter;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.framework.qual.DefaultQualifier;

@DefaultQualifier(NonNull.class)
public final class PixelIconListener implements Listener {

    private final PixelIcon pixelIcon;

    @Inject
    public PixelIconListener (
            final PixelIcon pixelIcon
    ) {
        this.pixelIcon = pixelIcon;
    }

    @EventHandler
    public void onEvent(final PlayerJoinEvent event) {
    }
}
