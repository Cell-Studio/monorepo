package com.cellstudio.pikaroad.feature.movie

import android.content.Context
import androidx.compose.runtime.staticCompositionLocalOf
import dagger.hilt.android.qualifiers.ActivityContext
import io.flutter.embedding.android.FlutterActivity
import javax.inject.Inject

val LocalMovieService = staticCompositionLocalOf<MovieService> {
    error("CompositionLocal LocalMovieService not present")
}

interface MovieService {
    fun openMovieFlow()
}

internal class MovieServiceFacade @Inject constructor(@ActivityContext private val context: Context): MovieService {
    override fun openMovieFlow() {
        context.startActivity(
            FlutterActivity.createDefaultIntent(context)
        )
    }
}