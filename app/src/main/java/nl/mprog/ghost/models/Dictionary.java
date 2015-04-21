package nl.mprog.ghost.models;
/**
 * Created by Govert on 4/21/15.
 * Basic dictionary class
 */

import java.util.ArrayList;
import java.util.List;


public class Dictionary {
    public List<String> wordlist;
    public List<String> wordlistFiltered;
    private String language;

    public Dictionary(List<String> wordlist, String language) {
        this.wordlist = wordlist;
        this.wordlistFiltered = new ArrayList<>(this.wordlist);
        this.language = language;
    }

    public void filter(String filter) {
        List<String> tempWordlistFiltered = new ArrayList<>();
        for (String word : this.wordlistFiltered) {
            if (word.startsWith(filter))
               tempWordlistFiltered.add(word);
        }
        this.wordlistFiltered = new ArrayList<>(tempWordlistFiltered);
    }

    public int count() {
        return this.wordlistFiltered.size();
    }

    public String result() {
        return (this.count() == 1) ? this.wordlistFiltered.get(0) : null;
    }

    public void reset() {
        this.wordlistFiltered = new ArrayList<>(this.wordlist);
    }

}
