package com.goscale.assignment.di.module

import com.goscale.assignment.view.ui.main.HomeViewPagerFragment
import com.goscale.assignment.view.ui.main.MovieListFragment
import com.goscale.assignment.view.ui.main.TvShowListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Prasad Rao on 23-05-2020 21:37
 **/
@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeHomeViewPagerFragment(): HomeViewPagerFragment

    @ContributesAndroidInjector
    abstract fun contributeMovieListFragment(): MovieListFragment

    @ContributesAndroidInjector
    abstract fun contributeTvShowListFragment(): TvShowListFragment

}