package github.tyonakaisan.pixelicon.api;

import net.kyori.adventure.text.Component;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.framework.qual.DefaultQualifier;

/**
 * Icon indicates that the Pixel can be converted into a Component that can be displayed in the game.
 */
@DefaultQualifier(NonNull.class)
public interface Icon {

    /**
     * Renders an icon.
     *
     * @return the rendered component
     */
    Component render();
}
