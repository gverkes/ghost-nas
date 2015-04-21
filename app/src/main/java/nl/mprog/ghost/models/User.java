package nl.mprog.ghost.models;
/**
 * Created by Govert on 4/21/15.
 * Simple user class, used for the word game 'Ghost'
 */


public class User {
    private int id;
    private String name;
    private int score;
    private int numberOfGames;

    public User(String name) {
        this.name = name;
        this.score = 0;
    }

    @Override
    public String toString() {
        return Integer.toString(id) + ":" + name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getNumberOfGames() {
        return numberOfGames;
    }

    public void increaseNumberOfGames() {
        this.numberOfGames++;
    }

    public int getScore() {
        return score;
    }
}
