package nl.mprog.ghost.models;


import android.util.Log;

/**
 * Created by Govert on 4/21/15.
 * Game class, containing a game of the simple word game 'Ghost'
 */



public abstract class Game {
    public static final int WIN_REASON_NONE = -1;
    public static final int WIN_REASON_NOWORDS = 0;
    public static final int WIN_REASON_WORDMADE = 1;

    private Dictionary dictionary;

    public Game(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public Dictionary getDictionary() {
        return dictionary;
    }

    public String getGuessWord() {
        return dictionary.getFilterString();
    }

    public void guess(char guessedChar) {
        dictionary.addCharFilter(guessedChar);
    }

    public void reset() {
        dictionary.reset();

    }

    public int wordsLeft() {
        return dictionary.count();
    }

    public boolean wordMade() {
        if (wordsLeft() == 1) {
            for (String word : dictionary.wordlistFiltered) {
                return getGuessWord().equals(word.toUpperCase());
            }
        }
        return false;
    }

    public abstract boolean ended();

    public abstract User turn();

    public abstract User winner();

    public abstract void checkIfWinner();

    public abstract User[] getPlayers();

    public abstract String getWinnerName();

    public abstract int getWinReason();

    public abstract String getTurnPlayerName();

    public abstract String[] getPlayersNames();
}
