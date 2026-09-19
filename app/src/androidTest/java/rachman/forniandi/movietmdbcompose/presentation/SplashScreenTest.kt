package rachman.forniandi.movietmdbcompose.presentation


import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import rachman.forniandi.movietmdbcompose.MainActivity

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class SplashScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    // ✅ POSITIF — splash muncul saat app pertama dibuka
    @Test
    fun splashScreen_isDisplayed_onAppLaunch() {
        composeRule
            .onNodeWithText("MovieTmdbCompose")
            .assertIsDisplayed()
    }

    // ✅ POSITIF — teks "Powered by" tampil di splash
    @Test
    fun splashScreen_poweredByText_isDisplayed() {
        composeRule
            .onNodeWithText("Powered by")
            .assertIsDisplayed()
    }

    // ✅ POSITIF — setelah 3 detik splash selesai, berpindah ke MovieList
    @Test
    fun splashScreen_navigatesToMovieList_afterDelay() {
        // mainClock.advanceTimeBy hanya untuk animation clock, tidak bisa
        // skip coroutine delay(). Gunakan waitUntil dengan real time timeout.
        composeRule.waitUntil(timeoutMillis = 3_000L) {
            // Tunggu sampai TopAppBar MovieList muncul — tanda sudah pindah screen
            composeRule
                .onAllNodes(hasText("The Movie Database (TMDb)"))
                .fetchSemanticsNodes()
                .isNotEmpty()
        }

        composeRule
            .onNodeWithText("The Movie Database (TMDb)")
            .assertIsDisplayed()
    }
}
