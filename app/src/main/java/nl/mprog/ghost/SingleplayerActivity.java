package nl.mprog.ghost;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


public class SingleplayerActivity extends Activity {

    GhostApp ghostApp;
    public static String currentLanguage;

    // Initialize language on english
    ImageButton imgBtnLangEn, imgBtnLangNl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_player);

        GhostApp ghostApp = (GhostApp) getApplication();

        currentLanguage = ghostApp.LANGUAGE_EN;
        imgBtnLangEn = (ImageButton) findViewById(R.id.imgBtnLangEn);
        imgBtnLangNl = (ImageButton) findViewById(R.id.imgBtnLangNl);
        imgBtnLangEn.setImageResource(R.drawable.language_en_selected);
    }

    public void onClickLangEnglish(View view) {
        // if current language is english change it to dutch by clicking on english button
       if (currentLanguage == ghostApp.LANGUAGE_EN) {
           imgBtnLangEn.setImageResource(R.drawable.language_en);
           currentLanguage = ghostApp.LANGUAGE_NONE;

       } else {
           imgBtnLangNl.setImageResource(R.drawable.language_nl);
           imgBtnLangEn.setImageResource(R.drawable.language_en_selected);
           currentLanguage = ghostApp.LANGUAGE_EN;
       }
    }

    public void onClickLangDutch(View view) {
        // if current language is dutch change it to english by clicking on dutch button
        if (currentLanguage == ghostApp.LANGUAGE_NL) {
            imgBtnLangNl.setImageResource(R.drawable.language_nl);
            currentLanguage = ghostApp.LANGUAGE_NONE;
        } else {
            imgBtnLangEn.setImageResource(R.drawable.language_en);
            imgBtnLangNl.setImageResource(R.drawable.language_nl_selected);
            currentLanguage = ghostApp.LANGUAGE_NL;
        }
    }
}
