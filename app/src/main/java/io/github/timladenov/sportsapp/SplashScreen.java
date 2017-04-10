/*
* @author   Tihomir Mladenov <tihomir.mladenov777@gmail.com>
*           This application has been created for Project 2 on Udacity's Google sponsored "Android For Beginners" course;
*           18 March 2017
*
* @version  v2.1 final
* @since    v1.0a
* */

package io.github.timladenov.sportsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    /*

    * onCreate method initiates a thread, which sleeps for 4 seconds and displays the logo;
    * After 4 seconds an Intent starts EnterTeamNames activity;
    *
    * */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Thread splashThreat = new Thread() {

            @Override
            public void run() {
                try {
                    sleep(4000);

                    Intent afterSplash = new Intent(getApplicationContext(), EnterTeamNames.class);
                    startActivity(afterSplash);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        splashThreat.start();
    }
}
