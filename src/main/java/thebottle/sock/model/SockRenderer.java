package thebottle.sock.model;

import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import thebottle.sock.item.SockItem;

import static thebottle.sock.Util.of;

public class SockRenderer extends GeoArmorRenderer<SockItem> {
    public SockRenderer(String id) {
        super(new DefaultedItemGeoModel<>(of("sock/" + id)));
    }
}
