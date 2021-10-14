package com.sumin.mymovies;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.sumin.mymovies.data.FavoriteMovie;
import com.sumin.mymovies.data.MainViewModel;
import com.sumin.mymovies.data.Movie;

public class DetailActivity extends AppCompatActivity {


    private ImageView imageViewAddToFavorite;
    private ImageView imageViewBigPoster;
    private TextView textViewTitle;
    private TextView textViewOriginalTitle;
    private TextView textViewRating;
    private TextView textViewReleaseDate;
    private TextView textViewOverview;

    private int id;

    private MainViewModel viewModel;
    private Movie movie;
    private FavoriteMovie favoriteMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imageViewAddToFavorite = findViewById(R.id.imageViewAddToFavorite);
        imageViewBigPoster = findViewById(R.id.imageViewBigPoster);
        textViewTitle = findViewById(R.id.textViewTitle);
        textViewOriginalTitle = findViewById(R.id.textViewOriginalTitle);
        textViewRating = findViewById(R.id.textViewRating);
        textViewReleaseDate = findViewById(R.id.textViewRealiseDate);
        textViewOverview = findViewById(R.id.textViewOverview);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("id")) {
            id = intent.getIntExtra("id", -1);
        } else {
            finish();
        }

        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        movie = viewModel.getMovieById(id);
        Picasso.get().load(movie.getBigPosterPath()).into(imageViewBigPoster);
        textViewTitle.setText(movie.getTitle());
        textViewOriginalTitle.setText(movie.getOriginalTitle());
        textViewOverview.setText(movie.getOverview());
        textViewReleaseDate.setText(movie.getReleaseDate());
        textViewRating.setText(Double.toString(movie.getVoteAverage()));
        setFavoriteMovie();
    }

    public void onClickChangeFavorite(View view) {
        favoriteMovie = viewModel.getFavoriteMovieById(id);
        if (favoriteMovie == null) {
            viewModel.insertFavoriteMovie(new FavoriteMovie(movie));
            Toast.makeText(this, "Добавлено в избранное", Toast.LENGTH_SHORT).show();
        } else {
            viewModel.deleteFavoriteMovie(favoriteMovie);
            Toast.makeText(this, "Удалено из избранное", Toast.LENGTH_SHORT).show();
        }
        setFavoriteMovie();
    }

    private void setFavoriteMovie() {
        favoriteMovie = viewModel.getFavoriteMovieById(id);
        if (favoriteMovie == null) {
            imageViewAddToFavorite.setImageResource(R.drawable.favourite_add_to);
        } else {
            imageViewAddToFavorite.setImageResource(R.drawable.favourite_remove);
        }
    }
}
