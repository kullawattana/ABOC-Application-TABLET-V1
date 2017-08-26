package com.example.suttipongk.testaboc;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;

import android.app.Dialog;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by TOPPEE on 8/19/2017.
 */

public class MainActivityFirebaseQueryHelper extends AppCompatActivity {

    ListView lv;
    ArrayAdapter<String> adapter;
    DatabaseReference db;
    FirebaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_arraylist_layout);

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        lv= (ListView) findViewById(R.id.lv);

        //SETUP FIREBASE
        db = FirebaseDatabase.getInstance().getReference();
        helper = new FirebaseHelper(db);

        //ADAPTER
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,helper.retrieve());
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //ตรวจสอบ ID จากการเลือก List
                Toast.makeText(MainActivityFirebaseQueryHelper.this, helper.retrieve().get(position), Toast.LENGTH_SHORT).show();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fbFirebase);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayInputDialog();
            }
        });

    }

    private void displayInputDialog()
    {
        Dialog d=new Dialog(this);
        d.setTitle("Save To Firebase");
        d.setContentView(R.layout.activity_main_input_arraylist_layout);

        //Name
        final EditText nameEditTxt = (EditText) d.findViewById(R.id.nameEditText);

        Button saveBtn= (Button) d.findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //GET DATA
                String name = nameEditTxt.getText().toString();                 //ชื่อ

                //SET DATA TO MODEL CLASS
                FirebaseQueryProfile s = new FirebaseQueryProfile();
                s.setName(name);

                //VALIDATE DATA
                if(name.length()> 0 && name != null) {      //ตรวจสอบว่ากรอกชื่อหรือยัง
                    if(helper.save(s)) {
                        nameEditTxt.setText("");
                        adapter=new ArrayAdapter<String>(MainActivityFirebaseQueryHelper.this,android.R.layout.simple_list_item_1,helper.retrieve());
                        lv.setAdapter(adapter);
                    }
                } else {
                    Toast.makeText(MainActivityFirebaseQueryHelper.this, "Cannot be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
        d.show();
    }

}
