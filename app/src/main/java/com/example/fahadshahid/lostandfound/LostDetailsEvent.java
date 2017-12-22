package com.example.fahadshahid.lostandfound;

/**
 * Created by Fahad Shahid on 11/14/2017.
 */

class LostDetailsEvent {
    private String message;

    public LostDetailsEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
