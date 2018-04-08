package com.example.android.sratim;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class Edit extends AppCompatActivity {
    private Button OK;
    private Button Cancel;
    private Button show;
    private EditText subject;
    private EditText body;
    private EditText url;
    private Movie movie;
    private ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        subject = (EditText) findViewById(R.id.subject);
        body = (EditText) findViewById(R.id.body);
        url = (EditText) findViewById(R.id.url);
        movie = (Movie) getIntent().getSerializableExtra("movie");
        //When getting from the InternetNew activity, the SerializableExtra "movie" is "null", so we need to check where we got it from, and of from Internet,
        //create a new instance of it
        if(!(movie==null)) {
            subject.setText(movie.getSubject());
            body.setText(movie.getBody());
            url.setText(movie.getUrl());
        }
        else {
            movie = new Movie(subject.getText().toString(), body.getText().toString(), url.getText().toString());
        }
        OK = (Button) findViewById((R.id.Ok));
        OK.setText(R.string.OK);
        Cancel = (Button) findViewById((R.id.Cancel));
        Cancel.setText(R.string.Cancel);
        show = (Button) findViewById(R.id.show);
        image = (ImageView) findViewById(R.id.image);
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Picasso.with(Edit.this).load(url.getText().toString()).into(image);
            }
        });
        View.OnClickListener myOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Result(view);
            }
        };
        OK.setOnClickListener(myOnClickListener);
        Cancel.setOnClickListener(myOnClickListener);

    }
    public void Result(View view) {
        String Click = (String) ((Button) view).getText();
        switch (Click) {
            case "OK": {
                //checking the subject isn`t empty
                if(!subject.getText().toString().equals("")) {
                    movie.setSubject(subject.getText().toString());
                    movie.setBody(body.getText().toString());
                    movie.setUrl(url.getText().toString());
                    Intent returnIntent = getIntent();
                    returnIntent.putExtra("movie", movie);
                    setResult(RESULT_OK, returnIntent);
                    finish();
                }
                else
                    Toast.makeText(Edit.this, "Enter Subject and then press OK button",
                            Toast.LENGTH_LONG).show();


            } break;
            case "Cancel": {
                Intent returnIntent = getIntent();
                setResult(RESULT_CANCELED, returnIntent);
                finish();

            } break;
        }

    }

}
