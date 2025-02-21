package thebottle.sock.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import software.bernie.geckolib.animatable.SingletonGeoAnimatable;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoObjectRenderer;

public class SockRenderer extends GeoObjectRenderer<SingletonGeoAnimatable> {
    public SockRenderer(String id) {
        super(new SockGeoModel(id));
    }


}
