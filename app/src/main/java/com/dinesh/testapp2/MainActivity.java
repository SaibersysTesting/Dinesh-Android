package com.dinesh.testapp2;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private  static RadioGroup radio_g;
    private  static RadioButton radio_b;
    private  static Button button_sbm;
    private static CheckBox checked;
    private static TextView text_v;
    private static RatingBar rating_b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onselectbuttonlistener();
        oncheckboxclicklistener();
        onratingbar();

//        onButtonsetonclicklistener();


    }

    @Override
    protected void onStart() {
        super.onStart();
        onnavigateactivity();
    }

    public void onselectbuttonlistener(){
        radio_g =(RadioGroup)findViewById(R.id.radio_g);
        button_sbm=(Button)findViewById(R.id.button);


        button_sbm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                    int selected_id = radio_g.getCheckedRadioButtonId();
                    radio_b=(RadioButton)findViewById(selected_id);


                    Toast.makeText(MainActivity.this,radio_b.getText().toString(),Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void oncheckboxclicklistener(){
        checked = (CheckBox)findViewById(R.id.checkBox);
        button_sbm=(Button)findViewById(R.id.button);
        button_sbm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuffer result = new StringBuffer();
                result.append("checked all radio buttons").append(checked.isChecked());
                Toast.makeText(MainActivity.this,result.toString(),Toast.LENGTH_LONG).show();

            }
        });

    }
    public void  onratingbar(){

        rating_b=(RatingBar)findViewById(R.id.ratingBar);
        text_v=(TextView)findViewById(R.id.editText);
        rating_b.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                text_v.setText(String.valueOf(rating));
            }
        });

    }
//    public void onButtonsetonclicklistener()
//    {
//        button_sbm =(Button)findViewById(R.id.button);
//        button_sbm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
//                alert.setMessage("If an trouble close this App!!!")
//
//                        .setCancelable(false)
//                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                finish();
//                            }
//                        })
//                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//
//                                dialog.cancel();
//                            }
//                        });
//                AlertDialog alertDialog = alert.create();
//                alertDialog.setTitle("Alert");
//                alertDialog.show();
//
//            }
//
//        });
//    }

    public void onnavigateactivity(){
        button_sbm=(Button)findViewById(R.id.button);
        button_sbm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                try {
                    intent = new Intent(MainActivity.this, Class.forName("com.dinesh.testapp2.Main2Activity"));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                startActivity(intent);
            }
        });
    }
}