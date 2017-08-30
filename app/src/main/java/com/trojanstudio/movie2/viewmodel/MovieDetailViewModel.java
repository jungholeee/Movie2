package com.trojanstudio.movie2.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

/**
 * Created by smjs2000 on 8/29/17.
 */
public class MovieDetailViewModel extends BaseObservable {

    private String title;
    private String imageUrl;
    private String overView;

    private DetailButtonEvent detailButtonEvent;

    public MovieDetailViewModel(String title, String imageUrl, String overView) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.overView = overView;
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    @Bindable
    public String getImageUrl() {
        return imageUrl;
    }

    @Bindable
    public String getOverView() {
        return overView;
    }

    public void setDetailButtonEvent(DetailButtonEvent detailButtonEvent) {
        this.detailButtonEvent = detailButtonEvent;
    }

    public void onButtonClick(View view) {
        if (detailButtonEvent != null) {
            detailButtonEvent.onButtonClick();
        }
    }

    public interface DetailButtonEvent {
        void onButtonClick();
    }
}
