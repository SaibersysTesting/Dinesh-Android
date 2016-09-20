package com.dinesh.testapp2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.AnalogClock;
import android.widget.Button;
import android.widget.DigitalClock;
import android.widget.EditText;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    private static Button button;
    private static AnalogClock analogClock;
    private static DigitalClock digitalClock;
    private static WebView browsers;
    private static EditText url_google;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        onswapbutton();
    }
    public void onswapbutton(){
        button=(Button)findViewById(R.id.button2);
//        analogClock= (AnalogClock)findViewById(R.id.analogClock);
        digitalClock=(DigitalClock)findViewById(R.id.digitalClock);
        browsers = (WebView) findViewById(R.id.webView);
        url_google = (EditText)findViewById(R.id.editText2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = url_google.getText().toString();
                browsers.getSettings().setLoadsImagesAutomatically(true);
                browsers.getSettings().setJavaScriptEnabled(true);
                browsers.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
                browsers.loadUrl(url);
//                if(digitalClock.getVisibility()== digitalClock.GONE){
//                    digitalClock.setVisibility(DigitalClock.VISIBLE);
//                    analogClock.setVisibility(AnalogClock.GONE);
//                }else {
//                    digitalClock.setVisibility(DigitalClock.GONE);
//                    analogClock.setVisibility(AnalogClock.VISIBLE);
//                }

            }
        });
    }
}
