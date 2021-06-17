package com.example.joystickremotecontrol.view_model;

import android.graphics.PorterDuff;

import com.example.joystickremotecontrol.model.Model;

public class ViewModel {
    private Model model;
    public ViewModel(Model myModel){
        model = myModel;
    }
    public void setAileron(float value) throws InterruptedException {
        model.setAileron(value);
    }
    //value between -1, 1
    public void setElevator(float value) throws InterruptedException {
        model.setElevator(value);
    }
    //value between -1, 1
    public void setRudder(float value) throws InterruptedException {
        model.setRudder(value);
    }
    //value between 0, 1
    public void setThrottle(float value) throws InterruptedException {
        model.setThrottle(value);
    }
}
