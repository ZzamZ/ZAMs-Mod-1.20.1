package net.zam.zamaquaticadditions.block.chest.renderer.tileentity;

import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.ChestRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.zam.zamaquaticadditions.ZAMAquaticAdditions;
import net.zam.zamaquaticadditions.block.chest.LostBountyBlockEntity;

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
        return new Material(Sheets.CHEST_SHEET, new ResourceLocation(ZAMAquaticAdditions.MOD_ID, "entity/tileentity/lost_bounty"));
    }
}