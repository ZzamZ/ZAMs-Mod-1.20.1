package net.zam.zamaquaticadditions.datagen;

import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.zam.zamaquaticadditions.ZAMAquaticAdditions;

public class ZAMItemModelProvider extends ItemModelProvider {
    public ZAMItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, ZAMAquaticAdditions.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {

    }
}
