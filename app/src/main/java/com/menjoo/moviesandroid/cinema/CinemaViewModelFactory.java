package com.menjoo.moviesandroid.cinema;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import javax.inject.Inject;

public class CinemaViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private CinemaContract.Presenter cinemaPresenter;

    @Inject
    public CinemaViewModelFactory(CinemaContract.Presenter cinemaPresenter) {
        this.cinemaPresenter = cinemaPresenter;
    }

    @Override
    @NonNull
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        T t = super.create(modelClass);
        if (t instanceof CinemaViewModel) {
            ((CinemaViewModel) t).presenter = cinemaPresenter;
        }
        return t;
    }
}
