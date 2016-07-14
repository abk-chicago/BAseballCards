package com.jackrocks.baseballcards;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class ShoppingCartScrollingActivity extends AppCompatActivity {

    private ArrayAdapter<String> mArrayAdapter;
    private ListView mList;
    private ShoppingCart mCart;
    private AdapterView.OnItemLongClickListener onItemClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCart = ShoppingCart.getInstance();

        onItemClickListener = new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                mCart.remove(position);
                mArrayAdapter.notifyDataSetChanged();
                return false;
            }
        };
        setContentView(R.layout.activity_shopping_cart_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // arraylist<String> comes from mCart variable

        mList = (ListView) findViewById(R.id.shopping_cart_list_view);
        mArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mCart);
        mList.setAdapter(mArrayAdapter);
        mList.setOnItemLongClickListener(onItemClickListener);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
