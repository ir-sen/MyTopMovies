package com.sumin.mymovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

import com.sumin.mymovies.adapters.MovieAdapter;
import com.sumin.mymovies.adapters.SearchAdapter;
import com.sumin.mymovies.data.Movie;
import com.sumin.mymovies.utils.JSONUtils;
import com.sumin.mymovies.utils.NetworkUtils;

import org.json.JSONObject;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SearchAdapter searchAdapter;
    String search_movie = "";

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.itemMain:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;

            case R.id.itemFavorite:
                Intent intentToFavorite = new Intent(this, FavouriteActivity.class);
                startActivity(intentToFavorite);
                break;

            case R.id.search_item_m:
                SearchView searchView = (SearchView) item.getActionView();
                searchView.setQueryHint("Search movie");
                final String[] movieSearch = {""};
                Intent intentSearch = new Intent(this, SearchActivity.class);
                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){

                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        intentSearch.putExtra("KEY_SEARCH", query);
                        startActivity(intentSearch);
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        return false;
                    }
                });
                //intentSearch.putExtra("KEY_SEARCH", movieSearch[0]);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Bundle arguments = getIntent().getExtras();
        if(arguments != null) {
            search_movie = arguments.get("KEY_SEARCH").toString();
        }
        recyclerView = findViewById(R.id.recyclerViewSearch);
        searchAdapter = new SearchAdapter();
        recyclerView.setLayoutManager(new GridLayoutManager(this, getColumnCount()));
        JSONObject jsonObject = NetworkUtils.getJSONFromSearch(search_movie);
        ArrayList<Movie> movies = JSONUtils.getMoviesFromJSON(jsonObject);
        searchAdapter.setMovies(movies);
        recyclerView.setAdapter(searchAdapter);

        searchAdapter.setOnPosterClickListener(new MovieAdapter.OnPosterClickListener() {
            @Override
            public void onPosterClick(int position) {
                Movie movie = searchAdapter.getMovies().get(position);
                Intent intent = new Intent(SearchActivity.this, DetailActivity.class);
                intent.putExtra("id", movie.getId());
                startActivity(intent);
            }
        });
    }

    private int getColumnCount() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        // получения независимых пикселей
        int width = (int) (displayMetrics.widthPixels / displayMetrics.density);
        return width / 185 > 2 ? width / 185 : 2;
    }



}