package com.example.colorgamesourcecode;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TwoPlayers extends AppCompatActivity {


    private EditText usernameEditText1, usernameEditText2 ;
    private Button proceedButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_players);

        usernameEditText1 = findViewById(R.id.username_edit_text1);
        usernameEditText2 = findViewById(R.id.username_edit_text2);
        proceedButton = findViewById(R.id.proceedBtn);

        usernameEditText1.addTextChangedListener(textWatcher);
        usernameEditText2.addTextChangedListener(textWatcher);

        proceedButton.setEnabled(false);



        proceedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username1 = usernameEditText1.getText().toString();
                String username2 = usernameEditText2.getText().toString();

                Intent intent = new Intent(TwoPlayers.this, TwoPlayersBoard.class);
                intent.putExtra("USERNAME1", username1);
                intent.putExtra("USERNAME2", username2);

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
            boolean allFieldsFilled = !usernameEditText1.getText().toString().isEmpty() &&
                    !usernameEditText2.getText().toString().isEmpty();

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