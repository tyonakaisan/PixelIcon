package github.tyonakaisan.pixelicon.pixel;

import github.tyonakaisan.pixelicon.api.Pixel;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.framework.qual.DefaultQualifier;


/**
 * PixelHolder implementation to hold pixel data.
 */
@DefaultQualifier(NonNull.class)
public record PixelHolder(
        TextColor color,
        char character
) implements Pixel {

    /**
     * {@inheritDoc}
     */
    @Override
    public Component pixel() {
        return Component.text(this.character)
                .color(this.color);
    }
}
