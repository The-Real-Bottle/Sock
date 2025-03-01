package thebottle.sock.model;

import jdk.dynalink.beans.StaticClass;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import software.bernie.geckolib.renderer.GeoItemRenderer;
import thebottle.sock.item.H2OSuitItem;

import static thebottle.sock.Util.of;

public class H2OSuitRenderers {
    public static class H2OSuitArmorRenderer extends GeoArmorRenderer<H2OSuitItem> {
        public H2OSuitArmorRenderer() {
            super(new DefaultedItemGeoModel<>(of("armor/h2o_suit")));
        }
    }
    
    public static class H2OSuitItemRenderer extends GeoItemRenderer<H2OSuitItem> {
        public H2OSuitItemRenderer() {
            super(new DefaultedItemGeoModel<>(of("armor/h2o_suit")));
        }
    }
}
