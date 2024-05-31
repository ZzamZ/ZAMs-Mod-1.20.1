package net.zam.zammod.block.arcade;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.zam.zammod.gui.arcade.ArcadeGame;
import net.zam.zammod.gui.arcade.ArcadeManager;
import net.zam.zammod.gui.arcade.ArcadeScreen;
import net.zam.zammod.gui.arcade.container.ArcadeContainer;
import net.zam.zammod.registry.ZAMBlockEntities;;

public class ArcadeTileEntity extends BlockEntity implements MenuProvider {

    public ArcadeScreen screen = null;
    public ArcadeGame game = null;
    public long time = 0;

    public ArcadeTileEntity(BlockPos pos, BlockState state) {
        super(ZAMBlockEntities.ARCADE_MACHINE.get(), pos, state);
        screen = new ArcadeScreen();
        //game = new QSPLArcadeGame(screen, this);

    }

    @Override
    public AbstractContainerMenu createMenu(int windowId, Inventory inv, Player player) {
        return ArcadeContainer.create(windowId, inv, getBlockPos());
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("container.zammod.arcade");
    }

    public static void tick(Level world, BlockPos pos, BlockState state, ArcadeTileEntity te) {
        if(world.isClientSide() && te.game != null)
            te.game.frame(te.time++);
    }

    public void setGame(ArcadeManager.GameConstructor g) {
        this.game = g.construct(screen, this);
    }
}