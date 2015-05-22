package nl.mprog.ghost.models;
/**
 * Created by Govert on 4/21/15.
 * Basic dictionary class
 */

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;



public class Dictionary {
    public HashSet<String> wordlist;
    public HashSet<String> wordlistFiltered;
    private StringBuffer filterString;
    private String language;

    public Dictionary(Context context, String filename, String language) {
        this.wordlist = new HashSet<>();
        this.loadAssetFile(context, filename);
        this.wordlistFiltered = new HashSet<>(this.wordlist);
        this.filterString = new StringBuffer();
        this.language = language;
    }

    public Dictionary(HashSet<String> wordlist, String language) {
        this.wordlist = wordlist;
        this.wordlistFiltered = new HashSet<>(this.wordlist);
        this.language = language;
    }

    public void addCharFilter(char addedChar) {
        filterString.append(addedChar);

        HashSet<String> tempWordlistFiltered = new HashSet<>();
        for (String word : wordlistFiltered) {
            if (word.toUpperCase().startsWith(filterString.toString().toUpperCase())) {
                // If the filter has an exact match return only exact match and break out of loop
                if (filterString.length() > 3 &&
                        word.toUpperCase().equals(filterString.toString().toUpperCase())) {
                    tempWordlistFiltered.clear();
                    tempWordlistFiltered.add(word);
                    break;
                }
                tempWordlistFiltered.add(word);
            }
        }
        wordlistFiltered = new HashSet<>(tempWordlistFiltered);
    }

    public HashMap<Character, int[]> possibleFilters() {

        StringBuffer tempFilterString = new StringBuffer(filterString);
        HashMap<Character, int[]> possibleFilters = new HashMap<>();

        for(char filterChar = 'A'; filterChar <= 'Z'; filterChar++) {

            tempFilterString.append(filterChar);
            possibleFilters.put(filterChar, new int[] {0, 0});

            int wordsLeft = 0, oddLengthWordsLeft = 0;

            for (String word : wordlistFiltered) {
                if (word.toUpperCase().startsWith(tempFilterString.toString().toUpperCase())) {
                    // If the filter has an exact match return only exact match and break out of loop
                    if (filterString.length() > 3 &&
                            word.toUpperCase().equals(tempFilterString.toString().toUpperCase())) {
                        wordsLeft = 1;
                        break;
                    }

                    // If word has odd letters left after filter increment counter,
                    // these words put you in a winnable position
                    if (((word.length() - tempFilterString.length()) & 1) != 0) {
                        oddLengthWordsLeft++;
                    }
                    wordsLeft++;
                }
            }

            possibleFilters.put(filterChar, new int[] {wordsLeft, oddLengthWordsLeft});

            tempFilterString.deleteCharAt(tempFilterString.length() - 1);

        }

        return possibleFilters;
    }

    public void loadAssetFile(Context context, String filename) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(context.getAssets().open(filename)));
            String word;
            while((word = br.readLine()) != null)
                this.wordlist.add(word); //break txt file into different words, add to wordList
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                br.close(); // Close file, when done reading
            }
            catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public int count() {
        return wordlistFiltered.size();
    }

    public String getFilterString() {
       return filterString.toString();
    }

    public String getLanguage() {
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
        // reset filter string and filtered wordlist
        filterString.delete(0, filterString.length());
        wordlistFiltered = new HashSet<>(wordlist);
    }

}
