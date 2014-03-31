package com.geaden.gametime.app;

import android.view.View;
import android.widget.AdapterView;

import com.geaden.gametime.Display;

/**
 * Created by geaden on 31/03/14.
 */
public class RatioSelectedListener implements AdapterView.OnItemSelectedListener {
    private Display displ;

    public RatioSelectedListener(Display displ) {
        this.displ = displ;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String ratio = adapterView.getItemAtPosition(i).toString();
        displ.setRatio(ratio);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
