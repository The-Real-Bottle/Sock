package thebottle.sock.model;

import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import thebottle.sock.block.TheBottleEntity;

import static thebottle.sock.Util.of;

public class TheBottleGeoModel extends GeoModel<TheBottleEntity> {
    @Override
    public Identifier getModelResource(TheBottleEntity theBottleEntity, @Nullable GeoRenderer<TheBottleEntity> geoRenderer) {
        return of("geo/the_bottle/the_bottle.geo.json");
    }

    @Override
    public Identifier getTextureResource(TheBottleEntity theBottleEntity, @Nullable GeoRenderer<TheBottleEntity> geoRenderer) {
        return of("textures/block/the_bottle.png");
    }

    @Override
    public Identifier getAnimationResource(TheBottleEntity theBottleEntity) {
        return of("animations/the_bottle/the_bottle.animation.json");
    }
}
