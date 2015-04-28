package nl.mprog.ghost;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import nl.mprog.ghost.models.Dictionary;
import nl.mprog.ghost.models.Game;
import nl.mprog.ghost.models.MultiplayerGame;
import nl.mprog.ghost.models.User;


public class MultiplayerActivity extends Activity {
    public static final String TAG = "MultiPlayerActivity";

    public static final String LANGUAGE_NONE = "";
    public static final String LANGUAGE_EN = "en";
    public static final String LANGUAGE_NL = "nl";

    GhostApp ghostApp;

    // Initialize language to english
    public static String currentLanguage = LANGUAGE_EN;
    ImageButton imgBtnLangEn, imgBtnLangNl;
    EditText txtPlayerOneName, txtPlayerTwoName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_player);

        ghostApp = ((GhostApp) this.getApplication());

        imgBtnLangEn = (ImageButton) findViewById(R.id.imgBtnLangEn);
        imgBtnLangNl = (ImageButton) findViewById(R.id.imgBtnLangNl);
        imgBtnLangEn.setImageResource(R.drawable.language_en_selected);

        txtPlayerOneName = (EditText) findViewById(R.id.txtPlayerOneName);
        txtPlayerTwoName = (EditText) findViewById(R.id.txtPlayerTwoName);
    }

    public void onClickLangEnglish(View view) {
        // if current language is english deselect english
        if (currentLanguage == LANGUAGE_EN) {
            imgBtnLangEn.setImageResource(R.drawable.language_en);
            currentLanguage = LANGUAGE_NONE;
        // if current language is not english select english
        } else {
            imgBtnLangNl.setImageResource(R.drawable.language_nl);
            imgBtnLangEn.setImageResource(R.drawable.language_en_selected);
            currentLanguage = LANGUAGE_EN;
        }
    }

    public void onClickLangDutch(View view) {
        // if current language is dutch deselect dutch
        if (currentLanguage == LANGUAGE_NL) {
            imgBtnLangNl.setImageResource(R.drawable.language_nl);
            currentLanguage = LANGUAGE_NONE;
        // if current language is dutch deselect dutch
        } else {
            imgBtnLangEn.setImageResource(R.drawable.language_en);
            imgBtnLangNl.setImageResource(R.drawable.language_nl_selected);
            currentLanguage = LANGUAGE_NL;
        }
    }

    public void onClickMpStart(View view) {
        if (!currentLanguage.equals(LANGUAGE_NONE)) {
            if (!txtPlayerOneName.getText().equals("")) {
                if (!txtPlayerTwoName.getText().equals("")) {
                    List<User> players = new ArrayList<>();
                    players.add(new User(txtPlayerOneName.getText().toString()));
                    players.add(new User(txtPlayerTwoName.getText().toString()));

                    Dictionary dictionary = new Dictionary(this, currentLanguage, currentLanguage);

                    Game game = new MultiplayerGame(dictionary, players);
                    ghostApp.setGame(game);
                    ghostApp.setGameMode(ghostApp.MULTIPLAYER_MODE);

                    Intent intent = new Intent(this, GameActivity.class);
                    startActivity(intent);
                }
            }
        }
    }
}
