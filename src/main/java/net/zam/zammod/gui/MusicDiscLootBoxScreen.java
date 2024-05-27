package net.zam.zammod.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.zam.zammod.ZAMMod;

import java.util.List;
import java.util.Random;

public class MusicDiscLootBoxScreen extends AbstractContainerScreen<MusicDiscLootBoxMenu> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(ZAMMod.MOD_ID, "textures/gui/case.png");
    private boolean spinning;
    private boolean showReward;
    private ItemStack rewardItem;
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
    private Button openButton;
    private Button claimButton;
    private static final ItemStack REQUIRED_KEY_ITEM = new ItemStack(Items.DIAMOND);

    public MusicDiscLootBoxScreen(MusicDiscLootBoxMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.imageWidth = 176;
        this.imageHeight = 166;
        this.spinning = false;
        this.showReward = false;
        this.rewardItem = ItemStack.EMPTY;
        this.spinDuration = 0;
        this.tickCounter = 0;
        this.itemScrollPosition = 0;
        this.speed = 8.0f;
    }

    @Override
    protected void init() {
        super.init();
        int buttonX = this.leftPos + (this.imageWidth / 2) - 50; // Center the button horizontally
        int buttonY = this.topPos + 10; // Adjusted button position

        this.openButton = Button.builder(Component.literal("Open Case"), button -> {
            if (this.menu.consumeKeyItem(this.minecraft.player, REQUIRED_KEY_ITEM)) {
                this.spinning = true;
                this.spinDuration = 400 + new Random().nextInt(100);
                this.tickCounter = 0;
                this.openButton.visible = false;
            } else {
                this.minecraft.player.displayClientMessage(Component.literal("You need a diamond to open the case!"), true);
            }
        }).bounds(buttonX, buttonY, 100, 20).build();

        this.claimButton = Button.builder(Component.literal("Claim Reward"), button -> {
            this.minecraft.player.getInventory().add(this.rewardItem);
            this.minecraft.setScreen(null);
        }).bounds(buttonX, buttonY + 40, 100, 20).build();
        this.claimButton.visible = false;

        this.addRenderableWidget(this.openButton);
        this.addRenderableWidget(this.claimButton);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
        this.renderBackground(guiGraphics);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = this.leftPos;
        int y = this.topPos;
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
            renderScrollingItems(guiGraphics, x + 10, y + 40); // Adjusted item position
        }
    }

    private void renderScrollingItems(GuiGraphics guiGraphics, int x, int y) {
        List<ItemStack> items = this.menu.getDiscItems();
        int centerIndex = items.size() / 2;

        for (int i = -centerIndex; i <= centerIndex; i++) {
            int itemX = x + (i * 18) - ((int) itemScrollPosition % 18);
            if (itemX >= x && itemX < x + this.imageWidth - 20) {
                int color = BOX_COLORS[Math.abs(i % BOX_COLORS.length)];
                guiGraphics.fill(itemX - 1, y - 1, itemX + 17, y + 17, color);
                guiGraphics.renderItem(items.get((centerIndex + i + items.size()) % items.size()), itemX, y);
                guiGraphics.renderItemDecorations(this.font, items.get((centerIndex + i + items.size()) % items.size()), itemX, y);
            }
        }

        guiGraphics.fill(x + (this.imageWidth / 2), y - 1, x + (this.imageWidth / 2) + 1, y + 17, 0xFFFF0000);
    }

    private void selectReward() {
        List<ItemStack> items = this.menu.getDiscItems();
        int selectedIndex = (int) ((itemScrollPosition / 18) % items.size());
        this.rewardItem = items.get(selectedIndex);
        this.showReward = true;
        this.claimButton.visible = true;
        this.minecraft.setScreen(new CaseRewardScreen(List.of(this.rewardItem)));
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        if (spinning) {
            this.renderBackground(guiGraphics);
            RenderSystem.setShaderTexture(0, TEXTURE);
            int x = (this.width - this.imageWidth) / 2;
            int y = (this.height - this.imageHeight) / 2 - 50; // Centered vertically
            guiGraphics.blit(TEXTURE, x, y, 0, 0, this.imageWidth, 35); // Render only the top part
        } else if (showReward) {
            super.render(guiGraphics, mouseX, mouseY, partialTicks);
            this.renderTooltip(guiGraphics, mouseX, mouseY);
            renderReward(guiGraphics);
        } else {
            super.render(guiGraphics, mouseX, mouseY, partialTicks);
            this.renderTooltip(guiGraphics, mouseX, mouseY);
        }
    }

    private void renderReward(GuiGraphics guiGraphics) {
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2 - 50;
        guiGraphics.renderItem(this.rewardItem, x + 80, y + 40);
        guiGraphics.renderItemDecorations(this.font, this.rewardItem, x + 80, y + 40);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
