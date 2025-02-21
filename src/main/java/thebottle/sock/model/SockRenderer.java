package thebottle.sock.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import software.bernie.geckolib.animatable.SingletonGeoAnimatable;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import software.bernie.geckolib.renderer.GeoObjectRenderer;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;
import thebottle.sock.item.SockItem;

public class SockRenderer extends GeoArmorRenderer<SockItem> {
    public SockRenderer(String id) {
        super( new SockGeoModel(id));

        addRenderLayer(new AutoGlowingGeoLayer<>(this));
    }
}
