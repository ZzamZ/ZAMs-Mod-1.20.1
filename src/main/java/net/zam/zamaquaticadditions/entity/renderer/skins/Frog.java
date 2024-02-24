package net.zam.zamaquaticadditions.entity.renderer.skins;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.FrogRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.zam.zamaquaticadditions.ZAMAquaticAdditions;

@OnlyIn(Dist.CLIENT)
public class Frog extends FrogRenderer {
    private static final ResourceLocation FROG_THANOS_LOCATION = new ResourceLocation(ZAMAquaticAdditions.MOD_ID, "textures/entity/frog/thanos_frog.png");


    public Frog(EntityRendererProvider.Context context) {
        super(context);
    }


    @Override
    public ResourceLocation getTextureLocation(net.minecraft.world.entity.animal.frog.Frog frog) {
        String s = ChatFormatting.stripFormatting(frog.getName().getString());
        if ("Thanos".equals(s)) {
            return FROG_THANOS_LOCATION;
        }
        return super.getTextureLocation(frog);
    }
}
