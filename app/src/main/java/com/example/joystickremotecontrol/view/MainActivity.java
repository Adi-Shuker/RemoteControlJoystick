package com.example.joystickremotecontrol.view;


import androidx.appcompat.app.AppCompatActivity;

import android.content.SyncStatusObserver;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.LinearLayout;

import com.example.joystickremotecontrol.R;
import com.example.joystickremotecontrol.model.Model;
import com.example.joystickremotecontrol.view_model.ViewModel;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MainActivity extends AppCompatActivity{
    private ViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Joystick joystick = (Joystick)findViewById(R.id.joystick);
                        viewModel = new ViewModel(new Model("10.0.0.6", 6500));
                        joystick.onChange = (aileron, elevator)->{
                            viewModel.setAileron(aileron);
                            viewModel.setElevator(elevator);
                        };
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                }
            }).start();
    }

}