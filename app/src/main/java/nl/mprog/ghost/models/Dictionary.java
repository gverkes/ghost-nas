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
import java.util.List;



public class Dictionary {
    public List<String> wordlist;
    public List<String> wordlistFiltered;
    private String language;

    public Dictionary(Context context, String filename, String language) {
        this.wordlist = new ArrayList<>();
        this.loadAssetFile(context, filename);
        this.wordlistFiltered = new ArrayList<>(this.wordlist);
        this.language = language;
    }

    public Dictionary(List<String> wordlist, String language) {
        this.wordlist = wordlist;
        this.wordlistFiltered = new ArrayList<>(this.wordlist);
        this.language = language;
    }

    public void filter(String filter) {
        List<String> tempWordlistFiltered = new ArrayList<>();
        for (String word : wordlistFiltered) {
            if (word.toUpperCase().startsWith(filter.toUpperCase()))
               tempWordlistFiltered.add(word);
        }

        wordlistFiltered = new ArrayList<>(tempWordlistFiltered);
    }

    public void loadAssetFile(Context context, String filename) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(context.getAssets().open(filename))); //throwing a FileNotFoundException?
            String word;
            while((word = br.readLine()) != null)
                this.wordlist.add(word); //break txt file into different words, add to wordList
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                br.close(); //stop reading
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
        return (this.count() == 1) ? wordlistFiltered.get(0) : null;
    }

    public void reset() {
        wordlistFiltered = new ArrayList<>(wordlist);
    }

}
