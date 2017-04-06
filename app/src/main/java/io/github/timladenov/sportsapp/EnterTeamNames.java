/*
* @author   Tihomri Mladenov <tihomir.mladenov777@gmail.com>
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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EnterTeamNames extends AppCompatActivity {

    /*
    *
    * @param nameA          EditText for first players' names;
    * @param nameB          EditText for second players' names;
    * @param nameOfPlayerA  obtains the name of the first player from nameA;
    * @param nameOfPlayerB  obtains the name of the second player from nameB;
    * @param toMain         starts MainActivity and passes players' names with an Intent;
    * @param toasMsgTxt     stores warning to enter both players' names;
    * @param errMsg         displays warning if nameA or nameB is blank;
    * @param switchMain     passes the names of the players to Main activity and initiates it;
    *
    * The method obtains the names of the players. If only one or none of the names is /are entered,
    * a warning Toast message with @param errMsg will be thrown.
    * When both names are entered, they will be passed to MainActivity with @param switchMain,
    * which will also initiate the new activity.
    *
    * */

    public String nameOfPlayerA = "";
    public String nameOfPlayerB = "";
    public Button toMain = null;
    public EditText nameA = null;
    public EditText nameB = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_team_names);

        toMain = (Button) findViewById(R.id.toMain);
        nameA = (EditText) findViewById(R.id.firstPlayer);
        nameB = (EditText) findViewById(R.id.secondPlayer);
        final String toasMsgTxt = getResources().getString(R.string.namesErrMsg);

        toMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameOfPlayerA = nameA.getText().toString();
                nameOfPlayerB = nameB.getText().toString();
                if (nameOfPlayerA.equals("") || nameOfPlayerB.equals("")) {
                    Toast errMsg = Toast.makeText(getApplicationContext(), toasMsgTxt, Toast.LENGTH_SHORT);
                    errMsg.show();
                } else {
                    Intent switchMain = new Intent(EnterTeamNames.this, MainActivity.class);
                    switchMain.putExtra("nameOfPlayerA", nameOfPlayerA);
                    switchMain.putExtra("nameOfPlayerB", nameOfPlayerB);
                    startActivity(switchMain);
                    finish();
                }
            }
        });
    }
}
