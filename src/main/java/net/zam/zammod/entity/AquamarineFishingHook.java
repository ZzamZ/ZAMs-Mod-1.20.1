package net.zam.zammod.entity;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ItemFishedEvent;
import net.zam.zammod.registry.ZAMItems;
import net.zam.zammod.registry.ZAMSounds;
import net.zam.zammod.registry.ZAMTags;
import net.zam.zammod.util.network.FishingHookLogic;

import java.util.List;
import java.util.Random;

public class AquamarineFishingHook extends FishingHook {

    private static final double DOUBLOON_CHANCE = 0.05; // 5% chance for a doubloon

    public AquamarineFishingHook(EntityType<? extends FishingHook> entityType, Level level) {
        super(entityType, level);
        System.out.println("AquamarineFishingHook created!");
    }

    @Override
    public int retrieve(ItemStack itemStack) {
        ServerPlayer player = (ServerPlayer) this.getPlayerOwner();
        if (player == null) {
            System.out.println("No player associated with this hook.");
            return 0;
        }

        List<ItemStack> caughtItems = getCaughtItems();
        System.out.println("Caught items: " + caughtItems);

        boolean hasMinigameItem = caughtItems.stream()
                .anyMatch(stack -> stack.is(ZAMTags.STARTS_MINIGAME));
        System.out.println("Minigame item found: " + hasMinigameItem);

        if (hasMinigameItem) {
            System.out.println("Starting minigame for player " + player.getName().getString());
            player.level().playSound(null, player, ZAMSounds.PULL_ITEM.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
            CriteriaTriggers.FISHING_ROD_HOOKED.trigger(player, itemStack, this, caughtItems);
            FishingHookLogic.getStoredRewards(this).ifPresent(rewards -> rewards.addAll(caughtItems));
            FishingHookLogic.startMinigame(player);
            return 0; // Prevent normal retrieve behavior
        } else {
            System.out.println("No minigame items found, normal item retrieval.");
            FishingHookLogic.modifyRewards(caughtItems, 0);

            // Assuming 1.0 as a placeholder value for accuracy, modify if you have actual logic to determine accuracy
            double accuracy = 1.0;
            FishingHookLogic.giveRewards(player, accuracy);
        }

        return super.retrieve(itemStack);
    }

    public List<ItemStack> getCaughtItems() {
        if (!(this.level() instanceof ServerLevel serverLevel)) {
            return List.of(); // Return an empty list if not on server side
        }

        LootTable lootTable = serverLevel.getServer().getLootData().getLootTable(getFishingLootTable());
        LootParams.Builder paramsBuilder = new LootParams.Builder(serverLevel)
                .withParameter(LootContextParams.ORIGIN, this.position())
                .withParameter(LootContextParams.TOOL, new ItemStack(Items.FISHING_ROD))
                .withParameter(LootContextParams.THIS_ENTITY, this)
                .withParameter(LootContextParams.KILLER_ENTITY, this.getOwner());

        List<ItemStack> loot = lootTable.getRandomItems(paramsBuilder.create(LootContextParamSets.FISHING));

        // Handle the chance of catching a doubloon
        if (new Random().nextDouble() < DOUBLOON_CHANCE) {
            loot.add(new ItemStack(ZAMItems.DOUBLOON.get()));
        }

        // Fire the ItemFishedEvent
        ItemFishedEvent event = new ItemFishedEvent(loot, 1, this);
        MinecraftForge.EVENT_BUS.post(event);

        // Return the list of items caught
        return event.isCanceled() ? List.of() : event.getDrops();
    }

    private ResourceLocation getFishingLootTable() {
        return new ResourceLocation("minecraft", "gameplay/fishing");
    }
}
