package com.example.android.sratim;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Administrator on 15/03/2018.
 */

public class Database extends SQLiteOpenHelper {

    public Database() {
        super(MyApp.getContext(), "database", null, 1);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Movies(_id INTEGER PRIMARY KEY AUTOINCREMENT, subject TEXT NOT NULL, body TEXT , url TEXT )");

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE Movies");
        onCreate(db);
    }

    // Adding a new movie
    public void addMovie(Movie movie) {
        String sql = String.format("INSERT INTO Movies(subject, body, url) VALUES('%s','%s', '%s')", movie.getSubject(), movie.getBody(), movie.getUrl(), movie.get_id());
        SQLiteDatabase db = getWritableDatabase(); // Open connection.
        db.execSQL(sql);
        Cursor cursor = db.rawQuery("SELECT last_insert_rowid()", null);
        cursor.moveToNext();
        int id = cursor.getInt(0);
        movie.set_id(id);
        cursor.close();
        db.close(); // Close connection.

    }
    //updating an existing movie
    public void updateMovie(Movie movie) {
        String sql = String.format("UPDATE Movies SET subject='%s',body='%s', url='%s' WHERE _id=%d", movie.getSubject(), movie.getBody(), movie.getUrl(), movie.get_id());
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
        db.close();
    }

    // Delete an existing movie
    public void deleteMovie(Movie movie) {
        String sql = String.format("DELETE FROM Movies WHERE _id=%d", movie.get_id());
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
        db.close();
    }

    //when onCreate is called, the database loads all the movie`s detail`s to the ArrayList "Movies" to send it to the Main.Activity use
        public ArrayList<Movie> getAllMovies() {

        ArrayList<Movie> Movies = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Movies", null);

        // Take indices of all columns:
        int _id = cursor.getColumnIndex("_id");
        int subjectI = cursor.getColumnIndex("subject");
        int bodyI = cursor.getColumnIndex("body");
        int urlI = cursor.getColumnIndex("url");
        // Run on all rows, create product from each row:
        while(cursor.moveToNext()) {
            int id = cursor.getInt(_id);
            String subject = cursor.getString(subjectI);
            String body = cursor.getString(bodyI);
            String url = cursor.getString(urlI);
            Movie movie = new Movie(id ,subject, body, url);
            Movies.add(movie);
        }

        cursor.close();
        db.close();

        return Movies;
    }
}
