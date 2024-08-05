package net.zam.zammod.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.RecordItem;
import net.zam.zammod.ZAMMod;
import net.zam.zammod.util.RarityItem;
import net.zam.zammod.util.network.NetworkHandler;
import net.zam.zammod.util.network.packet.ClaimRewardPacket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class MusicDiscLootBoxRewardScreen extends Screen {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final ResourceLocation TEXTURE = new ResourceLocation(ZAMMod.MOD_ID, "textures/gui/case.png");
    private final List<RarityItem> rewardItems;
    private Button claimButton;
    private final Player player;
    private final Component title;
    private final int titleColor;
    private final Component caseTitle;
    private boolean rewardClaimed = false;

    public MusicDiscLootBoxRewardScreen(List<RarityItem> rewardItems, Player player, Component caseTitle) {
        super(Component.literal("Reward"));
        this.rewardItems = rewardItems;
        this.player = player;
        RarityItem rewardItem = rewardItems.get(0);
        this.title = determineTitle(rewardItem);
        this.titleColor = rewardItem.getRarity().getColor();
        this.caseTitle = caseTitle;
    }

    private Component determineTitle(RarityItem rarityItem) {
        ItemStack itemStack = rarityItem.getItemStack();
        if (itemStack.getItem() instanceof RecordItem) {
            return Component.translatable(itemStack.getDescriptionId() + ".desc");
        } else {
            return itemStack.getHoverName();
        }
    }

    @Override
    protected void init() {
        super.init();
        int screenWidth = this.width;
        int screenHeight = this.height;
        int x = (screenWidth - 176) / 2;
        int y = (screenHeight - 70) / 2;
        int buttonX = x + 38;
        int buttonY = y + 35;

        this.claimButton = Button.builder(Component.literal("Claim"), button -> {
            sendClaimRewardPacket();
            announceReward();
            this.rewardClaimed = true;
            this.onClose();
        }).bounds(buttonX, buttonY, 100, 20).build();

        this.addRenderableWidget(this.claimButton);
    }

    private void sendClaimRewardPacket() {
        RarityItem selectedItem = rewardItems.get(0);
        LOGGER.info("Sending claim reward packet to server for item: " + selectedItem.getItemStack());
        NetworkHandler.CHANNEL.sendToServer(new ClaimRewardPacket(selectedItem));
    }

    private void announceReward() {
        RarityItem selectedItem = rewardItems.get(0);
        ItemStack selectedItemStack = selectedItem.getItemStack();
        Component itemNameOrDescription = selectedItemStack.getItem() instanceof RecordItem ?
                Component.translatable(selectedItemStack.getDescriptionId() + ".desc") :
                selectedItemStack.getHoverName();

        Component playerNameComponent = player.getName().copy().withStyle(style -> style.withColor(0x55FF55));
        Component itemNameOrDescriptionComponent = itemNameOrDescription.copy().withStyle(style -> style.withColor(selectedItem.getRarity().getColor()));
        Component caseNameComponent = caseTitle.copy().withStyle(style -> style.withColor(0xADD8E6));
        Component message = Component.literal("")
                .append(playerNameComponent)
                .append(Component.literal(" opened a ").withStyle(style -> style.withColor(0xADD8E6)))
                .append(Component.literal("[").withStyle(style -> style.withColor(0xADD8E6)))
                .append(caseNameComponent)
                .append(Component.literal("] and received ").withStyle(style -> style.withColor(0xADD8E6)))
                .append(Component.literal("[").withStyle(style -> style.withColor(selectedItem.getRarity().getColor())))
                .append(itemNameOrDescriptionComponent)
                .append(Component.literal("]").withStyle(style -> style.withColor(selectedItem.getRarity().getColor())));

        player.sendSystemMessage(message);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(guiGraphics);
        RenderSystem.setShaderTexture(0, TEXTURE);

        int screenWidth = this.width;
        int screenHeight = this.height;
        int x = (screenWidth - 176) / 2;
        int y = (screenHeight - 70) / 2;
        guiGraphics.blit(TEXTURE, x, y, 0, 0, 176, 61);
        drawCenteredString(guiGraphics, this.font, this.title.getString(), this.width / 2, y + 8, titleColor);

        for (int i = 0; i < rewardItems.size(); i++) {
            ItemStack itemStack = rewardItems.get(i).getItemStack();
            int itemX = (screenWidth - 16) / 2;
            int itemY = y + 17 + (i * 18);

            guiGraphics.renderItem(itemStack, itemX, itemY);
            guiGraphics.renderItemDecorations(this.font, itemStack, itemX, itemY);
        }

        super.render(guiGraphics, mouseX, mouseY, partialTicks);
    }

    private void drawCenteredString(GuiGraphics guiGraphics, Font font, String text, int centerX, int y, int color) {
        int width = font.width(text);
        guiGraphics.drawString(font, text, centerX - width / 2, y, color, false);
    }

    @Override
    public void onClose() {
        if (!rewardClaimed) {
            return; // Prevent closing the screen unless reward is claimed
        }
        super.onClose();
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
