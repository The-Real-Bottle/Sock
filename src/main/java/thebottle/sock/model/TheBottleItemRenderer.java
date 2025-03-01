package thebottle.sock.model;

import software.bernie.geckolib.renderer.GeoItemRenderer;
import thebottle.sock.item.TheBottleItem;

public class TheBottleItemRenderer extends GeoItemRenderer<TheBottleItem> {
    public TheBottleItemRenderer() {
        super(new TheBottleGeoModel<>());
    }
}
