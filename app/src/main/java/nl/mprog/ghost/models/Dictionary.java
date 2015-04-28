package nl.mprog.ghost.models;
/**
 * Created by Govert on 4/21/15.
 * Basic dictionary class
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
        for (String word : wordlistFiltered) {
            if (word.startsWith(filter))
               tempWordlistFiltered.add(word);
        }

        wordlistFiltered = new ArrayList<>(tempWordlistFiltered);
    }

    public void loadFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename)))
        {

            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                System.out.println(sCurrentLine);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public int count() {
        return wordlistFiltered.size();
    }

    public String get_language() {
        return language;
    }

    public String result() {
        return (this.count() == 1) ? wordlistFiltered.get(0) : null;
    }

    public void reset() {
        wordlistFiltered = new ArrayList<>(wordlist);
    }

}
