package nl.mprog.ghost;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import nl.mprog.ghost.database.UserDbHandler;
import nl.mprog.ghost.helper.HighscoreListViewAdapter;


public class HighscoreActivity extends Activity {
    private static final String TAG = "HighscoreActivity";
    UserDbHandler dbHandler;

    ListView listView;

    public ArrayList<HashMap<String, String>> userList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);
        dbHandler = new UserDbHandler(this);

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
    }


    public void onClickClearHighscore(View view) {
        dbHandler.clear();
        userList = dbHandler.getUserList();

        HighscoreListViewAdapter adapter = new HighscoreListViewAdapter(this, userList);
        listView.setAdapter(adapter);
    }
}
