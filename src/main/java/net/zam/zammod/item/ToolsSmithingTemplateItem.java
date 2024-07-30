package net.zam.zammod.item;

import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SmithingTemplateItem;
import net.zam.zammod.ZAMMod;

import java.util.List;

public class ToolsSmithingTemplateItem extends SmithingTemplateItem {
    private static final ChatFormatting TITLE_FORMAT = ChatFormatting.GRAY;
    private static final ChatFormatting DESCRIPTION_FORMAT = ChatFormatting.BLUE;
    private static final Component TOOL_UPGRADE = Component.translatable(Util.makeDescriptionId("upgrade", ZAMMod.id("tool_upgrade"))).withStyle(TITLE_FORMAT);
    private static final Component TOOL_UPGRADE_APPLIES_TO = Component.translatable(Util.makeDescriptionId("item", ZAMMod.id("smithing_template.tool_upgrade.applies_to"))).withStyle(DESCRIPTION_FORMAT);
    private static final Component TOOL_UPGRADE_INGREDIENTS = Component.translatable(Util.makeDescriptionId("item", ZAMMod.id("smithing_template.tool_upgrade.ingredients"))).withStyle(DESCRIPTION_FORMAT);
    private static final Component TOOL_UPGRADE_BASE_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", ZAMMod.id("smithing_template.tool_upgrade.base_slot_description")));
    private static final Component TOOL_UPGRADE_ADDITIONS_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", ZAMMod.id("smithing_template.tool_upgrade.additions_slot_description")));
    private static final ResourceLocation EMPTY_SLOT_HOE = new ResourceLocation("item/empty_slot_hoe");
    private static final ResourceLocation EMPTY_SLOT_AXE = new ResourceLocation("item/empty_slot_axe");
    private static final ResourceLocation EMPTY_SLOT_SWORD = new ResourceLocation("item/empty_slot_sword");
    private static final ResourceLocation EMPTY_SLOT_SHOVEL = new ResourceLocation("item/empty_slot_shovel");
    private static final ResourceLocation EMPTY_SLOT_PICKAXE = new ResourceLocation("item/empty_slot_pickaxe");
    private static final ResourceLocation EMPTY_SLOT_INGOT = new ResourceLocation("item/empty_slot_ingot");


    public ToolsSmithingTemplateItem() {
        super(TOOL_UPGRADE_APPLIES_TO, TOOL_UPGRADE_INGREDIENTS, TOOL_UPGRADE, TOOL_UPGRADE_BASE_SLOT_DESCRIPTION, TOOL_UPGRADE_ADDITIONS_SLOT_DESCRIPTION, createNetheriteUpgradeIconList(), createNetheriteUpgradeMaterialList());
    }

    private static List<ResourceLocation> createNetheriteUpgradeIconList() {
        return List.of(EMPTY_SLOT_SWORD, EMPTY_SLOT_PICKAXE, EMPTY_SLOT_AXE, EMPTY_SLOT_HOE, EMPTY_SLOT_SHOVEL);
    }

    private static List<ResourceLocation> createNetheriteUpgradeMaterialList() {
        return List.of(EMPTY_SLOT_INGOT);
    }
}
