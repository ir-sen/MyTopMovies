package com.sumin.mymovies.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.sumin.mymovies.R;
import com.sumin.mymovies.data.Movie;

import java.util.ArrayList;
import java.util.List;


public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    private List<Movie> searchMovies;

    private MovieAdapter.OnPosterClickListener onPosterClickListener;

    public SearchAdapter() {
        searchMovies = new ArrayList<>();
    }

    public List<Movie> getSearchMovies() {
        return searchMovies;
    }

    public MovieAdapter.OnPosterClickListener getOnPosterClickListener() {
        return onPosterClickListener;
    }

    public void setSearchMovies(List<Movie> searchMovies) {
        this.searchMovies = searchMovies;
    }

    public void setOnPosterClickListener(MovieAdapter.OnPosterClickListener onPosterClickListener) {
        this.onPosterClickListener = onPosterClickListener;
    }


    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movie_item,
                viewGroup,
                false);

        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder searchViewHolder, int i) {
        Movie movie = searchMovies.get(i);
        Picasso.get().load(movie.getPosterPath()).into(searchViewHolder.imageSmallPoster);
    }

    @Override
    public int getItemCount() {
        return searchMovies.size();
    }

    class SearchViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageSmallPoster;
        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            imageSmallPoster = itemView.findViewById(R.id.imageViewSmallPoster);
            itemView.setOnClickListener(v -> {
                if (onPosterClickListener != null) {
                    onPosterClickListener.onPosterClick(getAdapterPosition());
                }
            });
        }
    }


    public void setMovies(List<Movie> movies) {
        this.searchMovies = movies;
        notifyDataSetChanged();
    }
    // для добавления элемента без надобности обновления всего массива
    public void addMovies(List<Movie> movies) {
        this.searchMovies.addAll(movies);
        notifyDataSetChanged();
    }

    public List<Movie> getMovies() {
        return searchMovies;
    }
}
