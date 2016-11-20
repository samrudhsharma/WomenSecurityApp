package com.example.hp.mapdatabase;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends Activity {
    EditText editText, editText2, editText3, editText4, editText5;
    Button button, button2, button3, button4;
    RadioGroup radioGroup;
    RadioButton radioButton;
    DatabaseHelper myDb;
    MainActivityCont mac;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        //mac=new MainActivityCont();
        myDb = new DatabaseHelper(this);
        editText = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);
        editText3 = (EditText) findViewById(R.id.editText3);
        editText4 = (EditText) findViewById(R.id.editText4);
        button = (Button) findViewById(R.id.button);
        editText5 = (EditText) findViewById(R.id.editText5);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        button2=(Button)findViewById(R.id.button2);

        addData();
  //      viewAll();
    }


    public void addData()
    {

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selectedid = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(selectedid);


                if (editText.getText().toString().isEmpty() || editText.length() < 3 ) {
                    editText.setError("Name should not be empty , should not contain any spaces and must be of atleast three characters");
                }
                else if(editText2.getText().toString().isEmpty()||!editText2.getText().toString().contains("@")||!editText2.getText().toString().contains(".")){

                    editText2.setError("Please Enter the valid Email Address");
                }
                else if(editText3.getText().toString().isEmpty()||editText3.length()<10){
                    editText3.setError("Please enter the 10 digit valid mobile number");

                }
                else if(editText4.getText().toString().isEmpty()) {

                    editText4.setError("Please enter the correct address");
                }

                else if(editText5.getText().toString().isEmpty()||!(editText5.getText().toString().contains("-"))){
                        editText5.setError("Please enter the Correct Date Of Birth");
                    }

                else
                {
                boolean isInserted = myDb.insertData(editText.getText().toString(), editText2.getText().toString(), editText3.getText().toString(), radioButton.getText().toString() , editText4.getText().toString() , editText5.getText().toString());
                if (isInserted == true) {
                    Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
//                    setVisible(false);
                  //  mac.setVisible(true);
                    Intent intent=new Intent(MainActivity.this,MainActivityCont.class);
                    startActivity(intent);

                }
                else
                    Toast.makeText(MainActivity.this, "Data not Inserted", Toast.LENGTH_SHORT).show();
            }
            }
        });

    }

    public void viewAll()
    {
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDb.getAllData();
                if (res.getCount() == 0) {
                    showMessage("error", "nothing found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {

                    buffer.append("Name: " + res.getString(0) + "\n");
                    buffer.append("Email: " + res.getString(1) + "\n");
                    buffer.append("Phone: " + res.getString(2) + "\n");
                    buffer.append("Sex: " + res.getString(3) + "\n");
                    buffer.append("Address: " + res.getString(4) + "\n");
                    buffer.append("Date of birth: " + res.getString(5) + "\n");
                }
                showMessage("data", buffer.toString());
            }
        });
    }

    public void showMessage(String title, String Message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}