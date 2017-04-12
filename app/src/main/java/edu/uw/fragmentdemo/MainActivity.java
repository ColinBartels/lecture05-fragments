package edu.uw.fragmentdemo;

import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements MoviesFragment.OnMovieSelectedListener, SearchFragment.OnSearchSubmittedListener {

    private static final String TAG = "MainActivity";
    private int pages = 1;

    private ViewPager pager;
    private PagerAdapter pagerAdapter;

    SearchFragment searchFragment;
    DetailFragment detailFragment;
    MoviesFragment moviesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instantiate a ViewPager and a PagerAdapter.
        pager = (ViewPager) findViewById(R.id.container);
        pagerAdapter = new MoviePagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);

        searchFragment = SearchFragment.newInstance();
        pagerAdapter.notifyDataSetChanged();
        pager.setCurrentItem(0);
        pages = 1;
    }

    @Override
    public void onMovieSelected(Movie movie) {
        detailFragment = DetailFragment.newInstance(movie.toString(), movie.imdbId);
        pager.setCurrentItem(2);
        pages = 3;
        pagerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onSearchSubmitted(String searchTerm) {
        moviesFragment = MoviesFragment.newInstance(searchTerm);
        pager.setCurrentItem(1);
        pages = 2;
        pagerAdapter.notifyDataSetChanged();
    }

    private class MoviePagerAdapter extends FragmentStatePagerAdapter {

        public MoviePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return searchFragment;
                case 1:
                    return moviesFragment;
                case 2:
                    return detailFragment;
                default:
                    return searchFragment;
            }
        }

        @Override
        public int getCount() {
            return pages;
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }
    }
}
