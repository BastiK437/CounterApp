package com.example.basti_pc.bouldern;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        path = "/storage/emulated/0/Android/data/Basti";
        Config configObj = new Config(path);

        if( !configObj.exists() ){
            configObj.initializeConfig();
            Intent i = new Intent(MainActivity.this, InitializationPageActivity.class);
            startActivity(i);
        }

        findViewById(R.id.changeButton).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent i = new Intent(MainActivity.this, InitializationPageActivity.class);
                startActivity(i);
            }
        });

        TextView jahresbeitrag = (TextView) findViewById(R.id.text1);
        jahresbeitrag.setText(configObj.readData().get(2).getDataType() + " = " + configObj.readData().get(2).getData() );

        //configObj.printConfig();
    }

    public String getPath(){
        return path;
    }


}
