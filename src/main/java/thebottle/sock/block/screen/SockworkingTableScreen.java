package thebottle.sock.block.screen;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ButtonTextures;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TexturedButtonWidget;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class SockworkingTableScreen extends HandledScreen<SockworkingTableScreenHandler> {
    public SockworkingTableScreen(SockworkingTableScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {

    }

    @Override
    protected void init() {
        super.init();

        this.addButton(new TexturedButtonWidget(
                50,
                50,
                10,
                10,
                new ButtonTextures(
                        Identifier.of(""),
                        Identifier.of("")
                ),
                (buttonWidget) -> {}
        ));
    }

    private <T extends ButtonWidget> void addButton(T button) {
        this.addDrawableChild(button);
    }


}
