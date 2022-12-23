package com.example.lab2;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import utils.TextCounter;

public class MainActivity extends AppCompatActivity {
    private final TextView tvMain = (TextView) findViewById(R.id.TvMain);
    private final EditText edPhrase = (EditText) findViewById(R.id.edPhrase);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnMessage = (Button) findViewById(R.id.btnMessage);
        Spinner spinnerMessage = (Spinner) findViewById(R.id.spnMessage);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Data, android.R.layout.simple_spinner_item);
        spinnerMessage.setAdapter(adapter);

        btnMessage.setOnClickListener(new View.OnClickListener() {
            public void setSpnMessage(AdapterView<Adapter> spnMessage) {
                this.spnMessage = spnMessage;
            }

            private AdapterView<Adapter> spnMessage;

            public void onClick(View view) {
                String selectedOption = this.spnMessage.getSelectedItem().toString();
                String defaultCharSelectedOption = getResources().getString(R.string.char_data);
                if (selectedOption.equals(defaultCharSelectedOption)) {

                    String userInputTest = edPhrase.getText().toString();
                    String CharsCount = TextCounter.getCharsCount(userInputTest);
                    tvMain.setText(CharsCount);


                }

            }

            public String getCharsCount(String input) {

                return String.valueOf(input.length());
            }

        });

    }
}

