package io.github.timladenov.sportsapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import static io.github.timladenov.sportsapp.R.id.pointsA;

public class MainActivity extends AppCompatActivity {

    public int teamApoints = 0;
    public int teamBpoints = 0;

    public String pointsAteamStr = "";
    public String pointsBteamStr = "";

    public int gamesA = 0;
    public int gamesB = 0;

    public boolean canAdA = false;
    public boolean canAdB = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("teamApoints", teamApoints);
        savedInstanceState.putInt("teamBpoints", teamBpoints);
        savedInstanceState.putString("pointsAteamStr", pointsAteamStr);
        savedInstanceState.putString("pointsBteamStr", pointsBteamStr);
        savedInstanceState.putInt("gamesA", gamesA);
        savedInstanceState.putInt("gamesB", gamesB);
        savedInstanceState.putBoolean("canAdA",canAdA);
        savedInstanceState.putBoolean("canAdB", canAdB);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstaceState) {
        super.onSaveInstanceState(savedInstaceState);
        teamApoints = savedInstaceState.getInt("teamApoints");
        teamBpoints = savedInstaceState.getInt("teamBpoints");
        pointsAteamStr = savedInstaceState.getString("pointsAteamStr");
        pointsBteamStr = savedInstaceState.getString("pointsBteamStr");
        gamesA = savedInstaceState.getInt("gamesA");
        gamesB = savedInstaceState.getInt("gamesB");
        canAdA = savedInstaceState.getBoolean("canAdA");
        canAdB = savedInstaceState.getBoolean("canAdB");
        updateScreen();
    }


    public void newResult(View view) {
        int buttonID = view.getId();

        if(!(canAdA && canAdB)) {

            switch(buttonID) {

                case R.id.addPointsA:
                    if (teamApoints < 30) {
                        teamApoints += 15;
                        pointsAteamStr = Integer.toString(teamApoints);
                        returnResult(pointsAteamStr, buttonID);
                    } else {
                        teamApoints += 10;
                        pointsAteamStr = Integer.toString(teamApoints);
                        returnResult(pointsAteamStr, buttonID);

                        if(teamApoints == 40) {
                            canAdA = true;
                        }
                    }
                    break;

                case R.id.addPointsB:
                    if(teamBpoints < 30) {
                        teamBpoints += 15;
                        pointsBteamStr = Integer.toString(teamBpoints);
                        returnResult(pointsBteamStr, buttonID);
                    }
                    else {
                        teamBpoints += 10;
                        pointsBteamStr = Integer.toString(teamBpoints);
                        returnResult(pointsBteamStr, buttonID);

                        if(teamBpoints == 40) {
                            canAdB = true;
                        }
                    }
                    break;
            }
        }
        else if(canAdA && canAdB) {
            switch(buttonID) {

                case R.id.addPointsA:
                    TextView tmp = (TextView) findViewById(pointsA);
                    pointsAteamStr = "AD";
                    tmp.setText(pointsAteamStr);
                    canAdA = false;

                    returnResult(pointsAteamStr, R.id.addPointsB);
                    break;

                case R.id.addPointsB:
                    TextView tmp2 = (TextView) findViewById(R.id.pointsB);
                    pointsBteamStr = "AD";
                    tmp2.setText(pointsBteamStr);
                    canAdB = false;

                    returnResult(pointsBteamStr, R.id.addPointsA);
                    break;
            }
        }
    }

    public void reset(View view) {
        TextView txtA = (TextView) findViewById(R.id.pointsA);
        TextView txtB = (TextView) findViewById(R.id.pointsB);

        String viewTxtA = txtA.getText().toString();
        String viewTxtB = txtB.getText().toString();

        if(viewTxtA == "AD") {
            txtB = (TextView) findViewById(R.id.pointsB);
            txtB.setText("AD");

            txtA = (TextView) findViewById(R.id.pointsA);
            txtA.setText(Integer.toString(teamApoints));
        }
        else if(viewTxtB == "AD") {
            txtA = (TextView) findViewById(R.id.pointsA);
            txtA.setText("AD");

            txtB = (TextView) findViewById(R.id.pointsB);
            txtB.setText(Integer.toString(teamBpoints));
        }
        else {
            gamesA = 0;
            gamesB = 0;

            teamApoints = 0;
            teamBpoints = 0;

            pointsAteamStr = "0";
            pointsBteamStr = "0";

            txtA.setText(pointsAteamStr);
            txtA = (TextView) findViewById(R.id.teamAGames);
            txtA.setText(Integer.toString(gamesA));

            txtB.setText(pointsBteamStr);
            txtB = (TextView) findViewById(R.id.teamBGames);
            txtB.setText(Integer.toString(gamesB));
        }
    }

    public void returnResult(String points, int buttonID) {

        TextView pushResult = null;

        switch (buttonID) {

            case R.id.addPointsA:

                if(teamApoints <= 40) {
                    pushResult = (TextView) findViewById(pointsA);
                    pushResult.setText(pointsAteamStr);
                }

                else {
                    gamesA++;
                    pushResult = (TextView) findViewById(R.id.teamAGames);
                    pushResult.setText(Integer.toString(gamesA));

                    teamApoints = 0;
                    pointsAteamStr = "0";
                    pushResult = (TextView) findViewById(pointsA);
                    pushResult.setText(pointsAteamStr);

                    teamBpoints = 0;
                    pointsBteamStr = "0";
                    pushResult = (TextView) findViewById(R.id.pointsB);
                    pushResult.setText(pointsBteamStr);

                    canAdA = false;
                    canAdB = false;
                }
                break;

            case R.id.addPointsB:

                if(teamBpoints <= 40) {
                    pushResult = (TextView) findViewById(R.id.pointsB);
                    pushResult.setText(pointsBteamStr);
                }

                else {
                    gamesB++;
                    pushResult = (TextView) findViewById(R.id.teamBGames);
                    pushResult.setText(Integer.toString(gamesB));

                    teamApoints = 0;
                    pointsAteamStr = "0";
                    pushResult = (TextView) findViewById(pointsA);
                    pushResult.setText(pointsAteamStr);

                    teamBpoints = 0;
                    pointsBteamStr = "0";
                    pushResult = (TextView) findViewById(R.id.pointsB);
                    pushResult.setText(pointsBteamStr);

                    canAdA = false;
                    canAdB = false;
                }
                break;
        }
    }

    public void updateScreen() {
        TextView reloader = (TextView) findViewById(R.id.teamAGames);
        reloader.setText(Integer.toString(gamesA));
        reloader = (TextView) findViewById(R.id.teamBGames);
        reloader.setText(Integer.toString(gamesB));

        reloader = (TextView) findViewById(R.id.pointsA);
        reloader.setText(pointsAteamStr);
        reloader = (TextView) findViewById(R.id.pointsB);
        reloader.setText(pointsBteamStr);
    }
}
