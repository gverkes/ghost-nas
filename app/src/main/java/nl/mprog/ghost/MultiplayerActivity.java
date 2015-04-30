package nl.mprog.ghost;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

import nl.mprog.ghost.database.UserDbHandler;
import nl.mprog.ghost.models.Dictionary;
import nl.mprog.ghost.models.Game;
import nl.mprog.ghost.models.MultiplayerGame;
import nl.mprog.ghost.models.User;


public class MultiplayerActivity extends Activity {
    public static final String TAG = "MultiPlayerActivity";

    GhostApp ghostApp;
    UserDbHandler dbHandler;

    // Initialize language to english
    public static String currentLanguage;
    ImageButton imgBtnLangEn, imgBtnLangNl;
    EditText txtPlayerOneName, txtPlayerTwoName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_player);

        ghostApp = ((GhostApp) this.getApplication());
        dbHandler = new UserDbHandler(this);

        currentLanguage = ghostApp.LANGUAGE_EN;
        imgBtnLangEn = (ImageButton) findViewById(R.id.imgBtnLangEn);
        imgBtnLangNl = (ImageButton) findViewById(R.id.imgBtnLangNl);
        imgBtnLangEn.setImageResource(R.drawable.language_en_selected);

        txtPlayerOneName = (EditText) findViewById(R.id.txtPlayerOneName);
        txtPlayerTwoName = (EditText) findViewById(R.id.txtPlayerTwoName);
    }

    public void onClickLangEnglish(View view) {
        // if current language is english deselect english
        if (currentLanguage == ghostApp.LANGUAGE_EN) {
            imgBtnLangEn.setImageResource(R.drawable.language_en);
            currentLanguage = ghostApp.LANGUAGE_NONE;
        // if current language is not english select english
        } else {
            imgBtnLangNl.setImageResource(R.drawable.language_nl);
            imgBtnLangEn.setImageResource(R.drawable.language_en_selected);
            currentLanguage = ghostApp.LANGUAGE_EN;
        }
    }

    public void onClickLangDutch(View view) {
        // if current language is dutch deselect dutch
        if (currentLanguage == ghostApp.LANGUAGE_NL) {
            imgBtnLangNl.setImageResource(R.drawable.language_nl);
            currentLanguage = ghostApp.LANGUAGE_NONE;
        // if current language is dutch deselect dutch
        } else {
            imgBtnLangEn.setImageResource(R.drawable.language_en);
            imgBtnLangNl.setImageResource(R.drawable.language_nl_selected);
            currentLanguage = ghostApp.LANGUAGE_NL;
        }
    }

    public void onClickMpStart(View view) {
        if (!currentLanguage.equals(ghostApp.LANGUAGE_NONE)) {
            if (!txtPlayerOneName.getText().equals("")) {
                if (!txtPlayerTwoName.getText().equals("")) {
                    User playerOne = new User(txtPlayerOneName.getText().toString());
                    User playerTwo = new User(txtPlayerTwoName.getText().toString());

                    if (dbHandler.getUserByName(playerOne.getName()) != null) {
                        Log.e(TAG, "onClickMpStart: Username already exists.");
                        return;
                    } else {
                        dbHandler.insert(playerOne);
                    }

                    Dictionary dictionary = new Dictionary(this, currentLanguage, currentLanguage);

                    ghostApp.setGame(new MultiplayerGame(dictionary, playerOne, playerTwo));
                    ghostApp.setGameMode(ghostApp.MULTIPLAYER_MODE);

                    Intent intent = new Intent(this, GameActivity.class);
                    startActivity(intent);
                }
            }
        }
    }
}
