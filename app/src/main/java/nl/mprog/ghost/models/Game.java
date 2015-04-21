package nl.mprog.ghost.models;
/**
 * Created by Govert on 4/21/15.
 * Game class, containing a game of the simple word game 'Ghost'
 */

import java.util.List;


public class Game {
    public static int SINGLEPLAYER_MODE = 0;
    public static int MULTIPLAYER_MODE = 1;

    private Dictionary dictionary;
    private int gameMode;
    private List<User> players;
    private User winnerPlayer;
    private User turnPlayer;
    private String word;

    public Game(Dictionary dictionary, List<User> players) {
        this.dictionary = dictionary;
        this.word = new String();
        this.players = players;

        if (this.players.size() == 1)
            this.gameMode = this.SINGLEPLAYER_MODE;
        else
            this.gameMode = this.MULTIPLAYER_MODE;

        this.winnerPlayer = null;
        this.turnPlayer = players.get(0);
    }

    public Dictionary getDictionary() {
        return dictionary;
    }

    public void setDictionary(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public List<User> getPlayerIds() {
        return players;
    }

    public User getTurnPlayers() {
        return turnPlayer;
    }

    public String getWord() {
        return word;
    }

    public boolean ended() {
        return winnerPlayer != null;
    }

    public void guess(char geussedChar) {

    }

    public User turn() {
        return turnPlayer;
    }

    public User winner() {
        return winnerPlayer;
    }

}
