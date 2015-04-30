package nl.mprog.ghost.models;
/**
 * Created by Govert on 4/21/15.
 * Basic dictionary class
 */

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;



public class Dictionary {
    public HashSet<String> wordlist;
    public HashSet<String> wordlistFiltered;
    private String language;

    public Dictionary(Context context, String filename, String language) {
        this.wordlist = new HashSet<>();
        this.loadAssetFile(context, filename);
        this.wordlistFiltered = new HashSet<>(this.wordlist);
        this.language = language;
    }

    public Dictionary(HashSet<String> wordlist, String language) {
        this.wordlist = wordlist;
        this.wordlistFiltered = new HashSet<>(this.wordlist);
        this.language = language;
    }

    public void filter(String filter) {
        HashSet<String> tempWordlistFiltered = new HashSet<>();
        for (String word : wordlistFiltered) {
            if (word.toUpperCase().startsWith(filter.toUpperCase()))
               tempWordlistFiltered.add(word);
        }

        wordlistFiltered = new HashSet<>(tempWordlistFiltered);
    }

    public void loadAssetFile(Context context, String filename) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(context.getAssets().open(filename)));
            String word;
            while((word = br.readLine()) != null)
                this.wordlist.add(word); //break txt file into different words, add to wordList
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                br.close(); // Close file, when done reading
            }
            catch(IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public int count() {
        return wordlistFiltered.size();
    }

    public String get_language() {
        return language;
    }

    public String result() {
        if (wordlistFiltered.size() == 1) {
            for (String word : wordlistFiltered)
                return word;
        }
        return null;
    }

    public void reset() {
        wordlistFiltered = new HashSet<>(wordlist);
    }

}
