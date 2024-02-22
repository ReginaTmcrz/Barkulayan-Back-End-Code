package com.example.colorgamesourcecode;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.text.TextWatcher;

public class SinglePlayer extends AppCompatActivity {

    private EditText usernameEditText1 ;
    private Button proceedButton ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_player);

        usernameEditText1 = findViewById(R.id.username_edit_text1);
        proceedButton = findViewById(R.id.proceedBtn);

        usernameEditText1.addTextChangedListener(textWatcher);

        proceedButton.setEnabled(false);


        proceedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username1 = usernameEditText1.getText().toString();

                Intent intent = new Intent(SinglePlayer.this, SinglePlayerBoard.class);
                intent.putExtra("USERNAME1", username1);

                startActivity(intent);
            }
        });

    }


    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        @Override
        public void afterTextChanged(Editable s) {
            // Check if all text fields are non-empty
            boolean allFieldsFilled = !usernameEditText1.getText().toString().isEmpty();

            // Enable or disable the button based on the condition
            proceedButton.setEnabled(allFieldsFilled);
        }
    };

    public void goToPageSelectNumbersOfPlayersPage(View v) {
        Intent i = new Intent(this, selectNumbersOfPlayers.class);
        startActivity(i);
        finish();
    }
}