package com.trojanstudio.movie2.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

/**
 * Created by smjs2000 on 8/29/17.
 */
public class MovieListItemViewModel extends BaseObservable {

    private String imageUrl;
    private ItemClickEvent itemClickEvent;

    public MovieListItemViewModel(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Bindable
    public String getImageUrl() {
        return imageUrl;
    }

    public void setItemClickEvent(ItemClickEvent itemClickEvent) {
        this.itemClickEvent = itemClickEvent;
    }

    public void onCardClick(View view) {
        if (itemClickEvent != null) {
            itemClickEvent.onItemClick();
        }
    }

    public interface ItemClickEvent {
        void onItemClick();
    }
}
