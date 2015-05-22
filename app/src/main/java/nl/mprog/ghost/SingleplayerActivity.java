package nl.mprog.ghost;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
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

                if (dictionary.count() == 0) {

                    Toast toast = Toast.makeText(this, "Couldn't load dictionary, try reinstalling the app", Toast.LENGTH_LONG);
                    toast.show();

                } else {

                    ghostApp.setGame(new SingleplayerGame(dictionary, player, SingleplayerGame.DIFFICULTY_HARD));
                    ghostApp.setGameMode(ghostApp.SINGLEPLAYER_MODE);

                    Intent intent = new Intent(this, GameActivity.class);
                    startActivity(intent);

                }
            }
        }
    }
}
