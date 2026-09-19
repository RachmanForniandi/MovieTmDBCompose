package rachman.forniandi.movietmdbcompose.presentation

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import rachman.forniandi.movietmdbcompose.MainActivity
import rachman.forniandi.movietmdbcompose.R

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class MovieListScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    private lateinit var appName: String

    @Before
    fun setUp() {
        hiltRule.inject()
        appName = composeRule.activity.getString(R.string.app_name)

        composeRule.waitUntil(timeoutMillis = 5_000L) {
            composeRule.onAllNodes(hasText(appName))
                .fetchSemanticsNodes().isNotEmpty()
        }
        composeRule.waitForIdle()
    }

    // ✅ POSITIF — TopAppBar dengan judul app tampil
    @Test
    fun movieListScreen_topBar_isDisplayed() {
        composeRule
            .onNodeWithText(appName)
            .assertIsDisplayed()
    }

    // ✅ POSITIF — tombol about dengan contentDescription "about_page" tampil
    @Test
    fun movieListScreen_aboutButton_isDisplayed() {
        composeRule
            .onNodeWithContentDescription("about_page")
            .assertIsDisplayed()
    }

    // ✅ POSITIF — tombol favorite dengan contentDescription "favorite_page" tampil
    @Test
    fun movieListScreen_favoriteButton_isDisplayed() {
        composeRule
            .onNodeWithContentDescription("favorite_page")
            .assertIsDisplayed()
    }

    // ✅ POSITIF — search field tampil (dicek lewat leading icon contentDescription "Search")
    @Test
    fun movieListScreen_searchField_isDisplayed() {
        composeRule
            .onNodeWithContentDescription("Search")
            .assertIsDisplayed()
    }

    // ✅ POSITIF — list movie tampil setelah data berhasil di-load
    @Test
    fun movieListScreen_movieGrid_isDisplayed_afterLoad() {
        composeRule.waitUntil(timeoutMillis = 10_000L) {
            composeRule
                .onAllNodesWithContentDescription("Poster", substring = true)
                .fetchSemanticsNodes().isNotEmpty()
        }

        composeRule
            .onAllNodesWithContentDescription("Poster", substring = true)
            .onFirst()
            .assertIsDisplayed()
    }

    // ✅ POSITIF — klik item movie menavigasi ke halaman detail
    @Test
    fun movieListScreen_clickMovieItem_navigatesToDetail() {
        composeRule.waitUntil(timeoutMillis = 10_000L) {
            composeRule
                .onAllNodesWithContentDescription("Poster", substring = true)
                .fetchSemanticsNodes().isNotEmpty()
        }

        composeRule
            .onAllNodesWithContentDescription("Poster", substring = true)
            .onFirst()
            .performClick()
        composeRule.waitForIdle()

        composeRule
            .onNodeWithContentDescription("Kembali")
            .assertIsDisplayed()
    }

    // ✅ POSITIF — klik about button menavigasi ke AboutScreen
    @Test
    fun movieListScreen_clickAboutButton_navigatesToAbout() {
        composeRule
            .onNodeWithContentDescription("about_page")
            .performClick()
        composeRule.waitForIdle()

        composeRule
            .onNodeWithText("Tentang")
            .assertIsDisplayed()
    }

    // ✅ POSITIF — klik favorite button menavigasi ke FavoriteScreen
    @Test
    fun movieListScreen_clickFavoriteButton_navigatesToFavorite() {
        composeRule
            .onNodeWithContentDescription("favorite_page")
            .performClick()
        composeRule.waitForIdle()

        composeRule
            .onNodeWithText("Film Favorit")
            .assertIsDisplayed()
    }

    // ✅ POSITIF — search dengan query valid menampilkan hasil pencarian
    @Test
    fun movieListScreen_searchWithValidQuery_showsResults() {
        composeRule.waitUntil(timeoutMillis = 10_000L) {
            composeRule
                .onAllNodesWithContentDescription("Poster", substring = true)
                .fetchSemanticsNodes().isNotEmpty()
        }

        composeRule
            .onAllNodesWithText("Cari film...")
            .onFirst()
            .performTextInput("Avengers")

        composeRule.waitUntil(timeoutMillis = 10_000L) {
            composeRule
                .onAllNodesWithContentDescription("Poster", substring = true)
                .fetchSemanticsNodes().isNotEmpty()
        }

        composeRule
            .onAllNodesWithContentDescription("Poster", substring = true)
            .onFirst()
            .assertIsDisplayed()
    }

    // ❌ NEGATIF — search dengan query yang tidak ada hasilnya menampilkan pesan kosong
    @Test
    fun movieListScreen_searchWithNoResult_showsEmptyMessage() {
        composeRule.waitUntil(timeoutMillis = 10_000L) {
            composeRule
                .onAllNodesWithContentDescription("Poster", substring = true)
                .fetchSemanticsNodes().isNotEmpty()
        }

        composeRule
            .onAllNodesWithText("Cari film...")
            .onFirst()
            .performTextInput("xyzxyzxyznotamovie123456")

        composeRule.waitUntil(timeoutMillis = 10_000L) {
            composeRule
                .onAllNodes(hasText("Film tidak ditemukan"))
                .fetchSemanticsNodes().isNotEmpty()
        }

        composeRule
            .onNodeWithText("Film tidak ditemukan")
            .assertIsDisplayed()
    }

    // ✅ POSITIF — clear search kembali menampilkan popular movies
    @Test
    fun movieListScreen_clearSearch_showsPopularMovies() {
        composeRule.waitUntil(timeoutMillis = 10_000L) {
            composeRule
                .onAllNodesWithContentDescription("Poster", substring = true)
                .fetchSemanticsNodes().isNotEmpty()
        }

        composeRule
            .onAllNodesWithText("Cari film...")
            .onFirst()
            .performTextInput("Avengers")
        composeRule.waitForIdle()

        composeRule
            .onNode(hasText("Avengers"))
            .performTextClearance()

        composeRule.waitUntil(timeoutMillis = 10_000L) {
            composeRule
                .onAllNodesWithContentDescription("Poster", substring = true)
                .fetchSemanticsNodes().isNotEmpty()
        }

        composeRule
            .onAllNodesWithContentDescription("Poster", substring = true)
            .onFirst()
            .assertIsDisplayed()
    }
}
