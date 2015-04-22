package nl.mprog.ghost;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import nl.mprog.ghost.database.UserDbHandler;


public class HighscoreActivity extends Activity {
    public ArrayList<HashMap<String, String>> userList;
    TextView txt1, txt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        txt1 = (TextView) findViewById(R.id.txt1);
        txt2 = (TextView) findViewById(R.id.txt2);

    }

    @Override
    protected void onResume() {
        super.onResume();
        UserDbHandler dbHandler = new UserDbHandler(this);
        userList = dbHandler.getUserList();

        String names = "";
        for (HashMap<String, String> map : userList) {
            names += map.get("name");
        }

        txt1.setText(names);
    }


}
