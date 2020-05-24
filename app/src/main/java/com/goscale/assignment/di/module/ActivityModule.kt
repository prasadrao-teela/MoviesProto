package com.goscale.assignment.di.module

import com.goscale.assignment.view.ui.HomeActivity
import com.goscale.assignment.view.ui.details.MovieDetailsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Prasad Rao on 23-05-2020 21:37
 **/
@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [FragmentBuilderModule::class])
    abstract fun contributeHomeActivity(): HomeActivity

    @ContributesAndroidInjector
    abstract fun contributeMovieDetailsActivity(): MovieDetailsActivity
}