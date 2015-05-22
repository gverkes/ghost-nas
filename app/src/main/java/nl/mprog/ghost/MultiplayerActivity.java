package nl.mprog.ghost;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


import nl.mprog.ghost.database.UserDbHandler;
import nl.mprog.ghost.models.Dictionary;
import nl.mprog.ghost.models.MultiplayerGame;
import nl.mprog.ghost.models.User;


public class MultiplayerActivity extends Activity {
    public static final String TAG = "MultiPlayerActivity";

    GhostApp ghostApp;
    UserDbHandler dbHandler;

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

        txtPlayerTwoName.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View view, int keyCode, KeyEvent keyevent) {
                //If the keyevent is a key-down event on the "enter" button
                if ((keyevent.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    onClickMpStart(view);
                    return true;
                }
                return false;
            }
        });

        txtPlayerOneName.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View view, int keyCode, KeyEvent keyevent) {
                //If the keyevent is a key-down event on the "enter" button
                if ((keyevent.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    onClickMpStart(view);
                    return true;
                }
                return false;
            }
        });
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
            if (!txtPlayerOneName.getText().toString().equals("")) {
                if (!txtPlayerTwoName.getText().toString().equals("")) {
                    User playerOne = new User(txtPlayerOneName.getText().toString());
                    User playerTwo = new User(txtPlayerTwoName.getText().toString());

                    if (dbHandler.getUserByName(playerOne.getName()) != null) {
                        Log.e(TAG, "onClickMpStart: Username for player one already exists.");
                        playerOne = dbHandler.getUserByName(playerOne.getName());
                    } else {
                        dbHandler.insert(playerOne);
                    }

                    if (dbHandler.getUserByName(playerTwo.getName()) != null) {
                        Log.e(TAG, "onClickMpStart: Username for player two already exists.");
                        playerTwo = dbHandler.getUserByName(playerTwo.getName());
                    } else {
                        dbHandler.insert(playerTwo);
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
                        Toast toast = Toast.makeText(this, getString(R.string.couldnt_load_dicitionary), Toast.LENGTH_LONG);
                        toast.show();
                    } else {


                        ghostApp.setGame(new MultiplayerGame(dictionary, playerOne, playerTwo));
                        ghostApp.setGameMode(ghostApp.MULTIPLAYER_MODE);

                        Intent intent = new Intent(this, GameActivity.class);
                        startActivity(intent);

                    }
                } else {
                    Toast toast = Toast.makeText(this, getString(R.string.please_fill_in_names), Toast.LENGTH_LONG);
                    toast.show();
                }
            } else {
                Toast toast = Toast.makeText(this, getString(R.string.please_fill_in_names), Toast.LENGTH_LONG);
                toast.show();
            }
        } else {
            Toast toast = Toast.makeText(this, getString(R.string.please_select_language), Toast.LENGTH_LONG);
            toast.show();
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
