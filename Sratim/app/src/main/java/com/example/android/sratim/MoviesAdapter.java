package com.example.android.sratim;

/**
 * Created by Android on 21/03/2018.
 */

import android.widget.ArrayAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;

public class MoviesAdapter extends ArrayAdapter<BasicMovieInfo> {

    private LayoutInflater layoutInflater;

    public MoviesAdapter(Context context,ArrayList<BasicMovieInfo> movies) {
        super(context, 0, movies);
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Inflate layout and get controls:
        RelativeLayout relativeLayout =
                (RelativeLayout)layoutInflater.inflate(R.layout.movie_item, null);

        TextView textViewType = (TextView)relativeLayout.findViewById(R.id.textViewType);
        // Get current item:
        BasicMovieInfo movieInfo = getItem(position);


        textViewType.setText(movieInfo.getName());
        // Return created layout:
        return relativeLayout;
    }
}