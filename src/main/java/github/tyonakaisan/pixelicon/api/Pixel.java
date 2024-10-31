package github.tyonakaisan.pixelicon.api;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.framework.qual.DefaultQualifier;

/**
 * A Pixel is an object that stores a single character and color.
 */
@DefaultQualifier(NonNull.class)
public interface Pixel {

    /**
     * Get the color of this pixel.
     * @return the color
     */
    TextColor color();

    /**
     * Get the character.
     * @return the character
     */
    char character();

    /**
     * Renders a pixel.
     * @return the rendered component
     */
    Component pixel();
}
