package com.example.fahadshahid.lostandfound;

import com.example.fahadshahid.lostandfound.models.Lost;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fahad Shahid on 11/14/2017.
 */

class LostEvent {
    private ArrayList<Lost> message;

    public LostEvent(ArrayList<Lost> message) {
        this.message = message;
    }



    public ArrayList<Lost> getMessage() {
        return message;
    }
}
