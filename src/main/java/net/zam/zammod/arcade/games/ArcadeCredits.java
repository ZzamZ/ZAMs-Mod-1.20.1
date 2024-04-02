package net.zam.zammod.arcade.games;

import net.zam.zammod.arcade.ArcadeGame;
import net.zam.zammod.arcade.ArcadeScreen;
import net.zam.zammod.block.arcade.ArcadeTileEntity;

public class ArcadeCredits extends ArcadeGame {

    public ArcadeCredits(ArcadeScreen s, ArcadeTileEntity te) {
        super(s, te);
    }

    @Override
    public void start() {
        super.start();
        s.setColors(0x0, 0xf);
        s.clear();

        int ln = 1;
        s.print(1, ln++, "Builders Crafts & Additions");
        ln++;
        s.print(1, ln++, "Programming: MRH0");
        s.print(1, ln++, "Models: MRH0");
        s.print(1, ln++, "Textures: MRH0");
        s.print(1, ln++, "Translation: vanja-san, Mikeliro,");
        s.print(1, ln++, "Pancakes0228, Yupoman, alierenreis,");
        s.print(1, ln++, "spiderfromi, EdicionGamerYT, Lyaiya");
        ln++;
        s.print(1, ln++, "Source:");
        s.print(1, ln++, "github.com/mrh0/buildersaddition/arcade");
    }
}