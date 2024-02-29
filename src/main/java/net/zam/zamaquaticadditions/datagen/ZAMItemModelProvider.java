package net.zam.zamaquaticadditions.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.zam.zamaquaticadditions.ZAMAquaticAdditions;
import net.zam.zamaquaticadditions.registry.ZAMItems;

public class ZAMItemModelProvider extends ItemModelProvider {
    public ZAMItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, ZAMAquaticAdditions.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        caseItem(ZAMItems.POKEMON_ALBUM_CASE);
        pokemonMusicItem(ZAMItems.POKEMON_ACCUMULA_TOWN);
        pokemonMusicItem(ZAMItems.POKEMON_NIMBASA_CITY);
        pokemonMusicItem(ZAMItems.POKEMON_DRIFTVEIL_CITY);
        pokemonMusicItem(ZAMItems.POKEMON_ICIRRUS_CITY);
        pokemonMusicItem(ZAMItems.POKEMON_GROUND);
        pokemonMusicItem(ZAMItems.POKEMON_VIRBANK_CITY);
        pokemonMusicItem(ZAMItems.POKEMON_HUMILAU_CITY);
        pokemonMusicItem(ZAMItems.POKEMON_WATER);
        pokemonMusicItem(ZAMItems.POKEMON_NS_CASTLE);
        pokemonMusicItem(ZAMItems.POKEMON_VOLO);
        pokemonMusicItem(ZAMItems.POKEMON_SKYARROW_BRIDGE);
        pokemonMusicItem(ZAMItems.POKEMON_GEAR_STATION);
        pokemonMusicItem(ZAMItems.POKEMON_STRIATON_CITY);
        pokemonMusicItem(ZAMItems.POKEMON_EAST_PROVINCE);
        pokemonMusicItem(ZAMItems.POKEMON_HOENN_ROAD);
        pokemonMusicItem(ZAMItems.POKEMON_UNOVA_ROAD);
        pokemonMusicItem(ZAMItems.POKEMON_LUMIOSE_CITY);
        pokemonMusicItem(ZAMItems.POKEMON_CASTELIA_CITY);
        pokemonMusicItem(ZAMItems.POKEMON_MISTRALTON_CITY);
        pokemonMusicItem(ZAMItems.POKEMON_MISTRALTON_GYM);

    }


    private ItemModelBuilder caseItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(ZAMAquaticAdditions.MOD_ID,"item/music/cover/" + item.getId().getPath()));
    }

    private ItemModelBuilder pokemonMusicItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(ZAMAquaticAdditions.MOD_ID,"item/music/pokemon/" + item.getId().getPath()));
    }
}
