package net.zam.zammod.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.zam.zammod.ZAMMod;

import java.util.List;
import java.util.Random;

public class MusicDiscSpinScreen extends AbstractContainerScreen<MusicDiscLootBoxMenu> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(ZAMMod.MOD_ID, "textures/gui/case.png");
    private boolean spinning;
    private int spinDuration;
    private int tickCounter;
    private float itemScrollPosition;
    private float speed;
    private static final int[] BOX_COLORS = {
            0x80FF0000, // Semi-transparent red
            0x8000FF00, // Semi-transparent green
            0x800000FF, // Semi-transparent blue
            0x80FFFF00, // Semi-transparent yellow
            0x80FF00FF, // Semi-transparent magenta
            0x8000FFFF  // Semi-transparent cyan
    };

    public MusicDiscSpinScreen(MusicDiscLootBoxMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.imageWidth = 176;
        this.imageHeight = 166;
        this.spinning = true;
        this.spinDuration = 400 + new Random().nextInt(100);
        this.tickCounter = 0;
        this.itemScrollPosition = 0;
        this.speed = 8.0f; // Initial speed
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
        this.renderBackground(guiGraphics);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;
        guiGraphics.blit(TEXTURE, x, y, 0, 0, this.imageWidth, this.imageHeight);

        if (spinning) {
            tickCounter++;
            if (tickCounter >= 40) {
                speed = Math.max(0.1f, speed * 0.97f);
            }
            itemScrollPosition += speed;
            if (tickCounter >= spinDuration) {
                spinning = false;
                tickCounter = 0;
                itemScrollPosition = 0;
                speed = 8.0f;
                selectReward();
            }
        }

        if (spinning) {
            renderScrollingItems(guiGraphics, x + 8, y + 58); // Adjusted item position
        }
    }

    private void renderScrollingItems(GuiGraphics guiGraphics, int x, int y) {
        List<ItemStack> items = this.menu.getDiscItems();
        int centerIndex = items.size() / 2;

        for (int i = -centerIndex; i <= centerIndex; i++) {
            int itemX = x + (i * 18) - ((int) itemScrollPosition % 18);
            if (itemX >= x && itemX < x + this.imageWidth) {
                int color = BOX_COLORS[Math.abs(i % BOX_COLORS.length)];
                guiGraphics.fill(itemX - 1, y - 1, itemX + 17, y + 17, color);
                guiGraphics.renderItem(items.get((centerIndex + i + items.size()) % items.size()), itemX, y);
                guiGraphics.renderItemDecorations(this.font, items.get((centerIndex + i + items.size()) % items.size()), itemX, y);
            }
        }

        guiGraphics.fill(x + this.imageWidth / 2, y - 1, x + this.imageWidth / 2 + 1, y + 17, 0xFFFF0000);
    }

    private void selectReward() {
        List<ItemStack> items = this.menu.getDiscItems();
        int selectedIndex = (int) ((itemScrollPosition / 18) % items.size());
        ItemStack rewardItem = items.get(selectedIndex);
        this.minecraft.setScreen(new CaseRewardScreen(List.of(rewardItem)));
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(guiGraphics);
        if (spinning) {
            RenderSystem.setShaderTexture(0, TEXTURE);
            int x = (this.width - this.imageWidth) / 2;
            int y = (this.height - this.imageHeight) / 2;
            guiGraphics.blit(TEXTURE, x, y, 0, 0, this.imageWidth, this.imageHeight);
            renderScrollingItems(guiGraphics, x + 8, y + 58);
        } else {
            super.render(guiGraphics, mouseX, mouseY, partialTicks);
        }
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        // Override this method to prevent titles from rendering
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
