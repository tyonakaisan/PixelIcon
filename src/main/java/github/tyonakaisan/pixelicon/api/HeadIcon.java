package github.tyonakaisan.pixelicon.api;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.framework.qual.DefaultQualifier;

import java.util.UUID;

/**
 * HeadIcon that can be displayed in-game.
 */
@DefaultQualifier(NonNull.class)
public interface HeadIcon extends Icon {

    /**
     * Create a pixel based on the head image of the specified UUID.
     * @param uuid the uuid
     * @return the pixel an array
     */
    Icon create(final UUID uuid);
}
