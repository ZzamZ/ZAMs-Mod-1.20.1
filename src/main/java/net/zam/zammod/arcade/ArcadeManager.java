package net.zam.zammod.arcade;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.network.chat.Component;
import net.zam.zammod.arcade.games.ArcadeBreakout;
import net.zam.zammod.arcade.games.ArcadeCredits;
import net.zam.zammod.arcade.games.ArcadeSnake;
import net.zam.zammod.block.arcade.ArcadeTileEntity;

public class ArcadeManager {
    public interface GameConstructor {
        public ArcadeGame construct(ArcadeScreen s, ArcadeTileEntity te);
    }

    public static ArcadeManager instance = null;
    private final ArrayList<GameConstructor> games;
    private final ArrayList<Component> names;

    public ArcadeManager() {
        if(instance == null)
            instance = this;
        games = new ArrayList<GameConstructor>();
        names = new ArrayList<Component>();
    }

    public void add(GameConstructor game, String key) {
        games.add(game);
        names.add(Component.translatable("arcade.buildersaddition.game." + key));
    }

    public List<GameConstructor> getGames() {
        return this.games;
    }

    public GameConstructor getGame(int index) {
        return this.games.get(index);
    }

    public String getGameName(int index) {
        return this.names.get(index).getString();
    }

    public static void init() {
        new ArcadeManager();
        instance.add(ArcadeSnake::new, "snake");
        instance.add(ArcadeBreakout::new, "breakout");
        instance.add(ArcadeCredits::new, "credits");
    }
}