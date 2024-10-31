package github.tyonakaisan.pixelicon.integration;

import com.google.inject.Inject;
import github.tyonakaisan.pixelicon.PixelIcon;
import github.tyonakaisan.pixelicon.api.Icon;
import github.tyonakaisan.pixelicon.pixel.IconManager;
import io.github.miniplaceholders.api.Expansion;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.identity.Identity;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.logger.slf4j.ComponentLogger;
import net.kyori.adventure.text.minimessage.tag.Tag;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.DefaultQualifier;

@SuppressWarnings("PatternValidation")
@DefaultQualifier(NonNull.class)
public final class MiniPlaceholdersExpansion {

    private final IconManager iconManager;
    private final ComponentLogger logger;

    @Inject
    public MiniPlaceholdersExpansion(
            final IconManager iconManager,
            final ComponentLogger logger
    ) {
        this.iconManager = iconManager;
        this.logger = logger;
    }

    public void registerExpansion() {
        if (PixelIcon.miniPlaceholdersLoaded()) {
            Expansion.builder("pixelicon")
                    .filter(audience -> audience.get(Identity.UUID).isPresent())
                    .audiencePlaceholder("head", (audience, queue, ctx) -> Tag.selfClosingInserting(this.headIcon(audience)))
                    .build()
                    .register();

            this.logger.info("Successfully MiniPlaceholders hook!");
        }
    }

    private Component headIcon(final Audience audience) {
        final var uuid = audience.get(Identity.UUID).orElseThrow();
        final @Nullable Icon icon = this.iconManager.get(Key.key("head_icon", uuid.toString()));
        return icon == null ? Component.empty() : icon.render();
    }
}
