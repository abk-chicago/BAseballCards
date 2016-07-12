package com.jackrocks.baseballcards;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ListActivity extends AppCompatActivity {
    private DatabaseHelper mHelper;
    Intent mDetailIntent;
    ListView CardsListView;
    Cursor mCursor;
    AdapterView.OnItemClickListener mClickListener;
     CursorAdapter mCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_);


        DatabaseHelper db = new DatabaseHelper(this);


        mCursor = db.getSports();
        CardsListView = (ListView) findViewById(R.id.list_of_cards);

        mCursorAdapter = new CursorAdapter(ListActivity.this, mCursor, 0) {
            @Override
            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                return LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1,
                        parent, false);
            }

            @Override
            public void bindView(View view, Context context, Cursor cursor) {
                TextView txt = (TextView) view.findViewById(android.R.id.text1);
                String rowData = cursor.getString(cursor.getColumnIndex("Name")) + "   $" + cursor.getString(cursor.getColumnIndex("Price"));
                txt.setText(rowData);

            }
        };




        mClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mDetailIntent = new Intent(ListActivity.this, DetailActivity.class);

                String Name = mCursor.getString(mCursor.getColumnIndex("Name"));
                String Brand = mCursor.getString(mCursor.getColumnIndex("Brand"));
                String Year = mCursor.getString(mCursor.getColumnIndex("Year"));
                String Team = mCursor.getString(mCursor.getColumnIndex("Team"));
                String Description = mCursor.getString(mCursor.getColumnIndex("Description"));
                String Price = mCursor.getString(mCursor.getColumnIndex("Price"));

                mDetailIntent.putExtra("Name", Name);
                mDetailIntent.putExtra("Brand", Brand);
                mDetailIntent.putExtra("Year", Year);
                mDetailIntent.putExtra("Team", Team);
                mDetailIntent.putExtra("Description", Description);
                mDetailIntent.putExtra("Price", Price);
                startActivity(mDetailIntent);

            }
        };


        CardsListView.setAdapter(mCursorAdapter);
        handleIntent(getIntent());
        CardsListView.setOnItemClickListener(mClickListener);



        }

        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            MenuInflater SearchInflater = getMenuInflater();
            SearchInflater.inflate(R.menu.options_menu, menu);

            SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
            SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

            return true;
        }

        @Override
        public void onNewIntent (Intent intent){
            handleIntent(intent);
        }


    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            mHelper = new DatabaseHelper(getApplicationContext());
            if(query!=null){
            mCursor = mHelper.getSearchResults(query);
                }

            Toast.makeText(ListActivity.this, "Searching for: " + query, Toast.LENGTH_SHORT).show();
            mCursorAdapter.changeCursor(mCursor);
            mCursorAdapter.notifyDataSetChanged();


        }


    }





}


