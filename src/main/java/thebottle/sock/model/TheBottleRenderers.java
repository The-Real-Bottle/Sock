package thebottle.sock.model;

import software.bernie.geckolib.model.DefaultedBlockGeoModel;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
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
            super(new DefaultedItemGeoModel<>(of("the_bottle")));
        }
    }
}
