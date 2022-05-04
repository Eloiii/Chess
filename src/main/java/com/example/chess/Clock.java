package com.example.chess;

import javafx.application.Platform;
import javafx.scene.control.Label;

import java.util.Timer;
import java.util.TimerTask;

public class Clock {

    Timer clock;
    TimerTask task;
    int secondsRemaining;
    Label label;

    public Clock(int seconds, Label label) {
        this.clock = new Timer();
        this.secondsRemaining = seconds;
        this.label = label;
        setSecondsRemaining();
        this.setUpTask();
    }

    private void setUpTask() {
        this.task = new TimerTask(){
            public void run(){
                setSecondsRemaining();
                secondsRemaining--;
            }
        };

    }

    private void setSecondsRemaining() {
        Platform.runLater(() -> label.setText(String.format("%02d:%02d", this.secondsRemaining / 60, this.secondsRemaining % 60)));
    }

    public void startTimer() {
        this.clock.scheduleAtFixedRate(task, 0, 1000);
    }

    public int getSecondsRemaining() {
        return this.secondsRemaining;
    }

    public void pause() {
        this.clock.cancel();
    }

    public void resume() {
        this.clock = new Timer();
        this.setUpTask();
        this.startTimer();
    }
}
