package thebottle.sock.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

public class TheBottleEntity extends BlockEntity implements GeoBlockEntity {
    protected static final RawAnimation USE_ANIM = RawAnimation.begin().thenPlay("animation.the_bottle.spin");

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public TheBottleEntity(BlockPos pos, BlockState state) {
        super(SockBlocks.THE_BOTTLE_ENTITY, pos, state);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, this::useAnimController));
    }

    private <E extends TheBottleEntity> PlayState useAnimController(AnimationState<E> state) {
        return state.setAndContinue(USE_ANIM);
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}
