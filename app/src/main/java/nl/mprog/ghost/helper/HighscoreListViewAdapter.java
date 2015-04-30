package nl.mprog.ghost.helper;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import nl.mprog.ghost.R;

/**
 * Created by govert on 4/29/15.
 */
public class HighscoreListViewAdapter extends BaseAdapter {

    public ArrayList<HashMap<String, String>> highscoreList;
    Context context;

    public HighscoreListViewAdapter(Context context, ArrayList<HashMap<String, String>> highscoreList) {
        super();
        this.context = context;
        this.highscoreList = highscoreList;
    }


    @Override
    public int getCount() {
        return highscoreList.size();
    }

    @Override
    public Object getItem(int position) {
        return highscoreList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View highscoreRowView = inflater.inflate(R.layout.listview_highscore, parent, false);

        TextView txtUsername = (TextView) highscoreRowView.findViewById(R.id.txtUsername);
        TextView txtScore = (TextView) highscoreRowView.findViewById(R.id.txtScore);

        txtUsername.setText(highscoreList.get(position).get("name"));
        txtScore.setText(highscoreList.get(position).get("score"));

        return highscoreRowView;
    }
}
