package net.zam.zammod.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.zam.zammod.ZAMMod;
import net.zam.zammod.registry.ZAMItems;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class ZAMItemTags extends ItemTagsProvider {
    public ZAMItemTags(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> future,
                       CompletableFuture<TagLookup<Block>> completableFuture, @Nullable ExistingFileHelper existingFileHelper) {
        super(packOutput, future, completableFuture, ZAMMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(ItemTags.MUSIC_DISCS);
             //  .add(ZAMItems.POKEMON_STRIATON_CITY.get())
             //  .add(ZAMItems.POKEMON_CASTELIA_CITY.get())
             //  .add(ZAMItems.POKEMON_NIMBASA_CITY.get())
             //  .add(ZAMItems.POKEMON_DRIFTVEIL_CITY.get())
             //  .add(ZAMItems.POKEMON_MISTRALTON_CITY.get())
             //  .add(ZAMItems.POKEMON_ICIRRUS_CITY.get())
             //  .add(ZAMItems.POKEMON_VIRBANK_CITY.get())
             //  .add(ZAMItems.POKEMON_HUMILAU_CITY.get())
             //  .add(ZAMItems.POKEMON_GEAR_STATION.get())
             //  .add(ZAMItems.POKEMON_EAST_PROVINCE.get())
             //  .add(ZAMItems.POKEMON_NS_CASTLE.get())
             //  .add(ZAMItems.POKEMON_VOLO.get());



    }

    @Override
    public String getName() {
        return "Item Tags";
    }
}
