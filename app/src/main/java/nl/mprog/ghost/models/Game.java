package nl.mprog.ghost.models;
/**
 * Created by Govert on 4/21/15.
 * Game class, containing a game of the simple word game 'Ghost'
 */



public abstract class Game {
    private Dictionary dictionary;
    private StringBuffer guessWord;

    public Game(Dictionary dictionary) {
        this.dictionary = dictionary;
        guessWord = new StringBuffer();
    }

    public Dictionary getDictionary() {
        return dictionary;
    }

    public StringBuffer getGuessWord() {
        return guessWord;
    }

    public void guess(char guessedChar) {
        guessWord.append(guessedChar);
        dictionary.filter(guessWord.toString());
    }

    public void reset() {
        dictionary.reset();
        guessWord = new StringBuffer();
    }

    public int wordsLeft() {
        return dictionary.count();
    }

    public abstract boolean ended();

    public abstract User turn();

    public abstract User winner();

    public abstract String getWinMessage();

    public abstract String getTurnPlayerMessage();

    public abstract String getVersusMessage();
}
