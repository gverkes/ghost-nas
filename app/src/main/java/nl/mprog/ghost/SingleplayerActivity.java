package nl.mprog.ghost;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


public class SingleplayerActivity extends Activity {
    public static final String LANGUAGE_NONE = "";
    public static final String LANGUAGE_EN = "en";
    public static final String LANGUAGE_NL = "nl";

    // Initialize language on english
    public static String currentLanguage = LANGUAGE_EN;
    ImageButton imgBtnLangEn, imgBtnLangNl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_player);

        imgBtnLangEn = (ImageButton) findViewById(R.id.imgBtnLangEn);
        imgBtnLangNl = (ImageButton) findViewById(R.id.imgBtnLangNl);
        imgBtnLangEn.setImageResource(R.drawable.language_en_selected);
    }

    public void onClickLangEnglish(View view) {
        // if current language is english change it to dutch by clicking on english button
       if (currentLanguage == LANGUAGE_EN) {
           imgBtnLangEn.setImageResource(R.drawable.language_en);
           currentLanguage = LANGUAGE_NONE;

       } else {
           imgBtnLangNl.setImageResource(R.drawable.language_nl);
           imgBtnLangEn.setImageResource(R.drawable.language_en_selected);
           currentLanguage = LANGUAGE_EN;
       }
    }

    public void onClickLangDutch(View view) {
        // if current language is dutch change it to english by clicking on dutch button
        if (currentLanguage == LANGUAGE_NL) {
            imgBtnLangNl.setImageResource(R.drawable.language_nl);
            currentLanguage = LANGUAGE_NONE;
        } else {
            imgBtnLangEn.setImageResource(R.drawable.language_en);
            imgBtnLangNl.setImageResource(R.drawable.language_nl_selected);
            currentLanguage = LANGUAGE_NL;
        }
    }
}
