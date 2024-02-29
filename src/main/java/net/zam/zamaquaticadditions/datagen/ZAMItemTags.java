package net.zam.zamaquaticadditions.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.zam.zamaquaticadditions.ZAMAquaticAdditions;
import net.zam.zamaquaticadditions.registry.ZAMItems;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class ZAMItemTags extends ItemTagsProvider {
    public ZAMItemTags(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> future,
                       CompletableFuture<TagLookup<Block>> completableFuture, @Nullable ExistingFileHelper existingFileHelper) {
        super(packOutput, future, completableFuture, ZAMAquaticAdditions.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {


        this.tag(ItemTags.MUSIC_DISCS)
                .add(ZAMItems.POKEMON_ACCUMULA_TOWN.get())
                .add(ZAMItems.POKEMON_EAST_PROVINCE.get())
                .add(ZAMItems.POKEMON_NIMBASA_CITY.get())
                .add(ZAMItems.POKEMON_DRIFTVEIL_CITY.get())
                .add(ZAMItems.POKEMON_VIRBANK_CITY.get())
                .add(ZAMItems.POKEMON_HUMILAU_CITY.get())
                .add(ZAMItems.POKEMON_GEAR_STATION.get())
                .add(ZAMItems.POKEMON_NS_CASTLE.get())
                .add(ZAMItems.POKEMON_ICIRRUS_CITY.get())
                .add(ZAMItems.POKEMON_VOLO.get())
                .add(ZAMItems.POKEMON_UNOVA_ROAD.get())
                .add(ZAMItems.POKEMON_HOENN_ROAD.get())
                .add(ZAMItems.POKEMON_WATER.get())
                .add(ZAMItems.POKEMON_GROUND.get())
                .add(ZAMItems.POKEMON_LUMIOSE_CITY.get())
                .add(ZAMItems.POKEMON_SKYARROW_BRIDGE.get())
                .add(ZAMItems.POKEMON_STRIATON_CITY.get())
                .add(ZAMItems.POKEMON_CASTELIA_CITY.get())
                .add(ZAMItems.POKEMON_MISTRALTON_CITY.get())
                .add(ZAMItems.POKEMON_MISTRALTON_GYM.get());

    }

    @Override
    public String getName() {
        return "Item Tags";
    }
}