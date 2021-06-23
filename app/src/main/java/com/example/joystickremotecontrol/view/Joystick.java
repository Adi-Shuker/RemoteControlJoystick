package com.example.joystickremotecontrol.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import androidx.annotation.NonNull;
import java.util.jar.Attributes;

public class Joystick extends SurfaceView implements SurfaceHolder.Callback, View.OnTouchListener {
    public JoystickListener onChange;
    private float centerX;
    private float centerY;
    private float baseRadius;
    private float hatRadius;
    private JoystickListener joystickCallback;
    private final int ratio = 5;

    private void setupDimensions(){
        centerX = getWidth()/2;
        centerY = getHeight()/2;
        baseRadius = Math.min(getWidth(),getHeight())/3;
        hatRadius = Math.min(getWidth(),getHeight())/5;
    }

    public Joystick(Context context, int x) {
        super(context);
        getHolder().addCallback(this);
        setOnTouchListener(this);
        if(context instanceof JoystickListener) {
            joystickCallback = (JoystickListener) context;
        }
    }

    public Joystick(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        getHolder().addCallback(this);
        setOnTouchListener(this);
        if (context instanceof JoystickListener) {
            joystickCallback = (JoystickListener) context;

        }
    }

    public Joystick(Context context, AttributeSet attributeSet, int style){
            super(context, attributeSet, style);
            getHolder().addCallback(this);
            setOnTouchListener(this);
            if (context instanceof JoystickListener) {
                joystickCallback = (JoystickListener) context;

            }
        }

    private void drawJoystick(float newX, float newY){
        if(getHolder().getSurface().isValid()){
            Canvas canvas = this.getHolder().lockCanvas();
            Paint colors = new Paint();
            canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
            colors.setARGB(255, 50, 50,50);
            canvas.drawCircle(centerX, centerY, baseRadius, colors);
            colors.setARGB(255, 0, 0,128);
            canvas.drawCircle(newX, newY, hatRadius, colors);
            getHolder().unlockCanvasAndPost(canvas);
        }
    }
    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        setupDimensions();
        drawJoystick(centerX,centerY);
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(v.equals(this)){
            if(event.getAction() != event.ACTION_UP){
                float displacement = (float) Math.sqrt((Math.pow(event.getX() - centerX, 2)) + Math.pow(event.getY() - centerY, 2));
                if(displacement < baseRadius){
                    drawJoystick(event.getX(), event.getY());
                    try {
                        onChange.onJoystickMoved((event.getX() - centerX) / baseRadius, (event.getY() - centerY) / baseRadius);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                    float ratio = baseRadius / displacement;
                    float constrainedX = centerX + (event.getX() - centerX)*ratio;
                    float constrainedY = centerY + (event.getY() - centerY)*ratio;
                    drawJoystick(constrainedX, constrainedY);
                    try {
                        onChange.onJoystickMoved((constrainedX - centerX) / baseRadius, (constrainedY - centerY) / baseRadius);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }else{
                drawJoystick(centerX, centerY);
                try {
                    onChange.onJoystickMoved(0, 0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    public interface JoystickListener
    {
        void onJoystickMoved(float xPercent, float yPercent) throws InterruptedException;
    }
}
