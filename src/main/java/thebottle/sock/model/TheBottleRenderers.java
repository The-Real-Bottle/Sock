package thebottle.sock.model;

import software.bernie.geckolib.model.DefaultedBlockGeoModel;
import software.bernie.geckolib.model.DefaultedGeoModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;
import software.bernie.geckolib.renderer.GeoItemRenderer;
import thebottle.sock.block.TheBottleEntity;
import thebottle.sock.item.TheBottleItem;

import static thebottle.sock.Util.of;

public class TheBottleRenderers  {
    public static class TheBottleBlockRenderer extends GeoBlockRenderer<TheBottleEntity> {
        public TheBottleBlockRenderer() {
            super(new DefaultedBlockGeoModel<>(of("the_bottle")));
        }
    }

    public static class TheBottleItemRenderer extends GeoItemRenderer<TheBottleItem> {
        public TheBottleItemRenderer() {
            super(new DefaultedGeoModel<>(of("the_bottle")) {
                @Override
                protected String subtype() {
                    // reduce asset duplication
                    return "block";
                }
            });
        }
    }
}
