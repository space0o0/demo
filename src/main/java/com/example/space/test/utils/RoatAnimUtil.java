package com.example.space.test.utils;

import android.view.View;
import android.view.animation.RotateAnimation;

/**
 * Created by space on 16/12/5.
 */

public class RoatAnimUtil {

    public static void startAnimIn(View view){
        animIn(view,0);
    }

    public static void startAnimOut(View view){
        animOut(view,0);
    }

    public static void animOut(View view ,int delay){
        RotateAnimation rotateAnimation=new RotateAnimation(0,45,view.getWidth()/2,view.getHeight()/2);
        rotateAnimation.setDuration(200);
        rotateAnimation.setStartOffset(delay);
        rotateAnimation.setFillAfter(true);
        view.startAnimation(rotateAnimation);
    }

    public static void animIn(View view,int delay){
        RotateAnimation rotateAnimation2=new RotateAnimation(45,0,view.getWidth()/2,view.getHeight()/2);
        rotateAnimation2.setDuration(200);
        rotateAnimation2.setStartOffset(delay);
        rotateAnimation2.setFillAfter(true);
        view.startAnimation(rotateAnimation2);
    }
}
