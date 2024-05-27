package net.zam.zammod.gui;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class CaseRewardScreen extends Screen {
    private final List<ItemStack> rewards;

    public CaseRewardScreen(List<ItemStack> rewards) {
        super(Component.literal("Reward Screen"));
        this.rewards = rewards;
    }

    @Override
    protected void init() {
        int buttonX = this.width / 2 - 50;
        int buttonY = this.height / 2 + 30;

        this.addRenderableWidget(Button.builder(Component.literal("Claim Reward"), button -> {
            this.minecraft.player.getInventory().add(this.rewards.get(0));
            this.minecraft.setScreen(null);
        }).bounds(buttonX, buttonY, 100, 20).build());
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, partialTicks);

        int x = this.width / 2 - (this.rewards.size() * 18) / 2;
        int y = this.height / 2 - 30;

        for (ItemStack stack : this.rewards) {
            guiGraphics.renderItem(stack, x, y);
            guiGraphics.renderItemDecorations(this.font, stack, x, y);
            x += 18;
        }
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
