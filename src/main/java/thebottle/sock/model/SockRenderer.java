package thebottle.sock.model;

import software.bernie.geckolib.renderer.GeoArmorRenderer;
import thebottle.sock.item.SockItem;

public class SockRenderer extends GeoArmorRenderer<SockItem> {
    public SockRenderer(String id) {
        super(new SockGeoModel(id));
    }
}
