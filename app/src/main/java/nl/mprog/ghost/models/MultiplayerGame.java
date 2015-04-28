package nl.mprog.ghost.models;

import java.util.List;

/**
 * Created by govert on 4/28/15.
 */
public class MultiplayerGame extends Game {
    private List<User> players;
    private int indexTurnPlayer;
    private User winner;

    public MultiplayerGame(Dictionary dictionary, List<User> players) {
        super(dictionary);

        if (players.size() == 2) {
            this.players = players;
        } else {
            throw new IllegalArgumentException("MultiplayerGame class can only be constructed with players list of size 2");
        }

        indexTurnPlayer = 0;
        winner= null;
    }

    @Override
    public boolean ended() {
        return winner != null;
    }

    @Override
    public void guess(char guessedChar) {
        super.guess(guessedChar);
        this.nextTurn();
        
        if (super.wordsLeft() == 0) {
            winner = this.turn();
        } else if (super.wordsLeft() == 1) {
            winner = this.turn();
        } else {
        }
    }

    @Override
    public User winner() {
        return winner;
    }

    @Override
    public User turn() {
        return players.get(indexTurnPlayer);
    }

    public void nextTurn() {
        indexTurnPlayer = 1 - indexTurnPlayer;
    }

    @Override
    public String getWinMessage() {
        if (this.ended()) {
            return this.winner.getName() + " won!";
        }
        return null;
    }

    @Override
    public String getTurnPlayerMessage() {
        return this.turn().getName() + "'s turn";
    }

    @Override
    public String getVersusMessage() {
        return players.get(0).getName() + " vs. " + players.get(1).getName();
    }
}
