package nl.mprog.ghost;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import nl.mprog.ghost.models.Game;
import nl.mprog.ghost.models.MultiplayerGame;

public class GameActivity extends Activity {
    public static final String TAG = "GameActivity";

    GhostApp ghostApp;

    Game game;

    TextView txtGuessWord, txtVersus, txtWordsLeft, txtTurnPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        ghostApp = (GhostApp) this.getApplication();

        game = ghostApp.getGame();

        txtGuessWord = (TextView) findViewById(R.id.txtGuessWord);
        txtTurnPlayer = (TextView) findViewById(R.id.txtTurnPlayer);
        txtVersus = (TextView) findViewById(R.id.txtVersus);

        txtVersus.setText(game.getVersusMessage());

    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        String inputString = "";
        if (event.getAction()==KeyEvent.ACTION_UP) inputString = String.valueOf((char)event.getUnicodeChar()).toUpperCase();
        else if (event.getAction()==KeyEvent.ACTION_MULTIPLE) inputString = String.valueOf(event.getCharacters()).toUpperCase();

        // Check if inputChar matches a letter
        if (inputString.matches("\\p{L}")) {
            guess(inputString.charAt(0));
            Log.v(TAG, "dispatchKeyEvent:" + inputString.charAt(0));
        }

        return super.dispatchKeyEvent(event);
    }

    public void guess(char guessChar) {
        game.guess(guessChar);

        txtGuessWord.setText(game.getGuessWord());
        txtTurnPlayer.setText(game.getTurnPlayerMessage());

        if (game.ended()) {
            txtVersus.setText(game.getWinMessage());
        }
    }

    public void onClickActivity(View view) {

    }
}
