package com.example.numeralstrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.speech.tts.TextToSpeech;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;


public class NumeralsTrain extends AppCompatActivity {

private TextToSpeech textToSpeechSystem;
private ImageButton buttonNext;
private TextView txtNumerals;
private EditText editTextNumber;
private Button buttonShowNumber;
private ImageButton btnRepeat;
private int randomNum;
private ImageButton btnBackToMainScreen;
private MChronometer mChronometer;
int valueOfWordsDone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numerals_train);

        buttonNext = findViewById(R.id.buttonNext);
        txtNumerals = findViewById(R.id.txtNumerals);
        editTextNumber = findViewById(R.id.editTextNumber);
        buttonShowNumber = findViewById(R.id.buttonShowNumber);
        btnRepeat = findViewById(R.id.btnRepeat);
        btnBackToMainScreen = findViewById(R.id.btnBackToMainScreen);


        mChronometer = findViewById(R.id.mChronometer);
        mChronometer.start();

        mChronometer.setOnChronometerTickListener(new MChronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(MChronometer chronometer) {
//                if(mChronometer.getText().toString().equalsIgnoreCase("00:00:6")){
//                    mChronometer.stop();
//                }
            }
        });



        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        editTextNumber.requestFocus();

        buttonShowNumber.setVisibility(View.INVISIBLE);


        btnBackToMainScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });




        btnRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textToSpeechSystem.speak(txtNumerals.getText().toString(),TextToSpeech.QUEUE_ADD,null);
            }
        });


        buttonShowNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtNumerals.setVisibility(View.VISIBLE);
                buttonShowNumber.setVisibility(View.INVISIBLE);
            }
        });


        /**next number*/
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                randomNum = ThreadLocalRandom.current().nextInt(1001,2100);
                txtNumerals.setVisibility(View.INVISIBLE);
                buttonShowNumber.setVisibility(View.VISIBLE);
                txtNumerals.setText(Integer.toString(randomNum));
                editTextNumber.setText("");
                textToSpeechSystem.speak(txtNumerals.getText().toString(),TextToSpeech.QUEUE_ADD,null);

                mChronometer.setBase(SystemClock.elapsedRealtime());


            }
        });

        textToSpeechSystem = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {

            @Override
            public void onInit(int i) {
                textToSpeechSystem.setLanguage(Locale.US);
            }
        });



    /**ппвпвапвапвап*/
        editTextNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if(txtNumerals.getText().equals(charSequence.toString())){
                        buttonShowNumber.setVisibility(View.INVISIBLE);
                        txtNumerals.setVisibility(View.VISIBLE);
                        editTextNumber.setText("");
                        buttonNext.callOnClick();
                    }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });




    }
}