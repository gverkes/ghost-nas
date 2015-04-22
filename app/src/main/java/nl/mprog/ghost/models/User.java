package nl.mprog.ghost.models;

import android.provider.BaseColumns;

/**
 * Created by Govert on 4/21/15.
 * Simple user class, used for the word game 'Ghost'
 */


public class User {


    private int id;
    private String name;
    private int score;
    private int numberOfGames;

    public User() { }

    public User(String name) {
        this.name = name;
        score = 0;
        numberOfGames = 0;
    }

    public User(int id, String name, int score, int numberOfGames) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.numberOfGames = numberOfGames;
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
        numberOfGames++;
    }

    public int getScore() {
        return score;
    }
}