package io.vinicius.parse.coroutines

import android.content.Context
import android.os.Build
import androidx.test.core.app.ApplicationProvider
import com.parse.Parse
import com.parse.ParseObject
import io.vinicius.parse.common.ktx.toDate
import io.vinicius.parse.common.model.Show
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.time.LocalDate

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P])
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class ParseObjectCoroutinesTest
{
    @Before
    fun `Initializing Parse`()
    {
        val context = ApplicationProvider.getApplicationContext<Context>()
        ParseObject.registerSubclass(Show::class.java)

        Parse.initialize(Parse.Configuration.Builder(context)
            .server("https://parse.vinicius.io/app/common")
            .applicationId("common")
            .build())
    }

    @Test
    fun `1- Saves a ParseObject`()
    {
        val show = Show().apply {
            imdbId = "tt0903747"
            title = "Breaking Bad"
            releaseDate = LocalDate.of(2008, 1, 20).toDate("UTC")
        }

        runBlockingTest {
            show.cr.save()
        }
    }

    @Test
    fun `2- Saves & Fetches a ParseObject`()
    {
        val show = Show().apply {
            imdbId = "tt3032476"
            title = "Better Call Saul"
            releaseDate = LocalDate.of(2015, 2, 8).toDate("UTC")
        }

        runBlockingTest {
            show.cr.save()
            val fetchedShow = show.cr.fetch()

            Assert.assertEquals(show.imdbId, fetchedShow.imdbId)
        }
    }
}