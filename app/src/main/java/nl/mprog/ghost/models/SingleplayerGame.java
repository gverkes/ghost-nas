package nl.mprog.ghost.models;

/**
 * Created by govert on 4/28/15.
 */
public class SingleplayerGame extends Game {
    public SingleplayerGame(Dictionary dictionary) {
        super(dictionary);
    }

    @Override
    public String getWinMessage() {
        return null;
    }

    @Override
    public String getTurnPlayerMessage() {
        return null;
    }

    @Override
    public Dictionary getDictionary() {
        return super.getDictionary();
    }

    @Override
    public StringBuffer getGuessWord() {
        return super.getGuessWord();
    }

    @Override
    public void guess(char guessedChar) {
        super.guess(guessedChar);
    }

    @Override
    public void reset() {
        super.reset();
    }

    @Override
    public int wordsLeft() {
        return super.wordsLeft();
    }

    @Override
    public boolean ended() {
        return false;
    }

    @Override
    public User turn() {
        return null;
    }

    @Override
    public User winner() {
        return null;
    }

    @Override
    public String getVersusMessage() {
        return null;
    }
}
