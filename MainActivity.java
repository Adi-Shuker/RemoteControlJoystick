package com.example.joystickremotecontrol.view;


import androidx.appcompat.app.AppCompatActivity;

import android.content.SyncStatusObserver;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.joystickremotecontrol.R;
import com.example.joystickremotecontrol.model.Model;
import com.example.joystickremotecontrol.view_model.ViewModel;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MainActivity extends AppCompatActivity{

    private ViewModel viewModel;
    int min = -100, max = 100;
    String IP, Port;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //LinearLayout layout=(LinearLayout) findViewById(R.id.layout);
        //layout.setBackgroundResource(R.drawable.background1);

        EditText editIP = (EditText)findViewById(R.id.editIP);
        EditText editPort = (EditText)findViewById(R.id.editPort);
        Button connectButton = (Button)findViewById(R.id.connectButton);
        //TextView testIP = (TextView)findViewById(R.id.textViewTest);
        //TextView testPort = (TextView)findViewById(R.id.textViewTestPort);


        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IP = editIP.getText().toString();
                //testIP.setText("received IP: " + IP);
                Port = editPort.getText().toString();
                //testPort.setText("received Port: " + Port);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {

                            // create the view model
                            viewModel = new ViewModel(new Model(IP, Integer.parseInt(Port)));

                            // create the joystick
                            Joystick joystick = (Joystick)findViewById(R.id.joystick);
                            // update the viewModel when the joystick change
                            joystick.onChange = (aileron, elevator)->{
                                viewModel.setAileron(aileron);
                                viewModel.setElevator(elevator);
                            };


                            // textview for the throttle and the rudder
                            //TextView textView = (TextView)findViewById(R.id.textView);
                            //TextView textViewRudder = (TextView)findViewById(R.id.textViewRudder);

                            // create the throttle seekbar
                            SeekBar throttle = (SeekBar)findViewById(R.id.throttle);
                            // define the values of the throttle seekBar
                            throttle.setMax(max -min);
                            // update the viewModel when the throttle seekBar changed
                            throttle.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                                @Override
                                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                    float val = (float)progress + (float)min;
                                    val = val / 100;

                                    //textView.setText("Throttle" + val);
                                    try {
                                        viewModel.setThrottle(val);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }

                                }

                                @Override
                                public void onStartTrackingTouch(SeekBar seekBar) {}
                                @Override
                                public void onStopTrackingTouch(SeekBar seekBar) {}
                            });


                            // create the rudder seekbar
                            SeekBar rudder = (SeekBar)findViewById(R.id.rudder);
                            // define the values of the throttle seekBar
                            rudder.setMax(max -min);
                            // update the viewModel when the rudder seekBar changed
                            rudder.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                                @Override
                                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                    float val = (float)progress + (float)min;
                                    val = val / 100;
                                    //textViewRudder.setText("Rudder" + val);
                                    try {
                                        viewModel.setRudder(val);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }

                                @Override
                                public void onStartTrackingTouch(SeekBar seekBar) {}
                                @Override
                                public void onStopTrackingTouch(SeekBar seekBar) {}
                            });

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
    }
}