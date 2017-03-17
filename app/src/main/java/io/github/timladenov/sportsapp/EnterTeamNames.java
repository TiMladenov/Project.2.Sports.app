package io.github.timladenov.sportsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class EnterTeamNames extends AppCompatActivity {

    Button toMain = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_team_names);

        toMain = (Button) findViewById(R.id.toMain);

        toMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchMain = new Intent(EnterTeamNames.this, MainActivity.class);
                startActivity(switchMain);
                finish();
            }
        });
    }

    public static String getTeamNamesA(String name1) {
        return name1 = "This A";
    }

    public static String getTeamNamesB(String name2) {
        return name2 = "This A";
    }
}
