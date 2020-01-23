package com.example.colourguesser;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private View colourImage;
    private View chosenColour;
    private Button guessButton;
    private Button nextButton;
    private SeekBar rSeekBar;
    private SeekBar gSeekBar;
    private SeekBar bSeekBar;
    private TextView redValueLabel, greenValueLabel, blueValueLabel;
    private int red, green, blue, chosenRed, chosenGreen, chosenBlue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        guessButton = findViewById(R.id.guessButton);
        nextButton = findViewById(R.id.nextButton);
        colourImage = findViewById(R.id.mainColour);
        chosenColour = findViewById(R.id.chosenColour);
        rSeekBar = findViewById(R.id.redSeekBar);
        gSeekBar = findViewById(R.id.greenSeekBar);
        bSeekBar = findViewById(R.id.blueSeekBar);
        redValueLabel = findViewById(R.id.redValueLabel);
        greenValueLabel = findViewById(R.id.greenValueLabel);
        blueValueLabel = findViewById(R.id.blueValueLabel);

        guessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleButtons();
                showColour();
            }
        });

        bSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                chosenRed = rSeekBar.getProgress() * 255 / 100;
                chosenGreen = gSeekBar.getProgress() * 255 / 100;
                chosenBlue = bSeekBar.getProgress() * 255 / 100;
                redValueLabel.setText(Integer.toString(chosenRed));
                greenValueLabel.setText(Integer.toString(chosenGreen));
                blueValueLabel.setText(Integer.toString(chosenBlue));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleButtons();
                newColour();
            }
        });
    }

    public void showColour() {
        int rgb = (0xFF << 24) + (chosenRed << 16) + (chosenGreen << 8) + (chosenBlue);
        chosenColour.setBackgroundColor(rgb);
    }

    public void newColour() {
        red = new Random().nextInt(256);
        green = new Random().nextInt(256);
        blue = new Random().nextInt(256);
        int rgb = (0xFF << 24) + (red << 16) + (green << 8) + (blue);
        colourImage.setBackgroundColor(rgb);
        chosenColour.setBackgroundColor(Color.WHITE);
    }

    public void toggleButtons() {
        guessButton.setEnabled(!guessButton.isEnabled());
        nextButton.setEnabled(!nextButton.isEnabled());
    }
}
