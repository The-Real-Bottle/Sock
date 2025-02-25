package thebottle.sock.model;

import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import thebottle.sock.item.H2OSuitItem;

import static thebottle.sock.Util.of;

public class H2OSuitRenderer extends GeoArmorRenderer<H2OSuitItem> {
    public H2OSuitRenderer() {
        super(new DefaultedItemGeoModel<>(of("armor/h2o_suit")));
    }
}
