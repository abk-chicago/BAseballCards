package com.jackrocks.baseballcards;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.StringBuilderPrinter;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    TextView mTxtName;
    TextView mTxtBrand;
    TextView mTxtYear;
    TextView mTxtTeam;
    TextView mTxtDescription;
    TextView mTxtPrice;
    private Intent mCheckout;

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



        Button btn_ShoppingCart =  (Button) findViewById(R.id.btn_Shoppingcart);
        mCheckout = new Intent(DetailActivity.this, ShoppingCartScrollingActivity.class);
        View.OnClickListener CheckoutView = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(mCheckout);
            }
        };

        btn_ShoppingCart.setOnClickListener(CheckoutView);


        ShoppingCart cart = ShoppingCart.getInstance();
        //cart.add(realName + " - " + realTeam + " @ " + realformattedPrice);
        //now...
        cart.add(formattedListCard(realName, realTeam, realformattedPrice));
        cart.add(formattedListCard(realName, realTeam, realformattedPrice));
        cart.add(formattedListCard(realName, realTeam, realformattedPrice));




    }


    private String formattedListCard(String name,String team, String price) {
        StringBuilder sb = new StringBuilder();
        //        cart.add(realName + " - " + realTeam + " @ " + realformattedPrice);
        sb.append(name);
        sb.append(" - ");
        sb.append(team);
        sb.append(" : ");
        sb.append(price);
        return sb.toString();
    }
}



