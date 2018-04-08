package com.example.android.sratim;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class InternetNew extends AppCompatActivity {
private SingleMovieReaderController controller;
private  ListView listViewMovies;
 public    ArrayList<Movie> movies;
 public Context con;
 private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internet_new);
        listViewMovies = (ListView)findViewById(R.id.listViewMovies);
        controller = new SingleMovieReaderController(this);
        con = MyApp.getContext();

    }


    public void onClick_getItems(View V) {
        EditText search = (EditText) findViewById(R.id.search);
        String str = search.getText().toString();
        //after searching the api, making the listview of the relevant movies visible
        controller.readAllMovies(str);
        listViewMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //sending the item`s details to the Edit activity
                send(i);
            }
        });

        }

    public void onClick_Cancel(View V) {
        finish();
    }

    private void send(int i) {
        Intent intent = new Intent(this, Edit.class);
        movies = controller.giveMovies();
        movie = new Movie (movies.get(i).getSubject(), movies.get(i).getBody(), "http://image.tmdb.org/t/p/w185"+movies.get(i).getUrl());
        intent.putExtra("movie", movie);
        this.startActivityForResult(intent, 1);
        this.finish();
    }

}

