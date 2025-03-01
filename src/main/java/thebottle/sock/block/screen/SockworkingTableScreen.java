package thebottle.sock.block.screen;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import thebottle.sock.block.networking.SockC2SPacketSender;

import static thebottle.sock.Util.of;

@Environment(EnvType.CLIENT)
public class SockworkingTableScreen extends HandledScreen<SockworkingTableScreenHandler> {
    public static final Identifier TEXTURE = of("textures/gui/container/sockworking_table.png");

    public SockworkingTableScreen(SockworkingTableScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        if (this.client == null) throw new AssertionError("Expected render to only be called client side.");
        float tickDelta = this.client.getRenderTickCounter().getTickDelta(false);
        super.render(context, mouseX, mouseY, tickDelta);
        this.drawMouseoverTooltip(context, mouseX, mouseY);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        int x = (this.width - this.backgroundWidth) / 2;
        int y = (this.height - this.backgroundHeight) / 2;
        context.drawTexture(RenderLayer::getGuiTextured, TEXTURE, x, y, 0f, 0f, this.backgroundWidth, this.backgroundHeight, 256, 256);
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    protected void onMouseClick(Slot slot, int slotId, int button, SlotActionType actionType) {
        super.onMouseClick(slot, slotId, button, actionType);

        if (slotId == 2) {
            SockC2SPacketSender.sendSockCraftPacket();
        }
    }
}
