package com.dinesh.sqldatabase;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

public class MainActivity extends AppCompatActivity {
    DatabaseStudentDetails myDB;
    EditText editFirstNmae,editLastName, editRollNumber, editEmailId, editGPA, editid;
    Button   buttonSave;
    Button buttonView;
    Button buttonupdate;
    Button deletebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDB =new DatabaseStudentDetails(this);
        editid = (EditText)findViewById(R.id.updateid);
        editFirstNmae = (EditText)findViewById(R.id.firstName);
        editLastName =(EditText)findViewById(R.id.last);
        editRollNumber =(EditText)findViewById(R.id.rollNumber);
        editEmailId= (EditText)findViewById(R.id.emailid);
        editGPA = (EditText)findViewById(R.id.gpa);
        buttonSave =(Button)findViewById(R.id.button);
        buttonView =(Button)findViewById(R.id.button2);
        deletebtn =(Button)findViewById(R.id.deletebutton);


        buttonupdate =(Button)findViewById(R.id.buttonUpdate);
        AddData();
        ViewAll();
        check();
        DeleteData();



    }
    public void AddData(){
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editFirstNmae.getText().toString().trim();
                String last = editLastName.getText().toString().trim();
                String rollNO = editRollNumber.getText().toString().trim();
                String email = editEmailId.getText().toString().trim();
                String gpa = editGPA.getText().toString().trim();
                boolean isInserted = myDB.insertData( name,last,rollNO,email,gpa);
                if (isInserted == true) {
                    Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(MainActivity.this, "Data is  not  Inserted", Toast.LENGTH_LONG).show();

                }


            }
        });
    }
    public void DeleteData(){
        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deleterows = myDB.deleteData(editid.getText().toString());
                if (deleterows > 0){
                    Toast.makeText(MainActivity.this, "Data deletd", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(MainActivity.this, "Data is  not  deleted", Toast.LENGTH_LONG).show();


                }
            }
        });
    }
    public void ViewAll(){
        buttonView.setOnClickListener(new View.OnClickListener() {
            public static final String TAG = "student data" ;

            @Override
            public void onClick(View v) {

//               boolean result = myDB.insertData("Dinesh","Pogula","700642649","dineshpogula@gmail.com","4.0");
                Cursor res =myDB.getAllData();

                StringBuffer buffer = new StringBuffer();
//                check();
              if(res.getCount()==0){
                  showMessage("Error","Nothing found");
                  return;
              }


                while (res.moveToNext()){
                    buffer.append("Id :"+res.getString(0)+"\n");
                    buffer.append("FirstName :"+res.getString(1)+"\n");
                    buffer.append("LastName :"+res.getString(2)+"\n");
                    buffer.append("RollNumber :"+res.getString(3)+"\n");
                    buffer.append("EmailId :"+res.getString(4)+"\n");
                    buffer.append("GPA :"+res.getString(5)+"\n\n");
                }
                showMessage("Student Data",buffer.toString());

//                Log.i(TAG, buffer.toString());
            }
        });
    }
    public void check(){
        buttonupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdated = myDB.updateData(editid.getText().toString(),editFirstNmae.getText().toString(),editLastName.getText().toString(),
                        editRollNumber.getText().toString(),editEmailId.getText().toString(),editGPA.getText().toString());
                if (isUpdated == true) {
                    Toast.makeText(MainActivity.this, "Data updated", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(MainActivity.this, "Data is  not  updated", Toast.LENGTH_LONG).show();

                }
            }
        });

    }
    public  void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }


    @Override
    protected void onStart() {
        super.onStart();

    }
}
