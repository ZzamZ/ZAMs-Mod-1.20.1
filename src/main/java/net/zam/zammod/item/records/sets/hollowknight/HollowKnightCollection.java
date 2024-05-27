package net.zam.zammod.item.records.sets.hollowknight;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.RecordItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;
import java.util.List;

public class HollowKnightCollection extends RecordItem {


    public HollowKnightCollection(int pAnalogOutput, RegistryObject<SoundEvent> pSound, Properties pProperties, int pLengthInSeconds) {
        super(pAnalogOutput, pSound, pProperties, pLengthInSeconds);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);
        tooltip.add(Component.literal("Hollow Knight Collection").withStyle(ChatFormatting.WHITE));
    }
}
