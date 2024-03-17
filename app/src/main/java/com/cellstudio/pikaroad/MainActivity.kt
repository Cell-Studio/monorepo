package com.cellstudio.pikaroad

import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.CompositionLocalProvider
import com.cellstudio.core.browser.BrowserService
import com.cellstudio.pikaroad.feature.home.ui.home.HOME_ROUTE
import com.cellstudio.pikaroad.feature.movie.LocalMovieService
import com.cellstudio.pikaroad.feature.movie.MovieService
import com.cellstudio.pikaroad.feature.onboarding.navigation.GENDER_SCREEN
import com.cellstudio.pikaroad.feature.onboarding.navigation.ONBOARDING_ROUTE
import com.cellstudio.pikaroad.navigation.PikaNavHost
import com.cellstudio.pikaroad.ui.theme.PikaRoadTheme
import com.cellstudio.tcg.ui.navigation.TCG_ROUTE
import com.cellstudio.ui.composition.LocalBrowserService
import com.cellstudio.vgc.ui.navigation.VGC_ROUTE
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

private const val PARAM_SCREENTYPE = "screenType"
private const val ONBOARDING_DEEPLINK = "onboarding"
private const val VGC_DEEPLINK = "vgc"
private const val TCG_DEEPLINK = "tcg"
private const val HOME_DEEPLINK = "home"
private const val GENDER_DEEPLINK = "gender"

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var browserService: BrowserService

    @Inject
    lateinit var movieService: MovieService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge(navigationBarStyle = SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT))

        val data = intent.data?.let { handleDeeplink(it) }

        setContent {
            CompositionLocalProvider(LocalBrowserService provides browserService) {
                CompositionLocalProvider(LocalMovieService provides movieService) {
                    PikaRoadTheme {
                        PikaNavHost(
                            startDestination = data ?: HOME_ROUTE
                        )
                    }
                }
            }
        }
    }

    private fun handleDeeplink(uri: Uri): String {
        val screenType = uri.getQueryParameter(PARAM_SCREENTYPE)?: HOME_DEEPLINK
        val route = when (screenType.lowercase()) {
            ONBOARDING_DEEPLINK -> ONBOARDING_ROUTE
            VGC_DEEPLINK -> VGC_ROUTE
            TCG_DEEPLINK -> TCG_ROUTE
            GENDER_DEEPLINK -> "$ONBOARDING_ROUTE/$GENDER_SCREEN"
            else -> HOME_ROUTE
        }

        return route
    }
}