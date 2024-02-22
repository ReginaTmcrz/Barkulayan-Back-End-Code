package com.example.colorgamesourcecode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void goToPageSelectNumbersOfPlayersPage(View v) {
        Intent i = new Intent(this, selectNumbersOfPlayers.class);
        startActivity(i);
        finish();
    }

    public void goToAboutPage(View v) {
        Intent i = new Intent(this, AboutPage.class);
        startActivity(i);
        finish();
    }

}
