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
import androidx.compose.ui.test.performScrollTo
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
class DetailMovieScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        hiltRule.inject()

        // FIX: timeout dinaikkan ke 5000ms — sebelumnya 3000ms sama persis
        // dengan delay(3000L) di SplashScreen, tidak ada buffer sama sekali
        composeRule.waitUntil(timeoutMillis = 5_000L) {
            composeRule.onAllNodes(hasText("Movie TmDB"))
                .fetchSemanticsNodes().isNotEmpty()
        }
        composeRule.waitForIdle()   // ← dikembalikan, jangan di-comment

        // Tunggu grid movie muncul lalu klik item pertama
        composeRule.waitUntil(timeoutMillis = 5_000L) {
            composeRule
                .onAllNodesWithContentDescription("Poster", substring = true)
                .fetchSemanticsNodes().isNotEmpty()
        }
        composeRule
            .onAllNodesWithContentDescription("Poster", substring = true)
            .onFirst()
            .performClick()
        composeRule.waitForIdle()   // ← dikembalikan, agar navigasi ke detail selesai dulu
    }

    // ✅ POSITIF — back button tampil di detail screen
    @Test
    fun movieDetailScreen_backButton_isDisplayed() {
        composeRule
            .onNodeWithContentDescription("Kembali")
            .assertIsDisplayed()
    }

    // ✅ POSITIF — tombol favorit tampil setelah data load
    @Test
    fun movieDetailScreen_favoriteButton_isDisplayed() {
        composeRule.waitUntil(timeoutMillis = 5_000L) {
            composeRule
                .onAllNodesWithContentDescription("Tambah ke favorit")
                .fetchSemanticsNodes().isNotEmpty()
        }
        composeRule
            .onNodeWithContentDescription("Tambah ke favorit")
            .assertIsDisplayed()
    }

    // ✅ POSITIF — section Sinopsis tampil
    @Test
    fun movieDetailScreen_sinopsis_isDisplayed() {
        composeRule.waitUntil(timeoutMillis = 5_000L) {
            composeRule
                .onAllNodesWithText("Sinopsis")
                .fetchSemanticsNodes().isNotEmpty()
        }
        composeRule
            .onNodeWithText("Sinopsis")
            .performScrollTo()
            .assertIsDisplayed()
    }

    // ✅ POSITIF — section Genre tampil
    @Test
    fun movieDetailScreen_genre_isDisplayed() {
        composeRule.waitUntil(timeoutMillis = 5_000L) {
            composeRule
                .onAllNodesWithText("Genre")
                .fetchSemanticsNodes().isNotEmpty()
        }
        composeRule
            .onNodeWithText("Genre")
            .performScrollTo()
            .assertIsDisplayed()
    }

    // ✅ POSITIF — add favorit berhasil mengubah ikon jadi filled
    @Test
    fun movieDetailScreen_addFavorite_changesIconToFilled() {
        composeRule.waitUntil(timeoutMillis = 5_000L) {
            composeRule
                .onAllNodesWithContentDescription("Tambah ke favorit")
                .fetchSemanticsNodes().isNotEmpty()
        }
        composeRule
            .onNodeWithContentDescription("Tambah ke favorit")
            .performClick()
        composeRule.waitForIdle()

        composeRule
            .onNodeWithContentDescription("Hapus dari favorit")
            .assertIsDisplayed()
    }

    // ✅ POSITIF — remove favorit berhasil mengubah ikon jadi border
    @Test
    fun movieDetailScreen_removeFavorite_changesIconToBorder() {
        composeRule.waitUntil(timeoutMillis = 5_000L) {
            composeRule
                .onAllNodesWithContentDescription("Tambah ke favorit")
                .fetchSemanticsNodes().isNotEmpty()
        }
        composeRule.onNodeWithContentDescription("Tambah ke favorit").performClick()
        composeRule.waitForIdle()

        composeRule.onNodeWithContentDescription("Hapus dari favorit").performClick()
        composeRule.waitForIdle()

        composeRule
            .onNodeWithContentDescription("Tambah ke favorit")
            .assertIsDisplayed()
    }

    // ✅ POSITIF — klik back kembali ke list
    @Test
    fun movieDetailScreen_clickBack_returnsToList() {
        composeRule
            .onNodeWithContentDescription("Kembali")
            .performClick()
        composeRule.waitForIdle()

        composeRule
            .onNodeWithText("Movie TmDB")
            .assertIsDisplayed()
    }

    // ❌ NEGATIF — detail screen tidak menampilkan search bar
    @Test
    fun movieDetailScreen_doesNotShow_searchBar() {
        composeRule
            .onAllNodesWithText("Cari film...")
            .fetchSemanticsNodes()
            .also { assert(it.isEmpty()) }
    }
}
