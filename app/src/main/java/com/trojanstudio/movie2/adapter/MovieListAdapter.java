package com.trojanstudio.movie2.adapter;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trojanstudio.movie2.DetailActivity;
import com.trojanstudio.movie2.R;
import com.trojanstudio.movie2.api.model.Movie;
import com.trojanstudio.movie2.databinding.MovieListItemBinding;
import com.trojanstudio.movie2.viewmodel.MovieListItemViewModel;

import java.util.List;

/**
 * Created by smjs2000 on 8/29/17.
 */
public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieViewHolder> {

    private List<Movie> movieList;

    public MovieListAdapter(List<Movie> movies) {
        this.movieList = movies;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.movie_list_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        holder.bind(movieList.get(position));
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder implements MovieListItemViewModel.ItemClickEvent {
        private MovieListItemBinding binding;
        private Movie movie;

        MovieViewHolder(View itemView) {
            super(itemView);
            this.binding = DataBindingUtil.bind(itemView);
        }

        void bind(Movie movie) {
            this.movie = movie;
            String smallBaseUrl = itemView.getContext().getString(R.string.image_base_url_small);
            MovieListItemViewModel viewModel = new MovieListItemViewModel(smallBaseUrl + movie.getPosterPath());
            viewModel.setItemClickEvent(this);
            binding.setViewModel(viewModel);
        }

        @Override
        public void onItemClick() {
            Intent intent = new Intent(itemView.getContext(), DetailActivity.class);
            intent.putExtra(DetailActivity.KEY_MOVIE_OBJECT, movie);
            itemView.getContext().startActivity(intent);
        }
    }
}
