package io.github.timladenov.sportsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EnterTeamNames extends AppCompatActivity {

    Button toMain = null;

    EditText tmp = null;
    EditText nameA = null;
    EditText nameB = null;

    public String nameOfPlayerA = "";
    public String nameOfPlayerB = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_team_names);

        toMain = (Button) findViewById(R.id.toMain);

        toMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchMain = new Intent(EnterTeamNames.this, MainActivity.class);

                nameA = (EditText) findViewById(R.id.firstPlayer);
                nameOfPlayerA = nameA.getText().toString();

                switchMain.putExtra("nameOfPlayerA", nameOfPlayerA);

                nameB = (EditText) findViewById(R.id.secondPlayer);
                nameOfPlayerB = nameB.getText().toString();

                switchMain.putExtra("nameOfPlayerB", nameOfPlayerB);

                startActivity(switchMain);
                finish();
            }
        });
    }
}
