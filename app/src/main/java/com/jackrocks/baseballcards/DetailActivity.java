package com.jackrocks.baseballcards;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    TextView mTxtName;
    TextView mTxtBrand;
    TextView mTxtYear;
    TextView mTxtTeam;
    TextView mTxtDescription;
    TextView mTxtPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mTxtName = (TextView) findViewById(R.id.textView_name);
        mTxtBrand = (TextView) findViewById(R.id.textView_brand);
        mTxtYear = (TextView) findViewById(R.id.textView_year);
        mTxtTeam = (TextView) findViewById(R.id.textView_team);
        mTxtDescription = (TextView) findViewById(R.id.textView_description);
        mTxtPrice = (TextView) findViewById(R.id.textView_price);


        Intent listIntent = getIntent();
        String Name = listIntent.getStringExtra("Name");
        String Brand = listIntent.getStringExtra("Brand");
        String Year = listIntent.getStringExtra("Year");
        String Team = listIntent.getStringExtra("Team");
        String Description = listIntent.getStringExtra("Description");
        String Price = listIntent.getStringExtra("Price");
        String formattedPrice = "$"+ Price;
        String realName = "Name :  " + Name;
        String realBrand = "Brand :  " + Brand;
        String realYear = "Year :  " + Year;
        String realTeam = "Team :  " + Team;
        String realDescription = "Description :  " + Description;
        String realformattedPrice = "Price :  " + formattedPrice;




        mTxtName.setText(realName);
        mTxtBrand.setText(realBrand);
        mTxtYear.setText(realYear);
        mTxtTeam.setText(realTeam);
        mTxtDescription.setText(realDescription);
        mTxtPrice.setText(realformattedPrice);




    }
}



