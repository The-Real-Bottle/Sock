package thebottle.sock.model;

import software.bernie.geckolib.renderer.GeoBlockRenderer;
import thebottle.sock.block.TheBottleEntity;

public class TheBottleRenderer extends GeoBlockRenderer<TheBottleEntity> {
    public TheBottleRenderer() {
        super(new TheBottleGeoModel<>());
    }
}
