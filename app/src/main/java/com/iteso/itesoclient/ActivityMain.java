package com.iteso.itesoclient;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ActivityMain extends AppCompatActivity {
    Uri uri = Uri.parse("content://com.iteso.examen2.database.ItemProductControl/Products");
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.activity_main_menu,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem){
        LinearLayoutManager layoutManager
            = new LinearLayoutManager(ActivityMain.this, LinearLayoutManager.VERTICAL, false);

        ArrayList <String> strings = new ArrayList<>();
        int id = menuItem.getItemId();
        int index;
        int value;

        Cursor cursor = getContentResolver().query(uri,null,null,null,null);
        if (cursor !=null){
            cursor.moveToFirst();
            do {
                index = cursor.getColumnIndex("PRODUCT_TITLE");
                value = cursor.getInt(index);
                if(value == id){
                    recyclerView.setHasFixedSize(true);
                    strings.add(cursor.getString(index));
                }
            }while (cursor.moveToNext());
        }
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        return super.onOptionsItemSelected(menuItem);
    }
    public class ItemCursorAdapter extends CursorAdapter {
        @Override
        public ItemCursorAdapter(){

        }
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            return  LayoutInflater.from(context).inflate(R.layout.item_list,parent,false);
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            TextView textView = (TextView) view.findViewById(R.id.client_item_list);

            textView.setText(cursor.getString());
        }
    }
}

