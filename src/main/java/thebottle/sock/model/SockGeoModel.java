package thebottle.sock.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.SingletonGeoAnimatable;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import static thebottle.sock.Util.of;

@Environment(EnvType.CLIENT)
public class SockGeoModel extends GeoModel<SingletonGeoAnimatable> {
    private final String id;

    public SockGeoModel(String id) {
        this.id = id;
    }

    @Override
    public Identifier getModelResource(SingletonGeoAnimatable animatable, @Nullable GeoRenderer<SingletonGeoAnimatable> renderer) {
        return of("geo/sock/" + id + ".geo.json");
    }

    @Override
    public Identifier getTextureResource(SingletonGeoAnimatable animatable, @Nullable GeoRenderer<SingletonGeoAnimatable> renderer) {
        return of("textures/sock/" + id + ".png");
    }

    @Override
    public Identifier getAnimationResource(SingletonGeoAnimatable animatable) {
        return of("animations/sock/" + id + ".animation.json");
    }
}
