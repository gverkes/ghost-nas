package nl.mprog.ghost.models;


/**
 * Created by govert on 4/28/15.
 */
public class MultiplayerGame extends Game {
    private User playerOne, playerTwo;
    private User turnPlayer;
    private User winner;

    public MultiplayerGame(Dictionary dictionary, User playerOne, User playerTwo) {
        super(dictionary);

        this.playerOne = playerOne;
        this.playerTwo = playerTwo;

        turnPlayer = playerOne;
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
        }
    }

    @Override
    public User winner() {
        return winner;
    }

    @Override
    public User turn() {
        return turnPlayer;
    }

    public void nextTurn() {
        turnPlayer = (turnPlayer == playerOne) ? playerTwo : playerOne;
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
        return playerOne.getName() + " vs. " + playerTwo.getName();
    }
}
