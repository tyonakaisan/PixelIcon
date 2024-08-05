package github.tyonakaisan.pixelicon;

import com.google.inject.Singleton;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.DefaultQualifier;

@DefaultQualifier(NonNull.class)
@Singleton
public final class PixelIconProvider {

    private static @Nullable PixelIcon instance;

    private PixelIconProvider() {

    }

    static void register(final PixelIcon instance) {
        PixelIconProvider.instance = instance;
    }

    public static PixelIcon instance() {
        if (instance == null) {
            throw new IllegalStateException("PixelIcon not initialized!");
        }

        return instance;
    }
}