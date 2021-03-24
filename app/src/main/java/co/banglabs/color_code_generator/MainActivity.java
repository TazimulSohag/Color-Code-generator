package co.banglabs.color_code_generator;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    View vColor;
    TextView tvColor, tvValue;
    SeekBar sbRed, sbGreen, sbBlue;
    int red = 0, green = 0, blue = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vColor = findViewById(R.id.v_color);
        tvColor = findViewById(R.id.tv_code);
        tvValue = findViewById(R.id.tv_value);
        sbRed = findViewById(R.id.sb_red);
        sbGreen = findViewById(R.id.sb_green);
        sbBlue = findViewById(R.id.sb_blue);


        AdView mAdView = findViewById(R.id.adView);
        MobileAds.initialize(this, "ca-app-pub-3940256099942544/6300978111");
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);




        sbRed.setOnSeekBarChangeListener(this);
        sbGreen.setOnSeekBarChangeListener(this);
        sbBlue.setOnSeekBarChangeListener(this);


    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

        switch (seekBar.getId()) {
            case R.id.sb_red:
                red = i;
                break;


            case R.id.sb_green:
                green = i;
                break;

            case R.id.sb_blue:
                blue = i;
                break;


        }

        vColor.setBackgroundColor(Color.rgb(red,green,blue));
        String code = HexCOde(red,green,blue);

        tvColor.setText(code.toUpperCase());
        tvValue.setText(String.format("RGB (%d, %d, %d)",red,green,blue));


    }

    private String HexCOde(int red, int green, int blue) {

        String code;
        code ="#";
        code += Integer.toHexString(red);
        code += Integer.toHexString(green);
        code += Integer.toHexString(blue);

        return code;

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}