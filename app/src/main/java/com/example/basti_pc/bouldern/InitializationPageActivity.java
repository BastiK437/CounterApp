package com.example.basti_pc.bouldern;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class InitializationPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initialization_page);

        findViewById(R.id.saveButton).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                Config config = new Config( "/storage/emulated/0/Android/data/Basti" );
                EditText et = (EditText) findViewById(R.id.jahresbeitrag);
                config.writeData( new ConfigObj( "Jahresbeitrag", et.getText().toString() ) );

                //finish();

                Intent i = new Intent(InitializationPageActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}
