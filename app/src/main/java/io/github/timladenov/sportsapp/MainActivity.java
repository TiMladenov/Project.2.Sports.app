/*
* @author   Tihomir Mladenov <tihomir.mladenov777@gmail.com>
*           This application has been created for Project 2 on Udacity's Google sponsored "Android For Beginners" course;
*           18 March 2017
*
* @version  v2.2 final 
* @since    v1.0a
* */

package io.github.timladenov.sportsapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int teamApoints;
    private int teamBpoints;
    private String pointsAteamStr;
    private String pointsBteamStr;
    private int gamesA;
    private int gamesB;
    private int deuceCount;
    private boolean canAdA = false;
    private boolean canAdB = false;
    private TextView setIntentData;
    private TextView pointsTeamA;
    private TextView pointsTeamB;
    private TextView gamesTeamA;
    private TextView gamesTeamB;

    /*
    *
    * @param teamApoints    stores the points of the first player in an integer;
    * @param teamBpoints    stores the points of the second player in an integer;
    * @param pointsAteamStr stores the points of the first player in a String;
    * @param pointsBteamStr stores the points of the second player in a String;
    * @param gamesA         stores the number of won games by the first player;
    * @param gamesB         stores the number of won games by the second player;
    * @param deuceCount     stores the number of deuces;
    * @param canAdA         stores value True if player A has 40 points and can have an advantage;
    * @param canAdB         stores value True if player B has 40 points and can have an advantage;
    * @param setIntentData  gets players' names from the Intent, sets them in their TextViews;
    * @param pointsTeamA    updates the point TextViews of Player A;
    * @param pointsTeamB    updates the point TextViews of Player B;
    * @param gamesTeamA     updates the games won TextViews of Player A;
    * @param gamesTeamB     updates the games won TextViews of Player B;
    *
    * The method initiates the above mentioned parameters. Then calls method updateScreen() to
    * update the TextViews on the screen.
    *
    * */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        teamApoints = 0;
        teamBpoints = 0;
        pointsAteamStr = "0";
        pointsBteamStr = "0";
        gamesA = 0;
        gamesB = 0;
        deuceCount = 1;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setIntentData = (TextView) findViewById(R.id.TeamAName);
        setIntentData.setText(getIntent().getExtras().getString("nameOfPlayerA"));
        setIntentData = (TextView) findViewById(R.id.TeamBName);
        setIntentData.setText(getIntent().getExtras().getString("nameOfPlayerB"));
        pointsTeamA = (TextView) findViewById(R.id.pointsA);
        pointsTeamB = (TextView) findViewById(R.id.pointsB);
        gamesTeamA = (TextView) findViewById(R.id.teamAGames);
        gamesTeamB = (TextView) findViewById(R.id.teamBGames);
        updateScreen();
    }

    /*
    *
    * The method restores saved data on screen rotation.
    * */

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("teamApoints", teamApoints);
        savedInstanceState.putInt("teamBpoints", teamBpoints);
        savedInstanceState.putString("pointsAteamStr", pointsAteamStr);
        savedInstanceState.putString("pointsBteamStr", pointsBteamStr);
        savedInstanceState.putInt("deuceCount", deuceCount);
        savedInstanceState.putInt("gamesA", gamesA);
        savedInstanceState.putInt("gamesB", gamesB);
        savedInstanceState.putBoolean("canAdA", canAdA);
        savedInstanceState.putBoolean("canAdB", canAdB);
        super.onSaveInstanceState(savedInstanceState);
    }

    /*
    *
    * The method saves the data on screen rotation;
    * */

    @Override
    protected void onRestoreInstanceState(Bundle savedInstaceState) {
        super.onSaveInstanceState(savedInstaceState);
        teamApoints = savedInstaceState.getInt("teamApoints");
        teamBpoints = savedInstaceState.getInt("teamBpoints");
        pointsAteamStr = savedInstaceState.getString("pointsAteamStr");
        pointsBteamStr = savedInstaceState.getString("pointsBteamStr");
        deuceCount = savedInstaceState.getInt("deuceCount");
        gamesA = savedInstaceState.getInt("gamesA");
        gamesB = savedInstaceState.getInt("gamesB");
        canAdA = savedInstaceState.getBoolean("canAdA");
        canAdB = savedInstaceState.getBoolean("canAdB");
        updateScreen();
    }

    /*
    *
    * The method is called from "Add Point" button. If the points of both players are different,
    * the method will simply iterate to 40 and then add game point to the winning player.
    * It calls method returnResult() to do this job.
    *
    * However, if the points to both players are equal, and are equal to 40, then the leading
    * player will receive the Advantage. If that player loses his advantage, method swap() is called,
    * which swaps the advantages and counts the number of deuces.
    *
    * @see  onCreate documentation for information on variables;
    * */


    public void newResult(View view) {
        int buttonID = view.getId();
        if (!(canAdA && canAdB)) {
            switch (buttonID) {
                case R.id.addPointsA:
                    if (teamApoints < 30 && pointsAteamStr != "AD") {
                        teamApoints += 15;
                        pointsAteamStr = Integer.toString(teamApoints);
                        returnResult(buttonID);
                    } else {
                        if (pointsBteamStr != "AD") {
                            teamApoints += 10;
                            pointsAteamStr = Integer.toString(teamApoints);
                            returnResult(buttonID);
                            // Can have an advantage if the other player too has 40 points;
                            if (teamApoints == 40) {
                                canAdA = true;
                            }
                            // Swaps advantages;
                        } else if (pointsBteamStr == "AD") {
                            swap();
                        }
                    }
                    break;
                case R.id.addPointsB:
                    if (teamBpoints < 30 && pointsBteamStr != "AD") {
                        teamBpoints += 15;
                        pointsBteamStr = Integer.toString(teamBpoints);
                        returnResult(buttonID);
                    } else {
                        if (pointsAteamStr != "AD") {
                            teamBpoints += 10;
                            pointsBteamStr = Integer.toString(teamBpoints);
                            returnResult(buttonID);
                            // Can have an advantage if the other player too has 40 points;
                            if (teamBpoints == 40) {
                                canAdB = true;
                            }
                            // Swaps advantages;
                        } else if (pointsAteamStr == "AD") {
                            swap();
                        }
                    }
                    break;
            }
            // Sets advantage to the leading player when both have 40 points. Sets the score to the
            // player who lost his advantage;
        } else if (canAdA && canAdB) {
            switch (buttonID) {
                case R.id.addPointsA:
                    pointsAteamStr = "AD";
                    pointsTeamA.setText(pointsAteamStr);
                    canAdA = false;
                    returnResult(R.id.addPointsB);
                    break;
                case R.id.addPointsB:
                    pointsBteamStr = "AD";
                    pointsTeamB.setText(pointsBteamStr);
                    canAdB = false;
                    returnResult(R.id.addPointsA);
                    break;
            }
        }
    }

    /*
    *
    * Sets advantage to the leading player. Sets last score before the advantage of the loosing
    * player. Displays Toast with @param deuceMessage with deuce count.
    *
    * Called by newResult() method.
    *
    * @see  onCreate documentation for information on variables;
    * */

    private void swap() {
        String viewTxtA = pointsTeamA.getText().toString();
        String viewTxtB = pointsTeamB.getText().toString();
        Toast deuceMessage = null;
        if (viewTxtA == "AD") {
            deuceCount++;
            pointsBteamStr = "AD";
            pointsTeamB.setText(pointsBteamStr);
            pointsAteamStr = Integer.toString(teamApoints);
            pointsTeamA.setText(pointsAteamStr);
        } else if (viewTxtB == "AD") {
            pointsAteamStr = "AD";
            deuceCount++;
            pointsTeamA.setText(pointsAteamStr);
            pointsBteamStr = Integer.toString(teamBpoints);
            pointsTeamB.setText(pointsBteamStr);
        }
        deuceMessage = Toast.makeText(this, getResources().getString(R.string.deuceCounter) + Integer.toString(deuceCount), Toast.LENGTH_SHORT);
        deuceMessage.show();
    }

    /*
    * Resets the game score. Called by Rest button.
    *
    * @see  onCreate documentation for information on variables;
    * */

    public void reset(View view) {
        gamesA = 0;
        gamesB = 0;
        teamApoints = 0;
        teamBpoints = 0;
        deuceCount = 1;
        pointsAteamStr = "0";
        pointsBteamStr = "0";
        canAdA = false;
        canAdB = false;
        pointsTeamA.setText(pointsAteamStr);
        gamesTeamA.setText(Integer.toString(gamesA));
        pointsTeamB.setText(pointsBteamStr);
        gamesTeamB.setText(Integer.toString(gamesB));
    }

    /*
    *
    * The method receives the ID of the button, corresponding to Add point for Player A or B.
    * If the score is less than 40, it sets the score of the player at @param teamApoints or
    * @param teamBpoints, at @param pointsTeamA or @param pointsTeamB for Player B respectively.
    *
    * If the score is more than 40, it iterates won games count of the winning player and resets
    * the score view, and the advantage booleans.
    *
    * Called by newResult() method.
    *
    * @see  onCreate documentation for information on variables;
    * */

    private void returnResult(int buttonID) {
        switch (buttonID) {
            case R.id.addPointsA:
                if (teamApoints <= 40) {
                    pointsTeamA.setText(pointsAteamStr);
                } else {
                    gamesA++;
                    gamesTeamA.setText(Integer.toString(gamesA));
                    teamApoints = 0;
                    pointsAteamStr = "0";
                    pointsTeamA.setText(pointsAteamStr);
                    teamBpoints = 0;
                    deuceCount = 1;
                    pointsBteamStr = "0";
                    pointsTeamB.setText(pointsBteamStr);
                    canAdA = false;
                    canAdB = false;
                }
                break;
            case R.id.addPointsB:
                if (teamBpoints <= 40) {
                    pointsTeamB.setText(pointsBteamStr);
                } else {
                    gamesB++;
                    gamesTeamB.setText(Integer.toString(gamesB));
                    teamApoints = 0;
                    pointsAteamStr = "0";
                    pointsTeamA.setText(pointsAteamStr);
                    teamBpoints = 0;
                    deuceCount = 1;
                    pointsBteamStr = "0";
                    pointsTeamB.setText(pointsBteamStr);
                    canAdA = false;
                    canAdB = false;
                }
                break;
        }
    }

    /*
    * Updates the text on the screen with the saved data, when the device is rotated.
    *
    * @see  onCreate documentation for information on variables;
    * */

    private void updateScreen() {
        pointsTeamA.setText(pointsAteamStr);
        pointsTeamB.setText(pointsBteamStr);
        gamesTeamA.setText(Integer.toString(gamesA));
        gamesTeamB.setText(Integer.toString(gamesB));
    }
}
