package nl.mprog.ghost.models;


/**
 * Created by govert on 4/28/15.
 */
public class MultiplayerGame extends Game {
    private User playerOne, playerTwo;
    private User turnPlayer;
    private User winner;
    private int winReason;

    public MultiplayerGame(Dictionary dictionary, User playerOne, User playerTwo) {
        super(dictionary);

        this.playerOne = playerOne;
        this.playerTwo = playerTwo;

        turnPlayer = playerOne;
        winner= null;
        winReason = WIN_REASON_NONE;
    }

    @Override
    public void guess(char guessedChar) {
        super.guess(guessedChar);
        nextTurn();

        // If there is a winner, User variable winner will be set
        checkIfWinner();
    }

    @Override
    public void checkIfWinner() {
        // If no words are left
        if (wordsLeft() == 0) {
            this.winReason = WIN_REASON_NOWORDS;
            winner = this.turn();
            playerOne.increaseNumberOfGames();
            playerTwo.increaseNumberOfGames();
            winner.increaseScore();
        // If only one word is left and the filter equals this word
        } else if (wordsLeft() == 1 && wordMade()) {
            this.winReason = WIN_REASON_WORDMADE;
            winner = this.turn();
            playerOne.increaseNumberOfGames();
            playerTwo.increaseNumberOfGames();
            winner.increaseScore();
        }
    }

    @Override
    public void reset() {
        super.reset();
        turnPlayer = playerOne;
        winner= null;
        winReason = WIN_REASON_NONE;
    }

    @Override
    public int wordsLeft() {
        return super.wordsLeft();
    }

    @Override
    public boolean wordMade() {
        return super.wordMade();
    }


    @Override
    public boolean ended() {
        return winner != null;
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

    // get functions
    @Override
    public Dictionary getDictionary() {
        return super.getDictionary();
    }

    @Override
    public String getGuessWord() {
        return super.getGuessWord();
    }

    public User[] getPlayers() {
        return new User[]{this.playerOne, this.playerTwo};
    }

    @Override
    public String getWinnerName() {
        if (this.ended()) {
            return this.winner.getName();
        }
        return null;
    }

    public int getWinReason() {
        return this.winReason;
    }

    @Override
    public String getTurnPlayerName() {
        return this.turn().getName();
    }

    @Override
    public String[] getPlayersNames() {
        return new String[]{playerOne.getName(), playerTwo.getName()};
    }
}
