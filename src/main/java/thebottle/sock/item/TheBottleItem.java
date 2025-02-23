package thebottle.sock.item;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.SingletonGeoAnimatable;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;
import thebottle.sock.model.TheBottleItemRenderer;

import java.util.function.Consumer;

public class TheBottleItem extends BlockItem implements GeoItem {
    // It spins to increase viewer attention. This is marketing for The Bottle™️ after all
    protected static final RawAnimation IDLE_ANIM = RawAnimation.begin().thenLoop("animation.the_bottle.spin");
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    
    public TheBottleItem(Block block, Settings settings) {
        super(block, settings);
        SingletonGeoAnimatable.registerSyncedAnimatable(this);
    }

    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            private TheBottleItemRenderer renderer;

            @Override
            public TheBottleItemRenderer getGeoItemRenderer() {
                if (this.renderer == null)
                    this.renderer = new TheBottleItemRenderer();

                return this.renderer;
            }
        });
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, this::useIdleAnim));
    }

    private <E extends TheBottleItem> PlayState useIdleAnim(AnimationState<E> state) {
        return state.setAndContinue(IDLE_ANIM);
    }
    
    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}
