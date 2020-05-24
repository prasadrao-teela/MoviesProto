package com.goscale.assignment.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.goscale.assignment.di.scope.ViewModelScope
import com.goscale.assignment.viewmodel.MovieDetailViewModel
import com.goscale.assignment.viewmodel.MovieViewModel
import com.goscale.assignment.viewmodel.TvShowViewModel
import com.goscale.assignment.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by Prasad Rao on 23-05-2020 19:40
 **/
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelScope(MovieViewModel::class)
    abstract fun bindMovieViewModel(viewModel: MovieViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelScope(MovieDetailViewModel::class)
    abstract fun bindMovieDetailViewModel(viewModel: MovieDetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelScope(TvShowViewModel::class)
    abstract fun bindTvShowViewModel(viewModel: TvShowViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}