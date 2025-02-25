package thebottle.sock.item;

import com.google.common.collect.Multimap;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import dev.emi.trinkets.api.client.TrinketRenderer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.equipment.EquipmentModel;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.state.BipedEntityRenderState;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.util.GeckoLibUtil;
import thebottle.sock.enchantment.SockEnchantments;
import thebottle.sock.model.SockRenderer;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static thebottle.sock.Util.of;

public final class SockItem extends TrinketItem implements GeoItem, TrinketRenderer {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private float partialTick = 0;
    private final String sockId;
    private final List<Pair<RegistryEntry<EntityAttribute>, EntityAttributeModifier>> extraModifiers;

    public SockItem(Settings settings, String sockId, List<Pair<RegistryEntry<EntityAttribute>, EntityAttributeModifier>> extraModifiers) {
        super(settings.maxCount(1));
        this.sockId = sockId;
        this.extraModifiers = extraModifiers;
    }

    public SockItem(Settings settings, SockData sockData) {
        this(
                settings,
                sockData.sockId,
                sockData.extraModifiers
        );
    }

    @Override
    public void onUnequip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if (entity instanceof PlayerEntity player) {
            player.sendMessage(Text.of("How dare you take of your socks!"), false);
        }
    }

    @Override
    public Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, Identifier slotIdentifier) {
        var modifiers = super.getModifiers(stack, slot, entity, slotIdentifier);

        final Map<RegistryKey<Enchantment>, Integer> enchantmentLevels = stack.getEnchantments().getEnchantments()
                .stream()
                .map(entry -> new Pair<>(entry, stack.getEnchantments().getLevel(entry)))
                .map(pair -> new Pair<>(pair.getLeft().getKey(), pair.getRight()))
                .map(pair -> new Pair<>(pair.getLeft().orElseThrow(), pair.getRight()))
                .collect(Collectors.toMap(Pair::getLeft, Pair::getRight));

        modifiers.put(
                EntityAttributes.WATER_MOVEMENT_EFFICIENCY,
                new EntityAttributeModifier(
                        of("sock.water_speed"),
                        -0.5 + 0.1*enchantmentLevels.getOrDefault(SockEnchantments.WATERPROOF, 0),
                        EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                )
        );

        modifiers.put(
                EntityAttributes.SAFE_FALL_DISTANCE,
                new EntityAttributeModifier(
                        of("sock.safe_fall_distance"),
                        0.25,
                        EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                )
        );

        modifiers.put(
                EntityAttributes.MOVEMENT_SPEED,
                new EntityAttributeModifier(
                        of("sock.movement_speed"),
                        0.1*enchantmentLevels.getOrDefault(SockEnchantments.SPEEDY, 0),
                        EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                )
        );

        modifiers.put(
                EntityAttributes.STEP_HEIGHT,
                new EntityAttributeModifier(
                        of("sock.step_height"),
                        0.5*enchantmentLevels.getOrDefault(SockEnchantments.GREATER_STEPPING, 0),
                        EntityAttributeModifier.Operation.ADD_VALUE
                )
        );

        extraModifiers.forEach(pair -> modifiers.put(pair.getLeft(), pair.getRight()));

        return modifiers;
    }

    // Create our armor model/renderer and return it
    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            private SockRenderer renderer;

            @Override
            public <E extends LivingEntity, S extends BipedEntityRenderState> BipedEntityModel<?> getGeoArmorRenderer(@Nullable E livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, EquipmentModel.LayerType type, BipedEntityModel<S> original) {
                if (this.renderer == null)
                    this.renderer = new SockRenderer(sockId);
                // Defer creation of our renderer then cache it so that it doesn't get instantiated too early

                return this.renderer;
            }
        });
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, 20, state -> {
            // Apply our generic idle animation.
            // Whether it plays or not is decided down below.
            state.getController().setAnimation(DefaultAnimations.IDLE);
            this.partialTick = state.getPartialTick();

            return PlayState.CONTINUE;
        }));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    @Override
    public void render(ItemStack stack, SlotReference slotReference, EntityModel<? extends LivingEntityRenderState> contextModel, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, LivingEntityRenderState state, float limbAngle, float limbDistance) {
        if (contextModel instanceof BipedEntityModel<?> bipedEntityModel && state instanceof BipedEntityRenderState bipedEntityRenderState) {
            SockRenderer renderer = new SockRenderer(sockId);
            matrices.push();
            TrinketRenderer.translateToLeftLeg(matrices, bipedEntityModel, bipedEntityRenderState);
            matrices.translate(new Vec3d(0, -1.5, 0));
            renderer.prepForRender(
                    MinecraftClient.getInstance().player,
                    stack,
                    EquipmentSlot.FEET,
                    bipedEntityModel,
                    vertexConsumers,
                    partialTick,
                    bipedEntityRenderState.yawDegrees,
                    bipedEntityRenderState.pitch
            );
            renderer.defaultRender(
                    matrices,
                    this,
                    vertexConsumers,
                    null,
                    null,
                    partialTick,
                    light
            );
            matrices.pop();

            matrices.push();
            TrinketRenderer.translateToRightLeg(matrices, bipedEntityModel, bipedEntityRenderState);
            matrices.translate(new Vec3d(0, -1.5, 0));
            matrices.scale(-1, 1, 1);
            renderer.prepForRender(
                    MinecraftClient.getInstance().player,
                    stack,
                    EquipmentSlot.FEET,
                    bipedEntityModel,
                    vertexConsumers,
                    partialTick,
                    bipedEntityRenderState.yawDegrees,
                    bipedEntityRenderState.pitch
            );
            renderer.defaultRender(
                    matrices,
                    this,
                    vertexConsumers,
                    null,
                    null,
                    partialTick,
                    light
            );
            matrices.pop();
        }
    }

    public record SockData(String sockId, List<Pair<RegistryEntry<EntityAttribute>, EntityAttributeModifier>> extraModifiers) {}
}

