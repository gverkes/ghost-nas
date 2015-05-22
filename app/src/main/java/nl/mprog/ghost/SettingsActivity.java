package nl.mprog.ghost;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.SeekBar;


public class SettingsActivity extends Activity {
    GhostApp ghostApp;

    SeekBar skbScreenTint;
    RelativeLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ghostApp = (GhostApp) this.getApplication();

        skbScreenTint = (SeekBar) findViewById(R.id.skbScreenTint);
        mainLayout = (RelativeLayout) findViewById(R.id.mainLayout);

        skbScreenTint.setProgress(ghostApp.getBackgroundTintProgress());

        skbScreenTint.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // TODO Auto-generated method stub

                float hue = (float) progress * 3f;
                int backgroundTint = Color.HSVToColor(new float[]{hue, 1.0f, 0.6f});

                mainLayout.setBackgroundTintMode(PorterDuff.Mode.OVERLAY);
                mainLayout.getBackground().setTint(backgroundTint);

                ghostApp.setBackgroundTintEnabled(true);
                ghostApp.setBackgroundTint(backgroundTint);
                ghostApp.setBackgroundTintProgress(progress);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (ghostApp.isBackgroundTintEnabled())
            mainLayout.getBackground().setTint(ghostApp.getBackgroundTint());
    }

    public void onClickHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
