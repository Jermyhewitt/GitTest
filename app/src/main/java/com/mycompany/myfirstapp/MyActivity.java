package com.mycompany.myfirstapp;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.io.File;
import java.io.FileOutputStream;

public class MyActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE="com.mycompany.myfirstapp.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        File file= new File("name");
        try
        {
            FileOutputStream stream= openFileOutput("test",Context.MODE_PRIVATE);
            stream.write(10);
            stream.close();

        }
        catch (Exception e)
        {

        }


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        EditText editText=(EditText) findViewById(R.id.edit_message);
        editText.setText("Text changed");

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALENDAR)!= PackageManager.PERMISSION_GRANTED)
        {
            editText.setText("No permission");
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_CALENDAR))
            {
                editText.setText("This is an explanation");
            }
            else
            {
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CALENDAR},44);
            }

        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my, menu);
        return true;
    }

    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 44: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void sendMessage(View view)
    {

        Intent intent= new Intent(this,DisplayMessageActivity.class);
        EditText editText=(EditText) findViewById(R.id.edit_message);
        editText.setText(Environment.MEDIA_UNMOUNTABLE);
        String message= editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);


    }

    public void third(View view)
    {
        Intent intent =new Intent(Intent.ACTION_DIAL);
        if(intent.resolveActivity(getPackageManager())!=null)
        startActivity(intent);
    }
}
