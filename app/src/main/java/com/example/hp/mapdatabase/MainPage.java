package com.example.hp.mapdatabase;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainPage extends Activity {
    ImageButton imgbtn;
    DatabaseHelper db1;
    TextView tv1,tv2,tv3;

    Button vb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_mainpage);

        db1 = new DatabaseHelper(this);

        vb=(Button)findViewById(R.id.button22);

        tv1=(TextView)findViewById(R.id.Texttt1);
        tv2=(TextView)findViewById(R.id.Texttt2);
        tv3 = (TextView) findViewById(R.id.Texttt3);
        imgbtn = (ImageButton) findViewById(R.id.imageButton1);

        boolean isInserted = db1.insertRegId(tv3.getText().toString());
        if (isInserted == true) {
            Toast.makeText(MainPage.this, "Data Inserted", Toast.LENGTH_SHORT).show();
        }
        setData1();
        //showNum();

    }

    public void addListenerOnButton(View view) {
        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainPage.this, "Image Button Clciked", Toast.LENGTH_SHORT).show();

                final Intent i=getIntent();
                String n1=i.getStringExtra("n1");
                String n2=i.getStringExtra("n2");
                String n3=i.getStringExtra("n3");
                String add=i.getStringExtra("Add");

                sendSMS("",add);
                sendSMS("",add);
                sendSMS("",add);

            }
        });

}
    public void sendSMS(String PhnNum,String msg){
        SmsManager sms=SmsManager.getDefault();
        sms.sendTextMessage(PhnNum,null,msg,null,null);
    }
    public void setData1() {

        Cursor c = db1.getAllData();
        while (c.moveToNext()) {
            tv1.setText(c.getString(0));
            tv2.setText(c.getString(2));
            tv3.setText(c.getString(6));
            //tv2.setText(c.getString(2));

        }
    }

/*    public void showNum(View view){
        vb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainPage.this, "cdnjfnjdsfndsmfmlf", Toast.LENGTH_SHORT).show();

               final Intent i=getIntent();
                //final Intent i1=new Intent();
                //final Intent i2=new Intent();
                String n1=i.getStringExtra("n1");
                String n2=i.getStringExtra("n2");
                String n3=i.getStringExtra("n3");
                String add=i.getStringExtra("Add");
                tv1.setText(n1);
                tv2.setText(n2);
                tv3.setText(n3);

            }
        });
    }
*/
}

