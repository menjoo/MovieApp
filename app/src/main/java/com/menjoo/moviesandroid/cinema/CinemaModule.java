package com.menjoo.moviesandroid.cinema;

import android.support.annotation.NonNull;

import com.menjoo.moviesandroid.injection.ActivityScoped;
import com.menjoo.moviesandroid.injection.FragmentScoped;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class CinemaModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract CinemaFragment cinemaFragment();

    @ActivityScoped
    @Binds
    abstract CinemaContract.Presenter cinemaPresenter(CinemaPresenter presenter);

    @ActivityScoped
    @Provides
    @NonNull
    static CinemaViewModelFactory provideCinemaViewModelFactory(@NonNull CinemaContract.Presenter presenter) {
        return new CinemaViewModelFactory(presenter);
    }
}
