package net.zam.zammod.entity.skins;

import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.WolfRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Wolf;
import net.zam.zammod.ZAMMod;

public class WolfSkins extends WolfRenderer {

    private static final ResourceLocation HONEY_TAME_LOCATION = new ResourceLocation(ZAMMod.MOD_ID, "textures/entity/wolf/honey_tame.png");
    private static final ResourceLocation HONEY_WILD_LOCATION = new ResourceLocation(ZAMMod.MOD_ID, "textures/entity/wolf/honey_wild.png");
    private static final ResourceLocation HONEY_ANGRY_LOCATION = new ResourceLocation(ZAMMod.MOD_ID, "textures/entity/wolf/honey_angry.png");

    public WolfSkins(EntityRendererProvider.Context p_174452_) {
        super(p_174452_);
    }

    @Override
    public ResourceLocation getTextureLocation(Wolf pEntity) {
        String s = ChatFormatting.stripFormatting(pEntity.getName().getString());
        if ("Honey".equals(s)) {
            if (pEntity.isTame()) {
                return HONEY_TAME_LOCATION;
            } else if (pEntity.isAngry()) {
                return HONEY_ANGRY_LOCATION;
            } else {
                return HONEY_WILD_LOCATION;
            }
        }
        return super.getTextureLocation(pEntity); // Call the superclass method for other wolves.
    }
}
