package nl.mprog.ghost;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import nl.mprog.ghost.database.UserDbHandler;
import nl.mprog.ghost.models.Game;
import nl.mprog.ghost.models.User;

public class GameActivity extends Activity {
    public static final String TAG = "GameActivity";

    GhostApp ghostApp;

    UserDbHandler dbHandler;

    Game game;

    TextView txtGuessWord, txtVersus, txtTurnPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        ghostApp = (GhostApp) this.getApplication();

        dbHandler = new UserDbHandler(this);

        game = ghostApp.getGame();

        txtGuessWord = (TextView) findViewById(R.id.txtGuessWord);
        txtTurnPlayer = (TextView) findViewById(R.id.txtTurnPlayer);
        txtVersus = (TextView) findViewById(R.id.txtVersus);

        txtVersus.setText(game.getPlayersNames()[0] + getString(R.string.versus_msg) + game.getPlayersNames()[1]);

        setTurnTxt();

    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        String inputString = "";
        if (event.getAction()==KeyEvent.ACTION_UP) inputString = String.valueOf((char)event.getUnicodeChar()).toUpperCase();
        else if (event.getAction()==KeyEvent.ACTION_MULTIPLE) inputString = String.valueOf(event.getCharacters()).toUpperCase();

        // Do not guess when game already ended, and check if inputChar matches a letter
        if (!game.ended() && inputString.matches("\\p{L}")) {
            nextTurn(inputString.charAt(0));
            Log.v(TAG, "dispatchKeyEvent:" + inputString.charAt(0));
        }

        return super.dispatchKeyEvent(event);
    }

    public void nextTurn(char guessChar) {
        game.guess(guessChar);

        txtGuessWord.setText(game.getGuessWord());

        setTurnTxt();

        // Update text messages when game has ended, also update users in database
        if (game.ended()) {
            txtVersus.setText(game.getWinnerName() + getString(R.string.won_msg));

            // Based on the win reason, output a correct message
            if (game.getWinReason() == Game.WIN_REASON_NOWORDS)
                txtTurnPlayer.setText(getString(R.string.no_words_left));
            else if (game.getWinReason() == Game.WIN_REASON_WORDMADE)
                txtTurnPlayer.setText(getString(R.string.correct_word_made));

            // Update all users in database
            for (User player : game.getPlayers()) {
                dbHandler.update(player);
            }

            // Reset game
            game.reset();

            final Dialog dialog = new Dialog(this);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            dialog.setContentView(R.layout.replay_popup);

            ImageButton btnDialogReplay = (ImageButton) dialog.findViewById(R.id.imgBtnReplay);
            // if button is clicked, close the custom dialog
            btnDialogReplay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickReplay(v);
                    dialog.dismiss();
                }
            });

            dialog.show();

        }
    }

    public void setTurnTxt() {
        if (ghostApp.getGameMode() == GhostApp.SINGLEPLAYER_MODE) {
            txtTurnPlayer.setText(getString(R.string.turn_msg_singleplayer));

        } else {
            // check if name ends with s to determine correct spelling
            if (game.getTurnPlayerName().endsWith("s"))
                txtTurnPlayer.setText(game.getTurnPlayerName() + getString(R.string.turn_msg_with_s));
            else
                txtTurnPlayer.setText(game.getTurnPlayerName() + getString(R.string.turn_msg_without_s));
        }
    }

    public void onClickHome(View view) {
        Log.e(TAG, "clicked home?");
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onClickReplay(View view) {
        game.reset();

        txtGuessWord.setText("");

        txtVersus.setText(game.getPlayersNames()[0] + getString(R.string.versus_msg) + game.getPlayersNames()[1]);

        setTurnTxt();

    }
}
