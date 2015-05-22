package nl.mprog.ghost;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.HashMap;

import nl.mprog.ghost.database.UserDbHandler;
import nl.mprog.ghost.helper.HighscoreListViewAdapter;


public class HighscoreActivity extends Activity {
    private static final String TAG = "HighscoreActivity";
    UserDbHandler dbHandler;

    GhostApp ghostApp;
    RelativeLayout mainLayout;

    ListView listView;

    public ArrayList<HashMap<String, String>> userList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);
        ghostApp = (GhostApp) this.getApplication();

        dbHandler = new UserDbHandler(this);

        mainLayout = (RelativeLayout) findViewById(R.id.mainLayout);

        userList = dbHandler.getUserList();

        listView = (ListView) findViewById(R.id.highscoreListView);
        HighscoreListViewAdapter adapter = new HighscoreListViewAdapter(this, userList);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        userList = dbHandler.getUserList();

        HighscoreListViewAdapter adapter = new HighscoreListViewAdapter(this, userList);
        listView.setAdapter(adapter);

        if (ghostApp.isBackgroundTintEnabled())
            mainLayout.getBackground().setTint(ghostApp.getBackgroundTint());
    }


    public void onClickClearHighscore(View view) {
        dbHandler.clear();
        userList = dbHandler.getUserList();

        HighscoreListViewAdapter adapter = new HighscoreListViewAdapter(this, userList);
        listView.setAdapter(adapter);
    }

    public void onClickHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
