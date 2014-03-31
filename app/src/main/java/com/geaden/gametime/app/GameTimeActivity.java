package com.geaden.gametime.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.geaden.gametime.impl.DisplayChronoImpl;
import com.geaden.gametime.impl.DisplayImpl;
import com.geaden.gametime.Display;
import com.geaden.gametime.DisplayChrono;


public class GameTimeActivity extends Activity {
    private Button startButton;
    private Button resetButton;
    private TextView ratio;
    private Chronometer curChronometer;
    private Chronometer calcChronometer;
    private DisplayChrono mCurChronometer;
    private DisplayChrono mCalcChronometer;
    private EditText displayName;
    private Display displ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_time);
        curChronometer = (Chronometer) findViewById(R.id.curChronometer);
        calcChronometer = (Chronometer) findViewById(R.id.calcChronometer);
        mCurChronometer = new DisplayChronoImpl(curChronometer);
        mCalcChronometer = new DisplayChronoImpl(calcChronometer);
        Spinner ratios = (Spinner) findViewById(R.id.ratios);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.ratio_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        ratios.setAdapter(adapter);
        ratios.setSelection(0);
        displayName = (EditText) findViewById(R.id.displayName);
        displ = new DisplayImpl(displayName.getText().toString(), (String) ratios.getSelectedItem(), mCurChronometer, mCalcChronometer);
        ratios.setOnItemSelectedListener(new RatioSelectedListener(displ));
        displayName.setText(displ.getName());
        resetButton = (Button) findViewById(R.id.resetButton);
        startButton = (Button) findViewById(R.id.startButton);
        startButtonInit();
    }

    /**
     * Initializes start button
     */
    private void startButtonInit() {
        startButton.setText("Start");
        startButton.setOnClickListener(startButtonListener);
    }

    View.OnClickListener startButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            displ.activate();
            resetButton.setEnabled(true);
            pauseButtonInit();
            resetButtonInit();
        }
    };


    private void resetButtonInit() {
        resetButton.setEnabled(true);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displ.reset();
                startButtonInit();
            }
        });
    }

    /**
     * Initializes pause button
     */
    private void pauseButtonInit() {
        startButton.setText("Pause");
        startButton.setOnClickListener(pauseButtonListener);
    }

    /**
     * Initializes resume button
     */
    private void resumeButtonInit() {
        startButton.setText("Resume");
        startButton.setOnClickListener(resumeButtonListener);
    }

    View.OnClickListener pauseButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (displ.isActivated() && !displ.isPaused()) {
                displ.pause();
                resumeButtonInit();
            }
        }
    };

    View.OnClickListener resumeButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            displ.activate();
            pauseButtonInit();
        }
    };
}