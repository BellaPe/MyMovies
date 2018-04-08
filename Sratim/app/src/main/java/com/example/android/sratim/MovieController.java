package com.example.android.sratim;

import android.app.Activity;
import android.app.ProgressDialog;
import android.widget.ListView;
import android.widget.Toast;

public abstract class MovieController implements httpRequest.Callbacks {

    protected Activity activity; // The main activity.
    protected ProgressDialog progressDialog; // Progress dialog.
    protected ListView listViewMovies; // The main List for updating the countries list.

    // constractor
    public MovieController(Activity activity) {
        this.activity = activity;
        listViewMovies = (ListView)activity.findViewById(R.id.listViewMovies);
        progressDialog = new ProgressDialog(activity);
        progressDialog.setTitle("Downloading...");
        progressDialog.setMessage("Please Wait...");
    }

    // Server access is about to start - show progress dialog:
    public void onAboutToStart() {
        progressDialog.show();
    }

    // Got error from server - show toast and dismiss dialog:
    public void onError(String errorMessage) {
        Toast.makeText(activity, "Error: " + errorMessage, Toast.LENGTH_LONG).show();
        progressDialog.dismiss();
    }
}