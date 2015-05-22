package nl.mprog.ghost;

import android.app.Application;
import android.util.Log;

import nl.mprog.ghost.models.Game;

/**
 * Created by govert on 4/28/15.
 */
public class GhostApp extends Application{
    public static final String LANGUAGE_NONE = "";
    public static final String LANGUAGE_EN = "en";
    public static final String LANGUAGE_NL = "nl";

    public static final int SINGLEPLAYER_MODE = 0;
    public static final int MULTIPLAYER_MODE = 1;

    Game game;
    int gameMode;
    int backgroundTint;
    boolean backgroundTintEnabled = false;
    int backgroundTintProgress;

    public boolean isBackgroundTintEnabled() {
        return backgroundTintEnabled;
    }

    public void setBackgroundTintEnabled(boolean backgroundTintEnabled) {
        this.backgroundTintEnabled = backgroundTintEnabled;
    }


    public int getBackgroundTintProgress() {
        return backgroundTintProgress;
    }

    public void setBackgroundTintProgress(int backgroundTintProgress) {
        this.backgroundTintProgress = backgroundTintProgress;
    }

    public int getBackgroundTint() {
        return backgroundTint;
    }

    public void setBackgroundTint(int backgroundTint) {
        this.backgroundTint = backgroundTint;
        Log.v("tint: ", Integer.toString(backgroundTint));
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public int getGameMode() {
        return gameMode;
    }

    public void setGameMode(int gameMode) {
        this.gameMode = gameMode;
    }

}
