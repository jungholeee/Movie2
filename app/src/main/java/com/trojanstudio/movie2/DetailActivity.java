package com.trojanstudio.movie2;

import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.trojanstudio.movie2.api.model.Movie;
import com.trojanstudio.movie2.databinding.ActivityDetailBinding;
import com.trojanstudio.movie2.viewmodel.MovieDetailViewModel;

/**
 * Created by smjs2000 on 8/29/17.
 */
public class DetailActivity extends AppCompatActivity implements MovieDetailViewModel.DetailButtonEvent {

    public static final String KEY_MOVIE_OBJECT = "MOVIE_OBJECT";

    private String title;
    private String imageUrl;
    private String overview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        setSupportActionBar(binding.detailToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Movie movie = getIntent().getParcelableExtra(KEY_MOVIE_OBJECT);
        title = movie.getTitle();
        imageUrl = getString(R.string.image_base_url_large) + movie.getPosterPath();
        overview = movie.getOverview();

        MovieDetailViewModel viewModel =
                new MovieDetailViewModel(title, imageUrl, overview);
        viewModel.setDetailButtonEvent(this);
        binding.setViewModel(viewModel);
        binding.detailMovieOverview.setViewModel(viewModel);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onButtonClick() {
        Intent intent = new Intent(Intent.ACTION_SENDTO);

        // check if there's any supported email app
        ComponentName emailApp = intent.resolveActivity(getPackageManager());
        ComponentName unsupportedAction = ComponentName.unflattenFromString("com.android.fallback/.Fallback");
        boolean hasEmailApp = emailApp != null && !emailApp.equals(unsupportedAction);

        if (hasEmailApp) {
            intent.setData(Uri.parse("mailto:"));
            intent.putExtra(Intent.EXTRA_SUBJECT, title);
            intent.putExtra(Intent.EXTRA_TEXT, overview);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }
        else {
            new AlertDialog.Builder(this).setTitle("Unsupported Action")
                    .setMessage("Please setup your email app first.")
                    .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).show();

        }
    }
}
