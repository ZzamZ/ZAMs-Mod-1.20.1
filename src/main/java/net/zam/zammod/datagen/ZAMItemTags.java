package net.zam.zammod.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.zam.zammod.ZAMMod;
import net.zam.zammod.integrations.ForgeItemTags;
import net.zam.zammod.registry.ZAMItems;
import net.zam.zammod.registry.ZAMTags;

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

        this.tag(ForgeItemTags.OPAL).add(ZAMItems.OPAL.get());
        this.tag(ForgeItemTags.AQUAMARINE).add(ZAMItems.AQUAMARINE.get());

        this.tag(ZAMTags.TRIMMABLE_TOOLS)
                .add(Items.NETHERITE_SWORD)
                .add(Items.NETHERITE_AXE)
                .add(Items.NETHERITE_PICKAXE)
                .add(Items.NETHERITE_SHOVEL)
                .add(Items.NETHERITE_HOE);
    }

    @Override
    public String getName() {
        return "Item Tags";
    }
}
