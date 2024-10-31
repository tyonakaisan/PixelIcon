package github.tyonakaisan.pixelicon.pixel.head;

import github.tyonakaisan.pixelicon.api.HeadIcon;
import github.tyonakaisan.pixelicon.api.Icon;
import github.tyonakaisan.pixelicon.api.Pixel;
import github.tyonakaisan.pixelicon.pixel.PixelHolder;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.logger.slf4j.ComponentLogger;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.DefaultQualifier;
import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.*;

@DefaultQualifier(NonNull.class)
public final class HeadIconImpl implements HeadIcon {

    private final List<Pixel> pixels = new ArrayList<>();
    private Component icon = Component.empty();

    private HeadIconImpl() {}

    public static Icon of(final UUID uuid) {
        return new HeadIconImpl().create(uuid);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Icon create(final UUID uuid) {
        final var image = Optional.ofNullable(this.head(uuid)).orElseThrow();
        final var width = image.getWidth();
        final var height = image.getHeight();
        var index = 0;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                final var color = TextColor.color(image.getRGB(x, y));
                final var character = (char) ('\uF000' + index % 8);
                this.pixels.add(new PixelHolder(color, character));
                index++;
            }
        }

        this.icon = this.renderer();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Component render() {
        return this.icon;
    }

    /**
     * Get the head image of the specified UUID.
     * <p>Return null if the invalid UUID.
     *
     * @param uuid the uuid
     * @return the buffered image
     */
    private @Nullable BufferedImage head(final @NotNull UUID uuid) {
        try {
            final var url = URI.create("https://crafatar.com/avatars/" + uuid + "?size=8&overlay").toURL();
            return ImageIO.read(url);
        } catch (final Exception e) {
            ComponentLogger.logger().error("An error occurred during image creation.", e);
            return null;
        }
    }

    private Component renderer() {
        final var component = Component.text();
        final int[] specialValues = {7, 15, 23, 31, 39, 47, 55};

        for (int i = 0; i < this.pixels.size(); i++) {
            final var pixel = this.pixels.get(i);
            final var finalI = i;
            final var space = Arrays.stream(specialValues).anyMatch(value -> value == finalI)
                    ? Component.text("\uF00A")
                    : Component.text("\uF00B");

            component.append(pixel.pixel().append(space));
        }

        return component.asComponent().compact();
    }
}
