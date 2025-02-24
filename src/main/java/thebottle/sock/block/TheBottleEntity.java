package thebottle.sock.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.animation.keyframe.event.builtin.AutoPlayingSoundKeyframeHandler;
import software.bernie.geckolib.util.GeckoLibUtil;

public class TheBottleEntity extends BlockEntity implements GeoBlockEntity {
    protected static final RawAnimation USE_ANIM = RawAnimation.begin().thenPlay("animation.the_bottle.spin");
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public TheBottleEntity(BlockPos pos, BlockState state) {
        super(SockBlocks.THE_BOTTLE_ENTITY, pos, state);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(
                new AnimationController<>(this, 20, state -> PlayState.STOP)
                        .triggerableAnim("use_anim", USE_ANIM)
                        .setSoundKeyframeHandler(new AutoPlayingSoundKeyframeHandler<>())
        );
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}
