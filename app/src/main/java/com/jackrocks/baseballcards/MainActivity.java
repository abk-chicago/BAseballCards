package com.jackrocks.baseballcards;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private Intent mExplore;
    private Intent mInfo


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHelper db = new DatabaseHelper(this);


        Button btn_Explore = (Button) findViewById(R.id.btn_Explore);
        mExplore = new Intent(MainActivity.this, ListActivity.class);
        View.OnClickListener ExploreView = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(mExplore);
            }
        };

        btn_Explore.setOnClickListener(ExploreView);


        Button btn_More_Info = (Button) findViewById(R.id.btn_More_Info);
        mInfo = new Intent(MainActivity.this, InfoActivity.class);
        View.OnClickListener InfoView =


    }
}
