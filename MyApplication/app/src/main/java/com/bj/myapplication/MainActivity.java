package com.bj.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.e("xxx", "");
        Toast.makeText(this, "骚白", Toast.LENGTH_SHORT).show();

        Toast.makeText(this, "lililiiiiiiiiiiiii", Toast.LENGTH_SHORT).show();

    }
}
