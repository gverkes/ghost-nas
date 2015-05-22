package nl.mprog.ghost.models;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by govert on 4/28/15.
 */
public class SingleplayerGame extends Game {
    public static final int DIFFICULTY_EASY = 0;
    public static final int DIFFICULTY_NORMAL = 1;
    public static final int DIFFICULTY_HARD = 2;

    private User player, computer;
    private int difficulty;
    private User turnPlayer;
    private User winner;
    private int winReason;

    public SingleplayerGame(Dictionary dictionary, User player, int difficulty) {
        super(dictionary);

        this.player= player;
        this.computer = new User("Computer");

        turnPlayer = player;
        winner= null;
        winReason = WIN_REASON_NONE;
        this.difficulty = difficulty;
    }

    @Override
    public void guess(char guessedChar) {
        super.guess(guessedChar);
        checkIfWinner();

        super.guess(computerGuess());
        checkIfWinner();
    }

    @Override
    public void checkIfWinner() {
        // If no words are left
        if (wordsLeft() == 0) {
            this.winReason = WIN_REASON_NOWORDS;
            winner = this.turn();
            player.increaseNumberOfGames();
            winner.increaseScore();
        // If only one word is left and the filter equals this word
        } else if (wordsLeft() == 1 && wordMade()) {
            this.winReason = WIN_REASON_WORDMADE;
            winner = this.turn();
            player.increaseNumberOfGames();
            winner.increaseScore();
        }
    }

    public char computerGuess() {
        // init computerGuessChar on 'Z' so all letters in alphabet are getting their turn,
        // only if none of them match it stays 'Z' but that also means the word can only be
        // made with 'Z'
        char computerGuessChar = 'Z';

        if (difficulty == DIFFICULTY_EASY) {

        } else if (difficulty == DIFFICULTY_NORMAL) {

        } else if (difficulty == DIFFICULTY_HARD) {
            HashMap<Character, int[]> filt = getDictionary().possibleFilters();

            int minWords = -1;
            float maxRatioWOddWords = 0;

            for (Map.Entry<Character, int[]> entry : filt.entrySet())
            {
                if (entry.getValue()[0] > 0) {
                    if (minWords == -1 || minWords > entry.getValue()[0]) {
                        minWords = entry.getValue()[0];
                    }

                    if (entry.getValue()[1] != 0) {
                        float tempRatioOddWords = entry.getValue()[1] / (float)entry.getValue()[0];
                        if (tempRatioOddWords > maxRatioWOddWords) {
                            maxRatioWOddWords = tempRatioOddWords;
                            computerGuessChar = entry.getKey();
                        }
                    }

                    if (computerGuessChar == 'Z') {
                        computerGuessChar = entry.getKey();
                    }

                    Log.i("possibleFilters", entry.getKey() + ": " + Integer.toString(entry.getValue()[0]) +
                            " | " + Integer.toString(entry.getValue()[1]));
                }
            }

            return computerGuessChar;
        }
        return computerGuessChar;
    }

    @Override
    public void reset() {
        super.reset();
        turnPlayer = player;
        winner= null;
        winReason = WIN_REASON_NONE;
    }

    @Override
    public int wordsLeft() {
        return super.wordsLeft();
    }

    @Override
    public boolean ended() {
        return winner != null;
    }

    @Override
    public User turn() {
        return turnPlayer;
    }

    @Override
    public User winner() {
        return winner;
    }

    @Override
    public boolean wordMade() {
        return super.wordMade();
    }

    // get functions
    @Override
    public Dictionary getDictionary() {
        return super.getDictionary();
    }

    @Override
    public String getGuessWord() {
        return super.getGuessWord();
    }

    @Override
    public User[] getPlayers() {
        return new User[]{this.player, this.computer};
    }

    @Override
    public String getWinnerName() {
        if (this.ended()) {
            return this.winner.getName();
        }
        return null;
    }

    @Override
    public int getWinReason() {
        return this.winReason;
    }

    @Override
    public String getTurnPlayerName() {
        return this.turn().getName();
    }

    @Override
    public String[] getPlayersNames() {
        return new String[]{this.player.getName(), this.computer.getName()};
    }
}
