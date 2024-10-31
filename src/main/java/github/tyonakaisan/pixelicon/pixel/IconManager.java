package github.tyonakaisan.pixelicon.pixel;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import github.tyonakaisan.pixelicon.api.Icon;
import net.kyori.adventure.key.Key;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.DefaultQualifier;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@DefaultQualifier(NonNull.class)
@Singleton
public final class IconManager {

    private final Map<Key, Icon> registry = new ConcurrentHashMap<>();

    @Inject
    public IconManager() {
    }

    public void register(final Key key, final Icon icon) {
        if (this.registry.containsKey(key)) {
            return;
        }
        this.registry.put(key, icon);
    }

    public @Nullable Icon get(final Key key) {
        return this.registry.get(key);
    }
}
