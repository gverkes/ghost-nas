package nl.mprog.ghost;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.Toast;

import nl.mprog.ghost.database.UserDbHandler;
import nl.mprog.ghost.models.Dictionary;
import nl.mprog.ghost.models.SingleplayerGame;
import nl.mprog.ghost.models.User;


public class SingleplayerActivity extends Activity {
    public static final String TAG = "SinglePlayerActivity";

    GhostApp ghostApp;
    UserDbHandler dbHandler;

    public static String currentLanguage;
    ImageButton imgBtnLangEn, imgBtnLangNl;
    EditText txtPlayerName;
    RatingBar rtbDifficulty;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_player);

        ghostApp = (GhostApp) getApplication();
        dbHandler = new UserDbHandler(this);

        // Initialize language on english
        currentLanguage = ghostApp.LANGUAGE_EN;
        imgBtnLangEn = (ImageButton) findViewById(R.id.imgBtnLangEn);
        imgBtnLangNl = (ImageButton) findViewById(R.id.imgBtnLangNl);
        imgBtnLangEn.setImageResource(R.drawable.language_en_selected);

        txtPlayerName = (EditText) findViewById(R.id.txtPlayerName);

        rtbDifficulty = (RatingBar) findViewById(R.id.rtbDifficulty);

    }

    public void onClickLangEnglish(View view) {
        // if current language is english change it to dutch by clicking on english button
       if (currentLanguage == ghostApp.LANGUAGE_EN) {
           imgBtnLangEn.setImageResource(R.drawable.language_en);
           currentLanguage = ghostApp.LANGUAGE_NONE;

       } else {
           imgBtnLangNl.setImageResource(R.drawable.language_nl);
           imgBtnLangEn.setImageResource(R.drawable.language_en_selected);
           currentLanguage = ghostApp.LANGUAGE_EN;
       }
    }

    public void onClickLangDutch(View view) {
        // if current language is dutch change it to english by clicking on dutch button
        if (currentLanguage == ghostApp.LANGUAGE_NL) {
            imgBtnLangNl.setImageResource(R.drawable.language_nl);
            currentLanguage = ghostApp.LANGUAGE_NONE;
        } else {
            imgBtnLangEn.setImageResource(R.drawable.language_en);
            imgBtnLangNl.setImageResource(R.drawable.language_nl_selected);
            currentLanguage = ghostApp.LANGUAGE_NL;
        }
    }

    public void onClickSpStart(View view) {
        if (!currentLanguage.equals(ghostApp.LANGUAGE_NONE)) {
            if (!txtPlayerName.getText().toString().equals("")) {
                User player = new User(txtPlayerName.getText().toString());

                if (dbHandler.getUserByName(player.getName()) != null) {
                    Log.e(TAG, "onClickMpStart: Username for player one already exists.");
                    player= dbHandler.getUserByName(player.getName());
                } else {
                    dbHandler.insert(player);
                }

                Dictionary dictionary;

                if (ghostApp.getGame() != null &&
                        ghostApp.getGame().getDictionary().getLanguage() == currentLanguage) {
                    dictionary = ghostApp.getGame().getDictionary();
                    dictionary.reset();
                } else {
                    dictionary = new Dictionary(this, currentLanguage, currentLanguage);
                }

                // Check if dictionary is correctly loaded
                if (dictionary.count() == 0) {
                    Toast toast = Toast.makeText(this, "Couldn't load dictionary, try reinstalling the app", Toast.LENGTH_LONG);
                    toast.show();

                } else {

                    // Determine selected difficulty and use it to create a new SingeplayerGame
                    int difficulty;
                    switch ((int)rtbDifficulty.getRating()) {
                        case SingleplayerGame.DIFFICULTY_EASY:
                            difficulty = SingleplayerGame.DIFFICULTY_EASY;
                            break;
                        case SingleplayerGame.DIFFICULTY_NORMAL:
                            difficulty = SingleplayerGame.DIFFICULTY_NORMAL;
                            break;
                        case SingleplayerGame.DIFFICULTY_HARD:
                            difficulty = SingleplayerGame.DIFFICULTY_HARD;
                            break;
                        default:
                            difficulty = SingleplayerGame.DIFFICULTY_EASY;
                    }

                    ghostApp.setGame(new SingleplayerGame(dictionary, player, difficulty));
                    ghostApp.setGameMode(ghostApp.SINGLEPLAYER_MODE);

                    // Start the actual Game Activity
                    Intent intent = new Intent(this, GameActivity.class);
                    startActivity(intent);

                }
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.
                INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        return true;
    }

    public void onClickHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
