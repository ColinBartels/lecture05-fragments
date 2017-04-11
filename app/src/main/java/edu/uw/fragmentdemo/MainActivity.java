package edu.uw.fragmentdemo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import static edu.uw.fragmentdemo.MovieDownloader.downloadMovieData;

public class MainActivity extends AppCompatActivity implements MoviesFragment.OnMovieClickListener {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //respond to search button clicking
    public void handleSearchClick(View v){
        EditText text = (EditText)findViewById(R.id.txtSearch);
        String searchTerm = text.getText().toString();

        MoviesFragment frag = MoviesFragment.newInstance(searchTerm);
        FragmentManager fm = getSupportFragmentManager();

        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.container, frag, "MovieFragment");
        ft.commit();
    }

    public void onMovieClick(Movie movie) {
        DetailFragment frag = DetailFragment.newInstance(movie);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, frag, "DetailFragment")
                .addToBackStack(null)
                .commit();
    }
}
