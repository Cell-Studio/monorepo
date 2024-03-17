package com.cellstudio.pikaroad.di

import com.cellstudio.pikaroad.feature.movie.MovieService
import com.cellstudio.pikaroad.feature.movie.MovieServiceFacade
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
internal interface AppActivityModule {
    @Binds
    fun bindMovieService(movieService: MovieServiceFacade): MovieService
}