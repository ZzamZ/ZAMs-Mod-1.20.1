package net.zam.zammod.entity.mob;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.ai.goal.LookAtTradingPlayerGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.OpenDoorGoal;
import net.minecraft.world.entity.ai.goal.MoveTowardsRestrictionGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.nbt.CompoundTag;
import org.jetbrains.annotations.Nullable;


import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.logging.Logger;

public class LostMerchantEntity extends WanderingTrader {
    private static final Logger LOGGER = Logger.getLogger(LostMerchantEntity.class.getName());
    private LocalDateTime lastTradeUpdate;
    private static final ZoneId TIME_ZONE = ZoneId.of("America/New_York"); // Change to your time zone if needed
    private static final int RESET_HOUR = 21; // 9:00 PM EST
    private static final int RESET_MINUTE = 10; // 9:10 PM EST

    public LostMerchantEntity(EntityType<? extends WanderingTrader> entityType, Level world) {
        super(entityType, world);
        this.lastTradeUpdate = LocalDateTime.now(TIME_ZONE).minusDays(1); // Initialize to force trade update on first spawn
    }

    public static AttributeSupplier.Builder createAttributes() {
        return WanderingTrader.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.2D);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new LookAtTradingPlayerGoal(this));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(3, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(4, new OpenDoorGoal(this, true));
        this.goalSelector.addGoal(5, new MoveTowardsRestrictionGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new RandomStrollGoal(this, 0.6D));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 8.0F));
    }

    @Override
    public void tick() {
        super.tick();
        updateTrades();
    }

    @Override
    protected void updateTrades() {
        LocalDateTime now = LocalDateTime.now(TIME_ZONE);
        long minutesUntilReset = Duration.between(now, getNextResetTime()).toMinutes();
        LOGGER.info("Current time: " + now);
        LOGGER.info("Next reset time: " + getNextResetTime());
        LOGGER.info("Minutes until reset: " + minutesUntilReset);

        if (minutesUntilReset <= 0 || lastTradeUpdate.isBefore(getLastResetTime())) {
            lastTradeUpdate = now;
            LOGGER.info("Updating trades at: " + now);
            VillagerTrades.ItemListing[] newTrades = getTradesForDay(now);
            this.getOffers().clear();
            this.addOffersFromItemListings(this.getOffers(), newTrades, 5);
        }
    }

    private LocalDateTime getNextResetTime() {
        LocalDateTime now = LocalDateTime.now(TIME_ZONE);
        LocalDateTime nextReset = now.withHour(RESET_HOUR).withMinute(RESET_MINUTE).withSecond(0).withNano(0);
        if (now.isAfter(nextReset)) {
            nextReset = nextReset.plusDays(1);
        }
        return nextReset;
    }

    private LocalDateTime getLastResetTime() {
        return getNextResetTime().minusDays(1);
    }

    private VillagerTrades.ItemListing[] getTradesForDay(LocalDateTime date) {
        int dayOfCycle = (int) ChronoUnit.DAYS.between(LocalDateTime.now(TIME_ZONE).withDayOfMonth(1), date) % 14;
        switch (dayOfCycle) {
            case 0:
                return new VillagerTrades.ItemListing[]{
                        new SimpleTrade(new ItemStack(Items.EMERALD, 1), new ItemStack(Items.DIAMOND, 1), 5, 10, 0.2F),
                        new SimpleTrade(new ItemStack(Items.EMERALD, 2), new ItemStack(Items.ENDER_PEARL, 1), 3, 10, 0.2F)
                };
            case 1:
                return new VillagerTrades.ItemListing[]{
                        new SimpleTrade(new ItemStack(Items.EMERALD, 1), new ItemStack(Items.GOLD_INGOT, 3), 5, 10, 0.2F),
                        new SimpleTrade(new ItemStack(Items.EMERALD, 2), new ItemStack(Items.BLAZE_ROD, 1), 3, 10, 0.2F)
                };
            case 2:
                return new VillagerTrades.ItemListing[]{
                        new SimpleTrade(new ItemStack(Items.EMERALD, 1), new ItemStack(Items.IRON_INGOT, 5), 5, 10, 0.2F),
                        new SimpleTrade(new ItemStack(Items.EMERALD, 2), new ItemStack(Items.NETHER_WART, 3), 3, 10, 0.2F)
                };
            case 3:
                return new VillagerTrades.ItemListing[]{
                        new SimpleTrade(new ItemStack(Items.EMERALD, 1), new ItemStack(Items.LAPIS_LAZULI, 8), 5, 10, 0.2F),
                        new SimpleTrade(new ItemStack(Items.EMERALD, 2), new ItemStack(Items.QUARTZ, 4), 3, 10, 0.2F)
                };

        }
        return new VillagerTrades.ItemListing[0];
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putString("LastTradeUpdate", lastTradeUpdate.toString());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("LastTradeUpdate")) {
            this.lastTradeUpdate = LocalDateTime.parse(compound.getString("LastTradeUpdate"));
        }
    }

    static class SimpleTrade implements VillagerTrades.ItemListing {
        private final ItemStack input;
        private final ItemStack output;
        private final int maxUses;
        private final int xpValue;
        private final float priceMultiplier;

        public SimpleTrade(ItemStack input, ItemStack output, int maxUses, int xpValue, float priceMultiplier) {
            this.input = input;
            this.output = output;
            this.maxUses = maxUses;
            this.xpValue = xpValue;
            this.priceMultiplier = priceMultiplier;
        }

        @Nullable
        @Override
        public MerchantOffer getOffer(Entity trader, RandomSource random) {
            return new MerchantOffer(input, output, maxUses, xpValue, priceMultiplier);
        }
    }
}
