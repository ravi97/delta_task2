package com.example.ravikiran.deltatask2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private String text;
    private int size=1;
    private int shape=1;
    Draw draw;
    private StringBuffer instructions = new StringBuffer("1.Please make sure that you have an internet connection." +
            "\n2.When you want to move the drawable," +
            "\nsay up,down,left or right" +
            "\n3.There are three sizes available: small,medium and large");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        draw=new Draw(MainActivity.this);
        setContentView(draw);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater menuInflater= getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id){
            case R.id.action_help:
                return true;
            case R.id.action_record:
                return true;

        }

        return super.onOptionsItemSelected(item);
    }

    public void recordVoice(){
        Intent i=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        i.putExtra(RecognizerIntent.EXTRA_PROMPT,"speak up");
        i.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS,1);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.ENGLISH);
        startActivityForResult(i, 1);
    }
    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data){
        if(requestCode==1){
            ArrayList<String> results;
            results=data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            text=results.get(0);
            exportResult(text);
        }
    }
    public void showAlert(){
        AlertDialog.Builder alert=new AlertDialog.Builder(this);
        alert.setTitle("Instructions");
        alert.setMessage(instructions);
        alert.setCancelable(false);
        alert.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alert.show();
    }
    public void exportResult(String text){
        switch(this.text){
            case "up":
                draw.getDirection(1);
                break;
            case "down":
                draw.getDirection(2);
                break;
            case "left":
                draw.getDirection(3);
                break;
            case "right":
                draw.getDirection(4);
                break;
            case "small":
                size=1;
                draw.setSizeShape(size,shape);
                break;
            case "medium":
                size=2;
                draw.setSizeShape(size,shape);
                break;
            case "large":
                size=3;
                draw.setSizeShape(size,shape);
                break;
            case "square":
                shape=1;
                draw.setSizeShape(size,shape);
                break;
            case "circle":
                shape=2;
                draw.setSizeShape(size,shape);
                break;
            case "triangle":
                shape=3;
                draw.setSizeShape(size,shape);
                break;
            default:
                Toast.makeText(MainActivity.this, this.text + " is not a valid command", Toast.LENGTH_SHORT).show();
        }
    }
}
