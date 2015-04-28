package nl.mprog.ghost;

import android.app.Application;

import nl.mprog.ghost.models.Dictionary;
import nl.mprog.ghost.models.Game;
import nl.mprog.ghost.models.User;

/**
 * Created by govert on 4/28/15.
 */
public class GhostApp extends Application{
    public static final int SINGLEPLAYER_MODE = 0;
    public static final int MULTIPLAYER_MODE = 1;

    Game game;
    int gameMode;

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
