package thebottle.sock.model;

import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;

import static thebottle.sock.Util.of;

/// yes, I am this lazy. I could not be bothered to make a separate model for either [TheBottleRenderer] or [TheBottleItemRenderer]
public class TheBottleGeoModel<T extends GeoAnimatable> extends GeoModel<T> {
    @Override
    public Identifier getModelResource(T t, @Nullable GeoRenderer<T> geoRenderer) {
        return of("geo/the_bottle/the_bottle.geo.json");
    }

    @Override
    public Identifier getTextureResource(T t, @Nullable GeoRenderer<T> geoRenderer) {
        return of("textures/block/the_bottle.png");
    }

    @Override
    public Identifier getAnimationResource(T theBottleEntity) {
        return of("animations/the_bottle/the_bottle.animation.json");
    }
}
