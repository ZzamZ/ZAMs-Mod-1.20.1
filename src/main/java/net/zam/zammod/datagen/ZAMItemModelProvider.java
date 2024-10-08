package net.zam.zammod.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.data.models.model.ModelLocationUtils;
import net.minecraft.data.models.model.ModelTemplate;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.zam.zammod.ZAMMod;
import net.zam.zammod.registry.ZAMItems;

public class ZAMItemModelProvider extends ItemModelProvider {
    public ZAMItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, ZAMMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ZAMItems.CORAL_FRUIT);
        simpleItem(ZAMItems.MARINE_BUBBLE);
        simpleItem(ZAMItems.STARDROP);
        simpleItem(ZAMItems.LOST_KEY);
        simpleItem(ZAMItems.DOUBLOON);
        simpleItem(ZAMItems.OPAL);
        simpleItem(ZAMItems.AQUAMARINE);
        //    simpleItem(ZAMItems.OLD_RECORD_BOX);
     //   caseItem(ZAMItems.POKEMON_ALBUM_CASE);
     //   pokemonMusicItem(ZAMItems.POKEMON_STRIATON_CITY);
     //   pokemonMusicItem(ZAMItems.POKEMON_CASTELIA_CITY);
     //   pokemonMusicItem(ZAMItems.POKEMON_NIMBASA_CITY);
     //   pokemonMusicItem(ZAMItems.POKEMON_DRIFTVEIL_CITY);
     //   pokemonMusicItem(ZAMItems.POKEMON_MISTRALTON_CITY);
      //  pokemonMusicItem(ZAMItems.POKEMON_ICIRRUS_CITY);
     //   pokemonMusicItem(ZAMItems.POKEMON_VIRBANK_CITY);
     //   pokemonMusicItem(ZAMItems.POKEMON_HUMILAU_CITY);
     //   pokemonMusicItem(ZAMItems.POKEMON_GEAR_STATION);
    //    pokemonMusicItem(ZAMItems.POKEMON_EAST_PROVINCE);
    //    pokemonMusicItem(ZAMItems.POKEMON_VOLO);
     //   pokemonMusicItem(ZAMItems.POKEMON_NS_CASTLE);
    //    withExistingParent(ZAMItems.OTTER_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));

        simpleItem(ZAMItems.STARDEW_SMITHING_TEMPLATE);
        handheldItem(ZAMItems.STARDEW_SWORD);
        handheldItem(ZAMItems.STARDEW_PICKAXE);
        handheldItem(ZAMItems.STARDEW_AXE);
        handheldItem(ZAMItems.STARDEW_SHOVEL);
        handheldItem(ZAMItems.STARDEW_HOE);
    }



    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(ZAMMod.MOD_ID,"item/" + item.getId().getPath()));
    }
    private ItemModelBuilder caseItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(ZAMMod.MOD_ID,"item/music/cover/" + item.getId().getPath()));
    }

    private ItemModelBuilder pokemonMusicItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(ZAMMod.MOD_ID,"item/music/pokemon/" + item.getId().getPath()));
    }

    private ItemModelBuilder handheldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(ZAMMod.MOD_ID,"item/" + item.getId().getPath()));
    }
}
