package thebottle.sock.item;

import net.minecraft.client.render.entity.equipment.EquipmentModel;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.state.BipedEntityRenderState;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.equipment.ArmorMaterials;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import software.bernie.geckolib.util.GeckoLibUtil;
import thebottle.sock.enchantment.SockEnchantments;
import thebottle.sock.model.H2OSuitRenderer;
import thebottle.sock.sound.SockSounds;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static thebottle.sock.Util.of;

public class H2OSuitItem extends Item implements GeoItem {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public H2OSuitItem(Settings settings) {
        super(processSettings(settings));
    }

    private static Settings processSettings(Settings settings) {
        List<AttributeModifiersComponent.Entry> modifiers = new ArrayList<>(
                ArmorMaterials.DIAMOND.createAttributeModifiers(EquipmentType.CHESTPLATE).modifiers()
        );

        modifiers.add(
                new AttributeModifiersComponent.Entry(
                        EntityAttributes.MOVEMENT_SPEED,
                        new EntityAttributeModifier(
                                of("h2o_suit.movement_speed"),
                                0.33,
                                EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                        ),
                        AttributeModifierSlot.CHEST
                )
        );

        AttributeModifiersComponent component = new AttributeModifiersComponent(
                modifiers,
                true
        );

        ArmorMaterials.DIAMOND.applySettings(settings, EquipmentType.CHESTPLATE);
        settings.attributeModifiers(component);
        return settings;
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (slot != EquipmentSlot.CHEST.getEntitySlotId()) return;

        Map<RegistryKey<Enchantment>, Integer> enchantments = stack.getEnchantments().getEnchantments()
                .stream()
                .map(entry -> Map.entry(entry.getKey(), stack.getEnchantments().getLevel(entry)))
                .filter(entry -> entry.getKey().isPresent())
                .map(entry -> Map.entry(entry.getKey().get(), entry.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        if (world.getRandom().nextDouble() < (float) (1 << enchantments.getOrDefault(SockEnchantments.WATERFULL, 0))) {
            entity.playSound(SockSounds.H2O_ADMINISTERED_EVENT, 1f, 1f);
            if (entity instanceof LivingEntity livingEntity) {
                StatusEffectInstance instance;
                StatusEffectInstance speedOnEntity;
                if ((speedOnEntity = livingEntity.getStatusEffect(StatusEffects.SPEED)) != null) {
                    instance = new StatusEffectInstance(
                            StatusEffects.SPEED,
                            Math.max(120,  (120 + speedOnEntity.getDuration()) / 2),
                            speedOnEntity.getAmplifier() + 3
                    );
                } else {
                    instance = new StatusEffectInstance(StatusEffects.SPEED, 120, 2, false, false, true);
                }
                livingEntity.addStatusEffect(instance);

                for (int i = 0; i < 100; i++) {
                    float g = livingEntity.getYaw();
                    float h = livingEntity.getPitch();
                    float xVel = -MathHelper.sin(g * ((float) Math.PI / 180F)) * MathHelper.cos(h * ((float) Math.PI / 180F));
                    float yVel = -MathHelper.sin(h * ((float) Math.PI / 180F));
                    float zVel = MathHelper.cos(g * ((float) Math.PI / 180F)) * MathHelper.cos(h * ((float) Math.PI / 180F));
                    float m = MathHelper.sqrt(xVel * xVel + yVel * yVel + zVel * zVel);
                    
                    xVel *= (float) (Math.random() * 10 / m);
                    yVel *= 2 / m;
                    zVel *= (float) (Math.random() * 10 / m);

                    var entityVelocity = livingEntity.getVelocity();
                    xVel += (float) entityVelocity.x;
                    yVel += (float) entityVelocity.y;
                    zVel += (float) entityVelocity.z;
                    world.addParticle(ParticleTypes.SPLASH, entity.getX(), entity.getY() + 1, entity.getZ(), xVel, yVel, zVel);
                }
            }
        }
    }

    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            private GeoArmorRenderer<H2OSuitItem> renderer;

            @Override
            public <E extends LivingEntity, S extends BipedEntityRenderState> BipedEntityModel<?> getGeoArmorRenderer(@Nullable E livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, EquipmentModel.LayerType type, BipedEntityModel<S> original) {
                if (this.renderer == null)
                    this.renderer = new H2OSuitRenderer();

                return this.renderer;
            }
        });
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, 0, state -> {
            state.getController().setAnimation(DefaultAnimations.WALK);

            Entity entity = state.getData(DataTickets.ENTITY);
            Vec3d velocity = entity.getVelocity();

            velocity = velocity.subtract(new Vec3d(0, velocity.y, 0));

            return velocity.length() > 0.05 ? PlayState.CONTINUE : PlayState.STOP;
        }));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}
