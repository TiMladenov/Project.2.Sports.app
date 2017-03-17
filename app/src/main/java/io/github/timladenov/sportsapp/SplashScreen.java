package io.github.timladenov.sportsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Thread splashThreat = new Thread(){

            @Override
            public void run() {
                try {
                    sleep(3000);

                    Intent afterSplash = new Intent(getApplicationContext(),MainActivity.class);
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
