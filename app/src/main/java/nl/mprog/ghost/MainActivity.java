package nl.mprog.ghost;
/**
 * Created by Govert on 4/21/15.
 * MainActivity, this activity contains the home screen,
 * from this activity you navigate to:
 *    - SinglePlayerActivity
 *    - MultiPlayerActivity
 *    - SettingActivity
 *    - HighscoresActivity
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import nl.mprog.ghost.database.UserDbHandler;
import nl.mprog.ghost.models.User;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickSingleplayer(View view) {
        Intent intent = new Intent(this, SingleplayerActivity.class);
        startActivity(intent);
    }

    public void onClickMultiplayer(View view) {
        Intent intent = new Intent(this, MultiplayerActivity.class);
        startActivity(intent);
    }

    public void onClickSettings(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void onClickHighscores(View view) {
        Intent intent = new Intent(this, HighscoreActivity.class);
        startActivity(intent);
    }
}
