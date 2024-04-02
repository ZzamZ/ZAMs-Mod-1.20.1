package net.zam.zammod.block.chest.renderer.tileentity;

import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.ChestRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.zam.zammod.ZAMMod;
import net.zam.zammod.block.chest.LostBountyBlockEntity;

import javax.annotation.Nonnull;

public class LostBountyRenderer extends ChestRenderer<LostBountyBlockEntity> {
    public static LostBountyRenderer instance;

    public LostBountyRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
        instance = this;
    }

    @Override
    @Nonnull
    protected Material getMaterial(@Nonnull LostBountyBlockEntity tileEntity, @Nonnull ChestType chestType) {
        return new Material(Sheets.CHEST_SHEET, new ResourceLocation(ZAMMod.MOD_ID, "entity/tileentity/lost_bounty"));
    }
}