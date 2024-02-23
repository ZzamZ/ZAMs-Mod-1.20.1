package net.zam.zamaquaticadditions.datagen;

import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.zam.zamaquaticadditions.ZAMAquaticAdditions;

public class ZAMBlockModelProvider extends BlockStateProvider {
    public ZAMBlockModelProvider(PackOutput output, ExistingFileHelper helper) {
        super(output, ZAMAquaticAdditions.MOD_ID, helper);
    }

    @Override
    protected void registerStatesAndModels() {

    }
}
