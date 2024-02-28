package com.example.colorgamesourcecode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class FourPlayersBoard extends AppCompatActivity {

    private ImageButton redButton, blueButton, yellowButton, greenButton, orangeButton, pinkButton,
            doneButton;
    private int selectedCount = 0;
    private TextView selectedColorsTextView11, selectedColorsTextView22, selectedColorsTextView33,
            selectedColorsTextView44;
    private ArrayList<String> selectedColors = new ArrayList<>();
    private ArrayList<ImageButton> colorButtons;
    private Random random = new Random();
    private int currentRound = 0;
    private TextView randomColorTextView, roundTextView;
    private TextView usernameTextView1, usernameTextView2, usernameTextView3, usernameTextView4;
    private List<TextView> usernameTextViews = new ArrayList<>();
    private List<TextView> selectedColorTextViews = new ArrayList<>();
    private ArrayList<ImageButton> selectedButtons = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four_players_board);

        redButton = findViewById(R.id.redBtn);
        blueButton = findViewById(R.id.blueBtn);
        yellowButton = findViewById(R.id.greenBtn);
        greenButton = findViewById(R.id.yellowBtn);
        orangeButton = findViewById(R.id.orangeBtn);
        pinkButton = findViewById(R.id.pinkBtn);
        doneButton = findViewById(R.id.betBtn);

        selectedColorsTextView11 = findViewById(R.id.selectedColorsTextView1);
        selectedColorsTextView22 = findViewById(R.id.selectedColorsTextView2);
        selectedColorsTextView33 = findViewById(R.id.selectedColorsTextView3);
        selectedColorsTextView44 = findViewById(R.id.selectedColorsTextView4);


        usernameTextView1 = findViewById(R.id.username_text_view1);
        usernameTextView2 = findViewById(R.id.username_text_view2);
        usernameTextView3 = findViewById(R.id.username_text_view3);
        usernameTextView4 = findViewById(R.id.username_text_view4);

        randomColorTextView = findViewById(R.id.random_color_textview);
        roundTextView = findViewById(R.id.round_textview);

        usernameTextViews.add(usernameTextView1);
        usernameTextViews.add(usernameTextView2);
        usernameTextViews.add(usernameTextView3);
        usernameTextViews.add(usernameTextView4);

        selectedColorTextViews.add(selectedColorsTextView11);
        selectedColorTextViews.add(selectedColorsTextView22);
        selectedColorTextViews.add(selectedColorsTextView33);
        selectedColorTextViews.add(selectedColorsTextView44);


        // For Players
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


        Intent intent2 = getIntent();
        if (intent2 != null && intent2.hasExtra("USERNAME3")) {
            String username3 = intent2.getStringExtra("USERNAME3");
            // Display username in the TextViews
            usernameTextView3.setText(username3);

        }

        Intent intent3 = getIntent();
        if (intent3 != null && intent3.hasExtra("USERNAME4")) {
            String username4 = intent3.getStringExtra("USERNAME4");
            // Display username in the TextViews
            usernameTextView4.setText(username4);

        }

        colorButtons = new ArrayList<>(Arrays.asList(redButton, blueButton, yellowButton, greenButton, orangeButton, pinkButton));

        redButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {handleButtonClick(redButton, "Red");
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

                if (selectedCount == 4) {
                    startNextRound();

                }
            }

        });
    }

    public void gotoHomePage(){
        Intent i = new Intent(this, selectNumbersOfPlayers.class);
        startActivity(i);
        finish();
    }
    private void startNextRound() {
        if (currentRound < 5) {
            ++currentRound;
            roundTextView.setText("" + currentRound);

            // Reset selected colors and buttons
            resetSelectedColorsAndButtons();

            // Randomly select a color and assign points
            int randomIndex = random.nextInt(colorButtons.size());
            ImageButton randomColorButton = colorButtons.get(randomIndex);

            String randomColor = randomColorButton.getContentDescription().toString();
            randomColorTextView.setText("" + randomColor);
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
        for (ImageButton button : colorButtons) {
            button.setEnabled(true);
        }
        selectedColorsTextView11.setText("choose color");
        selectedColorsTextView22.setText("choose color");
        selectedColorsTextView33.setText("choose color");
        selectedColorsTextView44.setText("choose color");


    }

    private void handleButtonClick(ImageButton button, String color) {
        if (button.isEnabled()) {
            button.setEnabled(false);
            selectedColors.add(color);
            selectedCount++;
            updateSelectedColorTextViews();
            if (selectedCount == 4) {
                doneButton.setEnabled(true);
                disableUnselectedButtons();
            }else {

            }
        }
    }


    private void disableUnselectedButtons() {
        for (ImageButton btn : new ImageButton[]{redButton, blueButton, yellowButton, greenButton, orangeButton, pinkButton}) {
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
            case 3:
                selectedColorsTextView33.setText(selectedColors.get(2));
                break;
            case 4:
                selectedColorsTextView44.setText(selectedColors.get(3));
                break;
        }
    }


    // Handle button clicks for each color// For simplicity, I'll add only one listener and determine the color based on the button's text
    public void onColorButtonClick(View view) {
        ImageButton button = (ImageButton) view;
        String color = button.getContentDescription().toString();
        handleButtonClick(button, color);
    }


    private void showToastForColor(String color) {
        switch (color) {
            case "Red":
                ImageView red = new ImageView(getApplicationContext());

                red.setImageResource(R.drawable.redtoast);
                Toast toastRed = new Toast(getApplicationContext());
                toastRed.setDuration(Toast.LENGTH_LONG);
                toastRed.setView(red);
                toastRed.show();

                // Load the GIF using Glide
                Glide.with(this)
                        .load(R.drawable.redtoast)
                        .into(red);

                break;


            case "Blue":
                ImageView blue = new ImageView(getApplicationContext());
                blue.setImageResource(R.drawable.bluetoast);
                Toast toastBlue = new Toast(getApplicationContext());
                toastBlue.setDuration(Toast.LENGTH_LONG);
                toastBlue.setView(blue);
                toastBlue.show();

                // Load the GIF using Glide
                Glide.with(this)
                        .load(R.drawable.bluetoast)
                        .into(blue);

                break;


            case "Yellow":
                ImageView yellow = new ImageView(getApplicationContext());
                yellow.setImageResource(R.drawable.yellowtoast);
                Toast toastYellow = new Toast(getApplicationContext());
                toastYellow.setDuration(Toast.LENGTH_LONG);
                toastYellow.setView(yellow);
                toastYellow.show();

                // Load the GIF using Glide
                Glide.with(this)
                        .load(R.drawable.yellowtoast)
                        .into(yellow);

                break;

            case "Green":
                ImageView green = new ImageView(getApplicationContext());
                green.setImageResource(R.drawable.greentoast);
                Toast toastGreen = new Toast(getApplicationContext());
                toastGreen.setDuration(Toast.LENGTH_LONG);
                toastGreen.setView(green);
                toastGreen.show();

                // Load the GIF using Glide
                Glide.with(this)
                        .load(R.drawable.greentoast)
                        .into(green);

                break;

            case "Orange":
                ImageView orange = new ImageView(getApplicationContext());
                orange.setImageResource(R.drawable.orangetoast);
                Toast toastOrange = new Toast(getApplicationContext());
                toastOrange.setDuration(Toast.LENGTH_LONG);
                toastOrange.setView(orange);
                toastOrange.show();

                // Load the GIF using Glide
                Glide.with(this)
                        .load(R.drawable.orangetoast)
                        .into(orange);



                break;
            case "Pink":
                ImageView pink = new ImageView(getApplicationContext());
                pink.setImageResource(R.drawable.pinktoast);
                Toast toastPink = new Toast(getApplicationContext());
                toastPink.setDuration(Toast.LENGTH_LONG);
                toastPink.setView(pink);
                toastPink.show();

                // Load the GIF using Glide
                Glide.with(this)
                        .load(R.drawable.pinktoast)
                        .into(pink);

                break;

        }
    }
}


