package thebottle.sock.item;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.*;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.SingletonGeoAnimatable;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.util.GeckoLibUtil;
import thebottle.sock.model.TheBottleItemRenderer;

import java.util.List;
import java.util.function.Consumer;

public class TheBottleItem extends BlockItem implements GeoItem, FluidModificationItem {
    // It spins to increase viewer attention. This is marketing for The Bottle™️ after all
    protected static final RawAnimation IDLE_ANIM = RawAnimation.begin().thenLoop("animation.the_bottle.idle");
    protected static final RawAnimation SPIN_ANIM = RawAnimation.begin().thenLoop("animation.the_bottle.spin");

    @Override
    public boolean isPerspectiveAware() {
        return true;
    }

    // The Bottle™ only holds the best fluid known to mankind: water
    protected static final Fluid FLUID = Fluids.WATER;

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public TheBottleItem(Block block, Settings settings) {
        super(block, settings);
        SingletonGeoAnimatable.registerSyncedAnimatable(this);
    }

    public static void registerCauldronHandler() {
        var emptyMap = CauldronBehavior.EMPTY_CAULDRON_BEHAVIOR.map();
        var waterMap = CauldronBehavior.WATER_CAULDRON_BEHAVIOR.map();
        
        CauldronBehavior behaviour = (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.incrementStat(Stats.FILL_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.setBlockState(pos, Blocks.WATER_CAULDRON.getDefaultState().with(LeveledCauldronBlock.LEVEL, 3));
                world.playSound(null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return ActionResult.SUCCESS;
        };
        
        emptyMap.put(SockItems.THE_BOTTLE_ITEM, behaviour);
        waterMap.put(SockItems.THE_BOTTLE_ITEM, behaviour);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("item.sock.the_bottle.tooltip"));
    }

    @Override
    // code taken and modified from net.minecraft.item.BlockItem and net.minecraft.item.BucketItem
    public ActionResult useOnBlock(ItemUsageContext context) {
        var user = context.getPlayer();
        var world = context.getWorld();
        var hand = context.getHand();

        if (user == null) return ActionResult.PASS;

        ItemStack itemStack = user.getStackInHand(hand);
        BlockHitResult blockHitResult = raycast(
                world, user, RaycastContext.FluidHandling.NONE
        );

        if (blockHitResult.getType() == HitResult.Type.MISS || blockHitResult.getType() != HitResult.Type.BLOCK) {
            return ActionResult.PASS;
        } else {
            BlockPos hitBlockPos = blockHitResult.getBlockPos();
            Direction direction = blockHitResult.getSide();
            BlockPos blockPos2 = hitBlockPos.offset(direction);
            if (!world.canPlayerModifyAt(user, hitBlockPos) || !user.canPlaceOn(blockPos2, direction, itemStack)) {
                return ActionResult.FAIL;
            } else {
                BlockState blockPost1State = world.getBlockState(hitBlockPos);
                var isFillable = blockPost1State.getBlock() instanceof FluidFillable;
                BlockPos blockPos = isFillable ? hitBlockPos : blockPos2;

                boolean sneaking = user.isSneaking();

                if (sneaking && !isFillable) {
                    ActionResult actionResult = this.place(new ItemPlacementContext(context));

                    return !actionResult.isAccepted() && context.getStack().contains(DataComponentTypes.CONSUMABLE)
                            ? super.use(world, user, hand)
                            : actionResult;
                }

                if (this.placeFluid(user, world, blockPos, blockHitResult)) {
                    this.onEmptied(user, world, itemStack, blockPos);
                    if (user instanceof ServerPlayerEntity) {
                        Criteria.PLACED_BLOCK.trigger((ServerPlayerEntity) user, blockPos, itemStack);
                    }

                    user.incrementStat(Stats.USED.getOrCreateStat(this));
                    return ActionResult.SUCCESS;
                } else {
                    return ActionResult.FAIL;
                }
            }

        }
    }

    @Override
    public void onItemEntityDestroyed(ItemEntity entity) {
        World world = entity.getWorld();
        List<? extends PlayerEntity> players = world.getPlayers();

        players.forEach(player -> {
            player.sendMessage(Text.translatable("item.sock.the_bottle.taunt", player.getDisplayName()).formatted(Formatting.RED, Formatting.UNDERLINE), true);

            // smite the traitors.
            LightningEntity lightning = new LightningEntity(EntityType.LIGHTNING_BOLT, world);
            lightning.setPos(player.getX(), player.getY(), player.getZ());
            world.spawnEntity(lightning);
        });
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
        controllers.add(new AnimationController<>(this, this::useIdleAnim).setSoundKeyframeHandler(context -> {
        }));
    }

    private <E extends TheBottleItem> PlayState useIdleAnim(AnimationState<E> state) {
        ModelTransformationMode perspective = state.getData(DataTickets.ITEM_RENDER_PERSPECTIVE);
        if (perspective == ModelTransformationMode.THIRD_PERSON_LEFT_HAND || perspective == ModelTransformationMode.THIRD_PERSON_RIGHT_HAND) {
            return state.setAndContinue(IDLE_ANIM);
        }
        return state.setAndContinue(SPIN_ANIM);
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    @Override
    //  Implementation mostly taken from net.minecraft.item.BucketItem
    public boolean placeFluid(@Nullable PlayerEntity player, World world, BlockPos pos, @Nullable BlockHitResult hitResult) {
        if (!(FLUID instanceof FlowableFluid flowableFluid)) {
            return false;
        } else {
            BlockState blockState = world.getBlockState(pos);
            Block block = blockState.getBlock();
            boolean canPlaceFluid = blockState.canBucketPlace(FLUID);
            boolean canFillWithFluid = block instanceof FluidFillable fluidFillable && fluidFillable.canFillWithFluid(player, world, pos, blockState, FLUID);

            // I honestly don't know what to call this variable
            boolean useSpecialBehaviour = blockState.isAir()
                    || canPlaceFluid
                    || canFillWithFluid;

            if (!useSpecialBehaviour) {
                return hitResult != null && this.placeFluid(player, world, hitResult.getBlockPos().offset(hitResult.getSide()), null);
            } else if (world.getDimension().ultrawarm()) {
                // nether handling: water immediately evaporates
                int i = pos.getX();
                int j = pos.getY();
                int k = pos.getZ();
                world.playSound(
                        player, pos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.5F, 2.6F + (world.random.nextFloat() - world.random.nextFloat()) * 0.8F
                );

                for (int l = 0; l < 8; l++) {
                    world.addParticle(ParticleTypes.LARGE_SMOKE, i + Math.random(), j + Math.random(), k + Math.random(), 0.0, 0.0, 0.0);
                }

                return true;
            } else if (block instanceof FluidFillable fluidFillablex) {
                // fluid block handling: fill cauldrons with water and such
                fluidFillablex.tryFillWithFluid(world, pos, blockState, flowableFluid.getStill(false));
                this.playEmptyingSound(player, world, pos);
                return true;
            } else {
                if (!world.isClient && canPlaceFluid && !blockState.isLiquid()) {
                    world.breakBlock(pos, true);
                }

                if (!world.setBlockState(pos, FLUID.getDefaultState().getBlockState(), Block.NOTIFY_ALL_AND_REDRAW) && !blockState.getFluidState().isStill()) {
                    return false;
                } else {
                    this.playEmptyingSound(player, world, pos);
                    return true;
                }
            }
        }
    }

    protected void playEmptyingSound(@Nullable PlayerEntity player, WorldAccess world, BlockPos pos) {
        SoundEvent soundEvent = SoundEvents.ITEM_BUCKET_EMPTY;
        world.playSound(player, pos, soundEvent, SoundCategory.BLOCKS, 1.0F, 1.0F);
        world.emitGameEvent(player, GameEvent.FLUID_PLACE, pos);
    }
}

