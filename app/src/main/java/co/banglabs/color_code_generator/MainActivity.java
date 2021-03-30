package co.banglabs.color_code_generator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    View vColor;
    TextView tvColor, tvValue;
    SeekBar sbRed, sbGreen, sbBlue;
    int red = 0, green = 0, blue = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Objects.requireNonNull(getSupportActionBar()).setTitle("");

        vColor = findViewById(R.id.v_color);
        tvColor = findViewById(R.id.tv_code);
        tvValue = findViewById(R.id.tv_value);
        sbRed = findViewById(R.id.sb_red);
        sbGreen = findViewById(R.id.sb_green);
        sbBlue = findViewById(R.id.sb_blue);


        AdView mAdView = findViewById(R.id.adView);
        MobileAds.initialize(this, "ca-app-pub-7419624357060010/1518124246");
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

        vColor.setBackgroundColor(Color.rgb(red, green, blue));
        String code = HexCOde(red, green, blue);

        tvColor.setText(code.toUpperCase());
        tvValue.setText(String.format("RGB (%d, %d, %d)", red, green, blue));


    }

    private String HexCOde(int red, int green, int blue) {

        String code;
        code = "#";
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



    // on back press
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MainActivity.this.finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_files, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.share_id:
                try {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                    String shareMessage = "if you like,share this app";
                    shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=co.banglabs.color_code_generator ";
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    startActivity(Intent.createChooser(shareIntent, "choose one"));

                } catch (Exception e) {
                    //e.toString();
                }

                break;


            case R.id.rate_id:

                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=co.banglabs.color_code_generator")));


                break;
        }


        return super.onOptionsItemSelected(item);
    }










}