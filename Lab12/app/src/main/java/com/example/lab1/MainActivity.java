package com.example.lab1;

import static com.example.lab1.R.id.btnChangeColor;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView txtMessage;
    private Button btnMessage;
    private Button btnMessageB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtMessage = (TextView) findViewById(R.id.textMessage);
        btnMessage = (Button) findViewById(R.id.btnTextChange);
        btnMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtMessage.setText("Goodbye World!");
            }


        });
        btnMessageB = (Button)findViewById(R.id.btnChangeColor);
        btnMessageB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtMessage.setTextColor(Color.BLUE);
            }
        });
    }
}





