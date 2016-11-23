package com.example.hp.mapdatabase;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivityCont extends Activity {

    MainPage mp;

    String name,name1,name2;
    String cNumber1,cNumber2,cNumber3;
    Intent intent;
    ImageButton btn;
    EditText et1,et2,et3;
    DatabaseHelper db;
    TextView tv1,tv2,tv3;
    ImageButton btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity_cont);

        db = new DatabaseHelper(this);

        btn=(ImageButton)findViewById(R.id.imageButton2);



        et1=(EditText)findViewById(R.id.editText);
        et2=(EditText)findViewById(R.id.editText2);
        et3=(EditText)findViewById(R.id.editText3);

        btn1=(ImageButton)findViewById(R.id.imageButton3);

        tv1=(TextView)findViewById(R.id.Textt1);
        tv2=(TextView)findViewById(R.id.Textt2);
        setData();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et1.getText().toString().isEmpty()) {
                    et1.setError("Please Select a Contact");
                } else if (et2.getText().toString().isEmpty()) {
                    et2.setError("Please Select Contacts");
                } else if (et3.getText().toString().isEmpty()) {
                    et3.setError("Please Select Contacts");

                } else {
                    //           sendData();
                   // MapAct();
                    Intent intent = new Intent(MainActivityCont.this, MainPage.class);
                    startActivity(intent);

                }
            }
        });
    }

    public void searchcontacts(View view){

        Intent intent=new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(intent, 1);

    }

    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);
/*
        switch (reqCode) {
            case (1) :
                if (resultCode == Activity.RESULT_OK) {
                    Uri contactData = data.getData();

                      Cursor c=managedQuery(contactData,null,null,null,null);

                    while(c.moveToNext()){
                        if (et1.getText().toString().isEmpty()) {

                             name = c.getString(c.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME));
                            //String number=c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                            et1.setText(name);
                            //et1.setText(number);
                            et2.requestFocus();

                        }  else if (et2.getText().toString().isEmpty()) {

                             name1 = c.getString(c.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME));
                            //String number=c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                            et2.setText(name1);
                            //et2.setText(number);

                            et3.requestFocus();

                        }  else if (et3.getText().toString().isEmpty()) {

                             name2 = c.getString(c.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME));
                            //String number=c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                            et3.setText(name2);
                            //et3.setText(number);

                        }

                    }
                }
                break;
        }*/


        switch (reqCode) {
            case (1) :
                if (resultCode == Activity.RESULT_OK) {

                    Uri contactData = data.getData();
                    Cursor c =  managedQuery(contactData, null, null, null, null);
                    while (c.moveToNext()) {


                        String id =c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts._ID));
                        String hasPhone = c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));

                        Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = "+id, null, null);

                        //phones.moveToFirst();
                        while(phones.moveToNext()) {
                            if (et1.getText().toString().isEmpty()) {
                                 cNumber1 = phones.getString(phones.getColumnIndex("data1"));
                                String name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                                et1.setText(name);
                                et2.requestFocus();
                                break;
                            }
                             else if (et2.getText().toString().isEmpty()) {
                                 cNumber2 = phones.getString(phones.getColumnIndex("data1"));
                                String name1 = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                                et2.setText(name1);
                                et3.requestFocus();
                                break;
                            }
                             else if(et3.getText().toString().isEmpty())
                            {
                                 cNumber3 = phones.getString(phones.getColumnIndex("data1"));
                                String name2 = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                                et3.setText(name2);

                            }

                        }

                    }

                }
                break;
        }

    }

    public void setData(){

        Cursor c=db.getAllData();
        while(c.moveToNext()){
            tv1.setText(c.getString(0));
            tv2.setText(c.getString(2));

        }
    }

   /*public void MapAct(){
        intent=new Intent(MainActivityCont.this,MapsActivity.class);
        intent.putExtra("n1",cNumber1);
        intent.putExtra("n2",cNumber2);
        intent.putExtra("n3",cNumber3);
        startActivity(intent);

    }*/

    /*public void sendData(){
        Intent i=new Intent();
        i.putExtra("n1",name);
        i.putExtra("n2",name1);
        i.putExtra("n3",name2);
       // startActivity(i);
    }*/

}
