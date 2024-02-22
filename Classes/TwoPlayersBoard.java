package com.example.colorgamesourcecode;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
public class TwoPlayersBoard extends AppCompatActivity {
    private Button redButton, blueButton, yellowButton, greenButton, orangeButton, pinkButton, doneButton;
    private int selectedCount = 0;
    private TextView selectedColorsTextView11, selectedColorsTextView22;
    private ArrayList<String> selectedColors = new ArrayList<>();
    private ArrayList<Button> colorButtons;
    private Random random = new Random();
    private int currentRound = 0;
    private TextView randomColorTextView, roundTextView;
    private TextView usernameTextView1, usernameTextView2;
    private List<TextView> usernameTextViews = new ArrayList<>();
    private List<TextView> selectedColorTextViews = new ArrayList<>();
    private ArrayList<Button> selectedButtons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_players_board);

        redButton = findViewById(R.id.redBtn);
        blueButton = findViewById(R.id.blueBtn);
        yellowButton = findViewById(R.id.greenBtn);
        greenButton = findViewById(R.id.yellowBtn);
        orangeButton = findViewById(R.id.orangeBtn);
        pinkButton = findViewById(R.id.pinkBtn);
        doneButton = findViewById(R.id.betBtn);

        selectedColorsTextView11 = findViewById(R.id.selectedColorsTextView1);
        selectedColorsTextView22 = findViewById(R.id.selectedColorsTextView2);

        usernameTextView1 = findViewById(R.id.username_text_view1);
        usernameTextView2 = findViewById(R.id.username_text_view2);

        randomColorTextView = findViewById(R.id.random_color_textview);
        roundTextView = findViewById(R.id.round_textview);

        usernameTextViews.add(usernameTextView1);
        usernameTextViews.add(usernameTextView2);

        selectedColorTextViews.add(selectedColorsTextView11);
        selectedColorTextViews.add(selectedColorsTextView22);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("USERNAME1")) {
            String username1 = intent.getStringExtra("USERNAME1");
            // Display username in the TextViews
            usernameTextView1.setText(username1);

        }

        Intent intent1 = getIntent();
        if (intent1 != null && intent1.hasExtra("USERNAME2")) {
            String username2 = intent1.getStringExtra("USERNAME2");
            // Display username in the TextViews
            usernameTextView2.setText(username2);

        }


        colorButtons = new ArrayList<>(Arrays.asList(redButton, blueButton, yellowButton, greenButton, orangeButton, pinkButton));

        redButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleButtonClick(redButton, "Red");
            }
        });

        blueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleButtonClick(blueButton, "Blue");
            }
        });

        yellowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleButtonClick(yellowButton, "Yellow");
            }
        });

        greenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleButtonClick(greenButton, "Green");
            }
        });

        orangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleButtonClick(orangeButton, "Orange");
            }
        });

        pinkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleButtonClick(pinkButton, "Pink");
            }
        });

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (selectedCount == 2) {
                    startNextRound();

                }
            }

        });
    }


    private void startNextRound() {
        if (currentRound < 5) {
            ++currentRound;
            roundTextView.setText("Round: " + currentRound);

            // Reset selected colors and buttons
            resetSelectedColorsAndButtons();

            // Randomly select a color and assign points
            int randomIndex = random.nextInt(colorButtons.size());
            Button randomColorButton = colorButtons.get(randomIndex);
            String randomColor = randomColorButton.getText().toString();
            randomColorTextView.setText("Random Color: " + randomColor);
            showToastForColor(randomColor);


        } else {
          GameOver();
        }
    }

    private void GameOver() {
        Intent i = new Intent(this, GameOverPage.class);
        startActivity(i);
        finish();
    }
    private void resetSelectedColorsAndButtons() {
        selectedCount = 0;
        selectedColors.clear();
        Collections.shuffle(colorButtons);
        for (Button button : colorButtons) {
            button.setEnabled(true);
        }
        selectedColorsTextView11.setText("choose color");
        selectedColorsTextView22.setText("choose color");

    }

    private void handleButtonClick(Button button, String color) {
        if (button.isEnabled()) {
            button.setEnabled(false);
            selectedColors.add(color);
            selectedCount++;
            updateSelectedColorTextViews();
            if (selectedCount == 2) {
                doneButton.setEnabled(true);
                disableUnselectedButtons();
            }else {

            }
        }
    }

    private void disableUnselectedButtons() {
        for (Button btn : new Button[]{redButton, blueButton, yellowButton, greenButton, orangeButton, pinkButton}) {
            if (!selectedButtons.contains(btn)) {
                btn.setEnabled(false);
            }
        }
    }

    private void updateSelectedColorTextViews() {
        switch (selectedCount) {
            case 1:
                selectedColorsTextView11.setText(selectedColors.get(0));
                break;
            case 2:
                selectedColorsTextView22.setText(selectedColors.get(1));
                break;
        }
    }


    // Handle button clicks for each color// For simplicity, I'll add only one listener and determine the color based on the button's text
    public void onColorButtonClick(View view) {
        Button button = (Button) view;
        String color = button.getText().toString();
        handleButtonClick(button, color);
    }


    private void showToastForColor(String color) {
        switch (color) {
            case "Red":
                ImageView red = new ImageView(getApplicationContext());

                red.setImageResource(R.drawable.red);
                Toast toastRed = new Toast(getApplicationContext());
                toastRed.setDuration(Toast.LENGTH_LONG);
                toastRed.setView(red);
                toastRed.show();
                break;


            case "Blue":
                ImageView blue = new ImageView(getApplicationContext());
                blue.setImageResource(R.drawable.blue);
                Toast toastBlue = new Toast(getApplicationContext());
                toastBlue.setDuration(Toast.LENGTH_LONG);
                toastBlue.setView(blue);
                toastBlue.show();
                break;


            case "Yellow":
                ImageView yellow = new ImageView(getApplicationContext());
                yellow.setImageResource(R.drawable.yellow);
                Toast toastYellow = new Toast(getApplicationContext());
                toastYellow.setDuration(Toast.LENGTH_LONG);
                toastYellow.setView(yellow);
                toastYellow.show();
                break;

            case "Green":
                ImageView green = new ImageView(getApplicationContext());
                green.setImageResource(R.drawable.green);
                Toast toastGreen = new Toast(getApplicationContext());
                toastGreen.setDuration(Toast.LENGTH_LONG);
                toastGreen.setView(green);
                toastGreen.show();
                break;

            case "Orange":
                ImageView orange = new ImageView(getApplicationContext());
                orange.setImageResource(R.drawable.orange);
                Toast toastOrange = new Toast(getApplicationContext());
                toastOrange.setDuration(Toast.LENGTH_LONG);
                toastOrange.setView(orange);
                toastOrange.show();
                break;
            case "Pink":
                ImageView pink = new ImageView(getApplicationContext());
                pink.setImageResource(R.drawable.pink);
                Toast toastPink = new Toast(getApplicationContext());
                toastPink.setDuration(Toast.LENGTH_LONG);
                toastPink.setView(pink);
                toastPink.show();
                break;

        }
    }
}
