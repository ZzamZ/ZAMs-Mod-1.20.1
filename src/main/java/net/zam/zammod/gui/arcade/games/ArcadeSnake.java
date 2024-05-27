package net.zam.zammod.gui.arcade.games;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.zam.zammod.gui.arcade.ArcadeGame;
import net.zam.zammod.gui.arcade.ArcadeGui;
import net.zam.zammod.gui.arcade.ArcadeScreen;
import net.zam.zammod.gui.arcade.NameScreen;
import net.zam.zammod.block.arcade.ArcadeTileEntity;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArcadeSnake extends ArcadeGame {
    public ArcadeGui gui = null;
    private static final String HIGH_SCORES_FILE = "arcade_snake_high_scores.json";

    public static class HighScoreEntry implements Comparable<HighScoreEntry> {
        int score;
        String playerName;

        public HighScoreEntry(String playerName, int score) {
            this.score = score;
            this.playerName = playerName;
        }

        @Override
        public int compareTo(HighScoreEntry other) {
            return Integer.compare(other.score, this.score); // For descending order
        }
    }

    public static List<HighScoreEntry> highScores = new ArrayList<>();

    public ArcadeSnake(ArcadeScreen s, ArcadeTileEntity te) {
        super(s, te);
        pw = s.width - 10;
        ph = s.height;
        loadHighScores();
    }

    int px = 0;
    int py = 0;

    int lpx = 0;
    int lpy = 0;

    int dir = -1;
    int ldir = -1;

    int pw;
    int ph;

    long deadTimer = Long.MAX_VALUE;
    boolean isDead = false;

    int snakeColor = 0xa;

    int score;

    boolean initScreen = true;

    Tail last = null;
    Tail first = null;
    int tailExt = 0;

    @Override
    public void start() {
        super.start();
        s.clear();
        s.testScreen();
        s.setColors(0x0, 0xf);
        s.clear(0, 1, s.width, s.height - 2);
        s.print(1, 1, "Arcade Snake v1.0");
        String msg = " Press any key to continue. ";
        s.print(s.width / 2 - msg.length() / 2, s.height / 2, msg);

        initScreen = false; // skip
        begin(); // skip

        dir = -1;
    }

    private void begin() {
        s.setColors(0x0, 0xf);
        s.clear();
        px = pw / 2;
        py = ph / 2;
        lpx = px;
        lpy = py;
        dir = -1;
        ldir = -1;
        score = 0;
        isDead = false;
        deadTimer = Long.MAX_VALUE;
        last = null;
        first = null;
        tailExt = 0;
        s.setColors(0x8, 0xa);
        s.clear(pw, 0, s.width - pw, s.height);
        s.print(pw + 1, 1, "Score:");
        s.print(pw + 1, 2, score + "");
        s.print(pw + 1, 8, "Top");
        s.print(pw + 1, 9, "Scores:");
        updateHighScoresDisplay();
        s.print(pw + 1, s.height - 5, "Arrows");
        s.print(pw + 1, s.height - 4, "or WASD");
        spawnFood();
        spawnFood();
    }

    private void updateHighScoresDisplay() {
        s.setColors(0x8, 0xa);
        int highScoresStart = 11; // Y position for "High Scores" label
        for (int i = 0; i < highScores.size() && i < 3; i++) {
            HighScoreEntry entry = highScores.get(i);
            String highScoreText = (i + 1) + "." + entry.playerName + ":" + entry.score;
            s.print(pw + 1, highScoresStart + i, highScoreText);
        }
    }

    public int randomRange(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    private void spawnFood() {
        int x = 0;
        int y = 0;
        do {
            x = randomRange(0, pw);
            y = randomRange(0, ph);
        } while (s.getBackground(x, y) != 0x0 && s.getChar(x, y) != 'o');
        s.setColors(0x0, 0xe);
        s.print(x, y, "o");
        s.setColors(0x8, 0xa);
        s.print(pw + 1, s.height - 2, repeat(">", 7 - tickRate(score)));
    }

    private int tickRate(int t) {
        if (t > 20)
            return 2;
        else if (t > 10)
            return 3;
        else if (t > 7)
            return 4;
        else if (t > 3)
            return 5;
        return 6;
    }

    private String repeat(String t, int n) {
        StringBuilder r = new StringBuilder();
        for (int i = 0; i < n; i++) {
            r.append(t);
        }
        return r.toString();
    }

    private void updateMovement(long time) {
        lpx = px;
        lpy = py;
        if (dir >= 0) {
            switch (dir) {
                case 0:
                    py--;
                    if (py < 0)
                        py = ph - 1;
                    break;
                case 1:
                    px++;
                    if (px > pw - 1)
                        px = 0;
                    break;
                case 2:
                    py++;
                    if (py > ph - 1)
                        py = 0;
                    break;
                case 3:
                    px--;
                    if (px < 0)
                        px = pw - 1;
                    break;
            }
            if (s.getBackground(px, py) == snakeColor) {
                deadTimer = time + 60;
                isDead = true;
                s.setColors(0x4, 0xf);
                s.print(px, py, "x");
                playSound(0);
                Player player = Minecraft.getInstance().player;
                if (player != null) {
                    Minecraft.getInstance().setScreen(new NameScreen(this, score));
                }
                return;
            } else if (s.getChar(px, py) == 'o') {
                score += 1;
                s.setColors(0x8, 0xa);
                s.print(pw + 1, 1, "Score:");
                s.print(pw + 1, 2, score + "");
                spawnFood();
                tailExt = 3;
                playSound(30);
            }
            if (last != null && tailExt <= 0) {
                s.setColors(0x0, 0xf);
                s.print(last.x, last.y, " ");
                last = last.next;
            }
            s.setColors(snakeColor, 0xf);
            s.print(lpx, lpy, getToken(dir, ldir));
            ldir = dir;
            Tail t = new Tail(lpx, lpy, null);
            if (first != null)
                first.next = t;
            first = t;
            if (last == null)
                last = t;
            tailExt--;
            if (tailExt < 0)
                tailExt = 0;
        }
        s.setColors(snakeColor, 0xf);
        s.print(px, py, "@");
    }

    private String getToken(int d, int ld) {
        if (d == ld) {
            return dir == 0 || dir == 2 ? "-" : "|";
        } else if ((d == 0 && ld == 1) || (d == 1 && ld == 0))
            return "\\";
        else if ((d == 0 && ld == 3) || (d == 3 && ld == 0))
            return "/";
        else if ((d == 2 && ld == 1) || (d == 1 && ld == 2))
            return "/";
        else if ((d == 2 && ld == 3) || (d == 3 && ld == 2))
            return "\\";
        return "-";
    }

    @Override
    public void frame(long time) {
        super.frame(time);
        if (time > deadTimer)
            begin();
        if (time % tickRate(score) == 0 && !isDead)
            updateMovement(time);
    }

    @Override
    public void onKeyPressed(int key) {
        super.onKeyPressed(key);
        if (initScreen && isAny(key)) {
            initScreen = false;
            begin();
        }
        if (isUp(key) && ldir != 2) {
            dir = 0;
        } else if (isRight(key) && ldir != 3) {
            dir = 1;
        } else if (isDown(key) && ldir != 0) {
            dir = 2;
        } else if (isLeft(key) && ldir != 1) {
            dir = 3;
        } else if (isReset(key)) {
            s.setColors(0x4, 0x0);
            String msg = " Restarting. ";
            s.print(pw / 2 - msg.length() / 2, s.height / 2, msg);
        }
    }

    @Override
    public void onKeyReleased(int key) {
        super.onKeyReleased(key);
        if (isReset(key)) {
            begin();
        }
    }

    public void addHighScore(String playerName, int score) {
        highScores.add(new HighScoreEntry(playerName, score));
        Collections.sort(highScores);
        if (highScores.size() > 3) {
            highScores.remove(3);
        }
        saveHighScores();
    }

    public void saveHighScores() {
        Gson gson = new Gson();
        try (Writer writer = new FileWriter(HIGH_SCORES_FILE)) {
            gson.toJson(highScores, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadHighScores() {
        Gson gson = new Gson();
        try (Reader reader = new FileReader(HIGH_SCORES_FILE)) {
            Type highScoreListType = new TypeToken<ArrayList<HighScoreEntry>>() {}.getType();
            highScores = gson.fromJson(reader, highScoreListType);
            if (highScores == null) {
                highScores = new ArrayList<>();
            }
        } catch (FileNotFoundException e) {
            highScores = new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class Tail {
        public int x;
        public int y;
        public Tail next;

        public Tail(int x, int y, Tail next) {
            this.x = x;
            this.y = y;
            this.next = next;
        }
    }
}
