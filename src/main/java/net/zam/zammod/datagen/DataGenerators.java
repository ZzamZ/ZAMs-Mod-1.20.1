package net.zam.zammod.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.zam.zammod.ZAMMod;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = ZAMMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        ExistingFileHelper existingFileHelper =event.getExistingFileHelper();
        generator.addProvider(event.includeClient(), new ZAMItemModelProvider(packOutput, existingFileHelper));
        BlockTagsProvider blockTagsProvider = new ZAMBlockTags(packOutput, lookupProvider, existingFileHelper);
        generator.addProvider(event.includeServer(), blockTagsProvider);
        generator.addProvider(event.includeServer(), new ZAMItemTags(packOutput, lookupProvider, blockTagsProvider.contentsGetter(), existingFileHelper));
        generator.addProvider(event.includeClient(), new ZAMBlockModelGenerator(packOutput, existingFileHelper));
        BiomeTagsProvider biomeTagsProvider = new ZAMBiomeTagsProvider(packOutput, lookupProvider, existingFileHelper);
        generator.addProvider(event.includeServer(), biomeTagsProvider);
        generator.addProvider(event.includeServer(), new ZAMWorldGenProvider(packOutput, lookupProvider));
    //    generator.addProvider(event.includeServer(), ZAMLootTableProvider.create(packOutput));
        generator.addProvider(event.includeServer(), new ZAMRecipeProvider(packOutput));



    }
}
